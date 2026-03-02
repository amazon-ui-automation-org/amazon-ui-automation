package com.amazon.tests.useraccount;

import com.amazon.base.BaseTest;
import com.amazon.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_smoke extends BaseTest {
    String phoneNum="7259356897";
    String password = "SAIBABA123";


    @Test

    public void verifyUserCanLoginWithValidCreds() throws InterruptedException {
       // m=3;
        //System.out.println(m);
        //m2();
        LoginPage lp = new LoginPage(driver);
        lp.clickSignIn();
        lp.enterMailORNum(phoneNum);
        lp.clickContinue();
        //Thread.sleep(2000);
        lp.enterPassword(password);
       // Thread.sleep(2000);
        lp.signInSubmit();
        lp.clicksiginafterlogin();
        Assert.assertTrue(lp.locateSignoutButton(),"SIgn In not sucessful");
    }
}
