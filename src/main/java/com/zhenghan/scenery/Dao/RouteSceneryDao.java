package com.zhenghan.scenery.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhenghan.scenery.Pojo.RouteSceneryPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RouteSceneryDao extends BaseMapper<RouteSceneryPojo> {
}
