package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Testovich").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactDate modifiedContact = before.iterator().next();
        ContactDate contact = new ContactDate()
                .withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Petrov").withEmail("Ivan@gmail.com");
        app.goTo().contactPage();
        app.contact().modify(contact);
        app.goTo().contactPage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        app.goTo().logoutUser();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }

}
