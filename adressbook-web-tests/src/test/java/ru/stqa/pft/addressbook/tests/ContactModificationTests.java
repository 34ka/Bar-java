package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
//      if (app.contact().all().size() == 0) {
//            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Petrov")
//                    .withEmail("Ivan@gmail.com"), true);
//        }
//    }
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Petrov")
                    .withEmail("Ivan@gmail.com"), true);
        }
    }

    @Test
    public void testContactModification() {
        //Contacts before = app.contact().all();// из веба
        Contacts before = app.db().contacts();// из базы
        ContactDate modifiedContact = before.iterator().next();
        ContactDate contact = new ContactDate()
                .withId(modifiedContact.getId()).withFirstname("Ivans").withLastname("Testovich")
                .withHomePhone("111110").withMobilePhone("222220").withWorkPhone("333330")
                .withAddress("Lenina, 35, 1").withEmail("fwf@fwf.com").withEmail2("rrfer@frff.fffrr")
                .withEmail3("2134@1234.rgregm").withGroup("test1");
        app.goTo().contactPage();
        app.contact().modify(contact);
        app.goTo().contactPage();
        assertThat(app.contact().сount(), equalTo(before.size()));
        //Contacts after = app.contact().all();// из веба
        Contacts after = app.db().contacts();// из базы
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
        app.goTo().logoutUser();
    }

}
