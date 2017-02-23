package com.shenchao.bos.web.action;

import com.shenchao.bos.domain.Function;
import com.shenchao.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * 权限管理的Action
 * Created by shenchao on 2016/12/3.
 */
@Scope("prototype")
@Controller
public class FunctionAction extends BaseAction<Function> {


    public String pageQuery() throws IOException {
        pageBean.setCurrentPage(Integer.parseInt(model.getPage()));
        functionService.pageQuery(pageBean);
        String[] excludes = {"function","functions","roles","detachedCriteria"};
        this.writePageBean2Json(pageBean,excludes);
        return NONE;
    }

    public String listAjax() throws IOException {
        List<Function> list = functionService.findAll();
        String[] excludes = {"function","functions","roles"};
        this.writeList2Json(list,excludes);
        return NONE;
    }

    public String add(){
        functionService.add(model);
        return NONE;
    }

    public String findMenu() throws IOException {
        List<Function> menu = functionService.findMenu();
        this.writeList2Json(menu,new String[]{"roles","functions"});
        return NONE;
    }
}
