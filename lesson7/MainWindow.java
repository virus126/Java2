/**
 * Created by Алексей on 19.04.2017.
 */

package lesson7;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;

class MainWindow extends JFrame implements ActionListener {
    MyClient myClient;
    JTextArea jTextArea = new JTextArea();
    JScrollPane jScrollPane = new JScrollPane(jTextArea);
    JTextField jTextField = new JTextField();
    JButton jButton = new JButton("SENT");
    SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);

    MainWindow(MyClient myClient) {
        // Конфигурируем окно приложения
        setSize(850, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)((dim.getWidth() - getWidth()) / 2);
        int y = (int)((dim.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chat");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c =  new GridBagConstraints();

        // jTextArea
        jTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret)jTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 1400;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        add(jScrollPane, c);

        // jTextField
        jTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.ipady = 0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 10, 10, 0);
        add(jTextField, c);

        // jButton
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.insets = new Insets(0, 5, 10, 10);
        add(jButton, c);

        // Обработчики событий
        jButton.addActionListener(this);
        jTextField.addActionListener(this);

        this.myClient = myClient;

        jTextField.requestFocusInWindow();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jTextField.getText().isEmpty()) {
            myClient.sendMsg(jTextField.getText());
            jTextField.setText("");
        }
        jTextField.requestFocusInWindow();
    }
}