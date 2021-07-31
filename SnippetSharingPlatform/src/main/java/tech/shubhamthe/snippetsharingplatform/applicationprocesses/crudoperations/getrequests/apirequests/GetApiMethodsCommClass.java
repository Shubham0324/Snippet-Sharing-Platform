package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests.apirequests;

import org.springframework.http.ResponseEntity;

/**
 * Communicating class to handle all communication between  GetMethods class and functions calling it
 * */
public class GetApiMethodsCommClass extends  GetApiMethods {


    public ResponseEntity<String> getApiCodeNth(String uuidRequested) {
        return  new GetApiMethods().getApiCodeNth(uuidRequested);
    }

    public ResponseEntity<String> getApiRandomLatest() {
        return new GetApiMethods().getApiRandomLatest();
    }

}
