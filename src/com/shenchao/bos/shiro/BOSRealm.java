package com.shenchao.bos.shiro;

import com.shenchao.bos.dao.IFunctionDao;
import com.shenchao.bos.dao.IUserDao;
import com.shenchao.bos.domain.Function;
import com.shenchao.bos.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
public class BOSRealm extends AuthorizingRealm {

    @Resource
    private IUserDao userDao;

    @Resource
    private IFunctionDao functionDao;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Function> list = null;
//        if (user.getUsername().equals("admin")) {
            //超级管理员，有全部权限
            list = functionDao.findAll();
//        }else{
//            list = functionDao.findListByUserid(user.getId());
//        }
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证方法"+new String((char[]) authenticationToken.getCredentials())+":"+authenticationToken.getPrincipal());
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User u = userDao.findByUsername(username);
        if (u == null) {
            return null;
        }else{
            String password = u.getPassword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(u, password, this.getClass().getSimpleName());
            return info;//null表示当前用户名不存在
        }
    }
}
