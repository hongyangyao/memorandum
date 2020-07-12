package com.yao.memorandum.util;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;

public class Alert {

    /**
     * swing弹窗提示,
     *
     * @param fontSize
     * @param msg
     * @return
     */
    public static JFrame alert(int fontSize, String msg) {

        JFrame frame = new JFrame("");// 新建窗体
        Window win = new Window(frame);// 设置圆角
        // JFrame.setDefaultLookAndFeelDecorated(true);//设置为swing默认窗体
        AWTUtilities.setWindowShape(win,
                new RoundRectangle2D.Double(0.0D, 0.0D, win.getWidth(), win.getHeight(), 26.0D, 26.0D));
        Color color = new Color(0, 191, 255, 80);// 蓝色背景，透明度为50的color；透明的取值范围0~255；
        frame.setAlwaysOnTop(true);// 设置窗口置顶
        frame.setLayout(new GridBagLayout());// 设置网格包布局
        frame.setUndecorated(true);// 设置无边框
        frame.setBackground(color);// 设置背景色

        JLabel label = new JLabel(msg);
        label.setForeground(Color.white);
        label.setFont(new Font("宋体", 0, fontSize));
        frame.setSize(120,  30);
        // 长度为字符大小*字符数量，宽度为字体大小+50像素
        frame.add(label);// 添加到窗体
        frame.setLocationRelativeTo(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                timer.restart();
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        return frame;
    }
}
