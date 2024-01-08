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
@TableName(value="pictrues")
public class PictruesPojo {
    @TableField("sceneryid")
    private String sceneryid;
    @TableField("image")
    private String image;

//    public PictruesPojo(String sceneryid, String filename) {
//        this.sceneryid=sceneryid;this.image=filename;
//    }
}
