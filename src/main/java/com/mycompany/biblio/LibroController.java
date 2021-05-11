/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biblio;

import com.mycompany.biblio.modelos.Usuario;
import com.mycompany.biblio.DAO.LibroDAO;
import com.mycompany.biblio.modelos.Genero;
import com.mycompany.biblio.modelos.Libros;
import com.mycompany.biblio.modelos.Usuario;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author sergi
 */
public class LibroController {

    private static Usuario user;
    private static LibroDAO ldao;
    private Libros librosSel;
    private ObservableList<Genero> generoz = FXCollections.observableArrayList(Arrays.asList(Genero.values()));
    @FXML
    private ComboBox genero;
    @FXML
    private TextField id;
    @FXML
    private TextField nombre;
    @FXML
    private TextField editorial;
    @FXML
    private TextField autor;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField precio;
    @FXML
    private TextField saga;
    @FXML
    private ListView listaLibro;

    @FXML
    private void addl() {
        try {
//    private int id;
//    private String nombre;
//    private String editorial;
//    private String autor;
//    private String genero;
//    private Date fecha;
//    private double precio;
//    private String saga;
// genero.getValue().toString()//
            ldao.insertarLibro(new Libros(Integer.parseInt(id.getText()), nombre.getText(), editorial.getText(), autor.getText(),
                    genero.getValue().toString(),
                    Date.valueOf(fecha.getValue()), Double.parseDouble(precio.getText()), saga.getText()));
            initLists();
        } catch (SQLException ex) {
            AlertsUtil.mostrarError("Error al modificar la ruta seleccionada. " + ex.getMessage());

        }

    }

    @FXML
    private void savel() {
        librosSel = (Libros) listaLibro.getSelectionModel().getSelectedItem();
        if (librosSel == null) {
            AlertsUtil.mostrarError("No se ha seleccionado ningun libro");
            return;
        }
        try {
//    private int id;
//    private String nombre;
//    private String editorial;
//    private String autor;
//    private String genero;
//    private Date fecha;
//    private double precio;
//    private String saga;
            ldao.modificarLibro(new Libros(Integer.parseInt(id.getText()), nombre.getText(), editorial.getText(), autor.getText(),
                    genero.getValue().toString(), Date.valueOf(fecha.getValue()), Double.parseDouble(precio.getText()), saga.getText()));
        } catch (SQLException ex) {
            AlertsUtil.mostrarError("Error al modificar el libro seleccionado");
        }

    }

    @FXML
    private void deletel() {
        librosSel = (Libros) listaLibro.getSelectionModel().getSelectedItem();
        if (librosSel == null) {
            AlertsUtil.mostrarError("No se ha seleccionado nigun libro");
            return;
        }
        try {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Eliminar Libro");
            confirmacion.setContentText("¿Estas seguro que quieres elminar el libro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                return;
            }
            ldao.deleteLibro(librosSel);
            initLists();

        } catch (SQLException ex) {
            AlertsUtil.mostrarError("Error al eliminar el libro seleccionado" + ex.getMessage());
        }

    }

    public LibroController(Usuario u) {
        user = u;
        conectarBD();

    }

    private static void conectarBD() {
        ldao = new LibroDAO();
        try {
            ldao.conectar();
        } catch (SQLException sqle) {
            AlertsUtil.mostrarError("Error al conectar con la base de datos" + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            AlertsUtil.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertsUtil.mostrarError("Error al cargar la configuración");
        }

    }

    private void cargarLibro(Libros libro) {

//    private int id;
//    private String nombre;
//    private String editorial;
//    private String autor;
//    private String genero;
//    private Date fecha;
//    private double precio;
//    private String saga;
        Integer.parseInt(id.getText());
        nombre.setText(libro.getNombre());
        editorial.setText(libro.getEditorial());
        autor.setText(libro.getAutor());
        genero.getValue().toString();
        Date.valueOf(fecha.getValue());
        Double.parseDouble(precio.getText());

    }

    private void limpiarLibro(Libros libro) {
        id.setId("");
        nombre.setText("");
        editorial.setText("");
        autor.setText("");
        genero.setValue(0);
        fecha.setValue(java.time.LocalDate.now());
        precio.setText("");
        saga.setText("");
    }

    public void initLists() {
        
        genero.setItems(generoz);
        listaLibro.getItems().clear();
        try {
            conectarBD();
            List<Libros> libro = ldao.listLibros();
            listaLibro.setItems(FXCollections.observableList(libro));

        } catch (SQLException sqle) {
            AlertsUtil.mostrarError("Error cargando los datos de la aplicación");
        }
    }

    @FXML
    public void disconnect() {
        try {
            ldao.desconectar();
            Platform.exit();
        } catch (SQLException ex) {
            AlertsUtil.mostrarError("Error desconexion");

        }
    }

}
