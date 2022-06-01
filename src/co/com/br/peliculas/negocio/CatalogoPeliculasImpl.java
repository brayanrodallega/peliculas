package co.com.br.peliculas.negocio;

import co.com.br.peliculas.datos.*;
import co.com.br.peliculas.domain.Pelicula;
import co.com.br.peliculas.excepciones.*;
import javax.swing.JOptionPane;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private final IAccesoDatos accesoDatos;

    public CatalogoPeliculasImpl() {
        accesoDatos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = accesoDatos.existe(NOMBRE_RECURSO);
            accesoDatos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo:\n" + ex.getMessage());
        }
    }

    @Override
    public void listarPelicula() {
        StringBuilder mensaje = new StringBuilder();
        try {
            var listaPeliculas = accesoDatos.listar(NOMBRE_RECURSO);
            for (Pelicula pelicula : listaPeliculas) {
                mensaje.append("Pelicula: " + pelicula.getNombre() + "\n");
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (AccesoDatosEx ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo:\n" + ex.getMessage());
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = accesoDatos.buscar(NOMBRE_RECURSO, buscar);
        } catch (LecturaDatosEx ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo\n" + ex.getMessage());
        }
        if (resultado != null) {
            JOptionPane.showMessageDialog(null, resultado);
        } else {
            JOptionPane.showMessageDialog(null, "La pelicula no fue encontrada!");
        }
    }

    @Override
    public void iniciarArchivo() {
        try {
            if (this.accesoDatos.existe(NOMBRE_RECURSO)) {
                this.accesoDatos.borrar(NOMBRE_RECURSO);
                this.accesoDatos.crear(NOMBRE_RECURSO);
            } else {
                this.accesoDatos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar catalogo de peliculas\n" + ex.getMessage());
        }
        finally{
            JOptionPane.showMessageDialog(null, "El archivo fue creado con exito!");
        }
    }
}
