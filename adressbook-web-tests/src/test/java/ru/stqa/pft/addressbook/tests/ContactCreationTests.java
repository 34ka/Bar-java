package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)// Если нужно отключить тест
  public void testContactCreation() throws Exception {
    Set<ContactDate> before = app.contact().all();
    ContactDate contact = new ContactDate().withFirstname("Ivans").withLastname("Testovich").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().contactPage();
    Set<ContactDate> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);

    //app.getNavigationHelper().logoutUser();// из одного браузера не запускаются тесты т.к. происходит logout
  }

}