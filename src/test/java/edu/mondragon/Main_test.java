package edu.mondragon;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Main_test {

	static Stream<Arguments> testGlobPatternData() {
		return Stream.of(
				Arguments.of("?a?", "aaa", 1),
				Arguments.of("a", "a", 1),
				Arguments.of("a?", "ab", 1),
				Arguments.of("a", "b", 0),
				Arguments.of("a?", "acc", 0),
				Arguments.of("?a??", "aaa", 0),
				Arguments.of("a?b", "a1b", 1),
				Arguments.of("a?b", "ab", 0),
				Arguments.of("??a", "bba", 1),
				Arguments.of("??", "bb", 1),

				// Patrones con varios comodines
				Arguments.of("???", "abc", 1),
				Arguments.of("???", "ab", 0),
				Arguments.of("???", "abcd", 0),
				Arguments.of("????", "abcd", 1),

				// Comodín al principio
				Arguments.of("?ab", "cab", 1),
				Arguments.of("?ab", "ab", 0),
				Arguments.of("?ab", "xab", 1),

				// Comodín en medio
				Arguments.of("a?c", "abc", 1),
				Arguments.of("a?c", "adc", 1),
				Arguments.of("a?c", "ac", 0),
				Arguments.of("a?c", "abbc", 0),

				// Comodín al final
				Arguments.of("ab?", "abc", 1),
				Arguments.of("ab?", "abd", 1),
				Arguments.of("ab?", "ab", 0),
				Arguments.of("ab?", "abcd", 0),

				// Varios comodines en el mismo patrón
				Arguments.of("?b?", "abc", 1),
				Arguments.of("?b?", "bbb", 1),
				Arguments.of("?b?", "bac", 0),
				Arguments.of("a??d", "abcd", 1),
				Arguments.of("a??d", "abxd", 1),
				Arguments.of("a??d", "abd", 0),
				Arguments.of("a??d", "abcde", 0),

				// Patrones con caracteres repetidos
				Arguments.of("aa?", "aaa", 1),
				Arguments.of("aa?", "aab", 1),
				Arguments.of("aa?", "ab", 0),
				Arguments.of("?aa", "baa", 1),
				Arguments.of("?aa", "aba", 0),

				// Casos límite
				Arguments.of("", "", 1),
				Arguments.of("", "a", 0),
				Arguments.of("a", "", 0),
				Arguments.of("?", "a", 1),
				Arguments.of("?", "", 0));
	}

	@ParameterizedTest
	@MethodSource("testGlobPatternData")
	public void testGlobPattern(String pattern, String text, int expected) {
		GlobPattern glob = new GlobPattern(pattern);

		assertEquals(expected, glob.match(text));
	}

}
