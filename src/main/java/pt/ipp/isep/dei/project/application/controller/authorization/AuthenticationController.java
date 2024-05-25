package pt.ipp.isep.dei.project.application.controller.authorization;

import pt.ipp.isep.dei.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * The type Authentication controller.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AuthenticationController {

    /**
     * The constant ROLE_ADMIN.
     */
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    /**
     * The constant ROLE_COLLAB.
     */
    public static final String ROLE_COLLAB = "COLLABORATOR";
    /**
     * The constant ROLE_VFM.
     */
    public static final String ROLE_VFM = "VFM";
    /**
     * The constant ROLE_HRM.
     */
    public static final String ROLE_HRM = "HRM";
    /**
     * The constant ROLE_GSM.
     */
    public static final String ROLE_GSM = "GSM";
    /**
     * The constant ROLE_QAM.
     */
    public static final String ROLE_QAM = "QAM";


    //private final ApplicationSession applicationSession;
    private final AuthenticationRepository authenticationRepository;

    /**
     * Instantiates a new Authentication controller.
     */
    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Do login boolean.
     *
     * @param email the email
     * @param pwd   the pwd
     * @return the boolean
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Gets user roles.
     *
     * @return the user roles
     */
    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Do logout.
     */
    public void doLogout() {
        authenticationRepository.doLogout();
    }
}