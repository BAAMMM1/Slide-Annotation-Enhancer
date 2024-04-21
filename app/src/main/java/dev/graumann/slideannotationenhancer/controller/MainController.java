package dev.graumann.slideannotationenhancer.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import dev.graumann.slideannotationenhancer.model.PDFMixer;
import dev.graumann.slideannotationenhancer.model.Slide;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    PDFMixer model;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnFileChooser;

    @FXML
    private ComboBox<Slide> cBSlide;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnFileClear;

    @FXML
    private ListView<File> listViewChoosed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new PDFMixer();

        this.initCbSlide();

        btnFileChooser.setOnMouseClicked(this.btnFilePickerOnMouseClicked());
        btnFileClear.setOnMouseClicked(this.btnFileClearOnMouseClicked());
        cBSlide.setOnMouseClicked(this.cBSlideOnMouseClicked());
        btnStart.setOnMouseClicked(this.btnMergeSetOnMouseClicked());

        this.listViewOnMousePressed();

    }

    private EventHandler<? super MouseEvent> btnMergeSetOnMouseClicked() {
        return event -> {
            ArrayList<File> selection = new ArrayList<>();

            for (File file : listViewChoosed.getItems()) {
                selection.add(file);
            }
            boolean flag = this.model.makePDF(cBSlide.getSelectionModel().getSelectedItem(), selection);

            Alert alert;
            if (flag) {
                alert = this.generateAlter(Alert.AlertType.INFORMATION, "Information Dialog",
                        "Erfolgreich durchgeführt", "PDF(s) wurden erfolgreich bearbeitet");
            } else {
                alert = this.generateAlter(Alert.AlertType.ERROR, "Information Dialog",
                        "Error", "PDF(s) konnten nicht bearbeitet werden");
            }

            alert.showAndWait();
            listViewChoosed.getItems().clear();
        };
    }

    private Alert generateAlter(Alert.AlertType type, String titel, String headerText, String context) {
        Alert alert = new Alert(type);
        alert.setTitle(titel);
        alert.setHeaderText(headerText);
        alert.setContentText(context);
        return alert;
    }

    private void initCbSlide() {

        for (Slide slide : model.getSlides()) {
            cBSlide.getItems().add(slide);
        }

        cBSlide.getSelectionModel().selectFirst();
    }

    private EventHandler<? super MouseEvent> cBSlideOnMouseClicked() {
        return event -> {

        };
    }

    private EventHandler<MouseEvent> btnFilePickerOnMouseClicked() {
        return event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
            List<File> selected = fileChooser.showOpenMultipleDialog(null);

            if (selected != null && !selected.isEmpty()) {
                for (File file : selected) {
                    listViewChoosed.getItems().add(file);
                }
            }
        };
    }

    private EventHandler<MouseEvent> btnFileClearOnMouseClicked() {
        return event -> {
            listViewChoosed.getItems().clear();
        };
    }

    private void listViewOnMousePressed() {
        listViewChoosed.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });
    }

}
