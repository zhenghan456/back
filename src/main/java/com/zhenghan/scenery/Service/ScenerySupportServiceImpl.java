package com.zhenghan.scenery.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Dao.ScenerySupportDao;
import com.zhenghan.scenery.Pojo.ScenerySupportPojo;
import com.zhenghan.scenery.issupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenerySupportServiceImpl {
    @Autowired
    ScenerySupportDao scenerySupportDao;
    public issupport issupport(String sceneryid, String userid){
        QueryWrapper<ScenerySupportPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("sceneryid",sceneryid);
        wrapper.eq("userid",userid);
        issupport is=new issupport();
        if(scenerySupportDao.exists(wrapper)) is.setIssupport("yes");
        else is.setIssupport("no");
        return is;
    }
    public void support(String sceneryid,String userid){
        scenerySupportDao.insert(new ScenerySupportPojo(sceneryid,userid));
    }
    public void unsupport(String sceneryid,String userid){
        QueryWrapper<ScenerySupportPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("sceneryid",sceneryid);
        wrapper.eq("userid",userid);
        scenerySupportDao.delete(wrapper);
    }
    public List<String> findById(String userid){
        QueryWrapper<ScenerySupportPojo> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        List<ScenerySupportPojo> scenerys=scenerySupportDao.selectList(wrapper);
        List<String> ids=new ArrayList<>();
        for(ScenerySupportPojo scenery : scenerys){
            String id=scenery.getSceneryid();
            ids.add(id);
        }
        return ids;
    }
}
