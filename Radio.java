import java.util.ArrayList;

public class Radio implements IRadio {

    ArrayList<ArrayList<ICancion>> _playlists;
    ArrayList<Float> _emisoras;
    private Bandas _banda;
    private float _emisoraActual = 0;
    private boolean _encendida = true;
    private int _volumen = 0;
    private ModosRadio _modo = ModosRadio.RADIO;

    protected Radio(ArrayList<ArrayList<ICancion>> playlists, ArrayList<Float> emisorasGuardadas) {
        // Hacer algo con las playlists y emisoras que "el usuario ya guardó"
        _playlists = playlists;
        _emisoras = emisorasGuardadas;
    }

    @Override
    public void cambiarBanda(Bandas nuevaBanda) {
        _banda = nuevaBanda;
    }

    @Override
    public Bandas obtenerBanda() {
        return _banda;
    }

    @Override
    public void subirEmisora() {
        _emisoraActual += DELTA_EMISORA;
    }

    @Override
    public void bajarEmisora() {
        _emisoraActual -= DELTA_EMISORA;
        _emisoraActual = Math.max(0, _emisoraActual);
    }

    @Override
    public float obtenerEmisoraActual() {
        return _emisoraActual;
    }

    @Override
    public ArrayList<Float> obtenerEmisorasGuardadas() {
        return _emisoras;
    }

    @Override
    public int obtenerPosicionEmisora(float emisora) {
        return _emisoras.indexOf(emisora);
    }

    @Override
    public void guardarEmisoraActual() {
        boolean hayEspacio = _emisoras.size() != MAX_EMISORAS_GUARDADAS;
        boolean noSeRepite = _emisoras.indexOf(_emisoraActual) == -1;

        if (hayEspacio && noSeRepite) {
            _emisoras.add(_emisoraActual);
        }
    }

    @Override
    public void cargarEmisora(int posicion) {
        Float emisora = _emisoras.get(posicion);
        if (emisora != null) {
            _emisoraActual = emisora;
        }
    }

    @Override
    public ArrayList<ArrayList<ICancion>> obtenerPlaylists() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void seleccionarPlaylist(int posicion) {
        // TODO Auto-generated method stub

    }

    @Override
    public void siguienteCancion() {
        // TODO Auto-generated method stub

    }

    @Override
    public void cancionAnterior() {
        // TODO Auto-generated method stub

    }

    @Override
    public ICancion obtenerCancion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean conectarTelefono(ITelefono telefono) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void desconectarTelefono() {
        // TODO Auto-generated method stub

    }

    @Override
    public ArrayList<IContacto> obtenerContactos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void encender() {
        _encendida = true;
    }

    @Override
    public void apagar() {
        _encendida = false;
    }

    @Override
    public void aumentarVolumen() {
        _volumen += DELTA_VOLUMEN;
    }

    @Override
    public void disminuirVolumen() {
        _volumen -= DELTA_VOLUMEN;
        _volumen = Math.max(0, _volumen);
    }

    @Override
    public void cambiarModo(ModosRadio nuevoModo) {
        _modo = nuevoModo;
    }

    @Override
    public ModosRadio obtenerModo() {
        return _modo;
    }

    @Override
    public boolean estaEncendida() {
        return _encendida;
    }
}
