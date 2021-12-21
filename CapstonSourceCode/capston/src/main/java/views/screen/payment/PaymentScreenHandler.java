package views.screen.payment;

import controller.PaymentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.util.Map;

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

    public PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        this.setBController(new PaymentController());

        btnPayment.setOnMouseClicked(e -> {
            try {
                confirmToPayOrder();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        });


    }

    void confirmToPayOrder() throws IOException{
        String contents = "pay order";
        PaymentController ctrl = (PaymentController) getBController();
        Map<String, String> response = ctrl.payDeposit(123, tfContent.getText(), tfCode.getText(), tfNameHolder.getText(),
                tfExpirationDate.getText(), tfSecurityCode.getText());

    }
}
