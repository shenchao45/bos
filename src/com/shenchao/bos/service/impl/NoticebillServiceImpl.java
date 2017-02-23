package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.IDecidedzoneDao;
import com.shenchao.bos.dao.INoticebillDao;
import com.shenchao.bos.dao.IWorkbillDao;
import com.shenchao.bos.domain.Decidedzone;
import com.shenchao.bos.domain.Noticebill;
import com.shenchao.bos.domain.Staff;
import com.shenchao.bos.domain.Workbill;
import com.shenchao.bos.service.CustomerService;
import com.shenchao.bos.service.INoticebillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by shenchao on 2016/12/1.
 */
@Transactional
@Service
public class NoticebillServiceImpl implements INoticebillService {
    @Resource
    private INoticebillDao noticebillDao;

    @Resource
    private CustomerService customerService;

    @Resource
    private IDecidedzoneDao decidedzoneDao;

    @Resource
    private IWorkbillDao workbillDao;

    @Override
    public void save(Noticebill model) {
        noticebillDao.save(model);
        String pickaddress = model.getPickaddress();
        String did = customerService.findDecidedzoneIdByPickaddress(pickaddress);
        if (did != null) {
            //查询到了定区id，可以自动分单
            Decidedzone decidedzone = decidedzoneDao.findById(did);
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            model.setOrdertype("自动");
            //需要为取派员创建一个工单
            Workbill workbill = new Workbill();
            workbill.setStaff(staff);
            workbill.setAttachbilltimes(0);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setNoticebill(model);
            workbill.setPickstate("未取件");
            workbill.setRemark(model.getRemark());
            workbill.setType("新单");
            workbillDao.save(workbill);
            //发送短信给取派员
        }else{
            //没有查询到，转为人工分单
            model.setOrdertype("人工");
        }

    }
}
