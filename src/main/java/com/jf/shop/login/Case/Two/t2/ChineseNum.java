package com.jf.shop.login.Case.Two.t2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class ChineseNum implements ActionListener {
    public static final String SYMBOL_ADD = "+";
    public static final String SYMBOL_MUL = "*";
    public static final String SYMBOL_DIV = "/";
    public static final String SYMBOL_SUB = "-";
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton sub;
    private JButton add;
    private JButton one;
    private JButton CE;
    private JButton four;
    private JButton div;
    private JButton five;
    private JButton C;
    private JButton two;
    private JButton D;
    private JButton nine;
    private JButton mul;
    private JButton six;
    private JButton three;
    private JButton eight;
    private JButton seven;
    private JButton zero;
    private JButton dot;
    private JButton equ;
    private JButton minus;
    private JTextArea textArea2;

    int count = 0;
    BigDecimal b3 = null;
    BigDecimal b1 = null;
    BigDecimal b2 = null;
    BigDecimal num1 = null;
    BigDecimal num2 = null;
    Double v1 = null;
    Double v2 = null;
    //s是textArea2.getText()中的字符窜,
    String s = null;
    //判断计算输入数字时是输入setText一个还是可以append多个
    boolean falgs = false;
    //判断是否进入运算
    boolean operatorFlags = false;
    boolean canDoOperatior = true;
    //当交叉运算是+/*的数量
    int countNum = 0;
    //判断除数是否为0
    String string = null;
    //判断是否清零
    boolean isClear = false;
    //判断是0. 还是 1.，即判断textArea1中的数字是上次的（则改为0.），还是新点击的（num .）
    boolean isDot = false;
    //判断是否可以D（比如2+3+后，textArea1中是上次2+3 的值，此时就不可以D掉5）
    boolean isD = false;
    private String lastSymbol;


    public ChineseNum() {
        /*one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.append("1");
            }
        });*/
        clearNum();
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        zero.addActionListener(this);
        add.addActionListener(this);
        equ.addActionListener(this);
        dot.addActionListener(this);
        C.addActionListener(this);
        mul.addActionListener(this);
        CE.addActionListener(this);
        D.addActionListener(this);
        div.addActionListener(this);
        sub.addActionListener(this);
        minus.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == one) {
            insertNum("1");
        } else if (e.getSource() == two) {
            insertNum("2");
        } else if (e.getSource() == three) {
            insertNum("3");
        } else if (e.getSource() == four) {
            insertNum("4");
        } else if (e.getSource() == five) {
            insertNum("5");
        } else if (e.getSource() == six) {
            insertNum("6");
        } else if (e.getSource() == seven) {
            insertNum("7");
        } else if (e.getSource() == eight) {
            insertNum("8");
        } else if (e.getSource() == nine) {
            insertNum("9");
        } else if (e.getSource() == zero) {
            if (!(textArea1.getText().equals("0") || textArea1.getText().equals("-0"))) {
//                textArea1.append("0");
                insertNum("0");
                falgs = true;
            } else if (!textArea2.getText().isEmpty()) {
                textArea1.setText("0");
//                insertNum("0");
            }
        } else if (e.getSource() == dot) {
            if (isDot) {
                textArea1.setText("0.");
                falgs = false;
                isClear = false;
            } else {
                if (!textArea1.getText().contains(".")) {
                    textArea1.append(".");
                    falgs = false;
                    isClear = false;
                }
            }
        } else if (e.getSource() == minus) {
            if (textArea1.getText().contains(SYMBOL_SUB)) {
                textArea1.setText(textArea1.getText().substring(1, textArea1.getText().length()));
            } else {
                textArea1.setText(SYMBOL_SUB + textArea1.getText());
            }
            operatorFlags = true;
        } else if (e.getSource() == CE) {
            textArea1.setText("");
            clearNum();
        } else if (e.getSource() == D) {
            if (isD) {
                //textArea1中的字符窜
                String textArea1s;
                /*if (textArea1.getText().length()>1){
                    textArea1s = textArea1.getText().substring(0, textArea1.getText().length()-1);
                }else {
                    textArea1s = "0";
                    falgs = true;
                }
                textArea1.setText(textArea1s);*/
               /* if (textArea1.getText().matches("^\\-.")){
                    textArea1s = "0";
                    falgs = true;
                }*/
                if (textArea1.getText().length() <= 1 || textArea1.getText().matches("^\\-.")) {
                    textArea1s = "0";
                    falgs = true;
                } else {
                    textArea1s = textArea1.getText().substring(0, textArea1.getText().length() - 1);
                }
                textArea1.setText(textArea1s);
            }
        } else if (e.getSource() == C) {
            textArea1.setText("");
            textArea2.setText("");
            clearNum();
            isClear = true;
            b3 = null;
            num1 = null;
            count = 0;
            falgs = false;
            operatorFlags = false;
            countNum = 0;
        } else if (e.getSource() == add) {
            handleSymbol(add);
        } else if (e.getSource() == sub) {
            handleSymbol(sub);
        } else if (e.getSource() == mul) {
            handleSymbol(mul);
        } else if (e.getSource() == div) {
            handleSymbol(div);
        } else if (e.getSource() == equ) {
            if (num1 == null) {
                return;
            }
            num2 = new BigDecimal(Double.parseDouble(textArea1.getText()));
            BigDecimal bigDecimal = doCount(lastSymbol, num1, num2);
            num1 = bigDecimal;
            textArea1.setText(bigDecimal.stripTrailingZeros().toPlainString());
            falgs = true;
//            textArea1.setText(num1.toString());


            num1 = null;
            textArea2.setText("");
            count = 0;
            falgs = true;
            isDot = true;
            operatorFlags = true;
            isD = false;
            countNum = 0;
        }
    }

    private boolean handleSymbol(JButton button) {
        if (!canDoOperatior) {
            return true;
        }
        if (button == add) {
            textArea2.append(textArea1.getText() + SYMBOL_ADD);
        } else if (button == sub) {
            textArea2.append(textArea1.getText() + SYMBOL_SUB);
        } else if (button == mul) {
            textArea2.append(textArea1.getText() + SYMBOL_MUL);
        } else if (button == div) {
            textArea2.append(textArea1.getText() + SYMBOL_DIV);
        }
        falgs = true;
        if (num1 == null) {
            num1 = new BigDecimal(Double.parseDouble(textArea1.getText()));
        } else {
            num2 = new BigDecimal(Double.parseDouble(textArea1.getText()));
            BigDecimal bigDecimal = doCount(lastSymbol, num1, num2);
            num1 = bigDecimal;
            textArea1.setText(bigDecimal.stripTrailingZeros().toPlainString());
        }
        if (button == add) {
            lastSymbol = SYMBOL_ADD;
        } else if (button == sub) {
            lastSymbol = SYMBOL_SUB;
        } else if (button == mul) {
            lastSymbol = SYMBOL_MUL;
        } else if (button == div) {
            lastSymbol = SYMBOL_DIV;
        }
        canDoOperatior = false;
        return false;
    }

    private static BigDecimal doCount(String lastSymbol, BigDecimal num1, BigDecimal num2) {
        BigDecimal result = null;
        if (lastSymbol.equals(SYMBOL_ADD)) {
            result = new BigDecimal(num1.add(num2).toString());
        } else if (lastSymbol.equals(SYMBOL_MUL)) {
            result = new BigDecimal(num1.multiply(num2).toString());
        } else if (lastSymbol.equals(SYMBOL_DIV)) {
            result = new BigDecimal(num1.divide(num2,10, BigDecimal.ROUND_HALF_EVEN).toString());
        } else {
            result = new BigDecimal(num1.subtract(num2).toString());
        }
        return result;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ChineseNum");
        frame.setContentPane(new ChineseNum().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void clearNum() {
        if (textArea1.getText().isEmpty()) {
            textArea1.setText("0");
            falgs = true;
            operatorFlags = true;
        }
    }

    //输入数字
    public void insertNum(String num) {
        if (isClear) {
            textArea1.setText("");
            isClear = false;
        }
        if (falgs) {
            textArea1.setText(num);
            falgs = false;
        } else {
            textArea1.append(num);
        }
        canDoOperatior = true;
        operatorFlags = true;
        isDot = false;
        isD = true;
    }



}
