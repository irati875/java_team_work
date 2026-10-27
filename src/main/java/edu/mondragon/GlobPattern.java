package edu.mondragon;

public class GlobPattern {
    private String pattern;

    public GlobPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean match(String text) {
        // Si el texto es nulo o la longitud no coincide, no hay match
        if (text == null || text.length() != pattern.length()) {
            return false;
        }
        
        // Recorremos carácter a carácter
        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            char t = text.charAt(i);
            
            // Si no es '?' y los caracteres no son iguales, falla
            if (p != '?' && p != t) {
                return false;
            }
        }
        return true; // Si pasa el bucle, es un match
    }
}