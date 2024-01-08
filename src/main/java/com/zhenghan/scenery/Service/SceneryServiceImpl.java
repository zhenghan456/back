package com.zhenghan.scenery.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.zhenghan.scenery.Dao.SceneryDao;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SceneryServiceImpl {
    @Autowired
    SceneryDao sceneryDao;
    public SceneryPojo findSceneryById(String id){return sceneryDao.selectById(id);}
    public Long maxid(){return sceneryDao.selectCount(new QueryWrapper<>() );}
    public void add(String sceneryid,String sceneryname,String description,String time,String longitude,String latitude,String userid){
         sceneryDao.insert(new SceneryPojo(sceneryid,sceneryname,description,time,longitude,latitude,userid));
    }
}
