package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("Ivan", "Testovich", null, "test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        List<ContactDate> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactDate contact = new ContactDate(before.get(index).getId(),"Ivan", "Petrov", "Ivan@gmail.com", null);
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().modifyContact(before, index, contact);
        app.getNavigationHelper().gotoHomePage();
        List<ContactDate> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        app.getNavigationHelper().logoutUser();

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
