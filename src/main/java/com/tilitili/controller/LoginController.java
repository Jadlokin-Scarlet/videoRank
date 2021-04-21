package com.tilitili.controller;

import com.tilitili.common.entity.Admin;
import com.tilitili.common.entity.Users;
import com.tilitili.common.entity.view.BaseModel;
import com.tilitili.common.mapper.UsersMapper;
import com.tilitili.common.utils.Asserts;
import com.tilitili.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Slf4j
@Controller
@RequestMapping("/api/users")
public class LoginController extends BaseController {
    private final UsersMapper usersMapper;
    private final UsersService usersService;

    @Autowired
    public LoginController(UsersMapper usersMapper, UsersService usersService) {
        this.usersMapper = usersMapper;
        this.usersService = usersService;
    }

    @GetMapping("/isLogin")
    @ResponseBody
    public BaseModel isLogin(@SessionAttribute(value = "users", required = false) Users users) {
        if (users == null) {
            return new BaseModel("请重新登陆");
        }else {
            return new BaseModel("已登录", true, users);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public BaseModel login(@RequestBody Users users, HttpSession session) {
        Asserts.notBlank(users.getPassword(), "密码");
        if (isBlank(users.getName()) && isBlank(users.getEmail()) && isBlank(users.getPhone())) {
            return new BaseModel("用户名 is blank");
        }
        Users oldUsers = usersService.login(users);
        if (oldUsers == null) {
            return new BaseModel("用户名密码错误");
        }
        session.setAttribute("users", oldUsers);
        return new BaseModel("登陆成功", true, oldUsers);
    }

    @PostMapping("/loginOut")
    @ResponseBody
    public BaseModel loginOut(HttpSession session) {
        session.removeAttribute("users");
        return new BaseModel("退出登录成功", true);
    }

    @PatchMapping("/register")
    @ResponseBody
    public BaseModel register(@RequestBody Users users, HttpSession session) {
        Asserts.notBlank(users.getPassword(), "密码");
        if (isBlank(users.getName()) && isBlank(users.getEmail()) && isBlank(users.getPhone())) {
            return new BaseModel("用户名 is blank");
        }
        Users newUsers = usersService.register(users);
        session.setAttribute("users", newUsers);
        return new BaseModel("登陆成功", true, newUsers);
    }


}
