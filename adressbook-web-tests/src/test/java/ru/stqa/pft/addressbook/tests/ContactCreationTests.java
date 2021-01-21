package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().openNewContactForm();
    app.getContactHelper().fillContactForm(new ContactDate("Ivan", "Testovich", null, "test1"), true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
    app.getNavigationHelper().logoutUser();
  }

}