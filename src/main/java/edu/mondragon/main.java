package edu.mondragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    /**
     * Nombre que se muestra en el mensaje de uso (el "$(COMMAND)" del enunciado).
     * Java no expone el nombre del ejecutable de forma portable, así que se usa
     * una constante: ajústala si los tests esperan otro valor.
     */
    private static final String COMMAND = "glob";

    public static void main(String[] args) {
        // Debe recibir EXACTAMENTE un argumento (el patrón)
        if (args == null || args.length != 1) {
            System.out.println("Usage: " + COMMAND + " [PATTERN]");
            return;
        }

        GlobPattern pattern = new GlobPattern(args[0]);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = System.out;

        try {
            String line;
            while ((line = in.readLine()) != null) {
                out.println(pattern.match(line) ? "1" : "0");
            }
        } catch (IOException e) {
            // Error de E/S: número negativo
            out.println("-1");
            out.flush();
            System.exit(1);
        }
    }
}