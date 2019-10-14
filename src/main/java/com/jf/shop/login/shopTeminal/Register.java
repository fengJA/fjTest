package com.jf.shop.login.shopTeminal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jf.shop.login.shopTeminal.common.CommonApi;
import com.jf.shop.login.shopTeminal.responseEntity.GotoRegisterEntity;
import com.jf.shop.login.shopTeminal.utils.HttpRequestUtil;
import com.jf.shop.login.shopTeminal.utils.ResultModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    private JTextField username;
    private JTextField password;
    private JTextField password2;
    private JButton register;
    private JPanel as;
    private JLabel show;

    public Register() {
        register.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                doRegister();
            }
        });
    }

    public void doRegister() {
        GotoRegisterEntity gotoRegisterEntity = new GotoRegisterEntity();
        gotoRegisterEntity.setUsername(username.getText());
        gotoRegisterEntity.setPassword(password.getText());
//        gotoRegisterEntity.setPasswordAgain(password2.getText());

        String url = CommonApi.BASE_URL + CommonApi.GOTO_REGISTER;
        String sr= HttpRequestUtil.sendPost(url, JSONObject.toJSONString(gotoRegisterEntity),false);

        ResultModel goToLoginResponseEntityResultModel = JSON.parseObject(sr, new TypeReference<ResultModel>() {
        });
        System.out.println(JSONObject.toJSONString(goToLoginResponseEntityResultModel));
        show.setText(goToLoginResponseEntityResultModel.getMessage());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Register");
        frame.setContentPane(new Register().as);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
