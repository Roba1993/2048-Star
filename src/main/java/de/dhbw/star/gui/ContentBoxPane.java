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
        setBackground(Color.decode("#bbada0"));
        setBorder(new LineBorder(Color.decode("#bbada0"), 3));

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
                setBackground(Color.decode("#eee4da"));
                break;
            case "4":
                setBackground(Color.decode("#ede0c8"));
                break;
            case "8":
                setBackground(Color.decode("#f2b179"));
                break;
            case "16":
                setBackground(Color.decode("#f59563"));
                break;
            case "32":
                setBackground(Color.decode("#f67c5f"));
                break;
            case "64":
                setBackground(Color.decode("#f65e3b"));
                break;
            case "128":
                setBackground(Color.decode("#edcf72"));
                break;
            case "256":
                setBackground(Color.decode("#edcc61"));
                break;
            case "512":
                setBackground(Color.decode("#edc850"));
                break;
            case "1024":
                setBackground(Color.decode("#edc53f"));
                break;
            case "2048":
                setBackground(Color.decode("#edc22e"));
                break;
            default:
                setBackground(Color.decode("#FFFCF9"));
                break;
        }

    }
}
