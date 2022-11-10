import java.time.Duration;
public class Cancion implements ICancion{
    private String _nombreCancion;
    private Duration _duracionCancion;
    private String _generoCancion;
    private String _autorCancion;



    public Cancion(String _nombreCancion, Duration _duracionCancion, String _generoCancion, String _autorCancion) {
        this._nombreCancion = _nombreCancion;
        this._duracionCancion = _duracionCancion;
        this._generoCancion = _generoCancion;
        this._autorCancion = _autorCancion;
    }

    public String obtenerNombre(){
        return _nombreCancion;
    }

    public Duration obtenerDuracion(){
        return _duracionCancion;
    }

    public String obtenerGenero(){
        return _generoCancion;
    }

    public String obtenerAutor(){
        return _autorCancion;
    }

}
