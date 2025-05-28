package gui.dashboard;

import gui.Main_Form;
import gui.fahrzeug.Fahrzeuge_Haupt_Panel;
import gui.mitarbeiter.Mitarbeiter_Haupt_Panel;
import gui.zuweisung.Zuweisung_Haupt_Panel;
import gui.zuweisung.Zuweisung_Mitarbeiter_Panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import service.*;

public class DashboardPanel extends JPanel {

    private Main_Form mainForm;

    // geht ein object von Main_Form , damm kann von Metod zeigePanel() benutzen
    public DashboardPanel(Main_Form mainForm) {
        this.mainForm = mainForm;

        setLayout(new GridLayout(2, 2, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        setBackground(new Color(230, 240, 250));

        Font fontTitle = new Font("SansSerif", Font.BOLD, 16);
        Font fontValue = new Font("SansSerif", Font.PLAIN, 20);
        Color colorTitle = new Color(0, 51, 102);
        Color colorValue = Color.DARK_GRAY;
        Color blockBackground = Color.WHITE;
        Color blockBorder = new Color(180, 180, 255);

        String[] beschriftungen = {
                "Gesamt Fahrzeuge", "Gesamt Mitarbeiter", "Gesamt Zuweisungen", "Kilometer Gesamt"
        };
        String[] icons = {
                "\uD83D\uDE97", "\uD83D\uDC68\u200D", "\uD83D\uDCCB", "\uD83D\uDCCF"
        };

        JPanel[] zielPanels = {
                new Fahrzeuge_Haupt_Panel(),
                new Mitarbeiter_Haupt_Panel(),
                new Zuweisung_Mitarbeiter_Panel(),
                new Zuweisung_Haupt_Panel()
        };

        for (int i = 0; i < beschriftungen.length; i++) {
            JPanel block = new JPanel(new BorderLayout());
            block.setBackground(blockBackground);
            block.setBorder(BorderFactory.createLineBorder(blockBorder, 2));

            JLabel labelTitle = new JLabel(icons[i] + " " + beschriftungen[i], SwingConstants.CENTER);
            labelTitle.setFont(fontTitle);
            labelTitle.setForeground(colorTitle);

            String wert = switch (i) {
                case 0 -> String.valueOf(FahrzeugService.getAnzahlFahrzeuge());
                case 1 -> String.valueOf(MitarbeiterService.getAnzahlMitarbeiter());
                case 2 -> String.valueOf(ZuweisungService.getAnzahlZuweisungen());
                case 3 -> String.valueOf(KilometerService.getGesamtKilometer()) + " km";
                default -> "-";
            };

            JLabel labelValue = new JLabel(wert, SwingConstants.CENTER);

            labelValue.setFont(fontValue);
            labelValue.setForeground(colorValue);

            block.add(labelTitle, BorderLayout.NORTH);
            block.add(labelValue, BorderLayout.CENTER);

            int finalI = i;
            block.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mainForm.zeigePanel(zielPanels[finalI], beschriftungen[finalI]);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    block.setBackground(new Color(210, 220, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    block.setBackground(blockBackground);
                }
            });

            add(block);
        }
    }
}
