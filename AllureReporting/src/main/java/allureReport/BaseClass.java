package allureReport;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	public WebDriver driver;
	
    public static ThreadLocal<WebDriver> tdriver=new ThreadLocal<WebDriver>();
    
    public WebDriver initialize_driver() {
    	
    	ChromeOptions options=new ChromeOptions();
		 options.addArguments("--remote-allow-origins=*");
		 options.addArguments("--disable-notifications");

    	//WebDriverManager.chromedriver().setup();
	 System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

    	driver=new ChromeDriver(options);
    	
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    	driver.manage().window().maximize();
    	tdriver.set(driver);
    	return getDriver();
    	
    }
    
    public static synchronized WebDriver getDriver()
    {
    	return tdriver.get();
    }
    	
    	
   
    	
    
	
	
	
}
