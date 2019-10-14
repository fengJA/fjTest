package com.jf.shop.login.aa;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CalculatorImp   {
    public static void main(String[] args) {
       /* MD5Test md = new MD5Test();
        Proxy.newProxyInstance(md.getClass().getClassLoader(), md.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.equals("getParamter")){
                    String invoke = (String) method.invoke(md, args);
                    invoke = new String(invoke.getBytes("utf-8"),"ASCII");
                    return invoke;
                }
                return method.invoke(md, args);
            }
        });*/

        CalculatorImp.collection();
    }

    public static void collection(){
        List<String> strings = Arrays.asList("5", "7","3","2","8","6");
        int[] array = new int[]{9,5,7,12};
        Arrays.sort(array);
        System.out.println("array:"+array);
        System.out.println("array1:"+array.toString());
        Collections.sort(strings);
        System.out.println("strings:"+strings.toString());
        System.out.println("arrays:"+strings.toArray());
    }

    public static void hashTableTest(){
        Hashtable htable = new Hashtable();
        HashMap hMap = new HashMap();

        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ConcurrentHashMap ch = new ConcurrentHashMap();
        Collection c = new ArrayList();
        ArrayList<String> strings = new ArrayList<>();
        ListIterator<String> stringListIterator = strings.listIterator();
        strings.iterator();
        Iterator iterator = c.iterator();
        boolean b = iterator.hasNext();
        if (b){
            Object next = iterator.next();
        }
        Set<Object> hashSet = Collections.synchronizedSet(new HashSet<>());
        Set<Object> treeSet = Collections.synchronizedSet(new TreeSet<>());
        SortedSet<Object> sortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        Map<Object, Object> hashMap = Collections.synchronizedMap(new HashMap<>());
        List<Object> arrayList = Collections.synchronizedList(new ArrayList<>());

    }

    public static void hashMapTest(){
        HashMap<String, String> hashMap = new HashMap<>();
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        Set<String> strings = hashMap.keySet();
        hashMap.get(strings);
        TreeMap tr = new TreeMap();
        Map.Entry entry = tr.lastEntry();

    }
}
