package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/3/8.
 * select deebot
 */
public class SelectDEEBOTActivity {
    private static SelectDEEBOTActivity selectDEEBOTActivity = null;
    private IOSDriver driver = null;
    private static Logger logger = LoggerFactory.getLogger(SelectDEEBOTActivity.class);

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAStaticText[1]")
    private MobileElement textViewSelectDeebot = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;

    private SelectDEEBOTActivity(){

    }

    public static SelectDEEBOTActivity getInstance(){
        if (selectDEEBOTActivity == null){
            selectDEEBOTActivity = new SelectDEEBOTActivity();
        }
        return selectDEEBOTActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    private boolean staticUI(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_deebot"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SelectDEEBOTActivity", title.getText(),
                    tranMap.get("random_deebot_select_deebot"), "fail");
        }
        boolean btextViewSelectDeebot = textViewSelectDeebot.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_device"));
        if(!btextViewSelectDeebot){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SelectDEEBOTActivity", textViewSelectDeebot.getText(),
                    tranMap.get("random_deebot_select_device"), "fail");
        }
        return bTitle && btextViewSelectDeebot;
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUI(tranMap);
    }

    public boolean selectDM80i(String strDeebot){

        String str = "UIATarget.localTarget().frontMostApp().mainWindow()." +
                "tableViews()[0].cells()[\"" + strDeebot + "\"]";
        //driver.executeScript(path + ".scrollToVisible();");
        MobileElement textViewCountry;
        try {
            textViewCountry = (MobileElement) driver
                    .findElementByIosUIAutomation(str);
        }catch (NoSuchElementException e){
            return false;
        }
        textViewCountry.click();
        logger.info("Selected DEEBOT - " + strDeebot);
        return true;
    }


}
