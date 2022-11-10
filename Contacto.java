public class Contacto implements IContacto {
    private String _nombreContacto;
    private String _numeroContacto;


    public Contacto(String _nombreContacto, String _numeroContacto) {
        this._nombreContacto = _nombreContacto;
        this._numeroContacto = _numeroContacto;
    }

    public String obtenerNombre(){
        return _nombreContacto;
    }

    public String obtenerNumero(){
        return _numeroContacto;
    }

}
