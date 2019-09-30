package com.genetor.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
public class JMyLabel extends JLabel{

    private Rectangle2D mfRect = new Rectangle2D.Float();
    //颜色
    private Color mfColor = Color.GRAY;

    private float[] dash1 = {5.0f};

    private BasicStroke s = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public JMyLabel(){
        super();
    }

    public JMyLabel(String info) {
        super(info);
    }

    /**
     * 重写paint方法
     */
    public void paint(Graphics g){

        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        //设置边框颜色
        g2d.setColor(mfColor);
        //设置边框范围
        mfRect.setRect(0,0,getWidth()-1,getHeight()-1);
        //设置边框类型
        g2d.setStroke(s);

        g2d.draw(mfRect);

    }
}