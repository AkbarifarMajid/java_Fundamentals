package model;

public class Track {

    // Alle Attribute
    private String trackId;
    private String titleTrack;
    private String typeFile;
    private int minutes;
    private int seconds;

    // Constructor
    public Track(String trackId, String title, String typeFileName, int minutes, int seconds) {
        this.trackId = trackId;
        this.titleTrack = title;
        this.typeFile = typeFileName;
        setMinutes(minutes);
        setSeconds(seconds);
    }

    //Getter und Setter

    public String getTrackId() {return trackId;}

    public void setTrackId(String trackId) {this.trackId = trackId;}

    public String getTypeFile() {return typeFile;}

    public void setTypeFile(String typeFile) {this.typeFile = typeFile;}

    public String getTitleTrack() {return titleTrack;}

    public void setTitleTrack(String titleTrack) {this.titleTrack = titleTrack;}

    //public double getDuration() {return duration;}

    //public void setDuration(double duration) {this.duration = duration;}
    public void setMinutes(int minutes) {
        if (minutes >= 0) {
            this.minutes = minutes;
        } else {
            System.out.println("Die Minute muss eine positive Zahl sein. Wert wird auf 0 gesetzt.");
            this.minutes = 0;
        }
    }

    public int getMinutes() {return minutes;}

    public int getSeconds() {return seconds;}

    public void setSeconds(int seconds) {
        if (seconds >= 0 && seconds < 60) {
            this.seconds = seconds;
        } else {
            System.out.println("Die Minute muss eine positive Zahl  zwieschen 0 bis 59 sein. Wert wird auf 0 gesetzt.");
            this.seconds = 0;
        }
    }

/*
    // Dauer als Minuten in Double berechnen
    public double GenaueZeitberechnungnMinute() {
        return minutes + (seconds % 60.0);
    }

 */
    
    @Override
    public String toString() {
        //return titleTrack + " --> " + String.format("Time Track = %02d:%02d", minutes, seconds) + " min, Type Track: " + fileName ;

        return  "Name Track --> " + titleTrack + " \n" + String.format("Time --> = %02d:%02d", minutes, seconds) + " min\nType --> " + typeFile;

    }
}
