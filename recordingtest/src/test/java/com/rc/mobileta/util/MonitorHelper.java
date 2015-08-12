package com.rc.mobileta.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;

/**
 * Created by shawn.zhang on 2015/8/12.
 */
//todo: refactor to make it readable
public class MonitorHelper {
    public static Thread thread;
    public static Object wait_obj = new Object();
    public static void startRec(String movieName) throws IOException, InterruptedException {
        thread = new Thread() {
            public void run() {
                try {
                    runShell(movieName);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public static void stopRec() throws InterruptedException {
        synchronized (wait_obj) {
            wait_obj.notify();
        }
        thread.join();
    }

    public static void runShell(String movieName) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "E:\\dev-tools\\ffmpeg\\ffmpeg.exe", "-f", "dshow", "-i", "video=Venus USB2.0 Camera:audio=Microphone (High Definition Aud", "-vcodec", "libx264", movieName + ".mkv");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStreamReader ir = new InputStreamReader(process.getInputStream());
        LineNumberReader reader = new LineNumberReader(ir);

        Thread thread = new Thread(){
            public void run(){
                System.out.println("******************* Thread Running *******************");
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(process.getOutputStream());
                    System.out.println("******** before wait ********");
                    synchronized (wait_obj) {
                        wait_obj.wait();
                        System.out.println("******** after wait ********");
                        outputStreamWriter.write("q");
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        String line = "";
        while ((line = reader.readLine ()) != null) {
            System.out.println ("Stdout: " + line);
        }

        thread.join();
        process.waitFor();
        reader.close();
        ir.close();
    }
}
