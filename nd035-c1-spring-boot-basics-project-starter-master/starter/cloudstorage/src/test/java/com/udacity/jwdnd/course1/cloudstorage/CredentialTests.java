package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests extends CloudStorageApplicationTests {


  @LocalServerPort
  private int port;

  private WebDriver driver;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    this.driver = new ChromeDriver();
  }


  @AfterEach
  public void afterEach() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  public List<WebElement> createCredential() {
    driver.get("http://localhost:" + this.port);
    WebElement notesTab = driver.findElement(By.id("nav-credentials-tab"));
    notesTab.click();
    WebElement uploadNoteButton = driver.findElement(By.id("add-credential"));

    ((JavascriptExecutor) driver).executeScript("showCredentialModal();");

    WebElement noteModal = driver.findElement(By.id("credentialModal"));
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement url = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("url")));
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

    url.sendKeys("gmail.com");
    username.sendKeys("thisisatest");
    password.sendKeys("thisisatest");

    WebElement credentialSubmit = driver.findElement(new By.ByCssSelector("#credentialModal > div > div > div.modal-footer > button.btn.btn-primary"));
    credentialSubmit.click();

    driver.get("http://localhost:" + this.port);

    WebElement credentialTab2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
    credentialTab2.click();
    List<WebElement> credentials = driver.findElements(new By.ByCssSelector("#credentialTable > tbody > tr"));
    return credentials;
  }

  @Test()
  public void createCredentialTest() throws InterruptedException {
    List<WebElement> credehtials = createCredential();
    Assertions.assertEquals(1, credehtials.size());
    Thread.sleep(10000);
  }


  @Test()
  public void editCredentialTest() throws InterruptedException {
    String editedURL = "Edited URL";
    String editedUsername = "Edited UName";
    String editedPassword = "newPassword";

    List<WebElement> credential = createCredential();

    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement editCredentialButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
      new By.ByCssSelector("#credentialTable > tbody > tr > td:nth-child(1) > button")
    ));
    editCredentialButton.click();

    WebElement urlTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
      By.name("url")
    ));

    WebElement usernameTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
      By.name("username")
    ));

    WebElement passwordTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
      By.name("password")
    ));

    urlTextInput.clear();
    usernameTextInput.clear();
    passwordTextInput.clear();

    urlTextInput.sendKeys(editedURL);
    usernameTextInput.sendKeys(editedUsername);
    passwordTextInput.sendKeys(editedPassword);

    WebElement credentialSubmit =
      driver.findElement
        (new
          By.ByCssSelector(
          "#credentialModal > div > div > div.modal-footer > button.btn.btn-primary"
        ));
    credentialSubmit.click();

    driver.get("http://localhost:" + this.port);

    WebElement credentialTab2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
    credentialTab2.click();

    WebElement editedCredentialURL = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("#credentialTable > tbody > tr > th")));
    WebElement editedCredetialUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("#credentialTable > tbody > tr > td:nth-child(3)")));

    String newUrl = editedCredentialURL.getText();
    String newUsername = editedCredetialUsername.getText();

    Assertions.assertEquals(newUrl, editedURL);
    Assertions.assertEquals(newUsername, editedUsername);

    Thread.sleep(1000);
  }


  @Test()
  public void deleteCredentialTest() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    List<WebElement> notes = createCredential();

    WebElement deletelink = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("#credentialTable > tbody > tr > td:nth-child(1) > a")));
    deletelink.click();

    driver.get("http://localhost:" + this.port);

    WebElement credentialTab2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
    credentialTab2.click();

    List<WebElement> allCredentials = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(new By.ByCssSelector("#credentialTable > tbody > tr")));

    Assertions.assertEquals(1, allCredentials.size());
    Thread.sleep(1000);

  }
}
