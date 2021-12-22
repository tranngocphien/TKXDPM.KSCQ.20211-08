package views.screen.return_bicycle;

import controller.ReturnBikeController;
import controller.SearchDockController;
import entity.bicycle.Vehicle;
import entity.dock.Dock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.dock.ViewDockScreenHandler;
import views.screen.popup.PopupScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ReturnBicycleScreenHandler extends BaseScreenHandler {

    @FXML
    private Button confirmReturnBike;

    @FXML
    private Button cancelReturnBike;

    @FXML
    private ComboBox cbbListDock;

    @FXML
    private Label bikeCode;

    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    public ReturnBicycleScreenHandler(Stage stage, String screenPath, Vehicle vehicle) throws IOException {
        super(stage, screenPath);
        setBController(new ReturnBikeController());
        vehicle.setBikeCode(vehicle.getBikeCode());

//        ObservableList<String> data = FXCollections.observableArrayList("text1", "text2", "text3");
//        cbbListDock.setItems(data);
        bikeCode.setText(vehicle.getBikeCode());
        List<Dock> dockList = getBController().getListDock();
        List<String> items = dockList.stream().map(t->t.getName()).collect(Collectors.toList());
        cbbListDock.getItems().setAll(items);

        confirmReturnBike.setOnAction(event -> {
            returnBike();
        });

    }

    public void returnBike() {
        try {
            String str = cbbListDock.getValue().toString();
            Long dockId = Long.parseLong(str.substring(7));
            System.out.println(str.substring(7));
            System.out.println("Mã xe trả: "+ bikeCode.getText());
        } catch (Exception e) {

//            try {
//                PopupScreenHandler popupScreenHandler = new PopupScreenHandler(this.stage, Configs.POPUP_PATH, "KẾT QUẢ TRẢ XE", "TRẢ XE THÀNH CÔNG");
//                popupScreenHandler.setScreenTitle("Trả xe thành thông");
//                popupScreenHandler.setPreviousScreen(this.homeScreenHandler);
//                popupScreenHandler.show();
//            } catch (IOException io) {
//                io.printStackTrace();
//            }
            e.printStackTrace();
        }

        //getBController().returnBike(1l,dockId);

        PopupScreenHandler popupScreenHandler = null;
        try {
            popupScreenHandler = new PopupScreenHandler(this.stage, Configs.POPUP_PATH, "KẾT QUẢ TRẢ XE", "TRẢ XE THÀNH CÔNG");
        } catch (IOException e) {
            e.printStackTrace();
        }
        popupScreenHandler.setScreenTitle("Trả xe thành thông");
        popupScreenHandler.setPreviousScreen(this.homeScreenHandler);
        popupScreenHandler.show();
    }
}
