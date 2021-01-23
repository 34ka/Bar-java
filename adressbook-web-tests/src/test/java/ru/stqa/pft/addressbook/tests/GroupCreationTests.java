package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test(enabled = false)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    List<GroupDate> before = app.group().list();
    GroupDate group = new GroupDate().withName("test2");
    app.group().create(group);
    List<GroupDate> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    //app.getNavigationHelper().logoutUser(); // из одного браузера не запускаются тесты т.к. происходит logout

    before.add(group);
    Comparator<? super GroupDate> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
