package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)// Если нужно отключить тест
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/cat.png");
    ContactDate contact = new ContactDate().withFirstname("Ivans").withLastname("Testovich")
            /*.withPhoto(photo)*/.withHomePhone("111110").withMobilePhone("222220")
            .withWorkPhone("333330").withAddress("Lenina, 35, 1").withEmail("fwf@fwf.com")
            .withEmail2("rrfer@frff.fffrr").withEmail3("2134@1234.rgregm").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().contactPage();
    assertThat(app.contact().сount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    //app.getNavigationHelper().logoutUser();// из одного браузера не запускаются тесты из-за этой строки
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/cat.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test(enabled = false)// Если нужно отключить тест
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactDate contact = new ContactDate().withFirstname("Ivans'").withLastname("Testovich").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().contactPage();
    assertThat(app.contact().сount(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
    //app.getNavigationHelper().logoutUser();// из одного браузера не запускаются тесты из-за этой строки
  }

}