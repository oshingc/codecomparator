package pe.com.codecomparator.domain.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUtil {

	public static BufferedReader obtainBufferedReaderFromFile(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return br;
	}

}
