package pe.com.codecomparator.model.command.core.fdtw;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class FDTW {
	// FDTW
	public Double[] Q; // Secuencia
	public Double[] C; // Secuencia
	public Double[][] M; // Matriz de distancias
	// FDTW & Pattern Recognition
	public Node[] Path; // Array of index in M, for subsequence detection
	public int patterns_founded;
	public int[] Qcode_line;
	public int[] Ccode_line;
	// Otras variables
	public String FileQ, FileC; // Nombres de los archivos para guardar las
								// secuencias

	public FDTW() { // Default Constructor
	}

	// Se asigna la secuencia _Q, _C a Q y C
	public void SetSequences(Double[] _Q, Double[] _C) {
		Q = (Double[]) _Q.clone();
		C = (Double[]) _C.clone();
	}

	// Returns de FDTW distance between Q,C Sequences
	public double GetDistance() {
		try {
			M = new Double[Q.length][C.length];
			// First element
			M[0][0] = euclidean(Q[0], C[0]);
			// First Row
			for (int j = 1; j < C.length; j++)
				M[0][j] = M[0][j - 1] + euclidean(Q[0], C[j]);
			// First Column
			for (int i = 1; i < Q.length; i++)
				M[i][0] = M[i - 1][0] + euclidean(Q[i], C[0]);
			// Rest of table
			for (int i = 1; i < Q.length; i++)
				for (int j = 1; j < C.length; j++) {
					double distance = euclidean(Q[i], C[j]);
					M[i][j] = M[i - 1][j - 1] + distance;
					if ((M[i - 1][j] + distance) < M[i][j])
						M[i][j] = M[i - 1][j] + distance;
					if ((M[i][j - 1] + distance) < M[i][j])
						M[i][j] = M[i][j - 1] + distance;
				}
			// Gets the Warp path for subsequence detection
			this.BackTracking();
			// return de FDTW distance

			return M[Q.length - 1][C.length - 1];
		} catch (Exception ex) {
			System.err.println(ex.toString());
			// if an error occurs return Infinity
			return Double.MAX_VALUE;
		}
	}

	// Returns the Euclidean distance between two values
	private double euclidean(Double q, Double c) {
		// return Math.abs(q-c);
		return (Math.sqrt(Math.pow(q - c, 2)));
	}

	// Performs BackTracking for further subsequence detection
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void BackTracking() {
		double distance = 0.00;
		int i, j; // Distance Matrix index
		Node p = new Node(); // Contains one path_item
		ArrayList path = new ArrayList(); // Temporary Array
		p.q = i = Q.length - 1; // Path always include the last
		p.c = j = C.length - 1; // Matrix cell [Q,C]
		path.add(p); // Add node to path
		// Move Along Distance Matrix until M[0,1] or M[1,0]
		while (i > 1 || j > 1) {
			p = new Node();
			// Search the minimun distance from M[i,j] to
			// 45,0, and 90 degrees neighbors
			// 45 Degrees
			if (i >= 1 && j >= 1) {
				distance = M[i - 1][j - 1];
				p.q = i - 1;
				p.c = j - 1;
			} else
				distance = Double.MAX_VALUE;

			// 0 Grados
			if (i >= 0 && j > 0)
				if (M[i][j - 1] < distance) {
					p.q = i;
					p.c = j - 1;
					distance = M[i][j - 1];
				}

			// 90 Grados
			if (i > 0 && j >= 0)
				if (M[i - 1][j] < distance) {
					p.q = i - 1;
					p.c = j;
				}
			i = p.q; // Move i,j index to closest neighbord with
			j = p.c; // minimum distance
			path.add(p); // Add this node into path
		}

		// Path always include the first Matrix cell M[0,0]
		p = new Node();
		p.q = p.c = 0;
		path.add(p);
		// Sort the elements for display
		// path.Reverse();
		Collections.reverse(path);
		// Path = (Node[])path.ToArray(typeof(node));
		// Path =(Node[]) path.toArray();

		Path = new Node[path.size()];
		for (int kk = 0; kk < path.size(); kk++) {
			Path[kk] = (Node) path.get(kk);
		}

		int nsub = 0;
		boolean found = false;
		// Subsequence Detection
		for (int z = 0; z < Path.length - 1; z++)
			if (Math.abs(Path[z].q - Path[z + 1].q) == 1
					&& Math.abs(Path[z].c - Path[z + 1].c) == 1) { // Do
																	// something
																	// with
																	// subsequence
				Path[z].IsSub = true;
				Path[z].nsub = nsub;
				Path[z + 1].IsSub = true;
				Path[z + 1].nsub = nsub;
				found = true;
			} else if (found) {
				nsub++;
				found = false;
			}
		patterns_founded = nsub + 1;
	}

	// Write the subsequences founded into a folder.
	public void WriteSubsequences(String folder_dir,
			boolean zeroAverage_oneSplit) {
		try {
			String dir;
			if (zeroAverage_oneSplit)
				// dir = folder_dir + "subs_"+ ExtractFileName(FileQ) + "_"+
				// ExtractFileName(FileC) + "\\";
				dir = folder_dir + "subs_" + (FileQ) + "_" + (FileC) + "\\";
			else
				dir = folder_dir + "AVsubs_" + (FileQ) + "_" + (FileC) + "\\";
			// dir = folder_dir + "AVsubs_"+ ExtractFileName(FileQ) + "_"+
			// ExtractFileName(FileC) + "\\";

			/*
			 * if(!Directory.Exists(dir)) Directory.CreateDirectory(dir);
			 */

			// crear directorio:
			File directorio = new File(dir);
			if (!directorio.exists()) {
				try {
					directorio.mkdirs();
				} catch (Exception e) {
				}
			}

			// Split
			if (zeroAverage_oneSplit)
				for (int z = 0; z < Path.length; z++) {
					Node n;
					if (Path[z].IsSub) {
						int sub = Path[z].nsub;
						// using(StreamWriter sq = new StreamWriter(dir +
						// (sub+1) + "_q.txt")){
						File sq = new File(dir + (sub + 1) + "_q.txt");
						FileWriter f1 = new FileWriter(sq, true);
						// using(StreamWriter sc = new StreamWriter(dir +
						// (sub+1) + "_c.txt")){
						File sc = new File(dir + (sub + 1) + "_c.txt");
						FileWriter f2 = new FileWriter(sc, true);
						while (z < Path.length && Path[z].nsub == sub) {
							n = Path[z];
							// sq.WriteLine(String.valueOf(Q[n.q]));
							f1.write(String.valueOf(Q[n.q]) + "\t");
							// sc.WriteLine(String.valueOf(C[n.c]));
							f2.write(String.valueOf(C[n.c]) + "\t");
							z++;
						}
						f1.close();
						f2.close();
						// }
						// }
						z--;
					}
				}
			else
				// Average
				// Show Subsequences
				for (int z = 0; z < Path.length; z++) {
					Node n;
					if (Path[z].IsSub) {
						int sub = Path[z].nsub;
						// using(StreamWriter sw = new StreamWriter(dir +
						// (sub+1) + "_av.txt")){
						File sw = new File(dir + (sub + 1) + "_av.txt");
						FileWriter f3 = new FileWriter(sw, true);
						while (z < Path.length && Path[z].nsub == sub) {
							n = Path[z];
							// sw.WriteLine(((Q[n.q] + C[n.c]) / 2));
							f3.write(String.valueOf((Q[n.q] + C[n.c]) / 2)
									+ "\t");
							z++;
						}
						f3.close();
						// }
						z--;
					}
				}
		} catch (Exception ex) {
		}
	}
}