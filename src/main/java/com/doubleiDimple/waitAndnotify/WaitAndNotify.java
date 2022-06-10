package com.doubleiDimple.waitAndnotify;

public class WaitAndNotify {

    final static  Object object = new Object();

    public static void main(String[] args) {
         Thread t1 = new T1();
         Thread t2 = new T2();
         t1.start();
         t2.start();

    }
    public static class  T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait obejct");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end");
            }
        }
    }

    public static class T2 extends Thread{

        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ":T2 start notify random thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
