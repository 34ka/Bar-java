package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
//    list.add(new Object[] {new GroupDate().withName("test1").withHeader( "header 1").withFooter( "footer 1")});
//    list.add(new Object[] {new GroupDate().withName("test2").withHeader( "header 2").withFooter( "footer 2")});
//    list.add(new Object[] {new GroupDate().withName("test3").withHeader( "header 3").withFooter( "footer 3")});
    //Блок try для закрытия файла, чтобы не потерять данные
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupDate.class);
      List<GroupDate> groups = (List<GroupDate>)xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object [] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    //Блок try для закрытия файла, чтобы не потерять данные
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupDate> groups = gson.fromJson(json, new TypeToken<List<GroupDate>>(){}.getType()); // List<GroupDate>.class
      return groups.stream().map((g) -> new Object [] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJson")//(enabled = false)
  public void testGroupCreation(GroupDate group) throws Exception {
     app.goTo().groupPage();
     Groups before = app.group().all();
     app.group().create(group);
     assertThat(app.group().count(), equalTo(before.size() + 1));
     Groups after = app.group().all();
     //app.getNavigationHelper().logoutUser(); // из одного браузера не запускаются тесты т.к. происходит logout
    assertThat(after, equalTo(
            before.withAdded( group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)//тест не работает т.к. нет негативной проверки.
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupDate group = new GroupDate().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    //app.getNavigationHelper().logoutUser(); // из одного браузера не запускаются тесты т.к. происходит logout
    assertThat(after, equalTo(before));
  }

}
