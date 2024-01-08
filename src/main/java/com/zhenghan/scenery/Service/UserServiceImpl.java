package com.zhenghan.scenery.Service;


import com.zhenghan.scenery.Dao.UserDao;
import com.zhenghan.scenery.Pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    UserDao userDao;
    public UserPojo findUserById(String id){return userDao.selectById(id);}
}

