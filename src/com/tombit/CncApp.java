package com.tombit;

import com.tombit.chart.ChartPanel;
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
    private Map<Integer, String> readedFile = new HashMap<>();
    private Map<Integer, Double> points;
    private Map<Integer, Double> pointsModified;
    private final JFileChooser fc = new JFileChooser();
    public static final String NEWLINE = "\n";

    public CncApp() {
        log.append("Start" + NEWLINE);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        readFileButton.addActionListener(new ReadFileButton(this));

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