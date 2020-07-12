package com.yao.memorandum.util;

import com.yao.memorandum.table.Table;

import javax.swing.*;

public class MouseUtil {

    private static JPopupMenu m_popupMenu;


    //菜单栏
     static  {
        m_popupMenu = new JPopupMenu();

        JMenuItem delMenItem = new JMenuItem();
        delMenItem.setText("删除");
        delMenItem.addActionListener(evt -> {
            //该操作需要做的事
            Table.delRow();
        });
        JMenuItem topMenItem = new JMenuItem();
        topMenItem.setText("置顶");
        topMenItem.addActionListener(evt -> {
            //该操作需要做的事
            Table.top();
        });
        JMenuItem okMenItem = new JMenuItem();
        okMenItem.setText("完成");
        okMenItem.addActionListener(evt -> {
            //该操作需要做的事
            Table.finish();
        });
        m_popupMenu.add(topMenItem);
        m_popupMenu.add(okMenItem);
        m_popupMenu.add(delMenItem);
    }

    //鼠标右键点击事件
    public static void mouseRightButtonClick(java.awt.event.MouseEvent evt,JTable table) {
        //判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            //通过点击位置找到点击为表格中的行
            int focusedRowIndex = table.rowAtPoint(evt.getPoint());
            if (focusedRowIndex == -1) {
                return;
            }
            //将表格所选项设为当前右键点击的行
            table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
            //弹出菜单
            m_popupMenu.show(table, evt.getX(), evt.getY());
        }

    }

}
