package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.*;
import javafx.util.Callback;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
public class Controller implements Initializable{
    private Main main;
    public int selected = 0;
    public InformationaboutTask copy_task = new InformationaboutTask();
    @FXML
    private ListView<InformationaboutTask> TODOList;
    @FXML
    private ListView<InformationaboutTask> done;
    @FXML
    private ListView<InformationaboutTask> inProgress;

    private ObservableList<InformationaboutTask> ToDoList = FXCollections.observableArrayList();
    private ObservableList<InformationaboutTask> inProgressList = FXCollections.observableArrayList();
    private ObservableList<InformationaboutTask> doneList = FXCollections.observableArrayList();
    @FXML

    private void goAddNewTask() throws IOException{
        InformationaboutTask task_add = new InformationaboutTask();
        main.showAddNewTask(task_add);
        add_to_TODO(task_add);
        setColorTODO();
    }
    @FXML
    private void goFileClose(){
        main.FileClose();
    }
    @FXML
    private void goAbout(){
        main.goAboutFile();
    }

    public void add_to_TODO(InformationaboutTask add_element){
        ToDoList.add(add_element);
        TODOList.setItems(ToDoList);
    }
    public void setColorTODO() {
        TODOList.setCellFactory(new Callback<ListView<InformationaboutTask>, ListCell<InformationaboutTask>>() {
            @Override
            public ListCell<InformationaboutTask> call(ListView<InformationaboutTask> list) {
                return new PriorityColor();
            }
        });
        done.setCellFactory(new Callback<ListView<InformationaboutTask>, ListCell<InformationaboutTask>>() {
            @Override
            public ListCell<InformationaboutTask> call(ListView<InformationaboutTask> list) {
                return new PriorityColor();
            }
        });
        inProgress.setCellFactory(new Callback<ListView<InformationaboutTask>, ListCell<InformationaboutTask>>() {
            @Override
            public ListCell<InformationaboutTask> call(ListView<InformationaboutTask> list) {
                return new PriorityColor();
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TODOList.setItems(ToDoList);
        inProgress.setItems(inProgressList);
        done.setItems(doneList);

        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Delete");

        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
//                System.out.println(TODOList.getItems().get(0)+" "+TODOList.getSelectionModel().getSelectedIndex());
                int selected_index = done.getSelectionModel().getSelectedIndex();
                done.getItems().remove(selected_index);
            }
        });

        MenuItem item2 = new MenuItem("Edit");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                InformationaboutTask selected = TODOList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    boolean okClicked = false;
                    try {
                        main.showAddNewTask(selected);
                        TODOList.refresh();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        contextMenu.getItems().addAll(item1,item2);
        inProgress.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard drag = inProgress.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(inProgress.getSelectionModel().getSelectedItem().getTitle());

                selected = inProgress.getSelectionModel().getSelectedIndex();
                copy_task.setTitle(inProgress.getSelectionModel().getSelectedItem().getTitle());
                copy_task.setPriority(inProgress.getSelectionModel().getSelectedItem().getPriority());
                copy_task.setExp_time(inProgress.getSelectionModel().getSelectedItem().getExp_time());
                copy_task.setDesription(inProgress.getSelectionModel().getSelectedItem().getDesription());

                drag.setContent(content);
            }
        });

        inProgress.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {

            }

        });

        TODOList.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard drag = TODOList.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(TODOList.getSelectionModel().getSelectedItem().getTitle());

                selected = TODOList.getSelectionModel().getSelectedIndex();
                copy_task.setTitle(TODOList.getSelectionModel().getSelectedItem().getTitle());
                copy_task.setPriority(TODOList.getSelectionModel().getSelectedItem().getPriority());
                copy_task.setDesription(TODOList.getSelectionModel().getSelectedItem().getDesription());
                copy_task.setExp_time(TODOList.getSelectionModel().getSelectedItem().getExp_time());

                drag.setContent(content);
            }
        });

        TODOList.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {

            }

        });


        inProgress.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                inProgress.setBlendMode(BlendMode.DIFFERENCE);
            }
        });

        inProgress.setOnDragExited(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                inProgress.setBlendMode(null);
            }
        });

        inProgress.setOnDragOver(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
        });

        inProgress.setOnDragDropped(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                InformationaboutTask tmp_task = new InformationaboutTask();
                tmp_task.setTitle(copy_task.getTitle());
                tmp_task.setDesription(copy_task.getDesription());
                tmp_task.setExp_time(copy_task.getExp_time());
                tmp_task.setPriority(copy_task.getPriority());

                TODOList.getItems().remove(selected);
                inProgress.getItems().add(tmp_task);

                dragEvent.setDropCompleted(true);
            }
        });

        done.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                done.setBlendMode(BlendMode.DIFFERENCE);
            }
        });


        done.setOnDragExited(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                done.setBlendMode(null);
            }
        });

        done.setOnDragOver(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
        });

        done.setOnDragDropped(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                InformationaboutTask tmp_task = new InformationaboutTask();
                tmp_task.setTitle(copy_task.getTitle());
                tmp_task.setDesription(copy_task.getDesription());
                tmp_task.setExp_time(copy_task.getExp_time());
                tmp_task.setPriority(copy_task.getPriority());

                done.getItems().add(tmp_task);

                inProgress.getItems().remove(selected);

                dragEvent.setDropCompleted(true);
            }
        });

        TODOList.setContextMenu(contextMenu);
        done.setContextMenu(contextMenu);
        inProgress.setContextMenu(contextMenu);
        TODOList.refresh();
        done.refresh();
        inProgress.refresh();
    }

    @FXML
    public void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save lists");

        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Bin files (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(null);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject("to do");
            outputStream.writeObject(new ArrayList<>(ToDoList));
             outputStream.writeObject("progress");
            outputStream.writeObject(new ArrayList<>(inProgressList));
             outputStream.writeObject("done");
            outputStream.writeObject(new ArrayList<>(doneList));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open lists");

        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Bin files (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(null);

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            String d = (String) inputStream.readObject();
            List<InformationaboutTask> list = (List<InformationaboutTask>) inputStream.readObject();
            String p = (String) inputStream.readObject();
            List<InformationaboutTask> progressList = (List<InformationaboutTask>) inputStream.readObject();
             String o = (String) inputStream.readObject();
            List<InformationaboutTask> doneLi = (List<InformationaboutTask>) inputStream.readObject();


            ToDoList.removeAll();
            TODOList.refresh();
            ToDoList.addAll(list);
            inProgressList.removeAll();
            inProgress.refresh();
            inProgressList.addAll(progressList);
            doneList.removeAll();
            done.refresh();
            doneList.addAll(doneLi);
            TODOList.refresh();
            inProgress.refresh();
            done.refresh();

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("File  not exist or wrong extencion!!");

            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void importCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open lists");

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            List<InformationaboutTask> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(line.equals("progress")) {
                    break;
                }
                String[] fields = line.split(",", -1);
                list.add(new InformationaboutTask(fields[0], fields[1], fields[2], fields[3]));
            }
            ToDoList.removeAll();
            ToDoList.addAll(list);

            List<InformationaboutTask> progressList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(line.equals("done")) {
                    break;
                }
                String[] fields = line.split(",", -1);
                //System.out.println(fields[0]);
                progressList.add(new InformationaboutTask(fields[0], fields[1], fields[2], fields[3]));
            }
            inProgressList.removeAll();
            inProgressList.addAll(progressList);
            inProgress.refresh();

            List<InformationaboutTask> doneLi = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                doneLi.add(new InformationaboutTask(fields[0], fields[1], fields[2], fields[3]));
            }
            doneList.removeAll();
            doneList.addAll(doneLi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exportCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save lists");

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            for (InformationaboutTask event : ToDoList) {
                String text = event.title + "," + event.description + "," + event.exp_time + "," + event.priority + "\n";
                writer.write(text);
            }
            writer.write("progress\n");
            for (InformationaboutTask event : inProgressList) {
                String text = event.title + "," + event.description + "," + event.exp_time + "," + event.priority + "\n";
                writer.write(text);
            }
            writer.write("done\n");
            for (InformationaboutTask event : doneList) {
                String text = event.title + "," + event.description + "," + event.exp_time + "," + event.priority + "\n";
                writer.write(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
