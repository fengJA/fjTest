package com.jf.shop.login.shopTeminal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GoodsDetail implements ItemListener {
    private JLabel storeName;
    private JLabel goodsName;
    private JLabel number;
    private JLabel prices;
    private JLabel specification;
    private JLabel description;
    private JPanel bb;
    private JPanel buttonPanel;
    static int goodsId;
    //设置边框显示条
    //获取窗体容器

    //定义三个窗体按钮
    JRadioButton jradio1 = new JRadioButton("男");
    JRadioButton jradio2 = new JRadioButton("女");
    JRadioButton jradio3 = new JRadioButton("嗯...");

    public GoodsDetail() {
        JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createTitledBorder("性别："));
        //定义排版样式

        pan.setLayout(new GridLayout(3, 1));
        //定义按钮组
        ButtonGroup group = new ButtonGroup();
        //把单选按钮添加到按钮组中，这样只能选组中的一个按钮，真正实现单选
        group.add(jradio1);
        group.add(jradio2);
        group.add(jradio3);
        pan.add(jradio1);
        pan.add(jradio2);
        pan.add(jradio3);
        jradio1.addItemListener(this) ;
        jradio2.addItemListener(this) ;
        jradio3.addItemListener(this) ;
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.add(pan);
    }

    public static void show(int goodsId) {
        GoodsDetail.goodsId = goodsId;
        main(null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GoodsDetail");
        frame.setContentPane(new GoodsDetail().bb);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==jradio1){
            prices.setText(jradio1.getText());
        } else if (e.getSource() == jradio2) {
            prices.setText(jradio2.getText());
        } else if (e.getSource() == jradio3) {
            prices.setText(jradio3.getText());
        }

    }
}
