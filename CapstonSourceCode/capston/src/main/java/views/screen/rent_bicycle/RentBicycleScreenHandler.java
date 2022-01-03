package views.screen.rent_bicycle;

import controller.RentBicycleController;
import controller.ViewBorrowingBicycleController;
import entity.bicycle.Bicycle;
import entity.bicycle.CoupleBike;
import entity.bicycle.ElectricBicycle;
import entity.bicycle.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

import java.io.IOException;

public class RentBicycleScreenHandler extends BaseScreenHandler {
    @FXML
    private Label lbBaterry;

    @FXML
    private Label lbBicycleCode;

    @FXML
    private Label lbType;

    @FXML
    private Label tbDeposit;

    @FXML
    private Button btnRentBicycle;

    public RentBicycleController getBController() {
        return (RentBicycleController) super.getBController();
    }

    public RentBicycleScreenHandler(Stage stage, String screenPath, Vehicle vehicle) throws IOException {
        super(stage, screenPath);

        setBController(new RentBicycleController());
        lbBicycleCode.setText(vehicle.getBikeCode());
        if(vehicle instanceof Bicycle){
            lbType.setText("Xe đạp thường");
            lbBaterry.setVisible(false);
        }
        else if (vehicle instanceof CoupleBike){
            lbType.setText("Xe đạp đôi");
            lbBaterry.setVisible(false);
        }
        else {
            lbType.setText("Xe đạp điện");
            lbBaterry.setText(((ElectricBicycle) vehicle).getPin().toString() );
        }

        if(getBController().isBorrowing()){
            btnRentBicycle.setDisable(true);

        }

        btnRentBicycle.setOnMouseClicked(e -> {
            PaymentScreenHandler paymentScreenHandler = null;
            try {
                paymentScreenHandler = new PaymentScreenHandler(stage, Configs.PAYMENT_PATH, vehicle);
                paymentScreenHandler.setScreenTitle("Bicycle");
                paymentScreenHandler.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @FXML
    void back(MouseEvent event) {
        getPreviousScreen().show();

    }

}
