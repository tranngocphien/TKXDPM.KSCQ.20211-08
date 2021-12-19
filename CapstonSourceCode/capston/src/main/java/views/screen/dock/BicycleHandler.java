package views.screen.dock;

import entity.bicycle.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;

public class BicycleHandler extends BaseScreenHandler {
    @FXML
    private Button btnDetail;

    @FXML
    private Label lbBicycleCode;

    @FXML
    private Label lbLocation;

    @FXML
    private Label lbName;

    private Vehicle vehicle;

    public BicycleHandler(Stage stage, String screenPath, Vehicle vehicle) throws IOException {
        super(stage, screenPath);
        this.vehicle = vehicle;
        setBikeInformation();
    }

    public void setBikeInformation(){
        lbName.setText(vehicle.getName());
        lbBicycleCode.setText(vehicle.getBikeCode());
        lbLocation.setText(vehicle.getLocationAtDock().toString());
    }
}
