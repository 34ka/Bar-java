package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test//(enabled = false)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupDate> before = app.group().all();
    GroupDate group = new GroupDate().withName("test2");
    app.group().create(group);
    Set<GroupDate> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    //app.getNavigationHelper().logoutUser(); // из одного браузера не запускаются тесты т.к. происходит logout

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }
}
