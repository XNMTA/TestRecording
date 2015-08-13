package com.rc.mobileta.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.FileWriter;
import java.io.IOException;

public class SubtitleMaker {
    private int stepIndex = 1;
    private String filePath = "";
    private Long durationStartTime = 0L;
    private Long durationEndTime = 0L;
    private Long movieStartTime = 0L;
    private String durationText = "Preparation...";
    private String durationStartTimeStr = "00:00:00,000";
    private FileWriter fileWriter;

    public SubtitleMaker(String filePath) throws IOException {
        this.filePath = filePath;
        this.durationStartTime = new DateTime().getMillis();
        this.fileWriter = new FileWriter(filePath);
        this.movieStartTime = durationStartTime;
    }
    public static SubtitleMaker initSubtitle(String subtitle) throws IOException {
        return new SubtitleMaker(subtitle);
    }

    public void finishSubtitle() throws IOException {
        // finish the last section
        fileWriter.write(stepIndex + "\n");
        fileWriter.write(durationStartTimeStr);
        fileWriter.write(" --> ");
        fileWriter.write("00:59:59,999\n");
        fileWriter.write(durationText + "\n\n");
        fileWriter.close();
    }

    public void addStepMsgToSubtitle(String stepMsg) throws IOException {
        // write previous step subtitle
        // write index
        fileWriter.write(stepIndex + "\n");
        // write subtitle section start and end time
        Long timeDelta;
        fileWriter.write(durationStartTimeStr);
        fileWriter.write(" --> ");
        long tempTime = new DateTime().getMillis();
        timeDelta = tempTime - movieStartTime;
        durationStartTime = tempTime;
        durationStartTimeStr = new DateTime(timeDelta).withZone(DateTimeZone.UTC).toString("HH:mm:ss,SSS");
        fileWriter.write(durationStartTimeStr + "\n");
        fileWriter.write(durationText + "\n\n");
        durationEndTime = new DateTime().getMillis();
        this.durationText = stepMsg;
        stepIndex++;
    }

    public static void main() throws IOException, InterruptedException {
        SubtitleMaker subtitleMaker = SubtitleMaker.initSubtitle("hello.srt");
        Thread.sleep(3000);
        subtitleMaker.addStepMsgToSubtitle("STEP1");
        Thread.sleep(4000);
        subtitleMaker.addStepMsgToSubtitle("STEP2");
        Thread.sleep(4000);
        subtitleMaker.addStepMsgToSubtitle("STEP3");
        Thread.sleep(4000);
        subtitleMaker.finishSubtitle();
    }
}
