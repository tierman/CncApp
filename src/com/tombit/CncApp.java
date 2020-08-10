package com.tombit;

import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CncApp extends JPanel {

    private JButton readFileButton;
    private JPanel mainPanel;
    private JPanel chartPanel;
    private JButton saveFileButton;
    private JTextArea log;
    private JPanel buttonPanel;
    private JScrollPane logScrollPane;
    private final JFileChooser fc = new JFileChooser();
    static private final String newline = "\n";

    public CncApp() {
        log.append("Start" + newline);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        readFileButton.addActionListener(e -> {
            FileOpenService fos = null;
            FileContents fileContents = null;

            try {
                fos = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
            } catch (UnavailableServiceException exc) {
            }

            if (fos != null) {
                try {
                    fileContents = fos.openFileDialog(null, null);
                } catch (Exception exc) {
                    log.append("Open command failed: "
                            + exc.getLocalizedMessage()
                            + newline);
                    log.setCaretPosition(log.getDocument().getLength());
                }
            }

            if (e.getSource() == readFileButton) {
                int returnVal = fc.showOpenDialog(CncApp.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    log.append("Opening: " + file.getName() + "." + newline);
                } else {
                    log.append("Open command cancelled by user." + newline);
                }
            }
        });
        //buttonPanel.add(readFileButton);
        //buttonPanel.add(saveFileButton);
        //mainPanel.add(logScrollPane);
        //mainPanel.add(buttonPanel);
        //mainPanel.add(chartPanel);
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
}