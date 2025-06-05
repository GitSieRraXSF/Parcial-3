package data;

import oracle.jdbc.OracleTypes; // Asegúrate de tener esta importación
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;
import javafx.scene.control.Alert;
import model.Articulo;

public class ArticuloDAO {
	private Connection connection;

	public ArticuloDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Articulo> fetch() {
		ArrayList<Articulo> productos = new ArrayList<>();
		//String sequel = "SELECT * FROM PROGRAMMINGII.Producto";
		String sql = "{? = call FetchArticulos()}";
		try (CallableStatement cs = connection.prepareCall(sql)) {
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			try (ResultSet rs = (ResultSet) cs.getObject(1)){
				while (rs.next()) {
					String doi = rs.getString("Doi");
					String titulo = rs.getString("Titulo");
					String autor = rs.getString("autor");
					int annio = rs.getInt("Año");
					int numPags = rs.getInt("NumPaginas");
					boolean estado = rs.getBoolean("Estado");
					Articulo producto = new Articulo(doi, titulo, autor, annio, numPags, estado);
					productos.add(producto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
		return productos;
	}
	
	public void update(Articulo articulo) {
		String sql = "{call = UpdateArticulo(?, ?, ?, ?, ?, ?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setString(1, articulo.getDoi());
			stmt.setString(2, articulo.getTitulo());
			stmt.setString(3, articulo.getAutor());
			stmt.setInt(4, articulo.getAño());
			stmt.setInt(5, articulo.getNumPags());
			stmt.setBoolean(6, articulo.isEstado());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
	}
	
	public void delete(String Doi) {
		String sql = "{call DeleteArticulo(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setString(1, Doi);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
	}
}