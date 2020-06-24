package com.company;
/**
 *
 * Date: 24 of June 2020
 * @author VitaliiDanyshchuk
 * @version 1.1
 *
 * Classname LogsManager
 *
 **/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws IOException {

        String path =  "C:\\Users\\Vitaliy\\Desktop\\logs.txt";

        LogsManage manage = new LogsManage(path);

        // start time execution
        LocalDateTime start1 = LocalDateTime.now();

        // get erorrs by this date
        manage.getErrorByDate("2020-05-08");
        manage.getErrorByDate("2020-05-09");
        manage.getErrorByDate("2020-05-10");
        manage.getErrorByDate("2020-05-11");
        manage.getErrorByDate("2020-05-13");

        // stop time execution
        LocalDateTime finish1 = LocalDateTime.now();

        long executionTime1 = ChronoUnit.MILLIS.between(start1, finish1);
        System.out.println("Execution time: : " + executionTime1 + " ms.");


        System.out.println("-----Multi-threading-----");
        //  start time execution
        LocalDateTime start2 = LocalDateTime.now();

        // creating threads
        MyThread thread1 = new MyThread("2020-05-08", path);
        thread1.start();
        MyThread thread2 = new MyThread("2020-05-09", path);
        thread2.start();
        MyThread thread3 = new MyThread("2020-05-10", path);
        thread3.start();
        MyThread thread4 = new MyThread("2020-05-11", path);
        thread4.start();
        MyThread thread5 = new MyThread("2020-05-12", path);
        thread5.start();

        // threads join
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        LocalDateTime finish2 = LocalDateTime.now();//// stop time execution

        long executionTime2 = ChronoUnit.MILLIS.between(start2, finish2);
        System.out.println("execution time: : " + executionTime2 + " ms");

        if (executionTime1 > executionTime2) {
            System.out.println("Multi-Threading calls are faster");
        }
        else if (executionTime1 < executionTime2) {
            System.out.println("consequent calls are faster");
        }

    }


    //static class which extends class Thread
    static class MyThread extends Thread {

        private String date;
        private String filePath;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        MyThread(String date, String filePath){
            this.date = date;
            this.filePath = filePath;
        }

        @Override
        public void run() {
            LogsManage manage = new LogsManage(this.filePath);
            try {
                manage.getErrorByDate(this.date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}