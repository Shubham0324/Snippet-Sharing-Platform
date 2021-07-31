package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests.apirequests;

import org.springframework.http.ResponseEntity;
import tech.shubhamthe.snippetsharingplatform.SnippetSharingPlatformApplication;
import tech.shubhamthe.snippetsharingplatform.structure.Code;

public class GetApiMethods extends SnippetSharingPlatformApplication {

    protected ResponseEntity<String> getApiCodeNth(String uuidRequested) {

        if(uuid.containsKey(uuidRequested)){
            Code snip = uuid.get(uuidRequested);
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(getApiN(snip.getName(), snip.getGenre(), snip.getCode(), snip.getDate()));
        }
        return ResponseEntity.badRequest()
                .header("Content-Type", "text/html")
                .body("Cannot Process Request");
    }


    protected ResponseEntity<String> getApiRandomLatest(){
        if(uuid.size() == 0) {
            return ResponseEntity.badRequest()
                    .header("Content-Type", "text/html")
                    .body("We currently does not have Any Public Code Available Cannot Process Request");
        }

        StringBuilder returnable = new StringBuilder("");
        int itr = 0;
        for(Code snip : uuid.values()) {
            if (itr < 10) {
                returnable.append(getApiN(snip.getName(), snip.getGenre(), snip.getCode(), snip.getDate()));
                itr++;
            } else {
                break;
            }
        }

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(returnable.toString());
    }






    private String getApiN(String authorName, String genre, String snippet, String date) {
        return String.format("\n   {\n" +
                "        \"author\": \"%s\",\n" +
                "        \"genre\": \"%s\",\n" +
                "        \"snippet\": \"%s\",\n" +
                "        \"date\": \"%s\"\n" +
                "    }\n", authorName, genre, snippet, date );
    }
}
