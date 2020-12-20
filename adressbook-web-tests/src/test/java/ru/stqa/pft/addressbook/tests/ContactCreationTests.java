package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.openNewContactForm();
    app.fillContactForm(new ContactDate("Ivan", "Petrov", "Ivan@gmail.com"));
    app.submitContactForm();
    app.gotoHomePage();
    app.logoutUser();
  }

}
