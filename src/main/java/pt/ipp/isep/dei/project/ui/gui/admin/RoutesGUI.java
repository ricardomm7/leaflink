package pt.ipp.isep.dei.project.ui.gui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.WaterIrrigationController;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.File;


public class RoutesGUI {

    private final WaterIrrigationController w1 = new WaterIrrigationController();

    private String filePath;

    @FXML
    private TextField filepathInput;

    @FXML
    private ImageView media2;

    @FXML
    private ImageView media1;

    @FXML
    private Button executeBtn;

    @FXML
    void handleOpenNewBtn(ActionEvent event) {
        executeBtn.setDisable(true);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");

        // Define o filtro de extensão para mostrar apenas arquivos CSV
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Obtém o palco associado ao evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Mostra o seletor de arquivo e obtém o arquivo selecionado
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Se o arquivo foi selecionado, define o caminho no campo de entrada
        if (selectedFile != null) {
            filepathInput.setText(selectedFile.getAbsolutePath());
            filePath = selectedFile.getAbsolutePath();
            executeBtn.setDisable(false);
        }
    }

    public void loadSVGImages() {
        try {
            String baseDir = System.getProperty("user.dir") + File.separator + "goOut";
            String allRoutesImagePath = baseDir + File.separator + "_AllRoutesImage.png";
            String mstRoutesImagePath = baseDir + File.separator + "_MSTRoutesImage.png";

            // Carrega a imagem PNG do arquivo _AllRoutesImage.png
            Image allRoutesImage = new Image(new File(allRoutesImagePath).toURI().toString());
            media1.setImage(allRoutesImage);

            // Carrega a imagem PNG do arquivo _MSTRoutesImage.png
            Image mstRoutesImage = new Image(new File(mstRoutesImagePath).toURI().toString());
            media2.setImage(mstRoutesImage);
        } catch (Exception e) {
            e.printStackTrace();
            ShowError.showAlert("Error", e.getLocalizedMessage(), "Error defining the images.");
        }
    }

    @FXML
    void handleExecuteBtn(ActionEvent event) {
        w1.execute(filePath);
        loadSVGImages();
    }


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
    void routeBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_routes.fxml", true, 1205, 900, true);
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
    void spacesBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_spaces.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void usersBtnActionHandle(ActionEvent event) {

    }

}

