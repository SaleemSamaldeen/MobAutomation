package demo.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;


class AppConfig {

    static AppiumDriver driver;

    public static void launchAppInMobile(String[] args) throws IOException {
        Properties properties = readProps();
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(AUTOMATION_NAME, "Appium");
        dc.setCapability(DEVICE_NAME, properties.getProperty("mobile.deviceName"));
        dc.setCapability("appium:platformName", ANDROID);
        dc.setCapability(PLATFORM_VERSION, "11.0");
        dc.setCapability(APP, properties.getProperty("mobile.apkfile"));
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url,dc);
        driver.quit();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Properties properties = readProps();
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(AUTOMATION_NAME, "Appium");
        dc.setCapability(DEVICE_NAME, properties.getProperty("mobile.deviceName"));
        dc.setCapability("appium:platformName", ANDROID);
        dc.setCapability(PLATFORM_VERSION, "11.0");
        dc.setCapability(BROWSER_NAME, properties.getProperty("mobile.browser"));
        //dc.setCapability(APP, properties.getProperty("mobile.chromeApp")); //if app not installed
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,dc);
        driver.get(properties.getProperty("mobile.url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//button[contains(text(),'ACCEPT')]")).click();
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("0,document.body.scrollHeight");
        driver.findElement(By.xpath("//div[contains(@class,'order-first')]//a[contains(@class,'font-semibold')]")).click();
        driver.switchTo().frame("grnhse_iframe");
        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("saleem");
        driver.quit();
    }

    public static Properties readProps() throws IOException {
        FileInputStream fis;
        Properties prop;
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Not able to read data from application.properties");
        }
        return prop;
    }



}
