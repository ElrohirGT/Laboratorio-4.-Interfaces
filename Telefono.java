import java.util.ArrayList;
public class Telefono implements ITelefono {
    ArrayList<IContacto> _contactos;

    private String _nombreTelefono;
    private String _numeroTelefono;
    private String _duenioTelefono;

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
