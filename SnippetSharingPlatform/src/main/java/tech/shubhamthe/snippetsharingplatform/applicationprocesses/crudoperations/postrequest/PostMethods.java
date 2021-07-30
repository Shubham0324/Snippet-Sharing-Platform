package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.postrequest;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import tech.shubhamthe.snippetsharingplatform.SnippetSharingPlatformApplication;
import tech.shubhamthe.snippetsharingplatform.structure.Code;
import tech.shubhamthe.snippetsharingplatform.structure.HtmlCommClass;

import java.util.UUID;

public class PostMethods extends SnippetSharingPlatformApplication {

    // Creates and Return New API for the new code snippet created @Post
    // Here uuid is created and is stored in LinkedHashMap called uuid
    protected ResponseEntity<String> getApiCodeNew( String snippetObject){
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
        uuid.put(UUID, code1);
        latest = HtmlCommClass.confirmMsgEditorCommMethod(UUID, String.valueOf(code1.getViewer()), code1.getDestructTime());
        //
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(String.format("{ \"id\" : \"%s\"}", UUID));
    }



    private String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    protected String getApiN(String code, String date) {
        return String.format("\n   {\n" +
                "        \"code\": \"%s\",\n" +
                "        \"date\": \"%s\"\n" +
                "    },", code, date );
    }
}
