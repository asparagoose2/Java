package il.ac.shenkar.chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ClientGUI implements StringConsumer, StringProducer {

    // Client Data
    private String nickname;
    private Socket socket;
    private ConnectionProxy cp;
    private static int port = 1234;

    // UI components
    private JFrame frame;
    private JTextField inputField;
    private JButton btnSend;
    private JPanel inputPanel;
    private JPanel messagesPanel;
    private  JScrollPane messagesScroller;
    private JPanel header;
    private JLabel nicknameLabel;
    private JLabel applicationTitle;
    private Font font = new Font("ariel", Font.PLAIN, 22);

    // Actions
    private ActionListener sendMessageActionListener;

    public static void main(String[] args) throws ChatException {
        ClientGUI g;
        try {
            g = new ClientGUI(new Socket("localhost", port));
            g.setNickname(args[0]);
            System.out.println("Client Conected!");
        } catch (IOException e) {
            throw new ChatException("ClientGUI main()",e);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                g.init();
            }
        });
        g.consume("bla bla");
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ClientGUI(Socket s) throws ChatException {
        this.nickname = "default";
        cp = new ConnectionProxy(s);
        cp.addConsumer(this);
        this.addConsumer(cp);
        cp.start();
        System.out.println("Client!");


        sendMessageActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                consume(nickname + ": " + inputField.getText());
                inputField.setText("");
            }
        };

    }

    private void init() {
        frame = new JFrame();
        frame.setSize(1000,1400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Setting the message list part
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messagesScroller = new JScrollPane(messagesPanel);
        messagesScroller.setPreferredSize(new Dimension(950,1250));
        messagesScroller.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Setting the input part
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        btnSend = new JButton("SEND");
        btnSend.setPreferredSize(new Dimension(100,50));
        btnSend.setFont(font);
        btnSend.addActionListener(sendMessageActionListener);
        inputField = new JTextField();
        inputField.setFont(font);
        inputField.setPreferredSize(new Dimension(850,50));
        inputPanel.add(inputField, BorderLayout.WEST);
        inputPanel.add(btnSend, BorderLayout.EAST);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        // Setting the header
        header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(10, 10, 10, 10));
        header.setPreferredSize(new Dimension(1000, 50));
        header.setBackground(Color.orange);
        nicknameLabel = new JLabel("Logged in as: " + nickname);
        nicknameLabel.setFont(font);
        applicationTitle = new JLabel("Chat Application");
        applicationTitle.setFont(font);
        header.add(applicationTitle, BorderLayout.WEST);
        header.add(nicknameLabel, BorderLayout.EAST);



        // Bringing all together
        frame.add(messagesScroller, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(header, BorderLayout.NORTH);
        frame.setVisible(true);
        for(int i = 0; i < 100; i ++)
        {
            JLabel t = new JLabel("test");
            t.setFont(new Font("ariel", Font.PLAIN, 22));
            messagesPanel.add(t);

        }


    }

    void sendMSG(String s) {
        cp.consume(s);
    }

    @Override
    public void consume(String str) {
        System.out.println(str);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JLabel newMSG = new JLabel(str);
                newMSG.setFont(font);
                messagesPanel.add(newMSG);
                messagesScroller.getVerticalScrollBar().setValue( messagesScroller.getVerticalScrollBar().getMaximum());
            }
        });

    }

    @Override
    public void addConsumer(StringConsumer sc) {

    }

    @Override
    public void removeConsumer(StringConsumer sc) {

    }
}
