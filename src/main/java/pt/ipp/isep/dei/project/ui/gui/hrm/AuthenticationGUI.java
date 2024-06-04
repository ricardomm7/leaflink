package pt.ipp.isep.dei.project.ui.gui.hrm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.ui.ShowError;
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
    private Label errorLabel;

    private final AuthenticationController ctrl = new AuthenticationController();

    /**
     * Handle login button action.
     */
    @FXML
    void handleLoginButtonAction() {
        errorLabel.setVisible(false);
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean success = doLogin(username, password);
        if (success) {
            redirectToRoleGUI(ApplicationSession.getInstance().getCurrentSession().getUserRoles().get(0));
        } else {
            errorLabel.setText("Invalid E-Mail and/or Password. Please try again.");
            errorLabel.setVisible(true);
            //ShowError.showAlert("Login Failed", "Invalid UserId and/or Password.", null);
        }
    }

    @FXML
    private void handleUsernameFieldKeyPressed(KeyEvent event) {
        errorLabel.setVisible(false);
        if (event.getCode() == KeyCode.ENTER) {
            passwordField.requestFocus();
        }
    }

    @FXML
    private void handlePasswordFieldKeyPressed(KeyEvent event) {
        errorLabel.setVisible(false);
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
            if (u.getId() == AuthenticationController.ROLE_HRM) {
                caminho = "mainMenus/HRM/hrmMenu.fxml";
            }
            if (caminho == null) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            ShowError.showAlert("Error", "There is no UI assigned to this user.", null);
        }
        launchActivity(caminho);
    }

    private void launchActivity(String caminho) {
        try {
            Main.loadNewActivity(caminho, true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
