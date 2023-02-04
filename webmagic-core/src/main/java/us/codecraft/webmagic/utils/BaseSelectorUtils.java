package us.codecraft.webmagic.utils;

/**
 * @author hooy
 */
public class BaseSelectorUtils {

    /**
     * Jsoup/HtmlCleaner could not parse "tr" or "td" tag directly
     * https://stackoverflow.com/questions/63607740/jsoup-couldnt-parse-tr-tag
     *
     * @param text - the html string
     * @return text
     */
    public static String preParse(String text) {
        if (((text.startsWith("<tr>") || text.startsWith("<tr ")) && text.endsWith("</tr>"))
                || ((text.startsWith("<td>") || text.startsWith("<td ")) && text.endsWith("</td>"))) {
            text = "<table>" + text + "</table>";
        }
        return text;
    }

}
