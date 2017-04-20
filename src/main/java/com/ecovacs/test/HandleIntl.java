package com.ecovacs.test;

import com.ecovacs.test.activity.*;
import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.JsonParse;
import com.ecovacs.test.common.TranslateErrorReport;
import com.ecovacs.test.common.TranslateIntl;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

//import java.io.File;

/**
 * Created by ecosqa on 16/11/30.
 * handle international app
 */
class HandleIntl {
    private static Logger logger = LoggerFactory.getLogger(HandleIntl.class);
    private static HandleIntl handleIntl = null;
    //private IOSDriver driver = null;
    private Map<String, String> languageMap = null;

    private HandleIntl(){

    }

    public static HandleIntl getInstance(){
        if(handleIntl == null){
            handleIntl = new HandleIntl();
        }
        return handleIntl;
    }

    public void init(IOSDriver driver){
        //this.driver = driver;
        AboutActivity.getInstance().init(driver);
        ChangePassActivity.getInstance().init(driver);
        ConnectGuideActivity.getInstance().init(driver);
        ConsumableActivity.getInstance().init(driver);
        ContinueCleanActivity.getInstance().init(driver);
        CountrySelectActivity.getInstance().init(driver);
        FailNetworkSettingActivity.getInstance().init(driver);
        FirmVerActivity.getInstance().init(driver);
        ForgetPassActivity.getInstance().init(driver);
        FullScreenActivity.getInstance().init(driver);
        HelpCenterActivity.getInstance().init(driver);
        LanguageActivity.getInstance().init(driver);
        LoginActivity.getInstance().init(driver);
        MainActivity.getInstance().init(driver);
        MoreActivity.getInstance().init(driver);
        NetworkSettingActivity.getInstance().init(driver);
        NetworkSettingActivity_ing.getInstance().init(driver);
        NewScheduleActivity.getInstance().init(driver);
        RegisterActivity.getInstance().init(driver);
        RenameActivity.getInstance().init(driver);
        RepetitionActivity.getInstance().init(driver);
        RetrievePassActivity.getInstance().init(driver);
        SelectDEEBOTActivity.getInstance().init(driver);
        SettingActivity.getInstance().init(driver);
        SwitchWLANActivity.getInstance().init(driver);
        TimeScheduleActivity.getInstance().init(driver);
        UnibotCleanActivity.getInstance().init(driver);
        UserAgreeActivity.getInstance().init(driver);
        VirtualWallActivity.getInstance().init(driver);
        WelcomeActivity.getInstance().init(driver);
        WorkLogActivity.getInstance().init(driver);
    }
/*
    boolean enterWelcomeActivity(){
        if (!WelcomeActivity.getInstance().showWelcomeActivity()) {
            logger.error("Can not show welcome activity!!!");
            return false;
        }
        return true;
    }

    private boolean enterRegisterActivity(){
        WelcomeActivity.getInstance().clickRegister();
        if (!RegisterActivity.getInstance().showRegisterActivity()) {
            logger.error("Can not show register activity!!!");
            return false;
        }
        logger.info("Show register activity!!!");
        return true;
    }
*/
    /**
     * verify email
     //* @param strJar jar file
     //* @param strCountry country
     //* @param strMail email
     //* @param strType handle type, ex:Register
     */
/*
    private void verifyEmail(String strJar, String strCountry, String strMail, String strType){
        String strPath = RegisterActivity.class.getResource("/").getPath();
        strPath = strPath + "../../" + strJar;
        File fileApp = new File(strPath);
        logger.info(strPath);
        Common.getInstance().executeCommand("java -jar " + fileApp.getName() + " " + strCountry + " " + strMail + " " + strType);
        logger.info("********exec command finished!!!");
    }

    boolean registerAndLogin(String strCountry, String strEmailType, String strEmail, String strPass){
        //register
        if(!enterRegisterActivity()){
            return false;
        }
        if (!RegisterActivity.getInstance().fill_Screenshot_Click(strCountry, strEmail, strPass)) {
            logger.error("Register failed!!! country--" + strCountry);
            return false;
        }
        if(!RetrievePassActivity.getInstance().showRetrieveConfirmActivity()){
            Common.getInstance().setFailType(Common.FailType.ALREADY_REGISTER);
            Common.getInstance().goBack(driver, 1);
            logger.error("Not show Retrieve confirm activity!!!");
            return false;
        }
        logger.info("Show active email activity!!!");
        //verify email
        verifyEmail("VerifyEmail.one-jar.jar", strCountry, strEmailType, "Register");
        //check--login with new password
        RetrievePassActivity.getInstance().clickLogin();
        if (!loginWithoutWelcome(strCountry, strEmail, strPass)) {
            logger.info("Login failed after forget password country- " + strCountry);
            return false;
        }
        if (!logout()) {
            logger.info("Logout failed after forget password country- " + strCountry);
            Common.getInstance().goBack(driver, 1);
            return false;
        }
        return true;
    }

    private boolean enterLoginAcivity(){
        WelcomeActivity.getInstance().clickLogin();
        if (!LoginActivity.getInstance().showLoginActivity()) {
            logger.info("Not show login activity!!!");
            return false;
        }
        logger.info("Show login activity!!!");
        return true;
    }

    boolean forgetPassword(String strCountry, String strEmailType, String strEmail, String strPass){
        if(!enterLoginAcivity()){
            return false;
        }
        LoginActivity.getInstance().clickForgetPass();
        logger.info("Click forget password!!!");
        if (!ForgetPassActivity.getInstance().showActivity()) {
            Common.getInstance().goBack(driver, 1);
            logger.error("Not show forget password activity!!!");
            return false;
        }
        if (!ForgetPassActivity.getInstance().sendEmail(strCountry, strEmail)) {
            Common.getInstance().goBack(driver, 2);
            Common.getInstance().setFailType(Common.FailType.NOT_REGISTER);
            logger.error("Not show retrieve password activity!!!");
            return false;
        }
        logger.info("Click send verify email!!!");
        if (!RetrievePassActivity.getInstance().showRetrieveConfirmActivity()) {
            logger.error("Not show retrieve password activity!!!");
            Common.getInstance().goBack(driver, 2);
            return false;
            //invalid email
        }
        logger.info("Show retrieve password activity!!!");
        verifyEmail("VerifyEmail.one-jar.jar", strCountry, strEmailType, "DoFindPass");
        //check--login with new password
        RetrievePassActivity.getInstance().clickLogin();
        if (!loginWithoutWelcome(strCountry, strEmail, strPass)) {
            logger.info("Login failed after forget password country- " + strCountry);
            Common.getInstance().goBack(driver, 1);
            return false;
        }
        if (!logout()) {
            logger.info("Logout failed after forget password country- " + strCountry);
            return false;
        }
        return true;
    }
*/
    private boolean loginWithoutWelcome(String strCountry, String strEmail, String strPass){
        if(!LoginActivity.getInstance().showLoginActivity()){
            logger.error("Can not show login activity!!!");
            return false;
        }
        logger.info("Show login activity!!!");
        LoginActivity.getInstance().clickCountry();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }
        logger.info("Finished to select country!!!");
        LoginActivity.getInstance().fillInfoAndClick(strEmail, strPass);
        logger.info("Finished to click login!!!");
        MainActivity.getInstance().showActivity();
        logger.info("login successfully country- " + strCountry);
        return true;
    }

    private boolean login(String strCountry, String strEmail, String strPass){
        if(!WelcomeActivity.getInstance().showWelcomeActivity()){
            logger.error("Can not show welcome activity!!!");
            return false;
        }
        WelcomeActivity.getInstance().clickLogin();
        logger.info("Click login in welcome activity!!!");
        return loginWithoutWelcome(strCountry, strEmail, strPass);
    }

    private boolean logout(){
        MainActivity.getInstance().clickMore();
        if(!MoreActivity.getInstance().showMoreActivity()){
            logger.info("Can not show more activity!!!");
            return false;
        }
        MoreActivity.getInstance().clickLogout();
        return WelcomeActivity.getInstance().showWelcomeActivity();
    }

    /*public boolean loginAndLogout(String strCountry, String strEmail, String strPass){
        if(!login(strCountry, strEmail, strPass)){
            logger.info("login failed country- " + strCountry);
            return false;
        }
        if(!logout()){
            logger.info("logout successfully country- " + strCountry);
            return false;
        }
        return true;
    }*/

    void changeLanguage(String strLanguage){
        //return deebot clean
        //SettingActivity.getInstance().clickBack();
        //return main
        //mvUnibotCleanActivity.getInstance().clickBack();
        /*if(!login("Japan", PropertyData.getProperty("hotmail_email"), PropertyData.getProperty("login_pass"))){
            logger.error("login failed!!!");
            return;
        }*/
        MainActivity.getInstance().showDeviceList();
        MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        LanguageActivity.getInstance().selectLanguage(strLanguage);
        //return main
        MoreActivity.getInstance().clickBack();
        MainActivity.getInstance().showDeviceList();
        if(!logout()){
            logger.info("logout failed!!!");
        }
    }

    void translateErrorReport_init(){
        List<String> list = JsonParse.getJsonParse().readDataFromJson("country.json", "sheets");
        TranslateErrorReport.getInstance().init(list);
    }

    void translate_init(String strColName){
        Map<String, String> tranMapCommon = TranslateIntl.getInstance().readExcel("Translate.xlsx", strColName);
        if(tranMapCommon.isEmpty()){
            logger.error("The language map is empty!!!");
            return;
        }
        Map<String, String> tranMap = TranslateIntl.getInstance().readExcel("Random_translate.xlsx", strColName);
        if(tranMap.isEmpty()){
            logger.error("The language map is empty!!!");
            return;
        }
        tranMap.putAll(tranMapCommon);
        languageMap = tranMap;
    }

    boolean translateWelcome(){
        return WelcomeActivity.getInstance().translate(languageMap);
    }

    boolean translateLogin(){
        WelcomeActivity.getInstance().clickLogin();
        LoginActivity.getInstance().showLoginActivity();
        boolean bRes = LoginActivity.getInstance().translate(languageMap);
        LoginActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateRegister(){
        WelcomeActivity.getInstance().clickRegister();
        RegisterActivity.getInstance().showRegisterActivity();
        boolean bRes = RegisterActivity.getInstance().translate(languageMap);
        RegisterActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateCountryRegion(){
        WelcomeActivity.getInstance().clickRegister();
        RegisterActivity.getInstance().showRegisterActivity();
        RegisterActivity.getInstance().clickCountryRegion();
        boolean bRes = CountrySelectActivity.getInstance().translate(languageMap);
        CountrySelectActivity.getInstance().back();
        RegisterActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateForget(){
        WelcomeActivity.getInstance().clickLogin();
        LoginActivity.getInstance().showLoginActivity();
        LoginActivity.getInstance().clickForgetPass();
        ForgetPassActivity.getInstance().showActivity();
        boolean bRes = ForgetPassActivity.getInstance().translate(languageMap);
        ForgetPassActivity.getInstance().clickBack();
        LoginActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateMain(){
        //login("Japan", PropertyData.getProperty("hotmail_email"), PropertyData.getProperty("login_pass"));
        //
        MainActivity.getInstance().showDeviceList();
        //
        return MainActivity.getInstance().translate(languageMap);
    }

    boolean translateSelectDEEBOT(){
        MainActivity.getInstance().clickAdd();
        boolean bRes = SelectDEEBOTActivity.getInstance().translate(languageMap);
        SelectDEEBOTActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateNetworkSetting(){
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickAdd();
        SelectDEEBOTActivity.getInstance().selectDM80i("DM80i (DEEBOT)");
        NetworkSettingActivity.getInstance().showActivity();
        boolean bRes = NetworkSettingActivity.getInstance().translate(languageMap);
        NetworkSettingActivity.getInstance().clickBack();
        SelectDEEBOTActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateConnectGuide(){
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickAdd();
        SelectDEEBOTActivity.getInstance().selectDM80i("DM80i (DEEBOT)");
        NetworkSettingActivity.getInstance().showActivity();
        NetworkSettingActivity.getInstance().clickNext();
        boolean bRes = ConnectGuideActivity.getInstance().translate(languageMap);
        ConnectGuideActivity.getInstance().clickBack();
        NetworkSettingActivity.getInstance().clickBack();
        SelectDEEBOTActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateNetworkSettingActivity_ing(){
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickAdd();
        SelectDEEBOTActivity.getInstance().selectDM80i("DM80i (DEEBOT)");
        NetworkSettingActivity.getInstance().showActivity();
        NetworkSettingActivity.getInstance().clickNext();
        ConnectGuideActivity.getInstance().clickNext();
        boolean bRes = NetworkSettingActivity_ing.getInstance().translate(languageMap);
        NetworkSettingActivity_ing.getInstance().clickCancel();
        NetworkSettingActivity.getInstance().clickBack();
        SelectDEEBOTActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateSwitchWLAN(){
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickAdd();
        SelectDEEBOTActivity.getInstance().selectDM80i("DM80i (DEEBOT)");
        NetworkSettingActivity.getInstance().showActivity();
        NetworkSettingActivity.getInstance().clickNext();
        ConnectGuideActivity.getInstance().clickNext();
        //wait 90 second
        Common.getInstance().waitForSecond(1000 * 90);
        SwitchWLANActivity.getInstance().showActivity();
        Common.getInstance().waitForSecond(3000);
        boolean bRes = SwitchWLANActivity.getInstance().translate(languageMap);
        SwitchWLANActivity.getInstance().clickCancel();
        NetworkSettingActivity.getInstance().clickBack();
        SelectDEEBOTActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateFailNetworkSetting(){
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickAdd();
        SelectDEEBOTActivity.getInstance().selectDM80i("DM80i (DEEBOT)");
        NetworkSettingActivity.getInstance().showActivity();
        NetworkSettingActivity.getInstance().clickNext();
        ConnectGuideActivity.getInstance().clickNext();
        //wait 90 second
        Common.getInstance().waitForSecond(1000 * 90);
        SwitchWLANActivity.getInstance().showActivity();
        //wait 3 minute
        Common.getInstance().waitForSecond(1000 * 60 * 2);
        FailNetworkSettingActivity.getInstance().showActivity();
        boolean bRes = FailNetworkSettingActivity.getInstance().translate(languageMap);
        FailNetworkSettingActivity.getInstance().clickBack();
        NetworkSettingActivity.getInstance().clickBack();
        SelectDEEBOTActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateMore(){
        MainActivity.getInstance().clickMore();
        return MoreActivity.getInstance().translate(languageMap);
        //MoreActivity.getInstance().clickBack();
        //return bTranlate;
    }

    boolean translateAbout(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        boolean bTrans =  AboutActivity.getInstance().staticUITranslate(languageMap);
        AboutActivity.getInstance().clickBack();
        //MoreActivity.getInstance().clickBack();
        return bTrans;
    }

    boolean translateHelpCenter(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickHelp();
        HelpCenterActivity.getInstance().showActivity();
        boolean bTrans =  HelpCenterActivity.getInstance().translate(languageMap);
        HelpCenterActivity.getInstance().clickBack();
        //MoreActivity.getInstance().clickBack();
        return bTrans;
    }

    boolean translateUserAgree(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        AboutActivity.getInstance().clickUserAgree();
        boolean bTrans =  UserAgreeActivity.getInstance().staticUITranslate(languageMap);
        UserAgreeActivity.getInstance().clickBack();
        AboutActivity.getInstance().clickBack();
        //MoreActivity.getInstance().clickBack();
        return bTrans;
    }

    boolean translateLanguage(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        boolean bRes = LanguageActivity.getInstance().staticUITranslation(languageMap);
        LanguageActivity.getInstance().clickBack();
        //MoreActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateChangePass(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickChangePass();
        ChangePassActivity.getInstance().showActivity();
        boolean bTrans =  ChangePassActivity.getInstance().translate(languageMap);
        ChangePassActivity.getInstance().clickBack();
        MoreActivity.getInstance().clickBack();
        return bTrans;
    }

    boolean translateUnibotClean(){
        MainActivity.getInstance().showDeviceList();
        Common.getInstance().waitForSecond(2000);
        MainActivity.getInstance().clickDevice();
        UnibotCleanActivity.getInstance().showActivity();
        UnibotCleanActivity.getInstance().showText("-");
        boolean bRes = UnibotCleanActivity.getInstance().translate(languageMap);
        //only for work log
        UnibotCleanActivity.getInstance().clickAuto7();
        return bRes;
    }

    boolean translateUnibotSetting(){
        //will delete
        //MainActivity.getInstance().clickDevice();
        //
        UnibotCleanActivity.getInstance().showActivity();
        UnibotCleanActivity.getInstance().clickSetting();
        return SettingActivity.getInstance().translate(languageMap);
    }

    boolean translateWorkLog(){
        //after check return to setting
        SettingActivity.getInstance().clickWorkLog();
        boolean bResult = WorkLogActivity.getInstance().translate(languageMap);
        WorkLogActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateContinueClean(){
        //after check return to setting
        SettingActivity.getInstance().clickContinuedClean();
        ContinueCleanActivity.getInstance().showActivity();
        boolean bResult = ContinueCleanActivity.getInstance().translate(languageMap);
        ContinueCleanActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateContiuneClean_Status(){
        SettingActivity.getInstance().clickContinuedClean();
        ContinueCleanActivity.getInstance().showActivity();
        ContinueCleanActivity.getInstance().clickSwitch();
        ContinueCleanActivity.getInstance().showStartTime();
        ContinueCleanActivity.getInstance().clickBack();
        boolean bRes = SettingActivity.getInstance().checkDisturbStatus(languageMap);
        //recover
        SettingActivity.getInstance().clickContinuedClean();
        ContinueCleanActivity.getInstance().clickSwitch();
        ContinueCleanActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateSameContinueTime(){
        SettingActivity.getInstance().clickContinuedClean();
        ContinueCleanActivity.getInstance().clickSwitch();
        ContinueCleanActivity.getInstance().showStartTime();
        ContinueCleanActivity.getInstance().setTime();
        ContinueCleanActivity.getInstance().clickBack();
        Common.getInstance().waitForSecond(200);
        boolean bResult = ContinueCleanActivity.getInstance().translateSameTime(languageMap);
        ContinueCleanActivity.getInstance().clickSwitch();
        //return to settings
        ContinueCleanActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateConsumable(){
        //after check return to setting
        SettingActivity.getInstance().clickConsumable();
        boolean bResult = ConsumableActivity.getInstance().translate(languageMap);
        ConsumableActivity.getInstance().clickBack();
        //SettingActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateFirmVer(){
        //start click more
        //MainActivity.getInstance().clickMore();
        SettingActivity.getInstance().clickFirmware();
        boolean bResult = FirmVerActivity.getInstance().staticUITranslation(languageMap);
        FirmVerActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateRename(){
        SettingActivity.getInstance().clickRename();
        RenameActivity.getInstance().showActivity();
        boolean bResult = RenameActivity.getInstance().translate(languageMap);
        RenameActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateNoTimeSchedule(){
        SettingActivity.getInstance().clickTimeSchedule();
        TimeScheduleActivity.getInstance().showEmptyActivity();
        return TimeScheduleActivity.getInstance().translate(languageMap);
    }

    boolean translateNewSchedule(){
        //will delete
        //SettingActivity.getInstance().clickTimeSchedule();
        //
        TimeScheduleActivity.getInstance().clickAddSchedule();
        boolean bResult = NewScheduleActivity.getInstance().translate(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateRepetition(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bRes = RepetitionActivity.getInstance().staticUITranslation(languageMap);
        RepetitionActivity.getInstance().clickBack();
        NewScheduleActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateSelectWeekOfDate(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateSelectWeekOfDate(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateSelectWeekend(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateWeekend(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateSelectWorkday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateWorkday(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateSelectEveryday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateEveryday(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    private boolean translateAddTimeSchedule(int iDate){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        //set sunday
        NewScheduleActivity.getInstance().clickRepeat();
        RepetitionActivity.getInstance().clickWeekOfDate(iDate);
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickBack();
        //set time
        NewScheduleActivity.getInstance().setStartTime();
        NewScheduleActivity.getInstance().clickConfirmAdd();
        TimeScheduleActivity.getInstance().showAddedActivity();
        boolean bRes = TimeScheduleActivity.getInstance().translateAddNewSchedule(languageMap, iDate);
        TimeScheduleActivity.getInstance().delAllTasks();
        TimeScheduleActivity.getInstance().showEmptyActivity();
        return bRes;
    }

    boolean translateAddTimeSchedule_sun(){
        return translateAddTimeSchedule(0);
    }

    boolean translateAddTimeSchedule_mon(){
        return translateAddTimeSchedule(1);
    }

    boolean translateAddTimeSchedule_tues(){
        return translateAddTimeSchedule(2);
    }

    boolean translateAddTimeSchedule_wed(){
        return translateAddTimeSchedule(3);
    }

    boolean translateAddTimeSchedule_thurs(){
        return translateAddTimeSchedule(4);
    }

    boolean translateAddTimeSchedule_fri(){
        return translateAddTimeSchedule(5);
    }

    boolean translateAddTimeSchedule_sat(){
        return translateAddTimeSchedule(6);
    }

    boolean translateAddTimeSchedule_weekends(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().setStartTime();
        NewScheduleActivity.getInstance().clickRepeat();
        RepetitionActivity.getInstance().clickWeekOfDate(0);
        RepetitionActivity.getInstance().clickWeekOfDate(6);
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickBack();
        NewScheduleActivity.getInstance().clickConfirmAdd();
        boolean bRes = TimeScheduleActivity.getInstance().translateAddNewSchedule(languageMap, 7);
        TimeScheduleActivity.getInstance().delAllTasks();
        TimeScheduleActivity.getInstance().showAddedActivity();
        return bRes;
    }

    boolean translateAddTimeSchedule_workday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().setStartTime();
        NewScheduleActivity.getInstance().clickRepeat();
        for (int i = 1; i < 6; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickBack();
        NewScheduleActivity.getInstance().clickConfirmAdd();
        boolean bRes = TimeScheduleActivity.getInstance().translateAddNewSchedule(languageMap, 8);
        TimeScheduleActivity.getInstance().delAllTasks();
        TimeScheduleActivity.getInstance().showAddedActivity();
        return bRes;
    }

    boolean translateAddTimeSchedule_everyday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().setStartTime();
        NewScheduleActivity.getInstance().clickRepeat();
        for (int i = 0; i < 7; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickBack();
        NewScheduleActivity.getInstance().clickConfirmAdd();
        return TimeScheduleActivity.getInstance().translateAddNewSchedule(languageMap, 9);
        /*TimeScheduleActivity.getInstance().delAllTasks();
        TimeScheduleActivity.getInstance().showAddedActivity();
        return bRes;*/
    }

    boolean translateDelSchedule_Edit(){
        TimeScheduleActivity.getInstance().clickTextDate();
        boolean bRes = NewScheduleActivity.getInstance().trans_edit_del(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        TimeScheduleActivity.getInstance().showEmptyActivity();
        return bRes;
    }

    boolean translateDelSchedule_Swipe(){
        boolean bRes = TimeScheduleActivity.getInstance().translateDel_Swipe(languageMap);
        TimeScheduleActivity.getInstance().clickDel();
        //TimeScheduleActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateAddTimeSchedule_repeat(){
        logger.info("add time schedule 1!!!");
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().setStartTimeNight();
        NewScheduleActivity.getInstance().clickConfirmAdd();
        TimeScheduleActivity.getInstance().showAddedActivity();
        logger.info("add time schedule 2!!!");
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().setStartTimeNight();
        NewScheduleActivity.getInstance().clickConfirmAdd();
        boolean bRepeat = NewScheduleActivity.getInstance().repeatTimeSchedule(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        TimeScheduleActivity.getInstance().delAllTasks();
        TimeScheduleActivity.getInstance().showAddedActivity();
        return bRepeat;
    }

    boolean translateOverTimeSchedule(){
        //
        //SettingActivity.getInstance().clickTimeSchedule();
        //
        for (int i = 0; i < 7; i++){
            System.out.println("i = " + i);
            int iDate = i;
            if(i >= 7){
                iDate = i - 7;
            }
            TimeScheduleActivity.getInstance().clickAddSchedule();
            NewScheduleActivity.getInstance().setStartTime();
            NewScheduleActivity.getInstance().clickRepeat();
            RepetitionActivity.getInstance().clickWeekOfDate(iDate);
            RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
            RepetitionActivity.getInstance().clickBack();
            NewScheduleActivity.getInstance().clickConfirmAdd();
            TimeScheduleActivity.getInstance().showAddedActivity();
            Common.getInstance().waitForSecond(500);
            System.out.println("add time schedule!!! " + iDate);
        }
        TimeScheduleActivity.getInstance().clickAddSchedule();
        boolean bRes = TimeScheduleActivity.getInstance().translateOverTen(languageMap);
        TimeScheduleActivity.getInstance().showAddedActivity();
        TimeScheduleActivity.getInstance().delAllTasks();
        //return settings
        TimeScheduleActivity.getInstance().clickBack();
        //return true;
        return bRes;
    }

}
