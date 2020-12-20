package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private WebDriver wd;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void logoutUser() {
      wd.findElement(By.linkText("Logout")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys("admin");
    }

    public void returnToGroupPage() {
      wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupDate groupDate) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupDate.getName());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupDate.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupDate.getFooter());
    }

    public void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    public void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    public void stop() {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    public void deleteSelectedGroups() {
      wd.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

    public void gotoHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactForm() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactDate contactDate) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactDate.getFirstname());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactDate.getLastname());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactDate.getEmail());
    }

    public void openNewContactForm() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void closeAlertWindow() {
      wd.switchTo().alert().accept();
    }

    public void deleteSelectedContacts() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void selectContact() {
      wd.findElement(By.name("selected[]")).click();
    }
}
