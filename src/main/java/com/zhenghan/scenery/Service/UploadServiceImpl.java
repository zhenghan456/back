package com.zhenghan.scenery.Service;

import com.zhenghan.scenery.Dao.UploadDao;
import com.zhenghan.scenery.Pojo.UploadPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl {
    @Autowired
    UploadDao uploadDao;
    public void add(String sceneryid,String userid){
        uploadDao.insert(new UploadPojo(sceneryid，userid));
    }
}
