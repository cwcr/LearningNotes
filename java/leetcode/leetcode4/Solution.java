package leetcode4;

public class Solution {
	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 int size = 0;
		 if(nums1!=null) {
			 size += nums1.length;
		 }
		 if(nums2!=null) {
			 size += nums2.length;
		 }
		 int i = 0;
		 int j = 0;
		 int index = -1;
		 int[] newInts = new int[size];
		 while ((i+j)<size) {
			 index ++ ;
			if(i==nums1.length) {newInts[index]=nums2[j]; j++;continue;}
			else if(j== nums2.length) {
				newInts[index]=nums1[i];i++;continue;
			}
			else if(nums1[i]<nums2[j]) {
				newInts[index]=nums1[i];
				i++;
			}else {
				newInts[index]=nums2[j];
				j++;
			}
		}
		 if(size%2==0) {
			 return (double)(newInts[index/2]+newInts[index/2+1])/2;
		 }
		 return newInts[index/2];
	 }
	 
	 public static void main(String[] args) {
		System.err.println(new Solution().findMedianSortedArrays(new int[]{}, new int[]{2,3}));;
	}
	 
	 public void show(int[] ints) {
		 for (int i : ints) {
			System.err.print(i+" ");
		}
		 System.err.println();
	 }
}
