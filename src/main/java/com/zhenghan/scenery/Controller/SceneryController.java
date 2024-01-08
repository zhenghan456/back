package com.zhenghan.scenery.Controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhenghan.scenery.Dao.ScenerySupportDao;
import com.zhenghan.scenery.Pojo.PictruesPojo;
import com.zhenghan.scenery.Pojo.SceneryLabelPojo;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import com.zhenghan.scenery.Pojo.UserPojo;
import com.zhenghan.scenery.Service.*;
import com.zhenghan.scenery.issupport;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class SceneryController {
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

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public void upload(@RequestParam(value = "file") MultipartFile[] files, @RequestBody Map map, HttpServletRequest req) throws IOException {
        JSONObject json=new JSONObject(map);
        String sceneryname=json.getString("sceneryname");
        String description=json.getString("discription");
        String label=json.getString("label");
        String longitude=json.getString("longitude");
        String latitude=json.getString("latitude");
        String time=json.getString("time");
        String isrecall=json.getString("isrecall");
        String userid=json.getString("userid");
        Long id=sceneryService.maxid()+1;
        String sceneryid=id.toString();
        sceneryService.add(sceneryid,sceneryname,description,time,longitude,latitude,userid);
        if(isrecall.equals("yes")){
           recallService.add(sceneryid,userid);
        }
        //String uploadPathImg="C:\\Users\\admin\\IdeaProjects\\src\\main\\java\\com\\zhenghan\\scenery\\images\\";
        String uploadPathImg = "src/main/java/com/zhenghan/scenery/images/";
        for(MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            String upload_file_dir = uploadPathImg;//注意这里需要添加目录信息
            String url = uploadPathImg + fileName;
            File upload_file_dir_file = new File(upload_file_dir);
            if (!upload_file_dir_file.exists()) {
                upload_file_dir_file.mkdirs();
            }
            File targetFile = new File(upload_file_dir_file, fileName);
            file.transferTo(targetFile);
            pictruesService.add(sceneryid,fileName);
        }

    }

    @RequestMapping(value = "/sceneryinformation", method = RequestMethod.POST)
    @ResponseBody
    public String sceneryinformation(HttpServletRequest request, @RequestBody Map map,
                                     @RequestParam Map<String, String> parameter) {
        JSONObject json = new JSONObject(map);
        String sceneryid = json.getString("sceneryid");
        String userid = json.getString("userid");
        SceneryPojo list1 = sceneryService.findSceneryById(sceneryid);
        String uploader = list1.getUserid();
        UserPojo list2 = userService.findUserById(uploader);
        List<PictruesPojo> list3 = pictruesService.findPictruesById(sceneryid);
        List<SceneryLabelPojo> list4 = sceneryLabelService.findlabel(sceneryid);
        issupport is = scenerySupportService.issupport(sceneryid, userid);
        System.out.println(is.toString());
        List<Object> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.addAll(list3);
        list.addAll(list4);
        list.add(is);
        String result = JSON.toJSONString(list);
        return JSON.toJSONString(result);
    }
}
