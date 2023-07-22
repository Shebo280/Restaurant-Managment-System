package com.Shebo.swing;

//import com.Shebo.model.StatusType;
//import com.Shebo.swing.TableHeader;
//import java.awt.Color;
//import java.awt.Component;
//import javax.swing.JLabel;
import com.Shebo.model.StatusType;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(o + "");
               return header;
           }
        });
//        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
//                if (column != 4) {
//                   Component com= super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
//                   com.setBackground(Color.WHITE);
//                    setBorder(noFocusBorder);
//                    if (isSelected) {
//                        com.setForeground(new Color(15,89,140));
//                    } else {
//                        com.setForeground(new Color(102,102,102));
//                    }
//                   return com;
//                }
//                StatusType type=(StatusType)o;
//                CellStatus cell = new CellStatus(type);
//                return cell;
//            }
//
//   });
   }
    public void addRow(Object[] row){
        DefaultTableModel model=(DefaultTableModel)getModel();
        model.addRow(row);
    }
}
