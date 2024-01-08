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
@TableName(value="history")
public class HistoryPojo {
    @TableField("userid")
    private String userid;
    @TableField("sceneryid")
    private String sceneryid;
    @TableField("time")
    private String time;
//    public HistoryPojo(String userid, String sceneryid,String time) {
//        this.sceneryid=sceneryid;this.userid=userid;this.time=time;
//    }
}

