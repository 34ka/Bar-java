package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

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
        //attach(By.name("photo"), contactDate.getPhoto());
        type(By.name("address"), contactDate.getAddress());
        type(By.name("home"), contactDate.getHomePhone());
        type(By.name("mobile"), contactDate.getMobilePhone());
        type(By.name("work"), contactDate.getWorkPhone());
        type(By.name("email"), contactDate.getEmail());
        type(By.name("email2"), contactDate.getEmail2());
        type(By.name("email3"), contactDate.getEmail3());

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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int id) {
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactDate contact, boolean b) {
        openNewContactForm();
        fillContactForm(contact, b);
        submitContactForm();
        contactCache = null;
    }
    public void modify(ContactDate contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactDate contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
        closeAlertWindow();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int сount() {
       return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

     /*public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }*/


    public Contacts all() {
        if (contactCache !=null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactDate contact = new ContactDate()
                    .withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactDate infoFromEditForm(ContactDate contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactDate().withFirstname(firstname).withLastname(lastname)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //лёгкие аналоги верха
        //wd.findElement(By.xpath(String.format("a[href='edit.php?id=%s']",id)));
        //аналог верха
        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id)));
        //аналог верха
        //wd.findElement(By.xpath(String.format("tr[.//input[@value='%s']]/td[8]/a",id)));
        //аналог верха
        //wd.findElement(By.xpath(String.format("//input[@value='@s']/../../td[8]/a", id))).click();
        //аналог верха
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value'@s']]/td[8]/a", id))).click();
        //аналог верха
        //wd.findElement(By.xpath(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}

