package com.shenchao.bos.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by shenchao on 2016/12/5.
 */
@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    //接受上传文件的
    public void setZipFile(File zipFile) {
        this.zipFile = zipFile;
    }

    private File zipFile;

    @Resource
    private RepositoryService repositoryService;

    public String list() {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.latestVersion();
        query.orderByProcessDefinitionName().desc();
        List<ProcessDefinition> list = query.list();
        ActionContext.getContext().getValueStack().set("list",list);
        return "list";
    }

    public String deploy() throws FileNotFoundException {
        repositoryService.createDeployment().addZipInputStream(new ZipInputStream(new FileInputStream(zipFile))).deploy();
        return "toList";
    }

    /**
     *查看png的图片
     * @return
     */
    public String viewpng(){
        InputStream in = repositoryService.getProcessDiagram(id);
        ActionContext.getContext().getValueStack().set("pngStream",in);
        return "showpng";
    }

    public String delete() {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
        String deltag = "1";
        try {
            repositoryService.deleteDeployment(processDefinition.getDeploymentId());
        } catch (Exception e) {
            e.printStackTrace();
            deltag = "0";
            ActionContext.getContext().getValueStack().set("deltag",deltag);
            ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
            query.latestVersion();
            query.orderByProcessDefinitionName().desc();
            List<ProcessDefinition> list = query.list();
            ActionContext.getContext().getValueStack().set("list",list);
            return "list";
        }
        return "toList";
    }

}
