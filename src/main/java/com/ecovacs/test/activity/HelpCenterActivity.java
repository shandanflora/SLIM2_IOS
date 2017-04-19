package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/3/6.
 *
 */
public class HelpCenterActivity {
    private static HelpCenterActivity helpCenterActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;

    private HelpCenterActivity(){

    }

    public static HelpCenterActivity getInstance(){
        if(helpCenterActivity == null){
            helpCenterActivity = new HelpCenterActivity();
        }
        return helpCenterActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickBack(){
        back.click();
    }

    public void showActivity(){
        Common.getInstance().showActivity(title);
    }

    private boolean staticUI(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("setting_help_center"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "HelpCenter", title.getText(),
                    tranMap.get("setting_help_center"), "fail");
        }
        return bTitle;
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUI(tranMap);
    }


}
