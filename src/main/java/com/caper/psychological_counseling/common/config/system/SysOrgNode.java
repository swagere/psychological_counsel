package com.caper.psychological_counseling.common.config.system;

import com.caper.psychological_counseling.common.config.system.utils.DataTree;
import com.caper.psychological_counseling.model.domain.Organization;

import java.util.List;

public class SysOrgNode extends Organization implements DataTree<SysOrgNode> {
    //为某对象加上children成员变量
    private List<SysOrgNode> children;

    @Override
    public Long getParentId() {
        //因为不同的人设计model或者数据库，
        //父id字段叫不同的名字，pid或parentId或org_pid等，
        //这里适配一下，统一为使用getParentId，获取父id数据
        return super.getOrgPid();
    }
    //set和get方法，为children赋值取值
    @Override
    public void setChildren(List<SysOrgNode> children) {
        this.children = children;
    }

    @Override
    public List<SysOrgNode> getChildren() {
        return this.children;
    }
}