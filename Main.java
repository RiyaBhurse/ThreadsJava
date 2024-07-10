package threads;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.*;

public class Main {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
        // System.out.println("Hello World" + Thread.currentThread().getName());

        // HelloWorldPrinter hwp = new HelloWorldPrinter();
        // hwp.start();
        // HelloWorldPrinter hwp1 = new HelloWorldPrinter();
        // hwp1.start();
        
        // NumberPrinter np1 = new NumberPrinter();
        // np1.start();
        // NumberPrinter np2 = new NumberPrinter();
        // np2.start();
     
        // for(int i=1; i<=100; i++){
        //   SingleNumberPrinterV2 task = new SingleNumberPrinterV2(i);
        //   Thread th = new Thread(task);
        //   th.start();
        // }

        // ExecutorService will take care of task queue and thread pool 
        // ExecutorService es = Executors.newFixedThreadPool(10);

        // If free threads are available then it will not create new thread
        // If all threads are busy then it will create new thread
        ExecutorService es = Executors.newCachedThreadPool();
         
        // for(int i=1; i<=100; i++){
        //   es.submit(new SingleNumberPrinterV2(i));
        // }

        // ArrayList<Integer> list0 = new ArrayList<>();
        // for(int i=1; i<=10; i++){
        //   list0.add(i);
        // }
        // ArrayListModifier arrayListModifier = new ArrayListModifier(list0);
        // Future<ArrayList<Integer>> doubleList = es.submit(arrayListModifier);
        // System.out.println(doubleList.get());

        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(10);
        // list.add(1);
        // list.add(5);
        // list.add(3);
        // list.add(7);
        // list.add(2);
        // list.add(8);
        // list.add(6);
        // list.add(4);
        // list.add(9);
        // Sorter sortingTask = new Sorter(list);
        // Future<ArrayList<Integer>> sorted = es.submit(sortingTask);
        // System.out.println(sorted.get());

        // ExecutorService es = Executors.newCachedThreadPool();g

        Value v1 = new Value(0);
        AdderWithSyncronization adderWithSyncronization = new AdderWithSyncronization(v1);
        SubWithSyncronization subWithSyncronization = new SubWithSyncronization(v1);
        Future<Void> addFWithSyncronization =  es.submit(adderWithSyncronization);
        Future<Void> subFWithSyncronization =  es.submit(subWithSyncronization);
        addFWithSyncronization.get();
        subFWithSyncronization.get();
        System.out.println("with syncronization:"+v1.getData());  
        
        // Value v2 = new Value(0);
        // Adder adder = new Adder(v2);
        // Sub sub = new Sub(v2);
        // Future<Void> addF =  es.submit(adder);
        // Future<Void> subF =  es.submit(sub);
        // addF.get();
        // subF.get();
        // System.out.println("without lock: "+v2.data);

        Value v3 = new Value(0);
        Lock lock = new ReentrantLock();
        AdderWithLock adderWithLock = new AdderWithLock(v3, lock);
        SubWithLock subWithLock = new SubWithLock(v3, lock);
        Future<Void> addFWithLock =  es.submit(adderWithLock);
        Future<Void> subFWithLock =  es.submit(subWithLock);
        addFWithLock.get();
        subFWithLock.get();
        System.out.println("with lock: "+v3.getData());     
        
        // 1.Critical Section: Part of code that works on shared data
        // 2.Race Condition: When multiple threads are trying to access critical section at the same time
        // 3.Preemptive OS: OS that can stop a thread at any time
        //   when three of them are there then we will have Syncronization issue
        // Syncronization actually means that only one thread can access the critical section at a time
        //  Solution for Synchronized: 
        // 1. Mutual Exclusion: Only one thread can access critical section at a time
        //    No race condition
        // 2. Progress: Overall application should keep making some progress
        // 3. Bounded Waiting: W
        //    There should be a limit on how long a thread can wait
        // 4. No Busy Waiting:they should not be checking if cpu is free or not
        //    ambani's marriage and washroom example 


      // Actual solution:
      // 1. Mutex (mutualexclusion): Lock
      //    Lock is interface

      es.shutdown();
    }
  }

//when we want our function to return some data we will not use runnables interface
// we will use callable interface

// class HelloWorldPrinter extends Thread {
//   public void run() {
//     System.out.println("Hello World" + Thread.currentThread().getName());
//   }
// }

// class NumberPrinter extends Thread {
//   public void run(){
//     for (int i = 1; i <= 10; i++) {
//       try {
//         Thread.sleep(1000);
//       } catch (InterruptedException ex) {
//       }
//       System.out.println(i + " " + Thread.currentThread().getName());
//     }
//   }
// }

// class SingleNumberPrinter extends Thread {
//   static int num=0;
//   public void run() {
//     num++;
//     System.out.println(num + " " + Thread.currentThread().getName());
//   }
// }

// class SingleNumberPrinterV2 extends Thread{
//   int num;
//   SingleNumberPrinterV2(int num) {
//     this.num = num;
//   }
//   @Override
//   public void run() {
//     System.out.println(num + " " + Thread.currentThread().getName());
//   }
// }

// class SingleNumberPrinterV2 implements Runnable {
//   int num;
//   SingleNumberPrinterV2(int num) {
//     this.num = num;
//   }
//   @Override
//   public void run() {
//     System.out.println(num + " " + Thread.currentThread().getName());
//   }
// }

// class student extends Thread{
//   //basically if we want student to inherit something from user class
//   // then we cannot use extends keyword as java does not support multiple inheritance
//   // so we can use interface
//   // we can use implements keyword to implement an interface
//   // implements runnable interface 
//   public void run(){
//     System.out.println("Student is studying");
//   }
// }

// class DoubleNumberInList extends Thread{
//   ArrayList<Integer> list;
//   DoubleNumberInList(ArrayList<Integer> list){
//     this.list = list;
//   }
//   public void run(){
//     for(int i=0; i<list.size(); i++){
//       list.set(i, list.get(i)*2);
//     }
//     System.out.println(list);
//   }
// }

// class ArrayListModifier implements Callable<ArrayList<Integer>>{
//   ArrayList<Integer> list;
//   ArrayListModifier(ArrayList<Integer> list){
//     this.list = list;
//   }
//   public ArrayList<Integer> call(){
//     ArrayList<Integer> doubleList = new ArrayList<>();
//     for(int i=0; i<list.size(); i++){
//       doubleList.add(list.get(i)*2);
//     }
//     return doubleList;
//   }
// }

// class Sorter implements Callable<ArrayList<Integer>> {
//   ArrayList<Integer> listToSort;
//   Sorter(ArrayList<Integer> listToSort) {
//     this.listToSort = listToSort;
//   }
//   @Override
//   public ArrayList<Integer> call() throws InterruptedException, ExecutionException {
//     if (listToSort.size() <= 1) return listToSort;
//     int mid = listToSort.size() / 2;
//     ArrayList<Integer> left = getSubList(listToSort, 0, mid - 1);
//     ArrayList<Integer> right = getSubList(listToSort, mid, listToSort.size() - 1);
//     ExecutorService es = Executors.newFixedThreadPool(5);
//     Sorter leftSort = new Sorter(left);
//     Sorter rightSort = new Sorter(right);
//     Future<ArrayList<Integer>> leftSorted = es.submit(leftSort);
//     Future<ArrayList<Integer>> rightSorted = es.submit(rightSort);
//     ArrayList<Integer> sortedLeft = leftSorted.get();
//     ArrayList<Integer> sortedRight = rightSorted.get();
//     es.shutdown(); 
//     return merge(sortedLeft, sortedRight);
//   }
//   public ArrayList<Integer> getSubList(ArrayList<Integer> list, int start, int end) {
//     ArrayList<Integer> subList = new ArrayList<>();
//     for (int i = start; i <= end; i++) {
//       subList.add(list.get(i));
//     }
//     return subList;
//   }
//   public ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
//     ArrayList<Integer> merged = new ArrayList<>();
//     int leftIndex = 0;
//     int rightIndex = 0;
//     while (leftIndex < left.size() && rightIndex < right.size()) {
//       if (left.get(leftIndex) < right.get(rightIndex)) {
//         merged.add(left.get(leftIndex));
//         leftIndex++;
//       } else {
//         merged.add(right.get(rightIndex));
//         rightIndex++;
//       }
//     }
//     while (leftIndex < left.size()) {
//       merged.add(left.get(leftIndex));
//       leftIndex++;
//     }
//     while (rightIndex < right.size()) {
//       merged.add(right.get(rightIndex));
//       rightIndex++;
//     }
//     return merged;
//   }
// }

class Value {
    private int data;
    Value(int data) {
      this.data = data;
    }
    synchronized public int getData() {
      return data;
    }
    synchronized public void setData(int data) {
      this.data = data;
    }
    public void add(int a){
      this.data += a;
    }
    public void sub(int a){
      this.data -= a;
    }
}

class Adder implements Callable<Void>{
  Value v1;
  public Adder(Value v1){
    this.v1 = v1;
  }
  @Override
  public Void call(){
    for(int i=0; i<=1000; i++){
      v1.setData(i+v1.getData());
    }
    return null;
  }
}

class Sub implements Callable<Void>{
  Value v1;
  public Sub(Value v1){
    this.v1 = v1;
  }
  @Override
  public Void call(){
    for(int i=0; i<=1000; i++){
      this.v1.setData(v1.getData()-i);
    }
    return null;
  }
}

class AdderWithLock implements Callable<Void>{
  Value v1;
  Lock lock;
  public AdderWithLock(Value v1, Lock lock){
    this.v1 = v1;
    this.lock = lock;
  }
  @Override
  public Void call(){
    for(int i=0; i<=1000; i++){
      lock.lock();
      this.v1.setData(i+v1.getData());
      lock.unlock();
    }
    return null;
  }
}

class SubWithLock implements Callable<Void>{
  Value v1;
  Lock lock;
  public SubWithLock(Value v1, Lock lock){
    this.v1 = v1;
    this.lock = lock;
  }
  @Override
  public Void call(){
    for(int i=0; i<=1000; i++){
      lock.lock();
      v1.setData(v1.getData()-i);
      lock.unlock();
    }
    return null;
  }
}

class AdderWithSyncronization implements Callable<Void>{
  Value v1;
  public AdderWithSyncronization(Value v1){
    this.v1 = v1;
  }
  @Override
  public Void call(){
    for(int i=0; i<=1000; i++){
      synchronized(v1){
        v1.setData(i+v1.getData());
      }
    }
    return null;
  }
}

class SubWithSyncronization implements Callable<Void>{
  Value v1;
  public SubWithSyncronization(Value v1){
    this.v1 = v1;
  }
  @Override
  public Void call(){
    for(int i=0; i<=1000; i++){
      synchronized(v1){
        v1.setData(v1.getData()-i);
      }
    }
    return null;
  }
}




