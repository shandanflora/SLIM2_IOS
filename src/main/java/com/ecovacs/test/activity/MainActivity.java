package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * robot list activity
 * Created by ecosqa on 16/12/8.
 */
public class MainActivity {
    private static MainActivity mainActivity = null;
    private static Logger logger = LoggerFactory.getLogger(RegisterActivity.class);

    private MainActivity(){

    }

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textViewFooter = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private IOSElement btnAdd = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private IOSElement textViewMore = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]")
    private MobileElement listView = null;

    public static MainActivity getInstance(){
        if (mainActivity == null){
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(listView);
    }

    public void showDeviceList(){
        while (true){
            List<MobileElement> rlList = listView.findElements(By.className("UIATableCell"));
            if (rlList.size() == 0){
                Common.getInstance().waitForSecond(500);
                logger.info("Not show device list, try again!!!");
            }else {
                break;
            }
        }

    }

    public void clickMore(){
        textViewMore.click();
    }

    public void clickAdd(){
        btnAdd.click();
    }

    public void clickDevice(String strKey){
        List<MobileElement> textList = listView.findElements(By.className("UIAStaticText"));
        logger.info("textList size:" + textList.size());
        for (MobileElement text:textList){
            logger.info("child elment--" + text.getText());
            if (text.getText().contains(PropertyData.getProperty(strKey))){
                text.click();
                break;
            }
        }
    }

    private boolean staticUITranslation(Map<String,String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btextViewFooter = textViewFooter.getText().equalsIgnoreCase(tranMap.get("pull_to_refresh_tap_label"));
        if(!btextViewFooter){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Main", textViewFooter.getText(),
                    tranMap.get("pull_to_refresh_tap_label"), "fail");
        }
        boolean bOnline = true;
        boolean bOffline = true;
        List<MobileElement> cellList = listView.findElements(By.className("UIATableCell"));
        for (MobileElement cell:cellList){
            List<MobileElement> lineLayouts = cell.findElements(By.className("UIAStaticText"));
            if (lineLayouts.size() == 0){
                continue;
            }
            String strRobot = lineLayouts.get(0).getText();
            logger.info(strRobot);
            String strStatus = lineLayouts.get(1).getText();
            logger.info(strStatus);
            if (strRobot.contains("DM80i")){
                if (!strStatus.equalsIgnoreCase(tranMap.get("online"))){
                    TranslateErrorReport.getInstance().insetNewLine(
                            strLanguage, "Main", strStatus,
                            tranMap.get("online"), "fail");
                    bOnline = false;
                }
            }else if(strRobot.contains("A650")){
                if (!strStatus.equalsIgnoreCase(tranMap.get("offline"))){
                    TranslateErrorReport.getInstance().insetNewLine(
                            strLanguage, "Main", strStatus,
                            tranMap.get("offline"), "fail");
                    bOffline = false;
                }
            }
        }
        return btextViewFooter && bOffline && bOnline;
    }

    public boolean translate(Map<String,String> tranMap){
        return staticUITranslation(tranMap);
    }
}
