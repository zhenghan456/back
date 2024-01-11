package com.zhenghan.scenery.Controller;

import com.alibaba.fastjson2.JSON;
import com.zhenghan.scenery.Pictrues;
import com.zhenghan.scenery.Pojo.*;
import com.zhenghan.scenery.Route;
import com.zhenghan.scenery.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    SceneryServiceImpl sceneryService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PictruesServiceImpl pictruesService;
    @Autowired
    ScenerySupportServiceImpl scenerySupportService;
    @Autowired
    SceneryLabelServiceImpl sceneryLabelService;
    @Autowired
    RecallServiceImpl recallService;
    @Autowired
    RouteServiceImpl routeService;
    @Autowired
    RouteSceneryServiceImpl routeSceneryService;
    @RequestMapping(value = "/user", produces = "application/json; charset=UTF-8",method = RequestMethod.POST)
    public String userinformation(@RequestParam("userid") String userid,
                                  HttpServletRequest req){
        UserPojo list1=userService.findUserById(userid);
        List<String> ids=sceneryService.upload(userid);
        List<Object> list=new ArrayList<>();
        list.add(list1);
        for(String id :ids){
            List<PictruesPojo> pictrues=pictruesService.findPictruesById(id);
            Pictrues pictrue=new Pictrues(pictrues);
            list.add(pictrue);
        }
                return JSON.toJSONString(list);
    }
    @RequestMapping(value = "/supportinformation", produces = "application/json; charset=UTF-8",method = RequestMethod.POST)
    public String support(@RequestParam("userid") String userid,
                          HttpServletRequest req){
        List<String> ids=scenerySupportService.findById(userid);
        List<Pictrues> list=new ArrayList<>();
        for(String id :ids){
            List<PictruesPojo> pictrues=pictruesService.findPictruesById(id);
            Pictrues pictrue=new Pictrues(pictrues);
            list.add(pictrue);
        }
        return JSON.toJSONString(list);
    }
    @RequestMapping(value = "/favorites", produces = "application/json; charset=UTF-8",method = RequestMethod.POST)
    public String favorites(@RequestParam("userid")String userid,
                            HttpServletRequest req){
       List<String> routeids=routeService.findById(userid);
       List<RoutePojo> routes=routeService.findroute(userid);
       List<Route> result=new ArrayList<>();
       for(int i=0;i<routeids.size();i++){
           String routeid=routeids.get(i);
           RoutePojo route=routes.get(i);
           List<Object> firstlist=new ArrayList<>();
           firstlist.add(route);
          List<String> sceneryids=routeSceneryService.findByid(routeid);
          List<Pictrues> list=new ArrayList<>();
           for(String sceneryid :sceneryids){
               List<PictruesPojo> pictrues=pictruesService.findPictruesById(sceneryid);
               Pictrues pictrue=new Pictrues(pictrues);
               list.add(pictrue);
           }
           firstlist.addAll(list);
           Route route1=new Route(firstlist);
           result.add(route1);
       }
       return JSON.toJSONString(result);
    }
}
