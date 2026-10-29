package edu.mondragon;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MainTest {

	public static Stream<Arguments> testGlobPatternData() {
		return Stream.of(
				Arguments.of("?a?", "aaa", true),
				Arguments.of("a", "a", true),
				Arguments.of("a?", "ab", true),
				Arguments.of("a", "b", false),
				Arguments.of("a?", "acc", false),
				Arguments.of("?a??", "aaa", false),
				Arguments.of("a?b", "atrueb", false),
				Arguments.of("a?b", "ab", false),
				Arguments.of("??a", "bba", true),
				Arguments.of("??", "bb", true),

				// Patrones con varios comodines
				Arguments.of("???", "abc", true),
				Arguments.of("???", "ab", false),
				Arguments.of("???", "abcd", false),
				Arguments.of("????", "abcd", true),

				// Comodín al principio
				Arguments.of("?ab", "cab", true),
				Arguments.of("?ab", "ab", false),
				Arguments.of("?ab", "xab", true),

				// Comodín en medio
				Arguments.of("a?c", "abc", true),
				Arguments.of("a?c", "adc", true),
				Arguments.of("a?c", "ac", false),
				Arguments.of("a?c", "abbc", false),

				// Comodín al final
				Arguments.of("ab?", "abc", true),
				Arguments.of("ab?", "abd", true),
				Arguments.of("ab?", "ab", false),
				Arguments.of("ab?", "abcd", false),

				// Varios comodines en el mismo patrón
				Arguments.of("?b?", "abc", true),
				Arguments.of("?b?", "bbb", true),
				Arguments.of("?b?", "bac", false),
				Arguments.of("a??d", "abcd", true),
				Arguments.of("a??d", "abxd", true),
				Arguments.of("a??d", "abd", false),
				Arguments.of("a??d", "abcde", false),

				// Patrones con caracteres repetidos
				Arguments.of("aa?", "aaa", true),
				Arguments.of("aa?", "aab", true),
				Arguments.of("aa?", "ab", false),
				Arguments.of("?aa", "baa", true),
				Arguments.of("?aa", "aba", false),

				// Casos límite
				Arguments.of("", "", true),
				Arguments.of("", "a", false),
				Arguments.of("a", "", false),
				Arguments.of("?", "a", true),
				Arguments.of("?", "", false) );
	}

	@ParameterizedTest
	@MethodSource("testGlobPatternData")
	public void testGlobPattern(String pattern, String text, boolean  expected) {
		GlobPattern glob = new GlobPattern(pattern);

		assertEquals(expected, glob.match(text));
	}

}
