package com.ph.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 * <p>
 * SocketClient 客户端
 */

public final class SocketClient {

    private final long connectTimeOut;
    private final long readTimeOut;
    private final long writeTimeOut;


    public SocketClient(Builder builder) {
        this.connectTimeOut = builder.connectTimeOut;
        this.readTimeOut = builder.readTimeOut;
        this.writeTimeOut = builder.writeTimeOut;
    }

    public void newWebSocket(OnReceiver receiver) {
        //客户端  3s记录自身运动数据并发送给服务器进行比对校验。

        //服务器计算完毕后返回客户端  当前状态（云计算 计算密集型）

        Socket socket = SocketManger.get();

        try {
            socket.connect(new InetSocketAddress("http://www.baidu.com", 8080));

            /**
             * onOpen
             */
            receiver.onOpen("socket 连接开启");

            InputStream inputStream = socket.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];

            while (true) {
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                byte[] byteArray = baos.toByteArray();
                String result = new String(byteArray);
                receiver.onMessage(result);
            }
        } catch (IOException e) {
            receiver.onFail("IO异常", e);
            e.printStackTrace();
        } finally {
            if (socket == null) {
                try {
                    socket.close();
                    receiver.onClose("Socket关闭");
                } catch (IOException e) {
                    receiver.onFail("IO异常", e);
                    e.printStackTrace();
                }
            }
        }
    }


    public void sendMessage(String msg) throws IOException {
        OutputStream outputStream = SocketManger.get().getOutputStream();
        outputStream.write(msg.getBytes());
    }


    public static class Builder {

        private long connectTimeOut;
        private long readTimeOut;
        private long writeTimeOut;

        public Builder connectTimeOut(long connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
            return this;
        }

        public Builder readTimeOut(long readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        public Builder writeTimeOut(long writeTimeOut) {
            this.writeTimeOut = writeTimeOut;
            return this;
        }

        public SocketClient build() {
            return new SocketClient(this);
        }

    }

}
