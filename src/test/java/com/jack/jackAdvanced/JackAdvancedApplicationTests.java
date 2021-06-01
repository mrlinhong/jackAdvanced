package com.jack.jackAdvanced;

import com.jack.jackAdvanced.domain.entity.User;
import com.jack.jackAdvanced.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JackAdvancedApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListUser() {
        List<User> userList = userMapper.listUser();
        userList.forEach(System.out::println);
    }

}
