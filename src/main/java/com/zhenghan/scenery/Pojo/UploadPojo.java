package com.zhenghan.scenery.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="upload")
public class UploadPojo {
    @TableField("sceneryid")
    private String sceneryid;
    @TableField("userid")
    private String userid;
}
