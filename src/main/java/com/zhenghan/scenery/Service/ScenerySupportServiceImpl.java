package com.zhenghan.scenery.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Dao.ScenerySupportDao;
import com.zhenghan.scenery.Pojo.ScenerySupportPojo;
import com.zhenghan.scenery.issupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScenerySupportServiceImpl {
    @Autowired
    ScenerySupportDao scenerySupportDao;
    public issupport issupport(String sceneryid, String userid){
        QueryWrapper<ScenerySupportPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("sceneryid",sceneryid);
        wrapper.eq("userid",userid);
        issupport is=new issupport();
        if(scenerySupportDao.exists(wrapper)) is.setIssupport("yes");
        else is.setIssupport("no");
        return is;
    }
}
