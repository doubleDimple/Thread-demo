package com.doubleiDimple.waitAndnotify;

public class PrintEvenAndOdd {

    final static Object object = new Object();

    public static void main(String[] args) {
         PrintOdd printOdd = new PrintOdd();
         PrintEven printEven = new PrintEven();
         printOdd.start();
         printEven.start();
    }

    public static class PrintOdd extends Thread{
        @Override
        public void run() {
            synchronized (object){
                for (int i = 0;i<10;i++){
                    if (i % 2 != 0){
                        System.out.println("T1 print:"+ i);
                        object.notify();
                    }else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public static  class PrintEven extends Thread{
        @Override
        public void run() {
            synchronized (object){
                for (int i = 0;i<10;i++){
                    if (i % 2 == 0){
                        System.out.println("T2 print:"+ i);
                        object.notify();
                    }else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
