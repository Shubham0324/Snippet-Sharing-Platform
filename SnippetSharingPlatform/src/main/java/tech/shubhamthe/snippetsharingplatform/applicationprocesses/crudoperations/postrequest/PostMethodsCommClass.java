package tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.postrequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests.GetMethods;
import tech.shubhamthe.snippetsharingplatform.exception.ApplicationFailed;

public class PostMethodsCommClass extends PostMethods{
    public ResponseEntity<String> getApiCodeNew( String snippetObject) throws ApplicationFailed {

        return  new PostMethods().getApiCodeNew(snippetObject);
    }
}
