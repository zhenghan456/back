package com.zhenghan.scenery;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhenghan.scenery.Dao.SceneryDao;
import com.zhenghan.scenery.Pojo.*;
import com.zhenghan.scenery.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class SceneryApplicationTests {
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
    RouteServiceImpl routeService;
    @Autowired
    RouteSceneryServiceImpl routeSceneryService;
    @Autowired
    RecallServiceImpl recallService;
    @Test
    void contextLoads() throws JsonProcessingException, ParseException {
        String search="丁真";
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
        System.out.println(list);
        String time ="2024-01-09T15:32:03.006481";
        String timerequire="7";
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
        System.out.println(list);
        String longitude ="116.43423200000001";
        String latitude ="39.908182";
        String distancerequire ="15";
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
        System.out.println(list);
    }
    @Test
    void contextLoads2() throws ParseException {
        String userid="1";
        String date="2024-01-09T15:32:03.006481";
        Long max=sceneryService.maxid();
        List<Main> resultList=new ArrayList<>();
        for(int i=1;i<=5;i++) {
            List<Object> list = new ArrayList<>();
            Random rand = new Random();
            int id = rand.nextInt(Math.toIntExact(max)) + 1;
            System.out.println(id);
            String sceneryid=Integer.toString(id);
            SceneryPojo list1 = sceneryService.findSceneryById(sceneryid);
            String uploader = list1.getUserid();
            UserPojo list2 = userService.findUserById(uploader);
            Pictrues list3 = new Pictrues(pictruesService.findPictruesById(sceneryid));
            Labels list4 = new Labels(sceneryLabelService.findlabel(sceneryid));
            issupport is = scenerySupportService.issupport(sceneryid, userid);
            String then=list1.getTime();
            System.out.println(then);
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
        System.out.println(JSON.toJSONString(resultList));
    }
    @Test
    void contextLoads3() throws ParseException {
        String dateStr1 = "2024-01-09T15:32:00.006481";
        String dateStr2 = "2024-01-09T15:32:03.006481";
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS",Locale.ENGLISH);//输入的被转化的时间格式
//        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//需要转化成的时间格式
        Date date1 = dff.parse(dateStr1);
        Date date2 = dff.parse(dateStr2);
//        String str1 = df1.format(date1);
//        String str2 = df1.format(date2);
//        System.out.println(str1);
//        System.out.println(str2);
        System.out.println("两个时间相差 "+DateUtil.between(date1, date2, DateUnit.DAY)+" 天");
        System.out.println("两个时间相差 "+DateUtil.between(date1, date2, DateUnit.HOUR)+" 小时");
        System.out.println("两个时间相差 "+DateUtil.between(date1, date2, DateUnit.MINUTE)+" 分钟");
        System.out.println("两个时间相差 "+DateUtil.between(date1, date2, DateUnit.SECOND)+" 秒");
    }
    @Test
    void contextLoads4() throws ParseException {
        String userid="1";
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
        System.out.println(JSON.toJSONString(result));
    }
}
