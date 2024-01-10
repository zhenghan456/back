package com.zhenghan.scenery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Main {
    private List<Object> main;
//    public Main(){}
//    public Main(List<Object> main){this.main=main;}
    public void setMain(List<Object> main){this.main=main;}
}
