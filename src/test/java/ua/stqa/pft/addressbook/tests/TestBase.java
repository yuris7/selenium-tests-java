package ua.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.stqa.pft.addressbook.appmanager.ApplicationManager;
import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.openqa.selenium.remote.BrowserType.CHROME;


public class TestBase {

//    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static ApplicationManager app;

    static {
        try {
            app = new ApplicationManager(System.getProperty("browser", CHROME));
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
    public void verifyGroupListInUI() {
        if(Boolean.getBoolean("verifyUI")){
            Groups dbGroups= app.db().groups();
            Groups uiGroups= app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }

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
