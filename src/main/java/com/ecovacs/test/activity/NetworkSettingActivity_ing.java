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
 * Created by ecosqa on 17/3/8.
 * is network setting
 */
public class NetworkSettingActivity_ing {
    private static NetworkSettingActivity_ing networkSettingActivity_ing = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textSetting = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private MobileElement textDoNotChange = null;

    private NetworkSettingActivity_ing(){

    }

    public static NetworkSettingActivity_ing getInstance(){
        if (networkSettingActivity_ing == null){
            networkSettingActivity_ing = new NetworkSettingActivity_ing();
        }
        return networkSettingActivity_ing;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(textSetting);
    }

    private boolean staticUI(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_network_set"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting_ing", title.getText(),
                    tranMap.get("random_deebot_select_network_set"), "fail");
        }
        boolean bback = back.getText().equalsIgnoreCase(tranMap.get("random_deebot_cancel"));
        if(!bback){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting_ing", back.getText(),
                    tranMap.get("random_deebot_cancel"), "fail");
        }
        boolean btextSetting = textSetting.getText().equalsIgnoreCase(tranMap.get("random_deebot_network_setting"));
        if(!btextSetting){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting_ing", textSetting.getText(),
                    tranMap.get("random_deebot_network_setting"), "fail");
        }
        boolean btextDoNotChange = textDoNotChange.getText().equalsIgnoreCase(tranMap.get("random_deebot_not_switch"));
        if(!btextDoNotChange){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting_ing", textDoNotChange.getText(),
                    tranMap.get("random_deebot_not_switch"), "fail");
        }
        return bTitle && bback && btextSetting && btextDoNotChange;
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUI(tranMap);
    }

    public void clickCancel(){
        back.click();
    }


}
