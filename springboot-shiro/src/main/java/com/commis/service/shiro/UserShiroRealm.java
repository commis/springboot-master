package com.commis.service.shiro;

import com.commis.dao.PermissionRepository;
import com.commis.dao.RoleRepository;
import com.commis.dao.UserRepository;
import com.commis.dao.entity.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserShiroRealm extends AuthorizingRealm {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        log.info("权限配置-->UserShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserBean userBean = (UserBean) principal.getPrimaryPrincipal();
        roleRepository.findRoleByUsername(userBean.getUsername()).stream().forEach(roleBean -> {
            authorizationInfo.addRole(roleBean.getName());
            permissionRepository.findPermissionByRoleId(roleBean.getId()).stream().forEach(
                permissionBean -> {
                    authorizationInfo.addStringPermission(permissionBean.getMethod());
                }
            );
        });
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("UserShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        log.info("token {}", token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserBean userInfo = userRepository.findByUsername(username);
        log.info("----->>userInfo={}", userInfo);
        if (userInfo == null) {
            ////没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
            userInfo, //用户名
            userInfo.getPassword(), //密码
            ByteSource.Util.bytes(userInfo.getTelephone()),//salt=username+salt
            getName()  //realm name
        );
        return authenticationInfo;
    }

}
