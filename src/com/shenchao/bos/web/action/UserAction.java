package com.shenchao.bos.web.action;

import com.shenchao.bos.domain.User;
import com.shenchao.bos.utils.MD5Utils;
import com.shenchao.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by shenchao on 2016/11/23.
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

//    通过属性驱动接受验证码
    private String checkcode;

    private String[] roleIds;

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String login() {
        Object key = ServletActionContext.getRequest().getSession().getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
//            User u = userService.login(model);
//            if (u != null) {
//                //登录成功
//                ServletActionContext.getRequest().getSession().setAttribute("loginUser",u);
//                return "home";
//            }else{
//                //登录失败
//                addActionError(this.getText("loginError"));
//                return "login";
//            }
            Subject subject = SecurityUtils.getSubject();//未认证
            AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
            try {
                subject.login(token);
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
                return "home";
            }catch (UnknownAccountException e){
                addActionError(this.getText("usernamenotfound"));
                return "login";
            }catch (Exception e){
                addActionError(this.getText("用户名或者密码错误"));
                return "login";
            }
        }
        addActionError(this.getText("validateCodeError"));
        return "login";
    }

    public String logout() {
        ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
        return "login";
    }
    public String resetPassword() throws IOException {
        User u = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        String flag = "1";
        u.setPassword(model.getPassword().trim());
        try {
            userService.resetPassword(u);
        } catch (Exception e) {
            flag = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(flag);
        return NONE;
    }

    public String pageQuery() throws IOException {
        userService.pageQuery(pageBean);
        this.writePageBean2Json(pageBean, new String[]{"noticebills","roles"});
        return NONE;
    }

    public String add() {
        userService.add(model,roleIds);
        return "list";
    }
}
