package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getContactHelper().openNewContactForm();
    app.getContactHelper().fillContactForm(new ContactDate("Ivan", "Testovich", null, "test1"), true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().gotoHomePage();
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getNavigationHelper().logoutUser();
  }

}