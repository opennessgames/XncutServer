/*
 * @Author: xixi_
 * @Date: 2026-06-27 08:23:03
 * @LastEditors: xixi_
 * @LastEditTime: 2026-06-27 14:44:26
 * @FilePath: /Xncut-Server/openGAME/Recorder/RecorderApplication.java
 * Copyright (c) 2017-2026 by xixi_ , All Rights Reserved.
 */

package openGAME.Recorder;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class RecorderApplication {

    private Consumer<Socket> M_Handler;

    public RecorderApplication() {
    }

    public void SetClientHandler(Consumer<Socket> Handler) {
        this.M_Handler = Handler;
    }

    /**
     * 执行的入口函数
     **/
    public void Exec() {

        /* 尝试new套接字 */
        try {
            ServerSocket BackServerSocket = new ServerSocket(11451);

            /* Ctrl + C */
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    /* 不要重复关闭 */
                    if (BackServerSocket.isClosed()) {
                        return;
                    }
                    BackServerSocket.close();
                } catch (Exception Err) {
                    /* 释放失败 */
                    System.out.println(Err.getMessage());
                }
            }));

            /* Event Loop */
            while (true) {
                Socket ClientSocket = BackServerSocket.accept();
                new Thread(() -> M_Handler.accept(ClientSocket)).start();
            }
        } catch (Exception Err) {
            /* 服务器启动失败 */
            System.out.println(Err.getMessage());
        }
    }
}
