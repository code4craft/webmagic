package us.codecraft.webmagic.scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.scripts.languages.JRuby;
import us.codecraft.webmagic.scripts.languages.Javascript;
import us.codecraft.webmagic.scripts.languages.Language;
import us.codecraft.webmagic.utils.WMCollections;

public class Params {
   @Getter
   Language language = new Javascript();

   @Getter @Setter
   String scriptFileName;

   @Getter @Setter
   List<String> urls;

   @Getter @Setter
   int thread = 1;

   @Getter @Setter
   int sleepTime = 1000;
   
   private static Map<Language, Set<String>> alias;

   public Params() {
      alias = new HashMap<Language, Set<String>>();
      alias.put(new Javascript(), WMCollections.<String>newHashSet("js", "javascript", "JavaScript", "JS"));
      alias.put(new JRuby(), WMCollections.<String>newHashSet("ruby", "jruby", "Ruby", "JRuby"));
   }

   public void setLanguagefromArg(String arg) {
       for (Map.Entry<Language, Set<String>> languageSetEntry : alias.entrySet()) {
           if (languageSetEntry.getValue().contains(arg)) {
               this.language = languageSetEntry.getKey();
               return;
           }
       }
   }
}