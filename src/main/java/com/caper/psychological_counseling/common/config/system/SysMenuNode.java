package com.caper.psychological_counseling.common.config.system;

import com.caper.psychological_counseling.common.config.system.utils.DataTree;
import com.caper.psychological_counseling.model.domain.SysMenu;

import java.util.List;

public class SysMenuNode extends SysMenu implements DataTree<SysMenuNode> {

    private List<SysMenuNode> children;

    private String path;   //新加入path
    private String name;  //新加入name

    public String getPath() {
      return this.getUrl();   //path返回url
    }
    public String getName() {
      return this.getMenuName();  //name返回menuName
    }

    @Override
    public Long getParentId() {
        return super.getMenuPid();
    }
    @Override
    public void setChildren(List<SysMenuNode> children) {
        this.children = children;
    }
    @Override
    public List<SysMenuNode> getChildren() {
        return this.children;
    }
}