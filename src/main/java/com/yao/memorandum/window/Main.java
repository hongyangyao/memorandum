package com.yao.memorandum.window;

import com.yao.memorandum.table.Table;
import com.yao.memorandum.util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 主窗口
 */
public class Main extends JFrame {

    public static String filePath = System.getProperty("user.dir") + "/data.txt";

    public static void main(String[] args) throws IOException {
        System.out.println(filePath);
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setMinimumSize(new Dimension(300, 130));
            main.setSize(600, 200);
            main.setTitle("备忘录");
//            setTitle(main, "备忘录");
            main.setResizable(true);
            //窗口居中显示
            main.setLocationRelativeTo(null);
            //窗口按钮
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setIconImage(IconUtil.getIcon(IconUtil.Icon.APP).getImage());
            JButton add = new JButton("添加备忘");
            add.setFont(new Font("华文楷体", Font.PLAIN, 16));
            add.addActionListener(e -> Table.addRow());
            //添加按钮和表格
            Container pane = main.getContentPane();
            pane.setLayout(new BorderLayout());
            pane.add(add, BorderLayout.SOUTH);
            pane.add(Table.getTableAndData(), BorderLayout.CENTER);
            main.pack();

            //将窗口显示出来
            main.setVisible(true);
        });
    }

    private Main() {
        super();
    }

    private static void setTitle(JFrame frame, String title) {
        frame.setFont(new Font("System", Font.PLAIN, 14));
        Font f = frame.getFont();
        FontMetrics fm = frame.getFontMetrics(f);
        int x = fm.stringWidth(title);
        int y = fm.stringWidth(" ");
        int z = frame.getWidth() / 2 - (x / 2);
        int w = z / y / 2;
        String pad = "";
        pad = String.format("%" + w + "s", pad);
        frame.setTitle(pad + title);
    }
}
