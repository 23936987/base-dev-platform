package com.genetor.ui;

import com.genetor.common.DBModel;
import com.genetor.sence.IScene;
import com.genetor.sence.base.BaseConfig;
import com.genetor.sence.base.BaseScene;
import com.genetor.util.DBTool;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Dio
 * Date: 13-8-25
 * Time: 下午8:56
 * To change this template use File | Settings | File Templates.
 */
public class BaseSenceFrame extends JFrame implements ActionListener {
    private static final String _TITLE_="代码生成器 v0.1";
    private Connection conn = null;
    private JFrame previous;
    private DBModel dbModel;

    JPanel panel = new JPanel();

    JLabel table_name_lab = new JLabel("表名称：");

    JTextField table_name_text = new JTextField();

    JButton genetorBtn = new JButton("生成代码"),back = new JButton("上一步"),
            exitBtn = new JButton("退出");


    JLabel outpath_lab = new JLabel("输出目录：");
    JTextField outpath_text = new JTextField("D:/autoCode/");
    JLabel author_lab = new JLabel("作者：");
    JTextField author_text = new JTextField("hj");
    JLabel version_lab = new JLabel("版本：");
    JTextField version_text = new JTextField("1.0");
    JLabel exclude_lab = new JLabel("公共字段：");
    JTextField exclude_text = new JTextField(",id,");
    JLabel scenceName_lab = new JLabel("场景名称：");
    JTextField scenceName_text = new JTextField("基础场景");
    JLabel prefix_lab = new JLabel("前缀：");
    JTextField prefixName_text = new JTextField("t_");
    JLabel project_lab = new JLabel("项目名称：");
    JTextField project_text = new JTextField("base-dev-platform-base");
    JLabel parent_lab = new JLabel("代码目录：");
    JTextField parent_text = new JTextField("base");

    String[] header = new String[] { "序号", "表名", "备注" };
    JScrollPane scrollPane = new JScrollPane();

    JTable t = new JTable();
    private DefaultTableModel tableModel;
    private String dbSchema;


    public BaseSenceFrame(JFrame previous, Connection conn, DBModel model) {
        super();
        this.conn = conn;
        this.dbModel = model;
        this.previous=previous;
        this.dbSchema = this.dbModel.getDBname();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("基础场景代码生成："));


        panel.add(back);
        panel.add(genetorBtn);
        panel.add(exitBtn);
        panel.add(table_name_lab);
        panel.add(table_name_text);
        panel.add(outpath_lab);
        panel.add(outpath_text);
        panel.add(author_lab);
        panel.add(author_text);
        panel.add(version_lab);
        panel.add(version_text);
        panel.add(exclude_lab);
        panel.add(exclude_text);
        panel.add(project_lab);
        panel.add(project_text);
        panel.add(scenceName_lab);
        panel.add(scenceName_text);
        panel.add(prefix_lab);
        panel.add(prefixName_text);
        panel.add(parent_lab);
        panel.add(parent_text);

        panel.add(scrollPane, BorderLayout.CENTER);

       tableModel=new DefaultTableModel(queryData(),header){
             public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        t.setModel(tableModel);
        //t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //t.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(t);


        table_name_lab.setBounds(20, 30, 100, 30);
        table_name_text.setBounds(120, 30, 200, 30);
        scrollPane.setBounds(10,70,680,200);


        outpath_lab.setBounds(20, 300, 90, 30);
        outpath_text.setBounds(90, 300, 150, 30);
        author_lab.setBounds(245, 300, 50, 30);
        author_text.setBounds(285, 300, 50, 30);

        version_lab.setBounds(340, 300, 50, 30);
        version_text.setBounds(380, 300, 50, 30);
        exclude_lab.setBounds(440, 300, 80, 30);
        exclude_text.setBounds(510, 300, 50, 30);


        project_lab.setBounds(20, 350, 90, 30);
        project_text.setBounds(90, 350, 150, 30);
        scenceName_lab.setBounds(245, 350, 80, 30);
        scenceName_text.setBounds(305, 350, 80, 30);

        prefix_lab.setBounds(390, 350, 80, 30);
        prefixName_text.setBounds(430, 350, 80, 30);

        parent_lab.setBounds(515, 350, 80, 30);
        parent_text.setBounds(580, 350, 80, 30);


        t.setBorder(new LineBorder(new Color(0, 0, 0)));

        exitBtn.setBounds(580, 440, 100, 30);
        genetorBtn.setBounds(470, 440, 100, 30);
        back.setBounds(360, 440, 100, 30);

        table_name_text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String table_name = table_name_text.getText();

                List<Map<String,Object>> list = DBTool.tables(conn,dbSchema,table_name);

                tableModel.setRowCount(0);

                for (int i = 0; i < list.size();i++) {//循环遍历所有行
                    Map<String,Object> row = list.get(i);
                    String[] arr = new String[4];

                    arr[0] = String.valueOf(row.get("rowno"));
                    arr[1] = String.valueOf(row.get("TABLE_NAME"));
                    arr[2] = String.valueOf(row.get("TABLE_COMMENT"));

                    tableModel.insertRow(i,arr);
                }


            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String table_name = table_name_text.getText();

                List<Map<String,Object>> list = DBTool.tables(conn,dbSchema,table_name);

                tableModel.setRowCount(0);

                for (int i = 0; i < list.size();i++) {//循环遍历所有行
                    Map<String,Object> row = list.get(i);
                    String[] arr = new String[4];

                    arr[0] = String.valueOf(row.get("rowno"));
                    arr[1] = String.valueOf(row.get("TABLE_NAME"));
                    arr[2] = String.valueOf(row.get("TABLE_COMMENT"));

                    tableModel.insertRow(i,arr);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {


            }
        });

        genetorBtn.addActionListener(this);
        back.addActionListener(this);
        exitBtn.addActionListener(this);

        add(panel, BorderLayout.CENTER);
    }

    private Object[][] queryData() {
        // 表格中的内容，是一个二维数组

        List<Map<String,Object>> list = DBTool.tables(conn,dbSchema,"");
        Object[][] data = new Object[list.size()][4];

        if (CollectionUtils.isEmpty(list)) {
            return new Object[0][];
        }

        for (int i = 0; i < list.size();i++) {//循环遍历所有行
            Map<String,Object> row = list.get(i);
            String[] arr = new String[4];

            arr[0] = String.valueOf(row.get("rowno"));
            arr[1] = String.valueOf(row.get("TABLE_NAME"));
            arr[2] = String.valueOf(row.get("TABLE_COMMENT"));

            data[i] = arr;
        }

        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == genetorBtn) {
            int[] selectRowNos = t.getSelectedRows();
            if(selectRowNos.length  == 0) {
                JOptionPane.showMessageDialog(null, "请选择数据源表", "提示信息 ", JOptionPane.ERROR_MESSAGE);
            }

            for (int i = 0; i < selectRowNos.length; i++) {
                int selectRowNo = selectRowNos[i];
                String tableName = (String) tableModel.getValueAt(selectRowNo,1);


                try {
                    BaseConfig config = new BaseConfig();
                    config.setScene("base");
                    config.setSchema(dbSchema);
                    config.setTableName(tableName);
                    config.setOutpath(outpath_text.getText());
                    config.setAuthor(author_text.getText());
                    config.setVersion(version_text.getText());
                    config.setExclude(exclude_text.getText());
                    config.setParent(parent_text.getText());
                    config.setProject(project_text.getText());
                    config.setPrefix(prefixName_text.getText());
                    config.setScenceName(scenceName_text.getText());
                    config.setConn(conn);
                    //JOptionPane.showMessageDialog(null, JSON.toJSONString(param), "提示信息 ", JOptionPane.ERROR_MESSAGE);
                    IScene scene = new BaseScene(config);
                    scene.generator();
                    JOptionPane.showMessageDialog(null, "生成代码成功", "提示信息 ", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "生成代码失败", "提示信息 ", JOptionPane.ERROR_MESSAGE);
                }
            }





        }

        if (e.getSource() == back) {
            this.setVisible(false);
            this.previous.setVisible(true);
        }
        if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }

}
