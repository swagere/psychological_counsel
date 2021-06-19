package com.caper.psychological_counseling.common.config.system.utils;

import java.util.ArrayList;
import java.util.List;

public class DataTreeUtil {

    public static <T extends DataTree<T>> List<T> buildTree(
         List<T> paramList, Long rootNodeId) {
    List<T> returnList = new ArrayList<T>();
      for (T node : paramList) {//查找根节点
           if(node.getId().equals(rootNodeId) ){
                returnList.add(node);
           }
      }
      //递归为children赋值
      for (T entry : paramList) {
           toTreeChildren(returnList,entry);
      }
      return returnList;
    }

    public static <T extends DataTree<T>> List<T> buildTreeWithoutRoot(
            List<T> paramList, Long rootNodeId) {
        List<T> returnList = new ArrayList<T>();
        for (T node : paramList) {//查找根节点
            if(node.getParentId().equals(rootNodeId) ){
                returnList.add(node);
            }
        }
        //递归为children赋值
        for (T entry : paramList) {
            toTreeChildren(returnList,entry);
        }
        return returnList;
    }

    private static <T extends DataTree<T>> void toTreeChildren(List<T> returnList, T entry) {
      for (T node : returnList) {
           if(entry.getParentId().equals(node.getId())){
                if(node.getChildren() == null){
                     node.setChildren(new ArrayList<T>());
                }
                node.getChildren().add(entry);
           }
           if(node.getChildren() != null){
                toTreeChildren(node.getChildren(),entry);
           }
      }
    }
}