package cpjlaboratoriofinal;

import co.com.br.peliculas.negocio.*;
import javax.swing.JOptionPane;

public class CPJLaboratorioFinal {

    public static void main(String[] args) {

        ICatalogoPeliculas catalogoPeliculas = new CatalogoPeliculasImpl();

        int opcion = 0;

        do {
            
            opcion = 0;
            
            String opciones = "*** M E N U ***\n\n"
                    + "1.- Iniciar catalogo peliculas\n"
                    + "2.- Agregar pelicula\n"
                    + "3.- Listar Peliculas\n"
                    + "4.- Buscar Pelicula\n"
                    + "5.- Salir\n\n"
                    + "Opcion:\n";

            opcion = Integer.parseInt(JOptionPane.showInputDialog(opciones));

            switch (opcion) {

                case 1:
                    catalogoPeliculas.iniciarArchivo();
                    break;
                case 2:
                    catalogoPeliculas.agregarPelicula(JOptionPane.showInputDialog("Escriba el nombre de la pelicula:"));
                    break;
                case 3:
                    catalogoPeliculas.listarPelicula();
                    break;
                case 4:
                    catalogoPeliculas.buscarPelicula(JOptionPane.showInputDialog("Digite la pelicula ha consultar:"));
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion incorrecta!");
            }

        } while (opcion != 5);
    }
}
