module pt.ipp.isep.dei.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires org.apache.commons.lang3;
    requires java.logging;

    opens pt.ipp.isep.dei.project to javafx.fxml;
    exports pt.ipp.isep.dei.project;
    opens pt.ipp.isep.dei.project.ui.gui to javafx.fxml;
    exports pt.ipp.isep.dei.project.ui.gui;
}
