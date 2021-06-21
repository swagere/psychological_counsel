package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationMapper extends BaseMapper<Application> {

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Application entity);
}
