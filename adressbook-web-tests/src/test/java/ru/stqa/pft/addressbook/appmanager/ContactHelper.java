package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactDate contactDate, boolean creation) {
      type(By.name("firstname"), contactDate.getFirstname());
        type(By.name("lastname"), contactDate.getLastname());
        type(By.name("email"), contactDate.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void type(By locator) {
        click(locator);
    }

    public void openNewContactForm() {
        click(By.linkText("add new"));
    }

    public void closeAlertWindow() {
      wd.switchTo().alert().accept();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        //это можно будет использовать, если ассерты в подсчёте будут считать до загрузки
        //closeAlertWindow();
        //wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactDate contact, boolean b) {
        openNewContactForm();
        fillContactForm(contact, b);
        submitContactForm();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
       return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactDate> getContactList() {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        //List<WebElement> elements = wd.findElements(By.name("selected[]"));
        for (WebElement element : elements) {
            String name = element.getText();
            String lastname = element.getText();
            ContactDate contact = new ContactDate(name, lastname, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
