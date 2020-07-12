package com.yao.memorandum.table;

import com.yao.memorandum.util.DataUtil;
import com.yao.memorandum.util.DateUtil;
import com.yao.memorandum.util.IconUtil;
import com.yao.memorandum.util.MouseUtil;
import com.yao.memorandum.window.Main;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.yao.memorandum.window.Main.filePath;

public class Table extends JTable {

    private static DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //设置可编辑单元格
            return column == 2 || column == 3;
        }
    };
    private static JTable table = new Table(model) {
        @Override
        public Class getColumnClass(int column) {
            return (column == 0) ? Icon.class : Object.class;
        }
    };
    //序号、时间、详情、备注、创建时间、更新时间
    //序号不存储
    //创建时间、更新时间不展示
    private static List<Vector<Object>> tableData;

    private Table(DefaultTableModel model) {
        super(model);
    }

    public static JScrollPane getTableAndData() {
        model.addColumn("");
        model.addColumn("时间");
        model.addColumn("详情");
        model.addColumn("备注");
        //总列数
        model.setColumnCount(7);
        tableData = DataUtil.getTableData(filePath);
        tableData.forEach(model::addRow);
        // table大小设置
        table.setPreferredScrollableViewportSize(new Dimension(600, 300));
        //表头设置
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 22));
        DefaultTableCellRenderer renderer = alignCenter();
        renderer.setBackground(new Color(0, 191, 255));
        table.getTableHeader().setDefaultRenderer(renderer);
        // 行高
        table.setRowHeight(30);
        // 列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(390);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        //居中显示
        table.getColumnModel().getColumn(1).setCellRenderer(alignCenter());
        //隐藏最后两列
        hideColumn(table, 4);
        hideColumn(table, 5);
        hideColumn(table, 6);
        //启用列选择
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        //网格设置(width:垂直 height:水平网格)
        table.setIntercellSpacing(new Dimension(0, 1));
        //表格添加右键点击事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MouseUtil.mouseRightButtonClick(e, table);
            }
        });
        // 添加单元格监听事件
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();//改变的单元格所在的行索引，起始值为0
                Vector<Object> rowData = tableData.get(row);
                rowData.set(5, String.valueOf(System.currentTimeMillis()));
                table.updateUI();
                DataUtil.writLines(Main.filePath, DataUtil.transTableData(tableData));
            }
        });
        return new JScrollPane(table);
    }

    public static DefaultTableModel getDefaultModel() {
        return model;
    }

    public static JTable getTable() {
        return table;
    }

    public static Vector<Object> getSelectRowData() {
        int row = table.getSelectedRow();
        return tableData.get(row);
    }

    public static void addRow() {
        int rowCount = table.getRowCount() + 1;
        Vector<Object> vector = new Vector<>();
//        vector.add(String.valueOf(rowCount + 1));
        vector.add(IconUtil.getIcon(IconUtil.Icon.DEFAULT));
        vector.add(DateUtil.now());
        vector.add("");
        vector.add("");
        vector.add(String.valueOf(System.currentTimeMillis()));
        vector.add(String.valueOf(System.currentTimeMillis()));
        vector.add(0);
        tableData.add(vector);
        ArrayList<Vector<Object>> objects = new ArrayList<>();
        objects.add(vector);
        DataUtil.appendFile(Main.filePath, DataUtil.transTableData(objects));
        getDefaultModel().addRow(vector);
    }

    public static void delRow() {
        int row = table.getSelectedRow();
        tableData.remove(row);
        getDefaultModel().getDataVector().remove(row);
        Table.getTable().updateUI();
        DataUtil.writLines(Main.filePath, DataUtil.transTableData(tableData));
    }

    public static void top() {
        int row = table.getSelectedRow();
        ArrayList<Vector<Object>> newData = new ArrayList<>();
        Vector<Object> rowData = tableData.get(row);
        rowData.set(0, IconUtil.getIcon(IconUtil.Icon.TOP));
        rowData.set(6,1);
        newData.add(rowData);
        tableData.remove(row);
        newData.addAll(tableData);
        model.getDataVector().clear();
        newData.forEach(model.getDataVector()::add);
        tableData = newData;
        table.updateUI();
        DataUtil.writLines(Main.filePath,DataUtil.transTableData(tableData));
    }

    public static void finish() {
        ArrayList<Vector<Object>> newData = new ArrayList<>();
        int row = table.getSelectedRow();
        Vector<Object> rowData = tableData.get(row);
        rowData.set(0, IconUtil.getIcon(IconUtil.Icon.FINISH));
        rowData.set(6,2);
        tableData.remove(row);
        newData.addAll(tableData);
        newData.add(rowData);
        model.getDataVector().clear();
        newData.forEach(model.getDataVector()::add);
        tableData = newData;
        table.updateUI();
        DataUtil.writLines(Main.filePath,DataUtil.transTableData(tableData));
    }

    //居中显示
    public static DefaultTableCellRenderer alignCenter() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        return renderer;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getFillsViewportHeight()) {
            paintEmptyRows(g);
        }
    }

    /**
     * Paints the backgrounds of the implied empty rows when the table model is
     * insufficient to fill all the visible area available to us. We don't
     * involve cell renderers, because we have no data.
     */
    protected void paintEmptyRows(Graphics g) {
        final int rowCount = getRowCount();
        final Rectangle clip = g.getClipBounds();
        if (rowCount * rowHeight < clip.height) {
            for (int i = rowCount; i <= clip.height / rowHeight; ++i) {
                g.setColor(colorForRow(i));
                g.fillRect(clip.x, i * rowHeight, clip.width, rowHeight);
            }
        }
    }

    /**
     * Shades alternate rows in different colors.
     */
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (isCellSelected(row, column) == false) {
            c.setBackground(colorForRow(row));
            c.setForeground(UIManager.getColor("Table.foreground"));
        } else {
            c.setBackground(UIManager.getColor("Table.selectionBackground"));
            c.setForeground(UIManager.getColor("Table.selectionForeground"));
        }
        return c;
    }

    /**
     * Returns the appropriate background color for the given row.
     */
    protected Color colorForRow(int row) {
        return (row % 2 == 0) ? Color.white : new Color(240, 250, 255);
    }

    /**
     * 隐藏表格中的某一列
     *
     * @param table 表格
     * @param index 要隐藏的列 的索引
     */
    protected static void hideColumn(JTable table, int index) {
        TableColumn tc = table.getColumnModel().getColumn(index);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setMinWidth(0);
        tc.setWidth(0);

        table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0);
    }
}
