package controllerview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Model.ColorCode;
import javafx.stage.Stage;
import model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	Model model = new Model();

	@FXML
	private Button btnColor;

	@FXML
	private Label lblColor;

	@FXML
	private TextField txtRed;

	@FXML
	private TextField txtGreen;

	@FXML
	private TextField txtBlue;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.update(true);
	}

	public static void show(Stage stage){
		try {
			FXMLLoader loader = new FXMLLoader(Controller.class.getResource("View.fxml"));
			Parent root = loader.load();

			stage.setTitle("Moin");
			stage.setScene(new Scene(root));
			stage.show();

		}catch(Exception ignored){
			System.out.println("Error");
		}
	}

	@FXML
	private void btn_RedPlus()
	{
		model.changeColorViaRelativeValue(ColorCode.RED, 10);
		this.update(true);
	}

	@FXML
	private void btn_RedMinus()
	{
		model.changeColorViaRelativeValue(ColorCode.RED, -10);
		this.update(true);
	}

	@FXML
	private void btn_GreenPlus()
	{
		model.changeColorViaRelativeValue(ColorCode.GREEN, 10);
		this.update(true);
	}

	@FXML
	private void btn_GreenMinus()
	{
		model.changeColorViaRelativeValue(ColorCode.GREEN, -10);
		this.update(true);
	}

	@FXML
	private void btn_BluePlus()
	{
		model.changeColorViaRelativeValue(ColorCode.BLUE, 10);
		this.update(true);
	}

	@FXML
	private void btn_BlueMinus()
	{
		model.changeColorViaRelativeValue(ColorCode.BLUE, -10);
		this.update(true);
	}

	@FXML
	private void txt_RedChanged()
	{
		model.changeColorViaAbsoluteValue(ColorCode.RED, this.txtRed.getText());
		this.update(false);
	}

	@FXML
	private void txt_GreenChanged()
	{
		model.changeColorViaAbsoluteValue(ColorCode.GREEN, this.txtGreen.getText());
		this.update(false);
	}

	@FXML
	private void txt_BlueChanged()
	{
		model.changeColorViaAbsoluteValue(ColorCode.BLUE, this.txtBlue.getText());
		this.update(false);
	}

	void update(boolean updateInputs) {
		int r = model.getRed();
		int g = model.getGreen();
		int b = model.getBlue();

		if(updateInputs) {
			this.txtRed.setText(Integer.toString(r));
			this.txtGreen.setText(Integer.toString(g));
			this.txtBlue.setText(Integer.toString(b));
		}

		this.btnColor.setStyle("-fx-background-color: rgb(" + r + "," + g + ", " + b + ");");
		this.lblColor.setText("Hex: " + model.getHex());
	}



}


