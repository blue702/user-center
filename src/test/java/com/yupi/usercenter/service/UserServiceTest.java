package com.yupi.usercenter.service;
import java.util.Date;

import com.yupi.usercenter.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {


    @Resource
    private UserService userService;
    @Test
    void addUser() {
        User user = new User();
        user.setUserAccount("2047469393");
        user.setUsername("john");
        user.setGender(0);
        user.setAvatarUrl("https://images.zsxq.com/FtyoJkpZiZggIg_h79bUGuTkLExp?e=1725119999&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:ZgLY7ZQ9IPoXogMcgXZ1Uvq9uKM=");
        user.setUserPassword("123");
        user.setPhone("13672224456");
        user.setEmail("2047469393");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    /**
     * 测试用户注册
     */
    @Test
    void userRegister() {
//        String userAccount = "yupi";
//        String userPassword = "";
//        String checkPassword = "123456";
//        String planetCode = "1";
//        long result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yu";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yupi";
//        userPassword = "123456";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yu pi";
//        userPassword = "12345678";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        checkPassword = "123456789";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "dogYupi";
//        checkPassword = "12345678";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yupi";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//
//        userAccount = "yupiaaaa";
//        userPassword = "123456789";
//        checkPassword = "123456789";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertTrue(result > 0);

        String userAccount = "john111";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        String planetCode = "5";
        long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        System.out.println(result);

    }



}