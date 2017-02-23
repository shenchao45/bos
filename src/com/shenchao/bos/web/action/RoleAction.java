package com.shenchao.bos.web.action;

import com.shenchao.bos.domain.Role;
import com.shenchao.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String add(){
        roleSerivce.add(model,ids);
        return "list";
    }

    public String pageQuery() throws IOException {
        roleSerivce.pageQuery(pageBean);
        String[] excludes = {"functions","users","detachedCriteria"};
        this.writePageBean2Json(pageBean,excludes);
        return NONE;
    }

    public String listAjax() throws IOException {
        List<Role> list = roleSerivce.findAll();
        this.writeList2Json(list,new String[]{"functions","users"});
        return NONE;
    }
}
