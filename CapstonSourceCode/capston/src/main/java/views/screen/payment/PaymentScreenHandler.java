package views.screen.payment;

import controller.PaymentController;
import controller.RentBicycleController;
import entity.bicycle.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreenHandler;

import java.io.IOException;
import java.util.Map;

import static utils.Configs.userId;

public class PaymentScreenHandler extends BaseScreenHandler {
    @FXML
    private TextField tfCode;

    @FXML
    private TextArea tfContent;

    @FXML
    private TextField tfExpirationDate;

    @FXML
    private TextField tfNameHolder;

    @FXML
    private TextField tfSecurityCode;

    @FXML
    private Button btnPayment;

    public PaymentScreenHandler(Stage stage, String screenPath, Vehicle vehicle) throws IOException {
        super(stage, screenPath);
        this.setBController(new PaymentController());

        btnPayment.setOnMouseClicked(e -> {
            try {
                confirmToPayOrder(vehicle);
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        });


    }

    void confirmToPayOrder(Vehicle vehicle) throws IOException{
        String contents = "pay order";
        PaymentController ctrl = (PaymentController) getBController();
        new RentBicycleController().rentBike(vehicle.getId(), userId);
        try {
            Map<String, String> response = ctrl.payDeposit(123, tfContent.getText(), tfCode.getText(), tfNameHolder.getText(),
                    tfExpirationDate.getText(), tfSecurityCode.getText());
        }
        catch (Exception e) {
            System.out.println("Thanh toán không thành công!");
        }
        pupUp("THÀNH CÔNG", "THUÊ XE THÀNH CÔNG");
    }

    public void pupUp(String title, String message) {

        try {
            PopupScreenHandler popupScreenHandler = new PopupScreenHandler(this.stage, Configs.POPUP_PATH, title, message);
            popupScreenHandler.setScreenTitle("Trả xe thành thông");
            popupScreenHandler.setPreviousScreen(this.homeScreenHandler);
            popupScreenHandler.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
