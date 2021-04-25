package com.snippetsharingplatform.SnipetSharingPlatform;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class PlatformController {
    ArrayList<Code> indexGet = new ArrayList<>();
    LinkedHashMap<String, Code> uuid = new LinkedHashMap<>();
    CodeViewer viewer = new CodeViewer();



    // Listen @localhost:8889 for the landing page call viewer.landingPage which returns ResponseEntity<T>
    @GetMapping(value = "/" , produces = "text/html")
    public ResponseEntity<String> landingPage() {
       return viewer.landingPage();
    }


    // Listen @localhost:8889/code/uuid-uuid-.. for the finding code snippet based on UUID. It cals
    // viewer.getNthCode which returns ResponseEntity<T>
    @GetMapping(value = "/code/{uuidForCode}", produces = "text/html")
    public ResponseEntity<String> getNthCode(@PathVariable String uuidForCode) {
        return viewer.getNthCode(uuidForCode);
    }


    // Listen @localhost:8889/api/code/new which is called when send() func is triggered in UserInput.html
    // It calls viewer.getApiCodeNew which returns ResponseEntity<T>
    @PostMapping(value = "api/code/new", consumes = "application/json")
    public ResponseEntity<String> getApiCodeNew(@RequestBody String code){
        return viewer.getApiCodeNew(code);
    }

    // Helps User to create and store New code snippet
    @GetMapping(value = "/code/new", produces = "text/html")
    public ResponseEntity<String> submitNewCode() {
        return viewer.submitNewCode();
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ResponseEntity<String> getLatest() {
        if(uuid.size() == 0) {
            return ResponseEntity.badRequest()
                    .header("Content-Type", "text/html")
                    .body("We currently does not have Any Public Code");
        }
        StringBuilder returnable = new StringBuilder("");
        if (indexGet.size() >= 10) {
            for(int i = indexGet.size() - 1; i >= indexGet.size() - 10; i--) {
                //  returnable.append(addDiv((indexGet.get(i)).getCode(), (indexGet.get(i)).getDate()));
            }
        }
        else {
            for(int i = indexGet.size() - 1; i >= 0; i--) {
                System.out.println(i);
                // returnable.append(addDiv((indexGet.get(i)).getCode(), (indexGet.get(i)).getDate()));
            }
        }

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(returnable.toString());
    }



    @GetMapping(value = "/api/code/{index}")
    public ResponseEntity<Code> getApiCodeNth(@PathVariable int index) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        if(indexGet.size() + 1 <= index ) {
//                        return "d";
        }/*
        return String.format("{\n     \"code\" : \"%s\"" +
                        "\n     \"date\" : \"%s\"\n}",
                        (indexGet.get(index-1)).getCode(), (indexGet.get(index-1)).getDate().toString());*/
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(indexGet.get(index-1));
    }


    @GetMapping(value = "/api/code/latest")
    public ResponseEntity<List<Code>> getApiCodeLatest(){
        StringBuilder returnable = new StringBuilder("[");
        List<Code> codes = new ArrayList<>();

        if (indexGet.size() >= 10) {
            for(int i = indexGet.size() - 1; i >= indexGet.size() - 10; i--) {
                returnable.append(getApiN(indexGet.get(i).getCode(), indexGet.get(i).getDate()));
                codes.add(indexGet.get(i));
            }
        }
        else {
            for(int i = indexGet.size() - 1; i >= 0; i--) {
                returnable.append(getApiN(indexGet.get(i).getCode(), indexGet.get(i).getDate()));
                codes.add(indexGet.get(i));

            }
        }
        returnable.replace(returnable.lastIndexOf(","), returnable.lastIndexOf(",")+1, "");
        returnable.append("\n]");

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(codes);
    }





    private String getApiN(String code, String date) {
        return String.format("\n   {\n" +
                "        \"code\": \"%s\",\n" +
                "        \"date\": \"%s\"\n" +
                "    },", code, date );
    }
}
