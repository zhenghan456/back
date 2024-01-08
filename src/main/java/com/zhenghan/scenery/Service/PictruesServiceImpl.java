package com.zhenghan.scenery.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Dao.PictruesDao;
import com.zhenghan.scenery.Pojo.PictruesPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictruesServiceImpl {
    @Autowired
    PictruesDao pictruesDao;
    public List<PictruesPojo> findPictruesById(String id){
        QueryWrapper<PictruesPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("sceneryid",id);
        return pictruesDao.selectList(wrapper);
    }
    public void add(String sceneryid,String filename){
        pictruesDao.insert(new PictruesPojo(sceneryid,filename));
    }
}

