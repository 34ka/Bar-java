package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test(enabled = false)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupDate group = new GroupDate().withName("test2");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    //app.getNavigationHelper().logoutUser(); // из одного браузера не запускаются тесты т.к. происходит logout
    assertThat(after, equalTo(
            before.withAdded( group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test//(enabled = false)//тест не работает т.к. нет негативной проверки.
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
