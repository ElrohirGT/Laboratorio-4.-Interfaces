import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * Programa
 */
public class Programa {
    static final String ANSI_RESET = "\u001B[0m";

    static final String ANSI_BLACK = "\u001B[30m";

    static final String ANSI_RED = "\u001B[31m";

    static final String ANSI_GREEN = "\u001B[32m";

    static final String ANSI_YELLOW = "\u001B[33m";

    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";
    static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    static final int DIVIDER_LENGTH = 32;
    static final int DIVIDER_HALF_LENGTH = DIVIDER_LENGTH / 2;
    static final String DIVIDER = new String(new char[DIVIDER_LENGTH]).replace("\0", "=");
    static final String SUB_DIVIDER = new String(new char[DIVIDER_LENGTH]).replace("\0", "-");

    public static void main(String[] args) {
        String username = formLabel("Por favor ingrese su nombre", ANSI_CYAN);
        consoleWrite("Bienvenido ");
        consoleWrite(username, ANSI_GREEN);
        consoleWriteLine("!");
        final Function<Duration, String> durationToString = d -> {
            long seconds = d.getSeconds();
            long absSeconds = Math.abs(seconds);
            String positive = String.format(
                    "%d:%02d:%02d",
                    absSeconds / 3600,
                    (absSeconds % 3600) / 60,
                    absSeconds % 60);
            return seconds < 0 ? "-" + positive : positive;
        };

        // TODO add playlists and emisoras
        IRadio radio = new Radio(null, null);
        animation("Cargando ", new ArrayList<>() {
            {
                add("|");
                add("/");
                add("—");
                add("\\");
            }
        }, 5000, 3, ANSI_YELLOW);

        while (true) {
            clearScreen();
            final var modoRadio = radio.obtenerModo();
            Consumer<IRadio> showHeader = r -> {
                consoleWriteLine("Lo sentimos, un error gravísimo ha ocurrido.", ANSI_RED);
            };
            final var menu = new LinkedHashMap<String, Consumer<IRadio>>();
            if (!radio.estaEncendida()) {
                showHeader = r -> consoleWriteLine("La radio se encuentra apagada!", ANSI_WHITE, ANSI_RED_BACKGROUND);
                menu.put("Encender", r -> {
                    radio.encender();
                    clearScreen();
                    animation("Cargando ", new ArrayList<>() {
                        {
                            add("|");
                            add("/");
                            add("—");
                            add("\\");
                        }
                    }, 5000, 3, ANSI_YELLOW);
                });
            } else {
                menu.put("Apagar", r -> {
                    radio.apagar();
                    animation("Apagando ", new ArrayList<>() {
                        {
                            add("|");
                            add("/");
                            add("—");
                            add("\\");
                        }
                    }, 1000, 3, ANSI_YELLOW);
                });
                menu.put("Cambiar Modo", r -> {
                    var modes = Arrays.asList(ModosRadio.values());
                    IntStream.range(0, modes.size()).forEach(i -> {
                        printSpaceSeparated(i + 1 + ")", modes.get(i), ANSI_YELLOW);
                    });
                    var index = formLabel("Seleccione un modo", ANSI_CYAN, s -> {
                        try {
                            var n = Integer.parseInt(s);
                            if (n < 1 || n > modes.size()) {
                                consoleWriteLine("La posición debe estar entre 1 y " + modes.size(), ANSI_RED);
                                return false;
                            }
                            return true;
                        } catch (Exception e) {
                            consoleWriteLine("Por favor ingrese un número válido!", ANSI_RED);
                            return false;
                        }
                    }, Integer::parseInt) - 1;

                    ModosRadio nuevoModo = modes.get(index);
                    r.cambiarModo(nuevoModo);
                });
                menu.put("Subir volumen", r -> r.aumentarVolumen());
                menu.put("Bajar volumen", r -> r.disminuirVolumen());
                switch (modoRadio) {
                    case RADIO:
                        showHeader = r -> {
                            final var estacion = r.obtenerEmisoraActual();
                            final var volumen = r.obtenerVolumen();
                            final var emisorasGuardadas = r.obtenerEmisorasGuardadas().size();
                            final var banda = r.obtenerBanda();

                            printSpaceSeparatedFor("Estación Actual:", String.format("%.2f %s", estacion, banda),
                                    ANSI_GREEN);
                            printSpaceSeparatedFor("Volumen:", volumen, ANSI_PURPLE);
                            printSpaceSeparatedFor("# Emisoras:", emisorasGuardadas, ANSI_CYAN);
                            consoleWriteLine(SUB_DIVIDER);
                        };
                        final var emisoraActual = radio.obtenerEmisoraActual();
                        final var banda = radio.obtenerBanda();
                        final Bandas bandaContraria = (banda == Bandas.AM) ? Bandas.FM : Bandas.AM;

                        menu.put("Cambiar a " + bandaContraria, r -> r.cambiarBanda(bandaContraria));

                        menu.put("Guardar Emisora", r -> {
                            if (emisoraActual == 0) {
                                consoleWriteLine("No se puede guardar la emisora 0.", ANSI_RED);
                                return;
                            }
                            r.guardarEmisoraActual();
                        });
                        menu.put("Subir Emisora", r -> r.subirEmisora());
                        menu.put("Bajar Emisora", r -> {
                            if (emisoraActual == 0) {
                                consoleWriteLine("No se pueden tener emisoras negativas.", ANSI_RED);
                                return;
                            }
                            r.bajarEmisora();
                        });
                        menu.put("Cargar Emisora", r -> {
                            ArrayList<Float> emisorasGuardadas = r.obtenerEmisorasGuardadas();
                            int stationsCount = emisorasGuardadas.size();
                            if (stationsCount == 0) {
                                consoleWriteLine("No se han guardado emisoras todavía!", ANSI_RED);
                                return;
                            }

                            clearScreen();
                            consoleWriteLine("Emisoras guardadas", ANSI_BLACK, ANSI_CYAN_BACKGROUND);
                            IntStream.range(0, stationsCount).forEach(i -> {
                                printSpaceSeparatedFor(i + 1 + ")", String.format("%.2f", emisorasGuardadas.get(i)),
                                        ANSI_YELLOW);
                            });

                            int position = formLabel("Ingrese la posición que desea cargar", ANSI_CYAN, s -> {
                                try {
                                    var n = Integer.parseInt(s);
                                    if (n < 1 || n > stationsCount) {
                                        consoleWriteLine("La posición debe estar entre 1 y " + stationsCount, ANSI_RED);
                                        return false;
                                    }
                                    return true;
                                } catch (Exception e) {
                                    consoleWriteLine("Por favor ingrese un número válido!", ANSI_RED);
                                    return false;
                                }
                            }, Integer::parseInt) - 1;
                            r.cargarEmisora(position);
                        });
                        break;
                    case REPRODUCCION:
                        showHeader = r -> {
                            final ICancion cancion = r.obtenerCancion();

                            if (cancion == null) {
                                consoleWriteLine("Por favor seleccione una playlist!", ANSI_RED);
                                return;
                            }

                            consoleWrite(cancion.obtenerNombre(), ANSI_CYAN);
                            consoleWrite(" - ");
                            consoleWriteLine(cancion.obtenerAutor(), ANSI_PURPLE);

                            printSpaceSeparatedFor("Género:", cancion.obtenerGenero(), ANSI_GREEN);
                            consoleWriteLine(
                                    String.format("Duración: %s", durationToString.apply(cancion.obtenerDuracion())));
                        };

                        menu.put("Seleccionar Playlist", r -> {
                            final var listas = r.obtenerPlaylists();
                            consoleWriteLine("Playlists disponibles", ANSI_WHITE, ANSI_PURPLE_BACKGROUND);
                            IntStream.range(0, listas.size()).forEach(i -> {
                                printSpaceSeparated(i + 1 + ")", listas.get(i), ANSI_CYAN);
                            });

                            int option = formLabel("Seleccione una lista", ANSI_CYAN, s -> {
                                try {
                                    var n = Integer.parseInt(s);
                                    if (n < 1 || n > listas.size()) {
                                        consoleWriteLine("La posición debe estar entre 1 y " + listas.size(), ANSI_RED);
                                        return false;
                                    }
                                    return true;
                                } catch (Exception e) {
                                    consoleWriteLine("Por favor ingrese un número válido!", ANSI_RED);
                                    return false;
                                }
                            }, Integer::parseInt) - 1;

                            r.seleccionarPlaylist(option);
                        });

                        menu.put("Siguiente Canción", r -> {
                            r.siguienteCancion();
                        });
                        menu.put("Canción Anterior", r -> {
                            r.cancionAnterior();
                        });
                        break;
                    case TELEFONO:
                        showHeader = r -> {
                            final var contactos = r.obtenerContactos();
                            IntStream.range(0, contactos.size()).forEach(i -> {
                                printSpaceSeparated(i + 1 + ")",
                                        contactos.get(i).obtenerNombre() + contactos.get(i).obtenerNumero(),
                                        ANSI_YELLOW);
                            });
                        };
                        break;
                    default:
                        break;
                }
            }

            menu.put("Salir", r -> r.apagar());

            showHeader.accept(radio);
            String[] labels = menu.keySet().toArray(String[]::new);
            IntStream.range(0, labels.length).forEach(i -> {
                printSpaceSeparated(i + 1 + ")", labels[i], ANSI_YELLOW);
            });
            int selected = formLabel("Seleccione una opción", ANSI_CYAN, s -> {
                try {
                    var n = Integer.parseInt(s);
                    if (n < 1 || n > labels.length) {
                        consoleWriteLine("La opción debe estar entre 1 y " + labels.length, ANSI_RED);
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    consoleWriteLine("Por favor ingrese un número válido!", ANSI_RED);
                    return false;
                }
            }, Integer::parseInt) - 1;

            if (labels[selected].equals("Salir")) {
                break;
            }

            menu.get(labels[selected]).accept(radio);

            consoleWriteLine("Presione enter para continuar...");
            System.console().readLine();
        }
    }

    private static void animation(Object prefix, ArrayList<Object> frames, int msDuration, int framesPerSecond,
            String color) {
        String format = "%s%s%s";
        var formattedFrames = frames.stream().map(s -> (Object) String.format(format, color, s, ANSI_RESET)).toList();
        animation(prefix, formattedFrames, msDuration, framesPerSecond);
    }

    public static void animation(Object prefix, Iterable<Object> frames, int msDuration, int framesPerSecond) {
        var start = LocalTime.now();
        consoleWriteLine(prefix.toString());
        while (true) {
            for (Object frame : frames) {
                consoleWrite("\033[1A");// Move Up
                consoleWrite("\033[2K");// Clear line
                consoleWriteLine(prefix.toString() + frame);
                try {
                    Thread.sleep(1000 / framesPerSecond);
                } catch (Exception e) {
                    consoleWriteLine(e.toString(), ANSI_RED);
                }
                if ((LocalTime.now().getSecond() - start.getSecond()) * 1000 > msDuration) {
                    return;
                }
            }
        }
    }

    public static <T> void showInTable(Iterable<T> list,
            Function<T, LinkedHashMap<String, String>> convertToMap) {

        for (T itemT : list) {
            LinkedHashMap<String, String> rows = convertToMap.apply(itemT);
            showInTable(rows);
        }
    }

    public static void showInTable(LinkedHashMap<String, String> map) {
        for (String rowName : map.keySet()) {
            consoleWrite(rowName + "\t", ANSI_CYAN);
            consoleWriteLine(map.get(rowName));
        }
        consoleWriteLine(SUB_DIVIDER);
    }

    public static void menuItem(Object identifier, Object option, String color, boolean isActive) {
        if (isActive) {
            printSpaceSeparated(identifier, option, color);
        }
    }

    public static void menuItem(Object identifier, Object option, String color) {
        printSpaceSeparated(identifier, option, color);
    }

    public static String formLabel(Object label, String color) {
        consoleWrite(label.toString() + ": ");
        return waitForInputColored(color);
    }

    public static String formLabel(Object label, String color, Function<String, Boolean> check) {
        String input = "";
        do {
            input = formLabel(label, color);
        } while (!check.apply(input));

        return input;
    }

    public static <T> T formLabel(Object label, String color, Function<String, Boolean> check,
            Function<String, T> conv) {
        String input = formLabel(label, color, check);
        return conv.apply(input);
    }

    public static String waitForInputColored(String color) {
        System.out.print(color);
        String s = System.console().readLine();
        System.out.print(ANSI_RESET);
        return s;
    }

    public static void printSpaceSeparated(Object identifier, Object option, String color) {
        consoleWrite(identifier + " ", color);
        consoleWriteLine(option.toString());
    }

    public static void printSpaceSeparatedFor(Object identifier, Object option, String color) {
        consoleWrite(identifier + " ");
        consoleWriteLine(option.toString(), color);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void consoleWriteLine(String message) {
        consoleWriteLine(message, "", "");
    }

    public static void consoleWriteLine(String message, String foreground) {
        consoleWriteLine(message, foreground, "");
    }

    public static void consoleWriteLine(String message, String foreground, String background) {
        System.out.println(background + foreground + message + ANSI_RESET);
    }

    public static void consoleWrite(Object message) {
        consoleWrite(message, "", "");
    }

    public static void consoleWrite(Object message, String foreground) {
        consoleWrite(message, foreground, "");
    }

    public static void consoleWrite(Object message, String foreground, String background) {
        System.out.print(background + foreground + message + ANSI_RESET);
    }
}