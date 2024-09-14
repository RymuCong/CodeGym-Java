package com.t3h;

public class RunnableDemo implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello from RunnableDemo!");
    }

    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        new Thread(runnableDemo).start();
    }
}
