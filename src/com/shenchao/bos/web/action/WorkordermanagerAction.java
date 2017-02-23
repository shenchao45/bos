package com.shenchao.bos.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.shenchao.bos.domain.Workordermanager;
import com.shenchao.bos.service.IWorkordermanagerService;
import com.shenchao.bos.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by shenchao on 2016/12/4.
 */
@Controller
@Scope("prototype")
public class WorkordermanagerAction extends BaseAction<Workordermanager> {
    @Resource
    private IWorkordermanagerService workordermanagerService;
    public String add() throws IOException {
        int flag = 1;
        try {
            workordermanagerService.add(model);
        } catch (Exception e) {
            e.printStackTrace();
            flag = 0;
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(flag);
        return NONE;
    }

    public String list() {
        List<Workordermanager> list = workordermanagerService.findListNotStart();
        ActionContext.getContext().getValueStack().set("list",list);
        return "list";
    }

    public String start() {
        String id = model.getId();
        workordermanagerService.start(id);
        return "toList";
    }
}
