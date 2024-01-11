package com.zhenghan.scenery.Controller;

import com.alibaba.fastjson2.JSON;
import com.zhenghan.scenery.*;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import com.zhenghan.scenery.Pojo.UserPojo;
import com.zhenghan.scenery.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class MainController {
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
    @RequestMapping(value = "/main", produces = "application/json; charset=UTF-8",method = RequestMethod.POST)
    public String maininformation(HttpServletRequest req,
                                  @RequestParam("userid") String userid,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd’T’HH:mm:ss.SSS’Z")String date) throws ParseException {
        Long max=sceneryService.maxid();
        List<Object> resultList=new ArrayList<>();
        for(int i=1;i<=5;i++) {
            List<Object> list = new ArrayList<>();
            Random rand = new Random();
            int id = rand.nextInt(Math.toIntExact(max)) + 1;
            String sceneryid=Integer.toString(id);
            SceneryPojo list1 = sceneryService.findSceneryById(sceneryid);
            String uploader = list1.getUserid();
            UserPojo list2 = userService.findUserById(uploader);
            Pictrues list3 = new Pictrues(pictruesService.findPictruesById(sceneryid));
            Labels list4 = new Labels(sceneryLabelService.findlabel(sceneryid));
            issupport is = scenerySupportService.issupport(sceneryid, userid);
            String then=list1.getTime();
            Datejudge datejudge =sceneryService.datejudge(date,then);
            list.clear();
            list.add(list1);
            list.add(list2);
            list.add(list3);
            list.add(list4);
            list.add(is);
            list.add(datejudge);
            Main result=new Main(list);
            resultList.add(result);
        }
        return JSON.toJSONString(resultList);
    }
}
