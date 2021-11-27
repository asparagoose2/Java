package il.ac.shenkar.chat;

import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionProxy extends Thread implements StringConsumer, StringProducer

{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private StringConsumer consumer;
    private StringProducer producer;
    private String message;

    public ConnectionProxy(Socket socket) throws ChatException {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new ChatException("ConnectionProxy constructor",e);
        }

    }

//    public void start() {
//        listen();
//    }

    public void run() {
        Thread alwaysListeningThread = new Thread( new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        dis = new DataInputStream(socket.getInputStream());
                        System.out.println("Reading UTF() " + Thread.currentThread().getName());
                        message = dis.readUTF();
                        synchronized (consumer) {
                            consumer.consume(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        removeConsumer(consumer);
                    }
                }
            }
        });
        alwaysListeningThread.start();
    }

    @Override
    public void consume(String str) {
        try {
            dos.writeUTF(str);
            dos.flush();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        if (sc instanceof ClientDescriptor || sc instanceof ClientGUI) {
            consumer = sc;
        }

    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
