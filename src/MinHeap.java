import java.util.ArrayList;

// Clase que implementa un montículo mínimo (Min-Heap) usando un ArrayList
public class MinHeap {

    // Atributos
    private ArrayList<Integer> monticulo;

    // Métodos
    // Constructor
    public MinHeap() {
        monticulo = new ArrayList<>();
    }

    // Operaciones
    public boolean estaVacio() {
        return monticulo.isEmpty();
    }

    private int getIzq(int i) {
        return (2 * i) + 1;
    }

    private int getDer(int i) {
        return (2 * i) + 2;
    }

    private int getPadre(int i) {
        return (i - 1) / 2;
    }

    // Intercambia dos elementos dentro del ArrayList
    private void intercambiar(int i, int j) {
        int aux = monticulo.get(i);
        monticulo.set(i, monticulo.get(j));
        monticulo.set(j, aux);
    }

    // Inserta un nuevo valor al final y restaura el orden hacia arriba
    public void insertar(int nodo) {
        monticulo.add(nodo);
        upHeapify(monticulo.size() - 1);
    }

    // Reordena de forma recursiva subiendo el elemento mientras sea menor que su padre
    private void upHeapify(int i) {
        if (i <= 0) {
            return;
        }

        int padre = getPadre(i);
        if (monticulo.get(i) < monticulo.get(padre)) {
            intercambiar(i, padre);
            upHeapify(padre);
        }
    }

    // Elimina y retorna la cima del montículo manteniendo el orden mínimo
    public int eliminarMin() {
        if (estaVacio()) {
            System.out.println("El montículo está vacío.\n");
            return -1;
        }

        int minimo = monticulo.getFirst();
        int ultimo = monticulo.removeLast();

        // Si quedan elementos, el último sube a la cima y se reordena hacia abajo
        if (!estaVacio()) {
            monticulo.set(0, ultimo);
            downHeapify(0);
        }

        return minimo;
    }

    // Reordena de forma recursiva bajando el elemento hacia el menor de sus hijos
    private void downHeapify(int i) {
        int izq = getIzq(i);
        int der = getDer(i);
        int menor = i;

        // Si el hijo izquierdo existe y es menor, se marca como el menor
        if (izq < monticulo.size() && monticulo.get(izq) < monticulo.get(menor)) {
            menor = izq;
        }

        // Si el hijo derecho existe y es estrictamente menor, toma prioridad
        // (si ambos hijos son iguales, se mantiene el izquierdo)
        if (der < monticulo.size() && monticulo.get(der) < monticulo.get(menor)) {
            menor = der;
        }

        if (menor != i) {
            intercambiar(i, menor);
            downHeapify(menor);
        }
    }

    // Devuelve el valor en la cima sin eliminarlo
    public int peek() {
        if (estaVacio()) {
            System.out.println("El montículo está vacío.\n");
            return -1;
        }

        return monticulo.getFirst();
    }

    // Reemplaza el contenido actual por un arreglo y lo ordena como montículo mínimo
    public void heapify(int[] arreglo) {
        monticulo.clear();

        if (arreglo == null) {
            return;
        }

        for (int valor : arreglo) {
            monticulo.add(valor);
        }

        // Se aplica downHeapify desde el último nodo interno hasta la cima
        for (int i = (monticulo.size() / 2) - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    // Muestra el contenido actual del montículo en consola
    public void mostrarMonticulo() {
        if (estaVacio()) {
            System.out.println("El montículo está vacío.");
            return;
        }

        System.out.println("Montículo actual: " + monticulo);
    }
}