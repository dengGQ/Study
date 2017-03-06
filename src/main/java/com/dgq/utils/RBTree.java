package com.dgq.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * RBTree definition:
 * 1、任何一个节点都有颜色，红色 | 黑色
 * 2、根节点是 黑色
 * 3、父子节点之间不能出现连续的红节点
 * 4、任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等
 * 5、空节是黑色的
 * 
 * RBTree在理论上还是一棵BST(Binary Search Tree)树,但是它在对BST进行插入和删除操作的时候会维持树的平衡，
 * 也就是保证树的高度在[logN， logN+1](极端情况下会出现RBTree树的高度达到2*logN，但是很难遇到)，
 * 这样RBTree的查找时间复杂度始终保持在O(logN)从而接近于理想的BST, RBTree的插入和删除操作的时间复杂度也是O(logN)
 * 
 * 1、 插入修复：
 * 	因为新插入的节点是红色的，而根据以上RBTree的定义可知，只有当父节点为红色的时候需要插入修复操作，遇到父节点为黑色则修复操作结束
 * 
 * 	插入修复操作分为以下三种情况：
 * 	case1 叔叔节点也是红色： 
 * 			针对这种情况的修复操作是： 将父节点和叔叔节点的颜色跟祖父节点进行互换，
 *    		互换之后的祖父节点就成为了新的节点，此时如果祖父节点的父节点也不是黑色的，那就继续修复，直到为黑色停止修复
 * 	case2 叔叔节点为空，且祖父节点、父节点和新节点在一条斜线上：
 * 			这种情况的修复操作是： 将父节点进行左旋或者右旋（取决于新节是在父节点的左侧还是右侧），然后父节点与祖父节点互换颜色
 *  case3 叔叔节点为空，且祖父节点、父节点和新节点不在一条斜线上：
 *  		这种情况的修复操作是：将新节点进行左旋或则右旋（取决于是在父节点的左侧还是右侧），这样就成了 case2的情况了，然后再针对case2进行操作就行了
 * 		
 * @author dgq
 *
 * @param <T>
 */
public class RBTree<T extends Comparable<T>>{
	
	private final RBTreeNode<T> headNode; //起始节点
	
	// node number
	private AtomicLong size = new AtomicLong(0);
	
	// in overwriter mode, all node's value can not has same value
	// in non-overwrite mode, node can have same value, suggest don't use non-overwrite mode
	private volatile boolean overrideMode = true;
	
	public RBTree(){
		this.headNode = new RBTreeNode<T>();
	}
	
	public RBTree(boolean overrideMode){
		this();
		this.overrideMode = overrideMode;
	}

	public boolean isOverrideMode() {
		return overrideMode;
	}
	public void setOverrideMode(boolean overrideMode) {
		this.overrideMode = overrideMode;
	}
	
	// tree node number
	public long getSize(){
		return size.get();
	}
	
	// get the root node
	private RBTreeNode<T> getRoot(){
		return headNode.getLeft();
	}
	
	public T addNode(T value){
		RBTreeNode<T> rbTreeNode = new RBTreeNode<T>(value);
	
		return addNode(rbTreeNode);
	}
	
	private T addNode(RBTreeNode<T> node){
		if(headNode.getLeft() == null){
			
			headNode.setLeft(node);
			
			// root node is black
			node.setRed(false);
			size.incrementAndGet();
		}else{
			RBTreeNode<T> parentNode = findParentNode(node);
			
			int cmp = parentNode.getValue().compareTo(node.getValue());
			
			if(this.overrideMode && cmp == 0){ // overrideMode not have same value
				//old value
				T value = parentNode.getValue();
				
				// 重设新值
				parentNode.setValue(node.getValue());
				
				return value;
			}else if(cmp == 0){ // non-overrideMode ,value exits, ignore this node
				return parentNode.getValue();
			}
			
			setParent(node, parentNode);
			
			if(cmp > 0){
				parentNode.setLeft(node);
			}else{
				parentNode.setRight(node);
			}
			
			// 红黑树插入修复
			fixInsert(node);
			
			// record node number
			size.incrementAndGet();
		}
		
		return null;
	}
	
	/**
	 * 查找新插入节点的父节点
	 * @param node    new node
	 * @return
	 */
	private RBTreeNode<T> findParentNode(RBTreeNode<T> node){
		RBTreeNode<T> dataRoot = getRoot();
		RBTreeNode<T> child = dataRoot;
		
		while(child != null){
			 int cmp = child.getValue().compareTo(node.getValue());
			 if(cmp == 0){
				 return child;
			 }
			 if(cmp  > 0){
				 dataRoot = child;
				 child = child.getLeft();
			 }else if(cmp < 0){
				 dataRoot = child;
				 child = child.getRight();
			 }
		}
		return dataRoot;
	}
	/**
	 * 为在 tree 中没有一样值得新节点设置父节点
	 * @param node
	 * @param parent
	 */
	private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent){
		if(node != null){
			node.setParent(parent);
			if(parent == headNode){
				node.setParent(null);
			}
		}
	}
	
	/**
	 * red black tree insert fix
	 * @param node
	 */
	private void fixInsert(RBTreeNode<T> node){
		
		RBTreeNode<T> parent = node.getParent();
		
		while(parent != null && parent.isRed()){
			
			RBTreeNode<T> uncle = getUncle(node);
			
			if(uncle == null){ //叔叔节点为空需要修复
				RBTreeNode<T> ancestor = parent.getParent();//祖父节点
				
				if(parent == ancestor.getLeft()){
					//新插入节点是不是右节点
					boolean isRight = node == parent.getRight();
					if(isRight){ //如果是右节点进行左旋操作
						rotateLeft(parent);
					}
					rotateRight(ancestor);
				}else{
					
					
				}
			}else{ //uncle is red
				
			}
		}
	}
	
	/**
	 * get uncle node
	 * @param node
	 * @return
	 */
	private RBTreeNode<T> getUncle(RBTreeNode<T> node){
		
		RBTreeNode<T> parent = node.getParent();
		
		RBTreeNode<T> ancestor = parent.getParent();
		
		if(ancestor == null) return null;
		
		if(parent == ancestor.getLeft()){
			return ancestor.getRight();
		}else{
			return ancestor.getLeft();
		}
	}
	
	/**
	 * 插入修复的左旋操作
	 * @param node
	 */
	private void rotateLeft(RBTreeNode<T> node){
	}
	
	/**
	 * 插入修复的右旋转
	 * @param node
	 */
	private void rotateRight(RBTreeNode<T> node){
	
	}
}
