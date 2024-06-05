package pt.ipp.isep.dei.project.ui.gui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsersGUI {

    private List<UserDTO> allUsers;

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_tasks.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_teams.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_analysis.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void vehicleBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_vehic.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_collab.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void routeBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_routes.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void spacesBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_spaces.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void usersBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_users.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ObservableList<String> observableUserList = FXCollections.observableArrayList();

    @FXML
    private TextField searchUsersBar;

    @FXML
    private ListView<String> usersList;

    @FXML
    private Button removeBtn;


    @FXML
    void enterKeySearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchBtnHandle(new ActionEvent());
        }
    }

    @FXML
    void searchBtnHandle(ActionEvent event) {
        String searchText = searchUsersBar.getText().toLowerCase();
        List<String> filteredUsers = allUsers.stream()
                .filter(user -> user.getName().toLowerCase().contains(searchText))
                .map(user -> user.getName() + " | " + user.getId() + " | " +
                        user.getRoles().stream().map(UserRoleDTO::getDescription).collect(Collectors.joining(", ")))
                .collect(Collectors.toList());
        observableUserList.clear(); // Limpa a lista antes de atualizá-la
        observableUserList.addAll(filteredUsers);
        usersList.setItems(observableUserList);
    }

    @FXML
    void handleRemove(ActionEvent event) {
        int selectedIndex = usersList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            aC.getUsers().remove(selectedIndex);
        }
    }

    private AuthenticationController aC = new AuthenticationController();

    @FXML
    void initialize() {
        updateUsersList();
        usersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtn.setDisable(newValue == null);
        });
        removeBtn.setDisable(true);
    }

    private void updateUsersList() {
        AuthenticationController aC = new AuthenticationController();
        allUsers = aC.getUsers();
        observableUserList.clear(); // Limpa a lista antes de atualizá-la
        for (UserDTO user : allUsers) {
            StringBuilder roles = new StringBuilder();
            for (UserRoleDTO role : user.getRoles()) {
                roles.append(role.getDescription()).append(", ");
            }
            roles.delete(roles.length() - 2, roles.length()); // Remove a última vírgula e o espaço
            String userString = user.getName() + " | " + user.getId() + " | " + roles.toString();
            observableUserList.add(userString);
        }
        usersList.setItems(observableUserList);
    }
}
