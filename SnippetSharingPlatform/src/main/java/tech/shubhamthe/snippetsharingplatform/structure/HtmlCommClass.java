package tech.shubhamthe.snippetsharingplatform.structure;

public class HtmlCommClass extends HTMLLoader{

    public static String htmlFileLoader(String htmlFileName) {
        return HTMLLoader.htmlFileLoader(htmlFileName);
    }

    public static String confirmMsgEditor(String uuid, String views, String time) {
        return HTMLLoader.confirmMsgEditor(uuid, views, time);
    }

}
