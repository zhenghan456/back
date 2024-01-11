package com.zhenghan.scenery.Controller;

import com.alibaba.fastjson2.JSON;
import com.zhenghan.scenery.Main;
import com.zhenghan.scenery.Pictrues;
import com.zhenghan.scenery.Pojo.PictruesPojo;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import com.zhenghan.scenery.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class RecallController {
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

    @RequestMapping(value = "/recall", produces = "application/json; charset=UTF-8",method = RequestMethod.POST)
    public String  recall(@RequestParam("userid")String userid,
                          HttpServletRequest req){
        List<String> ids=recallService.find(userid);
        List<Object> result=new ArrayList<>();
        for(String id :ids){
            List<Object> list=new ArrayList<>();
            SceneryPojo scenery=sceneryService.findSceneryById(id);
            List<PictruesPojo> pictrues=pictruesService.findPictruesById(id);
            Pictrues pictrues1=new Pictrues(pictrues);
            list.add(scenery);
            list.add(pictrues1);
            Main main=new Main(list);
            result.add(main);
        }
        return JSON.toJSONString(result);
    }
}
