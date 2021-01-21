package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("Ivan", null, null, "test1"), true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactDate("Ivan", "Petrov", "Ivan@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
        app.getNavigationHelper().logoutUser();
    }
}
