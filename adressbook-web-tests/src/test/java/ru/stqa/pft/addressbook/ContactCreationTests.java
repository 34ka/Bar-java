package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    openNewContactForm();
    fillContactForm(new ContactDate("Ivan", "Petrov", "Ivan@gmail.com"));
    submitContactForm();
    gotoHomePage();
    logoutUser();
  }

}
