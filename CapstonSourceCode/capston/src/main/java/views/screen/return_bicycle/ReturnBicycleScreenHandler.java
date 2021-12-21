package views.screen.return_bicycle;

import controller.ReturnBikeController;
import controller.SearchDockController;
import entity.bicycle.Vehicle;
import entity.dock.Dock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ReturnBicycleScreenHandler extends BaseScreenHandler {

    @FXML
    private Button confirmReturnBike;

    @FXML
    private Button cancelReturnBike;

    @FXML
    private ComboBox cbbListDock;

    @FXML
    private Label bikeCode;

    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    public ReturnBicycleScreenHandler(Stage stage, String screenPath, Vehicle vehicle) throws IOException {
        super(stage, screenPath);
        setBController(new ReturnBikeController());
        vehicle.setBikeCode("BIKECO0010");

//        ObservableList<String> data = FXCollections.observableArrayList("text1", "text2", "text3");
//        cbbListDock.setItems(data);
        bikeCode.setText(vehicle.getBikeCode());
        List<Dock> dockList = getBController().getListDock();
        List<String> items = dockList.stream().map(t->t.getName()).collect(Collectors.toList());
        cbbListDock.getItems().setAll(items);

        confirmReturnBike.setOnAction(event -> {
            returnBike();
        });

    }

    public void returnBike() {
        String str = cbbListDock.getValue().toString();
        System.out.println(str.substring(7));
        System.out.println("Mã xe trả: "+ bikeCode.getText());
    }
}
