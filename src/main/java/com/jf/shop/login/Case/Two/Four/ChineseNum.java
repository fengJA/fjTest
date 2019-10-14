package com.jf.shop.login.Case.Two.Four;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class ChineseNum implements ActionListener{
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
    Double v1 = null;
    Double v2 = null;
    //s是textArea2.getText()中的字符窜,
    String s = null;
    //判断计算输入数字时是输入setText一个还是可以append多个
    boolean falgs = false;
    //判断是否进入运算
    boolean operatorFlags = false;
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
        if (e.getSource() == one){
            insertNum("1");
        }else if (e.getSource() == two){
            insertNum("2");
        }else if (e.getSource() == three){
            insertNum("3");
        }else if (e.getSource() == four){
            insertNum("4");
        }else if (e.getSource() == five){
            insertNum("5");
        }else if (e.getSource() == six){
            insertNum("6");
        }else if (e.getSource() == seven){
            insertNum("7");
        }else if (e.getSource() == eight){
            insertNum("8");
        }else if (e.getSource() == nine){
            insertNum("9");
        }else if (e.getSource() == zero){
            if (!(textArea1.getText().equals("0") || textArea1.getText().equals("-0"))){
//                textArea1.append("0");
                insertNum("0");
                falgs = true;
            }else if (!textArea2.getText().isEmpty()){
                textArea1.setText("0");
//                insertNum("0");
            }
        }else if (e.getSource() == dot){
            if (isDot){
                textArea1.setText("0.");
                falgs = false;
                isClear = false;
            } else {
                if (!textArea1.getText().contains(".")){
                    textArea1.append(".");
                    falgs = false;
                    isClear = false;
                }
            }
        }else if (e.getSource() == minus){
            if (textArea1.getText().contains("-")){
                textArea1.setText(textArea1.getText().substring(1, textArea1.getText().length()));
            }else {
                textArea1.setText("-" +textArea1.getText());
            }
            operatorFlags = true;
        }else if (e.getSource() == CE){
            textArea1.setText("");
            clearNum();
        }else if (e.getSource() == D){
            if (isD){
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
               if (textArea1.getText().length()<= 1 || textArea1.getText().matches("^\\-.")){
                   textArea1s = "0";
                   falgs = true;
                }else {
                   textArea1s = textArea1.getText().substring(0, textArea1.getText().length()-1);
                }
                textArea1.setText(textArea1s);
            }
        }else if (e.getSource() == C){
            textArea1.setText("");
            textArea2.setText("");
            clearNum();
            isClear = true;
            b3 = null;
            count = 0;
            falgs = false;
            operatorFlags = false;
            countNum = 0;
        } else if (e.getSource() == add){
            if (operatorFlags) {
                if (count>1){
                    selectOperater(textArea2.getText());
                    addNum();
                    ++count;
                }else {
                    countNum('*');
                    countNum('/');
                    countNum('-');
                    if (textArea2.getText().endsWith("*")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        mulTest();
                        count = 0;
                        addNum();
                        ++count;
                    }else  if (textArea2.getText().endsWith("/")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        divTest();
                        count = 0;
                        addNum();
                        ++count;
                    }else  if (textArea2.getText().endsWith("-")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        subTest();
                        count = 0;
                        addNum();
                        ++count;
                    } else {
                        addNum();
                    }
                }

            }
        }else if (e.getSource() == sub){
            if (operatorFlags) {
                if (count>1){
                    selectOperater(textArea2.getText());
                    subNum();
                    ++count;
                }else {
                    countNum('*');
                    countNum('/');
                    countNum('+');
                    if (textArea2.getText().endsWith("*")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        mulTest();
                        count = 0;
                        subNum();
                        ++count;
                    }else  if (textArea2.getText().endsWith("/")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        divTest();
                        count = 0;
                        subNum();
                        ++count;
                    } else if (textArea2.getText().endsWith("+") && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        addTest();
                        count = 0;
                        subNum();
                        ++count;
                    }else {
                        subNum();
                    }
                }
            }
        }else if (e.getSource() == mul){
            if (operatorFlags) {
                if (count>1){
                    selectOperater(textArea2.getText());

                    mulNum();
                    ++count;
                }else {
                    countNum('+');
                    countNum('/');
                    countNum('-');
                    if (textArea2.getText().endsWith("+") && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        addTest();
                        count = 0;
                        mulNum();
                        ++count;
                    }else  if (textArea2.getText().endsWith("/")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        divTest();
                        count = 0;
                        mulNum();
                        ++count;
                    }else  if (textArea2.getText().endsWith("-")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        subTest();
                        count = 0;
                        mulNum();
                        ++count;
                    }  else {
                        mulNum();
                    }
                }
            }
        }else if (e.getSource() == div){
            if (operatorFlags) {
                if (count>1){
                    selectOperater(textArea2.getText());
                    divNum();
                    ++count;
                }else {
                    countNum('+');
                    countNum('*');
                    countNum('-');
                    if (textArea2.getText().endsWith("+") && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        addTest();
                        count = 0;
                        divNum();
                        ++count;
                    }else if (textArea2.getText().endsWith("*")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        mulTest();
                        count = 0;
                        divNum();
                        ++count;
                    }else  if (textArea2.getText().endsWith("-")  && countNum == 1){
                        textArea2.setText(textArea2.getText()+textArea1.getText());
                        subTest();
                        count = 0;
                        divNum();
                        ++count;
                    } else {
                        divNum();
                    }
                }
            }
        }else if (e.getSource() == equ){
            s = textArea2.getText();
            System.out.println(s);
            if (s.endsWith("+")){
                String stAdd = textArea2.getText();
                String[] strAdd = stAdd.split("\\+");
                String st1Add = textArea1.getText();

                v2 = Double.parseDouble(st1Add);
                if (count>1){
                    v1 = Double.parseDouble(b3.toString());
                }else {
                    v1 = Double.parseDouble(strAdd[0]);
                }

                if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")){
                    String s1= String.format("%.0f", v1);
                    String s2= String.format("%.0f", v2);
                    System.out.println("v1和v2:"+s1+","+s2);

                     b1 = new BigDecimal(s1);
                     b2 = new BigDecimal(s2);
                    textArea1.setText(b1.add(b2).toString());
                }else {
                     b1 = BigDecimal.valueOf(v1);
                     b2 = BigDecimal.valueOf(v2);
                    textArea1.setText(b1.add(b2).toString());
                }
            } else if (s.endsWith("*")) {
                String stMul = textArea2.getText();
                String[] strMul = stMul.split("\\*");
                String st1Mul = textArea1.getText();

                v2 = Double.parseDouble(st1Mul);
                if (count>1){
                    v1 = Double.parseDouble(b3.toString());
                }else {
                    v1 = Double.parseDouble(strMul[0]);
                }
                if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")) {
                    String s1= String.format("%.0f", v1);
                    String s2= String.format("%.0f", v2);
                    System.out.println("v1和v2:" + s1 + "," + s2);

                    b1 = new BigDecimal(s1);
                    b2 = new BigDecimal(s2);
                    textArea1.setText(b1.multiply(b2).toString());
                } else {
                    b1 = BigDecimal.valueOf(v1);
                    b2 = BigDecimal.valueOf(v2);
                    textArea1.setText(b1.multiply(b2).toString());
                }
            } else if (s.endsWith("/")) {
                String stMul = textArea2.getText();
                String[] strMul = stMul.split("\\/");
                String st1Mul = textArea1.getText();

                v2 = Double.parseDouble(st1Mul);
                if (count>1){
                    v1 = Double.parseDouble(b3.toString());
                }else {
                    v1 = Double.parseDouble(strMul[0]);
                }
                if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")) {
                    String s1= String.format("%.0f", v1);
                    String s2= String.format("%.0f", v2);
                    System.out.println("v1和v2:" + s1 + "," + s2);

                    b1 = new BigDecimal(s1);
                    b2 = new BigDecimal(s2);
                    try {
                        if (b2.intValue()==0){
                            string = "除数不能为0";
                            throw new Exception("除数不能为0");
                        }else {
                            //保留8位小数,stripTrailingZeros().toPlainString():是BigDecimal的方法，去掉小数点后面无用的零
                            string = b1.divide(b2, 8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
                        }
                    } catch (Exception ex) {

                    }
                    textArea1.setText(string);
                } else {
                    b1 = BigDecimal.valueOf(v1);
                    b2 = BigDecimal.valueOf(v2);
                    try {
                        if (b2.intValue()==0){
                            string = "除数不能为0";
                            throw new Exception("除数不能为0");
                        }else {
                            string = b1.divide(b2, 8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
                        }
                    } catch (Exception ex) {

                    }
                    textArea1.setText(string);
                }
            } else if (s.endsWith("-")) {
                String stMul = textArea2.getText();
                String[] strMul = stMul.split("\\-");
                String st1Mul = textArea1.getText();

                v2 = Double.parseDouble(st1Mul);
                if (count>1){
                    v1 = Double.parseDouble(b3.toString());
                }else {
                    v1 = Double.parseDouble(strMul[0]);
                }
                if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")) {
                    String s1= String.format("%.0f", v1);
                    String s2= String.format("%.0f", v2);
                    System.out.println("v1和v2:" + s1 + "," + s2);

                    b1 = new BigDecimal(s1);
                    b2 = new BigDecimal(s2);
                    textArea1.setText(b1.subtract(b2).toString());
                } else {
                    b1 = BigDecimal.valueOf(v1);
                    b2 = BigDecimal.valueOf(v2);
                    textArea1.setText(b1.subtract(b2).toString());
                }
            }else if (textArea1.getText().endsWith(".")){
                textArea1.setText(textArea1.getText().substring(0, textArea1.getText().length()-1));
            } else {
                textArea1.setText(textArea1.getText());
            }

            textArea2.setText("");
            count = 0;
            falgs = true;
            isDot = true;
            operatorFlags = true;
            isD = false;
            countNum = 0;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ChineseNum");
        frame.setContentPane(new ChineseNum().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void clearNum(){
        if (textArea1.getText().isEmpty()){
            textArea1.setText("0");
            falgs = true;
            operatorFlags = true;
        }
    }

    //输入数字
    public void insertNum(String num){
        if(isClear){
            textArea1.setText("");
            isClear = false;
        }
        if (falgs ){
            textArea1.setText(num);
            falgs = false;
        }else {
            textArea1.append(num);
        }
        operatorFlags = true;
        isDot = false;
        isD = true;
    }

    //计算textArea2中有多少的运算符（+ 、*）
    public void countNum(char operate){
        String text = textArea2.getText();
        if (text.endsWith(operate + "")) {
            String text1 = textArea2.getText();
            char[] chars = text1.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == operate) {
                    countNum++;
                }
            }
        }
    }

    public void mulNum(){
        if (!operatorFlags){
            String s = textArea2.getText();
            System.out.println(s);
            textArea2.setText(s.substring(0, s.length()));
            textArea2.append("*");

        }else {
            textArea2.setText(textArea2.getText()+textArea1.getText());
            textArea2.append("*");
        }
            ++count;

            falgs = true;
            if (count>1){
                mulCal();
            }
            operatorFlags = false;
            isDot = true;
        isD = false;
    }

    public void mulTest(){
        mulCal();
        operatorFlags = false;
    }

    public void mulCal(){
        s = textArea2.getText();
        if (s.contains("*")) {
            String st = textArea2.getText();
            String[] str = st.split("\\*");
            String st1 = textArea1.getText();

            v2 = Double.parseDouble(st1);
            if (count>2){
                v1 = Double.parseDouble(b3.toString());
            }else {
                v1 = Double.parseDouble(str[0]);
            }
            if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")) {
                //NumberFormat nf = new DecimalFormat("0");
                String s1= String.format("%.0f", v1);
                String s2= String.format("%.0f", v2);
                System.out.println("v1和v2:" + s1 + "," + s2);

                b1 = new BigDecimal(s1);
                b2 = new BigDecimal(s2);
                b3 = new BigDecimal(b1.multiply(b2).toString());
                textArea1.setText(b1.multiply(b2).toString());
            } else {
                b1 = BigDecimal.valueOf(v1);
                b2 = BigDecimal.valueOf(v2);
                b3 = new BigDecimal(b1.multiply(b2).toString());
                textArea1.setText(b1.multiply(b2).toString());
            }
        }
    }
    public void addNum(){
        if (!operatorFlags){
            String s = textArea2.getText();
            System.out.println(s);
            textArea2.setText(s.substring(0, s.length()));
            textArea2.append("+");
        }else {
            textArea2.setText(textArea2.getText()+textArea1.getText());
            textArea2.append("+");
        }

        ++count;

        falgs = true;
        if (count>1){
            addCal();
        }
        operatorFlags = false;
        isDot = true;
        isD = false;
    }

    public void addTest(){
        addCal();
        operatorFlags = false;
    }

    public void addCal(){
        s = textArea2.getText();
        System.out.println(s);
        if (s.contains("+")) {
            String st = textArea2.getText();
            String[] str = st.split("\\+");
            String st1 = textArea1.getText();

            v2 = Double.parseDouble(st1);
            if (count>2){
                v1 = Double.parseDouble(b3.toString());
            }else {
                v1 = Double.parseDouble(str[0]);
            }
            if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")) {
                //NumberFormat nf = new DecimalFormat("0");
                String s1= String.format("%.0f", v1);
                String s2= String.format("%.0f", v2);
                System.out.println("v1和v2:" + s1 + "," + s2);

                b1 = new BigDecimal(s1);
                b2 = new BigDecimal(s2);
                b3 = new BigDecimal(b1.add(b2).toString());
                textArea1.setText(b1.add(b2).toString());
            } else {
                b1 = BigDecimal.valueOf(v1);
                b2 = BigDecimal.valueOf(v2);
                b3 = new BigDecimal(b1.add(b2).toString());
                textArea1.setText(b1.add(b2).toString());
            }
        }
    }

    public void subNum(){
        if (!operatorFlags){
            String s = textArea2.getText();
            System.out.println(s);
            textArea2.setText(s.substring(0, s.length()));
            textArea2.append("-");
        }else {
            textArea2.setText(textArea2.getText()+textArea1.getText());
            textArea2.append("-");
        }

        ++count;

        falgs = true;
        if (count>1){
            subCal();
        }
        operatorFlags = false;
        isDot = true;
        isD = false;
    }

    public void subTest(){
        subCal();
        operatorFlags = false;
    }

    public void subCal(){
        s = textArea2.getText();
        System.out.println(s);
        if (s.contains("-")) {
            String st = textArea2.getText();
            String[] str = st.split("\\-");
            String st1 = textArea1.getText();

            v2 = Double.parseDouble(st1);
            if (count>2){
                v1 = Double.parseDouble(b3.toString());
            }else {
                v1 = Double.parseDouble(str[0]);
            }
            if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")) {
                //NumberFormat nf = new DecimalFormat("0");
                String s1= String.format("%.0f", v1);
                String s2= String.format("%.0f", v2);
                System.out.println("v1和v2:" + s1 + "," + s2);

                b1 = new BigDecimal(s1);
                b2 = new BigDecimal(s2);
                b3 = new BigDecimal(b1.subtract(b2).toString());
                textArea1.setText(b1.subtract(b2).toString());
            } else {
                b1 = BigDecimal.valueOf(v1);
                b2 = BigDecimal.valueOf(v2);
                b3 = new BigDecimal(b1.subtract(b2).toString());
                textArea1.setText(b1.subtract(b2).toString());
            }
        }
    }

    public void divNum(){
        if (!operatorFlags){
            String s = textArea2.getText();
            System.out.println(s);
            textArea2.setText(s.substring(0, s.length()));
            textArea2.append("/");
        }else {
            textArea2.setText(textArea2.getText()+textArea1.getText());
            textArea2.append("/");
        }

        ++count;

        falgs = true;
        if (count>1){
            divCal();
        }
        operatorFlags = false;
        isDot = true;
        isD = false;
    }

    public void divTest(){
        divCal();
        operatorFlags = false;
    }

    public void divCal(){
        s = textArea2.getText();
        System.out.println(s);
        if (s.contains("/")) {
            String st = textArea2.getText();
            String[] str = st.split("\\/");
            String st1 = textArea1.getText();

            v2 = Double.parseDouble(st1);
            if (count>2){
                v1 = Double.parseDouble(b3.toString());
            }else {
                v1 = Double.parseDouble(str[0]);
            }
            if (v1.toString().endsWith(".0") && v2.toString().endsWith(".0")) {
                String s1= String.format("%.0f", v1);
                String s2= String.format("%.0f", v2);
                System.out.println("v1和v2:" + s1 + "," + s2);

                b1 = new BigDecimal(s1);
                b2 = new BigDecimal(s2);

                try {
                    if (b2.intValue()==0){
                        string = "除数不能为0";
                        throw new Exception("除数不能为0");
                    }else {
//                        string = b1.divide(b2).toString();
                        string = b1.divide(b2, 8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
                        b3 = new BigDecimal(string);
                    }
                } catch (Exception ex) {

                }
//                b3 = new BigDecimal(string);
                textArea1.setText(string);
            } else {
                b1 = BigDecimal.valueOf(v1);
                b2 = BigDecimal.valueOf(v2);
                try {
                    if (b2.intValue()==0){
                        string = "除数不能为0";
                        throw new Exception("除数不能为0");
                    }else {
//                        string = b1.divide(b2).toString();
                        string = b1.divide(b2, 8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
                        b3 = new BigDecimal(string);
                    }
                } catch (Exception ex) {

                }
//                b3 = new BigDecimal(string);
                textArea1.setText(string);
            }
        }
    }

    public void selectOperater(String text){
        if (text.endsWith("+")) {
            countNum('+');
            if (countNum < 1){
                b3 = new BigDecimal(b1.add(b2).toString());
            }
            ++count;
            textArea2.setText(textArea2.getText()+textArea1.getText());
            addCal();
            count = 0;
            operatorFlags = false;
        }else if (text.endsWith("-")){
            countNum('-');
            if (countNum < 1){
                b3 = new BigDecimal(b1.multiply(b2).toString());
            }
            ++count;
            textArea2.setText(textArea2.getText()+textArea1.getText());
            subCal();
            count = 0;
            operatorFlags = false;
        }else if (text.endsWith("/")){
            countNum('/');
            if (countNum < 1){
                b3 = new BigDecimal(b1.divide(b2).toString());
            }
            ++count;
            textArea2.setText(textArea2.getText()+textArea1.getText());
            divCal();
            count = 0;
            operatorFlags = false;
        }else {
            if (text.endsWith("*")){
                countNum('*');
                if (countNum < 1){
                    b3 = new BigDecimal(b1.multiply(b2).toString());
                }
                ++count;
                textArea2.setText(textArea2.getText()+textArea1.getText());
                mulCal();
                count = 0;
                operatorFlags = false;
            }
        }
    }
}
