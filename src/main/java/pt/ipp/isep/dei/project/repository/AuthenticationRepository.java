package pt.ipp.isep.dei.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

/**
 * The AuthenticationRepository class is responsible for handling authentication-related operations.
 * It interacts with the AuthFacade class to perform user authentication, logout, and user role management.
 */
public class AuthenticationRepository implements Serializable {
    private final AuthFacade authenticationFacade;

    /**
     * Constructs a new AuthenticationRepository object and initializes the AuthFacade.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Performs user login with the provided email and password.
     *
     * @param email the email of the user
     * @param pwd   the password of the user
     * @return true if the login is successful, false otherwise
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Performs user logout.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Gets the current user session.
     *
     * @return the current user session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a new user role with the provided ID and description.
     *
     * @param id          the ID of the user role
     * @param description the description of the user role
     * @return true if the user role is added successfully, false otherwise
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a new user with the provided name, email, password, and role ID.
     *
     * @param name   the name of the user
     * @param email  the email of the user
     * @param pwd    the password of the user
     * @param roleId the ID of the user role
     * @return true if the user is added successfully, false otherwise
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<UserDTO> getUsers() {
        return authenticationFacade.getUsers();
    }
}
