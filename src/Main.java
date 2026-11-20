import java.util.Scanner;

// Clase principal con el entorno de ejecución y menú de consola para el MinHeap
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinHeap monticulo = new MinHeap();

        System.out.println("=== Practica 3: Monticulo Minimo (Min-Heap) ===");

        // Llamada al menú principal de consola
        menu(monticulo, scanner);

        scanner.close();
    }

    // Menú de consola para interactuar con todas las operaciones del MinHeap
    public static void menu(MinHeap monticulo, Scanner scanner) {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Consultar minimo (peek)");
            System.out.println("3. Eliminar minimo (eliminarMin)");
            System.out.println("4. Construir monticulo desde un arreglo (heapify)");
            System.out.println("5. Mostrar monticulo");
            System.out.println("6. Salir");

            opcion = leerEntero(scanner, "Elija una opcion: ");

            switch (opcion) {
                case 1:
                    int valor = leerEntero(scanner, "Digite el numero entero a insertar: ");
                    monticulo.insertar(valor);
                    System.out.println("Elemento " + valor + " insertado correctamente.");
                    break;

                case 2:
                    if (!monticulo.estaVacio()) {
                        System.out.println("El elemento minimo (cima) es: " + monticulo.peek());
                    } else {
                        monticulo.peek();
                    }
                    break;

                case 3:
                    if (!monticulo.estaVacio()) {
                        int eliminado = monticulo.eliminarMin();
                        System.out.println("Se elimino el minimo: " + eliminado);
                    } else {
                        monticulo.eliminarMin();
                    }
                    break;

                case 4:
                    ejecutarHeapify(monticulo, scanner);
                    break;

                case 5:
                    monticulo.mostrarMonticulo();
                    break;

                case 6:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }
    }

    // Solicita al usuario un arreglo de enteros y ejecuta el método heapify
    private static void ejecutarHeapify(MinHeap monticulo, Scanner scanner) {
        int cantidad = leerEntero(scanner, "Cuantos elementos tendra el arreglo?: ");
        while (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            cantidad = leerEntero(scanner, "Cuantos elementos tendra el arreglo?: ");
        }

        int[] arreglo = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            arreglo[i] = leerEntero(scanner, "Digite el elemento [" + i + "]: ");
        }

        monticulo.heapify(arreglo);
        System.out.println("Arreglo convertido en monticulo minimo correctamente.");
        monticulo.mostrarMonticulo();
    }

    // Lee un numero entero desde consola evitando errores si el usuario escribe texto
    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe digitar un numero entero.");
            }
        }
    }
}