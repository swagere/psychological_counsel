package com.caper.psychological_counseling.common.config.utils;

import java.util.List;

public interface DataTree<T> {
   //维护树形关系：元素一id
    public Long getId();
    //维护树形关系：元素二父id
    public Long getParentId();
    //子节点数组
    public void setChildren(List<T> children);
    
    public List<T> getChildren();
}