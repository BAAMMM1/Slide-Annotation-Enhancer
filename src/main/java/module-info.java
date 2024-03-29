module dev.graumann.slideannotationenhancer.module {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;

    requires dev.graumann.guidecorator;

    exports dev.graumann.slideannotationenhancer;
    exports dev.graumann.slideannotationenhancer.controller;
    opens dev.graumann.slideannotationenhancer.controller;
}