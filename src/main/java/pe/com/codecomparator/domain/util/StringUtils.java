package pe.com.codecomparator.domain.util;

import pe.com.codecomparator.domain.CodeComparatorConstants;

public class StringUtils {

	public static String replaceBackslashByForwardslash(String word) {
		String[] tokens = word.split(CodeComparatorConstants.BACKSLASH_PATTERN);
		String out = "";
		for (String token : tokens) {
			out += token + CodeComparatorConstants.FORWARDSLASH;
		}
		return out;
	}

}
