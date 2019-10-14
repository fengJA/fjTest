package com.jf.shop.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengj
 * @date 2019/10/11 -23:30
 */
public class Search {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 8, 1000, 8, 1234};
//        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 8);
//        System.out.println(list);

        int index = insertSort(arr, 0, arr.length - 1, 10);
        System.out.println(arr[index]);
    }

    public static int binarySearch(int[] array, int left, int right, int key) {
        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (left > right) {
            return -1;
        } else if (key > midValue) {
            return binarySearch(array, mid + 1, right, key);
        } else if (key < midValue) {
            return binarySearch(array, left, mid - 1, key);
        } else {
            return mid;
        }

    }

    //完成一个课后思考题:
    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     */

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        System.out.println("hello~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            List<Integer> resIndexlist = new ArrayList<>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true){
                if (temp < 0){
                    break;
                }

                if (temp > 0 && arr[temp] == findVal) {//退出
                    //否则，就temp 放入到 resIndexlist
                    resIndexlist.add(temp);
                }
                temp -= 1; //temp左移

            }


            resIndexlist.add(mid);  //

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;

            while (true){

                if (temp > arr.length){
                    break;
                }
                if (temp < arr.length - 1 && arr[temp] == findVal) {//退出

                    //否则，就temp 放入到 resIndexlist
                    resIndexlist.add(temp);
                }
                temp += 1; //temp右移
            }


            return resIndexlist;
        }

    }

    // 插值排序，基于二分查找排序的，是有序数组中查找
    public static int insertSort(int[] array,int left,int right,int key){
        int mid = left + (right - left) * (key - array[left])/(array[right] - array[left]);
        int midValue = array[mid];

        if (right < left || array[left] > midValue || array[right] < midValue){
            return -1;
        }else if (midValue > key){
            return insertSort(array,left,mid-1,key);
        }else if (midValue < key){
            return insertSort(array,mid+1,right,key);
        }else {
            return mid;
        }
    }
}
