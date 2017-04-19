package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 * new schedule activity
 */
public class NewScheduleActivity {
    private static NewScheduleActivity newScheduleActivity = null;
    private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement confirmAdd = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnRepeat = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement repeat = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement repeatValue = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    private MobileElement btnDelete = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAPicker[1]/UIAPickerWheel[1]")
    private MobileElement pickerH = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAPicker[1]/UIAPickerWheel[2]")
    private MobileElement pickerM = null;
    @FindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    private MobileElement overPromptContent = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    private MobileElement overPromptSure = null;


    private NewScheduleActivity(){

    }

    public static NewScheduleActivity getInstance(){
        if (newScheduleActivity == null){
            newScheduleActivity = new NewScheduleActivity();
        }
        return newScheduleActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public boolean trans_edit_del(Map<String, String> tranMap) {
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_appointment_edit"));
        if (!btitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", title.getText(),
                    tranMap.get("random_deebot_appointment_edit"), "fail");
        }
        boolean bbtnDelete = btnDelete.getText().equalsIgnoreCase(tranMap.get("random_deebot_appointment_delete"));
        if (!bbtnDelete) {
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", btnDelete.getText(),
                    tranMap.get("random_deebot_appointment_delete"), "fail");
        }
        return btitle && bbtnDelete;
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_appointment_add"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", title.getText(),
                    tranMap.get("random_deebot_appointment_add"), "fail");
        }
        boolean brepeat = repeat.getText().equalsIgnoreCase(tranMap.get("random_deebot_frequency"));
        if (!brepeat){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", repeat.getText(),
                    tranMap.get("random_deebot_frequency"), "fail");
        }
        String[] weekDays = {"random_deebot_sunday", "random_deebot_monday",
                "random_deebot_Tuesday", "random_deebot_Wednesday",
                "random_deebot_Thursday", "random_deebot_Friday",
                "random_deebot_Saturday"};
        int iIndex = Common.getInstance().getWeekIndex();
        boolean brepeatValue = repeatValue.getText().trim().equalsIgnoreCase(tranMap.get(weekDays[iIndex]).trim());
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", repeatValue.getText().trim(),
                    tranMap.get(weekDays[iIndex]).trim(), "fail");
        }
        return bTitle && brepeat && brepeatValue;
    }

    public void clickRepeat(){
        btnRepeat.click();
    }

    public boolean translateWeekend(Map<String, String> tranMap){
        RepetitionActivity.getInstance().clickWeekOfDate(0);
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickWeekOfDate(6);
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue= repeatValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_weekends"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("random_deebot_weekends"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateWorkday(Map<String, String> tranMap){
        for(int i = 1; i < 6; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue = repeatValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_workdays"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("random_deebot_workdays"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateEveryday(Map<String, String> tranMap){
        for(int i = 0; i < 7; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue= repeatValue.getText().equalsIgnoreCase(tranMap.get("random_deebot_everyday"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("random_deebot_everyday"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateSelectWeekOfDate(Map<String, String> tranMap){
        String[] weekDays = {"random_deebot_sunday", "random_deebot_monday",
                "random_deebot_Tuesday", "random_deebot_Wednesday",
                "random_deebot_Thursday", "random_deebot_Friday",
                "random_deebot_Saturday"};
        boolean brepeatValue[] = new boolean[7];
        //click week of date
        int iOldIndex = Common.getInstance().getWeekIndex();
        for(int i = 0; i < 7; i++){
            //click pre date
            if(i != 0){
                //click repeat
                btnRepeat.click();
            }
            RepetitionActivity.getInstance().clickWeekOfDate(i);
            RepetitionActivity.getInstance().clickWeekOfDate(iOldIndex);
            RepetitionActivity.getInstance().clickBack();
            brepeatValue[i]= repeatValue.getText().trim().equalsIgnoreCase(tranMap.get(weekDays[i]).trim());
            if (!brepeatValue[i]){
                TranslateErrorReport.getInstance().insetNewLine(
                        tranMap.get("language"), "NewSchedule", repeatValue.getText().trim(),
                        tranMap.get(weekDays[i]).trim(), "fail");
            }
            //recovery
            iOldIndex = i;
        }

        return brepeatValue[0] && brepeatValue[1] && brepeatValue[2] && brepeatValue[3] &&
                brepeatValue[4] && brepeatValue[5] && brepeatValue[6];
    }

    private List<String> getCurTimeSchedule(){
        List<MobileElement> list = driver.findElementsByClassName("UIAPickerWheel");
        List<String> listTime = new ArrayList<String>();
        if (list.size() != 0){
            for(MobileElement editText: list){
                String str = editText.getText();
                listTime.add(str.substring(0, str.indexOf(" ")).trim());
            }
        }
        System.out.println(listTime.get(0));
        System.out.println(listTime.get(1));
        return listTime;
    }

    public void setStartTimeNight(){
        Common.getInstance().setStartTimeNight(driver, pickerH, pickerM, getCurTimeSchedule(), 4);
    }

    public void setStartTime(){
        Point point = pickerH.getLocation();
        Dimension dimension = pickerH.getSize();
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight/2;

        driver.swipe(point.x, point.y ,
                point.x, point.y - iHeight/4, 100);
    }

    public boolean repeatTimeSchedule(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bpromptContent = overPromptContent.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_appointment_limit_time"));
        if(!bpromptContent){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", overPromptContent.getText(),
                    tranMap.get("random_deebot_appointment_limit_time"), "fail");
        }
        boolean bpromptSure = overPromptSure.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_btn_known"));
        if(!bpromptSure){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", overPromptSure.getText(),
                    tranMap.get("random_deebot_btn_known"), "fail");
        }
        overPromptSure.click();
        return bpromptContent && bpromptSure;
    }

    public void clickConfirmAdd(){
        confirmAdd.click();
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUITranslation(tranMap) ;
    }



}
