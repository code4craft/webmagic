package us.codecraft.webmagic.selector;

/**
 * Object contains regex results.<br>
 * For multi group result extension.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
class RegexResult {

    public static final RegexResult EMPTY_RESULT = new RegexResult();
    private String[] groups;

    public RegexResult() {

    }

    public RegexResult(String[] groups) {
        this.groups = groups;
    }

    public String get(int groupId) {
        if (groups == null) {
            return null;
        }
        return groups[groupId];
    }
}
