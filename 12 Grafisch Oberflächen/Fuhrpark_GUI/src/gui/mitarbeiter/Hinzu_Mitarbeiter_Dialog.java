package gui.mitarbeiter;

import model.Mitarbeiter;
import service.MitarbeiterService;
import util.IconLoader;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

// JDialog ist ein modales Pop-up-Fenster zur Eingabe eines neuen Mitarbeiters
public class Hinzu_Mitarbeiter_Dialog extends JDialog {

    private JTextField textbox_Vorname, textbox_Nachname, textbox_Telefon, textbox_Email;
    private DatePicker datePicker_Einstellungsdatum;
    private JComboBox<String> combobox_Abteilung;

    public Hinzu_Mitarbeiter_Dialog(JFrame jFrame_Parent) {
        super(jFrame_Parent, "Neuer Mitarbeiter", true);

        setSize(650, 420);
        setLocationRelativeTo(jFrame_Parent);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(227, 224, 224));

        // Schrift und Farben
        Font schrift = new Font("Open Sans", Font.ITALIC, 16);
        Color hellblau = new Color(230, 240, 255);
        Color textColor = new Color(0, 46, 90);

        GridBagConstraints feld_Position = new GridBagConstraints();
        feld_Position.insets = new Insets(6, 10, 5, 10);
        feld_Position.fill = GridBagConstraints.HORIZONTAL;
        feld_Position.anchor = GridBagConstraints.WEST;

        // Labels
        JLabel label_Vorname = new JLabel("Vorname:");
        JLabel label_Nachname = new JLabel("Nachname:");
        JLabel label_Abteilung = new JLabel("Abteilung:");
        JLabel label_Telefon = new JLabel("Telefon:");
        JLabel label_Email = new JLabel("Email:");
        JLabel label_Datum = new JLabel("Einstellungsdatum:");

        JLabel[] labels = {label_Vorname, label_Nachname, label_Abteilung, label_Telefon, label_Email, label_Datum};
        for (JLabel lbl : labels) {
            lbl.setForeground(textColor);
            lbl.setFont(schrift);
        }

        // Eingabefelder
        textbox_Vorname = new JTextField(20);
        textbox_Nachname = new JTextField(20);
        textbox_Telefon = new JTextField(20);
        textbox_Email = new JTextField(20);
        datePicker_Einstellungsdatum = new DatePicker();

        JTextField[] textFields = {textbox_Vorname, textbox_Nachname, textbox_Telefon, textbox_Email};
        for (JTextField tf : textFields) {
            tf.setBackground(hellblau);
            tf.setFont(schrift);
            tf.setForeground(textColor);
        }

        datePicker_Einstellungsdatum.getComponentDateTextField().setBackground(hellblau);
        datePicker_Einstellungsdatum.getComponentDateTextField().setFont(schrift);
        datePicker_Einstellungsdatum.getComponentDateTextField().setForeground(textColor);

        combobox_Abteilung = new JComboBox<>(new String[]{"Buchhaltung", "IT", "Verwaltung", "Marketing", "Fuhrpark"});
        combobox_Abteilung.setFont(schrift);
        combobox_Abteilung.setForeground(textColor);

        // Platzierung der Komponenten
        Component[] komponenten = {textbox_Vorname, textbox_Nachname, combobox_Abteilung, textbox_Telefon, textbox_Email, datePicker_Einstellungsdatum};
        for (int i = 0; i < labels.length; i++) {
            feld_Position.gridx = 0; feld_Position.gridy = i;
            add(labels[i], feld_Position);
            feld_Position.gridx = 1;
            add(komponenten[i], feld_Position);
        }

        // Speichern-Button
        JButton button_Speichern = new JButton("\u2795");
        IconLoader.styl_UnicCode_Buttons(button_Speichern, "\u2795");
        button_Speichern.setToolTipText("Mitarbeiter speichern");

        feld_Position.gridx = 0;
        feld_Position.gridy = labels.length;
        feld_Position.gridwidth = 2;
        feld_Position.anchor = GridBagConstraints.CENTER;
        add(button_Speichern, feld_Position);

        // Aktion für Speichern-Button
        button_Speichern.addActionListener(e -> {
            Mitarbeiter validier_Mitarbeiter = new Mitarbeiter(
                    textbox_Vorname.getText().trim(),
                    textbox_Nachname.getText().trim(),
                    combobox_Abteilung.getSelectedItem().toString(),
                    textbox_Telefon.getText().trim(),
                    textbox_Email.getText().trim(),
                    datePicker_Einstellungsdatum.getDate() != null ? datePicker_Einstellungsdatum.getDate().toString() : ""
            );

            String validate_Result = MitarbeiterService.validiere(validier_Mitarbeiter);
            if (validate_Result != null) {
                JOptionPane.showMessageDialog(this, validate_Result, "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (MitarbeiterService.neu_Mitarbeiter(validier_Mitarbeiter)) {
                JOptionPane.showMessageDialog(this, "Mitarbeiter erfolgreich gespeichert!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Beim Speichern ist ein Fehler aufgetreten!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });


        setVisible(true);
    } // Ende Konstruktor

} // Ende Hinzu_Mitarbeiter_Dialog
