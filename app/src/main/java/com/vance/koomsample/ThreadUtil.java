package com.vance.koomsample;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import kotlin.text.Charsets;

public class ThreadUtil {

    public static void printThreads(String id) {
        File file = new File("/proc/self/status");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), Charsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Threads")) {
                    Log.i("vance", id + line);
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
