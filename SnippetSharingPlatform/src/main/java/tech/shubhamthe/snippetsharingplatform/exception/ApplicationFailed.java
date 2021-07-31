package tech.shubhamthe.snippetsharingplatform.exception;

import org.springframework.http.ResponseEntity;
import tech.shubhamthe.snippetsharingplatform.structure.HtmlCommClass;

/**
* Custom Exception class
* Exception occur when either load or save process on database.txt fails
*  */

public class ApplicationFailed extends Exception{


    public ApplicationFailed(){
        Err();
    }

    //Return 404 and loads Error.html
    protected ResponseEntity<String> Err(){
        return ResponseEntity.badRequest()
                .header("Content-Type", "text/html")
                .body(requestedHTMLFiles("Error.html"));


    }

    private String requestedHTMLFiles(String htmlFileName) {

        String htmlFileContent = HtmlCommClass.contentOfBaseHTMLFile;
        String landing = HtmlCommClass.htmlFileLoader(htmlFileName);
        htmlFileContent = htmlFileContent.replace("{replace}",landing );

        return htmlFileContent;
    }
}
