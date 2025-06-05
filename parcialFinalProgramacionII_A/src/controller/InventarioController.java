package controller;

import java.sql.Connection;
import java.util.ArrayList;
import application.Main;
import data.AdminConnection;
import data.ArticuloDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Articulo;

public class InventarioController {

	@FXML
	private TableColumn<Articulo, String> columnAutor;

	@FXML
	private TableColumn<Articulo, Integer> columnAño;

	@FXML
	private TableColumn<Articulo, Integer> columnNumPags;

	@FXML
	private TableColumn<Articulo, String> columnTitulo;

	@FXML
	private TableView<Articulo> tableInventario;

	private Connection connection = AdminConnection.getInstance().getConnection();
	private ArticuloDAO articuloDAO = new ArticuloDAO(connection);

	@FXML
	void initialize() {
		ObservableList<Articulo> availableProductos = FXCollections.observableArrayList();
		for (Articulo articulo : articuloDAO.fetch()) {
			availableProductos.add(articulo);
		}
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
		columnAño.setCellValueFactory(new PropertyValueFactory<>("Año"));
		columnNumPags.setCellValueFactory(new PropertyValueFactory<>("NumPags"));
		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
		tableInventario.setItems(availableProductos);
		ArrayList<Articulo> articulos = articuloDAO.fetch();
		LoadTableView(articulos);
	}

	@FXML
	void cerrarSesion(ActionEvent event) {
		Main.loadView("/view/Login.fxml");
	}

	@FXML
	void eliminar(ActionEvent event) {
		if (!tableInventario.getSelectionModel().isEmpty()) {
			Articulo articulo = tableInventario.getSelectionModel().getSelectedItem();
			articuloDAO.delete(articulo.getDoi());
			initialize();
		} else {
			Main.showAlert("Error!", "Delete invalido.", "Deberas seleccionar un articulo de la tabla.",
					Alert.AlertType.ERROR);
		}
		initialize();
	}

	private void LoadTableView(ArrayList<Articulo> productos) {
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
		columnAutor.setCellFactory(TextFieldTableCell.forTableColumn());
		columnAutor.setOnEditCommit(event -> {
			Articulo product = event.getRowValue();
			product.setAutor(event.getNewValue());
			articuloDAO.update(product);
		});
		columnAño.setCellValueFactory(new PropertyValueFactory<>("Año"));
		columnAño.setCellValueFactory(new PropertyValueFactory<>("price"));
		columnAño.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		columnAño.setOnEditCommit(event -> {
			Articulo product = event.getRowValue();
			product.setAño(event.getNewValue());
			articuloDAO.update(product);
		});
		columnNumPags.setCellValueFactory(new PropertyValueFactory<>("NumPags"));
		columnNumPags.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		columnNumPags.setOnEditCommit(event -> {
			Articulo product = event.getRowValue();
			product.setNumPags(event.getNewValue());
			articuloDAO.update(product);
		});
		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
		columnTitulo.setCellFactory(TextFieldTableCell.forTableColumn());
		columnTitulo.setOnEditCommit(event -> {
			Articulo product = event.getRowValue();
			product.setTitulo(event.getNewValue());
			articuloDAO.update(product);
		});
		tableInventario.getItems().setAll(productos);
		tableInventario.setEditable(true);
		initialize();
	}
}