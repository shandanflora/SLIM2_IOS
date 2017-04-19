package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/14.
 *
 */
public class ChangePassActivity {
    private static ChangePassActivity changePassActivity = null;
    private static Logger logger = LoggerFactory.getLogger(ChangePassActivity.class);

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private MobileElement textMessage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement line1_Old = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]")
    private MobileElement editOldPass = null;
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]/UIAButton[1]")
    private MobileElement btnClearOldPass = null;*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement line3_new = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[2]")
    private MobileElement editNewPass = null;
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[2]/UIAButton[1]")
    private MobileElement btnClearNewPass = null;*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[3]")
    private MobileElement editRePass = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnSavePass = null;


    private ChangePassActivity(){

    }

    public static ChangePassActivity getInstance(){
        if (changePassActivity == null){
            changePassActivity = new ChangePassActivity();
        }
        return changePassActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(title);
    }

    public void clickBack(){
        back.click();
    }

    private boolean staticUITranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("Change_the_password"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", title.getText(),
                    tranMap.get("Change_the_password"), "fail");
        }
        boolean bline1_Old = line1_Old.getText().equalsIgnoreCase(tranMap.get("The_old_password"));
        if(!bline1_Old){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", line1_Old.getText(),
                    tranMap.get("The_old_password"), "fail");
        }
        boolean beditOldPass = editOldPass.getText().equalsIgnoreCase(tranMap.get("input_pass_old"));
        if(!beditOldPass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", editOldPass.getText(),
                    tranMap.get("input_pass_old"), "fail");
        }
        logger.info(editOldPass.getText());
        boolean bline3_new = line3_new.getText().equalsIgnoreCase(tranMap.get("The_new_password"));
        if(!bline3_new){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", line3_new.getText(),
                    tranMap.get("The_new_password"), "fail");
        }
        boolean beditNewPass = editNewPass.getText().equalsIgnoreCase(tranMap.get("email_pass_new_8_20"));
        if(!beditNewPass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", editNewPass.getText(),
                    tranMap.get("email_pass_new_8_20"), "fail");
        }
        boolean beditRePass = editRePass.getText().trim().equalsIgnoreCase(tranMap.get("Please_enter_a_new_password_again"));
        if(!beditRePass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", editRePass.getText(),
                    tranMap.get("Please_enter_a_new_password_again"), "fail");
        }
        logger.info(editRePass.getText());
        boolean bbtnSavePass = btnSavePass.getText().equalsIgnoreCase(tranMap.get("Save_the_password"));
        if(!bbtnSavePass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", btnSavePass.getText(),
                    tranMap.get("Save_the_password"), "fail");
        }
        return btitle && bline1_Old && beditOldPass &&
                bline3_new && beditNewPass && beditRePass &&
                bbtnSavePass;
    }

    private boolean emptyOldPass(Map<String, String> tranMap){
        editOldPass.click();
        line1_Old.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("input_pass_old"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("input_pass_old"), "fail");
        }
        return btextMessage;
    }

    private boolean invalidOldPass(Map<String, String> tranMap){
        editOldPass.sendKeys("q");
        line1_Old.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextMessage;
    }

    private boolean emptyNewPass(Map<String, String> tranMap){
        editOldPass.sendKeys(PropertyData.getProperty("login_pass"));
        editNewPass.click();
        line3_new.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("input_pass_new"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("input_pass_new"), "fail");
        }
        return btextMessage;
    }

    private boolean invalidNewPass(Map<String, String> tranMap){
        editNewPass.sendKeys("1");
        line3_new.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextMessage;
    }

    private boolean emptyRePass(Map<String, String> tranMap){
        editNewPass.sendKeys("123456fg");
        line3_new.click();
        editRePass.click();
        line3_new.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("Please_enter_a_new_password_again"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("Please_enter_a_new_password_again"), "fail");
        }
        return btextMessage;
    }

    private boolean invalidRePass(Map<String, String> tranMap){
        editRePass.sendKeys("1");
        line3_new.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextMessage;
    }

    private boolean samePassword(Map<String, String> tranMap){
        editNewPass.sendKeys(PropertyData.getProperty("login_pass"));
        line3_new.click();
        editRePass.sendKeys(PropertyData.getProperty("login_pass"));
        line3_new.click();
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_old_new_no"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_old_new_no"), "fail");
        }
        return btextMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bstatic = staticUITranslate(tranMap);
        boolean bemptyOldPass = emptyOldPass(tranMap);
        boolean binvalidOldPass = invalidOldPass(tranMap);
        boolean bemptyNewPass = emptyNewPass(tranMap);
        boolean binvalidNewPass = invalidNewPass(tranMap);
        boolean bemptyRePass = emptyRePass(tranMap);
        boolean binvalidRePass = invalidRePass(tranMap);
        boolean bSame = samePassword(tranMap);
        return bstatic && bemptyOldPass && binvalidOldPass &&
                bemptyNewPass && binvalidNewPass && bemptyRePass &&
                binvalidRePass && bSame;
    }

}
