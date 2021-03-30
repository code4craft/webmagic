package us.codecraft.webmagic.selector;

import us.codecraft.webmagic.utils.Experimental;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Borrowed from https://code.google.com/p/cx-extractor/
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.4.1
 *
 */
@Experimental
public class SmartContentSelector implements Selector {
	/***
	 * Empty/ default constructor for SmartContentSelector
	 */
    public SmartContentSelector() {
    }

    @Override
    public String select(String html) {
        html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
        html = html.replaceAll("(?is)<!--.*?-->", "");				// remove html comment
        html = html.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
        html = html.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
        html = html.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
        html = html.replaceAll("(?is)<.*?>", "");
        List<String> lines;
        int blocksWidth =3;
        int threshold =86;
        int start;
        int end;
        StringBuilder text = new StringBuilder();
        ArrayList<Integer> indexDistribution = new ArrayList<>();

        lines = Arrays.asList(html.split("\n"));

        for (int i = 0; i < lines.size() - blocksWidth; i++) {
            int wordsNum = 0;
            for (int j = i; j < i + blocksWidth; j++) {
                lines.set(j, lines.get(j).replaceAll("\\s+", ""));
                wordsNum += lines.get(j).length();
            }
            indexDistribution.add(wordsNum);
        }

        start = -1; end = -1;
        boolean boolstart = false;
        boolean boolend = false;
        text.setLength(0);
       
        int i=0;
        while (i < indexDistribution.size() - 1) {
            
        	if ((indexDistribution.get(i) > threshold && ! boolstart) 
                && (indexDistribution.get(i+1).intValue() != 0
                        || indexDistribution.get(i+2).intValue() != 0
                        || indexDistribution.get(i+3).intValue() != 0) ){
                    boolstart = true;
                    start = i;
                    i++;
                }
            
            if ((boolstart) && (indexDistribution.get(i).intValue() == 0
                        || indexDistribution.get(i+1).intValue() == 0) ){
                    end = i;
                    boolend = true;
                }
            
            
            StringBuilder tmp = new StringBuilder();
            if (boolend) {
                for (int ii = start; ii <= end; ii++) {
                    if (lines.get(ii).length() < 5) i++;
                    tmp.append(lines.get(ii) + "\n");
                }
                String str = tmp.toString();
                
                if (str.contains("Copyright")) i++;
                text.append(str);
                boolstart = boolend = false;
            }
            i++;
        }
        return text.toString();
    }

    @Override
    public List<String> selectList(String text) {
        throw new UnsupportedOperationException();
    }
}
