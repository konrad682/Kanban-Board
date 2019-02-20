package sample;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
public class PriorityColor extends ListCell<InformationaboutTask> {
    @Override
    protected void updateItem(InformationaboutTask item, boolean empty) {
        super.updateItem(item, empty);

        if(item != null) {
            Tooltip tooltip = new Tooltip(item.getInformation());
            setTooltip(tooltip);
            setText(item.getTitle());
            if(item.getPriority().equals("Low")) {
                //setTextFill(Color.GREEN);
                setStyle("-fx-control-inner-background: LimeGreen;");
            }
            if(item.getPriority().equals("Medium")) {
                setStyle("-fx-control-inner-background: Coral;");
            }
            if(item.getPriority().equals("High")) {
                setStyle("-fx-control-inner-background: IndianRed;");
            }

        }
    }
}
