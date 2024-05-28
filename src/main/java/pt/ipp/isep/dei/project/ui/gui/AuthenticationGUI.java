package pt.ipp.isep.dei.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

/**
 * The type Authentication gui.
 */
public class AuthenticationGUI {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private final AuthenticationController ctrl = new AuthenticationController();

    /**
     * Handle login button action.
     */
    @FXML
    void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean success = doLogin(username, password);
        if (success) {
            redirectToRoleGUI(ApplicationSession.getInstance().getCurrentSession().getUserRoles().get(0));
        } else {
            showAlert("Login Failed", "Invalid UserId and/or Password.");
        }
    }

    @FXML
    private void handleUsernameFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            passwordField.requestFocus();
        }
    }

    @FXML
    private void handlePasswordFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLoginButtonAction();
        }
    }

    private boolean doLogin(String username, String password) {
        return ctrl.doLogin(username, password);
    }

    private void redirectToRoleGUI(UserRoleDTO u) {
        String caminho = null;
        try {
            if (u.getId() == AuthenticationController.ROLE_ADMIN) {
                caminho = "mainMenus/adminMenu.fxml";
            }
            if (caminho == null) {
                throw new IllegalArgumentException("Error!!");
            }
        } catch (Exception e) {
            showAlert("Error", "There is no UI assigned to this user.");
        }
        launchActivity(caminho);
    }

    private void launchActivity(String caminho) {
        try {
            Main.loadActivity(caminho, true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /*
        private void redirectToRoleUI() {
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    redirectToRoleUI(rolesUI, role);
                } else {
                    System.out.println("No role selected.");
                }
            }
            ctrl.doLogout();
        }

        private List<MenuItem> getMenuItemForRoles() {
            List<MenuItem> rolesUI = new ArrayList<>();
            rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
            rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI()));
            rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI()));
            rolesUI.add(new MenuItem(AuthenticationController.ROLE_GSM, new GsmUI()));
            rolesUI.add(new MenuItem(AuthenticationController.ROLE_QAM, new QamUI()));
            rolesUI.add(new MenuItem(AuthenticationController.ROLE_COLLAB, new ClbUI()));


            //TODO: Complete with other user roles and related RoleUI
            return rolesUI;
        }


        private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
            if (roles.size() == 1) {
                return roles.get(0);
            } else {
                return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
            }
        }

        private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
            boolean found = false;
            for (MenuItem item : rolesUI) {
                if (item.hasDescription(role.getDescription())) {
                    item.run();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
            }
        }
    */
    /*
    private void closeWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
    */
}
