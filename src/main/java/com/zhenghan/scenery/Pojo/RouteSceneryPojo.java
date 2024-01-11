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
@TableName(value="routescenery")
public class RouteSceneryPojo {
    @TableField("routeid")
    private String routeid;
    @TableField("sceneryid")
    private String sceneryid;
    public String getSceneryid(){return this.sceneryid;}
}
