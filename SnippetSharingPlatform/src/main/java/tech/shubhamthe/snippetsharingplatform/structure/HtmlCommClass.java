package tech.shubhamthe.snippetsharingplatform.structure;

/**
* Class was created to act as a communicating bridge between HTMLLoader class
* and outside classes. It extends HTMLLoader class and suitable methods  are
* created/provided for the same purpose.
*
* */

public class HtmlCommClass extends HTMLLoader{

    public static String htmlFileLoader(String htmlFileName) {
        return HTMLLoader.htmlFileLoader(htmlFileName);
    }

    public static String confirmMsgEditor(String uuid, String views, String time) {
        return HTMLLoader.confirmMsgEditor(uuid, views, time);
    }

}
