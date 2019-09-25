package com.zy.service.Impl;

import com.zy.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public void testLogger() {
        System.out.println("testLogger body");
    }
}
