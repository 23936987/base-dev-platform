package com.genetor.ui;

import com.genetor.common.DBModel;
import com.genetor.util.FrameTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Dio
 * Date: 13-8-25
 * Time: 下午8:56
 * To change this template use File | Settings | File Templates.
 */
public class SenceFrame extends JFrame implements ActionListener {
    private static final String _TITLE_="代码生成器 v0.1";
    private Connection conn = null;
    private JFrame previous;
    private JFrame next;
    private DBModel dbModel;
    private String sence;


    JPanel panel = new JPanel();
    JLabel sence_lab = new JLabel("场景名称："),
      sence_desc_area = new JMyLabel();

    JButton nextBtn = new JButton("下一步"), back = new JButton("上一步"),
            exitBtn = new JButton("退出");

    Map<String,String> senceData= new HashMap<String,String>(){{
        put("--请选择--","");
        put("基础","基础代码场景");
        put("CRUD","CRUD代码场景");
    }};


    String[] arr = new String[]{"--请选择--","基础","CRUD"};
    JComboBox<String> senceCb = new JComboBox<String>(arr);


    public SenceFrame(JFrame previous,Connection conn, DBModel model) {
        super();
        this.conn = conn;
        this.dbModel = model;
        this.previous=previous;

        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("请选择场景："));


        panel.add(nextBtn);
        panel.add(back);
        panel.add(exitBtn);
        panel.add(senceCb);

        panel.add(sence_lab);
        panel.add(sence_desc_area);

        exitBtn.setBounds(230, 240, 100, 30);
        nextBtn.setBounds(120, 240, 100, 30);
        back.setBounds(10, 240, 100, 30);

        sence_lab.setBounds(20,10,120,30);
        senceCb.setBounds(115,10,150,30);

        sence_desc_area.setBounds(20,60,300,150);


        senceCb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String selectedValue = senceCb.getSelectedItem().toString();
                String desc = senceData.get(selectedValue);
                sence_desc_area.setText(desc);
            }
        });


        nextBtn.addActionListener(this);
        back.addActionListener(this);
        exitBtn.addActionListener(this);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {
            String selectedValue = senceCb.getSelectedItem().toString();
            if("--请选择--".equalsIgnoreCase(selectedValue)){
                JOptionPane.showMessageDialog(null, "请选择要生成代码的场景", "提示信息 ", JOptionPane.ERROR_MESSAGE);
                return;
            }

            showTableFrame(selectedValue);
        }


        if (e.getSource() == back) {
            this.setVisible(false);
            this.previous.setVisible(true);
        }
        if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }

    private void showTableFrame(String sence) {

        if(this.sence == null || this.next == null || !this.sence.equalsIgnoreCase(sence)){
            this.sence = sence;
            if("基础".equalsIgnoreCase(sence)){
                this.next = new BaseSenceFrame(this,conn, dbModel);
            }
            if(this.next == null) {
                JOptionPane.showMessageDialog(null, "场景未支持,请耐心等待", "提示信息 ", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.setVisible(false);
            this.next.setResizable(false);
            this.next.setSize(700, 500);
            FrameTool.setCenter(this.next);
            this.next.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
            this.next.setTitle(_TITLE_);
        }
        this.next.setVisible(true);
    }
}
