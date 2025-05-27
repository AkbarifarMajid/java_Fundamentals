package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IconLoader {

    // Für JButton – Style
    public static void styl_UnicCode_Buttons(JButton button, String unicode) {
        button.setText(unicode);
        int baseSize = 16;
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, baseSize));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setForeground(new Color(0, 102, 204));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Segoe UI Emoji", Font.BOLD, baseSize));
                button.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, baseSize));
                button.setForeground(new Color(0, 102, 204));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(Color.RED);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setForeground(Color.ORANGE);
            }
        });
    }

    // Für JMenu – Style
    public static void style_UnicCode_Menu(JMenu menu, String unicode) {
        int baseSize = 12;
        menu.setText(unicode + " " + menu.getText());
        menu.setFont(new Font("Segoe UI Emoji", Font.PLAIN, baseSize));
        menu.setForeground(new Color(0, 102, 204));

        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setFont(new Font("Segoe UI Emoji", Font.BOLD, baseSize ));
                menu.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menu.setFont(new Font("Segoe UI Emoji", Font.PLAIN, baseSize));
                menu.setForeground(new Color(0, 102, 204));
            }
        });
    }

    public static String getUnicodeTypLabel(String typ) {
        return switch (typ) {
            case "PKW" -> "\uD83D\uDE97 PKW";
            case "LKW" -> "\uD83D\uDE9A LKW";
            case "Motorrad" -> "\uD83C\uDFCD Motorrad";
            case "Fahrrad" -> "\uD83D\uDEB2 Fahrrad";
            default -> typ;
        };
    }

}
