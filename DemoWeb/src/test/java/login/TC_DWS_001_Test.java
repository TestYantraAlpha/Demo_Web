package login;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricenties.basetest.BaseClass;

public class TC_DWS_001_Test extends BaseClass{
	@Test(groups = "syetem")
	public void loginTest() throws EncryptedDocumentException, IOException {
		String EXPECTEDTITLE = eLib.getDataFromExcel("Login", 1, 2);
		Assert.assertEquals(driver.getTitle(), EXPECTEDTITLE,"failed to login");
		test.log(Status.PASS, "User logged in successfully");
	}
}
