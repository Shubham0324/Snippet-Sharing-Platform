package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.postrequest;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import tech.shubhamthe.snippetsharingplatform.SnippetSharingPlatformApplication;
import tech.shubhamthe.snippetsharingplatform.exception.ApplicationFailed;
import tech.shubhamthe.snippetsharingplatform.structure.Code;
import tech.shubhamthe.snippetsharingplatform.structure.HtmlCommClass;

import java.io.*;
import java.util.UUID;

/**
 * Class to handle all post requests
 **/
class PostMethods extends SnippetSharingPlatformApplication implements Serializable {

    // Creates and Return New API for the new code snippet created @Post
    // Here uuid is created and is stored in LinkedHashMap called uuid
    protected ResponseEntity<String> getApiCodeNew( String snippetObject) throws ApplicationFailed {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        //Class code constructor is called with arguments recieved via post request
        // generated when user submit new snippet form
        Code code1 = new Code(
                new JSONObject(snippetObject).getString("name").trim(),
                new JSONObject(snippetObject).getString("email").trim(),
                new JSONObject(snippetObject).getString("pass").trim(),
                new JSONObject(snippetObject).getString("code").trim(),
                Integer.parseInt(new JSONObject(snippetObject).getString("time").trim()),
                Integer.parseInt(new JSONObject(snippetObject).getString("views").trim()),
                new JSONObject(snippetObject).getString("type").trim());


        // UUID Generation
        String UUID = generateUUID();
        uuid.put(UUID, code1);
        latest = HtmlCommClass.confirmMsgEditor(UUID, String.valueOf(code1.getViewer()), code1.getDestructTime());

        saveState();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(String.format("{ \"id\" : \"%s\"}", UUID));
    }


    private String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    // Whenever uuid is updated a curren state of uuid is saved for storage and future purpose
    private void saveState() throws ApplicationFailed {
        try{
            FileOutputStream fileOut = new FileOutputStream("database/database.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(uuid);
            objectOut.close();

        }catch (IOException e){
            System.out.println();
        }
    }
}
