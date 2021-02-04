package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
//    list.add(new Object[]{new ContactDate().withFirstname("Ivans 1").withLastname("Testovich 1").withHomePhone("111110 1")
//            .withMobilePhone("222220 1").withWorkPhone("333330 1").withAddress("Lenina, 35, 1").withEmail("fwf@fwf.com 1")
//            .withEmail2("rrfer@frff.fffrr 1").withEmail3("2134@1234.rgregm 1").withGroup("test1")});
//    list.add(new Object[]{new ContactDate().withFirstname("Ivans 2").withLastname("Testovich 2").withHomePhone("111110 2")
//            .withMobilePhone("222220 2").withWorkPhone("333330 2").withAddress("Lenina, 35, 2").withEmail("fwf@fwf.com 2")
//            .withEmail2("rrfer@frff.fffrr 2").withEmail3("2134@1234.rgregm 2").withGroup("test1")});
//    list.add(new Object[]{new ContactDate().withFirstname("Ivans 3").withLastname("Testovich 3").withHomePhone("111110 3")
//            .withMobilePhone("222220 3").withWorkPhone("333330 3").withAddress("Lenina, 35, 3").withEmail("fwf@fwf.com 3")
//            .withEmail2("rrfer@frff.fffrr 3").withEmail3("2134@1234.rgregm 3").withGroup("test1")});
    //Блок try для закрытия файла, чтобы не потерять данные
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactDate.class);
      List<ContactDate> contacts = (List<ContactDate>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    //Блок try для закрытия файла, чтобы не потерять данные
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactDate> contacts = gson.fromJson(json, new TypeToken<List<ContactDate>>(){}.getType()); // List<ContactDate>.class
      return contacts.stream().map((g) -> new Object [] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")//(enabled = false)// Если нужно отключить тест
  public void testContactCreation(ContactDate contact) {
        app.goTo().contactPage();
        //Contacts before = app.contact().all();// из веба
        Contacts before = app.db().contacts();// из базы
        //File photo = new File("src/test/resources/cat.png");
        app.contact().create(contact, true);
        app.goTo().contactPage();
        assertThat(app.contact().сount(), equalTo(before.size() + 1));
        //Contacts after = app.contact().all();// из веба
        Contacts after = app.db().contacts();// из базы
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        //app.getNavigationHelper().logoutUser();// из одного браузера не запускаются тесты из-за этой строки
        verifyContactListInUI();
      }


  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/cat.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test(enabled = false)// Если нужно отключить тест
  public void testBadContactCreation() throws Exception {
    //Contacts before = app.contact().all();// из веба
    Contacts before = app.db().contacts();// из базы
    ContactDate contact = new ContactDate().withFirstname("Ivans'").withLastname("Testovich").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().contactPage();
    assertThat(app.contact().сount(), equalTo(before.size()));
    //Contacts after = app.contact().all();// из веба
    Contacts after = app.db().contacts();// из базы
    assertThat(after, equalTo(before));
    //app.getNavigationHelper().logoutUser();// из одного браузера не запускаются тесты из-за этой строки
    verifyContactListInUI();
  }

}