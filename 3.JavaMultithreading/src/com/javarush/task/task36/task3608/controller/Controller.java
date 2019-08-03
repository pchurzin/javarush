package com.javarush.task.task36.task3608.controller;

import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Controller {
    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void setModel(Model model) {
        this.model = model;
    }

    public void onShowAllUsers() {
        if (model == null) return;
        model.loadUsers();
        if (usersView == null) return;
        usersView.refresh(model.getModelData());
    }

    public void onShowAllDeletedUsers() {
        if (model == null) return;
        model.loadDeletedUsers();
        if (usersView == null) return;
        usersView.refresh(model.getModelData());
    }

    public void onOpenUserEditForm(long userId) {
        if (model == null) return;
        model.loadUserById(userId);
        if (editUserView == null) return;
        editUserView.refresh(model.getModelData());
    }

    public void onUserDelete(long id) {
        if (model == null) return;
        model.deleteUserById(id);
        if (usersView == null) return;
        usersView.refresh(model.getModelData());
    }

    public void onUserChange(String name, long id, int level) {
        if (model == null) return;
        model.changeUserData(name, id, level);
        if (usersView == null) return;
        usersView.refresh(model.getModelData());
    }

    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
    }

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }
}
