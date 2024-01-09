package com.zhenghan.scenery;

import com.zhenghan.scenery.Pojo.PictruesPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pictrues {
    private List<PictruesPojo> pictrues;
    //public pictrues(List<PictruesPojo> list){this.pictrues=list;}
}
