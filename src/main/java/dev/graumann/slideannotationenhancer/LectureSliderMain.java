package dev.graumann.slideannotationenhancer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import dev.graumann.guidecorator.GUIDecorator;

public class LectureSliderMain extends Application {

    private static final String MAIN_VIEW_PATH = "/dev/graumann/slideannotationenhancer/fxml/main.fxml";
    private static final String STAGE_TITLE = "Slide Annotation Enhancer";
    private static final String STYLE_CSS = "/dev/graumann/slideannotationenhancer/css/style.css";

    private static final String SVG_ICON_TITELBAR_LOGO = "M11.99 18.54l-7.37-5.73L3 14.07l9 7 9-7-1.63-1.27zM12 16l7.36-5.73L21 9l-9-7-9 7 1.63 1.27L12 16zm0-11.47L17.74 9 12 13.47 6.26 9 12 4.53z";


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(MAIN_VIEW_PATH));
        Parent userContent = fxmlLoader.load();

        // Style hier adden, in der main.fxml führt dazu, dass die .css nur bei run, aber nicht im jar gefunden wird
        userContent.getStylesheets().clear();
        userContent.getStylesheets().add(getClass().getResource(STYLE_CSS).toExternalForm());

        Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
        double width = bounds.getWidth() / 2.5;
        double height = bounds.getHeight() / 2.5;

        Scene scene = new Scene(userContent, width, height);

        primaryStage.setTitle(STAGE_TITLE);
        primaryStage.setScene(scene);

        
        GUIDecorator decorator = new GUIDecorator();
        decorator.decorate(primaryStage);
        decorator.setTitelbarSVGIconContent(SVG_ICON_TITELBAR_LOGO);
        decorator.setRepositoryURL("https://github.com/BAAMMM1/PDF-Slide-Annotation-Enhancer");
        decorator.setBtnTitelbarFullscreenDisable(true);
        decorator.setBtnTitelbarFullscreenVisible(false);
        

        primaryStage.show();

    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
