package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public class AddNewTaskFrame {
    ObservableList<String> priorityStatusList = FXCollections.observableArrayList("Low","Medium","High");
    @FXML
    private TextField titleField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox priorityChoice = new ChoiceBox(priorityStatusList);
    @FXML
    private TextArea textArea;

    private Stage dialogStage;
    private InformationaboutTask task;

    private boolean okClicked = false;
    @FXML
    private void initialize() {
        priorityChoice.setValue("Low");
        priorityChoice.setItems(priorityStatusList);
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setTask(InformationaboutTask task)
    {
        this.task = task;

        titleField.setText(task.getTitle());
        textArea.setText(task.getDesription());
        priorityChoice.setValue(task.getPriority());
        datePicker.setPromptText(task.getExp_time());
    }
    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        task.setTitle(titleField.getText());
        task.setDesription(textArea.getText());
        task.setPriority(priorityChoice.getValue().toString());
        task.setExp_time(datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        okClicked = true;
        dialogStage.close();

    }
}
