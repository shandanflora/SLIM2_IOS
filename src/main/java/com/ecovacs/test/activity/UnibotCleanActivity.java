package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Point;

/**
 * Created by ecosqa on 17/2/15.
 * unibot cleaning
 */
public class UnibotCleanActivity {
    private static UnibotCleanActivity unibotCleanActivity = null;
    private static Logger logger = LoggerFactory.getLogger(UnibotCleanActivity.class);
    //private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement btnBack = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement btnRight = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement textViewStatusValue = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[4]")
    private MobileElement textViewStatus = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAImage[2]")
    private MobileElement imageBattery = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[5]")
    private MobileElement textViewDeBattery = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[1]")
    private MobileElement btnAuto = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[4]")
    private MobileElement btnCharge = null;
    @FindBy(xpath =" //UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[2]")
    private MobileElement btnEdge = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[3]")
    private MobileElement btnSpot = null;
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[1]")
    private MobileElement btnStandard = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[2]")
    private MobileElement btnMax = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]")
    private MobileElement scrollView = null;

    private enum DIRECTION_PAGER{
        TO_RIGHT,
        TO_LEFT
    }*/

    private UnibotCleanActivity() {

    }

    public static UnibotCleanActivity getInstance() {
        if (unibotCleanActivity == null) {
            unibotCleanActivity = new UnibotCleanActivity();
        }
        return unibotCleanActivity;
    }

    public void init(IOSDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //this.driver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(textViewStatusValue);
    }

    public boolean showText(String strText) {
        boolean bResult = false;
        int iLoop = 0;
        while (true) {
            if (iLoop > 50) {
                break;
            }
            if (textViewStatusValue.getText().contains(strText)) {
                logger.info(textViewStatusValue.getText());
                Common.getInstance().waitForSecond(500);
            } else {
                logger.info(textViewStatusValue.getText());
                bResult = true;
                break;
            }
            iLoop++;
        }
        return bResult;
    }

    private void showStatusCharge() {
        boolean bResult = false;
        while (!bResult) {
            try {
                bResult = imageBattery.isDisplayed();
                logger.info(textViewStatusValue.getText());
            }catch (Exception e){
                logger.info(textViewStatusValue.getText());
                Common.getInstance().waitForSecond(200);
            }
        }
    }

    public void clickSetting(){
        btnRight.click();
    }

    public void clickBack(){
        btnBack.click();
    }
/*
    private void swipe(DIRECTION_PAGER direction){
        Point point = scrollView.getLocation();
        Dimension dimension = scrollView.getSize();
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight/2;

        if(direction == DIRECTION_PAGER.TO_RIGHT){
            driver.swipe(point.x - iWidth/4, point.y,
                    point.x + iWidth/4, point.y, 200);
        }else if (direction == DIRECTION_PAGER.TO_LEFT){
            //driver.swipe(iRectX + iWidth - iWidth/4, point.y,
                    //iRectX + iWidth/4, point.y, 300);
            driver.swipe(point.x + iWidth/4, point.y, iWidth/2, point.y, 500);
            System.out.println(point.x + iWidth/4);
            System.out.println(point.y);

        }
    }*/

    private boolean staticUITranslate(Map<String, String> tranMap) {
        String strLanguage = tranMap.get("language");
        boolean btextViewStatus = textViewStatus.getText().equalsIgnoreCase(tranMap.get("random_deebot_state"));
        if (!btextViewStatus) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatus.getText(),
                    tranMap.get("random_deebot_state"), "fail");
        }
        boolean btextViewStatusValue = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_state_standby"));
        if (!btextViewStatusValue) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("random_deebot_state_standby"), "fail");
        }
        boolean btextViewDeBattery = textViewDeBattery.getText().equalsIgnoreCase(tranMap.get("random_deebot_battery"));
        if (!btextViewDeBattery) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewDeBattery.getText(),
                    tranMap.get("random_deebot_battery"), "fail");
        }
        /*boolean bbtnAuto = btnAuto.getText().equalsIgnoreCase(tranMap.get("random_deebot_auto"));
        if (!bbtnAuto) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", btnAuto.getText(),
                    tranMap.get("random_deebot_auto"), "fail");
        }
        boolean bbtnCharge = btnCharge.getText().equalsIgnoreCase(tranMap.get("random_deebot_state_go_charging"));
        if (!bbtnCharge) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", btnCharge.getText(),
                    tranMap.get("random_deebot_state_go_charging"), "fail");
        }
        boolean bbtnEdge = btnEdge.getText().equalsIgnoreCase(tranMap.get("random_deebot_edge"));
        if (!bbtnEdge) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", btnEdge.getText(),
                    tranMap.get("random_deebot_edge"), "fail");
        }
        boolean bbtnSpot = btnSpot.getText().equalsIgnoreCase(tranMap.get("random_deebot_spot"));
        if (!bbtnSpot) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", btnSpot.getText(),
                    tranMap.get("random_deebot_spot"), "fail");
        }
        //right to left
        swipe(DIRECTION_PAGER.TO_LEFT);
        boolean bbtnStandard = btnStandard.getText().equalsIgnoreCase(tranMap.get("random_deebot_standard"));
        if (!bbtnStandard) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", btnStandard.getText(),
                    tranMap.get("random_deebot_standard"), "fail");
        }
        boolean bbtnMax = btnMax.getText().equalsIgnoreCase(tranMap.get("random_deebot_max"));
        if (!bbtnMax) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", btnMax.getText(),
                    tranMap.get("random_deebot_max"), "fail");
        }
        //left to right
        swipe(DIRECTION_PAGER.TO_RIGHT);*/
        return btextViewStatus && btextViewStatusValue && btextViewDeBattery /*&&
                bbtnAuto && bbtnCharge && bbtnEdge && bbtnSpot && bbtnStandard &&
                bbtnMax*/;
        //return true;
    }

    private boolean checkStatus_clean_charge(Map<String, String> tranMap, MobileElement element){
        //auto to charge
        String strLanguage = tranMap.get("language");
        //check auto
        element.click();
        logger.info(textViewStatusValue.getText());
        boolean btextViewStatusValue = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_state_clean"));
        if (!btextViewStatusValue) {
            logger.error("Status auto cleaning is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("random_deebot_state_clean"), "fail");
        }
        //check stand by
        element.click();
        logger.info(textViewStatusValue.getText());
        boolean btextViewStatusValue1 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_state_standby"));
        if (!btextViewStatusValue1) {
            logger.error("Status auto cleaning is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("random_deebot_state_standby"), "fail");
        }
        //check charge
        btnCharge.click();
        logger.info(textViewStatusValue.getText());
        //Common.getInstance().waitForSecond(400);
        boolean btextViewStatusValue2 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_state_go_charging"));
        if (!btextViewStatusValue2) {
            logger.error("Status auto cleaning is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("random_deebot_state_go_charging"), "fail");
        }
        //check stand by
        btnCharge.click();
        logger.info(textViewStatusValue.getText());
        boolean btextViewStatusValue3 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_state_standby"));
        if (!btextViewStatusValue3) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("random_deebot_state_standby"), "fail");
        }
        btnCharge.click();
        logger.info(textViewStatusValue.getText());
        showStatusCharge();
        return btextViewStatusValue && btextViewStatusValue1 && btextViewStatusValue2 &&
                btextViewStatusValue3;
    }

    public void clickAuto7(){
        for (int i = 0; i < 7; i++){
            //auto
            btnAuto.click();
            Common.getInstance().waitForSecond(1000);
            //standby
            btnAuto.click();
            logger.info("i--" + i);
        }
        //charge
        btnCharge.click();
        logger.info(textViewStatusValue.getText());
        showText(textViewStatusValue.getText());
    }

    private boolean checkStatus(Map<String, String> tranMap){
        //check auto to standby to charge
        boolean bAuto = checkStatus_clean_charge(tranMap, btnAuto);
        //check edge to standby to charge
        boolean bEdge = checkStatus_clean_charge(tranMap, btnEdge);
        //check spot to standby to charge
        boolean bSpot = checkStatus_clean_charge(tranMap, btnSpot);
        return bAuto && bEdge && bSpot;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslate(tranMap);
        boolean bCheckStatus = checkStatus(tranMap);
        return bStatic && bCheckStatus;
    }

}
