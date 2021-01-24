package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Testovich").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Set<ContactDate> before = app.contact().all();
    ContactDate deletedContact = before.iterator().next();
    app.goTo().contactPage();
    app.contact().delete(deletedContact);
    app.goTo().contactPage();
    Set<ContactDate> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
    }

}
