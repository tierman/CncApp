package com.tombit.chart;

import com.tombit.CncApp;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListener extends ComponentAdapter {

    private final CncApp cncApp;

    public ResizeListener(CncApp cncApp) {
        this.cncApp = cncApp;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        cncApp.getLog().append("Chart resized." + CncApp.NEWLINE);
    }
}
