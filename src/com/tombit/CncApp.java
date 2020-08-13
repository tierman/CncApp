package com.tombit;

import com.tombit.chart.*;
import com.tombit.io.ReadFileButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CncApp extends JPanel {

    private JButton readFileButton;
    private JPanel mainPanel;
    private JPanel chartPanel;
    private JButton saveFileButton;
    private JTextArea log;
    private JPanel buttonPanel;
    private JScrollPane logScrollPane;
    private JButton zoomINButton;
    private JButton zoomOUTButton;
    private JButton zoomOriginalSize;
    private JScrollPane chartPanelJScrollPane;
    private Map<Integer, String> readedFile = new HashMap<>();
    private Map<Integer, Double> points;
    private Map<Integer, Double> pointsModified;
    private final JFileChooser fc = new JFileChooser();
    public static final String NEWLINE = "\n";

    public CncApp() {
        log.append("Start" + NEWLINE);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        readFileButton.addActionListener(new ReadFileButton(this));
        zoomINButton.addActionListener(new ZoomInActionListener(this));
        zoomOUTButton.addActionListener(new ZoomOutActionListener(this));
        zoomOriginalSize.addActionListener(new OriginalSizeActionListener(this));
        chartPanel.addComponentListener(new ResizeListener(this));
        //chartPanelJScrollPane.setViewportView(chartPanel);
    }

    public static void main(String[] params) {
        JFrame frame = new JFrame("CncApp");
        frame.setContentPane(new CncApp().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    public Map<Integer, String> getReadedFile() {
        return readedFile;
    }

    public JPanel getChartPanel() {
        return this.chartPanel;
    }

    public JTextArea getLog() {
        return log;
    }

    public JFileChooser getFileChooser() {
        return fc;
    }

    private void createUIComponents() {
        chartPanel = new ChartPanel(points);
    }
}