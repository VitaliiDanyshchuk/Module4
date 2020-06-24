package com.company;
/**
 *
 * Date: 24 of June 2020
 * @author VitaliiDanyshchuk
 * @version 1.1
 *
 * Classname LogsManage
 *
 **/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class LogsManage {
    private String filePath;

    public LogsManage(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void getErrorByDate(String date) throws IOException {

        // start time execution
        LocalDateTime start = LocalDateTime.now();

        //  get errors by date
        List<String> errorLinesList = Files.lines(Paths.get(this.getFilePath()))
                .filter(line -> line.contains(date))
                .filter(line -> line.contains("ERROR"))
                .collect(Collectors.toList());

        // count number of logs
        int linesCount = errorLinesList.size();

        // finish time execution
        LocalDateTime finish = LocalDateTime.now();

        // count execution time
        long timeExecuted = ChronoUnit.MILLIS.between(start, finish);

        System.out.println("There are " + linesCount + " ERROR lines." + " on " + date);
        System.out.println("Execution time: " + timeExecuted);


        String stringData = "";
        for (String line : errorLinesList) {
            stringData += line + "\n";
        }

        String optputPath = "C:\\Users\\Vitaliy\\Desktop\\error"
                + date + ".txt";
        Files.write(Paths.get(optputPath), stringData.getBytes());
    }

}