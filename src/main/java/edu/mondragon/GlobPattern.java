package edu.mondragon;

/**
 * Patrón glob simple:
 *   - '?'  coincide con exactamente un carácter cualquiera
 *   - cualquier otro carácter debe coincidir literalmente
 */
public class GlobPattern {

    private final String pattern;

    public GlobPattern(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern must not be null");
        }
        this.pattern = pattern;
    }

    public boolean match(String text) {
        if (text == null) {
            return false;
        }
        // Longitudes distintas => imposible que coincidan (no hay comodines de longitud variable)
        if (text.length() != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            if (p != '?' && p != text.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}