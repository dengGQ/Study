package com.dgq.utils;

public class DoubleLinkList {
	
	public DoubleLinkNode head; 
	
	public DoubleLinkNode tail;
	
	// 查询第 index 项的内容
	public DoubleLinkNode queryNode(int index){
		
		index = index < 0 ? 0 : index;
		
		int i = 0;
		
		DoubleLinkNode temnphead = head;
		
		while(i <= index && temnphead != null){
			temnphead = head.next;
			i++;
		}
		
		return temnphead;
	};
	
	
	// 将 toBeDelete 从链表中删除
	public void deleteNode(DoubleLinkNode toBeDelete){
		toBeDelete.prev.next = toBeDelete.next;
		toBeDelete.next.prev = toBeDelete.prev;
	};
	
	// 将toBeInsert插入到pos结点后面
	public void insertNode(DoubleLinkNode pos, DoubleLinkNode toBeInsert){
		DoubleLinkNode temp = pos.next;
		
		pos.next = toBeInsert;
		
		toBeInsert.prev = pos;
		toBeInsert.next = temp;
		
		temp.prev = toBeInsert;
		
	}; 

}
