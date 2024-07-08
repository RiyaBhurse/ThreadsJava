package threads;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class NumberPrinter extends Thread {
  public void run(){
    for (int i = 1; i <= 10; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
      System.out.println(i + " " + Thread.currentThread().getName());
    }
  }
}

// class SingleNumberPrinter extends Thread {
  int num;

  SingleNumberPrinter(int num) {
    this.num = num;
  }

  public void run() {
    num++;
    System.out.println(num + " " + Thread.currentThread().getName());
  }
}

// class HelloWorldPrinter extends Thread {

  public void run() {
    System.out.println("Hello World" + Thread.currentThread().getName());
  }
}

// class SingleNumberPrinterV2 implements Runnable {
  int num;

  SingleNumberPrinterV2(int num) {
    this.num = num;
  }

  @Override
  public void run() {
    System.out.println(num + " " + Thread.currentThread().getName());
  }
}

// class DoubleNumberInList extends Thread{
  ArrayList<Integer> list;
  DoubleNumberInList(ArrayList<Integer> list){
    this.list = list;
  }
  public void run(){
    for(int i=0; i<list.size(); i++){
      list.set(i, list.get(i)*2);
    }
    System.out.println(list);
  }

}

// class ArrayListModifier implements Runnable<ArrayList<Integer>>{
  ArrayList<Integer> list;
  ArrayListModifier(ArrayList<Integer> list){
    this.list = list;
  }
  public ArrayList<Integer> call() throws Exception{
    ArrayList<Integer> doubleList = new ArrayList<>();

  }
}

class Sorter implements Callable<ArrayList<Integer>> {
  @Override

  ArrayList<Integer> listToSort;

  Sorter(ArrayList<Integer> listToSort) {
    this.listToSort = listToSort;
  }

  public ArrayList<Integer> call() throws InterruptedException, ExecutionException {
    if (listToSort.size() <= 1)
      return listToSort;
    int mid = listToSort.size() / 2;
    ArrayList<Integer> left = getSubList(listToSort, 0, mid - 1);
    ArrayList<Integer> right = getSubList(listToSort, mid, listToSort.size() - 1);
    ExecutorService es = Executors.newFixedThreadPool(5);

    Sorter leftSort = new Sorter(left);
    Sorter rightSort = new Sorter(right);

    Future<ArrayList<Integer>> leftSorted = es.submit(leftSort);
    Future<ArrayList<Integer>> rightSorted = es.submit(rightSort);

    ArrayList<Integer> sortedLeft = leftSorted.get();
    ArrayList<Integer> sortedRight = rightSorted.get();

    return merge(sortedLeft, sortedRight);
  }

  public ArrayList<Integer> getSubList(ArrayList<Integer> list, int start, int end) {
    ArrayList<Integer> subList = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      subList.add(list.get(i));
    }
    return subList;
  }

  public ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
    ArrayList<Integer> merged = new ArrayList<>();
    int leftIndex = 0;
    int rightIndex = 0;
    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex) < right.get(rightIndex)) {
        merged.add(left.get(leftIndex));
        leftIndex++;
      } else {
        merged.add(right.get(rightIndex));
        rightIndex++;
      }
    }
    while (leftIndex < left.size()) {
      merged.add(left.get(leftIndex));
      leftIndex++;
    }
    while (rightIndex < right.size()) {
      merged.add(right.get(rightIndex));
      rightIndex++;
    }
    return merged;
  }
}

public class Adder{
  
}
public class Subt

public class Innert1 {
  public static void main(String[] args) {
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
        //   SingleNumberPrinter np = new SingleNumberPrinter(i);
        //   np.start();
        // }
        
        // for(int i=1; i<=100; i++){
        //   SingleNumberPrinterV2 task = new SingleNumberPrinterV2(i);
        //   Thread th = new Thread(task);
        //   th.start();
        // }
        ExecutorService es = Executors.newFixedThreadPool(10);
        // ExecutorService es = Executors.newCachedThreadPool(10);
        
        // for(int i=1; i<=100; i++){
        //   es.submit(new SingleNumberPrinterV2(i));
        // }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1; i<=10; i++){
          list.add(i);
        } 

        // DoubleNumberInList dn = new DoubleNumberInList(list);
        // dn.start();

        Sorter task = new Sorter(list);
        Future<ArrayList<Integer>> doubled = es.submit(task);
        System.out.println(doubled.get());
        es.shutdown();
    }
}
