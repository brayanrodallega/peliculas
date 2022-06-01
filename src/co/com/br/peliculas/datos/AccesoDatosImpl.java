package co.com.br.peliculas.datos;

import co.com.br.peliculas.domain.Pelicula;
import co.com.br.peliculas.excepciones.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo){

        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombre) throws LecturaDatosEx{
        
        File archivo = new File(nombre);
        List<Pelicula> listapelicula = new ArrayList<>();
        try {
            var lector = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = lector.readLine();
            while (linea != null){
                Pelicula pelicula = new Pelicula(linea);
                listapelicula.add(pelicula);
                linea = lector.readLine();
            }
            lector.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar archivo: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar archivo: " + ex.getMessage());
        }
        return listapelicula;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx{

        File archivo = new File(nombreArchivo);
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, anexar));
            escritor.write(pelicula.getNombre() + "\n");
            escritor.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir en el archivo: " + ex.getMessage());
        }
        
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx{
        File archivo = new File(nombreArchivo);
        String resultado = null;
        var indice = 1;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = lector.readLine();
            while (linea != null){
                if(buscar != null && linea.equalsIgnoreCase(buscar)){
                    resultado = "Pelicula: " + linea + " encontrada en la posicion " + indice;
                    break;
                }
                linea = lector.readLine();
                indice++;
            }
            lector.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar archivo: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx{

        File archivo = new File(nombreArchivo);
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, false));
            escritor.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo: " + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo){

        File archivo = new File(nombreArchivo);
        if (archivo.exists()){
            archivo.delete();
        }
        else{
            JOptionPane.showMessageDialog(null, "El archivo no existe!");
        }

    }

}
