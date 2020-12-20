package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactDate("Ivan", "Petrov", "Ivan@gmail.com"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        app.getNavigationHelper().logoutUser();
    }
}
