package com.jf.shop.login.shopTeminal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jf.shop.login.shopTeminal.common.CommonApi;
import com.jf.shop.login.shopTeminal.responseEntity.GetUserInfoResponseEntity;
import com.jf.shop.login.shopTeminal.responseEntity.GoToLoginResponseEntity;
import com.jf.shop.login.shopTeminal.responseEntity.PageInfoEntity;
import com.jf.shop.login.shopTeminal.responseEntity.ScanGoodsBySearchReaponseEntity;
import com.jf.shop.login.shopTeminal.utils.HttpRequestUtil;
import com.jf.shop.login.shopTeminal.utils.ResultModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URLEncoder;
import java.util.HashMap;

public class Home implements ActionListener {
    private JTextField textField1;
    private JButton search;
    private JTable table1;
    private JLabel username;
    private JLabel last_login_time;
    private JPanel qw;
    private JButton nextPage;
    private JButton prePage;
    private JLabel pageInfoLabel;
    static GoToLoginResponseEntity goToLoginResponseEntity;
    private static final int PAGE_SIZE = 5;
    private int page = 1;
    private int totaPage = 1;

    public Home() {
        search.addActionListener(this);

        searchGoods(textField1.getText(), page, PAGE_SIZE);

        nextPage.addActionListener(this);
        prePage.addActionListener(this);
        table1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 1) {//点击几次，这里是双击事件1
                    tableChanged();
                }
            }
        });

    }


    public void tableChanged() {
        int row = table1.getSelectedRow();
        String preId = table1.getValueAt(row, 0).toString();
        System.out.println(preId);

    }


    public void searchGoods(String str, int page, int pageSize) {
        // 表头（列名）
        Object[] columnNames = {"编号", "商店编号", "商品名", "更新时间", "创建时间", "商品描述"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table1.setModel(model);

        // 表格所有行数据
        Object[][] rowData = {
                {"张三", 80, 80, 80, 240},
                {"John", 70, 80, 90, 240},
                {"Sue", 70, 70, 70, 210},
                {"Jane", 80, 70, 60, 210},
                {"Joe", 80, 70, 60, 210}
        };


        table1.setModel(model);
        initUserInfo();

        String url = CommonApi.BASE_URL + CommonApi.SCAN_GOODS_URL;
        HashMap<String, String> header = new HashMap<>();
//        header.put("id",null);
        String param = "search=" + URLEncoder.encode(str) + "&" + "page=" + page + "&" + "pageSize=" + pageSize;

        String sr = HttpRequestUtil.sendGet(url, param, header, false);
        System.out.println("在线" + sr);

        ResultModel<ScanGoodsBySearchReaponseEntity> scanGoodsResponseEntityResultModel = JSON.parseObject(sr, new TypeReference<ResultModel<ScanGoodsBySearchReaponseEntity>>() {
        });
        System.out.println(scanGoodsResponseEntityResultModel);

        if (scanGoodsResponseEntityResultModel.getData() == null || scanGoodsResponseEntityResultModel.getData().getSkimGoodsList() == null || scanGoodsResponseEntityResultModel.getData().getSkimGoodsList().size() == 0) {

            return;
        }
        PageInfoEntity pageInfo = scanGoodsResponseEntityResultModel.getData().getPageInfo();
        pageInfoLabel.setText(pageInfo.getPage() + "-" + pageInfo.getTotlePage());
        totaPage = pageInfo.getTotlePage();
        for (int i = 0; i < scanGoodsResponseEntityResultModel.getData().getSkimGoodsList().size(); i++) {
            model.addRow(scanGoodsResponseEntityResultModel.getData().getSkimGoodsList().get(i).toArray());
        }
    }

    private void initUserInfo() {
        String url = CommonApi.BASE_URL + CommonApi.GET_USER_INFO_URL;

        HashMap<String, String> header = new HashMap<>();
        System.out.println("按时" + goToLoginResponseEntity);

        header.put("token", goToLoginResponseEntity.getToken());
        System.out.println("123");
        String sr = HttpRequestUtil.sendGet(url, null, header, false);
        System.out.println("请问" + sr);
        ResultModel<GetUserInfoResponseEntity> getUserInfoResponseEntityResultModel = JSON.parseObject(sr, new TypeReference<ResultModel<GetUserInfoResponseEntity>>() {
        });
        System.out.println(JSONObject.toJSONString(getUserInfoResponseEntityResultModel));
        getUserInfoResponseEntityResultModel.getData().getUserName();
        username.setText(getUserInfoResponseEntityResultModel.getData().getUserName());
        last_login_time.setText(getUserInfoResponseEntityResultModel.getData().getDate().toString());
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(new Home().qw);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


    }

    //从另一个Login中实体类GoToLoginResponseEntity中得到他的token
    public static void show(GoToLoginResponseEntity goToLoginResponseEntity) {
        Home.goToLoginResponseEntity = goToLoginResponseEntity;
        main(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            searchGoods(textField1.getText(), 1, PAGE_SIZE);
        } else if (e.getSource() == prePage) {
//            page = (page-1 < 1 ? 1 : page -1);
            if (page > 1) {
                page = page - 1;
                searchGoods(textField1.getText(), page, PAGE_SIZE);
            }
        } else if (e.getSource() == nextPage) {
            if (page < totaPage) {
                page = page + 1;
                searchGoods(textField1.getText(), page, PAGE_SIZE);
            }
        }

    }
}
