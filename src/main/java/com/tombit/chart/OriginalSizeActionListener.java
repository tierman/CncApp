package com.tombit.chart;

import com.tombit.CncApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OriginalSizeActionListener implements ActionListener {

    private final CncApp cncApp;

    public OriginalSizeActionListener(CncApp cncApp) {
        this.cncApp = cncApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ChartPanel) cncApp.getChartPanel()).setChartZoomFactor(1);
        ((ChartPanel) cncApp.getChartPanel()).setZoomer(true);
        cncApp.getChartPanel().repaint();
    }
}
