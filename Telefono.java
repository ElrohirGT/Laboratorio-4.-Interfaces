import java.util.ArrayList;

public class Telefono implements ITelefono {
    ArrayList<IContacto> _contactos;

    private String _nombreTelefono;
    private String _numeroTelefono;
    private String _duenioTelefono;

    public Telefono(String nombreTelefono, String numeroTelefono,
            String duenioTelefono, ArrayList<IContacto> contactos) {
        _contactos = contactos;
        _nombreTelefono = nombreTelefono;
        _numeroTelefono = numeroTelefono;
        _duenioTelefono = duenioTelefono;
    }

    /**
     * @return String
     */
    // Implements ITelefono
    public String obtenerNombre() {
        return _nombreTelefono;
    }

    /**
     * @return String
     */
    public String obtenerNumero() {
        return _numeroTelefono;
    }

    /**
     * @return String
     */
    public String obtenerDueño() {
        return _duenioTelefono;
    }

    public ArrayList<IContacto> obtenerContactos() {
        return _contactos;
    }

}
