package src.main.java.edu.mondragon;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Comprobar argumentos
        if (args.length != 1) {
            System.out.println("Usage: $(COMMAND) [PATTERN]");
            System.exit(-1); // Salir con error
        }

        // 2. Crear el patrón
        GlobPattern glob = new GlobPattern(args[0]);
        
        // 3. Leer de STDIN
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            // 4. Imprimir resultado en STDOUT
            if (glob.match(line)) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
        }
        scanner.close();
    }
}