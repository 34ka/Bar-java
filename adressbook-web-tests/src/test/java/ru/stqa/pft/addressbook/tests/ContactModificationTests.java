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
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Testovich").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        List<ContactDate> before = app.contact().list();
        int index = before.size() - 1;
        ContactDate contact = new ContactDate()
                .withId(before.get(index).getId()).withFirstname("Ivan").withLastname("Petrov").withEmail("Ivan@gmail.com");
        app.goTo().contactPage();
        app.contact().modify(before, index, contact);
        app.goTo().contactPage();
        List<ContactDate> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        app.goTo().logoutUser();

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
