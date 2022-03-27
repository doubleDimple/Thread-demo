package com.doubleiDimple.StopThread;

import com.doubleiDimple.common.UnSafe;
import com.doubleiDimple.pojo.User;

@UnSafe
public class StopThread {

    public static  User user = new User(0,
            "0");

    public static void main(String[] args){
        new ReadObjectThread().start();
        while (true){
            ChangeObjectThread thread =
                    new ChangeObjectThread();
            thread.start();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }
    }

    public static class ChangeObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted");
                    break;
                }
                synchronized (user) {
                     int id = (int) (System.currentTimeMillis() / 1000);
                     user.setId(id);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(id));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{

        @Override
        public void run() {
            while (true){
                synchronized (user){
                    if (user.getId() != (Integer.parseInt(user.getName()))){
                        System.out.println(user.toString());
                    }
                }
                Thread.yield();
            }
        }
    }
}
