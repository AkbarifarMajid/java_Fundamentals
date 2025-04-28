package model;

import java.util.ArrayList;
import java.util.List;

public class Verein {
    private String name;
    private String adresse;
    private List<Mannschaft> mannschaften = new ArrayList<>();

    public Verein(String name, String adresse) {
        this.name = name;
        this.adresse = adresse;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAdresse() {return adresse;}

    public void setAdresse(String adresse) {this.adresse = adresse;}

    public void setMannschaften(List<Mannschaft> mannschaften) {this.mannschaften = mannschaften;}

    public List<Mannschaft> getMannschaften() {return mannschaften;}

    //Add Team
    public void addMannschaft(Mannschaft team) {mannschaften.add(team);}

    //Remove Team
    public boolean removeMannschaftByName(String name) {
        boolean entfernt = mannschaften.removeIf(m -> m.getName().trim().equalsIgnoreCase(name.trim()));
        if (entfernt) {
            System.out.println("Mannschaft gelöscht: " + name);
        } else {
            System.out.println("Keine Mannschaft mit dem Namen '" + name + "' gefunden.");
        }
        return entfernt;
    }

    @Override
    public String toString() {
        String result = "Verein: " + name + " (" + adresse + ")\n";
        result += "Teams:\n";
        for (Mannschaft m : mannschaften) {
            result += "* " + m.getName() + "\n";
        }
        return result;
    }

    }

