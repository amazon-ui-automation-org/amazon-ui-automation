package com.amazon.tests.useraccount;
import com.amazon.base.BaseTest;
import com.amazon.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class loginFT1 extends BaseTest {
    @DataProvider (name="validMail")
    public Object[][] validMail(){
        return new Object[][]{

                {"saiknght@gmail.com"},
                {"muralisaiswaroop@gmail.com"},
                {"jj@gmail.com"},
        };
    }
    @DataProvider (name="validPhone")
    public Object[][] validPhone(){
        return new Object[][]{

                {"7259356897"},
                {"9538991476"}

        };
    }
    @Test(dataProvider="validMail")
    public void verifyEmailInputAcceptsValidEmail(String s){
        LoginPage p = new LoginPage(driver);
        p.clickSignIn();
        p.enterMailORNum(s);
        p.clickContinue();
      //  p.passwordInputVisibilty()
        Assert.assertTrue(p.passwordInputVisibilty(),"password field not visible for valid mailID");
    }
    @Test(dataProvider="validPhone")
    public void verifyPhoneInputAcceptsValidNumber(String s){
        LoginPage p = new LoginPage(driver);
        p.clickSignIn();
        p.enterMailORNum(s);
        p.clickContinue();
        //  p.password9InputVisibilty()
        Assert.assertTrue(p.passwordInputVisibilty(),"password field not visible for valid mailID");
    }
    @DataProvider (name = "invalidEmail")
    public Object[][] invalidEmail(){
       return new Object[][] {
            {"sss"},
            {"sdhjsdhj123"},
            {"saikng@gmail"},
               {"saight.com"}
        };

    }
    @Test(dataProvider="invalidEmail")
    public void verifyEmailInputAcceptsInvalidEmail(String s){
        LoginPage p = new LoginPage(driver);
        p.clickSignIn();
        p.enterMailORNum(s);
        p.clickContinue();
        //  p.password9InputVisibilty()
       // Assert.assertTrue(p.isInvalidFormatErrorDisplayed(),"validation message not displayed for invalid email");
    }
    @DataProvider (name = "invalidPhone")
    public Object[][] invalidPhone(){
        return new Object[][] {
                {"725935689"},
                {"72593568978"},
        };

    }
    @Test(dataProvider="invalidPhone")
    public void verifyPhoneInputAcceptsInvalidPhone(String s){
        LoginPage p = new LoginPage(driver);
        p.clickSignIn();
        p.enterMailORNum(s);
        p.clickContinue();
        //  p.password9InputVisibilty()
      //  Assert.assertTrue(p.isInvalidFormatErrorDisplayed(),"validation message not displayed for invalid phone");
    }
    @DataProvider (name = "blankMailORPhoneField")
    public Object[][] blankMailORPhoneField(){
        return new Object[][] {
                {""}
        };
    }
    @Test(dataProvider="blankMailORPhoneField")
    public void verifyPhoneORMailInputAcceptsBlank(String s){
        LoginPage p = new LoginPage(driver);
        p.clickSignIn();
        p.enterMailORNum(s);
        p.clickContinue();
        //  p.password9InputVisibilty()
        Assert.assertTrue(p.isMandatoryFieldErrorDisplayed(),"validation message not displayed for blank phone or mail input");
    }


    @Test(dataProvider="blankMailORPhoneField")
    public void verifyPasswordFieldAcceptsBlank(String s){
        LoginPage p = new LoginPage(driver);
        p.clickSignIn();
        p.enterMailORNum("7259356897");
        p.clickContinue();
        //  p.password9InputVisibilty()
        p.enterPassword(s);
        p.signInSubmit();
        Assert.assertTrue(p.isMandatoryFieldErrorDisplayed(),"validation message not displayed for blank phone or mail input");
    }


@DataProvider(name = "registeredEmailOrNumberAndIncorrectPassword")
public Object[][] registeredEmailOrNumberAndIncorrectPassword(){
    return new Object[][]{
        {"saiknght@gmail.com","123456"},
            {"7259356897","sdhhj123"}
    };


}
  @Test(dataProvider="registeredEmailOrNumberAndIncorrectPassword")
  public void verifyErrorOnIncorrectPassword(String mp, String pw){
      LoginPage p = new LoginPage(driver);
      p.clickSignIn();
      p.enterMailORNum(mp);
      p.clickContinue();
      //  p.password9InputVisibilty()
      //comment added
      p.enterPassword(pw);
      p.signInSubmit();
      Assert.assertTrue(p.isAuthenticationErrorDisplayed(),"Password validation message not displayed");
  }







}






