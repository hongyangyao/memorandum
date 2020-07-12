package com.yao.memorandum.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonUtil {

    public static void  click(JButton button){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame alert = Alert.alert(20, "添加成功");
            }
        });
    }
}
