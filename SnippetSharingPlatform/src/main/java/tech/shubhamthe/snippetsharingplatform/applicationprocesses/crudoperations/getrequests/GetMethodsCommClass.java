package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests;

import org.springframework.http.ResponseEntity;

/**
 * Communicating class to handle all communication between  GetMethods class and functions calling it
 * */

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

    public ResponseEntity<String> getRandomLatest() {
        return new GetMethods().getRandomLatest();
    }

    public ResponseEntity<String> err(){
        return new GetMethods().err();
    }
}
