package com.shenchao.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shenchao.bos.domain.User;
import org.apache.struts2.ServletActionContext;

/**
 * Created by shenchao on 2016/11/23.
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "login";
        }
        return actionInvocation.invoke();//放行
    }
}
