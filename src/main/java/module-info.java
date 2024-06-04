module pt.ipp.isep.dei.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires org.apache.commons.lang3;
    requires java.logging;
    requires java.xml;

    opens pt.ipp.isep.dei.project to javafx.fxml;
    exports pt.ipp.isep.dei.project;
    exports pt.ipp.isep.dei.project.ui.gui.admin;
    opens pt.ipp.isep.dei.project.ui.gui.admin to javafx.fxml;
    exports pt.ipp.isep.dei.project.ui.gui.hrm;
    opens pt.ipp.isep.dei.project.ui.gui.hrm to javafx.fxml;
}
