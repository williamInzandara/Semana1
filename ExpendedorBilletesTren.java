import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpendedorBilletesTren {

    // Lista de destinos disponibles (ejemplo simple)
    private static List<String> destinos = new ArrayList<>();
    
    static {
        destinos.add("Ciudad A");
        destinos.add("Ciudad B");
        destinos.add("Ciudad C");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== SISTEMA EXPENDEDOR DE BILLETES DE TREN ===");
        
        // 1. Simular botón de inicio
        System.out.println("Presione ENTER para iniciar...");
        sc.nextLine();
        
        // 2. Mostrar destinos y seleccionar
        mostrarDestinos();
        System.out.print("Seleccione el número de destino: ");
        int indiceDestino = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        
        // Validar índice
        if (indiceDestino < 1 || indiceDestino > destinos.size()) {
            System.out.println("Opción inválida. Saliendo del sistema...");
            return;
        }
        
        String destinoSeleccionado = destinos.get(indiceDestino - 1);
        
        // 3. Pedir ID personal
        System.out.print("Ingrese su ID personal: ");
        String idPersonal = sc.nextLine();
        
        // 4. Introducir tarjeta de crédito
        System.out.print("Ingrese número de tarjeta de crédito: ");
        String numeroTarjeta = sc.nextLine();
        
        // 5. Validar la transacción de crédito
        boolean pagoExitoso = ServicioProcesamientoBancario.validarPago(numeroTarjeta, idPersonal, 100.0); 
        // Se simula un costo de 100.0 para cualquier destino
        
        if (pagoExitoso) {
            // 6. Expedir billete
            TicketPrinter.imprimirBillete(destinoSeleccionado);
        } else {
            System.out.println("No se pudo procesar el pago. Transacción cancelada.");
        }
        
        sc.close();
    }
    
    /**
     * Muestra la lista de destinos por consola.
     */
    private static void mostrarDestinos() {
        System.out.println("Destinos disponibles:");
        for (int i = 0; i < destinos.size(); i++) {
            System.out.println((i + 1) + ". " + destinos.get(i));
        }
    }
}

/**
 * Clase que simula el servicio de procesamiento bancario.
 */
class ServicioProcesamientoBancario {
    
    /**
     * Valida un pago de manera simulada.
     * @param numeroTarjeta   Número de tarjeta (string ficticio).
     * @param idPersonal      ID personal.
     * @param monto           Monto a cobrar.
     * @return true si la validación "pasa", false en caso contrario.
     */
    public static boolean validarPago(String numeroTarjeta, String idPersonal, double monto) {
        // Lógica ficticia: se considera válido si ambos campos no están vacíos y
        // el número de tarjeta tiene al menos 8 caracteres
        if (numeroTarjeta != null && numeroTarjeta.length() >= 8 && idPersonal != null && !idPersonal.isEmpty()) {
            System.out.println("Procesando pago de $" + monto + " ...");
            System.out.println("Pago validado correctamente.");
            return true;
        }
        System.out.println("Error en los datos de pago. Pago rechazado.");
        return false;
    }
}

/**
 * Clase que simula la impresora de billetes.
 */
class TicketPrinter {
    
    /**
     * Imprime (simula) la expedición del billete.
     * @param destinoDestino seleccionado.
     */
    public static void imprimirBillete(String destino) {
        System.out.println("=== BILLETE DE TREN ===");
        System.out.println("Destino: " + destino);
        System.out.println("Fecha: " + java.time.LocalDate.now());
        System.out.println("¡Gracias por su compra! El billete ha sido expedido.");
    }
}
