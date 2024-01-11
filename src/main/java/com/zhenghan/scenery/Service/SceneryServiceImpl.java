package com.zhenghan.scenery.Service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.zhenghan.scenery.Dao.SceneryDao;
import com.zhenghan.scenery.Datejudge;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class SceneryServiceImpl {
    @Autowired
    SceneryDao sceneryDao;
    public SceneryPojo findSceneryById(String id){return sceneryDao.selectById(id);}
    public List<SceneryPojo> searchscenery(String search){
        QueryWrapper<SceneryPojo> wrapper=new QueryWrapper<>();
        wrapper.like("sceneryname", search);
        return sceneryDao.selectList(wrapper);
    }
    public Long maxid(){return sceneryDao.selectCount(new QueryWrapper<>() );}
    public void add(String sceneryid,String sceneryname,String description,String time,String longitude,String latitude,String userid){
         sceneryDao.insert(new SceneryPojo(sceneryid,sceneryname,description,time,longitude,latitude,userid));
    }
    public Datejudge datejudge(String date1,String date2) throws ParseException {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);//输入的被转化的时间格式
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//需要转化成的时间格式
        Date date3 = dff.parse(date1);
        Date date4 = dff.parse(date2);
        String date5 = df1.format(date4);
            Long day=DateUtil.between(date3, date4, DateUnit.DAY);
            Long hour=DateUtil.between(date3, date4, DateUnit.HOUR);
            Long minute=DateUtil.between(date3,date4,DateUnit.MINUTE);
            Datejudge result=new Datejudge();
            if(minute==0) result.setDate("刚刚");
            else if(hour==0) result.setDate(minute.toString()+"分钟前");
            else if(day==0) result.setDate(hour.toString()+"小时前");
            else if(day<=7) result.setDate(day.toString()+"天前");
            else result.setDate(date5);
            return result;
    }
    public void support(String sceneryid){
        Integer support=sceneryDao.selectById(sceneryid).getSupport();
        support=support+1;
        SceneryPojo sceneryPojo=new SceneryPojo();
        sceneryPojo.setSupport(support);
        QueryWrapper<SceneryPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("sceneryid",sceneryid);
        sceneryDao.update(sceneryPojo,wrapper);
    }
    public void unsupport(String sceneryid){
        Integer support=sceneryDao.selectById(sceneryid).getSupport();
        support=support-1;
        SceneryPojo sceneryPojo=new SceneryPojo();
        sceneryPojo.setSupport(support);
        QueryWrapper<SceneryPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("sceneryid",sceneryid);
        sceneryDao.update(sceneryPojo,wrapper);
    }
    public String getTime(String id){return sceneryDao.selectById(id).getTime();}
    public String getLongitude(String id){return sceneryDao.selectById(id).getLongitude();}
    public String getLatitude(String id){return sceneryDao.selectById(id).getLatitude();}

    public List<String> upload(String userid) {
        QueryWrapper<SceneryPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        List<SceneryPojo> scenerys=sceneryDao.selectList(wrapper);
        List<String> list=new ArrayList<>();
        for(SceneryPojo scenery : scenerys){
            String sceneryid=scenery.getSceneryid();
            list.add(sceneryid);
        }
        return list;
    }
    public String getSupport(String sceneryid){return sceneryDao.selectById(sceneryid).getSupport().toString();}
}
