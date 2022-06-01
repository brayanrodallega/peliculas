package co.com.br.peliculas.negocio;

public interface ICatalogoPeliculas {
    
    String NOMBRE_RECURSO = "Peliculas.txt";
    
    public void agregarPelicula(String nombrePelicula);
    
    public void listarPelicula();
    
    public void buscarPelicula(String buscar);
    
    public void iniciarArchivo();
}
