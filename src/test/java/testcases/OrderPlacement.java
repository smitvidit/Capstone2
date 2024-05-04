package testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import util2.CredentialsFetchExcel;

public class OrderPlacement extends BaseTest {
	public static String FirstName;
	public static String LastName;
	public static String Email;
	public static String Password;


	static Logger log = Logger.getLogger(OrderPlacement.class);

	// Test 3: End-to-End Test of the Entire Flow
	@Test
	public void endToEnd() throws InterruptedException, InvalidFormatException, IOException {
		
		homePage.navigateToHomePage();
		homePage.clickSignIn();

		CredentialsFetchExcel.reg();
		FirstName = CredentialsFetchExcel.FirstName;
		LastName = CredentialsFetchExcel.LastName;
		Password = CredentialsFetchExcel.Password;
		Email = CredentialsFetchExcel.Email;

		System.out.println(FirstName + " : " + LastName + " : " + Email + " : " + Password);

		log.info(FirstName + " : " + LastName + " : " + Email + " : " + Password);
		loginPage.enterRegisteredEmail(Email);
		loginPage.enterRegisteredPassword(Password);
		loginPage.clickSignInButton();

//            // Assert that the user is redirected to 'HomePage' page after successful LoginPage.
		Assert.assertTrue(myAccountPage.isOnHomePage(),
				"The user was not redirected to the 'HomePage' page after successful loginPage.");

//            // Assert Welcome message and the customer Full Name on User Account Menu Element.
		Assert.assertEquals(homePage.checkWelcomeMessageOnuSearchCountMenu(),
				"Welcome, " + FirstName + " " + LastName + "!",
				"The welcome message and customer name on the User Account Menu do not match the expected values.");

//            //Assert that 'Main Store Navigation Menu' is displayed on 'My Account' page.
		Assert.assertTrue(myAccountPage.mainStoreMenuIsDisplayed(),
				"The 'Main Store Navigation Menu' is not displayed on the 'My Account' page.");

		homePage.hoverToMenMenu();
		homePage.hoverToTops();
		homePage.clickOnJackets();

		menJacketsPage.addToCartBlackXLFitnessJackshirt();
//            // Assert the addition of 'Proteus Fitness JackShirt' product to the shopping cart.
		Thread.sleep(2000);
		Assert.assertEquals(menJacketsPage.getSuccessMessageFitnessJackshirt(),
				"You added Proteus Fitness Jackshirt to your shopping cart.",
				"The product 'Proteus Fitness JackShirt' was not successfully added to the shopping cart.");

		menJacketsPage.addToCartGreenXLWindJackshirt();
//            // Assert the addition of 'Montana Wind Jacket' product to the shopping cart.
		
		Thread.sleep(2000);
		Assert.assertEquals(menJacketsPage.getSuccessMessageWindJacket(),
				"You added Montana Wind Jacket to your shopping cart.",
				"The product 'Montana Wind Jacket' was not successfully added to the shopping cart.");

		menJacketsPage.clickProceedToCheckout();
		// Assert successful navigation to the 'Checkout' page.
		Assert.assertTrue(checkoutShippingPage.isOnCheckoutPage(),
				"The user was not redirected to the expected 'Checkout' page.");

		if (checkoutShippingPage.isNewAddressButtonPresent()) {
			checkoutShippingPage.clickNewAddressButton();
			// Assert that 'India' is available country in Country Drop Down menu.
			Assert.assertTrue(checkoutShippingPage.checkCountryIsListed("Honduras"), "'Honduras' is not available");

			checkoutShippingPage.enterPhoneNumber("+919177334455");
			checkoutShippingPage.enterZipCode("411057");
			checkoutShippingPage.enterStreetAddress("Brillio Office");
			checkoutShippingPage.enterCity("XYZ");
			checkoutShippingPage.clickShipHereButton();
			checkoutShippingPage.selectFixedShippingMethod();
			checkoutShippingPage.clickNextButtonToThePayment();
		}

		else {
			Assert.assertTrue(checkoutShippingPage.checkCountryIsListed("North Macedonia"),
					"'North Macedonia' is not available");

			checkoutShippingPage.selectCountry("North Macedonia");
			checkoutShippingPage.enterStreetAddress("3 MUB");
			checkoutShippingPage.enterCity("Skopje");
			checkoutShippingPage.enterZipCode("1000");
			checkoutShippingPage.enterPhoneNumber("+38977334455");
			checkoutShippingPage.selectFixedShippingMethod();
			checkoutShippingPage.clickNextButtonToThePayment();
		}
		// Assert successful navigation to the 'Payment' page.
		Assert.assertTrue(checkoutPaymentsPage.isOnPaymentPage(),
				"The navigation to the 'Payment' page was not successful.");

		// Assert Cart item count.
		Assert.assertEquals(checkoutPaymentsPage.getTotalItemsInCart(), "2",
				"The total number of items in the cart does not match the expected count.");

		// Assert Cart Subtotal price, Shipping Price and Order Total Price.
		Assert.assertEquals(checkoutPaymentsPage.getCartSubtotalPrice(), "$94.00",
				"The 'Cart Subtotal' price does not match expected value");
		Assert.assertEquals(checkoutPaymentsPage.getShippingPrice(), "$10.00",
				"The 'Shipping' price does not match expected value");
		Assert.assertEquals(checkoutPaymentsPage.getOrderTotalPrice(), "$104.00",
				"The 'Order Total' price does not match expected value");

		// Assert shipping information.
		Assert.assertEquals(checkoutPaymentsPage.getShipToInformation().replaceAll("\\s+", " ").trim(),
				FirstName + " " + LastName + " 3 MUB Skopje, 1000 North Macedonia +38977334455",
				"Shipping information does not match the expected value.");

		checkoutPaymentsPage.clickOnItemsInCartDropDown(3);
		// Assert the Order summary products prices.
		Assert.assertEquals(checkoutPaymentsPage.getProductOnePrice(), "$45.00",
				"The price for the first item in the order summary is incorrect.");
		Assert.assertEquals(checkoutPaymentsPage.getProductTwoPrice(), "$49.00",
				"The price for the second item in the order summary is incorrect.");

		checkoutPaymentsPage.clickPlaceOrderWithRetry(3);

		// Assert successful navigation to the 'Success' page.
		Assert.assertTrue(checkoutSuccessPage.isOnSuccessPage(),
				"The navigation to the 'Success' page was NOT successful.");

		// Assert successful purchase!
		Assert.assertEquals(checkoutSuccessPage.getSuccessfulPurchaseMessage(), "Thank you for your purchase!",
				"The successful purchase message does NOT match the expected message.");

		// Assert 'Print receipt' link is present on the Order Success Page.
		Assert.assertEquals(checkoutSuccessPage.confirmPrintReceiptLinkPresent(), "Print receipt",
				"'Print receipt' link is NOT present on the Order Success Page.");

		// Assert 'Continue Shopping' button is present on the Order Success Page
		Assert.assertEquals(checkoutSuccessPage.confirmContinueButtonPresent(), "Continue Shopping",
				"'Continue Shopping' button is NOT present on Order Success Page");
	}
}
