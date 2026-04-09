package com.amazon.DataProvider;



import com.amazon.utils.ConfigReader;
import com.amazon.utils.ExcelUtil;
import org.testng.annotations.DataProvider;

public class UserAccountDp {

    @DataProvider(name="userAccountDpMail")
    public Object[][] userAccountDpMail () {
       String path = ConfigReader.get("useraccountexcelpath");
       String sheetname=ConfigReader.get("useraccountmailsheet");
      return  ExcelUtil.getData(path,sheetname);

    }

    @DataProvider(name="userAccountDpNumber")
    public Object[][] userAccountDpNumber () {
        String path = ConfigReader.get("useraccountexcelpath");
        String sheetname=ConfigReader.get("useraccountphonenumbersheet");
        return  ExcelUtil.getData(path,sheetname);

    }



}

