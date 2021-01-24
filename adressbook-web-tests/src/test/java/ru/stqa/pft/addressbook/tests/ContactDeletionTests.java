package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Testovich").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = app.contact().all();
    ContactDate deletedContact = before.iterator().next();
    app.goTo().contactPage();
    app.contact().delete(deletedContact);
    app.goTo().contactPage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
    }

}
