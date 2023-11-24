package shoppingcart;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricenties.basetest.BaseClass;
import com.tricenties.obejctrepository.HomePage;
import com.tricenties.obejctrepository.ShoppingCartPage;

public class TC_DWS_002_Test extends BaseClass{
	@Test(groups = "system")
	public void addProductToCart() {
		hp=new HomePage(driver);
		hp.getAddToCartButtons().get(1).click();
		boolean productStatus = hp.getProductAddedMsg().isDisplayed();
		Assert.assertEquals(productStatus, true);
		test.log(Status.PASS, "Product has been added to cart message is displayed");
		wait.until(ExpectedConditions.invisibilityOf(hp.getProductAddedMsg()));
		wp.getShoppingCartLink().click();
		ShoppingCartPage sp=new ShoppingCartPage(driver);
		boolean productNameStatus = sp.getProductName().isDisplayed();
		Assert.assertEquals(productNameStatus, true,"failed to add product to cart");
		test.log(Status.PASS, "Product added to cart successfully");
	}
}
