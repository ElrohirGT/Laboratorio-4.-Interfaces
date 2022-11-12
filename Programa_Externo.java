import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

//Universidad del Valle de Guatemala
//Andre Yatmian Jo Mai 22199
//Esteban Meza 22252

//Programa parecido a MAX solo que es mejor porque se llama Tienda latinoamreicana 
//Amazon 2.0: Por lo tanto hay una categoria con sus respectivos productos y además de eso se pide en linea. 

public class Programa_Externo {
    public static void main(String[] args) {
        int opcion;
        int opciont;
        int posicion;
        String stringopcion;
        float opcionfloat;
        boolean opcionbol = true;
        Scanner teclado = new Scanner(System.in);
        final ArrayList<ArrayList<ICancion>> playlists = new ArrayList<>() {
            {
                add(new ArrayList<>() {
                    {
                        add(new Cancion("The show must go on", Duration.ofMinutes(4).plusSeconds(31), "Rock", "Queen"));
                        add(new Cancion("Nothing else matters", Duration.ofMinutes(6).plusSeconds(28), "Rock",
                                "Metallica"));
                        add(new Cancion("Thunderstruck", Duration.ofMinutes(4).plusSeconds(52), "Rcok", "AC/DC"));
                        add(new Cancion("Losing my Religion", Duration.ofMinutes(4).plusSeconds(28), "Rock", "R.E.M."));
                        add(new Cancion("I was made for lovin' you", Duration.ofMinutes(4).plusSeconds(31), "Rock",
                                "KISS"));
                        add(new Cancion("Zombie", Duration.ofMinutes(5).plusSeconds(6), "Rock", "The Cranberries"));
                    }
                });
                add(new ArrayList<>() {
                    {
                        add(new Cancion("Paranoid", Duration.ofMinutes(2).plusSeconds(48), "Rock", "Black Sabbath"));
                        add(new Cancion("Californication", Duration.ofMinutes(5).plusSeconds(29), "Rock",
                                "Red Hot Chili Peppers"));
                        add(new Cancion("La Cancion", Duration.ofMinutes(4).plusSeconds(10), "Reguetón", "Bad Bunny"));
                        add(new Cancion("House, Life & Soul", Duration.ofMinutes(2).plusSeconds(44), "Electrónica",
                                "D-Sides"));
                        add(new Cancion("Dance Alone", Duration.ofMinutes(2).plusSeconds(5), "Electrónica", "Bsno"));
                    }
                });
            }
        };
        final ArrayList<Float> emisoras = null;
        Radio radio = new Radio(playlists, emisoras);
        Telefono telefono = new Telefono("Redmi Note 8", "5524-2256", "Flavio Galán", new ArrayList<>() {
            {
                add(new Contacto("Brandon", "3357-0173"));
                add(new Contacto("Nicolle", "4517-6476"));
            }
        });

        System.out.println("1. Encender Radio");
        System.out.println("2. Apagar Radio");

        boolean estado = radio.estaEncendida();

        while (estado == true) {
            System.out.println("1. Subir Volumen");
            System.out.println("2. Bajar Volumen");
            System.out.println("3. Cambiar modo");
            System.out.println("4. Apagar Radio");
            System.out.println("Que opcion desea realizar");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: {
                    System.out.println("1. Subir Volumen");
                    radio.aumentarVolumen();
                    break;

                }
                case 2: {
                    System.out.println("3. Bajar Volumen");
                    radio.disminuirVolumen();
                    break;

                }

                case 3: {
                    System.out.println("3. Cambiar modo\n");

                    System.out.println("1. Radio");
                    System.out.println("2. Reproducción");
                    System.out.println("3. Telefono");
                    System.out.println("4. Productividad");
                    System.out.println("Que opcion desea realizar");
                    opciont = teclado.nextInt();
                    switch (opciont) {
                        case 1: { // radio
                            // ModoRadio modoradio = new ModoRadio();
                            System.out.println("\n1. Cambiar modo AM o FM");
                            System.out.println("2. Cambiar Emisoras");
                            System.out.println("3. Guardar emisora");
                            System.out.println("4. Guardar emisora");
                            System.out.println("Que opcion desea realizar?");
                            opciont = teclado.nextInt();

                            switch (opciont) {
                                case 1: {
                                    radio.obtenerBanda();
                                    System.out.println("Existe FM y AM que banda desea cambiar\n");
                                    System.out.println("1.AM");
                                    System.out.println("2.FM");
                                    opciont = teclado.nextInt();
                                    switch (opciont) {
                                        case 1: {
                                            radio.cambiarBanda(Bandas.AM);
                                            System.out.println("Se ha cambiado AM");
                                            break;

                                        }

                                        case 2: {
                                            radio.cambiarBanda(Bandas.FM);
                                            System.out.println("Se ha cambiado FM");
                                            break;

                                        }
                                        default: {
                                            System.out.println("No se ha cambiado las emisoras");
                                            break;
                                        }

                                    }
                                    break;

                                }
                                case 2: {
                                    System.out.println("Se esta cambiando las emisoras");
                                    while (opcionbol == true) {
                                        System.out.println("1.Subir");
                                        System.out.println("2.Bajar");
                                        System.out.println("3.salir");
                                        System.out.println("Que opcion desea realizar?");
                                        opciont = teclado.nextInt();
                                        teclado.nextLine();

                                        switch (opciont) {
                                            case 1: {
                                                System.out.println("Subiendo Frecuencia");
                                                radio.subirEmisora();
                                                System.out.println(radio.obtenerEmisoraActual());
                                                break;

                                            }
                                            case 2: {
                                                System.out.println("Bajando frecuencia");
                                                radio.bajarEmisora();
                                                System.out.println(radio.obtenerEmisoraActual());
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Se ha salido de cambio de emisoras");
                                                opcionbol = false;
                                                break;

                                            }
                                        }

                                    }

                                }
                                case 3: {
                                    System.out.println("Guardando emisora: ");
                                    radio.obtenerEmisoraActual();
                                    radio.obtenerEmisorasGuardadas().add(radio.obtenerEmisoraActual());
                                    break;

                                }
                                case 4: {
                                    System.out.println("Que emisora desea cargar de sus emisoras guardadas? ");
                                    opcionfloat = teclado.nextFloat();
                                    teclado.nextLine();
                                    radio.cargarEmisora(radio.obtenerPosicionEmisora(opcionfloat));
                                    break;

                                }

                            }

                            break;

                        }

                        case 2: {// Reproduccion

                            System.out.println("1.Seleccionar lista de reproduccion");
                            System.out.println("2.Cambiar cancion");
                            System.out.println("3.Escuchar cancion");
                            System.out.println("4.Salir");
                            System.out.println("Que opcion desea realizar");
                            opcion = teclado.nextInt();
                            switch (opcion) {
                                case 1: {
                                    System.out.println(
                                            "Hay " + radio.obtenerPlaylists().size() + " playlists");
                                    System.out.println("Que numero de playlist desea usar?");

                                    posicion = teclado.nextInt();
                                    radio.seleccionarPlaylist(posicion);
                                    System.out.println("Se esta reproduciendo actualmente.");

                                    break;
                                }

                                case 2: {
                                    System.out.println("1.Siguiente cancion");
                                    System.out.println("2.Cancion anterior");
                                    System.out.println("3.Salir");
                                    opcion = teclado.nextInt();
                                    switch (opcion) {
                                        case 1: {
                                            System.out.println("Se reproduce la siguiente cancion");
                                            radio.siguienteCancion();

                                        }

                                        case 2: {
                                            System.out.println("Se reproduce la cancion anterior");
                                            radio.cancionAnterior();

                                        }
                                        default: {
                                            System.out.println("Se ha salido");
                                            break;
                                        }
                                    }

                                    break;
                                }

                                case 3: {
                                    System.out.println("Reproduciendo");
                                    System.out.println(radio.obtenerCancion().obtenerNombre()
                                            + radio.obtenerCancion().obtenerAutor());
                                    System.out.println(radio.obtenerCancion().obtenerDuracion());
                                    break;
                                }

                                case 4: {
                                    System.out.println("Saliendo...");

                                    break;
                                }
                                default: {
                                    System.out.println("No se ingreso una opcion valida regresando");
                                }
                            }

                            break;

                        }

                        case 3: {// Telefono
                            System.out.println("1.Conectar telefono");
                            System.out.println("2.Mostrar contactos");
                            System.out.println("3.Llamar contactos");
                            System.out.println("4.Finalizar llamada");
                            System.out.println("5.Llamada en espera");
                            System.out.println("6.Salir");
                            opcion = teclado.nextInt();
                            switch (opcion) {

                                case 1: {
                                    System.out.println("Conectando telefono...");
                                    radio.conectarTelefono(telefono);
                                    telefono.obtenerNombre();
                                    telefono.obtenerDueño();
                                    telefono.obtenerNumero();

                                    break;

                                }
                                case 2: {
                                    System.out.println("Mostrando contactos..");
                                    for (int i = 0; i <= telefono.obtenerContactos().size(); i++) {

                                        System.out.println(
                                                (i + 1) + ". " + telefono.obtenerContactos().get(i).obtenerNombre());
                                        System.out.println(telefono.obtenerContactos().get(i).obtenerNumero());
                                        System.out.println("\n");

                                    }

                                    break;

                                }
                                case 3: {

                                    for (int i = 0; i <= telefono.obtenerContactos().size(); i++) {

                                        System.out.println(
                                                (i + 1) + ". " + telefono.obtenerContactos().get(i).obtenerNombre());
                                        System.out.println(telefono.obtenerContactos().get(i).obtenerNumero());
                                        System.out.println("\n");

                                    }

                                    System.out.println("Que numero desea llamar?");
                                    stringopcion = teclado.nextLine();
                                    System.out.println("Llamando a " + stringopcion);

                                    break;
                                }
                                case 4: {
                                    System.out.println("Finalizando la llamada...");
                                    break;

                                }
                                case 5: {
                                    System.out.println("LLamada en espera....");
                                    break;

                                }
                                case 6: {
                                    radio.desconectarTelefono();
                                    System.out.println("Saliendo...");
                                    break;
                                }
                            }

                            break;
                        }

                        case 4: {// Productividad

                            System.out
                                    .println("El pronostico del dia de hoy es..." + radio.obtenerPronóstico());

                            break;

                        }

                        default: {
                            System.out.println("No se ha ingresado una opcion valida");

                            break;
                        }

                    }

                    break;

                }
                case 4: {
                    System.out.println("Se ha apagado la radio");
                    estado = false;
                    break;

                }
                default: {
                    System.out.println("Se ha apagado la radio porque no hay opcion valida");
                    estado = false;
                    break;
                }
            }

        }

    }
}
