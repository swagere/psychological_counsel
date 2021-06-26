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

  List<SysMenu> selectMenuByUsername(@Param("username") String username );

  List<Organization> selectOrgTree(@Param("rootOrgId") Long rootOrgId ,
                             @Param("orgNameLike") String orgNameLike,
                             @Param("orgStatus") Boolean orgStatus);

}