package com.shenchao.bos.web.action;

import cn.itcast.crm.domain.Customer;
import com.shenchao.bos.domain.Noticebill;
import com.shenchao.bos.domain.User;
import com.shenchao.bos.service.INoticebillService;
import com.shenchao.bos.utils.BosContext;
import com.shenchao.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by shenchao on 2016/11/30.
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
    @Resource
    private INoticebillService noticebillService;

    public String findCustomerByTel() throws IOException {
        Customer customer = customerService.findCustomerByPhoneNumber(this.model.getTelephone());
        this.writeObject2Json(customer,new String[]{"workbills"});
        return NONE;
    }

    /**
     * 保存一个业务通知单数据，根据取件地址分单
     * @return
     */
    public String add() {
        User login = BosContext.getLogin();
        model.setUser(login);
        noticebillService.save(model);
        return "addUI";
    }
}
