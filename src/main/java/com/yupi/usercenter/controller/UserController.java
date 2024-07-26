package com.yupi.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.domain.request.UserLoginRequest;
import com.yupi.usercenter.model.domain.request.UserRegisterRequest;
import com.yupi.usercenter.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.yupi.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.yupi.usercenter.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求体
     * @return id
     */
    @PostMapping("/register")
    public Long UserRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        if(userRegisterRequest == null){
            return null;
        }

        String userAccount =userRegisterRequest.getUserAccount();
        String userPassword =userRegisterRequest.getUserPassword();
        String checkPassword =userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }

        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求体
     * @param request
     * @return User
     */
    @PostMapping("/login")
    public User UserLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {

        if(userLoginRequest == null){
            return null;
        }

        String userAccount =userLoginRequest.getUserAccount();
        String userPassword =userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }

        return userService.userLogin(userAccount, userPassword,request);
    }

    /**
     * 查询用户By用户名
     * @param userName 用户名
     * @return List<User>
     */
    @GetMapping("/search")
    public List<User> selectUsers(String userName,HttpServletRequest request) {
        if(!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            userQueryWrapper.like("username", userName);
        }
        List<User> userList = userService.list(userQueryWrapper);
        return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }


    /**
     * 删除用户
     * @param id
     * @param request
     * @return bool
     */
    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id,HttpServletRequest request) {
        if(!isAdmin(request)){
            return false;
        }
        if(id <= 0){
            return false;
        }
        return userService.removeById(id);
    }


    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user == null||user.getUserRole() != ADMIN_ROLE){
            return false;
        }
        return true;
    }

}
