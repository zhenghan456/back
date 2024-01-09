package com.zhenghan.scenery.Controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.*;
import com.zhenghan.scenery.Pojo.SceneryLabelPojo;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import com.zhenghan.scenery.Pojo.UserPojo;
import com.zhenghan.scenery.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class SearchController {
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
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String searchinformation(@DateTimeFormat(pattern = "yyyy-MM-dd’T’HH:mm:ss.SSS’Z’") String time,
                                    @RequestParam("longitude") String longitude,
                                    @RequestParam("latitude") String latitude,
                                    @RequestParam("timerequire") String timerequire,
                                    @RequestParam("distancerequire") String distancerequire,
                                    @RequestParam("search") String search,
                                    @RequestParam("userid") String userid,
                                    HttpServletRequest req ) throws ParseException {
        List<String> list=new ArrayList<>();
        List<SceneryPojo> list1=sceneryService.searchscenery(search);
        for(SceneryPojo scenery : list1){
            list.add(scenery.getSceneryid());
        }
        List<SceneryLabelPojo> list2=sceneryLabelService.searchscenery(search);
        for(SceneryLabelPojo scenery : list2){
            String sceneryid=scenery.getSceneryid();
            boolean isrepeat=false;
            for(String id : list) if(id.equals(sceneryid)) isrepeat=true;
            if(!isrepeat) list.add(sceneryid);
        }
        int timer=Integer.parseInt(timerequire);
       // List<String> deletelist=new ArrayList<>();
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);//输入的被转化的时间格式
        Date date1 = dff.parse(time);
        Iterator<String> iterator=list.iterator();
        while(iterator.hasNext()){
            String id=iterator.next();
            String date=sceneryService.getTime(id);
            Date date2 = dff.parse(date);
            int day=(int)DateUtil.between(date1, date2, DateUnit.DAY);
//            Long hour=DateUtil.between(date1, date2, DateUnit.HOUR);
//            Long minute=DateUtil.between(date1,date2,DateUnit.MINUTE);
//            Long second=DateUtil.between(date1,date2,DateUnit.SECOND);
            if(day>timer) iterator.remove();
        }
        double jingdu=Double.parseDouble(longitude);
        double weidu=Double.parseDouble(latitude);
        double distance=Double.parseDouble(distancerequire)*1000;
        Iterator<String> iterator1=list.iterator();
        while(iterator1.hasNext()){
            String id=iterator1.next();
            String longitude2=sceneryService.getLongitude(id);
            String latitude2=sceneryService.getLatitude(id);
            double jingdu2=Double.parseDouble(longitude2);
            double weidu2=Double.parseDouble(latitude2);
            double dist= GeoUtil.GetDistance(jingdu,weidu, jingdu2, weidu2);
            if(dist>distance) iterator1.remove();
        }
        List<Object> scenerylist = new ArrayList<>();
        List<Object> resultList=new ArrayList<>();
        for(String sceneryid : list){
            SceneryPojo scenerylist1 = sceneryService.findSceneryById(sceneryid);
            String uploader = scenerylist1.getUserid();
            UserPojo scenerylist2 = userService.findUserById(uploader);
            Pictrues scenerylist3 = new Pictrues(pictruesService.findPictruesById(sceneryid));
            Labels scenerylist4 = new Labels(sceneryLabelService.findlabel(sceneryid));
            issupport is = scenerySupportService.issupport(sceneryid, userid);
            scenerylist.clear();
            scenerylist.add(scenerylist1);
            scenerylist.add(scenerylist2);
            scenerylist.add(scenerylist3);
            scenerylist.add(scenerylist4);
            scenerylist.add(is);
            Main result=new Main(scenerylist);
            resultList.add(result);
        }
        return JSON.toJSONString(resultList);




    }
}
