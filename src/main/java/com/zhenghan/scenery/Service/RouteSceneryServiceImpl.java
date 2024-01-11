package com.zhenghan.scenery.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Dao.RouteSceneryDao;
import com.zhenghan.scenery.Pojo.RouteSceneryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteSceneryServiceImpl {
    @Autowired
    RouteSceneryDao routeSceneryDao;
    public void add(String routeid,String sceneryid){routeSceneryDao.insert(new RouteSceneryPojo(routeid,sceneryid));}
    public List<String> findByid(String routeid){
        QueryWrapper<RouteSceneryPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("routeid",routeid);
        List<RouteSceneryPojo> scenerys=routeSceneryDao.selectList(wrapper);
        List<String> sceneryids=new ArrayList<>();
        for(RouteSceneryPojo scenery :scenerys){
            String sceneryid=scenery.getSceneryid();
            sceneryids.add(sceneryid);
        }
        return sceneryids;
    }
}
