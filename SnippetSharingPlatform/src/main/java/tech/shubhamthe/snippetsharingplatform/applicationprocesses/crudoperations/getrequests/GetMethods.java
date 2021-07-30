package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import tech.shubhamthe.snippetsharingplatform.SnippetSharingPlatformApplication;
import tech.shubhamthe.snippetsharingplatform.structure.Code;
import tech.shubhamthe.snippetsharingplatform.structure.HTMLLoader;
import tech.shubhamthe.snippetsharingplatform.structure.HtmlCommClass;

public class GetMethods extends SnippetSharingPlatformApplication {

    // Return HTML with String as Landing Page Code @Get
    protected ResponseEntity<String> landingPage() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "text/html");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(requestedHTMLFiles("index.html"));
    }


    // Calls and userInput.html from where user enters Input form where
    // JSON is returned to getApiCodeNew
    protected ResponseEntity<String> submitNewCode() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(requestedHTMLFiles("userInput.html"));
    }


    public ResponseEntity<String> showConfirmPage() {
        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(latest);//changes
    }


    // Return Code Based on UUID if it is correct else Return 404 Not found @Get
    protected ResponseEntity<String> getNthCode(String uuidForCode) {
        String htmlFileContent = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "text/html");

        if (uuid.containsKey(uuidForCode)) {
            Code code = uuid.get(uuidForCode);
            if (code.checkVisibility()) {

                System.out.println("Here1");
                htmlFileContent = requestedHTMLFiles("viewer.html");
                String divValue = addDiv(code.getCode(), code.getDate(), code.getViewer(), code.getDestructTime(), code.getName(), code.getGenre());
                htmlFileContent = htmlFileContent.replace("{content}", divValue);
                code.setViewer(code.getViewer() - 1);


            } else {
                System.out.println("Here2");
                uuid.remove(uuidForCode);
                return ResponseEntity.badRequest()
                        .headers(httpHeaders)
                        .body(requestedHTMLFiles("404.html"));
            }
        } else {
            System.out.println("Here3");
            return ResponseEntity.badRequest()
                    .headers(httpHeaders)
                    .body(requestedHTMLFiles("404.html"));
        }
        System.out.println("Here4");
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(htmlFileContent);
    }



    private String addDiv(String code, String date, int view, String tillTime, String name, String genre) {
        return String.format("<div class=\"box\">\n" +
                "    <span class=\"load_date\">%s</span>\n" +
                "        <pre class=\"author\">Author - %s</pre>\n" +
                "        <pre class=\"genre\">Genre - %s</pre>\n" +
                "        <pre class=\"visibility-time\">You can view it %d times </pre>\n" +
                "        <pre class=\"visibility-view\">You can view till %s </pre>\n" +
                "    <div class=\"code-box\">\n" +
                "        <pre class=\"code_snippet\">%s</pre>\n" +
                "    </div>\n" +
                "</div>\n\n", date, name, genre, view, tillTime, code);
    }




    private String requestedHTMLFiles(String htmlFileName) {

        String htmlFileContent = HTMLLoader.contentOfBaseHTMLFile;
        String landing = HtmlCommClass.htmlFileLoaderCommMethod(htmlFileName);
        htmlFileContent = htmlFileContent.replace("{replace}",landing );

        return htmlFileContent;
    }

    public ResponseEntity<String> err(){
        return ResponseEntity.badRequest()
                .header("Content-Type", "text/html")
                .body(requestedHTMLFiles("404.html"));
    }

}
