package controller;

import java.sql.Connection;
import java.util.ArrayList;

import data.AdminConnection;
import data.ArticuloDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class InventarioController {

    @FXML
    private TableColumn<?, ?> columnAutor;

    @FXML
    private TableColumn<?, ?> columnAño;


    @FXML
    private TableColumn<?, ?> columnNumPags;

    @FXML
    private TableColumn<?, ?> columnTitulo;

    @FXML
    private TableView<?> tableInventario;
    
    private Connection connection = AdminConnection.getInstance().getConnection();
	private ArticuloDAO articuloDAO = new ArticuloDAO(connection);

	@FXML
	void initialize() {
				
	}

    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

}
