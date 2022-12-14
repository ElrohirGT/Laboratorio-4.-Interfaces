/**
 * IRadio
 */
public interface IRadio extends IModoRadio, IModoReproduccion, IModoTelefono, IModoProductividad {
    public final int DELTA_VOLUMEN = 1;

    /**
     * Enciende la radio.
     */
    void encender();

    /**
     * Apaga la radio.
     */
    void apagar();

    /**
     * @return verdadero si la radio está encendida, caso contrario falso.
     */
    boolean estaEncendida();

    /**
     * Aumenta el volumen de la radio de acuerdo a DELTA_VOLUMEN.
     */
    void aumentarVolumen();

    /**
     * Disminuye el volumen de la radio de acuerdo a DELTA_VOLUMEN.
     */
    void disminuirVolumen();

    /**
     * @return el volumen actual de la radio.
     */
    int obtenerVolumen();

    /**
     * Cambia el modo de la radio al modo dado.
     * 
     * @param nuevoModo El modo al que se quiere cambiar la radio.
     */
    void cambiarModo(ModosRadio nuevoModo);

    /**
     * Obtiene el actual de la radio
     */
    ModosRadio obtenerModo();
}