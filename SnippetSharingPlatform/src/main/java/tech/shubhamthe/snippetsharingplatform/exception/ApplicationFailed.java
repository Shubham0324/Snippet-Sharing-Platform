package tech.shubhamthe.snippetsharingplatform.exception;

import org.springframework.http.ResponseEntity;
import tech.shubhamthe.snippetsharingplatform.structure.HTMLLoader;
import tech.shubhamthe.snippetsharingplatform.structure.HtmlCommClass;

public class ApplicationFailed extends Exception{

    public ApplicationFailed(){
        Err();
    }


    protected ResponseEntity<String> Err(){
        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(requestedHTMLFiles("Error.html"));


    }
    private String requestedHTMLFiles(String htmlFileName) {


        String htmlFileContent = HTMLLoader.contentOfBaseHTMLFile;
        String landing = HtmlCommClass.htmlFileLoader(htmlFileName);
        htmlFileContent = htmlFileContent.replace("{replace}",landing );

        return htmlFileContent;
    }
}
