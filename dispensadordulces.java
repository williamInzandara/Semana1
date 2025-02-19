import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DispensadorDulces {

    // Datos de los dulces: nombres y precios (en COP)
    private static final String[] NOMBRES_DULCES = {
        "Chocorramo", "Bombombum", "Frunas", "Animoto", "Jet", "Bianchi"
    };
    private static final int[] PRECIOS_DULCES = {
        1500, 300, 200, 700, 1000, 1200
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== DISPENSADOR DE DULCES ===");
        mostrarMenuDulces();

        // 1. Selección de dulces
        System.out.println("\nIngrese los números de los dulces que desea comprar, separados por espacios:");
        String dulcesInput = sc.nextLine();
        String[] dulcesTokens = dulcesInput.trim().split("\\s+");

        List<String> dulcesSeleccionados = new ArrayList<>();
        int costoTotal = 0;
        for (String token : dulcesTokens) {
            try {
                int opcion = Integer.parseInt(token);
                if (opcion >= 1 && opcion <= NOMBRES_DULCES.length) {
                    dulcesSeleccionados.add(NOMBRES_DULCES[opcion - 1]);
                    costoTotal += PRECIOS_DULCES[opcion - 1];
                } else {
                    System.out.println("Opción " + opcion + " no es válida y se omitirá.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada \"" + token + "\" no es válida y se omitirá.");
            }
        }

        if (dulcesSeleccionados.isEmpty()) {
            System.out.println("No se seleccionaron dulces válidos. Saliendo del sistema...");
            sc.close();
            return;
        }

        // Mostrar selección y costo total
        System.out.println("\nHas seleccionado los siguientes dulces:");
        for (String dulce : dulcesSeleccionados) {
            System.out.println(" - " + dulce);
        }
        System.out.println("Costo total: " + costoTotal + " COP");

        // 2. Proceso de pago con opciones (1: 1000, 2: 2000, 3: 5000, 0: Cancelar)
        int totalIngresado = 0;
        while (totalIngresado < costoTotal) {
            System.out.println("\nIngrese billetes (opciones separadas por espacios):");
            System.out.println(" 1 = 1000 COP, 2 = 2000 COP, 3 = 5000 COP, 0 = Cancelar compra");
            String billetesInput = sc.nextLine();
            String[] billetesTokens = billetesInput.trim().split("\\s+");

            for (String token : billetesTokens) {
                try {
                    int opcionPago = Integer.parseInt(token);
                    if (opcionPago == 0) {
                        System.out.println("Compra cancelada. Devolviendo dinero ingresado: " 
                                + totalIngresado + " COP");
                        sc.close();
                        return;
                    } else if (opcionPago == 1) {
                        totalIngresado += 1000;
                    } else if (opcionPago == 2) {
                        totalIngresado += 2000;
                    } else if (opcionPago == 3) {
                        totalIngresado += 5000;
                    } else {
                        System.out.println("Opción " + opcionPago + " no es válida y se omitirá.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada \"" + token + "\" no es válida y se omitirá.");
                }
            }
            System.out.println("Total ingresado hasta ahora: " + totalIngresado + " COP");
            if (totalIngresado < costoTotal) {
                int restante = costoTotal - totalIngresado;
                System.out.println("Falta ingresar: " + restante + " COP.");
            }
        }

        // 3. Calcular cambio (si es que hay)
        int cambio = totalIngresado - costoTotal;

        // 4. Mostrar factura de la compra
        System.out.println("\n=== COMPRA EXITOSA ===");
        System.out.println("Dulces comprados:");
        for (String dulce : dulcesSeleccionados) {
            System.out.println(" - " + dulce);
        }
        System.out.println("Costo total: " + costoTotal + " COP");
        System.out.println("Total pagado: " + totalIngresado + " COP");
        if (cambio > 0) {
            System.out.println("Tu cambio es: " + cambio + " COP");
        } else {
            System.out.println("No hay cambio.");
        }
        System.out.println("¡Gracias por tu compra!");

        sc.close();
    }

    /**
     * Muestra el menú de dulces con sus precios.
     */
    private static void mostrarMenuDulces() {
        System.out.println("Dulces disponibles:");
        for (int i = 0; i < NOMBRES_DULCES.length; i++) {
            System.out.println((i + 1) + ") " + NOMBRES_DULCES[i] + " - " + PRECIOS_DULCES[i] + " COP");
        }
    }
}


