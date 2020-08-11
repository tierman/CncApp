package com.tombit.chart;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Map;

public class ChartPanel extends JPanel {

    private final Map<Integer, Double> points;

    public ChartPanel(Map<Integer, Double> points) {
        this.points = points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Path2D path = new Path2D.Double();
        path.moveTo(10, 10);
//        while ()
        path.lineTo(200, 800);
        path.lineTo(900, 1000);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.draw(path);
    }


}
