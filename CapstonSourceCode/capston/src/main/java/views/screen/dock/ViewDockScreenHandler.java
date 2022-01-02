package views.screen.dock;

import controller.ViewDockController;
import entity.bicycle.Bicycle;
import entity.bicycle.Vehicle;
import entity.dock.Dock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.rent_bicycle.RentBicycleScreenHandler;

import java.io.IOException;
import java.sql.SQLException;

public class ViewDockScreenHandler extends BaseScreenHandler {
    @FXML
    private GridPane gpVehicle;

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbArea;

    @FXML
    private Label lbNameDock;

    @FXML
    private Label lbTITLE;

    @FXML
    private Label lbTotalBike;

    @FXML
    private Button btnBack;

    @FXML
    private VBox vbBicycle;

    @FXML
    private VBox vbCoupleBike;

    @FXML
    private VBox vbElectric;

    @FXML
    private TextField tfBicycleCode;

    @FXML
    private Button btnRent;

    private Dock dock;

    public ViewDockController getBController(){
        return (ViewDockController) super.getBController();
    }

    public ViewDockScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException, SQLException {
        super(stage, screenPath);

        setBController(new ViewDockController());
        this.dock = getBController().getDetailDock(dock.getId());
        setInformation();
    }

    private void setInformation() throws IOException {
        lbTITLE.setText(dock.getName());
        lbNameDock.setText(dock.getName());
        lbArea.setText(dock.getArea().toString() + "m2");
        lbAddress.setText(dock.getAddress());

        vbBicycle.getChildren().clear();
        vbElectric.getChildren().clear();
        vbCoupleBike.getChildren().clear();

        for(Vehicle bicycle : dock.getListBicycle()){
            BicycleHandler bicycleHandler = new BicycleHandler(this.stage, Configs.VEHICLE_COMPONENT_PATH, bicycle);
            vbBicycle.getChildren().add(bicycleHandler.getContent());
        }

        for(Vehicle bicycle : dock.getListCoupleBike()){
            BicycleHandler bicycleHandler = new BicycleHandler(this.stage, Configs.VEHICLE_COMPONENT_PATH, bicycle);
            vbCoupleBike.getChildren().add(bicycleHandler.getContent());
        }

        for(Vehicle bicycle : dock.getListElectricBike()){
            BicycleHandler bicycleHandler = new BicycleHandler(this.stage, Configs.VEHICLE_COMPONENT_PATH, bicycle);
            vbElectric.getChildren().add(bicycleHandler.getContent());
        }

        btnRent.setOnMouseClicked(e -> {
            RentBicycleScreenHandler rentBicycleScreenHandler = null;
            Vehicle vehicle = null;
            try {
                vehicle = getBController().getVehicleInDockByCode(tfBicycleCode.getText(), dock.getId());
                if(vehicle == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Không có xe này trong bãi xe");
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
            if(vehicle != null){
                try {
                    rentBicycleScreenHandler = new RentBicycleScreenHandler(stage, Configs.RENT_BIKE_PATH,vehicle );
                    rentBicycleScreenHandler.setScreenTitle("Bicycle");
                    rentBicycleScreenHandler.show();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


    }

    @FXML
    public void back(ActionEvent event) {
        getPreviousScreen().show();

    }


}
