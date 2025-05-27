package util;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TableStyler {

    public static void style_Tabelle(JTable table) {

        Font headerFont = new Font("Arial Unicode MS", Font.BOLD, 16);
        Font cellFont = new Font("Arial Unicode MS", Font.PLAIN, 15);

        // Header-Stil
        JTableHeader header = table.getTableHeader();
        header.setFont(headerFont);
        header.setBackground(new Color(30, 90, 160));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false); // Ändern der Spaltenbreite

        //  Zeilenstil
        table.setFont(cellFont);
        table.setRowHeight(32);
        table.setGridColor(new Color(210, 210, 240));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        // Farben für den Hintergrund und die ausgewählte Zeile
        table.setBackground(new Color(250, 250, 255));
        table.setSelectionBackground(new Color(186, 220, 255));
        table.setSelectionForeground(Color.DARK_GRAY);

        // Zentrum für Inhaltssortierung
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Tooltip für Spaltenüberschriften
        header.setToolTipText("Klicken Sie hier, um die Spaltenüberschriften Sortieren.");
    }
}
