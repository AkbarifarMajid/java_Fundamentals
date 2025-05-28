package gui.zuweisung;

import gui.zuweisung.dialog.Fahrzeug_Auswahl_Dialog;
import gui.zuweisung.dialog.Mitarbeiter_Auswahl_Dialog;

import model.*;
import service.*;
import util.*;

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

        JTextField field_FahrzeugId = new JTextField(20);
        JTextField field_MitarbeiterId = new JTextField(20);
        DatePicker date_Picker_Von = new DatePicker();
        DatePicker date_Picker_Bis = new DatePicker();
        JTextArea text_Bemerkung = new JTextArea(3, 20);
        JScrollPane scroll_Text_Bemerkung = new JScrollPane(text_Bemerkung);

        // Auswahl-Buttons

        JButton button_Fahrzeug_Waehlen = new JButton();
        IconLoader.styl_UnicCode_Buttons(button_Fahrzeug_Waehlen, "\uD83D\uDD0D");
        button_Fahrzeug_Waehlen.setToolTipText("Fahrzeug auswählen");

        button_Fahrzeug_Waehlen.addActionListener(e -> {
            Integer id = Fahrzeug_Auswahl_Dialog.zeige_Dialog(this);
            if (id != null) field_FahrzeugId.setText(String.valueOf(id));
        });

        JButton button_Mitarbeiter_Waehlen = new JButton();
        IconLoader.styl_UnicCode_Buttons(button_Mitarbeiter_Waehlen, "\uD83D\uDD0D");
        button_Mitarbeiter_Waehlen.setToolTipText("Mitarbeiter auswählen");

        button_Mitarbeiter_Waehlen.addActionListener(e -> {
            Integer id = Mitarbeiter_Auswahl_Dialog.zeige_Dialog(this);
            if (id != null) field_MitarbeiterId.setText(String.valueOf(id));
        });

        JPanel fahrzeugPanel = new JPanel(new BorderLayout());
        fahrzeugPanel.add(field_FahrzeugId, BorderLayout.CENTER);
        fahrzeugPanel.add(button_Fahrzeug_Waehlen, BorderLayout.EAST);

        JPanel mitarbeiterPanel = new JPanel(new BorderLayout());
        mitarbeiterPanel.add(field_MitarbeiterId, BorderLayout.CENTER);
        mitarbeiterPanel.add(button_Mitarbeiter_Waehlen, BorderLayout.EAST);

        JComponent[] fields = { fahrzeugPanel, mitarbeiterPanel, date_Picker_Von, date_Picker_Bis, scroll_Text_Bemerkung };

        // Style für alle field
        field_FahrzeugId.setFont(schrift);
        field_FahrzeugId.setBackground(blau);
        field_FahrzeugId.setForeground(textFarbe);

        field_MitarbeiterId.setFont(schrift);
        field_MitarbeiterId.setBackground(blau);
        field_MitarbeiterId.setForeground(textFarbe);

        date_Picker_Von.getComponentDateTextField().setFont(schrift);
        date_Picker_Von.getComponentDateTextField().setBackground(blau);

        date_Picker_Bis.getComponentDateTextField().setFont(schrift);
        date_Picker_Bis.getComponentDateTextField().setBackground(blau);

        text_Bemerkung.setFont(schrift);
        text_Bemerkung.setBackground(blau);
        text_Bemerkung.setForeground(textFarbe);

        // add to form
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            formPanel.add(labels[i], gbc);
            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        // Button Hinzufügen +
        JButton button_Add = new JButton("\u2795"); // +
        IconLoader.styl_UnicCode_Buttons(button_Add, "\u2795");
        button_Add.setToolTipText("Fahrzeug zuweisen");
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        formPanel.add(button_Add, gbc);

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
        button_Add.addActionListener(e -> {
            try {
                int fahrzeug_Id = Integer.parseInt(field_FahrzeugId.getText().trim());
                int mitarbeiter_Id = Integer.parseInt(field_MitarbeiterId.getText().trim());
                LocalDate von = date_Picker_Von.getDate();
                LocalDate bis = date_Picker_Bis.getDate();
                String bemerkung = text_Bemerkung.getText().trim();

                if (FahrzeugService.fahrzeug_Suchen_ID(fahrzeug_Id) == null || MitarbeiterService.finde_Mitarbeiter_Id(mitarbeiter_Id) == null) {
                    JOptionPane.showMessageDialog(this, "Fahrzeug oder Mitarbeiter nicht gefunden!");
                    return;
                }

                if (bis != null && bis.isBefore(von)) {
                    JOptionPane.showMessageDialog(this, "Bis-Datum darf nicht vor Von-Datum liegen.");
                    return;
                }

                // Auf Zeitkonflikte mit vorherigen Zuteilungen prüfen
                if (!ZuweisungService.verfugbar_Contorl(fahrzeug_Id, von, bis)) {
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
                    field_FahrzeugId.setText("");
                    field_MitarbeiterId.setText("");
                    date_Picker_Von.clear();
                    date_Picker_Bis.clear();
                    text_Bemerkung.setText("");
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
