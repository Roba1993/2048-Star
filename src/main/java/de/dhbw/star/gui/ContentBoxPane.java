/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 */
package de.dhbw.star.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Robert Schütte
 */
public class ContentBoxPane extends JPanel {

    private JLabel value;

    public ContentBoxPane() {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black, 2));

        value = new JLabel("", JLabel.CENTER);
        value.setVisible(true);
        value.setFont(new Font("Serif", Font.BOLD, 24));
        add(value, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setText(String text) {
        if (null == text) {
            return;
        }

        //don't display the 0
        if ("0".equals(text)) {
            text = "";
        }

        //set the text
        value.setText(text);

        //color the background
        switch (text) {
            case "2":
                setBackground(Color.lightGray);
                break;
            case "4":
                setBackground(Color.yellow);
                break;
            case "8":
                setBackground(Color.cyan);
                break;
            case "16":
                setBackground(Color.green);
                break;
            case "32":
                setBackground(Color.magenta);
                break;
            case "64":
                setBackground(Color.red);
                break;
            default:
                setBackground(Color.white);
                break;
        }

    }
}
