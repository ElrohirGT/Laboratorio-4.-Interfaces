import java.time.Duration;

public class Cancion implements ICancion {
    private String _nombreCancion;
    private Duration _duracionCancion;
    private String _generoCancion;
    private String _autorCancion;

    public Cancion(String nombreCancion, Duration duracionCancion, String generoCancion, String autorCancion) {
        _nombreCancion = nombreCancion;
        _duracionCancion = duracionCancion;
        _generoCancion = generoCancion;
        _autorCancion = autorCancion;
    }

    public String obtenerNombre() {
        return _nombreCancion;
    }

    public Duration obtenerDuracion() {
        return _duracionCancion;
    }

    public String obtenerGenero() {
        return _generoCancion;
    }

    public String obtenerAutor() {
        return _autorCancion;
    }

    @Override
    public String toString() {
        return _nombreCancion;
    }
}
