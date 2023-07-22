package com.Shebo.form;

import Loay.Client;
import Loay.ClientQueries;
import Loay.MealQueries;
import Loay.Order;
import Loay.OrderQueries;
import com.Shebo.model.Model_Card;
import com.Shebo.model.StatusType;
import com.Shebo.swing.ScrollBar;
import com.mysql.cj.x.protobuf.MysqlxSql;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;

public class Form_Home2 extends javax.swing.JPanel {

    public Form_Home2(String ID) {
        initComponents();
        ClientQueries query = new ClientQueries();
        int cNum = (query.getAllClients().size());

        MealQueries query2 = new MealQueries();
        int mNum = (query2.getAllMeals().size());
        OrderQueries q=new OrderQueries();
        List<Order>orders=q.selectAllOrders();
        ClientQueries cq=new ClientQueries();
        double total=0;
        for (Order order:orders){
        total+=order.getTotalPrice();
        }
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/shebo/icon/Client.png")), "Total Customers", String.valueOf(cNum), ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/shebo/icon/Meal.png")), "Total Meals", String.valueOf(mNum), ""));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/shebo/icon/Profit.png")), "Total Profit", String.format("$%.2f",total), ""));

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.Shebo.component.Card();
        card2 = new com.Shebo.component.Card();
        card3 = new com.Shebo.component.Card();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(376, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Shebo.component.Card card1;
    private com.Shebo.component.Card card2;
    private com.Shebo.component.Card card3;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables
}
