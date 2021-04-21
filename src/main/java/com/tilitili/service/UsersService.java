package com.tilitili.service;

import com.tilitili.common.entity.Users;
import com.tilitili.common.manager.UsersManager;
import com.tilitili.common.mapper.UsersMapper;
import com.tilitili.common.utils.Asserts;
import com.tilitili.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.tilitili.util.MD5.md5;

@Service
public class UsersService {
    private final UsersMapper usersMapper;
    private final UsersManager usersManager;

    @Autowired
    public UsersService(UsersMapper usersMapper, UsersManager usersManager) {
        this.usersMapper = usersMapper;
        this.usersManager = usersManager;
    }

    public Users login(Users users) {
        Users oldUsers = usersManager.getUsers(users);
        Asserts.notNull(oldUsers, "用户");
        String password = oldUsers.getPassword();
        if (Objects.equals(password, md5(users.getPassword()))) {
            usersMapper.update(new Users().setId(oldUsers.getId()).setLastLoginTime(DateUtil.now()));
            return usersMapper.getById(oldUsers.getId());
        }
        return null;
    }

    public Users register(Users users) {
        Users oldUsers = usersManager.getUsers(users);
        Asserts.checkNull(oldUsers, "该用户名已存在");
        users.setPassword(md5(users.getPassword()));
        if (users.getName() == null && users.getPhone() != null) {
            users.setName(users.getPhone());
        }
        if (users.getName() == null && users.getEmail() != null) {
            users.setName(users.getEmail());
        }
        usersMapper.insert(users);
        Asserts.notNull(users.getId(), "用户id");
        return users;
    }

}
