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
 * Created by ecosqa on 17/3/14.
 *
 */
public class SwitchWLANActivity {
    private static SwitchWLANActivity switchWLANActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement btnCancel = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textDescription = null;

    private SwitchWLANActivity(){

    }

    public static SwitchWLANActivity getInstance(){
        if (switchWLANActivity == null){
            switchWLANActivity = new SwitchWLANActivity();
        }
        return switchWLANActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void showActivity(){
        Common.getInstance().showActivity(textDescription);
    }

    public void clickCancel(){
        btnCancel.click();
    }

    private boolean staticUI(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_select_change_wlan").trim());
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SwitchWLAN", title.getText().trim(),
                    tranMap.get("random_deebot_select_change_wlan").trim(), "fail");
        }
        /*boolean bbtnCancel = btnCancel.getText().equalsIgnoreCase(tranMap.get("random_deebot_cancel"));
        if(!bbtnCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SwitchWLAN", btnCancel.getText(),
                    tranMap.get("random_deebot_cancel"), "fail");
        }*/
        boolean btextDescription = textDescription.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_connect_wifi").trim());
        if(!btextDescription){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SwitchWLAN", textDescription.getText().trim(),
                    tranMap.get("random_deebot_connect_wifi").trim(), "fail");
        }
        return bTitle /*&& bbtnCancel*/ && btextDescription;
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUI(tranMap);
    }


}
