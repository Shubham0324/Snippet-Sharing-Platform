package tech.shubhamthe.snippetsharingplatform;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*

    For each new code snippet created by user a new Code object is created

 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Code {

    // Following String hold code snippet for particular Code object
    private String code;
  //  private String password;

    // Following LocalDateTime hold code creation date for particular Code object
    private LocalDateTime date = LocalDateTime.now();

    /*
       Visibility parameters with default value = 0
    */
    private int viewer;
    private LocalDateTime destructTime = LocalDateTime.now();

    // TO print date and time in nice pattern (passed)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    // Code is executed when no source code is provided
    Code() {
        // Default value
        this.code = "My Life My Rules";
        // Save time and date of when new Code is created
        this.date = LocalDateTime.now().withNano(0);
    }

    /*
    Constructor is called when send() function pass
    JSON to api/code/new and we call it by passing key
    values new Code(key1, key2, key3)
     */
    Code(String code, int time, int views/* String password*/) {
        this.code = code;
        this.viewer = views;
        this.destructTime = this.date.plusSeconds(time);
     //   this.password = password;
    }

    //GETTERS
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

    // SETTERS
    public void setViewer(int viewer) {
        this.viewer = viewer;
    }

    /*
    Check for condition of visibility of the code snippet
    return the '!' of result of condition the function is
    checking
     */
    public boolean checkVisibility() {
        LocalDateTime now = LocalDateTime.now();
        return (this.viewer != 0 && !now.isAfter(this.destructTime));
    }

}