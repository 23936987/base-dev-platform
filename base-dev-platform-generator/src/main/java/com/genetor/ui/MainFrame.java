package com.genetor.ui;

import com.genetor.common.DBConnection;
import com.genetor.common.DBModel;
import com.genetor.util.DBTool;
import com.genetor.util.FrameTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Dio
 * Date: 13-8-25
 * Time: 下午8:56
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame implements ActionListener {
    private static final String _TITLE_="代码生成器 v0.1";
    private JFrame next;
    JPanel panel = new JPanel();

    JLabel dbType_lab = new JLabel("数据库类型："),
            dbUrl_lab = new JLabel("数据库URL："),
            dbSchema_lab = new JLabel("数据库名称："),
            userName_lab = new JLabel("用户名："),
            password_lab = new JLabel("密码：");
    JTextField dbUrl_txt = new JTextField();
    JTextField dbSchema_txt = new JTextField();
    JTextField userName_txt = new JTextField();
    JPasswordField password_txt = new JPasswordField();
    JComboBox dbType = new JComboBox();

    JButton nextBtn = new JButton("下一步"),
            exitBtn = new JButton("退出");

    public MainFrame() {
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("数据库连接信息："));
        dbType_lab.setBounds(20, 40, 120, 30);
        dbUrl_lab.setBounds(20, 90, 120, 30);
        dbSchema_lab.setBounds(20, 140, 120, 30);
        userName_lab.setBounds(20, 190, 120, 30);
        password_lab.setBounds(20, 240, 120, 30);
        dbType.setBounds(115, 40, 200, 30);
        dbType.addItem(DBConnection.MYSQL_FLAG);
        //dbType.addItem(DBConnection.ORACLE_FLAG);
        dbUrl_txt.setBounds(115, 90, 200, 30);
        dbUrl_txt.setText("jdbc:mysql://10.207.33.111:3306/my_test1");
        dbSchema_txt.setBounds(115, 140, 200, 30);
        dbSchema_txt.setText("my_test1");
        userName_txt.setBounds(115, 190, 200, 30);
        userName_txt.setText("root");
        password_txt.setBounds(115, 240, 200, 30);
        password_txt.setText("root");

        nextBtn.setBounds(70, 290, 100, 30);
        exitBtn.setBounds(180, 290, 100, 30);


        panel.add(dbSchema_lab);
        panel.add(dbSchema_txt);
        panel.add(dbUrl_lab);
        panel.add(userName_lab);
        panel.add(password_lab);
        panel.add(dbUrl_txt);
        panel.add(userName_txt);
        panel.add(password_txt);
        panel.add(dbType_lab);
        panel.add(dbType);
        panel.add(nextBtn);
        panel.add(exitBtn);

        nextBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {
            String db_url = dbUrl_txt.getText().trim();
            String db_Type = dbType.getSelectedItem().toString();
            String dbSchema = dbSchema_txt.getText().toString();
            String userName = userName_txt.getText().trim();
            String password = password_txt.getText().trim();
            if (!"".equals(userName) && !"".equals(password)) {
                DBModel model = new DBModel();
                StringBuffer sqlUrl = new StringBuffer();
                sqlUrl.append(db_url);
                sqlUrl.append("?characterEncoding=UTF-8");
                model.setDBurl(sqlUrl.toString());
                model.setDBtype(db_Type);
                model.setDBuser(userName);
                model.setDBpwd(password);
                model.setDBname(dbSchema);
                try {
                    Connection conn = DBTool.getDBCon(model);
                    showTableFrame(conn, model);
                    this.setVisible(false);
                } catch (SQLException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    JOptionPane.showMessageDialog(null, "数据库连接异常，错误信息：" + e1, "Genetor提醒您", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, "系统出现异常..." + e1, "Genetor提醒您", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }


            } else if ("".equals(userName)) {
                JOptionPane.showMessageDialog(null, "请输入用户名", "Genetor提醒您", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "用户名密码不允许为空！！", "Genetor提醒您", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == exitBtn) System.exit(0);
    }

    public void showTableFrame(Connection conn, DBModel model) {
        if(this.next == null){
            this.next = new SenceFrame(this,conn, model);
            this.next.setResizable(false);
            this.next.setSize(350, 300);
            FrameTool.setCenter(this.next);
            this.next.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
            this.next.setTitle(_TITLE_);
        }
        this.next.setVisible(true);
    }
}
