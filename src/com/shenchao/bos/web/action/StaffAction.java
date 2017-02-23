package com.shenchao.bos.web.action;

import cn.itcast.crm.domain.Customer;
import com.shenchao.bos.domain.Staff;
import com.shenchao.bos.web.action.base.BaseAction;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by shenchao on 2016/11/24.
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    //注册属性记录
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String add() {
        System.out.println(model.getName());
        staffService.save(model);
        return "list";
    }

    /**
     * 分页查询
     * @return
     * @throws IOException
     */
    public String pageQuery() throws IOException {
        staffService.pageQuery(pageBean);
        this.writePageBean2Json(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","decidedzones"});
        return NONE;
    }

    /**
     * 批量删除功能(逻辑删除)
     * @return
     */
    @RequiresPermissions("staff")
    public String delete(){
        staffService.deleteBatch(ids);
        return "list";
    }

    /**
     * 修改取派员信息
     * @return
     */
    public String edit() {
        Staff staff = staffService.findById(model.getId());
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setStation(model.getStation());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staffService.update(staff);
        return "list";
    }

    /**
     * 查询没有作废的取派员
     * @return
     */
    public String listAjax() throws IOException {
        List<Staff> listNotDel = staffService.findListNotDel();
        this.writeList2Json(listNotDel,new String[]{"decidedzones"});
        return NONE;
    }



    public String test() throws IOException {
        List<Customer> customers = customerService.findnoassociationCustomers();
        this.writeList2Json(customers,new String[]{});
        return NONE;
    }
}
