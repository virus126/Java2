/**
 * Created by Алексей on 19.04.2017.
 */

package lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthWindow extends JFrame implements ActionListener {
    MyClient myClient;
    JLabel jLabelLogin = new JLabel("Введите логин:");
    JLabel jLabelPassword = new JLabel("Введите пароль:");
    JTextField jTextFieldLogin = new JTextField();
    JTextField jTextFieldPassword = new JTextField();
    JButton jButtonLogin = new JButton("LOGIN");

    AuthWindow(MyClient myClient) {
        // Конфигурируем окно приложения
        setSize(300, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)((dim.getWidth() - getWidth()) / 2);
        int y = (int)((dim.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Authorization");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c =  new GridBagConstraints();

        // jLabelLogin
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.insets = new Insets(0, 10, 5, 10);
        add(jLabelLogin, c);

        //  jTextField Login
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 10, 10);
        add(jTextFieldLogin, c);

        // jLabelPassword
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 10, 5, 10);
        add(jLabelPassword, c);

        //  jTextField Password
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 10, 15, 10);
        add(jTextFieldPassword, c);

        // jButton Login
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0, 10, 5, 10);
        add(jButtonLogin, c);

        // Обработчики событий
        jButtonLogin.addActionListener(this);

        this.myClient = myClient;

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myClient.attemptAuthorization(jTextFieldLogin.getText(), jTextFieldPassword.getText());
    }
}