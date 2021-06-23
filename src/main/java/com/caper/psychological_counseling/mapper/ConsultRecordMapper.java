package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRecordMapper extends BaseMapper<ConsultRecord> {


    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ConsultRecord consultRecord);
}
