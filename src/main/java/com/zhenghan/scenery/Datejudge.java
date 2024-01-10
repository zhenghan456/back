package com.zhenghan.scenery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datejudge {
    private String datejudge;
    public void setDate(String datejudge){this.datejudge=datejudge;}
}
