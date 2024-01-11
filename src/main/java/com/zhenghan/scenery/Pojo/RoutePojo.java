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
@TableName(value="route")
public class RoutePojo {
    @TableId(value = "routeid",type = IdType.ASSIGN_ID)
    private String routeid;
    @TableField("userid")
    private String userid;
    @TableField("routename")
    private String routename;
    public String getRouteid(){return this.routeid;}
    public String getRoutename(){return this.routename;}
}
