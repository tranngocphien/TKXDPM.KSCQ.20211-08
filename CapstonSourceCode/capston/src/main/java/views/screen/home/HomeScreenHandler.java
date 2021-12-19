package views.screen.home;

import controller.SearchDockController;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeScreenHandler extends BaseScreenHandler{
    @FXML
    private Button buttonDock;

    @FXML
    private GridPane gdListDocks;

    @FXML
    private TextField searchDockTextField;

    private List homeItems;

    public SearchDockController getBController() {
        return (SearchDockController) super.getBController();
    }

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setBController(new SearchDockController());
        try {
            List<Dock> docks = getBController().getListDock();
            this.homeItems = new ArrayList();
            for (Dock dock : docks) {
                DockHandler dockHandler = new DockHandler(this.stage,Configs.DOCK_COMPONENT_PATH, dock);
                dockHandler.setPreviousScreen(this);
                this.homeItems.add(dockHandler);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        addDockComponent(this.homeItems);

        buttonDock.setOnMouseClicked(e -> {
            searchDockByString();
        });
    }


    public void addDockComponent(List items) {
        ArrayList dockItems = (ArrayList) ((ArrayList) items).clone();
        gdListDocks.getChildren().clear();
        gdListDocks.setGridLinesVisible(true);
        gdListDocks.setVgap(40);
        gdListDocks.setAlignment(Pos.BASELINE_CENTER);
        gdListDocks.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(5.0), BorderWidths.DEFAULT)));
        for (int i = 0; i < dockItems.size(); i++) {
            DockHandler dockHandler = (DockHandler) dockItems.get(i);
            gdListDocks.add(dockHandler.getContent(), i % 3, (int) i / 3);
        }
        return;

    }

    public void searchDockByString() {
        List items = new ArrayList();
        try {
            System.out.println("search");
            String search = searchDockTextField.getText();
            System.out.println(search);
            List<Dock> docks = getBController().searchDockByString(search);
            for (Dock dock : docks) {
                DockHandler dockHandler = new DockHandler(this.stage,Configs.DOCK_COMPONENT_PATH, dock);
                dockHandler.setPreviousScreen(this);
                items.add(dockHandler);
            }
            addDockComponent(items);
        } catch (SQLException | IOException exp) {
            exp.printStackTrace();
        }
    }
}
