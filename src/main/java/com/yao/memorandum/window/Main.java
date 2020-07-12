package com.yao.memorandum.window;

import com.yao.memorandum.table.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 主窗口
 */
public class Main extends JFrame {

    public static String filePath = System.getProperty("user.dir") +"/data.txt";

    public static void main(String[] args) throws IOException {
        System.out.println(filePath);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.setTitle("备忘录");
                main.setSize(600, 200);
                main.setResizable(true);
                //窗口居中显示
                main.setLocationRelativeTo(null);
                //窗口按钮
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JButton add = new JButton("添加");
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Table.addRow();
                    }
                });
                //添加按钮和表格
                Container pane = main.getContentPane();
                pane.setLayout(new BorderLayout());
                pane.add(add, BorderLayout.SOUTH);
                pane.add(Table.getTableAndData(), BorderLayout.CENTER);
                main.pack();

                //将窗口显示出来
                main.setVisible(true);
            }
        });
    }

    private Main() {
        super();
    }
}
