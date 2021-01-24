package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Testovich").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactDate> before = app.contact().all();
        ContactDate modifiedContact = before.iterator().next();
        ContactDate contact = new ContactDate()
                .withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Petrov").withEmail("Ivan@gmail.com");
        app.goTo().contactPage();
        app.contact().modify(contact);
        app.goTo().contactPage();
        Set<ContactDate> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        app.goTo().logoutUser();

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }

}
