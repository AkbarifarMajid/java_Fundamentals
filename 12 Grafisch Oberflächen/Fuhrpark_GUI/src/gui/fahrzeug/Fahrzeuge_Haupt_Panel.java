package gui.fahrzeug;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

import model.*;
import service.*;
import util.IconLoader;
import java.util.List;

import util.*;

public class Fahrzeuge_Haupt_Panel extends JPanel {

    private JComboBox<String> my_combo_Typ;
    private JComboBox<String> my_combo_Feld;
    private JTextField mx_text_Suche;
    private JButton my_button_Suchen;
    private JTable table_Fahrzeug;
    private DefaultTableModel table_Model;

    public Fahrzeuge_Haupt_Panel() {
        setLayout(new BorderLayout());

        //  Suchpanel erstellen
        JPanel suchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        my_combo_Typ = new JComboBox<>(new String[]{"Alle", "PKW", "LKW", "Motorrad", "Fahrrad"});
        my_combo_Feld = new JComboBox<>();
        mx_text_Suche = new JTextField(15);
        my_button_Suchen = new JButton("\uD83D\uDD0D");
        my_button_Suchen.setToolTipText("Fahrzeug Suchen");
        IconLoader.styl_UnicCode_Buttons(my_button_Suchen, "\uD83D\uDD0D");
        suchPanel.add(my_combo_Typ);
        suchPanel.add(my_combo_Feld);
        suchPanel.add(mx_text_Suche);
        suchPanel.add(my_button_Suchen);

        // Schnellspeicher-Button (rechts oben)
        JButton btnFastSave = new JButton("\u270F\uFE0F");
        btnFastSave.setToolTipText("Schnelle Bearbeitung speichern");
        IconLoader.styl_UnicCode_Buttons(btnFastSave, "\u270F\uFE0F");

        JPanel fastEditPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        fastEditPanel.setBackground(suchPanel.getBackground());
        fastEditPanel.add(btnFastSave);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(suchPanel, BorderLayout.WEST);
        topPanel.add(fastEditPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // -------------------- Tabelle --------------------
        String[] spalten = {"ID", "Typ", "Hersteller", "Modell", "Baujahr", "Kennzeichen", "Besitzer"};

        table_Model = new DefaultTableModel(spalten, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID ist nicht editierbar
            }
        };

        table_Fahrzeug = new JTable(table_Model);

        //Sortieren und style der Tabelle
        table_Fahrzeug.setAutoCreateRowSorter(true);
        TableStyler.style_Tabelle(table_Fahrzeug);

        add(new JScrollPane(table_Fahrzeug), BorderLayout.CENTER);


        // -------------------------Panel unten mit Buttons --------------------
        JPanel botton_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botton_Panel.setBackground(new Color(240, 240, 255));

        JButton button_Add = new JButton("\u2795 "); // Add Button
        IconLoader.styl_UnicCode_Buttons(button_Add, "\u2795");
        button_Add.setToolTipText("Neues Fahrzeug eintragen");

        JButton button_Bearbeiten = new JButton("\u270F "); // Edit Button
        IconLoader.styl_UnicCode_Buttons(button_Bearbeiten, "\u270F");
        button_Bearbeiten.setToolTipText("Fahrzeug bearbeiten");
        button_Bearbeiten.setEnabled(false);

        JButton button_Loeschen = new JButton("\uD83D\uDDD1\u00F6");// Delete Button
        IconLoader.styl_UnicCode_Buttons(button_Loeschen, "\uD83D\uDDD1");
        button_Loeschen.setToolTipText("Fahrzeug entfernen");
        button_Loeschen.setEnabled(false);

        JButton button_Refresh = new JButton("\uD83D\uDD04 "); // Refresh Button
        IconLoader.styl_UnicCode_Buttons(button_Refresh, "\uD83D\uDD04");
        button_Refresh.setToolTipText("Tabelle neu laden");

        botton_Panel.add(button_Add);
        botton_Panel.add(button_Bearbeiten);
        botton_Panel.add(button_Loeschen);
        botton_Panel.add(button_Refresh);
        add(botton_Panel, BorderLayout.SOUTH);

        update_Felder_Liste("Alle");
        my_combo_Typ.addActionListener(e -> update_Felder_Liste((String) my_combo_Typ.getSelectedItem()));
        my_button_Suchen.addActionListener(e -> sucheen_Zeigen());
        button_Refresh.addActionListener(e -> lade_Tabelle());

        button_Add.addActionListener(e -> {
            new Hinzu_Fahrzeug_Dialog((JFrame) SwingUtilities.getWindowAncestor(this));
            lade_Tabelle();
        });

        table_Fahrzeug.getSelectionModel().addListSelectionListener(e -> {
            boolean aktiv = table_Fahrzeug.getSelectedRowCount() > 0;
            button_Bearbeiten.setEnabled(aktiv);
            button_Loeschen.setEnabled(aktiv);
        });

        button_Bearbeiten.addActionListener(e -> {
            int row = table_Fahrzeug.getSelectedRow();
            if (row == -1) return;
            int id = Integer.parseInt(table_Fahrzeug.getValueAt(row, 0).toString());
            Fahrzeug f = FahrzeugService.fahrzeug_Suchen_ID(id);
            if (f != null) new Bearbeiten_Fahrzeug_Dialog((JFrame) SwingUtilities.getWindowAncestor(this), f);
            lade_Tabelle();
        });

        button_Loeschen.addActionListener(e -> {
            int row = table_Fahrzeug.getSelectedRow();
            if (row == -1) return;
            int id = Integer.parseInt(table_Fahrzeug.getValueAt(row, 0).toString());
            int bestaetigung = JOptionPane.showConfirmDialog(this, "Wirklich l\u00f6schen?", "Best\u00e4tigung", JOptionPane.YES_NO_OPTION);
            if (bestaetigung == JOptionPane.YES_OPTION) {
                if (FahrzeugService.fahrzeug_Loeschen(id)) lade_Tabelle();
                else JOptionPane.showMessageDialog(this, "Fehler beim L\u00f6schen.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnFastSave.addActionListener(e -> {
            int zeilen = table_Model.getRowCount();
            for (int i = 0; i < zeilen; i++) {
                try {
                    int id = Integer.parseInt(table_Model.getValueAt(i, 0).toString());
                    String typ = table_Model.getValueAt(i, 1).toString();
                    String hersteller = table_Model.getValueAt(i, 2).toString();
                    String modell = table_Model.getValueAt(i, 3).toString();
                    int baujahr = Integer.parseInt(table_Model.getValueAt(i, 4).toString());
                    String kennzeichen = table_Model.getValueAt(i, 5).toString();

                    Fahrzeug original = FahrzeugService.fahrzeug_Suchen_ID(id);
                    if (original == null) continue;

                    original.setHersteller(hersteller);
                    original.setModell(modell);
                    original.setBaujahr(baujahr);
                    original.setKennzeichen(kennzeichen);

                    FahrzeugService.fahrzeug_Bearbeiten(original);
                } catch (Exception ex) {
                    System.err.println("Fehler beim Speichern in Zeile " + i + ": " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(this, "\u00c4nderungen erfolgreich gespeichert.");
            lade_Tabelle();
        });

        lade_Tabelle();
    }

    private void update_Felder_Liste(String typ) {
        my_combo_Feld.removeAllItems();
        List<String> basis = Arrays.asList("ID", "Hersteller", "Modell", "Baujahr");
        List<String> extra = switch (typ) {
            case "PKW" -> List.of("T\u00fcrenzahl");
            case "LKW" -> List.of("Max. Ladung");
            case "Motorrad" -> List.of("Hubraum", "Gangschaltung");
            case "Fahrrad" -> List.of("Hat Korb");
            default -> List.of();
        };
        basis.forEach(my_combo_Feld::addItem);
        extra.forEach(my_combo_Feld::addItem);
    }

    private void lade_Tabelle() {
        table_Model.setRowCount(0);  // clear table

        for (Fahrzeug fahrzeug : FahrzeugService.serviceAlleFahrzeug()) {
            String typ = fahrzeug.getTyp_Fahrzeug();
            String typMitUnicode = IconLoader.getUnicodeTypLabel(typ);

            // Wenn die ID gefunden wird, lesen Sie den vollständigen Namen des Mitarbeiters aus der Datenbank
            Integer besitzerId = FahrzeugService.getBesitzerId(fahrzeug.getId());
            String besitzerName = (besitzerId != null) ? MitarbeiterService.getNameById(besitzerId) : "—";


            // ADD Row in Tabele
            table_Model.addRow(new Object[]{fahrzeug.getId(), typMitUnicode, fahrzeug.getHersteller(), fahrzeug.getModell(), fahrzeug.getBaujahr(), fahrzeug.getKennzeichen(), besitzerName
            });
        }
    }


    private void sucheen_Zeigen() {

        String typ = (String) my_combo_Typ.getSelectedItem();
        String feld = (String) my_combo_Feld.getSelectedItem();
        String suchwert = mx_text_Suche.getText().trim().toLowerCase();
        table_Model.setRowCount(0);

        boolean gefunden = false;

        for (Fahrzeug fahrzeug : FahrzeugService.serviceAlleFahrzeug()) {
            String type_Farzeug = "?";
            if (fahrzeug instanceof PKW) type_Farzeug = "PKW";
            else if (fahrzeug instanceof LKW) type_Farzeug = "LKW";
            else if (fahrzeug instanceof Motorrad) type_Farzeug = "Motorrad";
            else if (fahrzeug instanceof Fahrrad) type_Farzeug = "Fahrrad";

            if (!typ.equals("Alle") && !type_Farzeug.equals(typ)) continue;

            String wert = "";
            if ("ID".equals(feld)) wert = String.valueOf(fahrzeug.getId());
            else if ("Hersteller".equals(feld)) wert = fahrzeug.getHersteller();
            else if ("Modell".equals(feld)) wert = fahrzeug.getModell();
            else if ("Baujahr".equals(feld)) wert = String.valueOf(fahrzeug.getBaujahr());
            else if ("Türenzahl".equals(feld) && fahrzeug instanceof PKW)
                wert = String.valueOf(((PKW) fahrzeug).getSitzanzahl());
            else if ("Max. Ladung".equals(feld) && fahrzeug instanceof LKW)
                wert = String.valueOf(((LKW) fahrzeug).getLadevolumen());
            else if ("Hubraum".equals(feld) && fahrzeug instanceof Motorrad)
                wert = String.valueOf(((Motorrad) fahrzeug).getHubraum());
            else if ("Gangschaltung".equals(feld) && fahrzeug instanceof Motorrad)
                wert = String.valueOf(((Motorrad) fahrzeug).getGangschaltung());
            else if ("Hat Korb".equals(feld) && fahrzeug instanceof Fahrrad)
                wert = ((Fahrrad) fahrzeug).isHatKorb() ? "ja" : "nein";

            if (wert.toLowerCase().contains(suchwert)) {
                table_Model.addRow(new Object[]{
                        fahrzeug.getId(),
                        fahrzeug.getTyp_Fahrzeug(),
                        fahrzeug.getHersteller(),
                        fahrzeug.getModell(),
                        fahrzeug.getBaujahr(),
                        type_Farzeug
                });
                gefunden = true;
            }
        }

        if (!gefunden) {
            JOptionPane.showMessageDialog(this, "Es gibt keinen Fahrzeug mit diesen Angaben.", "Leider", JOptionPane.INFORMATION_MESSAGE);
        }
    }


}
