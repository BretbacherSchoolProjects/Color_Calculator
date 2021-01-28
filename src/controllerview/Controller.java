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
import javafx.scene.control.TextArea;
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
        TextArea txta=(TextArea)event.getSource();
        String hexcode = Model.handle_setColor_exact(event.getCode().getCode(), Integer.parseInt(txta.getText()), txta.getId());
        btn_showColor.setStyle("-fx-background-color: " + hexcode + ";");
        l_hexcode.setText(hexcode);
    }

    public void handle_interval_change(Event event){
        Button btn=(Button)event.getSource();
        String hexcode=Model.handle_interval_change(btn.getText(), btn.getId());
        btn_showColor.setStyle("-fx-background-color: " + hexcode + ";");
        l_hexcode.setText(hexcode);
    }
}
