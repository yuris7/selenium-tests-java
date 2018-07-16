package ua.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static ApplicationManager app;

    static {
        try {
            app = new ApplicationManager(BrowserType.CHROME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {

        app.stop();
    }

    public ApplicationManager getApp() {

        return app;
    }
//    @BeforeMethod
//    public void logTestStart(Method m, Object[] p){
//        logger.info("Start test testGroupCreation" + m.getName() + "with patameters" + Arrays.asList(p));
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void logTestStop(Method m){
//        logger.info("Stop test testGroupCreation" + m.getName());
//    }
}
