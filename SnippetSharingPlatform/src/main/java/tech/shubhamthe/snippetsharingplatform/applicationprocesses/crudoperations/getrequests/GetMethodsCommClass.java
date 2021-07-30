package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

public class GetMethodsCommClass extends GetMethods {

    public  ResponseEntity<String> landingPage() {
            return  new GetMethods().landingPage();
    }

    public ResponseEntity<String> submitNewCode() {
        return new GetMethods().submitNewCode();
    }

    public ResponseEntity<String> showConfirmPage() {
        return new GetMethods().showConfirmPage();
    }

    public ResponseEntity<String> getNthCode(String uuidForCode){
        return new GetMethods().getNthCode(uuidForCode);
    }
}
