import java.util.ArrayList;

public class Radio implements IRadio {

    private ArrayList<ArrayList<ICancion>> _playlists = new ArrayList<>();
    private ArrayList<Float> _emisoras = new ArrayList<>();
    private Bandas _banda = Bandas.AM;
    private float _emisoraActual = 0;
    private boolean _encendida = true;
    private int _volumen = 0;
    private ModosRadio _modo = ModosRadio.RADIO;


    //Iradio
    private ArrayList<ICancion> _playListSeleccionada = null;
    private ICancion _nombreCancion = null;
    private int _posicionCancion = 0;
    protected Radio(ArrayList<ArrayList<ICancion>> playlists, ArrayList<Float> emisorasGuardadas) {
        // Hacer algo con las playlists y emisoras que "el usuario ya guardó"
        if (playlists != null) {
            _playlists = playlists;
        }
        ;

        if (emisorasGuardadas != null) {
            _emisoras = emisorasGuardadas;
        }
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
    //--------Parte de IModo Reproduccion--------
    
    @Override
    public ArrayList<ArrayList<ICancion>> obtenerPlaylists() {
        // TODO Auto-generated method stub
        //Ya
        return _playlists;
    }

    @Override
    public void seleccionarPlaylist(int posicion) {

        _playListSeleccionada = _playlists.get(posicion);
        _nombreCancion = _playListSeleccionada.get(0);
        //_nombreCancion = _playListSeleccionada.get(_posicionCancion);


    }

    @Override
    public void siguienteCancion() {

        // TODO Auto-generated method stub

        int indiceAnterior = _playListSeleccionada.indexOf(_nombreCancion);
        int tamanioTotalPosiciones = _playListSeleccionada.size()-1;
        int indcide_siguinte = indiceAnterior;
        _posicionCancion = indiceAnterior;

        if(indiceAnterior<=tamanioTotalPosiciones){
            indcide_siguinte = indiceAnterior+1;

            _nombreCancion = _playListSeleccionada.get(indcide_siguinte);
        }
        
        if(indiceAnterior>=tamanioTotalPosiciones){
            indcide_siguinte = 0;
            _nombreCancion = _playListSeleccionada.get(indcide_siguinte);
        }
        
     

    }

    @Override
    public void cancionAnterior() {
        // TODO Auto-generated method stub

        int indiceAnterior = _playListSeleccionada.indexOf(_nombreCancion);
        int tamanioTotalPosiciones = _playListSeleccionada.size()-1;
        int indcide_siguinte = indiceAnterior;

        if(indiceAnterior>0){
            indcide_siguinte = indiceAnterior-1;

            _nombreCancion = _playListSeleccionada.get(indcide_siguinte);
        }
        
        if(indiceAnterior==0){
            indcide_siguinte = tamanioTotalPosiciones;
            _nombreCancion = _playListSeleccionada.get(indcide_siguinte);
        }

    }

    //-----------------------------------------
    @Override
    public ICancion obtenerCancion() {
        // TODO Auto-generated method stub

        return _nombreCancion;
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
        _volumen = Math.min(100, _volumen);
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

    @Override
    public int obtenerVolumen() {
        return _volumen;
    }
}
