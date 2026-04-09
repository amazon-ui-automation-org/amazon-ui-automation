package com.amazon.tests.useraccount;

import com.amazon.Listeners.Listener;
import com.amazon.base.BaseTest;
import com.amazon.base.BaseTestThreadSafe;
import com.amazon.factory.DriverFactory;
import com.amazon.pages.LoginPage;
import com.amazon.pages.LoginPageMob;
import com.amazon.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class LoginsmokeTest extends BaseTestThreadSafe {

    private static final Logger log = LogManager.getLogger(LoginsmokeTest.class);

    String phoneNum = ConfigReader.get("phoneNum");
    String passwordNum = ConfigReader.get("passwordNum");
    String email = ConfigReader.get("email");
    String username = ConfigReader.get("username");
    String passwordMail = ConfigReader.get("passwordMail");
    String newMail = ConfigReader.get("newMail");
    String newName =  ConfigReader.get("newName");
    String newPassword =  ConfigReader.get("newPassword");
    String newNum =ConfigReader.get("newNum");



    @Test(groups = "smoke",priority = 2)
    public void verifyUserCanLoginWithRegisteredPhoneNumAndPassword() {

        LoginPageMob lp = new LoginPageMob(DriverFactory.getDriver());
        log.info("Navigating to signin page");
        lp.clickSignIn();
        log.info("Entering phone number in phone/no oe email field");
        lp.enterMailORNum(phoneNum);
        log.info("Navigating to password page after entering phone number in phone/no oe email field");
        lp.clickContinue();
        log.info("Entering password in password field");
        lp.enterPassword(passwordNum);
        log.info("Clicking on signin");
        lp.signInSubmit();
        log.info("Navigating to account menu");
        lp.clicksiginafterlogin();

        log.info("Verifying if signed in sucessfully");


        Assert.assertTrue(lp.locateSignoutButton(), "Sign out button not visible");
        //Assert.assertTrue(lp.usernameDisplayed().contains(username),
               // "Username not displayed on login");

        log.info("Signin sucessful for user: {}", username);
    }

    @Test(groups = "smoke",priority = 1)
    public void verifyUserCanLoginWithRegisteredEmailAndPassword() {

        LoginPageMob lp = new LoginPageMob(DriverFactory.getDriver());

        log.info("Navigating to sign in page");
        lp.clickSignIn();

        log.info("Entering email: {}", email);
        lp.enterMailORNum(email);

        log.info("Clicking Continue to navigate to password page");
        lp.clickContinue();

        log.info("Entering password");
        lp.enterPassword(passwordMail);

        log.info("Clicking Sign In");
        lp.signInSubmit();

        log.info("Opening account menu");
        lp.clicksiginafterlogin();

        log.info("Verifying login success");

        Assert.assertTrue(lp.locateSignoutButton(), "Sign out button not visible");

        String actualName = lp.usernameDisplayed();
       // Assert.assertTrue(actualName.contains(username),
           //     "Username not displayed on login");

        log.info("Login successful for user: {}", username);
    }

    @Test(groups = "smoke", priority = 5)
    public void verifyUserCanLoginWithRegisteredPhonenumAndOtp() throws InterruptedException {

        LoginPageMob lp = new LoginPageMob(DriverFactory.getDriver());

        log.info("Navigating to sign in page");
        lp.clickSignIn();

        log.info("Entering phone number: {}", phoneNum);
        lp.enterMailORNum(phoneNum);

        log.info("Clicking Continue");
        lp.clickContinue();

        log.info("Triggering OTP");
        lp.triggerOtp();

        log.info("Waiting for OTP (temporary - to be replaced with explicit wait)");
        Thread.sleep(5000);

        log.info("Submitting OTP");
        lp.submitOtp();

        log.info("Verifying login success");

        Assert.assertTrue(lp.isSignoutVisible(), "Sign out button not visible");

        String actualName = lp.usernameDisplayed();
        Assert.assertTrue(actualName.contains(username),
                "Username not displayed on login");

        log.info("OTP login successful for user: {}", username);
    }

    @Test(groups = "smoke", priority = 3)
    public void verifyUserCanLogoutSuccessfully() {

        LoginPageMob lp = new LoginPageMob(DriverFactory.getDriver());

        log.info("Navigating to sign in page");
        lp.clickSignIn();

        log.info("Entering email: {}", email);
        lp.enterMailORNum(email);

        log.info("Clicking Continue");
        lp.clickContinue();

        log.info("Entering password");
        lp.enterPassword(passwordMail);

        log.info("Clicking Sign In");
        lp.signInSubmit();

        log.info("Opening account menu");
        lp.clicksiginafterlogin();

        log.info("Clicking Sign Out");
        lp.clickSignout();

        log.info("Verifying user is logged out");

        Assert.assertTrue(lp.isSignInPageVisible(), "Sign out unsuccessful");

        log.info("Logout successful");
    }


@Test(groups = "smoke", priority = 4)
    public void VerifyUserCanSignupSucessfullyUsingValidCreds(){
    LoginPageMob lp = new LoginPageMob(DriverFactory.getDriver());

    log.info("Navigating to sign in page");
    lp.clickSignIn();

    log.info("Entering new email: {}", newMail);
    lp.enterMailORNum(newMail);

    log.info("Clicking Continue to go to signup");
    lp.clickContinue();

    log.info("Proceeding to create account");
    lp.proceedToCreateAcoount();

    log.info("Entering mobile number: {}", newNum);
    lp.enterMobileNumberForSignup(newNum);

    log.info("Entering name: {}", newName);
    lp.enterNameForSignup(newName);

    log.info("Entering password");
    lp.enterNewpassword(newPassword);

    log.info("Submitting signup form");
    lp.clickVerifyPhoneNumber();

    log.info("Verifying signup/login success");

    Assert.assertTrue(lp.isSignInPageVisible(), "Signup/Login flow failed");

    log.info("Signup flow completed successfully for user: {}", newName);

}

}