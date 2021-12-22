package views.screen.rent_bicycle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

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

    public RentBicycleScreenHandler(Stage stage, String screenPath, String id) throws IOException {
        super(stage, screenPath);
    }

}
