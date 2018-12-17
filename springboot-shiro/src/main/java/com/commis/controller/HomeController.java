package com.commis.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "/index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        log.info("HomeController.login");

        String msg = "success";
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        Object exception = request.getAttribute("shiroLoginFailure");
        if (exception != null) {
            if (UnknownAccountException.class.isInstance(exception)) {
                log.info("账户不存在");
                msg = "账户不存在或密码不正确";
            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                log.info("密码不正确");
                msg = "账户不存在或密码不正确";
            } else {
                log.info("其他异常");
                msg = "其他异常";
            }
        }

        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "login";
    }

    @GetMapping("/403")
    public String unauthorizedRole() {
        log.info("------没有权限-------");
        return "403";
    }
}
