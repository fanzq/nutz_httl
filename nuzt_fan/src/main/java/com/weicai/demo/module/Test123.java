package com.weicai.demo.module;

import java.util.Objects;

/**
 * Created by fan on 2015/9/14.
 */
public class Test123 {
    public static int getMiddle(Integer[] list, int low, int high) {
        int tmp = list[low];    //数组的第一个作为中轴
        while (low < high) {
            System.out.println("list["+high+"]="+list[high]+"   temp="+tmp);
            while (low < high && list[high] > tmp) {
                high--;
                System.out.println(high+"--------H");
            }
            list[low] = list[high];   //比中轴小的记录移到低端
            while (low < high && list[low] < tmp) {
                low++;
            }
            list[high] = list[low];   //比中轴大的记录移到高端
        }
        list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }
    public void _quickSort(Integer[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);  //将list数组进行一分为二
            _quickSort(list, low, middle - 1);        //对低字表进行递归排序
            _quickSort(list, middle + 1, high);       //对高字表进行递归排序
        }
    }
    public static void main(String[] args) {
        System.out.println(12<<2);
       // Integer[] list={34,3,53,2,23,7,14,10};
      //  getMiddle(list,0,list.length-1);
//        String s=null;
//        String s1="";
//        Objects.toString(s,"12");
//        Objects.toString(s1,"aa");
//        System.out.println(s+"----------"+s1);

    long lstart1=    System.nanoTime();
        fun1(999999999);
    long lend1=    System.nanoTime();
        System.out.println(lend1-lstart1+"----------ss");
    long lstart2=    System.nanoTime();
        fun2(999999999);
    long lend2=    System.nanoTime();
        System.out.println(lend2-lstart2+"----------ss");
    }

    public  static void  fun1(int n){
        if(n % 2==0){
            System.out.println(-n/2);
        }else{
            System.out.println(n/2+"            eeeeeeeeeeee");
            System.out.println(n);
            System.out.println(-(n/2)+n);
        }
    }
    public  static void  fun2(int n){
        if(n % 2==0){
            System.out.println(-(n>>1));
        }else{
            System.out.println(n-(n>>1));
        }
    }
}
