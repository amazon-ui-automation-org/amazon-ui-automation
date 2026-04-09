package com.amazon.pages;

import com.amazon.base.BaseTestThreadSafe;
import com.amazon.utils.MouseActions;
import com.amazon.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class LoginPageMob {
    private WebDriver driver;
    private WaitUtils wait;
    private By signInButton = By.id("nav-link-accountList-nav-line-1");
    private By mobileInput = By.id("ap_email_login");
    private By continueButton = By.xpath("//input[@aria-labelledby='continue-announce']");
    private By passwordInput = By.id("ap_password");
    private By hamburgerNav= By.id("nav-hamburger-menu");
    private By signOutButton = By.xpath("//a[text()='Sign Out']");
    private By signInSubmit = By.id("signInSubmit");
    private By invalidMailFormatError = By.xpath("//div[contains(text(),'Invalid email')]");
    private By invalidPhoneFormatError = By.xpath("//div[contains(text(),'Invalid mobile')]");
   // private By invalidPhoneValidation = By.xpath("//div[contains(text(),'Invalid mobile')]");
    private By mandatoryFieldError = By.xpath("//div[contains(text(),'Enter your mobile number')]");
    //private By blankPasswordValidation = By.xpath("//div[contains(text(),'Enter your password')]");
    private By authenticationError = By.xpath("//div[contains(.,'Your password is incorrect') and contains(@class,'a-alert-content')]");
    private By usernameText = By.id("nav-link-accountList-nav-line-1");
    private By usernameTextMob = By.id("nav-greeting-name");
    private MouseActions actions;
    private By signouthover= By.id("nav-item-signout");
    private By accountsAndLists = By.xpath("//a[@aria-controls='nav-flyout-accountList']");
   // private By sendOtpBtn=By.xpath("span[@aria-labelledby='auth-login-via-sms-otp-btn-announce']");
    private By sendOtpBtn=By.id("auth-login-via-sms-otp-btn");
    private By sendOtpBtnMob=By.id("secondary_channel_button-announce");
    private By smsotpinput = By.xpath("input[@type-'tel']");
    private By submitOtp = By.id("cvf-submit-otp-button");
    private By signinOrCreateAccountText = By.xpath("//h1[contains(text(),'Sign in')]");
    private By signinOrCreateAccountTextMob = By.xpath("//h3[contains(text(),'Sign in')]");
   private By signupMobileInput = By.id("ap_phone_number");
   private By signupPassword = By.id("ap_password");
   private By signupName =  By.id("ap_customer_name");
   private By verifyMobileNumber = By.xpath("//input[@aria-labelledby='auth-continue-announce']");
   private By CreateAmazonAccount = By.xpath("input[aria-label='Verify OTP Button']");
   private By forgotPasswordLink = By.partialLinkText("Forgot password?");
   private By passwordAssistanceText = By.xpath("//h1[contains(.,'Password assistance')]");
   private By proceedToCreateAccountButton = By.xpath("//input[@aria-labelledby='intention-submit-button-announce']");
   private By invalidPhoneFormatErrorSignup = By.xpath("//div[contains(text(),'Please enter a valid mobile phone number ')]");
   private By phoneInputSignup = By.id("ap_phone_number");
   private By nameInputSignup = By.id("ap_customer_name");
   private By passwordInputSignup = By.id("ap_password");
   private By verifyMobileNumberBtnSignup = By.id("continue");
   private By invalidPasswordFormatErrorSignUp = By.xpath("//div[@id='auth-password-invalid-password-alert']//div[contains(text(),'Passwords must be at least 6 characters.')]");
   private By mandatoryPasswordFieldFormatErrorSignUp = By.xpath("//div[@id='auth-password-missing-alert']//div[contains(text(),'auth-password-missing-alert')]");
    private By mandatoryNameFieldFormatErrorSignUp = By.xpath("//div[@id='auth-customerName-missing-alert']//div[contains(text(),'Enter your name')]");
    private By mandatoryPhoneFieldFormatErrorSignUp = By.xpath("//div[@id='auth-phoneNumber-missing-alert']//div[contains(text(),'Enter your mobile number')]");
    private By invalidOtpErrorSignUp = By.xpath("//div[contains(text(),'Invalid OTP.')]");
    private By otpfieldSignup = By.id("cvf-input-code");
    private By signInWithOtpBtn = By.xpath("//input[@aria-labelledby='auth-login-via-whatsapp-otp-btn-announce']");
   private By signInButtonMob = By.id("nav-logobar-greeting");




   public LoginPageMob(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.actions= new MouseActions(driver);
    }
    public void clickSignIn(){

        if (BaseTestThreadSafe.isMobile()) {
            wait.waitForElementToBeClickable(signInButtonMob).click();
        } else {
            wait.waitForElementToBeClickable(signInButton).click();
        }


      //  driver.findElement(signInButton).click();
    }
    public void enterMailORNum(String mailNumInput){


        wait.waitForElementToBeVisible(mobileInput).sendKeys(mailNumInput);
       // driver.findElement(mobileInput).sendKeys(mailNumInput);
    }
    public void clickContinue(){
        wait.waitForElementToBeClickable(continueButton).click();
     //  driver.findElement(continueButton).click();
    }
    public void enterPassword(String password) {

        if (BaseTestThreadSafe.isMobile()) {

            wait.waitForElementToBeVisible(passwordInput).sendKeys(password);
        } else {

            wait.waitForElementToBeVisible(passwordInput).sendKeys(password);
            // driver.findElement(passwordInput).sendKeys(password);
        }
    }
    public void clicksiginafterlogin() {

        if (BaseTestThreadSafe.isMobile()) {
            wait.waitForElementToBeClickable(signInButtonMob).click();
        } else {
            wait.waitForElementToBeClickable(hamburgerNav).click();
            // driver.findElement(hamburgerNav).click();
        }
    }
    public Boolean isSignoutVisible(){

       WebElement accandList= wait.waitForElementToBeClickable(accountsAndLists);

        actions.mouseHover(accandList);
       return wait.waitForElementToBeVisible(signOutButton).isDisplayed();


    }
    public Boolean locateSignoutButton(){
       return wait.waitForElementToBeVisible(signOutButton).isDisplayed();

      //  return driver.findElement(signOutButton).isDisplayed();
    }

public void triggerOtp() {
    if (BaseTestThreadSafe.isMobile()) {
        wait.waitForElementToBeClickable(sendOtpBtnMob).click();
    } else {
        wait.waitForElementToBeClickable(sendOtpBtn).click();
    }
}
@Step
public void enterOtp(String otp){
    wait.waitForElementToBeClickable(smsotpinput).sendKeys(otp);
}
public void submitOtp(){
        wait.waitForElementToBeClickable(submitOtp).click();
}

    public void signInSubmit(){
        wait.waitForElementToBeClickable(signInSubmit).click();
           // driver.findElement(signInSubmit).click();
    }
    public boolean passwordInputVisibilty(){
      return  wait.waitForElementToBeVisible(passwordInput).isDisplayed();
     //   return driver.findElement(passwordInput).isDisplayed();
    }
    public boolean isInvalidEmailFormatErrorDisplayed(){
     return   wait.waitForElementToBeVisible(invalidMailFormatError).isDisplayed();
      //  return driver.findElement(invalidFormatError).isDisplayed();
    }

    public boolean isInvalidPhoneFormatErrorDisplayed(){
        return   wait.waitForElementToBeVisible(invalidPhoneFormatError).isDisplayed();
        //  return driver.findElement(invalidFormatError).isDisplayed();
    }

//    public boolean invalidPhoneValidation(){
//        wait.waitForElementToBeVisible(invalidPhoneValidation);
//        return driver.findElement(invalidPhoneValidation).isDisplayed();
//    }

    public boolean isMandatoryFieldErrorDisplayed(){
      return  wait.waitForElementToBeVisible(mandatoryFieldError).isDisplayed();
      //  return driver.findElement(mandatoryFieldError).isDisplayed();
    }
//    public boolean blankPasswordValidation(){
//        wait.waitForElementToBeVisible(blankPasswordValidation);
//        return driver.findElement(blankPasswordValidation).isDisplayed();
//    }
    public boolean isAuthenticationErrorDisplayed(){
       return wait.waitForElementToBeVisible(authenticationError).isDisplayed();
      //  return driver.findElement(authenticationError).isDisplayed();
    }

    public String usernameDisplayed() {
        if (BaseTestThreadSafe.isMobile()) {
            String un = wait.waitForElementToBeVisible(usernameTextMob).getText().toLowerCase();
            Reporter.log("Username: " + un, true);
            return un;
        } else {
            String un = wait.waitForElementToBeVisible(usernameText).getText().toLowerCase();
            Reporter.log("Username: " + un, true);
            return un;
        }
    }
    public void clickSignout(){
        wait.waitForElementToBeClickable(signOutButton).click();
    }

    public Boolean isSignInPageVisible() {
        if (BaseTestThreadSafe.isMobile()) {
            return wait.waitForElementToBeVisible(signinOrCreateAccountTextMob).isDisplayed();
        } else {
            return wait.waitForElementToBeVisible(signinOrCreateAccountText).isDisplayed();
        }
    }

    public void proceedToCreateAcoount(){
        wait.waitForElementToBeClickable(proceedToCreateAccountButton).click();
    }

    public void enterMobileNumberForSignup(String newmobileNumber){

        wait.waitForElementToBeClickable(signupMobileInput).sendKeys(newmobileNumber);

    }
    public void enterNameForSignup(String newName){

        wait.waitForElementToBeClickable(signupName).sendKeys(newName);

    }
    public void enterNewpassword(String newPassword) {
        if (BaseTestThreadSafe.isMobile()) {

        } else {
            wait.waitForElementToBeClickable(signupPassword).sendKeys(newPassword);

        }
    }
    public void clickVerifyPhoneNumber(){

        wait.waitForElementToBeClickable(verifyMobileNumber).click();

    }

    public void enterMobileNumberOtpForSignUp(){

    }

    public void clickForgotPasswordLink(){

       wait.waitForElementToBeClickable(forgotPasswordLink).click();
    }
    public void clickCreateAmazonAccount(){

        wait.waitForElementToBeClickable(proceedToCreateAccountButton).click();

        }

        public void navigateToSignInPage(){

            clickSignIn();


        }



        public Boolean isPasswordAssistancePageVisible(){

        return wait.waitForElementToBeVisible(passwordAssistanceText).isDisplayed();

        }


        public void navigateToSignUpPage(String newmail){
        navigateToSignInPage();
        enterMailORNum(newmail);
        proceedToCreateAcoount();

        }

        public Boolean isErrorMessageForInvalidPhoneNumberOnSignup(){
   return wait.waitForElementToBeVisible(invalidPhoneFormatErrorSignup).isDisplayed();
        };

    public Boolean isErrorMessageForInvalidPasswordOnSignup(){
        return wait.waitForElementToBeVisible(invalidPasswordFormatErrorSignUp).isDisplayed();
    }

    public void enterOtpForSignup(String otp){
        wait.waitForElementToBeClickable(otpfieldSignup).sendKeys(otp);
    }


    public Boolean isMandatoryPhoneNumberFieldErrorMessageDispalyed(){
        return wait.waitForElementToBeVisible(mandatoryPhoneFieldFormatErrorSignUp).isDisplayed();
    }

    public Boolean isMandatoryNameFieldErrorMessageDispalyed(){
        return wait.waitForElementToBeVisible(mandatoryNameFieldFormatErrorSignUp).isDisplayed();
    }

    public Boolean isMandatoryPasswordFieldErrorMessageDispalyed(){
        return wait.waitForElementToBeVisible(mandatoryPasswordFieldFormatErrorSignUp).isDisplayed();
    }

    public void triggerOtpOnSignUp(String newmail,String newnum,String newname,String newpassword){
        navigateToSignUpPage(newmail);
        enterMobileNumberForSignup(newnum);
        enterNameForSignup(newname);
        enterNewpassword(newpassword);
        clickVerifyPhoneNumber();
    }

    public Boolean isInvalidOtpMessageDispalyed(){
        return wait.waitForElementToBeVisible(invalidOtpErrorSignUp).isDisplayed();
    }

    public Boolean isMandatoryOTPFieldMessageDispalyed(){


       String validationMessage= wait.waitForElementToBeVisible(otpfieldSignup).getAttribute("validationMessage");
       return  validationMessage.contains("Please fill out this field");
    }

    public void clickSendOtpSignIn(){
        wait.waitForElementToBeClickable(signInWithOtpBtn).click();
    }











    }







