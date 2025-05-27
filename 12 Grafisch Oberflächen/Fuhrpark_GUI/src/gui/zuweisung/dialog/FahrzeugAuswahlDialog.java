package gui.zuweisung.dialog;

import model.Fahrzeug;
import service.FahrzeugService;
import util.TableStyler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FahrzeugAuswahlDialog {

    public static Integer zeigeDialog(Component parent) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Fahrzeug auswählen", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(600, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        String[] spalten = {"ID", "Typ", "Hersteller", "Modell"};
        DefaultTableModel model = new DefaultTableModel(spalten, 0);
        JTable table = new JTable(model);
        TableStyler.style_Tabelle(table);

        for (Fahrzeug f : FahrzeugService.serviceAlleFahrzeug()) {
            model.addRow(new Object[]{
                    f.getId(), f.getTyp_Fahrzeug(), f.getHersteller(), f.getModell()
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
