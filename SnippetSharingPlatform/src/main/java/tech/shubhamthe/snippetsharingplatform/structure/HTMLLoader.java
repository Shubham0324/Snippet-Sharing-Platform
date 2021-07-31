package tech.shubhamthe.snippetsharingplatform.structure;

import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/**
* This class will facilitate application with all loading and
* required changes to be  processed in html file so that correct information
* can be displayed to the user
*
* Class will not be directly accessible to outside package classes for
* security reasons all interactions will be done with the help of
* HtmlCommClass created in same package
*
* */

class HTMLLoader {

    // This string holds content of base.html in String format that will act  as template for other
    // web pages. Done this to save processing time.
    public static final String  contentOfBaseHTMLFile = HTMLLoader.htmlFileLoader("base.html");

    // Method load and return html content of file name provided as parameter from template directory
    protected static String htmlFileLoader(String htmlFileName) {

        String htmlFileContent = "";
        try {
            File resource = new ClassPathResource("template/" + htmlFileName).getFile();
            htmlFileContent = new String(Files.readAllBytes(resource.toPath()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlFileContent;
    }


    // Used to process and return content of confirm.html to show user corresponding UUID
    // for the submitted snippet and display number of times code can be  seen and till
    // what time.
    protected static String confirmMsgEditor(String uuid, String views, String time) {
        String finalHTMLFile = contentOfBaseHTMLFile;
        String confirmHTMLFile = htmlFileLoader("confirm.html");
        finalHTMLFile = finalHTMLFile.replace("{replace}",confirmHTMLFile);
        finalHTMLFile = finalHTMLFile.replace("{uuid}", uuid);
        finalHTMLFile = finalHTMLFile.replace("{times}", views);
        finalHTMLFile = finalHTMLFile.replace("{seconds}", time);
        return finalHTMLFile;
    }
}
