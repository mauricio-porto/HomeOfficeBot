package br.com.braintech.homeofficebot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class HomeOfficeBot {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/mapo/bin/chromedriver"); // TODO: Troque para o caminho do seu chromedriver
        HomeOfficeBot me = new HomeOfficeBot();
        me.execute();
    }

    private void execute() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);

            options.setBinary("/usr/bin/google-chrome"); // TODO: Troque para o caminho do seu chrome

            driver = new ChromeDriver(options);
            js = (JavascriptExecutor) driver;
            vars = new HashMap<>();

            driver.get("https://alguma-url-desejada/");  // TODO: Troque para a URL desejada
            driver.findElement(By.id("requiredusuario")).sendKeys("user");      // TODO: Troque para o identificador desejado
            driver.findElement(By.id("requiredsenha")).sendKeys("pwd");     // TODO: Troque para o identificador desejado
            {
                WebElement dropdown = driver.findElement(By.name("campo-de-sele????o"));   // TODO: Troque para o identificador desejado
                dropdown.findElement(By.xpath("//option[. = 'MINHA OP????O']")).click();  // TODO: Troque para o identificador desejado
            }
            driver.findElement(By.name("Submit")).click();
            vars.put("window_handles", driver.getWindowHandles());
            {
                WebElement element = driver.findElement(By.id("menu2"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).click().perform();
            }
            driver.findElement(By.linkText("Um item do menu")).click();  // TODO: Troque para o identificador desejado
            vars.put("win7952", waitForWindow(2000));
            vars.put("root", driver.getWindowHandle());
            driver.switchTo().window(vars.get("win7952").toString());
            driver.findElement(By.id("Button1")).click();     // TODO: Troque para o identificador desejado
        } finally {
            Optional.ofNullable(driver).ifPresent(WebDriver::quit);
        }
    }
}
