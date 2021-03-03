package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTests extends CloudStorageApplicationTests {


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


  public void signUpUser() throws InterruptedException {
    driver.get("http://localhost:" + this.port + "/signup");

    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
    WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastname")));
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

    firstName.clear();
    lastName.clear();
    username.clear();
    password.clear();

    firstName.sendKeys("Test");
    lastName.sendKeys("User");
    username.sendKeys("testuser");
    password.sendKeys("testuser");

    WebElement signUpButton = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("button[type='submit']")));
    signUpButton.click();

  }

  public void loginUser() throws InterruptedException {

    driver.get("http://localhost:" + this.port + "/login");

    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

    username.clear();
    password.clear();

    username.sendKeys("testuser");
    password.sendKeys("testuser");

    WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("button[type='submit']")));
    loginButton.click();

  }

  @Test
  public void logoutUser() throws InterruptedException {

    signUpUser();
    loginUser();

    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutButton")));

    logoutButton.click();

    driver.get("http://localhost:" + this.port + "/home");

    Assertions.assertNotEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

  }

}
