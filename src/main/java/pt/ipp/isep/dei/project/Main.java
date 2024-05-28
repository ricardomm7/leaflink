package pt.ipp.isep.dei.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pt.ipp.isep.dei.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    private static Stage appStage;

    public static void loadNewActivity(String activity, boolean resizeable, double minWi, double minHe, boolean maximized) throws IOException {
        URL res = Main.class.getResource(activity);
        Parent root = new FXMLLoader(res).load();

        // Criar uma nova cena
        Scene scene = new Scene(root);

        // Configurar a janela principal
        Main.appStage.setScene(scene);
        Main.appStage.setMaximized(maximized);
        Main.appStage.setMinWidth(minWi);
        Main.appStage.setMinHeight(minHe);
        Main.appStage.setResizable(resizeable);
        Main.appStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icon.png"))));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.appStage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Main.appStage.setTitle("LeafLink");
        Main.appStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon.png"))));
        Main.appStage.setScene(new Scene(root));
        Main.appStage.setResizable(false);
        Main.appStage.show();
    }

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.deserializeAll();
        bootstrap.run();
        launch(args);
        bootstrap.serializeAll();
    }
}
