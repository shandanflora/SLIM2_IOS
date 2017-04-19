package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/3/8.
 * connect guide
 */
public class ConnectGuideActivity {
    private static ConnectGuideActivity connectGuideActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textTips = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnNext = null;


    private ConnectGuideActivity(){

    }

    public static ConnectGuideActivity getInstance(){
        if (connectGuideActivity == null){
            connectGuideActivity = new ConnectGuideActivity();
        }
        return connectGuideActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickNext(){
        btnNext.click();
    }

    public void clickBack(){
        back.click();
    }

    private boolean staticUI(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_guide"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ConnectGuide", title.getText(),
                    tranMap.get("random_deebot_select_guide"), "fail");
        }
        String strTips = tranMap.get("random_deebot_connect_tip").replace("\\n", "");
        boolean btextTips = textTips.getText().equalsIgnoreCase(strTips.trim());
        if(!btextTips){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ConnectGuide", textTips.getText(),
                    strTips.trim(), "fail");
        }
        boolean bbtnNext = btnNext.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_next"));
        if(!bbtnNext){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ConnectGuide", btnNext.getText(),
                    tranMap.get("random_deebot_select_next"), "fail");
        }
        return bTitle && btextTips && bbtnNext;
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUI(tranMap);
    }


}
