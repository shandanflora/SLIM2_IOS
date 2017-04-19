package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * Continue clean activity
 */
public class ContinueCleanActivity {
    private static ContinueCleanActivity continueCleanActivity = null;
    private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textViewline1 = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement textViewMessage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASwitch[1]")
    private MobileElement imageViewSwitch = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")
    private MobileElement textViewStart = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")
    private MobileElement textViewEnd = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[2]/UIAPicker[1]/UIAPickerWheel[1]")
    private MobileElement pickerH = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[2]/UIAPicker[1]/UIAPickerWheel[2]")
    private MobileElement pickerM = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")
    private MobileElement textStartTime = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]")
    private MobileElement textEndTime = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    private MobileElement promptContent = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    private MobileElement promptSure = null;


    private ContinueCleanActivity(){

    }

    public static ContinueCleanActivity getInstance(){
        if (continueCleanActivity == null){
            continueCleanActivity = new ContinueCleanActivity();
        }
        return continueCleanActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void showActivity(){
        Common.getInstance().showActivity(imageViewSwitch);
    }

    public void clickSwitch(){
        //imageViewSwitch.getSize();
        imageViewSwitch.click();
    }

    public void showStartTime(){
        Common.getInstance().showActivity(textViewStart);
    }

    public void clickBack(){
        back.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_no_disturb"));
        if (!btitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", title.getText(),
                    tranMap.get("random_deebot_no_disturb"), "fail");
        }
        boolean btextViewline1 = textViewline1.getText().equalsIgnoreCase(tranMap.get("random_deebot_no_disturb"));
        if (!btextViewline1) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewline1.getText(),
                    tranMap.get("random_deebot_no_disturb"), "fail");
        }
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("random_deebot_no_disturb_onoff_hint"));
        if (!btextViewMessage) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewMessage.getText(),
                    tranMap.get("random_deebot_no_disturb_onoff_hint"), "fail");
        }
        return btitle && btextViewline1 && btextViewMessage;
    }

    private boolean forbidTimeConfig(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        clickSwitch();
        Common.getInstance().showActivity(textViewStart);
        boolean btextViewStart = textViewStart.getText().equalsIgnoreCase(tranMap.get("random_deebot_no_disturb_start"));
        if (!btextViewStart) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewStart.getText(),
                    tranMap.get("random_deebot_no_disturb_start"), "fail");
        }
        boolean btextViewEnd = textViewEnd.getText().equalsIgnoreCase(tranMap.get("random_deebot_no_disturb_end"));
        if (!btextViewEnd) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewEnd.getText(),
                    tranMap.get("random_deebot_no_disturb_end"), "fail");
        }
        clickSwitch();
        return btextViewStart && btextViewEnd;
    }

    private List<String> getTime(MobileElement element){
        String[] strings = element.getText().split(":");
        List<String> list = new ArrayList<String>();
        list.add(strings[0]);
        list.add(strings[1]);
        return list;
    }

    private void setTimeNight(MobileElement element){
        element.click();
        Common.getInstance().setStartTimeNight(driver, pickerH, pickerM, getTime(element), 8);
        element.click();
        Common.getInstance().waitForSecond(1000);
    }

    public void setTime(){
        setTimeNight(textStartTime);
        setTimeNight(textEndTime);
    }

    public boolean translateSameTime(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bContent = promptContent.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_no_disturb_error"));
        if(!bContent) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", promptContent.getText(),
                    tranMap.get("random_deebot_no_disturb_error"), "fail");
        }
        boolean bSure = promptSure.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_btn_known"));
        if(!bSure){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", promptSure.getText(),
                    tranMap.get("random_deebot_btn_known"), "fail");
        }
        promptSure.click();
        return bContent && bSure;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bForbid = forbidTimeConfig(tranMap);
        return bStatic && bForbid;
    }

}
