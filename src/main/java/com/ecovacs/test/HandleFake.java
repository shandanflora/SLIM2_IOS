package com.ecovacs.test;

import com.ecovacs.test.activity.*;
import com.ecovacs.test.common.*;
import io.appium.java_client.ios.IOSDriver;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 17/4/28.
 * handle fake robot
 */
class HandleFake {
    private static HandleFake handleFake = null;
    private static Logger logger = LoggerFactory.getLogger(HandleFake.class);
    //private AndroidDriver androidDriver = null;
    private Map<String, String> languageMap = null;

    private HandleFake(){

    }

    public static HandleFake getInstance(){
        if (handleFake == null){
            handleFake = new HandleFake();
        }
        return handleFake;
    }

    public void init(IOSDriver driver){
        ConsumableActivity.getInstance().init(driver);
        MainActivity.getInstance().init(driver);
        SettingActivity.getInstance().init(driver);
        UnibotCleanActivity.getInstance().init(driver);
        AlertActivity.getInsatance().init(driver);
        MoreActivity.getInstance().init(driver);
        LanguageActivity.getInstance().init(driver);
    }

    void translateErrorReport_init(){
        List<String> list = JsonParse.getJsonParse().readDataFromJson("country.json", "sheets");
        TranslateErrorReport.getInstance().init(list);
    }

    void translate_init(String strColName){
        Map<String, String> tranMap = TranslateIntl.getInstance().readExcel("SLIM2.xlsx", strColName);
        if(tranMap.isEmpty()){
            logger.error("The language map is empty!!!");
            return;
        }
        tranMap.put("language", strColName);
        languageMap = tranMap;
    }

    void changeLanguage(String strLanguage){
        //return deebot clean
        //SettingActivity.getInstance().clickBack();
        //return com.ecovacs.test.main
        UnibotCleanActivity.getInstance().clickBack();
        MainActivity.getInstance().showDeviceList();
        MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        LanguageActivity.getInstance().selectLanguage(strLanguage);
        //return com.ecovacs.test.main
        MoreActivity.getInstance().clickBack();
        MainActivity.getInstance().showDeviceList();
    }

    private void enterUnibotClean(){
        Common.getInstance().waitForSecond(5000);
        MainActivity.getInstance().clickDevice("SLIM2_FAKE");
        UnibotCleanActivity.getInstance().showActivity();
        UnibotCleanActivity.getInstance().showText("-");
        Common.getInstance().waitForSecond(2500);
    }
/*
    boolean translateAlertBump(){
        enterUnibotClean();
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error111");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().bumpMalfunction(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        boolean bAlert = AlertActivity.getInsatance().bumpAlert(languageMap);
        return bMal && bAlert;
    }*/

    boolean translateAlertDrop(){
        enterUnibotClean();
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error104");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().dropMalfunction(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        boolean bAlert = AlertActivity.getInsatance().dropAlert(languageMap);
        return bMal && bAlert;
    }

    boolean translateAlertWheel(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error103");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().wheelMalfunction(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        boolean bAlert = AlertActivity.getInsatance().wheelAlert(languageMap);
        return bMal && bAlert;
    }
/*
    boolean translateMainBrush(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error109");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().mainBrushMalfunction(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        boolean bAlert = AlertActivity.getInsatance().mainBrushAlert(languageMap);
        return bMal && bAlert;
    }*/

    boolean translateSideBrush(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error108");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().sideBrushMalfunction(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        boolean bAlert = AlertActivity.getInsatance().sideBrushAlert(languageMap);
        return bMal && bAlert;
    }

    boolean translateDustBin(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error110");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().dustMalfunction(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        boolean bAlert = AlertActivity.getInsatance().dustBinAlert(languageMap);
        return bMal && bAlert;
    }

    boolean translateSuspendDeebot(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error102");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().deebotMalfunction(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        boolean bAlert = AlertActivity.getInsatance().deebotSuspendAlert(languageMap);
        resetMalfunction();
        return bMal && bAlert;
    }

    private void resetMalfunction(){
        UnibotCleanActivity.getInstance().clickBack();
        enterUnibotClean();
    }

    boolean translateSideBrushWillExpire(){
        //will be delete
        //enterUnibotClean();
        //********
        Common.getInstance().waitForSecond(10 * 1000);
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "sideBrush5");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().sideBrushWillExpire(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        List<String> strList = new ArrayList<String>();
        strList.add(languageMap.get("random_deebot_remind_text_title"));
        strList.add(languageMap.get("random_deebot_sidebrush_low_hint2"));
        AlertActivity.getInsatance().consumableRemind(languageMap, strList);
        Common.getInstance().waitForSecond(5 * 1000);
        return bMal;
    }

    boolean translateFilterWillExpire(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "filter5");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().filterWillExpire(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        List<String> strList = new ArrayList<String>();
        strList.add(languageMap.get("random_deebot_remind_text_title"));
        strList.add(languageMap.get("random_deebot_sidebrush_low_hint2"));
        strList.add(languageMap.get("random_deebot_remind_text_title"));
        strList.add(languageMap.get("random_deebot_filter_low_hint2"));
        AlertActivity.getInsatance().consumableRemind(languageMap, strList);
        Common.getInstance().waitForSecond(5 * 1000);
        return bMal /*&& bAlert*/;
    }

    boolean translateFilterExpired(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "filter0");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().filterExpired(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        List<String> strList = new ArrayList<String>();
        strList.add(languageMap.get("random_deebot_remind_text_title"));
        strList.add(languageMap.get("random_deebot_sidebrush_due_hint2"));
        strList.add(languageMap.get("random_deebot_remind_text_title"));
        strList.add(languageMap.get("random_deebot_filter_due_hint2"));
        AlertActivity.getInsatance().consumableRemind(languageMap, strList);
        Common.getInstance().waitForSecond(5 * 1000);
        //reset accessory
        resetConsumable();
        return bMal;
    }

    boolean translateSideBrushExpired(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "sideBrush0");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(1000);
        boolean bMal = UnibotCleanActivity.getInstance().sideBrushExpired(languageMap);
        UnibotCleanActivity.getInstance().clickTip();
        List<String> strList = new ArrayList<String>();
        strList.add(languageMap.get("random_deebot_remind_text_title"));
        strList.add(languageMap.get("random_deebot_sidebrush_due_hint2"));
        strList.add(languageMap.get("random_deebot_remind_text_title"));
        strList.add(languageMap.get("random_deebot_filter_low_hint2"));
        AlertActivity.getInsatance().consumableRemind(languageMap, strList);
        Common.getInstance().waitForSecond(5 * 1000);
        return bMal;
    }

    public void resetConsumable(){
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "sideBrush100");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(3 * 1000);
        paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "filter100");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(3 * 1000);
    }

    boolean translateLowBatteryStopCharger(){
        //will be delete
        //enterUnibotClean();
        //********
        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "error101");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(500);
        paras = HttpRequestUtils.getInstance().getParaFromJson("command.json", "goCharge");
        HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
        logger.info("complete send http request!!!");
        Common.getInstance().waitForSecond(500);
        UnibotCleanActivity.getInstance().clickCharge();
        return UnibotCleanActivity.getInstance().stopCharge(languageMap);
    }


}
