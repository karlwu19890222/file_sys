package cn.karlwu.common.utils;

import java.util.ArrayList;
import java.util.List;


public class TreeNodeUtils {
	
	 /**
	    * @Title: getfatherNode
	    * @Description 方法描述: 父节点
	    * @param 设定文件： @param treeDataList
	    * @param 设定文件： @return    
	    * @return 返回类型：List<EUTreeNode>    
	    * @throws
	    * @date 最后修改时间：2015年6月9日 下午6:39:26
	    */
	    public final static List<EUTreeNode> getfatherNode(List<EUTreeNode> treeDataList) {
	        List<EUTreeNode> newTreeDataList = new ArrayList<EUTreeNode>();
	        for (EUTreeNode EUTreeNode : treeDataList) {
	            if(EUTreeNode.getPid() == null) {
	                //获取父节点下的子节点
	                EUTreeNode.setChildren(getChildrenNode(EUTreeNode.getId(),treeDataList));
	                EUTreeNode.setState("open");
	                newTreeDataList.add(EUTreeNode);
	            }
	        }
	        return newTreeDataList;
	    }
	     
	    /**
	    * @Title: getChildrenNode
	    * @Description 方法描述: 子节点
	    * @param 设定文件： @param pid
	    * @param 设定文件： @param treeDataList
	    * @param 设定文件： @return    
	    * @return 返回类型：List<EUTreeNode>    
	    * @throws
	    * @date 最后修改时间：2015年6月9日 下午6:39:50
	    */
	    private final static List<EUTreeNode> getChildrenNode(String pid , List<EUTreeNode> treeDataList) {
	        List<EUTreeNode> newTreeDataList = new ArrayList<EUTreeNode>();
	        for (EUTreeNode EUTreeNode : treeDataList) {
	            if(EUTreeNode.getPid() == null)  continue;
	            //这是一个子节点
	            if(EUTreeNode.getPid().equals(pid)){
	                //递归获取子节点下的子节点
	                EUTreeNode.setChildren(getChildrenNode(EUTreeNode.getId() , treeDataList));
	                newTreeDataList.add(EUTreeNode);
	            }
	        }
	        return newTreeDataList;
	    }

}
