package tech.shubhamthe.snippetsharingplatform.structure;

public class HtmlCommClass extends HTMLLoader{

    public static String htmlFileLoaderCommMethod(String htmlFileName) {
        return HTMLLoader.htmlFileLoader(htmlFileName);
    }

    public static String confirmMsgEditorCommMethod(String uuid, String views, String time) {
        return HTMLLoader.confirmMsgEditor(uuid, views, time);
    }

}
