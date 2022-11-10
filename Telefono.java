import java.util.ArrayList;
public class Telefono implements ITelefono {
    ArrayList<IContacto> _contactos;

    private String _nombreTelefono;
    private String _numeroTelefono;
    private String _duenioTelefono;


    public Telefono(ArrayList<IContacto> _contactos, String _nombreTelefono, String _numeroTelefono, String _duenioTelefono) {
        this._contactos = _contactos;
        this._nombreTelefono = _nombreTelefono;
        this._numeroTelefono = _numeroTelefono;
        this._duenioTelefono = _duenioTelefono;
    }

    /** 
     * @return String
     */
    //Implements ITelefono
    public String obtenerNombre(){
        return _nombreTelefono;
    }
    
    
    /** 
     * @return String
     */
    public String obtenerNumero(){
        return _numeroTelefono;
    }
    
    
    /** 
     * @return String
     */
    public String obtenerDueño(){
        return _duenioTelefono;
    }


    public ArrayList<IContacto> obtenerContactos(){
        return _contactos;
    }


}
