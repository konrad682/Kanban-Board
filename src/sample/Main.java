package sample;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kanban Table");
        primaryStage.setScene(new Scene(root, 700, 550));
        primaryStage.show();
    }
    public static boolean showAddNewTask(InformationaboutTask task) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AddNewTaskWindow.fxml"));
        AnchorPane AddNewTask = loader.load();
        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Add new task");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(AddNewTask);
        addDialogStage.setScene(scene);
        AddNewTaskFrame controller = loader.getController();
        controller.setDialogStage(addDialogStage);
        controller.setTask(task);
        addDialogStage.showAndWait();
        return controller.isOkClicked();
    }

    public static void goAboutFile(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Version: 0.3.1\n\nAuthor: Konrad Wędzicha");
        alert.showAndWait();
    }
    public static void FileClose(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want exit the application ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
