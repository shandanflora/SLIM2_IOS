package com.ecovacs.test.common;

//import io.appium.java_client.AppiumDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

//import org.openqa.selenium.remote.Augmenter;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;

/**
 * Created by ecosqa on 16/7/27.
 * description:common function
 */
public class Common {
    //parameter
    private static Logger logger = LoggerFactory.getLogger(Common.class);
    private static Common common = null;

    //
    /*
    private enum FailType{
        NOT_REGISTER, //user not register
        ALREADY_REGISTER //user had registered
    }*/

    private Common(){

    }

    public static Common getInstance(){
        if(common == null){
            common = new Common();
        }
        return common;
    }

    public IOSDriver getDriver(){
        IOSDriver driver = null;
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.VERSION, "9.2.1");
        capabilities.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS,true);
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "Mac");
        capabilities.setCapability("deviceName","iphone 4s");
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("newCommandTimeout", 0);
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        try {
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
            logger.info("exception: " + e.toString());
        }
        return driver;
    }

    boolean delAllFile(String path) {
        File file = new File(path);
        File temp;
            String[] tempList = file.list();
            if(tempList == null){
                return false;
            }
            for(String strFile:tempList){
                if (path.endsWith(File.separator)) {
                    temp = new File(path + strFile);
                } else {
                    temp = new File(path + File.separator + strFile);
                }
                if (temp.isFile()) {
                    if(!temp.delete()){
                        return false;
                    }
                }
            }
        return true;
    }
/*
    public boolean screenShot(String strFileName, WebDriver driver){
        TakesScreenshot screen = (TakesScreenshot ) new Augmenter().augment(driver);
        String strPath = getClass().getResource("/").getPath()
                        + "../" + "screenShots/";
        //check
        File folder = new File(strPath);
        if(!folder.exists() && !folder.isDirectory()){
            if(!folder.mkdir()){
                return false;
            }
        }else {
            delAllFile(strPath);
        }
        File ss = new File(strPath + strFileName);
        return screen.getScreenshotAs(OutputType.FILE).renameTo(ss);
    }*/

    public void waitForSecond(int iMillSecond){
        try {
            Thread.sleep(iMillSecond);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /*public void goBack(AppiumDriver driver, int iLoop){
        for(int i = 0; i < iLoop; i++){
            driver.navigate().back();
            waitForSecond(300);
        }

    }

    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if(children == null){
                return false;
            }
            //recursion delete subfolder
            for(String strFile:children) {
                boolean success = deleteDir(new File(dir, strFile));
                if (!success) {
                    return false;
                }
            }
        }
        //delete empty folder or file
        return dir.delete();
    }

    public String executeCommand(String command) {

        StringBuilder output = new StringBuilder();
        Process process;
        try {
            //ProcessBuilder pb = new ProcessBuilder(command);
            process = Runtime.getRuntime().exec(command);
            //process = pb.start();
            process.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                output.append(line);
                output.append("\n");
                System.out.println(output.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }*/

    public boolean showActivity(WebElement element){
        boolean bResult = false;
        int iLoop = 0;
        while (true) {
            if(iLoop > 50){
                break;
            }
            try {
                if (element.isEnabled()) {
                    bResult = true;
                    //logger.info("Show activity!!!");
                    break;
                }
            } catch (Exception e) {
                //logger.error(e.toString());
                logger.info("Not show activity, try again!!!");
                waitForSecond(500);
            }
            iLoop++;
        }
        return bResult;
    }

    /**
     *
     * @return 0-6
     */
    public int getWeekIndex(){
        Calendar cal = Calendar.getInstance();
        int iIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (iIndex < 0){
            iIndex = 0;
        }
        return iIndex;
    }

    /**
     * set time 00:00
     */

    public void setStartTimeNight(IOSDriver driver, MobileElement pickerH, MobileElement pickerM, List<String> listTime, int iDire){
        //get current time
        int iHour = Integer.parseInt(listTime.get(0));
        int iMin = Integer.parseInt(listTime.get(1));
        System.out.println("current hour: " + iHour);
        System.out.println("current minute: " + iMin);

        Point pointH = pickerH.getLocation();
        Dimension dimensionH = pickerH.getSize();
        int iRectX_H = pointH.getX();
        int iRectY_H = pointH.getY();
        int iWidth_H = dimensionH.getWidth();
        int iHeight_H = dimensionH.getHeight();

        pointH.x = iRectX_H+ iWidth_H/2;
        pointH.y = iRectY_H + iHeight_H/2;

        Point pointM = pickerM.getLocation();
        Dimension dimensionM = pickerM.getSize();
        int iRectX_M = pointM.getX();
        int iRectY_M = pointM.getY();
        int iWidth_M = dimensionM.getWidth();
        int iHeight_M = dimensionM.getHeight();

        pointM.x = iRectX_M + iWidth_M/2;
        pointM.y = iRectY_M + iHeight_M/2;
        //set time 00:00
        for(int i = 0; i < iHour; i++){
            driver.swipe(pointH.x, pointH.y ,
                    pointH.x, pointH.y + iHeight_H/iDire, 200);
            Common.getInstance().waitForSecond(500);
        }
        for (int j = 0; j < iMin; j++){
            driver.swipe(pointM.x, pointM.y ,
                    pointM.x, pointM.y + iHeight_M/iDire, 200);
            Common.getInstance().waitForSecond(500);
        }
    }

}
