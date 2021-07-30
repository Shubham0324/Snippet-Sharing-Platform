package tech.shubhamthe.snippetsharingplatform;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HTMLLoader {
    public static final String  contentOfBaseHTMLFile = HTMLLoader.htmlFileLoader("base.html");

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


    protected String htmlFileEditor(String uuid, String views, String time) {
        String finalHTMLFile = contentOfBaseHTMLFile;
        String confirmHTMLFile = htmlFileLoader("confirm.html");
        finalHTMLFile = finalHTMLFile.replace("{replace}",confirmHTMLFile);
        finalHTMLFile = finalHTMLFile.replace("{uuid}", uuid);
        finalHTMLFile = finalHTMLFile.replace("{times}", views);
        finalHTMLFile = finalHTMLFile.replace("{seconds}", time);
        return finalHTMLFile;
    }
}
