package com.ph.connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 */

public class SocketManger {

    /**
     * Socket 池
     */
    public static final List<Socket> SOCKET_POOL = new ArrayList<>();

    /**
     * 池的最大Socket数
     */
    public static final int POOL_SIZE = 5;

    /**
     * 初始化SocketPool
     */
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            Socket socket = new Socket();
            SOCKET_POOL.add(socket);
        }
    }

    /**
     * 从池取出
     *
     * @return
     */
    public static synchronized Socket get() {
        if (SOCKET_POOL.isEmpty()) return null;
        Socket remove = SOCKET_POOL.remove(0);
        return remove;
    }

    /**
     * 返回池
     *
     * @param socket
     */
    public static void turnBack(Socket socket) {
        if (!SOCKET_POOL.contains(socket)) {
            SOCKET_POOL.add(socket);
        }
    }


}
