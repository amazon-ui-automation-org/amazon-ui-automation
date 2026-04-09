package com.amazon.tests.useraccount;

import com.amazon.DataProvider.UserAccountDp;
import com.amazon.base.BaseTest;
import com.amazon.pages.LoginPage;
import com.amazon.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginFunctionality extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginFunctionality.class);
    String phoneNum = ConfigReader.get("phoneNum");
    String passwordNum = ConfigReader.get("passwordNum");
    String email = ConfigReader.get("email");
    String username = ConfigReader.get("username");
    String passwordMail = ConfigReader.get("passwordMail");
    String newMail = ConfigReader.get("newMail");
    String newName =  ConfigReader.get("newName");
    String newPassword =  ConfigReader.get("newPassword");
    String newNum =ConfigReader.get("newNum");
    String UnregisteredPassword = "saiknght";

    @Test
    public void VerifyPasswordAssistancePageOpenUponClickingForgotPassword() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Sign In page");
        lp.navigateToSignInPage();

        log.info("Step 2: Entering registered email: {}", email);
        lp.enterMailORNum(email);

        log.info("Step 3: Clicking Continue to navigate to password page");
        lp.clickContinue();

        log.info("Step 4: Clicking Forgot Password link");
        lp.clickForgotPasswordLink();

        log.info("Step 5: Verifying Password Assistance page is displayed");

        Assert.assertTrue(lp.isPasswordAssistancePageVisible(),
                "Password Assistance page is not visible");

        log.info("Password Assistance page displayed successfully");

    }

    //abc
    //@gmail.com
    //abc@gmail


//    @DataProvider(name ="invalid mail format")
//    public Object [][] invalidmail(){
//        return new Object [][]{
//                {"abc"},
//                {"@gmail.com"},
//                {"abc@gmail"}
//        };
//    }

    @Test(dataProvider = "userAccountDpMail",dataProviderClass = UserAccountDp.class)
    public void verifyErrorMessageForInvalidMailFormat(String mail){
        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Sign In page");
        lp.navigateToSignInPage();

        log.info("Step 2: Entering invalid email: {}", mail);
        lp.enterMailORNum(mail);

        log.info("Step 3: Clicking Continue");
        lp.clickContinue();

        log.info("Step 4: Verifying error message for invalid email format");

        Assert.assertTrue(lp.isInvalidEmailFormatErrorDisplayed(),
                "Mail Format Error Message Not Displayed");

        log.info("Error message displayed correctly for invalid email: {}", mail);
    }



    @Test(dataProvider = "userAccountDpNumber",dataProviderClass = UserAccountDp.class)
    public void VerifyErrorMessageForInvalidPhoneFormat(String phone){

        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Sign In page");
        lp.navigateToSignInPage();

        log.info("Step 2: Entering invalid phone number: {}", phone);
        lp.enterMailORNum(phone);

        log.info("Step 3: Clicking Continue");
        lp.clickContinue();

        log.info("Step 4: Verifying error message for invalid phone number");

        Assert.assertTrue(lp.isInvalidPhoneFormatErrorDisplayed(),
                "Phone Format Error Message Not Displayed");

        log.info("Error message displayed correctly for invalid phone: {}", phone);

    }



    @Test
    public void verifyMandatoryFieldErrorOnBlankEmailORPhoneNumber(){
        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Sign In page");
        lp.navigateToSignInPage();

        log.info("Step 2: Leaving email/phone field blank");
        lp.enterMailORNum("");

        log.info("Step 3: Clicking Continue");
        lp.clickContinue();

        log.info("Step 4: Verifying mandatory field error");

        Assert.assertTrue(lp.isMandatoryFieldErrorDisplayed(),
                "Mandatory Field Error Message Not Displayed");

        log.info("Mandatory field error displayed successfully");

    }



    @Test
    public void verifyErrorMessageOnEnteringUnregisteredPasswordAfterValidMail(){
        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Sign In page");
        lp.navigateToSignInPage();

        log.info("Step 2: Entering valid email: {}", email);
        lp.enterMailORNum(email);

        log.info("Step 3: Clicking Continue");
        lp.clickContinue();

        log.info("Step 4: Entering invalid/unregistered password");
        lp.enterPassword(UnregisteredPassword);

        log.info("Step 5: Clicking Sign In");
        lp.clickSignIn();

        log.info("Step 6: Verifying authentication error message");

        Assert.assertTrue(lp.isAuthenticationErrorDisplayed(),
                "Authentication Error is not Displayed");

        log.info("Authentication error displayed successfully");
    }


    @DataProvider(name="InvalidPhoneNumberOnSignup")
    public Object [][] InvalidPhoneNumberOnSignup(){
        return new Object [][] {
                {"725787989"},
                {"85389914678"},
                {"5367680909"}
        };
    }


@Test(dataProvider = "InvalidPhoneNumberOnSignup")
    public void verifyerrorMessageForInvalidPhoneNumberOnSignup(String phone){
    LoginPage lp = new LoginPage(driver);
    log.info("Step 1: Navigating to Signup page");
    lp.navigateToSignUpPage(newMail);


    log.info("Step 2: Entering phone number: {}", newNum);
    lp.enterMobileNumberForSignup(phone);

    log.info("Step 3: Entering name: {}", newName);
    lp.enterNameForSignup(newName);

    log.info("Step 4: Entering invalid password");
    lp.enterNewpassword(newPassword);

    log.info("Step 5: Clicking Verify Phone Number");
    lp.clickVerifyPhoneNumber();

    log.info("Step 6: Verifying phone format error message");

    Assert.assertTrue(lp.isErrorMessageForInvalidPhoneNumberOnSignup(),
            "Phone Number Format Error Message Not Displayed on signup");

    log.info("Invalid phone number format error displayed correctly");

}

    @DataProvider(name="InvalidPasswordOnSignup")
    public Object [][] InvalidPasswordOnSignup(){
        return new Object [][] {
                {"abcde"},
        };
    }


    @Test(dataProvider = "InvalidPasswordOnSignup")
    public void verifyerrorMessageForInvalidPasswordOnSignup(String password){
        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Signup page");
        lp.navigateToSignUpPage(newMail);

        log.info("Step 2: Entering phone number: {}", newNum);
        lp.enterMobileNumberForSignup(newNum);

        log.info("Step 3: Entering name: {}", newName);
        lp.enterNameForSignup(newName);

        log.info("Step 4: Entering invalid password");
        lp.enterNewpassword(password);

        log.info("Step 5: Clicking Verify Phone Number");
        lp.clickVerifyPhoneNumber();

        log.info("Step 6: Verifying password error message");

        Assert.assertTrue(lp.isErrorMessageForInvalidPasswordOnSignup(),
                "Password Format Error Message Not Displayed on signup");

        log.info("Invalid password error displayed correctly");
    }

    @Test
    public void verifyMandatoryFieldMessageForBlankPasswordOnSignup(){
        //only for desktop
        LoginPage lp = new LoginPage(driver);
        lp.navigateToSignUpPage(newMail);
        lp.enterMobileNumberForSignup(newNum);
        lp.enterNameForSignup(newName);
        lp.enterNewpassword("");
        lp.clickVerifyPhoneNumber();
        Assert.assertTrue(lp.isMandatoryPasswordFieldErrorMessageDispalyed(),"Mandatory Password Field error message not displayed");
    }

    @Test
    public void verifyMandatoryFieldMessageForBlankNameOnSignup(){
        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Signup page with email: {}", newMail);
        lp.navigateToSignUpPage(newMail);

        log.info("Step 2: Entering mobile number: {}", newNum);
        lp.enterMobileNumberForSignup(newNum);

        log.info("Step 3: Leaving name field blank");
        lp.enterNameForSignup("");

        log.info("Step 4: Entering password");
        lp.enterNewpassword(newPassword);

        log.info("Step 5: Clicking Verify Phone Number");
        lp.clickVerifyPhoneNumber();

        log.info("Step 6: Verifying mandatory error message for name field");

        Assert.assertTrue(lp.isMandatoryNameFieldErrorMessageDispalyed(),
                "Mandatory Name Field error message not displayed");

        log.info("Mandatory name field error displayed successfully");
    }

    @Test
    public void verifyMandatoryFieldMessageForBlankNumberOnSignup(){
        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Signup page with email: {}", newMail);
        lp.navigateToSignUpPage(newMail);

        log.info("Step 2: Leaving phone number field blank");
        lp.enterMobileNumberForSignup("");

        log.info("Step 3: Entering name: {}", newName);
        lp.enterNameForSignup(newName);

        log.info("Step 4: Entering password");
        lp.enterNewpassword(newPassword);

        log.info("Step 5: Clicking Verify Phone Number");
        lp.clickVerifyPhoneNumber();

        log.info("Step 6: Verifying mandatory error message for phone number");

        Assert.assertTrue(lp.isMandatoryPhoneNumberFieldErrorMessageDispalyed(),
                "Mandatory PhoneNumber Field error message not displayed");

        log.info("Mandatory phone number field error displayed successfully");
    }
@Test
public void verifyErrorOnInvalidOtpOnSignup(){

    LoginPage lp = new LoginPage(driver);

    log.info("Step 1: Navigating to Signup page with email: {}", newMail);
    lp.navigateToSignUpPage(newMail);

    log.info("Step 2: Leaving phone number field blank");
    lp.enterMobileNumberForSignup("");

    log.info("Step 3: Entering name: {}", newName);
    lp.enterNameForSignup(newName);

    log.info("Step 4: Entering password");
    lp.enterNewpassword(newPassword);

    log.info("Step 5: Clicking Verify Phone Number");
    lp.clickVerifyPhoneNumber();

    log.info("Step 6: Verifying mandatory error message for phone number");

    Assert.assertTrue(lp.isInvalidOtpMessageDispalyed(),
            "invalid otp message not displayed");

    log.info("Invalid Otp Message dispalyed successfully");



}

    @Test
    public void verifyMandatoryFiledMessageOnBlankOtpOnSignUP(){


        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Triggering OTP for signup with email: {}", newMail);
        lp.triggerOtpOnSignUp(newMail, newNum, newName, newPassword);

        log.info("Step 2: Entering invalid OTP: 1234");
        lp.enterOtpForSignup("1234");

        log.info("Step 3: Verifying invalid OTP error message");

        Assert.assertTrue(lp.isInvalidOtpMessageDispalyed(),
                "Invalid Otp Message Not Displayed");

        log.info("Invalid OTP error displayed successfully on signup");



    }

    @Test
    public void verifyMandatoryFiledMessageOnBlankOtpOnSignIn(){

        LoginPage lp = new LoginPage(driver);
        log.info("Step 1: Navigating to Sign In page");
       lp.navigateToSignInPage();
        log.info("Step 2: Entering phone number: {}", phoneNum);
       lp.enterMailORNum(phoneNum);
        log.info("Step 3: Clicking Continue");
       lp.clickContinue();
        log.info("Step 4: Triggering OTP for Sign In");
       lp.clickSendOtpSignIn();
        log.info("Step 5: Leaving OTP field blank");
        lp.enterOtpForSignup("");
        log.info("Step 6: Verifying mandatory OTP error message");
        Assert.assertTrue(lp.isMandatoryOTPFieldMessageDispalyed(),"mandatory Otp Message Not Displayed");
        log.info("Mandatory OTP field error displayed successfully on Sign Up");
    }

    @Test
    public void verifyErrorMessageOnInvalidOtpOnSignIn(){


        LoginPage lp = new LoginPage(driver);

        log.info("Step 1: Navigating to Sign In page");
        lp.navigateToSignInPage();

        log.info("Step 2: Entering phone number: {}", phoneNum);
        lp.enterMailORNum(phoneNum);

        log.info("Step 3: Clicking Continue");
        lp.clickContinue();

        log.info("Step 4: Triggering OTP for Sign In");
        lp.clickSendOtpSignIn();

        log.info("Step 5: Leaving OTP field blank");
        lp.enterOtpForSignup("123");

        log.info("Step 6: Verifying mandatory OTP error message");

        Assert.assertTrue(lp.isInvalidOtpMessageDispalyed(),
                "Invalid Otp Message Not Displayed");

        log.info("Invalid OTP field error displayed successfully on Sign In");
    }















}
