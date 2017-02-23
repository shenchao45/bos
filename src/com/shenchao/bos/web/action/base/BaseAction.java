package com.shenchao.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shenchao.bos.service.*;
import com.shenchao.bos.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by shenchao on 2016/11/23.
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    //注入Service
    @Autowired
    protected IUserService userService;
    @Autowired
    protected ISubareaService subareaService;
    @Autowired
    protected CustomerService customerService;
    @Autowired
    protected IStaffService staffService;
    @Autowired
    protected IRegionService regionService;
    @Autowired
    protected IDecidedzoneService decidedzoneService;
    @Resource
    protected IRoleSerivce roleSerivce;
    @Resource
    protected IFunctionService functionService;

    protected PageBean pageBean = new PageBean();
    protected DetachedCriteria detachedCriteria;

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }
    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    protected T model;
    public BaseAction(){
        ParameterizedType genericSuperclass = null;
        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
            genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        }else{
            genericSuperclass = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
        }
        try {
            Class<T> entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            model = entityClass.newInstance();
            detachedCriteria = DetachedCriteria.forClass(entityClass);
            pageBean.setDetachedCriteria(detachedCriteria);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void writePageBean2Json(PageBean pageBean,String[] excludes) throws IOException {
        writeObject2Json(pageBean,excludes);
    }
    @Override
    public T getModel() {
        return model;
    }

    public void writeList2Json(List all, String[] excludes) throws IOException {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        JSONArray array = JSONArray.fromObject(all,jsonConfig);
        String s = array.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(s);
    }

    public void writeObject2Json(Object object,String[] excludes) throws IOException {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        //将pageBean 对象转换为json返回
        JSONObject jsonObject = JSONObject.fromObject(object,jsonConfig);
        String s = jsonObject.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(s);
    }
}
