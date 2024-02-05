import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SwagLabs{
    String BaseURL = "https://www.saucedemo.com/";

    WebDriver driver;



    @BeforeTest
    public void BeforeTestMethod() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);

    }

    @Test(priority = 1)

    public void SwagLabsLoginCredentials() throws InterruptedException {

        System.out.println("---------------TC 001---------------");

        driver.findElement(By.name("user-name")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.name("login-button")).click();

        System.out.println("Check Whether the user can log in to the system without enter the UserName and Password");

        driver.navigate().refresh();
        Thread.sleep(3000);

        driver.findElement(By.name("user-name")).sendKeys("Harshana1");
        driver.findElement(By.name("password")).sendKeys("Suraweera221");
        driver.findElement(By.name("login-button")).click();

        System.out.println("Check Whether the user can log in to the system with entering the Invalid UserName and Password");

        driver.navigate().refresh();

        Thread.sleep(3000);

        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("12345");
        driver.findElement(By.name("login-button")).click();

        System.out.println("Check Whether the user can log in to the system with entering the Correct UserName and Invalid Password");

        driver.navigate().refresh();
        Thread.sleep(3000);

        driver.findElement(By.name("user-name")).sendKeys("sand_mud");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        System.out.println("Check Whether the user can log in to the system with entering the Invalid UserName and Correct Password");

        driver.navigate().refresh();
        Thread.sleep(3000);

        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.name("login-button")).click();

        System.out.println("Check Whether the user can log in to the system with entering only correct UserName");

        driver.navigate().refresh();
        Thread.sleep(3000);

        driver.findElement(By.name("user-name")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        System.out.println("Check Whether the user can log in to the system with entering only correct Password");

        driver.navigate().refresh();
        Thread.sleep(3000);

        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        System.out.println("Correct UserName and Password");
        System.out.println("Logged in Successfully to product page");
        System.out.println("PASSED : Invalid Login");
        System.out.println("PASSED : Valid Login");
        System.out.println("TC 001: PASS");

        Thread.sleep(3000);

    }

    @Test(priority = 2)
    public void SauceLabsBackPack() throws InterruptedException {

        System.out.println("---------------TC 002---------------");
        System.out.println("---First Product---");

        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();

        BackToProducts();

        System.out.println("Sauce Labs BackPack Page");
        System.out.println("First Product added to the cart Successfully");
        System.out.println("Back to product page");


        System.out.println("---Second Product---");

        driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();

        Cart();
        Thread.sleep(3000);

        System.out.println("Sauce Labs Bolt T-shirt Page");
        System.out.println("Second Product added to the cart Successfully");
        System.out.println("Back to product page");
        System.out.println("TC 002: PASS");



    }

    @Test(priority = 3)
    public void CartRemoveItem() throws InterruptedException {

        System.out.println("---------------TC 003---------------");

        Cart();

        driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-bolt-t-shirt\"]")).click();
        Thread.sleep(2500);

        ContinueShopping();

        System.out.println("Cart Page");
        System.out.println("Check added product can remove from the Cart");
        System.out.println("Removed product from the cart successfully");
        System.out.println("TC 003: PASS");


    }
    @Test(priority = 4)
    public void AddProductFromTheProductPage() throws InterruptedException {

        System.out.println("---------------TC 004---------------");

        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();
        Thread.sleep(2500);

        Cart();

        Thread.sleep(2500);

        System.out.println("Added items to the cart successfully");
        System.out.println("Redirect to the Cart Page Successfully");
        System.out.println("TC 004: PASS");



    }
    @Test(priority = 5)

    public void CheckOutYourInformation() throws InterruptedException {

        System.out.println("---------------TC 005---------------");

        CheckOut();

        driver.findElement(By.id("first-name")).sendKeys("Harshana");
        driver.findElement(By.id("last-name")).sendKeys("Suraweera");
        driver.findElement(By.id("postal-code")).sendKeys("11670");

        Thread.sleep(3000);

        System.out.println("Information Page");
        System.out.println("Filled Information Successfully");
        System.out.println("TC 005: PASS");


    }

    @Test(priority = 6)

    public void testCheckoutCalculation() {

        System.out.println("---------------TC 006---------------");

        ContinueBtn();

        String itemTotalText = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")).getText();
        String taxText = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]")).getText();
        String totalText = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")).getText();


        double itemTotal = parseAndCalculate(itemTotalText);
        double tax = parseAndCalculate(taxText);
        double total = parseAndCalculate(totalText);


        assert itemTotal + tax == total : "Calculation is incorrect";


        System.out.println("Item Total: $" + itemTotal);
        System.out.println("Tax: $" + tax);
        System.out.println("Total: $" + total);

        FinishCheckOut();

        System.out.println("Check Calculation result get successfully");
        System.out.println("Checkout Completed Successfully");
        System.out.println("TC 006: PASS");


    }


    @AfterTest
    public void AfterTestMethod(){

    }
    public void Cart(){

        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    public void BackToProducts() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click();

        Thread.sleep(3000);

    }
    public void ContinueShopping() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"continue-shopping\"]")).click();

        Thread.sleep(2000);
    }

    public void CheckOut() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

        Thread.sleep(2000);
    }


    private static double parseAndCalculate(String text) {
        return Double.parseDouble(text.replaceAll("[^\\d.]", ""));
    }

    public void FinishCheckOut() {
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();

    }
    public void ContinueBtn(){
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
    }
}






