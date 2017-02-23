package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.IWorkordermanagerDao;
import com.shenchao.bos.domain.Workordermanager;
import com.shenchao.bos.service.IWorkordermanagerService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shenchao on 2016/12/4.
 */
@Service
@Transactional
public class WorkordermanagerServiceImpl implements IWorkordermanagerService {

    @Resource
    private IWorkordermanagerDao workordermanagerDao;

    @Resource
    private HistoryService historyService;

    @Resource
    private TaskService taskService;

    @Resource
    private RuntimeService runtimeService;

    @Override
    public void add(Workordermanager workordermanager) {
        workordermanager.setUpdatetime(new Date());
        workordermanagerDao.save(workordermanager);
    }

    @Override
    public List<Workordermanager> findListNotStart() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Workordermanager.class);
        criteria.add(Restrictions.eq("start", "0"));
        return workordermanagerDao.findByCriteria(criteria);
    }

    @Override
    public void start(String id) {
        Workordermanager workordermanager = workordermanagerDao.findById(id);
        workordermanager.setStart("1");
        String processDefinitionKey = "transfer";//流程定义key
        String businessKey = id;
        Map<String, Object> variables = new HashMap<>();
        variables.put("业务数据", workordermanager);
        runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    }

    @Override
    public Workordermanager findById(String workordermanageId) {
        return workordermanagerDao.findById(workordermanageId);
    }

    @Override
    public void checkWorkordermanage(String taskId, Integer check, String workordermanageId) {
        Workordermanager workordermanager = workordermanagerDao.findById(workordermanageId);
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> variables = new HashMap<>();
        variables.put("check", check);
        String processInstanceId = task.getProcessInstanceId();
        taskService.complete(taskId, variables);
        if (check == 0) {
            workordermanager.setStart("0");
            historyService.deleteHistoricProcessInstance(processInstanceId);
        }
    }
}
