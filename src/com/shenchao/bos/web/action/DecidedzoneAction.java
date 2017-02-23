package com.shenchao.bos.web.action;

import cn.itcast.crm.domain.Customer;
import com.shenchao.bos.domain.Decidedzone;
import com.shenchao.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by shenchao on 2016/11/27.
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    private Integer[] customerIds;

    public Integer[] getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(Integer[] customerIds) {
        this.customerIds = customerIds;
    }

    private String[] subareaid;

    public String[] getSubareaid() {
        return subareaid;
    }

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String add() {
        decidedzoneService.save(model,subareaid);
        return "list";
    }

    public String pageQuery() throws IOException {
        decidedzoneService.pageQuery(pageBean);
        this.writePageBean2Json(pageBean, new String[]{"subareas","decidedzones"});
        return NONE;
    }

    public String findnoassigncustomers() throws IOException {
        List<Customer> customers = customerService.findnoassociationCustomers();
        this.writeList2Json(customers, new String[]{"decidedzone_id"});
        return NONE;
    }

    public String findhasassigncustomers() throws IOException {
        List<Customer> customers = customerService.findhasassociationCustomers(model.getId());
        this.writeList2Json(customers,new String[]{"decidedzone_id","address"});
        return NONE;
    }


    public String assigncustomerstodecidedzone() {
        customerService.assignCustomersToDecidedZone(customerIds,model.getId());
        return "list";
    }
}
