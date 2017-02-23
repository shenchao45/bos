package com.shenchao.bos.utils;

import com.shenchao.bos.domain.User;
import org.apache.struts2.ServletActionContext;

/**
 * Created by shenchao on 2016/12/1.
 */
public class BosContext {
    public static User getLogin() {
        return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
    }
}
