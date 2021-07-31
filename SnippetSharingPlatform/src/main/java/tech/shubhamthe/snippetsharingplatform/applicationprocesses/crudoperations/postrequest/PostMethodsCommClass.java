package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.postrequest;

import org.springframework.http.ResponseEntity;
import tech.shubhamthe.snippetsharingplatform.exception.ApplicationFailed;
/**
 * Communicating class to handle all communication between  GetMethods class and functions calling it
 * */

public class PostMethodsCommClass extends PostMethods{
    public ResponseEntity<String> getApiCodeNew( String snippetObject) throws ApplicationFailed {

        return  new PostMethods().getApiCodeNew(snippetObject);
    }
}
