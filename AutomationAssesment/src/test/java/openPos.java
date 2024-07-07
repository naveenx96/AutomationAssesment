import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

@Test
public class openPos {
    public static void main(String[] args) {

    //TestCase1

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.pos.com.my/");
        driver.manage().window().maximize();
        WebElement homeTitle = driver.findElement(By.xpath("//h1[@class='home-title ng-tns-c47-0']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(homeTitle);
        actions.perform();
        WebElement buyInsuranceButton = driver.findElement(By.xpath("//a[@href='https://insurance.pos.com.my']"));
        buyInsuranceButton.click();
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        String expectedUrl = "https://insurance.pos.com.my/";
        try{
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
            System.out.println("Navigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }

        WebElement carButton = driver.findElement(By.xpath("//div[contains(text(),'I drive a car')]"));
        carButton.isEnabled();
        WebElement motorcycleButton = driver.findElement(By.xpath("//div[normalize-space(text())='I ride a motorcycle']"));
        motorcycleButton.isEnabled();
        carButton.click();
        WebElement gettoKnowLabel = driver.findElement(By.xpath("//h5[@class='mb-4']"));
        try {
            Assert.assertEquals(true, gettoKnowLabel.isDisplayed());
            System.out.println("New section displayed");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to new section");
        }
        driver.close();
        driver.switchTo().window(browserTabs.get(0));

    //Testcase2

        WebElement sendDropdown = driver.findElement(By.xpath("(//span[normalize-space(text())='Send'])[2]"));
        actions.moveToElement(sendDropdown).perform();
        WebElement parcelButton = driver.findElement(By.xpath("(//a[@href='send/send-parcel'])[2]"));
        parcelButton.click();

        String expectedUrl2 = "https://www.pos.com.my/send/send-parcel";
        try{
            Assert.assertEquals(expectedUrl2, driver.getCurrentUrl());
            System.out.println("Navigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }

        WebElement shipmentButton = driver.findElement(By.xpath("//div[normalize-space(text())='Create shipment now']"));
        actions.moveToElement(shipmentButton);
        actions.perform();
        shipmentButton.click();

        String expectedUrl3 = "https://send.pos.com.my/home/e-connote?lg=en";
        try{
            Assert.assertEquals(expectedUrl3, driver.getCurrentUrl());
            System.out.println("Navigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }

        WebElement senderInfo = driver.findElement(By.xpath("//div[normalize-space(text())='Sender Info']"));
        WebElement receiverInfo = driver.findElement(By.xpath("//div[normalize-space(text())='Receiver Info']"));
        WebElement parcelInfo = driver.findElement(By.xpath("//div[normalize-space(text())='Parcel Info']"));
        WebElement summary = driver.findElement(By.xpath("//div[normalize-space(text())='Summary']"));

        Assert.assertEquals(true, senderInfo.isDisplayed(), "senderInfo not visible");
        Assert.assertEquals(true, receiverInfo.isDisplayed(), "receiverInfo not visible");
        Assert.assertEquals(true, parcelInfo.isDisplayed(), "parcelInfo not visible");
        Assert.assertEquals(true, summary.isDisplayed(), "summary not visible");

        driver.quit();

    }
}
