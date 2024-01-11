package com.zhenghan.scenery.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Dao.RouteDao;
import com.zhenghan.scenery.Pojo.RoutePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl {
    @Autowired
    RouteDao routeDao;
    public List<String> findById(String userid){
        QueryWrapper<RoutePojo> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        List<RoutePojo> routes=routeDao.selectList(wrapper);
        List<String> routeids=new ArrayList<>();
        for(RoutePojo route :routes){
            String routeid=route.getRouteid();
            routeids.add(routeid);
        }
        return routeids;
    }
    public List<RoutePojo> findroute(String userid){
        QueryWrapper<RoutePojo> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        List<RoutePojo> routes=routeDao.selectList(wrapper);
        return routes;
    }
}
