package gui.zuweisung.dialog;

import model.Mitarbeiter;
import service.MitarbeiterService;
import util.TableStyler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Mitarbeiter_Auswahl_Dialog {

    public static Integer zeige_Dialog(Component parent) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Mitarbeiter auswählen", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(600, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        String[] spalten = {"ID", "Vorname", "Nachname", "Abteilung"};
        DefaultTableModel model = new DefaultTableModel(spalten, 0);

        JTable table = new JTable(model);
        TableStyler.style_Tabelle(table);

        for (Mitarbeiter mitarbeiter : MitarbeiterService.alle_Mitarbeiter()) {
            model.addRow(new Object[]{
                    mitarbeiter.getId(), mitarbeiter.getVorname(), mitarbeiter.getNachname(), mitarbeiter.getAbteilung()
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton btnAuswaehlen = new JButton("Auswählen");
        final Integer[] selectedId = {null};

        btnAuswaehlen.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                selectedId[0] = (Integer) model.getValueAt(selectedRow, 0);
                dialog.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAuswaehlen);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
        return selectedId[0];
    }
}
