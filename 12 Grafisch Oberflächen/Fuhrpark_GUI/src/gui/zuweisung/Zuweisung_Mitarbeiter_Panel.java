package gui.zuweisung;

import gui.zuweisung.dialog.FahrzeugAuswahlDialog;
import gui.zuweisung.dialog.MitarbeiterAuswahlDialog;

import model.*;
import service.*;
import util.*;
import dao.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import com.github.lgooddatepicker.components.DatePicker;

public class Zuweisung_Mitarbeiter_Panel extends JPanel {

    private DefaultTableModel tableModel;

    public Zuweisung_Mitarbeiter_Panel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("FAHRZEUG-MITARBEITER ZUWEISUNG"));

        // ---------- Formular ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font schrift = new Font("Open Sans", Font.PLAIN, 15);
        Color blau = new Color(230, 240, 255);
        Color textFarbe = new Color(0, 46, 90);

        JLabel[] labels = {
                new JLabel("Fahrzeug-ID:"),
                new JLabel("Mitarbeiter-ID:"),
                new JLabel("Von:"),
                new JLabel("Bis:"),
                new JLabel("Bemerkung:")
        };

        for (JLabel jLabel : labels) {
            jLabel.setFont(schrift);
            jLabel.setForeground(textFarbe);
        }

        JTextField tfFahrzeugId = new JTextField(20);
        JTextField tfMitarbeiterId = new JTextField(20);
        DatePicker dpVon = new DatePicker();
        DatePicker dpBis = new DatePicker();
        JTextArea taBemerkung = new JTextArea(3, 20);
        JScrollPane scrollBemerkung = new JScrollPane(taBemerkung);

        // Auswahl-Buttons

        JButton btnFahrzeugWaehlen = new JButton();
        IconLoader.styl_UnicCode_Buttons(btnFahrzeugWaehlen, "\uD83D\uDD0D");
        btnFahrzeugWaehlen.setToolTipText("Fahrzeug auswählen");

        btnFahrzeugWaehlen.addActionListener(e -> {
            Integer id = FahrzeugAuswahlDialog.zeigeDialog(this);
            if (id != null) tfFahrzeugId.setText(String.valueOf(id));
        });

        JButton btnMitarbeiterWaehlen = new JButton();
        IconLoader.styl_UnicCode_Buttons(btnMitarbeiterWaehlen, "\uD83D\uDD0D");
        btnMitarbeiterWaehlen.setToolTipText("Mitarbeiter auswählen");

        btnMitarbeiterWaehlen.addActionListener(e -> {
            Integer id = MitarbeiterAuswahlDialog.zeigeDialog(this);
            if (id != null) tfMitarbeiterId.setText(String.valueOf(id));
        });

        JPanel fahrzeugPanel = new JPanel(new BorderLayout());
        fahrzeugPanel.add(tfFahrzeugId, BorderLayout.CENTER);
        fahrzeugPanel.add(btnFahrzeugWaehlen, BorderLayout.EAST);

        JPanel mitarbeiterPanel = new JPanel(new BorderLayout());
        mitarbeiterPanel.add(tfMitarbeiterId, BorderLayout.CENTER);
        mitarbeiterPanel.add(btnMitarbeiterWaehlen, BorderLayout.EAST);

        JComponent[] fields = { fahrzeugPanel, mitarbeiterPanel, dpVon, dpBis, scrollBemerkung };

        // Style für alle field
        tfFahrzeugId.setFont(schrift);
        tfFahrzeugId.setBackground(blau);
        tfFahrzeugId.setForeground(textFarbe);

        tfMitarbeiterId.setFont(schrift);
        tfMitarbeiterId.setBackground(blau);
        tfMitarbeiterId.setForeground(textFarbe);

        dpVon.getComponentDateTextField().setFont(schrift);
        dpVon.getComponentDateTextField().setBackground(blau);

        dpBis.getComponentDateTextField().setFont(schrift);
        dpBis.getComponentDateTextField().setBackground(blau);

        taBemerkung.setFont(schrift);
        taBemerkung.setBackground(blau);
        taBemerkung.setForeground(textFarbe);

        // add to form
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            formPanel.add(labels[i], gbc);
            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        // Butto Hinzufügen +
        JButton button = new JButton("\u2795"); // +
        IconLoader.styl_UnicCode_Buttons(button, "\u2795");
        button.setToolTipText("Fahrzeug zuweisen");
        gbc.gridx = 0; gbc.gridy = labels.length; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(button, gbc);

        add(formPanel, BorderLayout.NORTH);

        // --------- Tablle ----------
        String[] spalten = { "Fahrzeug-ID", "Typ", "Mitarbeiter", "Von", "Bis", "Bemerkung" };
        tableModel = new DefaultTableModel(spalten, 0);
        JTable table = new JTable(tableModel);

        // Style und Scroll
        table.setAutoCreateRowSorter(true);
        TableStyler.style_Tabelle(table);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ---------- Event , Button Hinzufügen ----------
        button.addActionListener(e -> {
            try {
                int fahrzeug_Id = Integer.parseInt(tfFahrzeugId.getText().trim());
                int mitarbeiter_Id = Integer.parseInt(tfMitarbeiterId.getText().trim());
                LocalDate von = dpVon.getDate();
                LocalDate bis = dpBis.getDate();
                String bemerkung = taBemerkung.getText().trim();

                if (FahrzeugService.fahrzeug_Suchen_ID(fahrzeug_Id) == null || MitarbeiterService.finde_Mitarbeiter_Id(mitarbeiter_Id) == null) {
                    JOptionPane.showMessageDialog(this, "Fahrzeug oder Mitarbeiter nicht gefunden!");
                    return;
                }

                if (bis != null && bis.isBefore(von)) {
                    JOptionPane.showMessageDialog(this, "Bis-Datum darf nicht vor Von-Datum liegen.");
                    return;
                }

                // Auf Zeitkonflikte mit vorherigen Zuteilungen prüfen
                if (!ZuweisungService.istFahrzeugVerfuegbar(fahrzeug_Id, von, bis)) {
                    JOptionPane.showMessageDialog(this, "Dieses Fahrzeug ist im gewählten Zeitraum bereits zugewiesen.", "Konflikt", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                //  Falls verfügbar, fahren wir fort
                boolean result = ZuweisungService.neueZuweisung(fahrzeug_Id, mitarbeiter_Id, von, bis, bemerkung);
                if (result) {
                    Fahrzeug fahrzeug = FahrzeugService.fahrzeug_Suchen_ID(fahrzeug_Id);
                    Mitarbeiter mitarbeiter = MitarbeiterService.finde_Mitarbeiter_Id(mitarbeiter_Id);
                    tableModel.addRow(new Object[]{
                            fahrzeug_Id,
                            fahrzeug != null ? fahrzeug.getTyp_Fahrzeug() : "-",
                            mitarbeiter != null ? mitarbeiter.getVorname() + " " + mitarbeiter.getNachname() : "-",
                            von,
                            bis,
                            bemerkung
                    });
                    tfFahrzeugId.setText("");
                    tfMitarbeiterId.setText("");
                    dpVon.clear();
                    dpBis.clear();
                    taBemerkung.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Fehler beim Speichern!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Fehlerhafte Eingabe: " + ex.getMessage());
            }
        });


        // ----------Erse Datei ----------
        List<Object[]> zuweisungen = ZuweisungService.alleZuweisungen();
        for (Object[] z : zuweisungen) {
            int fahrzeugId = (int) z[0];
            int mitarbeiterId = (int) z[1];
            LocalDate von = (LocalDate) z[2];
            LocalDate bis = (LocalDate) z[3];
            String bemerkung = (String) z[4];

            Fahrzeug find_Fahrzeug = FahrzeugService.fahrzeug_Suchen_ID(fahrzeugId);
            Mitarbeiter find_Mitarbeiter = MitarbeiterService.finde_Mitarbeiter_Id(mitarbeiterId);

            // Add to Tabele
            tableModel.addRow(new Object[]{
                    fahrzeugId,
                    find_Fahrzeug != null ? find_Fahrzeug.getTyp_Fahrzeug() : "-",
                    find_Mitarbeiter != null ? find_Mitarbeiter.getVorname() + " " + find_Mitarbeiter.getNachname() : "-", von, bis, bemerkung
            });
        } // End for

    }
}
