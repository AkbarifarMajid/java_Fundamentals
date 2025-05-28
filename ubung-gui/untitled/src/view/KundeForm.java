package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;

public class KundeForm extends JFrame {

    private JTextField vornameField;
    private JTextField nachnameField;
    private JTextField telefonField;
    private JTextField emailField;
    private JButton addButton;

    public KundeForm() {
        setTitle("Kunde hinzufügen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // لیبل و فیلد نام
        JLabel vornameLabel = new JLabel("Vorname:");
        vornameField = new JTextField(20);
        mainPanel.add(vornameLabel);
        mainPanel.add(vornameField);

        // لیبل و فیلد نام خانوادگی
        JLabel nachnameLabel = new JLabel("Nachname:");
        nachnameField = new JTextField(20);
        mainPanel.add(nachnameLabel);
        mainPanel.add(nachnameField);

        // لیبل و فیلد تلفن
        JLabel telefonLabel = new JLabel("Telefon:");
        telefonField = new JTextField(20);
        mainPanel.add(telefonLabel);
        mainPanel.add(telefonField);

        // لیبل و فیلد ایمیل
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);

        // دکمه
        addButton = new JButton("Kunde hinzufügen");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setLocationRelativeTo(null); // مرکز صفحه

        // رویداد کلیک دکمه
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vorname = vornameField.getText();
                String nachname = nachnameField.getText();
                String telefon = telefonField.getText();
                String email = emailField.getText();

                Kunde neuerKunde = new model.Kunde(vorname, nachname, telefon, email);
                dao.KundeDAO dao = new dao.KundeDAO();
                dao.hinzufuegenKunde(neuerKunde);

                JOptionPane.showMessageDialog(KundeForm.this,
                        "✅ Kunde wurde erfolgreich hinzugefügt!",
                        "Erfolg", JOptionPane.INFORMATION_MESSAGE);

                vornameField.setText("");
                nachnameField.setText("");
                telefonField.setText("");
                emailField.setText("");
            }
        });

    }


}
