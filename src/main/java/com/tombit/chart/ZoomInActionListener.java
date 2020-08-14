package com.tombit.chart;

import com.tombit.CncApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomInActionListener implements ActionListener {

    private final CncApp cncApp;

    public ZoomInActionListener(CncApp cncApp) {
        this.cncApp = cncApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ChartPanel) cncApp.getChartPanel()).setChartZoomFactor(((ChartPanel) cncApp.getChartPanel()).getChartZoomFactor() * 1.1);
        ((ChartPanel) cncApp.getChartPanel()).setZoomer(true);
        cncApp.getChartPanel().repaint();
    }
}
