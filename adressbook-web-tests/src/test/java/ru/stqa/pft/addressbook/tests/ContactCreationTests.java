package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().openNewContactForm();
    app.getContactHelper().fillContactForm(new ContactDate("Ivan", null, null, "test1"), true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().gotoHomePage();
    app.getNavigationHelper().logoutUser();
  }

}
