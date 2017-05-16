// Автор: Зайцев Алексей
package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Window extends JFrame implements ActionListener {
    JTextArea jTextArea = new JTextArea();
    JScrollPane jScrollPane = new JScrollPane(jTextArea);
    JTextField jTextField = new JTextField();
    JButton jButton = new JButton("SENT");
    FileOutputStream fos;
    SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);

    Window() {
//      Конфигурируем окно приложения
        setSize(700, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)((dim.getWidth() - getWidth()) / 2);
        int y = (int)((dim.getHeight() - getHeight()) / 2);
        setLocation(x, y);
//        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chat");


        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c =  new GridBagConstraints();

//      jTextArea
        jTextArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 1400;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        add(jScrollPane, c);

//        jTextField
        jTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.ipady = 0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 10, 10, 0);
        add(jTextField, c);

//        jButton
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.insets = new Insets(0, 5, 10, 10);
        add(jButton, c);

//      Обработчики событий
        jButton.addActionListener(this);
        jTextField.addActionListener(this);

        try {
            fos = new FileOutputStream("history.txt");
            fos.write(sdf.format(new Date()).getBytes());
            fos.write(13);
            fos.write(10);
            sdf.applyPattern("HH:mm");
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    fos.close();
                }
                catch (IOException ex) {}
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jTextField.getText().isEmpty()) {
            String s = sdf.format(new Date()) + ": " + jTextField.getText();
            jTextField.setText(null);
            jTextArea.append(s + "\n");
            try {
                fos.write(s.getBytes());
                fos.write(13); // переносим
                fos.write(10); // строку
                fos.flush();
            }
            catch (IOException ex) {}
        }
        jTextField.requestFocusInWindow();
    }
}
