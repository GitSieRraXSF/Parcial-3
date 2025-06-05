package controller;

import java.sql.Connection;

import application.Main;
import data.AdminConnection;
import data.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private PasswordField txtContraseña;

	@FXML
	private TextField txtNickname;

	private Connection connection = AdminConnection.getInstance().getConnection();
	private UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

	@FXML
	void iniciarSesion(ActionEvent event) {
		if (usuarioDAO.authenticate(txtNickname.getText(), txtContraseña.getText())) {
			Main.loadView("/view/Inventario.fxml");
		} else {
			Main.showAlert("Error!", "Usuario Invalido!", "Favor ingrese un usuario valido para entrar.", Alert.AlertType.ERROR);
		}
	}
}
