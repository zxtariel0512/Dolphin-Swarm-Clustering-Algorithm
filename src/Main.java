import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import org.apache.log4j.BasicConfigurator;

public class Main {
	
	/**
	 * needed for noun group merge function
	 */
	public static final int FREQUENCY = 3;
	
	/**
	 * number of files in C1, C4, and C7 folder
	 */
	public static final int C1 = 8;
	public static final int C4 = 8;
	public static final int C7 = 8;
	
	/**
	 * number of files in each folder of author identification datasets
	 */
	public static final int AUT = 10;
	
	/**
	 * For detailed explanation and notes, please go to README
	 * Commented out needed blocks of codes to check the validity of testing results on report for each testing datasets
	 * ++++++++++++++++++++++++++++IMPORTANT: like many other clustering algorithm, this cluster algorithm can generate
	 * ++++++++++++++++++++++++++++++++++++++ different clustering results every time. So I have ran the algorithm on each dataset
	 * ++++++++++++++++++++++++++++++++++++++ for multiple times and stored the best one in performance text file. So it may be possible
	 * ++++++++++++++++++++++++++++++++++++++ For more notes on parameter choice of 'e', 'c', 'speed', please go to README
	 * *************************** If you want to test your own datasets, remember to first go to Dolphin.java, Prey.java, Cluster.java
	 * *************************** to change certain parameters (as specified both in code comments and in README) to match your option
	 * *************************** of real number / text datasets, cosine similarity / euclidean measure
	 * *************************** Otherwise there can be error message!!!
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		
//		// -------------------------------------------------------------------------TEST IRIS DATA FILES
//		HashMap<double[], String> ans = new HashMap<double[], String>();
//		File file = new File("iris.data");
//		BufferedReader r = new BufferedReader(new FileReader(file));
//		ArrayList<double[]> rawList = new ArrayList<double[]>();
//		String line = "";
//		while(!(line = r.readLine()).equals("")) {
//			String dataStr = line.substring(0, 15);
//			String cat = line.substring(21, line.length());
//			String[] dataStrArr = dataStr.split(",");
//			double[] rawPos = new double[4];
//			for(int i = 0; i < dataStrArr.length; i++) {
//				rawPos[i] = Double.parseDouble(dataStrArr[i]);
//			}
//			rawList.add(rawPos);
//			ans.put(rawPos, cat);
//		}
//		double[][] rawArr = new double[rawList.size()][4];
//		for(int i = 0; i < rawList.size(); i++) {
//			rawArr[i] = rawList.get(i);
//		}
//		int k = 3;
//		double searchR = 0.8;
//		double speed = 0.1;
//		int e = 4;
//		Cluster c = new Cluster(rawArr, k, searchR, speed, e);
//		ArrayList<Prey[]> res = new ArrayList<Prey[]>(c.cluster());
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				double[] plot = new double[2];
//				for(int i = 0; i < plot.length; i++) {
//					plot[i] = p.raw[i];
//				}
//				System.out.print(Arrays.toString(plot) + ",");
//			}
//			System.out.println("\n");
//		}
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.println(ans.get(p.raw));
//			}
//			System.out.println("\n");
//		}
		
		
		
		// -------------------------------------------------------------------------------TEST HW1 DOCUMENTS FILES
//		BasicConfigurator.configure();
//		
//		// initialize preprocess
//		Preprocess P = new Preprocess();
//		for(int i = 1; i <= C1; i++) {
//			Document d = new Document("C10" + i, "C1/article0" + i + ".txt");
//			P.documents.add(d);
//		}
//		for(int i = 1; i <= C4; i++) {
//			Document d = new Document("C40" + i, "C4/article0" + i + ".txt");
//			P.documents.add(d);
//		}
//		for(int i = 1; i <= C7; i++) {
//			Document d = new Document("C70" + i, "C7/article0" + i + ".txt");
//			P.documents.add(d);
//		}
//		
//		// start preprocessing
//		P.action();
//		
//		// initialize matrix generation
//		Term_Matrix M = new Term_Matrix();
//		// start generating
//		M.getAllWords(P.documents);
//		M.getMatrix(P.documents);
//		M.computeTFIDF(P.documents);
//		int k = 3;
//		double searchR = 0;
//		double speed = 0.02;
//		int e = 6;
//		Cluster c = new Cluster(P.documents, M.tfidf, k, searchR, speed, e);
//		HashMap<String, String> res = new HashMap<String, String>(c.cluster());
//		for(Document d: P.documents) {
//			System.out.println(d.name + "     " + res.get(d.name));
//		}
				
		
		
		
		// -------------------------------------------------------------------------------------TEST CANCER
//		HashMap<double[], Character> ans = new HashMap<double[], Character>();
//		File file = new File("cervical_cancer.csv");
//		BufferedReader r = new BufferedReader(new FileReader(file));
//		ArrayList<double[]> rawList = new ArrayList<double[]>();
//		String line = "";
//		int lineNum = 0;
//		while(lineNum < 73) {
//			line = r.readLine();
//			if(lineNum == 0) {
//				lineNum++;
//				continue;
//			}
//			String dataStr = line.substring(0, line.length() - 2);
//			char cat = line.charAt(line.length() - 1);
//			String[] dataStrArr = dataStr.split(",");
//			double[] rawPos = new double[dataStrArr.length];
//			for(int i = 0; i < dataStrArr.length; i++) {
//				rawPos[i] = Double.parseDouble(dataStrArr[i]);
//			}
//			rawList.add(rawPos);
//			ans.put(rawPos, cat);
//			lineNum++;
//		}
//		double[][] rawArr = new double[rawList.size()][rawList.get(0).length];
//		for(int i = 0; i < rawList.size(); i++) {
//			rawArr[i] = rawList.get(i);
//		}
//		int k = 2;
//		double searchR = 100;
//		double speed = 0.1;
//		int e = 50;
//		Cluster c = new Cluster(rawArr, k, searchR, speed, e);
//		ArrayList<Prey[]> res = new ArrayList<Prey[]>(c.cluster());
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				double[] plot = new double[2];
//				plot[0] = p.raw[3];
//				plot[1] = p.raw[9];
//				System.out.print(Arrays.toString(plot) + ",");
//			}
//			System.out.println("\n");
//		}
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.println(ans.get(p.raw));
//			}
//			System.out.println("\n");
//		}
		
		
		
		
		// -----------------------------------------------------------------------------------------TEST: HTRU
//		HashMap<double[], Character> ans = new HashMap<double[], Character>();
//		File file = new File("HTRU_2.csv");
//		BufferedReader r = new BufferedReader(new FileReader(file));
//		ArrayList<double[]> rawList = new ArrayList<double[]>();
//		String line = "";
//		int lineNum = 0;
//		while(lineNum < 2000) {
//			line = r.readLine();
//			if(lineNum == 0) {
//				lineNum++;
//				continue;
//			}
//			String dataStr = line.substring(0, line.length() - 2);
//			char cat = line.charAt(line.length() - 1);
//			String[] dataStrArr = dataStr.split(",");
//			double[] rawPos = new double[dataStrArr.length - 2];
//			for(int i = 0; i < rawPos.length; i++) {
//				rawPos[i] = Double.parseDouble(dataStrArr[i + 2]);
//			}
//			rawList.add(rawPos);
//			ans.put(rawPos, cat);
//			lineNum++;
//		}
//		double[][] rawArr = new double[rawList.size()][rawList.get(0).length];
//		for(int i = 0; i < rawList.size(); i++) {
//			rawArr[i] = rawList.get(i);
//		}
//		int k = 2;
//		double searchR = 100;
//		double speed = 0.1;
//		int e = 50;
//		Cluster c = new Cluster(rawArr, k, searchR, speed, e);
//		ArrayList<Prey[]> res = new ArrayList<Prey[]>(c.cluster());
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				double[] plot = new double[2];
//				plot[0] = p.raw[2];
//				plot[1] = p.raw[3];
//				System.out.print(Arrays.toString(plot) + ",");
//			}
//			System.out.println("\n");
//		}
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.println(ans.get(p.raw));
//			}
//			System.out.println("\n");
//		}
		
		
		
		
		
		
		
//		// ----------------------------------------------------------------------------TEST BREAST CANCER
//		HashMap<double[], String> ans = new HashMap<double[], String>();
//		File file = new File("wdbc.data");
//		BufferedReader r = new BufferedReader(new FileReader(file));
//		ArrayList<double[]> rawList = new ArrayList<double[]>();
//		String line = "";
//		int lineNum = 0;
//		while(lineNum < 569) {
//			line = r.readLine();
//			String dataStr = line;
//			String[] dataStrArr = dataStr.split(",");
//			String cat = dataStrArr[1];
//			double[] rawPos = new double[dataStrArr.length - 2];
//			for(int i = 0; i < rawPos.length; i++) {
//				rawPos[i] = Double.parseDouble(dataStrArr[i+2]);
//			}
//			rawList.add(rawPos);
//			ans.put(rawPos, cat);
//			lineNum++;
//		}
//		double[][] rawArr = new double[rawList.size()][rawList.get(0).length];
//		for(int i = 0; i < rawList.size(); i++) {
//			rawArr[i] = rawList.get(i);
//		}
//		int k = 2;
//		double searchR = 700;
//		double speed = 0.3;
//		int e = 800;
//		Cluster c = new Cluster(rawArr, k, searchR, speed, e);
//		ArrayList<Prey[]> res = new ArrayList<Prey[]>(c.cluster());
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.print(Arrays.toString(p.position) + ",");
//			}
//			System.out.println("\n");
//		}
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.println(ans.get(p.raw));
//			}
//			System.out.println("\n");
//		}
		
		
		
		
		
		// ------------------------------------------------------------------------------TEST: QCM CENSOR
//		HashMap<double[], String> ans = new HashMap<double[], String>();
//		File file = new File("QCM.csv");
//		BufferedReader r = new BufferedReader(new FileReader(file));
//		ArrayList<double[]> rawList = new ArrayList<double[]>();
//		String line = "";
//		int lineNum = 0;
//		while((line = r.readLine()) != null) {
//			if(lineNum == 0) {
//				lineNum++;
//				continue;
//			}
//			String dataStr = line;
//			String[] dataStrArr = dataStr.split(";");
//			String cat = "";
//			if(Integer.parseInt(dataStrArr[10]) == 1) cat = "1-Octanol";
//			else if(Integer.parseInt(dataStrArr[11]) == 1) cat = "1-Propanol";
//			else if(Integer.parseInt(dataStrArr[12]) == 1) cat = "2-Butanol";
//			else if(Integer.parseInt(dataStrArr[13]) == 1) cat = "2-propanol";
//			else if(Integer.parseInt(dataStrArr[14]) == 1) cat = "2-isobutanol";
//			else {
//				System.out.println("no cat");
//				lineNum++;
//				continue;
//			}
//			double[] rawPos = new double[dataStrArr.length - 5];
//			for(int i = 0; i < rawPos.length; i++) {
//				rawPos[i] = Double.parseDouble(dataStrArr[i]);
//			}
//			rawList.add(rawPos);
//			ans.put(rawPos, cat);
//			lineNum++;
//		}
//		double[][] rawArr = new double[rawList.size()][rawList.get(0).length];
//		for(int i = 0; i < rawList.size(); i++) {
//			rawArr[i] = rawList.get(i);
//		}
		// ++++++++++++++++++++++++++++++++++++++++++ parameters needed to be changed for each qcm file
//		// QCM3: searchR = 400, speed = 0.3, e = 1000
//		// QCM6: searchR = 500, speed = 0.1, e = 100
//		// QCM7: searchR = 300, speed = 0.3, e = 500
//		// QCM10:searchR = 400, speed = 0.3, e = 1000
//		// QCM12:searchR = 500, speed = 0.5, e = 1000
		// QCM: searchR = 400, speed = 0.3, e = 1000
//		int k = 5;
//		double searchR = 400;
//		double speed = 0.3;
//		int e = 1000;
//		Cluster c = new Cluster(rawArr, k, searchR, speed, e);
//		ArrayList<Prey[]> res = new ArrayList<Prey[]>(c.cluster());
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				double[] plot = new double[2];
//				plot[0] = p.raw[3];
//				plot[1] = p.raw[9];
//				System.out.print(Arrays.toString(plot) + ",");
//			}
//			System.out.println("\n");
//		}
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.println(ans.get(p.raw));
//			}
//			System.out.println("\n");
//		}
		
		
		
		
		
		//TEST: ------------------------------------------------------------------AUTHOR
		BasicConfigurator.configure();
		
		// initialize preprocess
		Preprocess P = new Preprocess();
		P.documents.add(new Document("AP1", "AaronPressman/106247newsML.txt"));
		P.documents.add(new Document("AP2", "AaronPressman/120600newsML.txt"));
		P.documents.add(new Document("AP3", "AaronPressman/120683newsML.txt"));
		P.documents.add(new Document("AP4", "AaronPressman/136958newsML.txt"));
		P.documents.add(new Document("AP5", "AaronPressman/137498newsML.txt"));
		P.documents.add(new Document("AP6", "AaronPressman/14014newsML.txt"));
		P.documents.add(new Document("AP7", "AaronPressman/156814newsML.txt"));
		P.documents.add(new Document("AP8", "AaronPressman/182596newsML.txt"));
		P.documents.add(new Document("AP9", "AaronPressman/186392newsML.txt"));
		P.documents.add(new Document("AP10", "AaronPressman/2537newsML.txt"));
		P.documents.add(new Document("AP11", "AaronPressman/357147newsML.txt"));
		P.documents.add(new Document("AP12", "AaronPressman/366020newsML.txt"));
		P.documents.add(new Document("AP13", "AaronPressman/369570newsML.txt"));
		P.documents.add(new Document("AP14", "AaronPressman/371380newsML.txt"));
		P.documents.add(new Document("AP15", "AaronPressman/372744newsML.txt"));
		P.documents.add(new Document("AC1", "AlanCrosby/101797newsML.txt"));
		P.documents.add(new Document("AC2", "AlanCrosby/10306newsML.txt"));
		P.documents.add(new Document("AC3", "AlanCrosby/104277newsML.txt"));
		P.documents.add(new Document("AC4", "AlanCrosby/104278newsML.txt"));
		P.documents.add(new Document("AC5", "AlanCrosby/10650newsML.txt"));
		P.documents.add(new Document("AC6", "AlanCrosby/109906newsML.txt"));
		P.documents.add(new Document("AC7", "AlanCrosby/110434newsML.txt"));
		P.documents.add(new Document("AC8", "AlanCrosby/113639newsML.txt"));
		P.documents.add(new Document("AC9", "AlanCrosby/20910newsML.txt"));
		P.documents.add(new Document("AC10", "AlanCrosby/21296newsML.txt"));
		P.documents.add(new Document("AC11", "AlanCrosby/188747newsML.txt"));
		P.documents.add(new Document("AC12", "AlanCrosby/194349newsML.txt"));
		P.documents.add(new Document("AC13", "AlanCrosby/194437newsML.txt"));
		P.documents.add(new Document("AC14", "AlanCrosby/194611newsML.txt"));
		P.documents.add(new Document("AC15", "AlanCrosby/194667newsML.txt"));
		P.documents.add(new Document("AS1", "AlexanderSmith/107525newsML.txt"));
		P.documents.add(new Document("AS2", "AlexanderSmith/109096newsML.txt"));
		P.documents.add(new Document("AS3", "AlexanderSmith/110282newsML.txt"));
		P.documents.add(new Document("AS4", "AlexanderSmith/134290newsML.txt"));
		P.documents.add(new Document("AS5", "AlexanderSmith/16167newsML.txt"));
		P.documents.add(new Document("AS6", "AlexanderSmith/18111newsML.txt"));
		P.documents.add(new Document("AS7", "AlexanderSmith/18227newsML.txt"));
		P.documents.add(new Document("AS8", "AlexanderSmith/21127newsML.txt"));
		P.documents.add(new Document("AS9", "AlexanderSmith/23876newsML.txt"));
		P.documents.add(new Document("AS10", "AlexanderSmith/29098newsML.txt"));
		P.documents.add(new Document("AS11", "AlexanderSmith/219512newsML.txt"));
		P.documents.add(new Document("AS12", "AlexanderSmith/219521newsML.txt"));
		P.documents.add(new Document("AS13", "AlexanderSmith/220666newsML.txt"));
		P.documents.add(new Document("AS14", "AlexanderSmith/223283newsML.txt"));
		P.documents.add(new Document("AS15", "AlexanderSmith/223300newsML.txt"));
		P.documents.add(new Document("BH1", "BernardHickey/103816newsML.txt"));
		P.documents.add(new Document("BH2", "BernardHickey/18328newsML.txt"));
		P.documents.add(new Document("BH3", "BernardHickey/25315newsML.txt"));
		P.documents.add(new Document("BH4", "BernardHickey/25320newsML.txt"));
		P.documents.add(new Document("BH5", "BernardHickey/25344newsML.txt"));
		P.documents.add(new Document("BH6", "BernardHickey/30677newsML.txt"));
		P.documents.add(new Document("BH7", "BernardHickey/31892newsML.txt"));
		P.documents.add(new Document("BH8", "BernardHickey/33299newsML.txt"));
		P.documents.add(new Document("BH9", "BernardHickey/33301newsML.txt"));
		P.documents.add(new Document("BH10", "BernardHickey/33315newsML.txt"));
		P.documents.add(new Document("BH11", "BernardHickey/155005newsML.txt"));
		P.documents.add(new Document("BH12", "BernardHickey/164986newsML.txt"));
		P.documents.add(new Document("BH13", "BernardHickey/165020newsML.txt"));
		P.documents.add(new Document("BH14", "BernardHickey/174426newsML.txt"));
		P.documents.add(new Document("BH15", "BernardHickey/18328newsML.txt"));
		P.documents.add(new Document("BK1", "BenjaminKangLim/102444newsML.txt"));
		P.documents.add(new Document("BK2", "BenjaminKangLim/106762newsML.txt"));
		P.documents.add(new Document("BK3", "BenjaminKangLim/110733newsML.txt"));
		P.documents.add(new Document("BK4", "BenjaminKangLim/112125newsML.txt"));
		P.documents.add(new Document("BK5", "BenjaminKangLim/12228newsML.txt"));
		P.documents.add(new Document("BK6", "BenjaminKangLim/14439newsML.txt"));
		P.documents.add(new Document("BK7", "BenjaminKangLim/15741newsML.txt"));
		P.documents.add(new Document("BK8", "BenjaminKangLim/18363newsML.txt"));
		P.documents.add(new Document("BK9", "BenjaminKangLim/21575newsML.txt"));
		P.documents.add(new Document("BK10", "BenjaminKangLim/24300newsML.txt"));
		P.documents.add(new Document("BK11", "BenjaminKangLim/186173newsML.txt"));
		P.documents.add(new Document("BK12", "BenjaminKangLim/186199newsML.txt"));
		P.documents.add(new Document("BK13", "BenjaminKangLim/187423newsML.txt"));
		P.documents.add(new Document("BK14", "BenjaminKangLim/178518newsML.txt"));
		P.documents.add(new Document("BK15", "BenjaminKangLim/182490newsML.txt"));
		
		// start preprocessing
		P.action();
		
		// initialize matrix generation
		Term_Matrix M = new Term_Matrix();
		// start generating
		M.getAllWords(P.documents);
		M.getMatrix(P.documents);
		M.computeTFIDF(P.documents);
		int k = 5;
		double searchR = 0;
		double speed = 0.01;
		int e = 15;
		Cluster c = new Cluster(P.documents, M.tfidf, k, searchR, speed, e);
		HashMap<String, String> res = new HashMap<String, String>(c.cluster());
		for(Document d: P.documents) {
			System.out.println(d.name + "     " + res.get(d.name));
		}
		
		
		
		
		
		
		
		// --------------------------------------------------------------------------user-input TEST: USER INPUT DATASETS
		// ----------------------------------------------------------------------------real number datasets
		// **************************** comment this block of code if you want to test text user input datasets
		// **************************** and remember to go to Dolphin.java, Prey.java, Cluster.java to make certain changes specified in README and code comments
//		Scanner dirIn = new Scanner(System.in);
//		System.out.println("Only accept .csv / .data / .txt file");
//		System.out.println("Please do not directly enter QCM series of datasets here, since the category in those files are represented by four columns, which are processed using a special way");
//		System.out.println("(Directory of real number file: ");
//		String dir = dirIn.nextLine();
//		System.out.println("Delimiter:");
//		String delimiter = dirIn.nextLine();
//		System.out.println("Enter the index of column of the predicted category (starting from 0): ");
//		int catIdx = dirIn.nextInt();
//		System.out.println("Do you need to skip the first line of the dataset? ('yes' / 'no')");
//		Scanner jumpIn = new Scanner(System.in);
//		String jump = jumpIn.nextLine();
//		HashMap<double[], String> ans = new HashMap<double[], String>();
//		File file = new File(dir);
//		BufferedReader r = new BufferedReader(new FileReader(file));
//		ArrayList<double[]> rawList = new ArrayList<double[]>();
//		String line = "";
//		int lineNum = 0;
//		while((line = r.readLine()) != null) {
//			if(jump.equals("yes")) {
//				if(lineNum == 0) {
//					lineNum++;
//					continue;
//				}
//			}
//			String dataStr = line;
//			String[] dataStrArr = dataStr.split(delimiter);
//			String cat = dataStrArr[catIdx];
//			double[] rawPos = new double[dataStrArr.length - 1];
//			int dummy = 0;
//			for(int i = 0; i < rawPos.length; i++) {
//				if(i != catIdx) rawPos[i] = Double.parseDouble(dataStrArr[i + dummy]);
//				else dummy = 1;
//			}
//			rawList.add(rawPos);
//			ans.put(rawPos, cat);
//			lineNum++;
//		}
//		double[][] rawArr = new double[rawList.size()][rawList.get(0).length];
//		for(int i = 0; i < rawList.size(); i++) {
//			rawArr[i] = rawList.get(i);
//		}
//		Scanner kIn = new Scanner(System.in);
//		System.out.println("Enter the number of initial clusters: ");
//		int k = kIn.nextInt();
//		kIn.close();
//		double searchR = -1;
//		double speed = 0.3;
//		int e = 3;
//		System.out.println("Rememeber to Cluster.java, Dolphin.java, and Prey.java to change some parameters for text / real number datasets, cosine / euclidean measure");
//		Cluster c = new Cluster(rawArr, k, searchR, speed, e); // ---------------------------------------used for real number datasets
//		ArrayList<Prey[]> res = new ArrayList<Prey[]>(c.cluster()); // ---------------------------------------used for real number datasets
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.print(Arrays.toString(p.position) + ",");
//			}
//			System.out.println("\n");
//		}
//		for(Prey[] resArr: res) {
//			System.out.println("cluster");
//			for(Prey p: resArr) {
//				System.out.println(ans.get(p.raw));
//			}
//			System.out.println("\n");
//		}
		
		
		// -----------------------------------------------------------------------------user-input text datasets
		// **************************** comment this block of code if you want to test real number user input datasets
		// **************************** and remember to go to Dolphin.java, Prey.java, Cluster.java to make certain changes specified in README and code comments
//		BasicConfigurator.configure();
//		
//		// initialize preprocess
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Enter the file names, separate by ',': ");
//		String nameRaw = scan.nextLine();
//		String[] name = nameRaw.split(",");
//		System.out.println("Enter the directories, separate by ',' (should be in the same order as previously entered file names)");
//		String dirRaw = scan.nextLine();
//		String[] dir = dirRaw.split(",");
//		Preprocess P = new Preprocess();
//		for(int i = 0; i < name.length; i++) {
//			P.documents.add(new Document(name[i], dir[i]));
//		}
//		
//		// start preprocessing
//		P.action();
//		
//		// initialize matrix generation
//		Term_Matrix M = new Term_Matrix();
//		// start generating
//		M.getAllWords(P.documents);
//		M.getMatrix(P.documents);
//		M.computeTFIDF(P.documents);
//		int k = 3;
//		double searchR = 0;
//		double speed = 0.02;
//		int e = 6;
//		Cluster c = new Cluster(P.documents, M.tfidf, k, searchR, speed, e);
//		HashMap<String, String> res = new HashMap<String, String>(c.cluster());
//		for(Document d: P.documents) {
//			System.out.println(d.name + "     " + res.get(d.name));
//		}
	}

}
