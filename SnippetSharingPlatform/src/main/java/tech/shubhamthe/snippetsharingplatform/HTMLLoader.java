package tech.shubhamthe.snippetsharingplatform;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class HTMLLoader {
    protected String htmlFileLoader(String htmlFileName) {
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
        String htmlFileContent = htmlFileLoader("confirm.html");
        htmlFileContent = htmlFileContent.replace("{uuid}", uuid);
        htmlFileContent = htmlFileContent.replace("{uuid}", uuid);
        htmlFileContent = htmlFileContent.replace("{times}", views);
        htmlFileContent = htmlFileContent.replace("{seconds}", time);
        return htmlFileContent;
    }
}
