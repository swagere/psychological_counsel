package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.vo.AreaVO;
import lombok.Builder;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper extends BaseMapper<Area> {

    @Select("select id from area where org_id = #{org_id}")
    List<Long> selectAreaIdsByOrgId(@Param("org_id") Long org_id);

    List<AreaVO> selectAreaVOByOrgId(@Param("org_id") Long org_id);

    @Override
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Area entity);

    boolean updateArea(@Param("area") Area area);
}
