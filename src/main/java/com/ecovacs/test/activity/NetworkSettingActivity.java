package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

//import com.ecovacs.test.common.PropertyData;

/**
 * Created by ecosqa on 17/3/8.
 * network setting activity
 */
public class NetworkSettingActivity {
    private static NetworkSettingActivity networkSettingActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textConnectWlan = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement textNot5G = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement textChangeWlan = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")
    private MobileElement editWifi = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[1]/UIAButton[1]")
    private MobileElement clearWifi = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    private MobileElement editPassword = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]")
    private MobileElement btnNextStep = null;

    private NetworkSettingActivity(){

    }

    public static NetworkSettingActivity getInstance(){
        if (networkSettingActivity == null){
            networkSettingActivity = new NetworkSettingActivity();
        }
        return networkSettingActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //this.driver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(btnNextStep);
    }

    private boolean staticUI(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_network_set"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting", title.getText(),
                    tranMap.get("random_deebot_select_network_set"), "fail");
        }
        boolean btextConnectWlan = textConnectWlan.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_connect_wlan"));
        if(!btextConnectWlan){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting", textConnectWlan.getText(),
                    tranMap.get("random_deebot_select_connect_wlan"), "fail");
        }
        boolean btextNot5G = textNot5G.getText().trim().equalsIgnoreCase(tranMap.get("config_net_hint").trim());
        if(!btextNot5G){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting", textNot5G.getText().trim(),
                    tranMap.get("config_net_hint").trim(), "fail");
        }
        boolean btextChangeWlan = textChangeWlan.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_change_wlan"));
        if(!btextChangeWlan){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting", textChangeWlan.getText(),
                    tranMap.get("random_deebot_select_change_wlan"), "fail");
        }
        boolean beditPassword = editPassword.getText().equalsIgnoreCase(tranMap.get("random_deebot_password"));
        if(!beditPassword){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting", editPassword.getText(),
                    tranMap.get("random_deebot_password"), "fail");
        }
        boolean bbtnNextStep = btnNextStep.getText().equalsIgnoreCase(tranMap.get("random_deebot_select_next"));
        if(!bbtnNextStep){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting", btnNextStep.getText(),
                    tranMap.get("random_deebot_select_next"), "fail");
        }
        return bTitle && btextConnectWlan && btextNot5G &&
                btextChangeWlan && beditPassword && bbtnNextStep;
    }

    boolean translateWifiName(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        editWifi.click();
        clearWifi.click();
        textConnectWlan.click();
        boolean bWifi = editWifi.getText().equalsIgnoreCase(tranMap.get("random_deebot_wifi"));
        if(!bWifi){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NetworkSetting", editWifi.getText(),
                    tranMap.get("random_deebot_wifi"), "fail");
        }
        return bWifi;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUI(tranMap);
        boolean bWifi = translateWifiName(tranMap);
        return bStatic && bWifi;
    }

    public void clickNext(){
        btnNextStep.click();
    }

    public void clickBack(){
        back.click();
    }


}
