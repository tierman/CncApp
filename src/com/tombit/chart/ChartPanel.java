package com.tombit.chart;

import com.tombit.CncApp;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class ChartPanel extends JPanel implements MouseWheelListener {

    private final CncApp cncApp;
    private double chartZoomFactor = 1;
    private double chartPreviousZoomFactor = 1;
    private boolean zoomer;
    private double xOffset = 0;
    private double yOffset = 0;
    private double chartScale = 1;

    public ChartPanel(CncApp cncApp) {
        this.cncApp = cncApp;
        addMouseWheelListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (zoomer) {
            AffineTransform at = new AffineTransform();

            //double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            //double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            //double zoomDiv = chartZoomFactor / chartPreviousZoomFactor;

            //xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            //yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            //at.translate(xOffset, yOffset);
            at.scale(chartZoomFactor, chartZoomFactor);
            chartPreviousZoomFactor = chartZoomFactor;
            g2d.transform(at);
            zoomer = false;
        }

   /*     Axis
        XYChart c = new LineChart("x", "y");//AreaChart(cncApp.getPointsBase().size(), cncApp.getPointsBase().size());
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
   */
        Integer maximumHeight = 0;
        cncApp.getPointsBase().forEach((integer, aDouble) -> {
            g2d.drawRect(aDouble.intValue(), integer, 1, 1);
        });

        for (int i = 0 ; i< cncApp.getPointsBase().size(); ++i) {
            double aaa = cncApp.getPointsBase().get(i);
            if (aaa.intValue() > maximumHeight) {
                maximumHeight = aaa.intValue();
            }

        }
        this.setPreferredSize(new Dimension(maximumHeight + 50, cncApp.getPointsBase().size()));
        //path.lineTo(900, 1000);
        cncApp.getLog().append("Scale: " + g2d.getTransform().getScaleX() + CncApp.NEWLINE);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.draw(path);
        g2d.dispose();
        chartScale = g2d.getTransform().getScaleX();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomer = true;
        //Zoom in
        if (e.getWheelRotation() < 0) {
            chartZoomFactor *= 1.1;
            Dimension d = new Dimension();
            d.setSize(this.getSize().width * chartScale, this.getSize().height * chartScale);
            this.setPreferredSize(d);
            this.revalidate();
            repaint();
        }
        //Zoom out
        if (e.getWheelRotation() > 0 && Double.compare(chartScale, 1) > 0) {
            chartZoomFactor /= 1.1;
            Dimension d = new Dimension();
            d.setSize(this.getSize().width / chartScale, this.getSize().height / chartScale);
            this.setPreferredSize(d);
            this.revalidate();
            repaint();
        }
    }

    public double getChartZoomFactor() {
        return chartZoomFactor;
    }

    public void setChartZoomFactor(double chartZoomFactor) {
        this.chartZoomFactor = chartZoomFactor;
    }

    public double getChartPreviousZoomFactor() {
        return chartPreviousZoomFactor;
    }

    public void setChartPreviousZoomFactor(double chartPreviousZoomFactor) {
        this.chartPreviousZoomFactor = chartPreviousZoomFactor;
    }

    public boolean isZoomer() {
        return zoomer;
    }

    public void setZoomer(boolean zoomer) {
        this.zoomer = zoomer;
    }

}
