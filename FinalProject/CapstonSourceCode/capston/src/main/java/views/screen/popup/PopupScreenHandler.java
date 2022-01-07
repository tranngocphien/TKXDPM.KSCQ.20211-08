package views.screen.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;

public class PopupScreenHandler extends BaseScreenHandler {

    @FXML
    private Label messagePopup;

    @FXML
    private Label titlePopup;

    @FXML
    private Button backButton;

    @FXML
    private Button btnBack;

    public PopupScreenHandler(Stage stage, String screenPath, String title, String message) throws IOException {
        super(stage, screenPath);
        messagePopup.setText(message);
        titlePopup.setText(title);

        backButton.setOnAction(event -> {
            HomeScreenHandler homeScreenHandler = null;
            try {
                homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeScreenHandler.setScreenTitle("Trang chủ");
            homeScreenHandler.setPreviousScreen(this.homeScreenHandler);
            homeScreenHandler.show();
        });
    }

    public PopupScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

        btnBack.setOnAction(event -> {
            HomeScreenHandler homeScreenHandler = null;
            try {
                homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeScreenHandler.setScreenTitle("Trang chủ");
            homeScreenHandler.setPreviousScreen(this.homeScreenHandler);
            homeScreenHandler.show();
        });
    }
}
