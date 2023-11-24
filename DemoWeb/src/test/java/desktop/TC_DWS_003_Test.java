package desktop;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricenties.basetest.BaseClass;
import com.tricenties.obejctrepository.HomePage;

public class TC_DWS_003_Test extends BaseClass{
	@Test(groups = "smoke")
	public void desktopTest() {
		hp=new HomePage(driver);
		wLib.mouseHoverOnElement(driver, hp.getComputersLink());
		hp.getDesktopLink().click();
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Desktops");
		test.log(Status.PASS, "Desktop page is displayed");
	}
}
