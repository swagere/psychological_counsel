package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationMapper extends BaseMapper<Organization> {

    @Select("select id from organization where level = #{level}")
    List<Long> selectOrgIdsByLevel(Integer level);
}
