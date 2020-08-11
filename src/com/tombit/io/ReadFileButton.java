package com.tombit.io;

import com.tombit.CncApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadFileButton implements ActionListener {
    private final CncApp cncApp;

    public ReadFileButton(CncApp cncApp) {
        this.cncApp = cncApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = cncApp.getFileChooser().showOpenDialog(cncApp);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fileInput = cncApp.getFileChooser().getSelectedFile();
            cncApp.getLog().append("Opening: " + fileInput.getName() + "." + CncApp.NEWLINE);
            cncApp.getReadedFile().clear();
            cncApp.getReadedFile().putAll(readFile(fileInput));
            //This is where a real application would open the file.
            cncApp.getLog().append("File readed successfully." + CncApp.NEWLINE);

        } else {
            cncApp.getLog().append("Open command cancelled by user." + CncApp.NEWLINE);
        }
    }

    private Map<Integer, String> readFile(File fileInput) {
        Map<Integer, String> result = new HashMap<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileInput));
            String line = reader.readLine();
            Integer counter = 0;
            while(line != null) {
                result.put(counter, line);
                line = reader.readLine();
                ++counter;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
