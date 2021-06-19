package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Organization;
import com.caper.psychological_counseling.model.domain.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemMapper {
  List<SysMenu> selectMenuTree(@Param("rootMenuId") Integer rootMenuId ,
                               @Param("menuNameLike") String menuNameLike,
                               @Param("menuStatus") Boolean menuStatus);

  //根据用户名加载用户可以访问的菜单
  @Select("<select id=\"selectMenuByUsername\"\n" +
          "        resultType=\"com.zimug.dongbb.common.persistence.auto.model.SysMenu\">\n" +
          "  SELECT distinct m.id,menu_pid,menu_pids,is_leaf,menu_name,url,icon,sort,level,status\n" +
          "  FROM sys_menu m\n" +
          "  LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id\n" +
          "  LEFT JOIN sys_user_role ur ON ur.role_id = rm.role_id\n" +
          "  LEFT JOIN sys_user u ON u.id = ur.user_id\n" +
          "  WHERE u.username = #{username}\n" +
          "  ORDER BY level,sort\n" +
          "</select>")
  List<SysMenu> selectMenuByUsername(@Param("username") String username );
}