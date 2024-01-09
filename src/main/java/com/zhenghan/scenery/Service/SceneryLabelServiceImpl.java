package com.zhenghan.scenery.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Dao.SceneryLabelDao;
import com.zhenghan.scenery.Dao.ScenerySupportDao;
import com.zhenghan.scenery.Pojo.PictruesPojo;
import com.zhenghan.scenery.Pojo.SceneryLabelPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneryLabelServiceImpl {
    @Autowired
    SceneryLabelDao sceneryLabelDao;
    public List<SceneryLabelPojo> findlabel(String id){
        QueryWrapper<SceneryLabelPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("sceneryid",id);
        return sceneryLabelDao.selectList(wrapper);
    }
    public List<SceneryLabelPojo> searchscenery(String search){
        QueryWrapper<SceneryLabelPojo> wrapper=new QueryWrapper<>();
        wrapper.like("label",search);
        return sceneryLabelDao.selectList(wrapper);
    }
}
