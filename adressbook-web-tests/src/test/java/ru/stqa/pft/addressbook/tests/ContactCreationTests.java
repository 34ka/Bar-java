package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)// Если нужно отключить тест
  public void testContactCreation() throws Exception {
    List<ContactDate> before = app.contact().list();
    ContactDate contact = new ContactDate().withFirstname("Ivans").withLastname("Testovich").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().contactPage();
    List<ContactDate> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    //app.getNavigationHelper().logoutUser();// из одного браузера не запускаются тесты т.к. происходит logout
  }

}