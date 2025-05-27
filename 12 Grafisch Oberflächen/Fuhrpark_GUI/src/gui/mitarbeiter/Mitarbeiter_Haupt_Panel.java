package gui.mitarbeiter;

import model.Mitarbeiter;
import service.MitarbeiterService;
import java.util.ArrayList;
import util.*;

import javax.swing.*; // import alle Grafik sachen von swing (JPanel, JTable, JButton, JLabel, JTextField, JDialog, JOptionPane )
import javax.swing.table.DefaultTableModel; // Wird für die Arbeit mit dem Tabellendatenmodell (JTable) verwendet.
import java.awt.*;

import java.awt.event.MouseAdapter; // Zum Empfangen und Verwalten von Mausereignissen wie Klicks, Doppelklicks usw.
import java.awt.event.MouseEvent; // Für Details zum Mausklick (wie gedrückte Taste oder Koordinaten)

import java.awt.event.KeyAdapter; // Zum Empfangen und Verwalten von Tastaturereignissen (wie etwa Drücken der Entf- oder Eingabetaste usw.)
import java.awt.event.KeyEvent; // Zum Schreiben von Aktionen im Zusammenhang mit Tastatur Tasten.


public class Mitarbeiter_Haupt_Panel extends JPanel {

    private JTable Tabelle_Mitarbeiter;
    private DefaultTableModel tabelleModell;
    private JComboBox<String> comboBox_Filter;
    private JTextField textBox_Search;

    public Mitarbeiter_Haupt_Panel() {
        setLayout(new BorderLayout());

        setLayout(new BorderLayout());

// Tabelle konfigurieren
        String[] spalten = {"ID", "Vorname", "Nachname", "Abteilung", "Telefon", "Email", "Einstellungsdatum"};
        tabelleModell = new DefaultTableModel(spalten, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID ist nicht editierbar
            }
        };

        Tabelle_Mitarbeiter = new JTable(tabelleModell);

        //  Sortieren und style der Tabelle
        Tabelle_Mitarbeiter.setAutoCreateRowSorter(true);
        TableStyler.style_Tabelle(Tabelle_Mitarbeiter);

        JScrollPane scrollPane = new JScrollPane(Tabelle_Mitarbeiter);
        add(scrollPane, BorderLayout.CENTER);


        //  Suchleiste und Schnell-Speichern-Button
        comboBox_Filter = new JComboBox<>(new String[]{"ID", "Vorname", "Nachname"});
        textBox_Search = new JTextField(20);
        textBox_Search.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        textBox_Search.setForeground(new Color(0, 102, 204));


        JButton suchen_Button = new JButton();
        IconLoader.styl_UnicCode_Buttons(suchen_Button, "\uD83D\uDD0D");
        suchen_Button.setToolTipText("Suchen");


        suchen_Button.addActionListener(e -> {
            String type_search = (String) comboBox_Filter.getSelectedItem();
            String wert_Search = textBox_Search.getText().trim();
            if (wert_Search.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Der Suchbegriff darf nicht leer sein.", "Achtung", JOptionPane.WARNING_MESSAGE);
                return;
            }
            zeig_Such_Ergebnis(type_search, wert_Search);
        });

        JButton schnel_Edit_Button = new JButton();
        IconLoader.styl_UnicCode_Buttons(schnel_Edit_Button, "\u270F\uFE0F");
        schnel_Edit_Button.setToolTipText("Bearbeiten");

        schnel_Edit_Button.addActionListener(e -> {
            int zeilen = tabelleModell.getRowCount();
            for (int i = 0; i < zeilen; i++) {
                try {
                    int id = (int) tabelleModell.getValueAt(i, 0);
                    String vorname = String.valueOf(tabelleModell.getValueAt(i, 1));
                    String nachname = String.valueOf(tabelleModell.getValueAt(i, 2));
                    String abteilung = String.valueOf(tabelleModell.getValueAt(i, 3));
                    String telefon = String.valueOf(tabelleModell.getValueAt(i, 4));
                    String email = String.valueOf(tabelleModell.getValueAt(i, 5));
                    String datum = String.valueOf(tabelleModell.getValueAt(i, 6));
                    Mitarbeiter m = new Mitarbeiter(id, vorname, nachname, abteilung, telefon, email, datum);
                    MitarbeiterService.mitarbeiter_Bearbeiten(m);
                } catch (Exception ex) {
                    System.err.println("Problem beim Speichern in Zeile " + i + ": " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(this, "Änderungen erfolgreich gespeichert.");
        });

        Tabelle_Mitarbeiter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                schnel_Edit_Button.revalidate();
                schnel_Edit_Button.repaint();
            }
        });

        // Top Panel (Suchen + Edit speichern)
        JPanel my_TopPanel = new JPanel(new BorderLayout());
        my_TopPanel.setBackground(new Color(245, 245, 245));

        JPanel link_Sucht_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        link_Sucht_Panel.setOpaque(false);
        link_Sucht_Panel.add(comboBox_Filter);
        link_Sucht_Panel.add(textBox_Search);
        link_Sucht_Panel.add(suchen_Button);

        JPanel recht_editB_Panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        recht_editB_Panel.setOpaque(false);
        recht_editB_Panel.add(schnel_Edit_Button);

        my_TopPanel.add(link_Sucht_Panel, BorderLayout.WEST);
        my_TopPanel.add(recht_editB_Panel, BorderLayout.EAST);
        add(my_TopPanel, BorderLayout.NORTH);

        // Panel für Aktionen unten
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));

        JButton button_add = new JButton();
        IconLoader.styl_UnicCode_Buttons(button_add, "\u2795");
        button_add.setToolTipText("Hinzufügen");
        // ADD
        button_add.addActionListener(e -> {
            new Hinzu_Mitarbeiter_Dialog((JFrame) SwingUtilities.getWindowAncestor(this));
            datenLaden();
        });

        // Edit
        JButton Button_Edit = new JButton();
        IconLoader.styl_UnicCode_Buttons(Button_Edit, "\u270F");
        Button_Edit.setToolTipText("Bearbeiten");

        Button_Edit.addActionListener(e -> {
            int selectedRow = Tabelle_Mitarbeiter.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Bitte wählen Sie einen Mitarbeiter aus.");
                return;
            }
            int id = (int) tabelleModell.getValueAt(selectedRow, 0);
            String vorname = (String) tabelleModell.getValueAt(selectedRow, 1);
            String nachname = (String) tabelleModell.getValueAt(selectedRow, 2);
            String abteilung = (String) tabelleModell.getValueAt(selectedRow, 3);
            String telefon = (String) tabelleModell.getValueAt(selectedRow, 4);
            String email = (String) tabelleModell.getValueAt(selectedRow, 5);
            String einstellungsdatum = (String) tabelleModell.getValueAt(selectedRow, 6);
            Mitarbeiter find_Mitarbeiter = new Mitarbeiter(id, vorname, nachname, abteilung, telefon, email, einstellungsdatum);
           Bearbeiten_Mitarbeiter_Dialog.bearbeiten_Form_Zeigen(this,find_Mitarbeiter,()->datenLaden());


        });

        // Delete
        JButton button_Delete = new JButton();
        IconLoader.styl_UnicCode_Buttons(button_Delete, "\uD83D\uDDD1");
        button_Delete.setToolTipText("Löschen");

        button_Delete.setEnabled(false);
        Tabelle_Mitarbeiter.getSelectionModel().addListSelectionListener(e -> {
            button_Delete.setEnabled(Tabelle_Mitarbeiter.getSelectedRowCount() > 0);
        });
        button_Delete.addActionListener(e -> {
            int[] selected = Tabelle_Mitarbeiter.getSelectedRows();
            int confirm = JOptionPane.showConfirmDialog(null, "Sind Sie sicher, dass Sie löschen möchten?", "Bestätigung", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int deleted = 0;
                for (int i = selected.length - 1; i >= 0; i--) {
                    int id = (int) Tabelle_Mitarbeiter.getValueAt(selected[i], 0);
                    if (MitarbeiterService.mitarbeiter_Loeschen(id)) {
                        ((DefaultTableModel) Tabelle_Mitarbeiter.getModel()).removeRow(selected[i]);
                        deleted++;
                    }
                }
                JOptionPane.showMessageDialog(null, deleted + " Mitarbeiter gelöscht.");
            }
        });
        Tabelle_Mitarbeiter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE && button_Delete.isEnabled()) {
                    button_Delete.doClick();
                }
            }
        });

        // Refresh
        JButton button_Refresh = new JButton();
        IconLoader.styl_UnicCode_Buttons(button_Refresh, "\uD83D\uDD04");
        button_Refresh.setToolTipText("Aktualisieren");

        button_Refresh.addActionListener(e -> datenLaden());

        // Add lae button in Panel
        buttonPanel.add(button_add);
        buttonPanel.add(Button_Edit);
        buttonPanel.add(button_Delete);
        buttonPanel.add(button_Refresh);
        add(buttonPanel, BorderLayout.SOUTH);

        datenLaden();


    } // end constraktor Mitarbeiter_Haupt_Panel


    //Metod, Alle Informationen in dieser Tabelle anzeigen.
    private void datenLaden() {
        tabelleModell.setRowCount(0); //Löscht alle aktuellen Zeilen in der Tabelle.
        ArrayList<Mitarbeiter> alledMitarbeiter_List = MitarbeiterService.alle_Mitarbeiter();
        for (Mitarbeiter mitarbeiter : alledMitarbeiter_List) {
            tabelleModell.addRow(new Object[]{
                    mitarbeiter.getId(), mitarbeiter.getVorname(), mitarbeiter.getNachname(),
                    mitarbeiter.getAbteilung(), mitarbeiter.getTelefon(), mitarbeiter.getEmail(), mitarbeiter.getEinstellungsdatum()
            });
        }
    }// End datenLaden

    // Metod Mitarbeiter Suchen nach zwei sachen field und wert !
    private void zeig_Such_Ergebnis(String feld, String wert) {
        tabelleModell.setRowCount(0); // //Löscht alle aktuellen Zeilen in der Tabelle.
        ArrayList<Mitarbeiter> my_Item_Filtter = MitarbeiterService.suche_Mitarbeiter(feld, wert);

        if (my_Item_Filtter.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Es gibt keinen Mitarbeiter mit diesen Angaben..", "Leider", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Mitarbeiter mitarbeiter : my_Item_Filtter) {
            tabelleModell.addRow(new Object[]{
                    mitarbeiter.getId(), mitarbeiter.getVorname(), mitarbeiter.getNachname(),
                    mitarbeiter.getAbteilung(), mitarbeiter.getTelefon(), mitarbeiter.getEmail(), mitarbeiter.getEinstellungsdatum()
            });
        }
    } // End mitarbeiterSuchen

}
