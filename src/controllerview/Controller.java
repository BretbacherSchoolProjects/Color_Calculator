package controllerview;

import Model.Model;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn_showColor=new Button();

    @FXML
    private Label l_hexcode = new Label("#000000");

    Model model=new Model();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public static void show(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("sample.fxml"));
        Parent root = loader.load();

        stage.setTitle("Moin");
        stage.setScene(new Scene(root, 400, 600));
        stage.show();
    }

    public void handle_txta_input(KeyEvent event){
        model.handle_setColor_exact(event, btn_showColor, l_hexcode);
    }

    public void handle_interval_change(Event event){
        model.handle_interval_change(event, btn_showColor, l_hexcode);
    }
}
