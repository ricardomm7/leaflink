package pt.ipp.isep.dei.project.application.session;

import pt.ipp.isep.dei.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Application session.
 */
public class ApplicationSession {
    private final AuthenticationRepository authenticationRepository;
    private static final String CONFIGURATION_FILENAME = "src/main/resources/config.properties";
    private static final String COMPANY_DESIGNATION = "Company.Designation";
    private static final String SORTALGORITHM_CLASSNAME = "sorting.algorithm";

    private Properties properties;

    private ApplicationSession() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        properties = loadProperties();
    }

    public UserSession getCurrentSession() {
        pt.isep.lei.esoft.auth.UserSession userSession = this.authenticationRepository.getCurrentUserSession();
        return new UserSession(userSession);
    }

    private Properties loadProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(COMPANY_DESIGNATION, "MusgoSublime");

        // Read configured values
        try (InputStream in = new FileInputStream(CONFIGURATION_FILENAME)) {
            props.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props;
    }

    public String getSortAlgorithmClassName() {
        return properties.getProperty(SORTALGORITHM_CLASSNAME);
    }

    private static ApplicationSession singleton = null;

    public static ApplicationSession getInstance() {
        if (singleton == null) {
            synchronized (ApplicationSession.class) {
                singleton = new ApplicationSession();
            }
        }
        return singleton;
    }
}
