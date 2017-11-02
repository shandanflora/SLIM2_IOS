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
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAImage[2]")
    private MobileElement imageBattery = null;*/
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
    //malfunction
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[1]")
    private MobileElement titlePrompt = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    private MobileElement  contentPrompt = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    private MobileElement surePrompt = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]/UIAButton[1]")
    private MobileElement viewPrompt = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]")
    private MobileElement textViewWarnTip = null;

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

    /*private void showStatusCharge() {
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
    }*/

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
        Common.getInstance().waitForSecond(1000 * 5);
        //check stand by
        element.click();
        logger.info(textViewStatusValue.getText());
        boolean btextViewStatusValue1 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_state_standby"));
        if (!btextViewStatusValue1) {
            logger.error("1Status auto cleaning is not match!!!");
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
            logger.error("2Status auto cleaning is not match!!!");
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
        showText(textViewStatusValue.getText());
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

    //
    private boolean translateMalPrompt(Map<String, String> tranMap, String strKey, String strPage, boolean bSupspend){
        String strLanguage = tranMap.get("language");
        boolean bTitle = false;
        if(!bSupspend){
            Common.getInstance().showActivity(titlePrompt);
            String strTitle = titlePrompt.getText().trim();
            bTitle = strTitle.equalsIgnoreCase
                    (tranMap.get("random_deebot_state_error").trim());
            if (!bTitle){
                TranslateErrorReport.getInstance().insetNewLine(
                        strLanguage, strPage, strTitle,
                        tranMap.get("random_deebot_state_error"), "fail");
            }
        }
        Common.getInstance().showActivity(contentPrompt);
        boolean bContent = contentPrompt.getText().trim().equalsIgnoreCase
                (tranMap.get(strKey).trim());
        if (!bContent){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, strPage, contentPrompt.getText(),
                    tranMap.get(strKey), "fail");
        }
        boolean bSure = surePrompt.getText().trim().equalsIgnoreCase
                (tranMap.get("random_deebot_confirm").trim());
        if (!bSure){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, strPage, surePrompt.getText(),
                    tranMap.get("random_deebot_confirm"), "fail");
        }
        surePrompt.click();
        if (bSupspend){
            return bContent && bSure;
        }
        return bTitle && bContent && bSure;
    }

    public void clickTip(){
        textViewWarnTip.click();
    }

    public void clickCharge(){
        btnCharge.click();
    }

    //check status
    private boolean translateMalState(Map<String, String> tranMap, String strKey){
        boolean bStatusValue = textViewStatusValue.getText().trim().equalsIgnoreCase
                (tranMap.get(strKey).trim());
        if (!bStatusValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Malfunction", textViewStatusValue.getText(),
                    tranMap.get(strKey), "fail");
        }
        return bStatusValue;
    }

    //check warn tip
    private boolean translateWarnTip(Map<String, String> tranMap, String strKey){
        boolean bWarnTip = textViewWarnTip.getText().trim().equalsIgnoreCase
                (tranMap.get(strKey).trim());
        if (!bWarnTip){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Malfunction", textViewWarnTip.getText(),
                    tranMap.get(strKey), "fail");
        }
        return bWarnTip;
    }

    public boolean dropMalfunction(Map<String, String> tranMap){
        boolean bPrompt = translateMalPrompt(tranMap, "random_deebot_drop_malfunction",
                "dropMalfunction", false);
        boolean bState = translateMalState(tranMap, "random_deebot_state_error");
        boolean bTip = translateWarnTip(tranMap, "random_deebot_drop_malfunction");
        return bPrompt && bState && bTip;
    }

    public boolean wheelMalfunction(Map<String, String> tranMap){
        logger.info("----wheelMalfunction----");
        boolean bPrompt = translateMalPrompt(tranMap, "random_deebot_wheel_malfunction",
                "wheelMalfunction", false);
        boolean bState = translateMalState(tranMap, "random_deebot_state_error");
        boolean bTip = translateWarnTip(tranMap, "random_deebot_wheel_malfunction");
        return bPrompt && bState && bTip;
    }
/*
    public boolean mainBrushMalfunction(Map<String, String> tranMap){
        logger.info("----mainBrushMalfunction----");
        boolean bPrompt = translateMalPrompt(tranMap, "random_deebot_mainbrush_malfunction",
                "mainBrushMalfunction", false);
        boolean bState = translateMalState(tranMap, "random_deebot_state_error");
        boolean bTip = translateWarnTip(tranMap, "random_deebot_mainbrush_malfunction");
        return bPrompt && bState && bTip;
    }*/

    public boolean sideBrushMalfunction(Map<String, String> tranMap){
        logger.info("----sideBrushMalfunction----");
        boolean bPrompt = translateMalPrompt(tranMap, "random_deebot_sidebrush_malfunction",
                "sideBrushMalfunction", false);
        boolean bState = translateMalState(tranMap, "random_deebot_state_error");
        boolean bTip = translateWarnTip(tranMap, "random_deebot_sidebrush_malfunction");
        return bPrompt && bState && bTip;
    }

    public boolean dustMalfunction(Map<String, String> tranMap){
        logger.info("----dustMalfunction----");
        boolean bPrompt = translateMalPrompt(tranMap, "random_deebot_dust_malfunction",
                "dustMalfunction", false);
        boolean bState = translateMalState(tranMap, "random_deebot_state_error");
        boolean bTip = translateWarnTip(tranMap, "random_deebot_dust_malfunction");
        return bPrompt && bState && bTip;
    }

    public boolean deebotMalfunction(Map<String, String> tranMap){
        boolean bPrompt = translateMalPrompt(tranMap, "random_deebot_suspend_malfunction",
                "deebotMalfunction", true);
        boolean bState = translateMalState(tranMap, "random_deebot_state_standby");
        boolean bTip = translateWarnTip(tranMap, "random_deebot_suspend_malfunction");
        return bPrompt && bState && bTip;
    }

    private boolean translateSumWarnTip(Map<String, String> tranMap){
        String strInfo = textViewWarnTip.getText().trim().substring(1);
        boolean bWarnTip = strInfo.equalsIgnoreCase
                (tranMap.get("random_deebot_warn_info_sum").trim());
        if (!bWarnTip){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Malfunction", strInfo,
                    tranMap.get("random_deebot_warn_info_sum"), "fail");
        }
        return bWarnTip;
    }

    private boolean consumablePrompt(Map<String, String> tranMap, String strKey){
        String strLanguage = tranMap.get("language");
        Common.getInstance().showActivity(contentPrompt);
        boolean bContent = contentPrompt.getText().trim().equalsIgnoreCase
                (tranMap.get(strKey).trim());
        if (!bContent){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Consumble", contentPrompt.getText(),
                    tranMap.get(strKey), "fail");
        }
        boolean bSure = viewPrompt.getText().trim().equalsIgnoreCase
                (tranMap.get("random_deebot_btn_check").trim());
        if (!bSure){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Consumble", viewPrompt.getText(),
                    tranMap.get("random_deebot_btn_check"), "fail");
        }
        boolean bCancel = surePrompt.getText().trim().equalsIgnoreCase
                (tranMap.get("random_deebot_btn_ignore").trim());
        if (!bCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Consumble", surePrompt.getText(),
                    tranMap.get("random_deebot_btn_ignore"), "fail");
        }
        surePrompt.click();
        return bContent && bSure && bCancel;
    }

    private boolean lowBatteryPrompt(Map<String, String> tranMap, String strKey){
        String strLanguage = tranMap.get("language");
        boolean bContent = contentPrompt.getText().trim().equalsIgnoreCase
                (tranMap.get(strKey).trim());
        if (!bContent){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "lowBattery", contentPrompt.getText(),
                    tranMap.get(strKey), "fail");
        }
        boolean bSure = viewPrompt.getText().trim().equalsIgnoreCase
                (tranMap.get("random_deebot_confirm").trim());
        if (!bSure){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "lowBattery", viewPrompt.getText(),
                    tranMap.get("random_deebot_confirm"), "fail");
        }
        boolean bCancel = surePrompt.getText().trim().equalsIgnoreCase
                (tranMap.get("random_deebot_cancel").trim());
        if (!bCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "lowBattery", surePrompt.getText(),
                    tranMap.get("random_deebot_cancel"), "fail");
        }
        surePrompt.click();
        return bContent && bSure && bCancel;
    }

    public boolean stopCharge(Map<String, String> tranMap){
        return lowBatteryPrompt(tranMap, "random_deebot_low_battery_stop_charge");
    }

    public boolean sideBrushWillExpire(Map<String, String> tranMap){
        boolean bPrompt = consumablePrompt(tranMap, "random_deebot_sidebrush_low_hint1");
        boolean bTip = translateWarnTip(tranMap, "random_deebot_sidebrush_low_hint1");
        return bPrompt && bTip;
    }

    public boolean filterWillExpire(Map<String, String> tranMap){
        boolean bPrompt = consumablePrompt(tranMap, "random_deebot_filter_low_hint1");
        boolean bTip = translateSumWarnTip(tranMap);
        return bPrompt && bTip;
    }

    public boolean filterExpired(Map<String, String> tranMap){
        boolean bPrompt = consumablePrompt(tranMap, "random_deebot_filter_due_hint1");
        boolean bTip = translateSumWarnTip(tranMap);
        return bPrompt && bTip;
    }

    public boolean sideBrushExpired(Map<String, String> tranMap){
        boolean bPrompt = consumablePrompt(tranMap, "random_deebot_sidebrush_due_hint1");
        boolean bTip = translateSumWarnTip(tranMap);
        return bPrompt && bTip;
    }


}
