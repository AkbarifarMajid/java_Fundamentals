package gui;

import javax.swing.*;
import java.awt.*;

import gui.dashboard.DashboardPanel;
import gui.fahrzeug.Fahrzeuge_Haupt_Panel;
import gui.mitarbeiter.Mitarbeiter_Haupt_Panel;
import gui.zuweisung.Zuweisung_Haupt_Panel;
import gui.zuweisung.Zuweisung_Mitarbeiter_Panel;
import util.*;
import java.awt.event.*;

//Erstellen einer allgemeinen Desktop-Software Vorlage
public class Main_Form extends JFrame {

    private JPanel haupt_Panel_Main;
    private JLabel weg_Label;
    //private JLabel logoLabel;

    public Main_Form() {
        setTitle("Fuhrpark-SZF");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Warnung beim Schließen
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                programm_schliessen();
            }
        });

        // Menüleiste erstellen
        JMenuBar my_Menu = new JMenuBar();

        JMenu menu_Dashboard = new JMenu("Dashboard");
        IconLoader.style_UnicCode_Menu(menu_Dashboard, "\uD83C\uDFE0");
        menu_Dashboard.addMenuListener(new Mein_Menue_Listener(() -> zeigePanel(new DashboardPanel(this), "Dashboard")));



        // Fahrzeuge Menü
        JMenu menu_Fahrzeuge = new JMenu("Fahrzeuge");
        IconLoader.style_UnicCode_Menu(menu_Fahrzeuge, "\uD83D\uDE97");
        menu_Fahrzeuge.addMenuListener(new Mein_Menue_Listener(() -> zeigePanel(new Fahrzeuge_Haupt_Panel(), "Fahrzeuge")));

        // Mitarbeiter Menü
        JMenu menu_Mitarbeiter = new JMenu("Mitarbeiter");
        IconLoader.style_UnicCode_Menu(menu_Mitarbeiter, "\uD83D\uDC68");
        menu_Mitarbeiter.addMenuListener(new Mein_Menue_Listener(() -> zeigePanel(new Mitarbeiter_Haupt_Panel(), "Mitarbeiter")));

        // Zuweisung Menü
        JMenu menu_Zuweisung = new JMenu("Nutzung");
        IconLoader.style_UnicCode_Menu(menu_Zuweisung, "\u2696");
        menu_Zuweisung.addMenuListener(new Mein_Menue_Listener(() -> zeigePanel(new Zuweisung_Haupt_Panel(), "Fahrzeugnutzung ")));

        // Zuweisung Menü
        JMenu menu_Fahrz_Zuweisung = new JMenu("Zuweisung ");
        IconLoader.style_UnicCode_Menu(menu_Fahrz_Zuweisung, "\u21D2");
        menu_Fahrz_Zuweisung.addMenuListener(new Mein_Menue_Listener(() -> zeigePanel(new Zuweisung_Mitarbeiter_Panel(), "Fahrzeugzuweisung")));

        // Beenden Menü
        JMenu menu_Beenden = new JMenu("Beenden");
        IconLoader.style_UnicCode_Menu(menu_Beenden, "\u274C");
        menu_Beenden.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                programm_schliessen();
            }
        });

        my_Menu.add(menu_Dashboard);
        my_Menu.add(menu_Fahrzeuge);
        my_Menu.add(menu_Mitarbeiter);
        my_Menu.add(menu_Zuweisung);
        my_Menu.add(menu_Fahrz_Zuweisung);
        my_Menu.add(menu_Beenden);
        setJMenuBar(my_Menu);


        // Breadcrumb oben (weg in Programm)
        weg_Label = new JLabel("\uD83C\uDFE0 Startseite", JLabel.LEFT);
        weg_Label.setFont(new Font("Roboto", Font.PLAIN, 16));
        weg_Label.setForeground(new Color(0, 102, 204));

        JPanel breadcrumbPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        breadcrumbPanel.setBackground(new Color(245, 245, 245));
        breadcrumbPanel.add(weg_Label);
        add(breadcrumbPanel, BorderLayout.NORTH);



        // Hauptpanel zuerst initialisieren
        haupt_Panel_Main = new JPanel(new BorderLayout());
        haupt_Panel_Main.setBackground(Color.LIGHT_GRAY);
        add(haupt_Panel_Main, BorderLayout.CENTER);

        // Jetzt Welcome-Panel hinzufügen
        JPanel welcome = new JPanel(new BorderLayout());
        welcome.setBackground(new Color(230, 240, 250));

        JLabel labelTitle = new JLabel("Willkommen zur Fuhrpark-Verwaltung", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        labelTitle.setForeground(new Color(0, 51, 102));

        JLabel labelSubtitle = new JLabel("Testversion der Fuhrpark Applikation", SwingConstants.CENTER);
        labelSubtitle.setFont(new Font("Verdana", Font.ITALIC, 18));
        labelSubtitle.setForeground(new Color(80, 80, 80));

        welcome.add(labelTitle, BorderLayout.NORTH);
        welcome.add(labelSubtitle, BorderLayout.CENTER);

        haupt_Panel_Main.add(welcome, BorderLayout.CENTER);


        // Footer unten
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel kontaktLabel = new JLabel(" Fohnsdorf/ PL1 8073 | 0043 111 222 333");
        kontaktLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        kontaktLabel.setForeground(Color.BLUE);

        JLabel sloganLabel = new JLabel("Für jeden Kilometer bestens organisiert");
        sloganLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
        sloganLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        sloganLabel.setForeground(Color.BLUE);

        footerPanel.add(kontaktLabel, BorderLayout.WEST);
        footerPanel.add(sloganLabel, BorderLayout.EAST);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void zeigePanel(JPanel neuesPanel, String pfad) {
        haupt_Panel_Main.removeAll();
        haupt_Panel_Main.add(neuesPanel, BorderLayout.CENTER);
        weg_Label.setText("\uD83C\uDFE0 Startseite / " + pfad);
        haupt_Panel_Main.revalidate();
        haupt_Panel_Main.repaint();
    }

    public void programm_schliessen() {
        int jaNein = JOptionPane.showConfirmDialog(this, "Schließen Sie das Programm?", "Beenden", JOptionPane.YES_NO_OPTION);
        if (jaNein == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Innen Class So führen beim Klicken auf Menüelemente ein bestimmtes Runnable aus
    private static class Mein_Menue_Listener implements javax.swing.event.MenuListener {
        Runnable auszufuehren;

        public Mein_Menue_Listener(Runnable r) {
            this.auszufuehren = r;
        }

        public void menuSelected(javax.swing.event.MenuEvent e) {
            auszufuehren.run();
        }

        public void menuDeselected(javax.swing.event.MenuEvent e) {}
        public void menuCanceled(javax.swing.event.MenuEvent e) {}
    }


}
