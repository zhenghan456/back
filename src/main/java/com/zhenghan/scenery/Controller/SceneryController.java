package com.zhenghan.scenery.Controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhenghan.scenery.Dao.ScenerySupportDao;
import com.zhenghan.scenery.Labels;
import com.zhenghan.scenery.Pictrues;
import com.zhenghan.scenery.Pojo.PictruesPojo;
import com.zhenghan.scenery.Pojo.SceneryLabelPojo;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import com.zhenghan.scenery.Pojo.UserPojo;
import com.zhenghan.scenery.Service.*;
import com.zhenghan.scenery.issupport;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
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
    public void upload(@RequestParam(value = "file") MultipartFile[] files,
                       @RequestParam("sceneryname") String sceneryname,
                       @RequestParam("description") String description,
                       @RequestParam("label") List<String> labels,
                       @RequestParam("longitude") String longitude,
                       @RequestParam("latitude") String latitude,
                       @DateTimeFormat(pattern = "yyyy-MM-dd’T’HH:mm:ss.SSS’Z") String time,
                       @RequestParam("isrecall") String isrecall,
                       @RequestParam("userid") String userid,
                       HttpServletRequest req) throws IOException {
        Long id=sceneryService.maxid()+1;
        String sceneryid=id.toString();
        sceneryService.add(sceneryid,sceneryname,description,time,longitude,latitude,userid);
        if(isrecall.equals("yes")){
           recallService.add(sceneryid,userid);
        }
        for(String label :labels){
            sceneryLabelService.add(label,sceneryid);
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
    public String sceneryinformation(HttpServletRequest request,
                                     @RequestParam("sceneryid") String sceneryid,
                                     @RequestParam("userid") String userid) {
        SceneryPojo list1 = sceneryService.findSceneryById(sceneryid);
        String uploader = list1.getUserid();
        UserPojo list2 = userService.findUserById(uploader);
        Pictrues list3=new Pictrues(pictruesService.findPictruesById(sceneryid));
        Labels list4=new Labels(sceneryLabelService.findlabel(sceneryid));
        issupport is = scenerySupportService.issupport(sceneryid, userid);
        List<Object> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        list.add(is);
        String result = JSON.toJSONString(list);
        return result;
    }
}
