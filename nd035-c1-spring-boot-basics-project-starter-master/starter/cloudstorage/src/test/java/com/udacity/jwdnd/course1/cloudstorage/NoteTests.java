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
public class NoteTests extends CloudStorageApplicationTests {


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

  public List<WebElement> createNote() {
    driver.get("http://localhost:" + this.port);
    WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
    notesTab.click();
    WebElement uploadNoteButton = driver.findElement(By.id("add-note"));

    ((JavascriptExecutor) driver).executeScript("showNoteModal();");

    WebElement noteModal = driver.findElement(By.id("noteModal"));
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement noteTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("notetitle")));
    WebElement noteBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("notedescription")));

    noteTitle.sendKeys("This is a note");
    noteBody.sendKeys("This is the body of the note");

    WebElement noteSubmit = driver.findElement(new By.ByCssSelector("#noteModal > div > div > div.modal-footer > button.btn.btn-primary"));
    noteSubmit.click();

    driver.get("http://localhost:" + this.port);

    WebElement notesTab2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    notesTab2.click();
    List<WebElement> notes = driver.findElements(new By.ByCssSelector("#notesTable > tbody > tr"));
    return notes;
  }

  @Test()
  public void createNoteTest() throws InterruptedException {
    List<WebElement> notes = createNote();
    Assertions.assertEquals(notes.size(), 1);
    Thread.sleep(1000);
  }


  @Test()
  public void editNoteTest() throws InterruptedException {
    String editedTitle = "Edited title";
    String editedDescription = "This is the edited description";
    List<WebElement> notes = createNote();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement editNoteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("#notesTable > tbody > tr:nth-of-type(1) > td > button")));
    editNoteButton.click();

    WebElement editNoteTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("notetitle")));
    WebElement editNoteBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("notedescription")));

    editNoteTitle.clear();
    editNoteBody.clear();

    editNoteTitle.sendKeys(editedTitle);
    editNoteBody.sendKeys(editedDescription);

    WebElement noteSubmit = driver.findElement(new By.ByCssSelector("#noteModal > div > div > div.modal-footer > button.btn.btn-primary"));
    noteSubmit.click();

    driver.get("http://localhost:" + this.port);

    WebElement notesTab2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    notesTab2.click();

    WebElement editNoteTitleText = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("#notesTable > tbody > tr:nth-of-type(1) > th")));
    WebElement editNoteDescriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("#notesTable > tbody > tr:nth-of-type(1) > td:nth-of-type(2)")));
    String editedNoteTitleText = editNoteTitleText.getText();
    String editedNoteDescriptionText = editNoteDescriptionText.getText();

    Assertions.assertEquals(editedNoteTitleText, editedTitle);
    Assertions.assertEquals(editedNoteDescriptionText, editedDescription);

    Thread.sleep(5000);
  }


  @Test()
  public void deleteNoteTest() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    List<WebElement> notes = createNote();

    WebElement deletelink = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("#notesTable > tbody > tr > td:nth-child(1) > a")));
    deletelink.click();

    driver.get("http://localhost:" + this.port);

    WebElement notesTab2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    notesTab2.click();

    List<WebElement> allNotes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(new By.ByCssSelector("#notesTable > tbody > tr")));

    Assertions.assertEquals(1, allNotes.size());
    Thread.sleep(1000);

  }
}
