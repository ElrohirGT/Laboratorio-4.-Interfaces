import java.util.ArrayList;

/**
 * IModoTelefono
 */
public interface IModoTelefono {

    /**
     * Intenta conectarse al teléfono dado.
     */
    boolean conectarTelefono(ITelefono telefono);

    /**
     * @return el teléfono conectado actualmente.
     *         Si no hay nada conectado retorna null.
     */
    ITelefono obtenerTelefonoConectado();

    /**
     * Se desconecta del teléfono dado.
     */
    void desconectarTelefono();

    /**
     * 
     * @return los contactos dados por el teléfono.
     */
    ArrayList<IContacto> obtenerContactos();
}