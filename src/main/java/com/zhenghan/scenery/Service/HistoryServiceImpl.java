package com.zhenghan.scenery.Service;

import com.zhenghan.scenery.Dao.HistoryDao;
import com.zhenghan.scenery.Pojo.HistoryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl {
    @Autowired
    HistoryDao historyDao;
    public void add(String userid,String sceneryid,String time){
        historyDao.insert(new HistoryPojo(userid,sceneryid,time));
    }
}
