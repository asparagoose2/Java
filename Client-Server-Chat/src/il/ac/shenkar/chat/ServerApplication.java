package il.ac.shenkar.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication
    {

        private static int port = 1234;

        public static void main (String args[]) {

            ServerSocket server = null;

            MessageBoard mb = new MessageBoard();

            try {
                server = new ServerSocket(port, 5);

            } catch(IOException e) {
                e.printStackTrace();
            }

            System.out.println("Server running on port: " + port);

            Socket socket = null;

            ClientDescriptor client = null;

            ConnectionProxy connection = null;

            while(true)
            {
                try {
                    socket = server.accept();
                    System.out.println("New Connection!");
                    connection = new ConnectionProxy(socket);
                    client = new ClientDescriptor();
                    connection.addConsumer(client);
                    connection.setProducer(mb);
                    client.addConsumer(mb);
                    mb.addConsumer(connection);
                    connection.start();

                } catch (IOException e) {

                } catch (ChatException e) {
                    e.printStackTrace();
                }

            }



        }

    }

