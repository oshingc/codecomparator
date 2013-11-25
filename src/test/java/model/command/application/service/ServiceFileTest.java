package model.command.application.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ServiceFileTest {

	@Test
	public void test() {
		File uno = new File("/home/braulio/Escritorio/uno/");
		uno.mkdir();
		
		assertTrue(uno.exists());
	}

}
