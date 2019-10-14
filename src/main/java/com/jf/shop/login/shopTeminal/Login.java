package com.jf.shop.login.shopTeminal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jf.shop.login.shopTeminal.common.CommonApi;
import com.jf.shop.login.shopTeminal.responseEntity.GoToLoginResponseEntity;
import com.jf.shop.login.shopTeminal.responseEntity.GotoLoginRequestEntity;
import com.jf.shop.login.shopTeminal.utils.HttpRequestUtil;
import com.jf.shop.login.shopTeminal.utils.ResultModel;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Login implements ActionListener{
    private JPanel panel1;
    private JTextField username;
    private JTextField password;
    private JButton registerButton;
    private JButton loginButton;
    private JLabel toast;
    static JFrame frame;
    public Login() {
        toast.setText("  ");
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    public static void main(String[] args) {
         frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        HashMap<String,String> header = new HashMap<>();
        header.put("za","12");
        for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
            System.out.println(stringStringEntry.getKey());
            System.out.println(stringStringEntry.getValue());

        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            if (StringUtils.isEmpty(username.getText())){
                toast.setText("用户名为空");
                return;
            }

            if (StringUtils.isEmpty(password.getText())){
                toast.setText("密码为空");
                return;
            }

            toast.setText("  ");

            String url = CommonApi.BASE_URL + CommonApi.GOTO_LOGIN_URL;
            /*String para = "{\n" +
                    "\t\"username\":\""+username.getText()+"\",\n" +
                    "\t\"password\":\""+password.getText()+"\"\n" +
                    "}";*/
            GotoLoginRequestEntity gotoLoginRequestEntity = new GotoLoginRequestEntity();
            gotoLoginRequestEntity.setUsername(username.getText());
            gotoLoginRequestEntity.setPassword(password.getText());

            String sr= HttpRequestUtil.sendPost(url,JSONObject.toJSONString(gotoLoginRequestEntity),false);
            System.out.println(sr);


            //将json字符串转换为实体类
            ResultModel<GoToLoginResponseEntity> goToLoginResponseEntityResultModel = JSON.parseObject(sr, new TypeReference<ResultModel<GoToLoginResponseEntity>>() {
            });
            System.out.println(JSONObject.toJSONString(goToLoginResponseEntityResultModel));

            if (goToLoginResponseEntityResultModel.getCode()==20000){
                //dispose隐藏一个界面，Home.main()运行下一个界面
                frame.dispose();

                Home.show(goToLoginResponseEntityResultModel.getData());
                System.out.println("1111");
            }else {
                System.out.println("1234");
                System.out.println(goToLoginResponseEntityResultModel.getMessage());
                System.out.println(goToLoginResponseEntityResultModel.getCode());
                toast.setText(goToLoginResponseEntityResultModel.getMessage()+ goToLoginResponseEntityResultModel.getCode());
            }

        } else if (e.getSource() == registerButton)  {
            System.out.println("register");
            Register.main(null);
            System.out.println("after");
        }
    }
}
