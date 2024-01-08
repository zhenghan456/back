package com.zhenghan.scenery.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="favorites")
public class FavoritesPojo {
    @TableField("userid")
    private String userid;
    @TableField("sceneryid")
    private String sceneryid;
    @TableField("favorite")
    private String favorite;
//    public FavoritesPojo(String userid, String sceneryid,String favorite) {
//        this.sceneryid=sceneryid;this.userid=userid;this.favorite=favorite;
//    }
}
