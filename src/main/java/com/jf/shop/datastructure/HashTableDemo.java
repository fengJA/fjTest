package com.jf.shop.datastructure;

import java.util.Scanner;

/**
 * @author fengj
 * @date 2019/10/12 -20:44
 */
public class HashTableDemo {
    public static void main(String[] args) {

        //创建哈希表
        HashTable hashTab = new HashTable(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Empo emp = new Empo(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.deleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }


    }

}

class HashTable{
    private int size;
    private EmpLinkedList[] empLinkedLists;

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Empo empo){
        int empLinkedListNO  = hasFun(empo.num);
        empLinkedLists[empLinkedListNO].add(empo);
    }

    //遍历所有的链表,遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hasFun(id);
        Empo emp = empLinkedLists[empLinkedListNO].findById(id);
        if(emp != null) {//找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        }else{
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    public void deleteById(int id){
        int empLinkedListNO = hasFun(id);
        empLinkedLists[empLinkedListNO].deleteById(id);
    }

    public int hasFun(int id){
        return id % size;
    }


}

class EmpLinkedList{
    private Empo head;

    public void add(Empo empo){
        Empo temp = head;
        if (head == null){
            head = empo;
            return;
        }else {
            while (true){
                if (temp.next == null){
                    break;
                }
                temp = temp.next;
            }
            temp.next = empo;
        }
    }

    // 遍历该节点下面的所有数据
    public void list(int num){
        if (head == null){
            System.out.println("该链表为空");
            return;
        }
        System.out.print("第 "+(num+1)+" 链表的信息为");

        Empo temp = head;
        while (true){
            System.out.printf(" => id=%d name=%s\t", temp.num, temp.name);
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
    }

    // 查询
    public Empo findById(int num){
        if (head == null){
            System.out.println("该链表为空");
            return null;
        }
        Empo temp = head;
        while (true){
            if (num == temp.num){
                break;
            }else if (temp.next == null){
               temp = null;
                break;
            }

            temp = temp.next;
        }
        return temp;
    }

    // 删除
    public void deleteById(int num){
        if (head == null){
            System.out.println("该链表为空");
            return;
        }
        Empo temp = head;
        while (true){
            if (num == temp.num){
                if (temp.next == null){
                    temp = null;
                }else {
                    temp.next = temp.next.next;
                }

                break;
            }else if (temp.next == null){
                System.out.println("该数据不存在");
                break;
            }
            temp = temp.next;
        }
    }
}

class Empo{
    public int num;
    public String name;
    public Empo next;

    public Empo(int num, String name) {
        this.num = num;
        this.name = name;
    }
}
