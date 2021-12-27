package views.screen.payment;

import controller.PaymentController;
import controller.RentBicycleController;
import entity.bicycle.Bicycle;
import entity.bicycle.CoupleBike;
import entity.bicycle.ElectricBicycle;
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

import static utils.Configs.POPUP_PATH_ERROR;
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
        try {
            double amountDeposit = 20000;
            if (vehicle instanceof ElectricBicycle) amountDeposit = vehicle.getDeposit(3);
            else if (vehicle instanceof CoupleBike) amountDeposit = vehicle.getDeposit(2);
            else amountDeposit = vehicle.getDeposit(1);

            Map<String, String> response = ctrl.payDeposit((int) amountDeposit, tfContent.getText(), tfCode.getText(), tfNameHolder.getText(),
                    tfExpirationDate.getText(), tfSecurityCode.getText(), vehicle.getId(), vehicle.getDockId());
            new RentBicycleController().rentBike(vehicle.getId(), userId);
            pupUp(Configs.POPUP_PATH_SUCCESS, "THANH TOÁN GIAO DỊCH", "THANH TOÁN THÀNH CÔNG \n THUÊ XE THÀNH CÔNG");
        }
        catch (Exception e) {
            System.out.println("Thanh toán không thành công!");
            popUpError(POPUP_PATH_ERROR);
        }

    }

    public void pupUp(String path,String title, String message) {

        try {
            PopupScreenHandler popupScreenHandler = new PopupScreenHandler(this.stage, path, title, message);
            popupScreenHandler.setScreenTitle("Trả xe thành thông");
            popupScreenHandler.setPreviousScreen(this.homeScreenHandler);
            popupScreenHandler.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popUpError(String path) {
        try {
            PopupScreenHandler popupScreenHandler = new PopupScreenHandler(this.stage, path);
            popupScreenHandler.setScreenTitle("TThanh toán ");
            popupScreenHandler.setPreviousScreen(this.homeScreenHandler);
            popupScreenHandler.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
