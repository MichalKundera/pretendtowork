package src.pretender.impl;

import java.time.LocalDateTime;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import src.pretender.WorkPretender;

import static src.pretender.timeSheduler.WorkTimeScheduler.timeNotPassed;

public class AnyKeySitePretender implements WorkPretender, Runnable {

    private String siteUrl;
    private static final String ANY_KEY = "a";
    private WebDriver driver = new FirefoxDriver();
    private int seconds;
    private Point windowPoint;

    public AnyKeySitePretender(String siteUrl,int seconds,Point windowPoint) {
        this.windowPoint = windowPoint;
        this.siteUrl = siteUrl;
        this.seconds = seconds;
    }

    @Override
    public void pretend() {
        driver.navigate().to(siteUrl);
        driver.manage().window().setPosition(windowPoint);
        Actions builder = new Actions(driver);
        Action pressAnyKeyAction = builder.sendKeys(ANY_KEY).build();
        LocalDateTime start = LocalDateTime.now();
        while (timeNotPassed(start, seconds)) {
            pressAnyKeyAction.perform();
        }
        driver.close();
    }

    @Override
    public void run() {
        pretend();
    }

    public class SiteUrl {
        public static final String GEEK_TYPER = "http://geektyper.com/umbrella/";
        public static final String HACKER_TYPER = "http://hackertyper.com";
        public static final String GEEK_TYPER2 = "http://geektyper.com/fallout/";
        private SiteUrl() {
        }
    }

    public static class WindowPosition {
        public static final Point RIGHT = new Point(2000, 0);
        public static final Point CENTER = new Point(500, 500);
        public static final Point CENTER_RIGHT = new Point(1000, 0);
        public static final Point CENTER_LEFT = new Point(0, 0);
        public static final Point LEFT = new Point(-1500, 0);


        private WindowPosition() {
        }
    }

}
