package views.screen.bicycle;

import controller.SearchDockController;
import controller.ViewBorrowingBicycleController;
import entity.bicycle.BorrowingBike;
import entity.bicycle.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.return_bicycle.ReturnBicycleScreenHandler;

import java.io.IOException;

import static utils.Configs.userId;

public class ViewBorrowingBicycleScreenHandler extends BaseScreenHandler {

    @FXML
    private Button btnReturnBike;

    @FXML
    private Label bikeCode;

    @FXML
    private Label bikeName;

    @FXML
    private Label pin;

    @FXML
    private Label deposit;

    @FXML
    private Label totalTime;

    @FXML
    private Label totalMoney;

    public ViewBorrowingBicycleController getBController() {
        return (ViewBorrowingBicycleController) super.getBController();
    }

    public ViewBorrowingBicycleScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setBController(new ViewBorrowingBicycleController());

        BorrowingBike borrowingBike = getBController().viewDetailBorrowingByUserId(userId);
        Vehicle vehicle = getBController().detailVehicle(borrowingBike.getBikeId());

        bikeCode.setText(vehicle.getBikeCode());
        bikeName.setText(vehicle.getName());
        pin.setText("Lượng pin còn lại: " +vehicle.getPin());
        deposit.setText(borrowingBike.getDeposit().toString());
        totalTime.setText(borrowingBike.getTotalTimeDisplay());
        totalMoney.setText(borrowingBike.getTotalAmount().toString());

        btnReturnBike.setOnMouseClicked(e->{
            returnBike(vehicle);
        });

    }

    public void returnBike(Vehicle vehicle) {
        try {
            ReturnBicycleScreenHandler returnBicycleScreenHandler = new ReturnBicycleScreenHandler(stage, Configs.RETURN_BIKE_PATH, vehicle);
            returnBicycleScreenHandler.setScreenTitle("TRẢ XE");
            returnBicycleScreenHandler.setPreviousScreen(this.homeScreenHandler);
            returnBicycleScreenHandler.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
