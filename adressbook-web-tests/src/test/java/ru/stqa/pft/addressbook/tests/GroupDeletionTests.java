package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.group().all().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupDate().withName("test2").withHeader("test2"));
    }
  }

  @Test//(enabled = false)
  public void testGroupDeletion() throws Exception {
    //Groups before = app.group().all();// из веба
    Groups before = app.db().groups();// из базы
    GroupDate deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    //Groups after = app.group().all();// из веба
    Groups after = app.db().groups();// из базы
    assertThat(after, equalTo(before.without(deletedGroup)));
    }

}
