package tech.shubhamthe.snippetsharingplatform.applicationprocesses;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.shubhamthe.snippetsharingplatform.SnippetSharingPlatformApplication;
import tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests.GetMethodsCommClass;
import tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests.apirequests.GetApiMethodsCommClass;
import tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.postrequest.PostMethodsCommClass;
import tech.shubhamthe.snippetsharingplatform.exception.ApplicationFailed;



@Controller
@EnableAutoConfiguration
public class PlatformController extends SnippetSharingPlatformApplication {


    static GetMethodsCommClass viewer = new GetMethodsCommClass();
    static PostMethodsCommClass apiWorker = new PostMethodsCommClass();
    static GetApiMethodsCommClass apiRequester = new GetApiMethodsCommClass();



    // Listen @localhost:8889 for the landing page call viewer.landingPage which returns ResponseEntity<T>
    @GetMapping(value = "/" , produces = "text/html")
    public ResponseEntity<String> landingPage() {
       return viewer.landingPage();
    }

    // Helps User to create and store New code snippet
    @GetMapping(value = "/code/new", produces = "text/html")
    public ResponseEntity<String> submitNewCode() {
        return viewer.submitNewCode();
    }


    // After Responding with status : 200 by ApiCodeNew().
    // This method is called from userInput.html with the help of function showConfirmPage()
    @GetMapping(value = "/code/confirm.html")
    public ResponseEntity<String> showConfirmPage() {
        return viewer.showConfirmPage();
    }



    // Listen @localhost:8889/code/uuid-uuid-.. for the finding code snippet based on UUID. It cals
    // viewer.getNthCode which returns ResponseEntity<T>
    @GetMapping(value = "/code/{uuidForCode}", produces = "text/html")
    public ResponseEntity<String> getNthCode(@PathVariable String uuidForCode) {
        return viewer.getNthCode(uuidForCode);
    }


    @GetMapping(value = "/code/latest", produces = "text/html")
    public ResponseEntity<String> getLatest() {
        return viewer.getRandomLatest();
    }



    // Listen @localhost:8889/api/code/new which is called when send() func is triggered in UserInput.html
    // It calls viewer.getApiCodeNew which returns ResponseEntity<T>
    @PostMapping(value = "api/code/new", consumes = "application/json")
    public ResponseEntity<String> getApiCodeNew(@RequestBody String code) throws ApplicationFailed {

        return apiWorker.getApiCodeNew(code);
    }



    @GetMapping(value = "/api/code/{uuid}")
    public ResponseEntity<String> getApiCodeNth(@PathVariable String uuid) {

        return apiRequester.getApiCodeNth(uuid);
    }


    @GetMapping(value = "/api/code/latest")
    public ResponseEntity<String> getApiCodeLatest(){

        return apiRequester.getApiRandomLatest();
    }


    @GetMapping(value = "/code/error", produces = "text/html")
    public ResponseEntity<String> getNthCode() {
        return viewer.err();
    }


}
