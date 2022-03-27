package com.doubleiDimple;

public class SleepThread {


    public static void main(String[] args) throws InterruptedException {
         Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("interurupted");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("interruted when sleep");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
         thread.start();
         Thread.sleep(2000);
         thread.interrupt();

    }
}
