package us.codecraft.webmagic.script.selenium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * @author yang
 *
 */
public class ScriptSeleniumWindowsChromeTest {

    //@Ignore("need chrome driver")
    @Test
    public void testExcuteScript() {
      
    	System.getProperties().setProperty("webdriver.chrome.driver", "D:\\software\\chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        
        options.setCapability("chrome.switches", Arrays.asList("--user-data-dir=D:\\software\\chrome\\temp\\chrome"));
    
        ChromeDriver webDriver = new ChromeDriver(options);
        
        webDriver.get("http://v.qq.com/x/search/?q=极限挑战");
		
		  String script=""; 
		  try { 
			  script = loadJs("js/manga.js"); 
			  } catch(IOException e) { 
			  // TODO Auto-generated catch block 
				  e.printStackTrace(); 
		}
		  
		 
		 //String func="(function(){ return document.getElementsByClassName('reading-content'); });";
        //return document.domain;
		  //Boolean
		  //Long
		 // String
		 // List
		 // WebElement.
        //Object content=webDriver.executeScript("return document.getElementsByClassName('reading-content');");
		  System.out.println("------the script should be excute-------------"+script);
        Object content=webDriver.executeScript(script);
        
        System.out.println("------return content-------------"+content.toString());
       
        
        
        webDriver.close();
    }
    public String loadJs(String scriptName) throws IOException{
		
	     InputStream is = ScriptSeleniumWindowsChromeTest.class.getClassLoader().getResourceAsStream(scriptName);
	     
	     
	     BufferedReader br = new BufferedReader(new InputStreamReader(is));
	     StringBuilder build=new StringBuilder();
	     for (String line = br.readLine(); line != null; line = br.readLine()) {
	    	 build.append(line);           
       }
	     return build.toString().trim();   
	 
	}
}
