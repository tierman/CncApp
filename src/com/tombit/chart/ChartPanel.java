package com.tombit.chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Map;

public class ChartPanel extends JPanel implements MouseWheelListener {

    private final Map<Integer, Double> points;
    private double chartZoomFactor = 1;
    private double chartPreviousZoomFactor = 1;
    private boolean zoomer;
    private double xOffset = 0;
    private double yOffset = 0;

    public ChartPanel(Map<Integer, Double> points) {
        this.setPreferredSize(new Dimension(2000, 1000));
        this.points = points;
        addMouseWheelListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = chartZoomFactor / chartPreviousZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(chartZoomFactor, chartZoomFactor);
            chartPreviousZoomFactor = chartZoomFactor;
            g2d.transform(at);
            zoomer = false;
        }

        Path2D path = new Path2D.Double();
        path.moveTo(10, 10);
//        while ()
        path.lineTo(200, 800);
        path.lineTo(900, 1000);
        g2d.getTransform().getScaleX();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.draw(path);
        g2d.dispose();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomer = true;
        //Zoom in
        if (e.getWheelRotation() < 0) {
            chartZoomFactor *= 1.1;
            Dimension d = new Dimension();
            d.setSize(this.getSize().width * 1.1, this.getSize().height * 1.1);
            this.setPreferredSize(d);
            this.revalidate();

            repaint();
            this.updateUI();
        }
        //Zoom out
        if (e.getWheelRotation() > 0) {
            chartZoomFactor /= 1.1;
            Dimension d = new Dimension();
            d.setSize(this.getSize().width / 1.1, this.getSize().height / 1.1);
            this.setPreferredSize(d);
            this.revalidate();
            repaint();
            this.updateUI();
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
