package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getContactHelper().openNewContactForm();
    ContactDate contact = new ContactDate("Ivans", "Testovich", null, "test1");
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().gotoHomePage();
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((Comparator<ContactDate>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    app.getNavigationHelper().logoutUser();
  }

}