package views.screen.home;

import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.dock.ViewDockScreenHandler;

import java.io.IOException;
import java.sql.SQLException;

public class DockHandler extends BaseScreenHandler {
    @FXML
    private Button btDetailDock;

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbArea;

    @FXML
    private Label lbDockName;

    @FXML
    private Label lbTotalBike;

    private Dock dock;

    public DockHandler(Stage stage, String screenPath, Dock dock) throws IOException {
        super(stage,screenPath);
        this.dock = dock;
        setDockInfo();
    }

    private void setDockInfo(){
        lbDockName.setText(dock.getName());
        lbTotalBike.setText(dock.getTotalBike().toString());
        lbAddress.setText(dock.getAddress());
        lbArea.setText(dock.getArea().toString() + "m2");

        btDetailDock.setOnMouseClicked(e -> {
            ViewDockScreenHandler dockScreenHandler = null;
            try {
                dockScreenHandler = new ViewDockScreenHandler(this.stage, Configs.DOCK_DETAIL_PATH, this.dock);
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
            dockScreenHandler.setScreenTitle("CHI TIẾT BÃI XE");
            dockScreenHandler.setPreviousScreen(this.homeScreenHandler);
            dockScreenHandler.show();
        });
    }
}
