package views.screen.bicycle;

import entity.bicycle.Vehicle;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;

public class ViewInfoBicycleScreenHandler extends BaseScreenHandler {
    public ViewInfoBicycleScreenHandler(Stage stage, String screenPath, Vehicle vehicle) throws IOException {
        super(stage, screenPath);
    }
}
