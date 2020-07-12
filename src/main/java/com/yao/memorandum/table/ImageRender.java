package com.yao.memorandum.table;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ImageRender extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        if (value instanceof Icon) {
            this.setIcon((Icon) value);
            /*if (isSelected) {
                setBorder(new LineBorder(Color.red));
            }*/
        } else if (value instanceof String) {
            setText((String) value);

        } else {
            setText("");
        }

        return this;
    }
}
