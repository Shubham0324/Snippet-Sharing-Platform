package tech.shubhamthe.snippetsharingplatform;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.LinkedHashMap;
import java.util.UUID;

@Controller
public class CodeViewer {
    LinkedHashMap<String, Code> uuid = new LinkedHashMap<>();
    static String latest;

    // Return HTML with String as Landing Page Code @Get
    protected ResponseEntity<String> landingPage() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "text/html");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(requestedHTMLFiles("index.html"));
    }


    // Return Code Based on UUID if it is correct else Return 404 Not found @Get
    protected ResponseEntity<String> getNthCode(@PathVariable String uuidForCode) {
        String htmlFileContent = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "text/html");

        if (uuid.containsKey(uuidForCode)) {
            Code code = uuid.get(uuidForCode);
            if (code.checkVisibility()) {


                htmlFileContent = requestedHTMLFiles("viewer.html");
                String divValue = addDiv(code.getCode(), code.getDate(), code.getViewer(), code.getDestructTime());
                htmlFileContent = htmlFileContent.replace("{content}", divValue);
                code.setViewer(code.getViewer() - 1);


            } else {
                uuid.remove(uuidForCode);
                return ResponseEntity.badRequest()
                        .headers(httpHeaders)
                        .body(requestedHTMLFiles("404.html"));
            }
        } else {
            return ResponseEntity.badRequest()
                    .headers(httpHeaders)
                    .body(requestedHTMLFiles("404.html"));
        }
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(htmlFileContent);
    }



    // Creates and Return New API for the new code snippet created @Post
    // Here uuid is created and is stored in LinkedHashMap called uuid
    protected ResponseEntity<String> getApiCodeNew(@RequestBody String snippetObject){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        Code code1 = new Code(
                new JSONObject(snippetObject).getString("name"),
                new JSONObject(snippetObject).getString("email"),
                new JSONObject(snippetObject).getString("pass"),
                new JSONObject(snippetObject).getString("code"),
                Integer.parseInt(new JSONObject(snippetObject).getString("time")),
                Integer.parseInt(new JSONObject(snippetObject).getString("views")),
                new JSONObject(snippetObject).getString("type"));


        // UUID Generation
        String UUID = generateUUID();
        uuid.put(UUID, code1); //USED Predefined for testing purpose
        latest = new HTMLLoader()
                .htmlFileEditor(UUID, String.valueOf(code1.getViewer()), code1.getDestructTime());
        //
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(String.format("{ \"id\" : \"%s\"}", UUID));
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


    // After Responding with status : 200 by ApiCodeNew().
    // This method is called from userInput.html with the help of function showConfirmPage()
    @GetMapping(value = "/code/confirm.html")
    public ResponseEntity<String> showConfirmPage() {
        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(latest);
    }

    private String addDiv(String code, String date, int view, String tillTime) {
        return String.format("<div class=\"box\">\n" +
                "    <span class=\"load_date\">%s</span>\n" +
                "        <pre class=\"visibility-time\">You can view it %d times </pre>\n" +
                "        <pre class=\"visibility-view\">You can view till %s </pre>\n" +
                "    <div class=\"code-box\">\n" +
                "        <pre class=\"code_snippet\">%s</pre>\n" +
                "    </div>\n" +
                "</div>\n\n", date ,view,tillTime, code);
    }

    private String getApiN(String code, String date) {
        return String.format("\n   {\n" +
                "        \"code\": \"%s\",\n" +
                "        \"date\": \"%s\"\n" +
                "    },", code, date );
    }

    private String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @GetMapping(value = "/error" , produces = "text/html")
    public ResponseEntity<String> err(){
        return ResponseEntity.badRequest()
                .header("Content-Type", "text/html")
                .body(requestedHTMLFiles("404.html"));
    }

    private String requestedHTMLFiles(String htmlFileName) {

        String htmlFileContent = HTMLLoader.contentOfBaseHTMLFile;
        String landing = HTMLLoader.htmlFileLoader(htmlFileName);
        htmlFileContent = htmlFileContent.replace("{replace}",landing );

        return htmlFileContent;
    }

}

