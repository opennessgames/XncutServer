/*
 * @Author: xixi_
 * @Date: 2026-06-26 14:35:54
 * @LastEditors: xixi_
 * @LastEditTime: 2026-06-27 13:46:41
 * @FilePath: /Xncut-Server/Src/XncutMain.java
 * Copyright (c) 2017-2026 by xixi_ , All Rights Reserved.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import openGAME.Recorder.RecorderApplication;

/**
 * Main
 */
public class XncutMain {
    public static void main(String[] args) {
        RecorderApplication Application = new RecorderApplication();

        Application.SetClientHandler((Scoket) -> {
            try {
                System.out.println("收到熙柠剪辑的请求:" + Scoket.getRemoteSocketAddress());

                var In = new BufferedReader(new InputStreamReader(Scoket.getInputStream()));
                var Out = new PrintWriter(Scoket.getOutputStream(), true);

                String XncutRequest = In.readLine();
                System.out.println("收到熙柠剪辑的请求" + XncutRequest);

                if ("GET_PUBG_XNCUT_VERSION".equals(XncutRequest)) {
                    Out.println("PubgXncut Version: 1.0.0");
                } else {
                    Out.println("");
                }

                Scoket.close();
            } catch (Exception Err) {
            }
        });

        Application.Exec();
    }
}