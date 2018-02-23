package com.commonsware.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.commonsware.todo.databinding.TodoEditBinding;

/**
 * Created by z00284305 on 23-Feb-18.
 */

public class EditFragment extends Fragment {

    private static final String ARG_ID = "id";
    private TodoEditBinding binding;

    static EditFragment newInstance(ToDoModel model) {
        EditFragment result = new EditFragment();
        if (model != null) {
            Bundle args = new Bundle();
            args.putString(ARG_ID, model.id());
            result.setArguments(args);
        }
        return result;
    }

    private String getModelId() {
        return getArguments() == null ? null : getArguments().getString(ARG_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TodoEditBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setModel(ToDoRepository.get().find(getModelId()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actions_edit, menu);
        menu.findItem(R.id.delete).setVisible(binding.getModel() != null);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            save();
            return true;
        } else if (item.getItemId() == R.id.delete) {
            delete();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    interface Contract {
        void finishEdit(boolean deleted);
    }

    private void save() {
        ToDoModel.Builder builder;
        if (binding.getModel() == null) {
            builder = ToDoModel.creator();
        } else {
            builder = binding.getModel().toBuilder();
        }
        ToDoModel newModel = builder
                .description(binding.desc.getText().toString())
                .notes(binding.notes.getText().toString())
                .isCompleted(binding.isCompleted.isChecked())
                .build();
        if (binding.getModel() == null) {
            ToDoRepository.get().add(newModel);
        } else {
            ToDoRepository.get().replace(newModel);
        }
        ((Contract) getActivity()).finishEdit(false);
    }

    private void delete() {
        ToDoRepository.get().delete(binding.getModel());
        ((Contract) getActivity()).finishEdit(true);
    }

}
