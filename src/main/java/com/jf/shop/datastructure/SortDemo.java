package com.jf.shop.datastructure;

import java.util.Arrays;

/**
 * @author fengj
 * @date 2019/10/9 -20:51
 */
public class SortDemo {
    public static void main(String[] args) {
//        int[] array = {-3,7,3,1,5,4};
//        int[] array = {2,3,5,7,6};
//        int[] array = {7,3,3,3,1};
//        int[] array = {7,3,12,56,11,76,2,3,65,7,3653,256,6,3,4,3,5,6,15,476,2,0};

//        bubbleSort(array);
//        selectSort(array);
//        quickSort(array,0,array.length -1);
//        quickFirstSort(array,0,array.length -1);
//        xx(array,0,array.length -1);

        /*int[] array = {2,6,1,24,8,22,8,2};
        int[] temp = new int[array.length];
        mergeSort(array,0,array.length - 1,temp);*/

        int[] array = {1,55,234,724,873,11};
        radixSort(array);
        System.out.println(array);
        System.out.println(Arrays.toString(array));

    }

    // 冒泡排序
    public static void bubbleSort(int[] array){
        boolean flags = false;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flags = true;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

            if (!flags){
                break;
            }else {
                flags = false;
            }
        }
    }

    // 选择排序
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int index = i;
            /*for (int j = i + 1; j < array.length; j++) {
                if (min > array [j]){
                // 这里交换没必要
                    int temp = array[j];
                    array[j] = min;
                    min = temp;
                    index =j;
                }
            }
            if (index != i){
                array[index] = array[i];
                array[i] = min;
            }*/
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]){
                    min = array[j];
                    index = j;
                }
            }

            if (index != i){
                array[index] = array[i];
                array[i] = min;
            }
        }
    }

    // 插入排序
    public static void insertSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int initArry = array[i];
            int index = i -1;
            while (index >=0 && array[index] > array[i]){
                array[i] = array[index];
                index--;// 小于0后跳出循环
            }
            array[index + 1] = initArry;
        }
    }

    // 希尔排序
    public static void shellSort(int[] array){
        int temp ;
        for (int gap = array.length/2; gap > 0; gap /=2) {
            for (int i = gap; i < array.length; i++) {
                // 挨着判断，效率低
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]){
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }

        // 优化
        for (int gap = array.length/2; gap > 0; gap /=2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int tempNum = array[i];
                if (array[j] < array[j - gap]){
                    while ((j-gap) >= 0 && array[j] < array[j - gap]){
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                }
                array[j] = tempNum;
            }
        }

    }

    // 快速排序,中间值判断
    public static void quickSort(int[] array,int left,int right){
        int l = left;
        int r = right;
        int center = array[(right + left)/2]; // 中间值为判断标准
        int temp = 0;

        while (l < r){
            // 判断左边
            while (array[l] < center){
                l++;
            }
            // 判断右边
            while (array[r] > center){
                r--;
            }

            if (l >= r){ // 表示判断完毕
                break;
            }

            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移，比如7，3,3,3,1
            if(array[l] == center) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if(array[r] == center) {
                l += 1;
            }
        }
        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出StackOverflowError
//        if (l == r){
//            l += 1;
//            r -= 1;
//        }

        if (left < l){ // 向左递归
            quickSort(array,left,l -1);
        }
        if (right > r){ // 向右递归
            quickSort(array,r+1,right);
        }
    }

    // 快速排序，第一个值判断,错的
    public static void quickFirstSort(int[] array,int left,int right){
        int l = left;
        int r = right;
        int key = array[left]; // 中间值为判断标准  {-3,7,3,1,5}  3 2  7 8 1
        int temp = 0;
        boolean flages = false;

        while (l < r){
            while (array[r] > key && l < r){
                r--;
            }

            while (array[l] <= key && l < r){
                l++;
            }

            if (l >= r){
                break;
            }
            temp = array[r];
            array[r] = array[l];
            array[l] = temp;
            flages = true;

           /* if (l < r){
                temp = array[r];
                array[r] = array[l];
                array[l] = temp;
                flages = true;
            }else {
                break;
            }*/
        }

        // 将第一个数（基准值）与最后交换的值互换，即基准值在中间
        if (flages){

            array[left] = array[r];
            array[r] = key;
        }


        if (left < r){

            quickFirstSort(array,left,r -1);
        }
        if (right > l){

            quickFirstSort(array,l +1 ,right);
        }

    }


    public static int midian(int []a,int left,int right,int target) {


        if(left < right) {

            int i = left;
            int j = right;
            int x = a[left];

            while(i < j ) {

                while(i < j && a[j] >= x)
                    j--;
                if(i<j) {
                    a[i]=a[j];
                    i++;
                }

                while(i < j && a[i] <= x)
                    i++;
                if(i<j) {
                    a[j]=a[i];
                    j--;
                }
            }
            a[i] = x;
            /**在每次定位好基准数之后，比较下标*/
            if(i == target)
                return a[i];
            if(i > target) {
                return	midian(a,left,i-1,target);
            }
            if(i<target) {
                return	midian(a,i+1,right,target);
            }
        }
        return a[left];


    }

    // 快速排序，第一个值判断
    public static void xx(int a[],int left,int right) {
//        if(left < right) {//递归出口条件判断
            int x = a[left];
            int i=left;
            int j = right;

            //整个循环目的：用基准数x,将数组分为2个部分(准确来讲是3部分->【左数组】,x,【右数组】)
            while(i<j) {
                /**从后往前比较*/
                while(a[j] > x)
                    j--;
                if(i < j) {
                    a[i]=a[j];
                    i++;
                }

                /**从前往后比较*/
                while(a[i] < x)
                    i++;
                if(i<j) {
                    a[j]=a[i];
                    j--;
                }
            }

        a[i] = x;
        /*if (i == j){
            i += 1;
            j -= 1;
        }*/

        if (left < j){ // 退出递归的条件，向左递归
            quickSort(a,left,j-1);
        }
        if (right > i){ // 退出递归的条件，向右递归
            quickSort(a,i+1,right);
        }


           /* a[i]=x;
            xx(a,left,i-1);// 左边数组
            xx(a,i+1,right);//右边数组*/
//        }

    }

    // 归并排序,时间很快，跟快排差不多
    // 第一步，分解 + 合并
    public static void mergeSort(int[] array,int left,int right,int[] temp){ // temp是临时数组,用来保存array数组中的值
        int mid = (left + right)/2;
        if (left < right){
            mergeSort(array,left,mid,temp);// 左边分解
            mergeSort(array,mid + 1,right,temp);// 左边分解
            merge(array,left,mid,right,temp);
        }

    }
    // 第二步，排序+合并
    public static void merge(int[] array,int left,int mid,int right,int[] temp){
        int t = 0; // temp数组中的索引
        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; //初始化j, 右边有序序列的初始索引
        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到 temp数组
            //然后 t++, i++
            if(array[i] <= array[j]) {
                temp[t] = array[i];
                t += 1;
                i += 1;
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = array[j];
                t += 1;
                j += 1;
            }
        }

        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while( i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = array[i];
            t += 1;
            i += 1;
        }

        while( j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = array[j];
            t += 1;
            j += 1;
        }

        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        //第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0  right = 7
        while(tempLeft <= right) {
            array[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

    // 基数排序，是基于桶排序的
    public static void radixSort(int[] array){
        // 得到最大数
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
        }

        int maxLength = (max + "").length();// 最大数的位数
//        String.valueOf(max).length();

        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        int[][] bucket = new int[10][array.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这里理解
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];


        for (int i = 0,n = 1; i < maxLength ; i++,n *= 10) {
            for (int j = 0; j < array.length; j++) {
                int digitalOfElement = array[j] / n % 10;
                bucket[digitalOfElement][bucketElementCounts[digitalOfElement]] = array[j];
                bucketElementCounts[digitalOfElement]++;
            }

            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCounts[k] != 0){
                    //循环该桶即第k个桶(即第k个一维数组), 放入
                    for(int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        array[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
                bucketElementCounts[k] = 0;
            }
        }

    }

    //基数排序方法
    public static void radixSort1(int[] arr) {

        //根据前面的推导过程，我们可以得到最终的基数排序代码

        //1. 得到数组中最大的数的位数
        int max = arr[0]; //假设第一数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();


        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 名明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这里理解
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];


        //这里我们使用循环将代码处理

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucket.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(即第k个一维数组), 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
                bucketElementCounts[k] = 0;

            }
            //System.out.println("第"+(i+1)+"轮，对个位的排序处理 arr =" + Arrays.toString(arr));

        }
    }


}


