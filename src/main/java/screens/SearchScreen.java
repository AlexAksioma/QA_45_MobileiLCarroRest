package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Locale;

public class SearchScreen extends BaseScreen {
    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnMoreOptions;

    @FindBy(xpath = "//*[@text='Registration']")
    AndroidElement btnRegistration;
    @FindBy(xpath = "//*[@text='Login']")
    AndroidElement btnLogin;
    @FindBy(xpath = "//*[@text='My Cars']")
    AndroidElement btnMyCars;
    //============================
    @FindBy(id = "com.telran.ilcarro:id/editLocation")
    AndroidElement inputCity;
    @FindBy(id = "com.telran.ilcarro:id/editFrom")
    AndroidElement inputDateFrom;
    @FindBy(id = "com.telran.ilcarro:id/editTo")
    AndroidElement inputDateTo;
    @FindBy(id = "com.telran.ilcarro:id/searchBtn")
    AndroidElement btnYalla;
    @FindBy(id = "android:id/button1")
    AndroidElement btnOkCalendar;
    @FindBy(id = "android:id/next")
    AndroidElement btnMonthNext;
    @FindBy(id = "android:id/prev")
    AndroidElement btnMonthPrev;
    @FindBy(id = "android:id/date_picker_header_year")
    AndroidElement btnYear;


    public void goToRegistrationScreen() {
        //btnMoreOptions.click();
        //btnRegistration.click();
        clickWait(btnMoreOptions, 5);
        clickWait(btnRegistration, 5);
    }

    public void goToLoginScreen() {
        clickWait(btnMoreOptions, 5);
        clickWait(btnLogin, 5);
    }

    public void goToMyCarsScreen() {
        clickWait(btnMoreOptions, 5);
        clickWait(btnMyCars, 5);
    }

    public boolean validateMessageSuccess(String message) {
        return textInElementPresent(popUpMessageSuccess, message, 5);
    }

    public void findCarWithCalendar(String city, String dateStart, String dateEnd) {
        //clickWait(inputCity, 5);  //02 March 2025
        inputCity.sendKeys(city);
        inputDateFrom.click();
        setDateCalendar(dateStart);
        inputDateTo.click();
        setDateCalendar(dateEnd);
    }

    private void setDateCalendar(String dateStart) {
        //pause(3);
        //March - 3   --> June 6 - 3 = 3 click
        //02 March 2025    -->  [02] [March] [2025]
        //
        String[] arrayDate = dateStart.split(" ");
        //=============== year
        if (LocalDate.now().getYear() != Integer.parseInt(arrayDate[2])) {
            clickWait(btnYear, 5);
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@text='" + arrayDate[2] + "']")
                    )).click();
        }
        //=============== month
        int month = returnNumOfMonth(arrayDate[1]);
        int currentMonth = LocalDate.now().getMonthValue() + 1;
        // 3  10 = 7      12   5 = -7
        int quantityClick = month - currentMonth;  //+  -
        if (quantityClick > 0)
            for (int i = 0; i < quantityClick; i++) {
                clickWait(btnMonthNext, 3);
            }
        else if (quantityClick < 0) {
            for (int i = 0; i < Math.abs(quantityClick); i++) {
                clickWait(btnMonthPrev, 3);
            }
        }
        //============================ day
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@content-desc='" + dateStart + "']")))
                .click();
        btnOkCalendar.click();
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated
                            (By.id("android:id/button1")));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private int returnNumOfMonth(String month) {
        Month month1 = Month.valueOf(month.toUpperCase(Locale.ENGLISH));
        return month1.getValue();
    }
}
