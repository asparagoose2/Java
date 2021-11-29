package il.ac.shenkar.chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class ClientGUI implements StringConsumer, StringProducer {

    // Client Data
    private String nickname;
    private static Socket socket;
    private ConnectionProxy cp;
    private static int port = 1234;

    boolean dialogFlag = false;

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
        ClientGUI g = new ClientGUI();

        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            g.connectionErrorDialog();

        }

        try {
//            g = new ClientGUI();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    g.init();
                }
            });
            g.connectionDialog();
            g.connect(new Socket("localhost", port));

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    g.nicknameLabel.setText("Connected as: " + g.nickname);
                }
            });

        } catch (IOException e) {
            throw new ChatException("ClientGUI main()",e);
        }

    }

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMSG();
        }
    }

    public void connectionDialog() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    setNickname(JOptionPane.showInputDialog("Enter Name"));
                    if(nickname == null || (nickname != null && ("".equals(nickname))))
                    {
                        System.exit(1);
                    }
                    dialogFlag = true;

                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void connectionErrorDialog() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(frame,"Server is not available!","Server Error!", JOptionPane.ERROR_MESSAGE);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ClientGUI() throws ChatException {
        this.nickname = "Disconnected";

        sendMessageActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMSG();
            }
        };

    }

    private void sendMSG() {
        if(!inputField.getText().equals("")) {
            cp.consume(nickname + ": " + inputField.getText());
            inputField.setText("");
        }
    }

    public void connect(Socket s) throws ChatException {
        cp = new ConnectionProxy(s);
        cp.addConsumer(this);
        cp.setProducer(this);
        this.addConsumer(cp);
        cp.start();
        cp.consume(nickname + " Connected!");
    }

    private void init() {
        frame = new JFrame();
        frame.setSize(1000,1400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    cp.consume(nickname + " Disconnected!");
                    cp.disconnect();
                } catch (ChatException e) {
                    e.printStackTrace();
                }
                super.windowClosing(windowEvent);
            }
        });
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
        inputField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMSG();
            }
        });
        inputPanel.add(inputField, BorderLayout.WEST);
        inputPanel.add(btnSend, BorderLayout.EAST);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        // Setting the header
        header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(10, 10, 10, 10));
        header.setPreferredSize(new Dimension(1000, 50));
        header.setBackground(Color.orange);
        nicknameLabel = new JLabel(nickname);
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
                frame.validate();
                frame.repaint();
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
