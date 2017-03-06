package com.dgq.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortAlgorithm {

	/**
	 * 二分法 查询有序数列中是否含有指定的数值
	 * 
	 * @param num
	 * @param nums
	 * @return
	 */
	public static boolean dichotomyFind(int num, int[] nums) {
		int start = 0, end = nums.length - 1, middle;

		while (start <= end) {
			middle = (start + end) / 2;

			int middleValue = nums[middle];

			if (middleValue > num) {
				end = --middle;
			} else if (middleValue < num) {
				start = ++middle;
			} else {
				return true;
			}
		}

		return false;
	}

	/**
	 * 二分法排序
	 * 
	 * @param nums
	 */
	public static void dichotomySort(int[] nums) {
		int start, end, middle;
		int middleValue, currentSortNum, j;
		for (int i = 0; i < nums.length; i++) {
			start = 0;
			end = i - 1;
			currentSortNum = nums[i];
			while (start <= end) {
				middle = (start + end) / 2;
				middleValue = nums[middle];
				if (middleValue > currentSortNum) {
					end = middle - 1;
				} else {
					start = middle + 1;
				}
			}

			for (j = i - 1; j > end; j--) {
				nums[j + 1] = nums[j];
			}

			nums[end + 1] = currentSortNum;
		}
	}

	/**
	 * 冒泡排序 (单层循环)
	 * 
	 * @param nums
	 */
	public static void bubbleSort(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			if (i <= 0)
				continue;
			int a = nums[i - 1];
			if (nums[i] < nums[i - 1]) {
				nums[i - 1] = nums[i];
				nums[i] = a;
				i -= 2;
			}
		}
	}

	/**
	 * 冒泡排序 (双层层循环)
	 * 
	 * @param nums
	 */
	public static void bubbleSort2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}

			}
		}
	}

	/**
	 * 归并排序
	 * @param args
	 */
	public static void mergeSort(int[] nums, int low, int high){
		int mid = (low + high) / 2;
		if(low < high){
			mergeSort(nums, low, mid);
			
			mergeSort(nums, mid+1, high);
			
			merge(nums, low, mid, high);
		}
	}
	private static void merge(int[] nums, int low, int mid, int high){
		int[] temp = new int[high - low + 1];
		int i = low, j = mid+1, k = 0;
		
		//将较小的数先移入临时数组
		while(i <= mid && j <= high){
			if(nums[i] < nums[j]){
				temp[k++] = nums[i++];
			}else{
				temp[k++] = nums[j++];
			}
		}
		
		//将左边剩余的数移入数组
		while(i <= mid){
			temp[k++] = nums[i++];
		}
		//将右边的数移入数组
		while(j <= high){
			temp[k++] = nums[j++];
		}
		
		for(int k2 = 0; k2 < temp.length; k2++){
			nums[k2 + low] = temp[k2];
		}
	
	}

	public static void main(String[] args) {
	}
	

		
}
