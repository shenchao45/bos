package com.shenchao.bos.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shenchao.bos.domain.Workordermanager;
import com.shenchao.bos.service.IWorkordermanagerService;
import com.shenchao.bos.utils.BosContext;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by shenchao on 2016/12/8.
 */
@Controller
@Scope("prototype")
public class TaskAction extends ActionSupport {
    @Resource
    private TaskService taskService;

    private Integer check;


    public void setCheck(Integer check) {
        this.check = check;
    }

    @Resource
    private IWorkordermanagerService workordermanagerService;

    @Resource
    private RuntimeService runtimeService;

    private String taskId;

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 查询组任务
     * @return
     */
    public String findGroupTask() {
        TaskQuery query = taskService.createTaskQuery();
        String candidateUser = BosContext.getLogin().getId();
        List<Task> list = query.taskCandidateUser(candidateUser).list();
        ActionContext.getContext().getValueStack().set("list",list);
        return "grouptasklist";
    }

    public String showData() throws IOException {
        Map<String, Object> variables = taskService.getVariables(taskId);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(variables);
        return NONE;
    }

    /**
     * 拾取任务
     * @return
     */
    public String takeTask() {
        taskService.claim(taskId,BosContext.getLogin().getId());
        return "togrouptasklist";
    }

    /**
     * 查询个人任务
     * @return
     */
    public String findPersonalTask() {
        TaskQuery query = taskService.createTaskQuery();
        List<Task> list = query.taskAssignee(BosContext.getLogin().getId()).list();
        ActionContext.getContext().getValueStack().set("list",list);
        return "personaltasklist";
    }

    public String checkWorkOrderManage() {
        // 根据任务id查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        // 根据任务对象查询流程实例id
        String processInstanceId = task.getProcessInstanceId();
        // 根据流程实例id查询流程实例对象
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        String workordermanageId = processInstance.getBusinessKey();
        Workordermanager workordermanager = workordermanagerService.findById(workordermanageId);
        if(check == null){
            //跳转到审核页面
            // 跳转到一个审核工作单页面，展示当前对应的工作单信息
            ActionContext.getContext().getValueStack().set("map", workordermanager);
            ActionContext.getContext().getValueStack().set("taskId", taskId);
            return "check";
        }else{
            workordermanagerService.checkWorkordermanage(taskId,check,workordermanageId);
            return "topersonaltasklist";
        }
    }

    public String outStore() {
        taskService.complete(taskId);
        return "topersonaltasklist";
    }
    public String transferGoods() {
        taskService.complete(taskId);
        return "topersonaltasklist";
    }
    public String receive() {
        taskService.complete(taskId);
        return "topersonaltasklist";
    }
}
