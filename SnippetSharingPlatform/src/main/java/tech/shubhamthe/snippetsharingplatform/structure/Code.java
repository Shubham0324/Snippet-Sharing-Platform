package tech.shubhamthe.snippetsharingplatform.structure;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** For each new snippet created by user a new Code object is created
*  and it acts as a skeleton for the snippet
* */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Code {

    //Information of Authors of respective snippet are saved in following variables
    final private String name;
    final private String email;
    final private String pass;

    // Following String hold code snippet for particular Code object
    private String code;

    // Following LocalDateTime hold code creation date for particular Code object
    final private LocalDateTime date = LocalDateTime.now();


   //Visibility parameters with default value = 0
    private int viewer;
    private LocalDateTime destructTime;

    //Genre of snippet
    private String genre;

    // TO print date and time in nice pattern (passed)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    /*
    * Constructor is called when send() function pass JSON to api/code/new, and we call it by passing required number of
    * arguments values new Code(...arg)
    * */
     public Code(String name, String email, String pass, String code, int time, int views, String genre ) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.code = code;
        this.viewer = views;
        // destructTime = time snippet was created + seconds snippet should available as entered by user.
        this.destructTime = this.date.plusSeconds(time);
        this.genre = genre;
    }

    // GETTER Functions
    public String getName() {
        return name;
    }
    public String getDate() {
        return this.date.format(FORMATTER);
    }
    public String getCode() {
        return this.code;
    }
    public int getViewer() {
        return viewer;
    }
    public String getDestructTime() {
        return  this.destructTime.format(FORMATTER);
    }
    public String getGenre() {
        return genre;
    }

    // SETTER Function is called each time snippet is viewed to decrement view count.
    public void setViewer(int viewer) {
        this.viewer = viewer;
    }

    /*
    * Check for condition of visibility of the code snippet return result of condition the function is checking
    * */
    public boolean checkVisibility() {
        LocalDateTime now = LocalDateTime.now();
        return (this.viewer != 0 && !now.isAfter(this.destructTime));
    }

}