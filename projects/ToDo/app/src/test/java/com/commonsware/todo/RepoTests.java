package com.commonsware.todo;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

public class RepoTests {

    private ToDoRepository repo;

    @Before
    public void setUp() {
        repo=new ToDoRepository();
        repo.add(ToDoModel.creator()
                .description("Buy a copy of _Exploring Android_")
                .notes("See https://wares.commonsware.com")
                .isCompleted(true)
                .build());
        repo.add(ToDoModel.creator()
                .description("Complete all of the tutorials")
                .build());
        repo.add(ToDoModel.creator()
                .description("Write an app for somebody in my community")
                .notes("Talk to some people at non-profit organizations to see what they need!")
                .build());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(3, repo.all().size());
    }

    @Test
    public void add() {
        ToDoModel model = ToDoModel.creator()
                .isCompleted(true)
                .description("foo")
                .build();

        repo.add(model);

        List<ToDoModel> models = repo.all();
        assertEquals(4, models.size());
        assertThat(models, hasItem(model));
    }

    @Test
    public void replace() {
        ToDoModel original = repo.all().get(1);
        ToDoModel edited = original.toBuilder()
                .isCompleted(true)
                .description("Currently on Tutorial #15")
                .build();
        repo.replace(edited);
        assertEquals(3, repo.all().size());
        assertSame(edited, repo.all().get(1));
    }

    @Test
    public void delete() {
        assertEquals(3, repo.all().size());
        repo.delete(repo.all().get(0));
        assertEquals(2, repo.all().size());
        repo.delete(repo.all().get(0).toBuilder().build());
        assertEquals(1, repo.all().size());
    }

}