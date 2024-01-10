package com.zhenghan.scenery.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhenghan.scenery.Pojo.UploadPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UploadDao extends BaseMapper<UploadPojo> {

}
