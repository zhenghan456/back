package com.zhenghan.scenery;

import com.zhenghan.scenery.Pojo.PictruesPojo;
import com.zhenghan.scenery.Pojo.SceneryLabelPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Labels {
    private List<SceneryLabelPojo> labels;
}
