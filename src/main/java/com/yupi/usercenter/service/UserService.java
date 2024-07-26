package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 20474
* @description 针对表【user】的数据库操作Service
* @createDate 2024-07-18 16:52:56
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 账户
     * @param userPassword 密码
     * @param checkPassword 密码
     * @param planetCode 星球编号
     * @return id
     */
    long userRegister(String userAccount, String userPassword,String checkPassword,String planetCode);


    /**
     * 用户登录
     * @param userAccount 账户
     * @param userPassword 密码
     * @return 脱敏后端用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    User getSafetyUser(User user);

    /**
     * 用户注销
     * @param request
     */
    int userLogout(HttpServletRequest request);
}
