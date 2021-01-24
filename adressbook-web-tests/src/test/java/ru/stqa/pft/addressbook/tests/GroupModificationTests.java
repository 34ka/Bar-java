package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupDate().withName("test1").withHeader("test2"));
        }
    }

    @Test(enabled = false)
    public void testGroupModification() {
        Set<GroupDate> before = app.group().all();
        GroupDate modifiedGroup = before.iterator().next();
        GroupDate group = new GroupDate()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        Set<GroupDate> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        app.goTo().logoutUser();

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
