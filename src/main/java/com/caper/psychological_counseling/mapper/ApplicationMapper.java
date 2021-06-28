package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.vo.ApplicationVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ApplicationMapper extends BaseMapper<Application> {

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Application entity);


    //查看自己的初访申请表
    @Select("SELECT a.rank,a.type,a.answer,a.grade,a.urgency,a.description\n" +
            "FROM application a\n" +
            "WHERE a.stu_id = #{user_id}")
    List<ApplicationVO> get_application(@Param("user_id") Long user_id);


    //计算得分(id为初访申请表id)
    @Update("UPDATE application\n" +
            "set grade = #{grade}\n" +
            "WHERE id = #{id}")
    void update_grade(@Param("grade")Integer grade,
                     @Param("id")Long id);


    //评判危险等级(id为初访申请表id)
    @Update("UPDATE application\n" +
            "set urgency = #{urgency}\n" +
            "WHERE id = #{id}")
    void update_urgency(@Param("urgency")Integer urgency,
                     @Param("id")Long id);

    //查询答案
    @Select("SELECT answer\n" +
            "FROM application\n" +
            "WHERE id = #{id}")
    String select_answer(@Param("id")Long id);


    //导入答案
    @Update("UPDATE application\n" +
            "SET answer = \n" +
            "WHERE id =")
    void update_answer(@Param("id")Long id,
                       @Param("answer")String answer);



}
