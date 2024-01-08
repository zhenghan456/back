package com.zhenghan.scenery.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="scenery")
public class SceneryPojo {
    @TableId(value = "sceneryid",type = IdType.ASSIGN_ID)
    private String sceneryid;
    @TableField("sceneryname")
    private String sceneryname;
    @TableField("support")
    private  Integer support;
    @TableField("description")
    private  String description;
    @TableField("time")
    private  String time;
    @TableField("longitude")
    private  String longitude;
    @TableField("latitude")
    private  String latitude;
    @TableField("userid")
    private  String userid;

    public SceneryPojo(String sceneryid, String sceneryname, String description, String time, String longitude, String latitude, String userid) {
        this.sceneryid=sceneryid;this.sceneryname=sceneryname;this.description=description;this.time=time;this.longitude=longitude;this.latitude=latitude;this.userid=userid;
    }

    public String getUserid(){return this.userid;}
}
