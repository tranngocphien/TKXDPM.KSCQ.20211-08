import entity.bicycle.Vehicle;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Configs;
import views.screen.home.HomeScreenHandler;
import views.screen.return_bicycle.ReturnBicycleScreenHandler;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage){
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // Load splash screen with fade in effect
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            // Finish splash with fade out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            // After fade in, start fade out
            fadeIn.play();
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            // After fade out, load actual content
            fadeOut.setOnFinished((e) -> {
                try {
//                    HomeScreenHandler homeHandler = new HomeScreenHandler(stage, Configs.HOME_PATH);
//                    homeHandler.setScreenTitle("Home Screen");
//                    homeHandler.show();
                    ReturnBicycleScreenHandler returnBicycleScreenHandler = new ReturnBicycleScreenHandler(stage, Configs.RETURN_BIKE_PATH, new Vehicle());
                    returnBicycleScreenHandler.setScreenTitle("Return Bike");
                    returnBicycleScreenHandler.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
