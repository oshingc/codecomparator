package model.command.application.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pe.com.codecomparator.domain.util.StringUtils;

public class StringUtilTest {

	@Test
	public void testReplaceBackslashByForwardslash() {
		String sourcePath = "src\\main\\java";

		String sourcePathExpected = "src/main/java/";
		String sourcePathActual = StringUtils
				.replaceBackslashByForwardslash(sourcePath);

		assertEquals(sourcePathExpected, sourcePathActual);
	}

}
