package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)// Если нужно отключить тест
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactDate contact = new ContactDate().withFirstname("Ivans").withLastname("Testovich").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().contactPage();
    assertThat(app.contact().сount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    //app.getNavigationHelper().logoutUser();// из одного браузера не запускаются тесты из-за этой строки
  }

  @Test//(enabled = false)// Если нужно отключить тест
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