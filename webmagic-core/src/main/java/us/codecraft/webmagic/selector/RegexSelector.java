package us.codecraft.webmagic.selector;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午7:09
 */
public class RegexSelector implements Selector {

    private String regexStr;

    private Pattern regex;

    public RegexSelector(String regexStr) {
        if (StringUtils.isBlank(regexStr)){
            throw new IllegalArgumentException("regex must not be empty");
        }
        if (!StringUtils.contains(regexStr,"(")&&!StringUtils.contains(regexStr,")")){
            regexStr="("+regexStr+")";
        }
        if (!StringUtils.contains(regexStr,"(")||!StringUtils.contains(regexStr,")")){
            throw new IllegalArgumentException("regex must have capture group 1");
        }
        this.regexStr = regexStr;
        try {
            regex = Pattern.compile(regexStr,Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("invalid regex", e);
        }
    }

    @Override
    public String select(String text) {
        return selectGroup(text).get(1);
    }

    @Override
    public List<String> selectList(String text) {
        List<String> strings=new ArrayList<String>();
        List<RegexResult> results = selectGroupList(text);
        for (RegexResult result : results) {
            strings.add(result.get(1));
        }
        return strings;
    }

    public RegexResult selectGroup(String text) {
        Matcher matcher = regex.matcher(text);
        if (matcher.find()) {
            String[] groups = new String[matcher.groupCount()+1];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = matcher.group(i);
            }
            return new RegexResult(groups);
        }
        return RegexResult.EMPTY_RESULT;
    }

    public List<RegexResult> selectGroupList(String text) {
        Matcher matcher = regex.matcher(text);
        List<RegexResult> resultList = new ArrayList<RegexResult>();
        while (matcher.find()) {
            String[] groups = new String[matcher.groupCount()+1];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = matcher.group(i);
            }
            resultList.add(new RegexResult(groups));
        }
        return resultList;
    }

    @Override
    public String toString() {
        return regexStr;
    }

}
