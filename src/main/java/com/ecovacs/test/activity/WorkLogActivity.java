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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * work log activity
 */
public class WorkLogActivity {
    private static WorkLogActivity workLogActivity = null;
    private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement timeTotal = null;*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement timeTips = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[1]/UIAStaticText[1]")
    private MobileElement cleanHistory = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]")
    private MobileElement listHistory = null;

    private WorkLogActivity(){

    }

    public static WorkLogActivity getInstance(){
        if(workLogActivity == null){
            workLogActivity = new WorkLogActivity();
        }
        return workLogActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("random_deebot_work_log"));
        if (!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Work log", title.getText(),
                    tranMap.get("random_deebot_work_log"), "fail");
        }
        boolean btimeTips = timeTips.getText().equalsIgnoreCase(tranMap.get("random_deebot_time_all"));
        if (!btimeTips){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Work log", timeTips.getText(),
                    tranMap.get("random_deebot_time_all"), "fail");
        }
        boolean bcleanHistory = cleanHistory.getText().equalsIgnoreCase(tranMap.get("random_deebot_work_log1"));
        if (!bcleanHistory){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Work log", cleanHistory.getText(),
                    tranMap.get("random_deebot_work_log1"), "fail");
        }
        return btitle && btimeTips && bcleanHistory;
    }

    private boolean translateListView(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        List<MobileElement> textViews = listHistory.findElementsByClassName("UIAStaticText");
        int iSize = textViews.size();
        System.out.println("textViews.size()--" + iSize);
        int i = 0;
        for(MobileElement textView: textViews) {
            if (i == 0){
                i++;
                continue;
            }
            //System.out.println("textView--" + textView.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
            boolean dateFlag=true;
            try {
                Date date = sdf.parse(textView.getText());
            } catch (ParseException e){
                dateFlag=false;
            }finally{
                if (!dateFlag) {//not date
                    int iIndex = textView.getText().trim().indexOf(" ");
                    //today
                    if (iIndex == -1){
                        boolean bToday = textView.getText().trim().equalsIgnoreCase(tranMap.get("random_deebot_today"));
                        if (!bToday){
                            TranslateErrorReport.getInstance().insetNewLine(
                                    strLanguage, "Work log", textView.getText().trim(),
                                    tranMap.get("random_deebot_today"), "fail");
                        }
                        System.out.println("today--" + textView.getText());
                    }else {//duration
                        String strDur = textView.getText().trim().substring(0, iIndex);
                        boolean bDuration = strDur.equalsIgnoreCase(tranMap.get("random_deebot_time"));
                        if (!bDuration){
                            TranslateErrorReport.getInstance().insetNewLine(
                                    strLanguage, "Work log", strDur,
                                    tranMap.get("random_deebot_time"), "fail");
                        }
                        System.out.println("duration--" + strDur);
                    }
                }
            }
        }
        return true;
    }

    private boolean historyListView(Map<String, String> tranMap){
        translateListView(tranMap);
        System.out.println("list page-- 1");
        swipeList();
        Common.getInstance().waitForSecond(1000);
        translateListView(tranMap);
        System.out.println("list page-- 2");
        return true;
    }

    private void swipeList(){
        Point point = listHistory.getLocation();
        Dimension dimension = listHistory.getSize();
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight/2;

        driver.swipe(point.x, point.y ,
                point.x, point.y - iHeight/3, 500);
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bHis = historyListView(tranMap);
        return bStatic && bHis;
    }

}
