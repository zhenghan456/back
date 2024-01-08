package com.zhenghan.scenery;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenghan.scenery.Pojo.PictruesPojo;
import com.zhenghan.scenery.Pojo.SceneryLabelPojo;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import com.zhenghan.scenery.Pojo.UserPojo;
import com.zhenghan.scenery.Service.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

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
    @Test
    void contextLoads() {
        String sceneryid = "1";
        String userid = "1";
        SceneryPojo list1 = sceneryService.findSceneryById(sceneryid);
        String uploader = list1.getUserid();
        UserPojo list2 = userService.findUserById(uploader);
        List<PictruesPojo> list3 = pictruesService.findPictruesById(sceneryid);
        List<SceneryLabelPojo> list4 = sceneryLabelService.findlabel(sceneryid);
        issupport is = scenerySupportService.issupport(sceneryid, userid);
        List<Object> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.addAll(list3);
        list.addAll(list4);
        list.add(is);
        String result = JSON.toJSONString(list);
        System.out.println(result);
    }


}
