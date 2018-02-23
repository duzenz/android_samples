package com.commonsware.todo;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by z00284305 on 19-Feb-18.
 */
@AutoValue
public abstract class ToDoModel {

    public abstract String id();

    public abstract boolean isCompleted();

    public abstract String description();

    @Nullable
    public abstract String notes();

    public abstract Calendar createdOn();

    static Builder builder() {
        return new AutoValue_ToDoModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        abstract Builder id(String id);

        public abstract Builder isCompleted(boolean isCompleted);

        public abstract Builder description(String desc);

        public abstract Builder notes(String notes);

        abstract Builder createdOn(Calendar date);

        public abstract ToDoModel build();

    }

    public static Builder creator() {
        return builder()
                .isCompleted(false)
                .id(UUID.randomUUID().toString())
                .createdOn(Calendar.getInstance());
    }

    public Builder toBuilder() {
        return builder()
                .id(id())
                .isCompleted(isCompleted())
                .description(description())
                .notes(notes())
                .createdOn(createdOn());
    }

}
