package com.zhenghan.scenery.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Dao.PictruesDao;
import com.zhenghan.scenery.Dao.RecallDao;
import com.zhenghan.scenery.Pojo.PictruesPojo;
import com.zhenghan.scenery.Pojo.RecallPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecallServiceImpl {
    @Autowired
    RecallDao recallDao;
    public void add(String sceneryid,String userid){
        recallDao.insert(new RecallPojo(sceneryid,userid));
    }
}
