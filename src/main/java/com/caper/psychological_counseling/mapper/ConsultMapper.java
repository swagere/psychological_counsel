package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.model.vo.ConsultVO;
import com.caper.psychological_counseling.model.vo.ConsultVO;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface ConsultMapper extends BaseMapper<Consult> {

    //选择符合条件的咨询师
    @Select("SELECT c.id,c.user_id,u.id,u.name,c.week,c.date,c.begin_time,c.end_time,a.area_name\n" +
            "FROM sys_user u \n" +
            "INNER JOIN schedule c ON(u.id = c.user_id)\n" +
            "INNER JOIN area a ON(c.area_id = a.id)\n" +
            "WHERE u.type like '%${type}%' and a.org_id = #{area_id} and date >= #{date} and date <=#{end_time}\n"+
            "and u.id in (SELECT user_id FROM sys_user_role WHERE role_id = 3)")
    List<ScheduleVO> find_consults(@Param("area_id")Long area_id,
                                   @Param("type")String type,
                                   @Param("date") Date date,
                                   @Param("end_time")Date end_time);

    //查询排班表的日期、time、校区、咨询师。根据排班表id
    @Select("SELECT *" +
            " FROM schedule" +
            " WHERE id = #{id}")
    ScheduleVO find_schedule(@Param("id")Long id);





    //根据时间、time、校区、咨询师查找排班id
    @Select("SELECT id" +
            " FROM schedule" +
            " WHERE date = #{date} and " +
            "begin_time = #{begin_time} and " +
            "end_time = #{end_time} and " +
            "user_id = #{user_id} and " +
            "area_id = #{area_id} ")
    Long  find_scheduleId(@Param("area_id")Long area_id,
                          @Param("date")Date date,
                          @Param("begin_time")String begin_time,
                          @Param("end_time")String end_time,
                          @Param("user_id")Long user_id);



    //创建咨询表
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Consult consult);


    //用户添加评论
    @Update("UPDATE consult\n" +
            "SET evaluate = #{evaluate}\n" +
            "WHERE id = #{id} ")
    void update_evaluate(@Param("evaluate") String evaluate,
                         @Param("id")Long id);


    //添加咨询状态
    @Update("UPDATE consult\n" +
            "SET `status` = #{status}\n" +
            "WHERE id = #{id}")
    void update_consultStatus(@Param("status")Integer status,
                              @Param("id")Long id);



    //修改咨询结论
    @Update("UPDATE consult\n" +
            "SET result = #{result}\n" +
            "WHERE id = #{id}")
    void update_consultResult(@Param("result")String result,
                              @Param("id")Long id);



    //添加咨询类型
    @Update("UPDATE consult\n" +
            "SET type = #{type}\n" +
            "WHERE id = #{id}")
    void update_consultType(@Param("type")String type,
                              @Param("id")Long id);


    //查看自己的咨询表（根据状态）
    @Select("SELECT *\n" +
            "FROM consult\n" +
            "WHERE status = #{status} \n" +
            "and id in\n" +
            "(SELECT consult_id FROM `consult_record` WHERE schedule_id in \n" +
            "(SELECT id FROM schedule WHERE user_id = #{id}))")
    List<ConsultVO> selectConsult(@Param("id")Long id,
                                  @Param("status")Integer status);




    //查看自己的排班表（今天以及以后，今天以及以前，全部）
    @Select("SELECT c.id,c.user_id,u.name,c.week,c.date,c.begin_time,c.end_time,a.area_name\n" +
            "FROM sys_user u \n" +
            "INNER JOIN schedule c ON(u.id = c.user_id)\n" +
            "INNER JOIN area a ON(c.area_id = a.id)\n" +
            "WHERE  date >= #{date}\n"+
            "and u.id = #{id}")
    List<ScheduleVO> selectConsultSchedules(@Param("id")Long id,
                                            @Param("date")Date date);

    @Select("SELECT c.id,c.user_id,u.name,c.week,c.date,c.begin_time,c.end_time,a.area_name\n" +
            "FROM sys_user u \n" +
            "INNER JOIN schedule c ON(u.id = c.user_id)\n" +
            "INNER JOIN area a ON(c.area_id = a.id)\n" +
            "WHERE  date < #{date}\n"+
            "and u.id = #{id}")
    List<ScheduleVO> selectConsultSchedulesBefor(@Param("id")Long id,
                                                 @Param("date")Date date);


    List<ConsultVO> selectConsultsByApplicationIds(@Param("application_ids") List<Long> application_ids);
}
