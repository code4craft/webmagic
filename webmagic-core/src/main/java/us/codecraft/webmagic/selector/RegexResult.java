package us.codecraft.webmagic.selector;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 上午7:39
 */
class RegexResult {

    private String[] groups;

    public static final RegexResult EMPTY_RESULT = new RegexResult();

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
