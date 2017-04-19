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

import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 * time schedule activity
 */
public class TimeScheduleActivity {
    private static TimeScheduleActivity timeScheduleActivity = null;
    private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement emptyList = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement btnAddTime = null;
    //new time schedule
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")
    private MobileElement textDate = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]")
    private MobileElement btnDelete = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]")
    private MobileElement listView = null;
    //over time schedule
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    private MobileElement overPromptContent = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    private MobileElement overPromptSure = null;

    private enum DIRECTION{
        RIGHT_TO_LEFT,
        LEFT_TO_RIGHT
    }

    private TimeScheduleActivity(){

    }

    public static TimeScheduleActivity getInstance(){
        if (timeScheduleActivity == null){
            timeScheduleActivity = new TimeScheduleActivity();
        }
        return timeScheduleActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public boolean showEmptyActivity(){
        return Common.getInstance().showActivity(emptyList);
    }

    public void showAddedActivity(){
        Common.getInstance().showActivity(textDate);
    }

    private boolean staticUITranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_appointment"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", title.getText(),
                    tranMap.get("random_deebot_appointment"), "fail");
        }
        boolean bemptyList = emptyList.getText().equalsIgnoreCase(tranMap.get("random_deebot_appointment_empty"));
        if (!bemptyList){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", emptyList.getText(),
                    tranMap.get("random_deebot_appointment_empty"), "fail");
        }
        return bTitle && bemptyList;
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUITranslate(tranMap);
    }

    public void clickAddSchedule(){
        btnAddTime.click();
    }

    public boolean translateAddNewSchedule(Map<String, String> tranMap, int iIndex){
        String[] weekDays = {"random_deebot_sunday", "random_deebot_monday",
                "random_deebot_Tuesday", "random_deebot_Wednesday",
                "random_deebot_Thursday", "random_deebot_Friday",
                "random_deebot_Saturday", "random_deebot_weekends",
                "random_deebot_workdays", "random_deebot_everyday",
                "random_deebot_never"};
        String strLanguage = tranMap.get("language");
        Common.getInstance().showActivity(textDate);
        boolean btextDate = textDate.getText().trim().equalsIgnoreCase(weekDays[iIndex]);
        if (!btextDate){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", textDate.getText().trim(),
                    tranMap.get(weekDays[iIndex]), "fail");
        }
        return btextDate;
    }

    private void swipeNewTask(DIRECTION direction){
        //swipe
        Point point = textDate.getLocation();
        Dimension dimension = textDate.getSize();
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight/2;

        if (direction == DIRECTION.RIGHT_TO_LEFT){
            driver.swipe(point.x + iWidth/4, point.y ,
                    point.x - iWidth/4, point.y, 100);
        }else if(direction == DIRECTION.LEFT_TO_RIGHT){
            driver.swipe(point.x - iWidth/4, point.y ,
                    point.x + iWidth/4, point.y, 100);
        }
    }

    private void delTime_swipe(){
        swipeNewTask(DIRECTION.RIGHT_TO_LEFT);
        clickDel();
    }

    public void clickDel(){
        btnDelete.click();
    }

    public boolean translateDel_Swipe(Map<String, String> tranMap){
        swipeNewTask(DIRECTION.RIGHT_TO_LEFT);
        boolean bbtnDelete = btnDelete.getText().equalsIgnoreCase(tranMap.get("random_deebot_delete"));
        if (!bbtnDelete){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "TimeSchedule", btnDelete.getText(),
                    tranMap.get("random_deebot_delete"), "fail");
        }
        return bbtnDelete;
    }

    public void clickTextDate(){
        textDate.click();
    }

    public boolean translateOverTen(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean boverPromptContent = overPromptContent.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_over_max").trim());
        if (!boverPromptContent){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", overPromptContent.getText().trim(),
                    tranMap.get("random_deebot_over_max").trim(), "fail");
        }
        boolean boverPromptSure = overPromptSure.getText().equalsIgnoreCase(tranMap.get("random_deebot_btn_known").trim());
        if (!boverPromptSure){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", overPromptSure.getText(),
                    tranMap.get("random_deebot_btn_known").trim(), "fail");
        }
        overPromptSure.click();
        return boverPromptContent && boverPromptSure;
    }

    public void delAllTasks(){
        List<MobileElement> lists = listView.findElementsByClassName("UIATableCell");
        int i = lists.size();
        System.out.println("i =" + i);
        while (!emptyList.isDisplayed()){
            delTime_swipe();
            Common.getInstance().showActivity(listView);
            Common.getInstance().waitForSecond(500);
            i = listView.findElementsByClassName("UIATableCell").size();
            System.out.println("i =" + i);
        }
    }
}
