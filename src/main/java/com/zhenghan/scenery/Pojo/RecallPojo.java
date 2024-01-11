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
@TableName(value="recall")
public class RecallPojo {
    @TableField("sceneryid")
    private String sceneryid;
    @TableField("userid")
    private String userid;
    public String getSceneryid(){return this.sceneryid;}
//    public RecallPojo(String sceneryid, String userid) {
//        this.sceneryid=sceneryid;this.userid=userid;
//    }
}
