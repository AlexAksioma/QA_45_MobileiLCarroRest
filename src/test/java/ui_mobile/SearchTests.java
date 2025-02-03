package ui_mobile;

import config.AppiumConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

public class SearchTests extends AppiumConfig {
    SearchScreen searchScreen;

    @BeforeMethod
    public void goToSearchScreen(){
        new SplashScreen(driver).goToSearchScreen();
        searchScreen = new SearchScreen(driver);
    }

    @Test
    public void searchPositiveTest_withCalendar(){
        searchScreen.findCarWithCalendar("Rehovot",
                "10 March 2025", "12 March 2025");
    }
}
