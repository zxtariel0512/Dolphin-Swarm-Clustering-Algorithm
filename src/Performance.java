import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Evaluate the performance of the algorithm on testing real numbers file:
 * iris.data
 * breast_cancer.csv
 * cervical_cancer.csv
 * QCM3.csv, QCM6.csv, QCM7.csv, QCM10.csv, QCM12.csv, QCM.csv
 * HTRU_2.csv
 * Text documents:
 * C1, C4, C7 folders
 * AaronPressman, AlanCrosby, AlexanderSmith, BenjaminKangLim, BernardHickey folders
 * @author arielzhu
 * Clustering results for above datasets are saved in:
 * iris_performance_input
 * breastCancer_performance_input
 * cervicalCancer_performance_input
 * qcm3_performance_input, qcm6_performance_input, qcm7_performance_input, qcm10_performance_input, qcm12_performance_input, qcm_performance_input
 * htru_performance_input
 * author_performance_input
 * hw1_performance_input
 * Values measured:
 * confusion matrix
 * precision for each attribute
 * recall for each attribute
 * accuracy
 */
public class Performance {
	
	// ------------Since the predicted attribute, number of attributes, types of attributes, and so on, are different
	// ------------results text files, which are the input for performance measurements, are analyzed using different blocks of codes
	// ------------Please comment out the needed blocks of codes to check the validity of performance evaluations written on reports
	// ------------for each testing datasets
	
	public static void main(String[] args) throws Exception {
		
		// ----------------------------------------------------------------------IRIS
		HashMap<Integer, String> ans = new HashMap<Integer, String>();
		File file = new File("iris.data");
		BufferedReader r = new BufferedReader(new FileReader(file));
		String line = "";
		int idx = 0;
		while(!(line = r.readLine()).equals("")) {
			String dataStr = line.substring(0, 15);
			String cat = line.substring(21, line.length());
			ans.put(idx, cat);
			idx++;
		}
		r.close();
		HashMap<Integer, String> res = new HashMap<Integer, String>();
		File f = new File("iris_performance_input");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String l;
		String label = "";
		idx = 0;
		while((l = reader.readLine()) != null) {
			if(l.equals("cluster_setosa")){ 
				label = "setosa";
			}
			else if(l.equals("cluster_versicolor")) {
				label = "verisicolor";
			}
			else if(l.equals("cluster_virginica")) {
				label = "virginica";
			}
			else {
				res.put(idx, label);
				idx++;
			}
		}
		reader.close();
		
		// confusion matrix
		int set_set = 0;
		int set_vir = 0;
		int set_ver = 0;
		int vir_set = 0;
		int vir_vir = 0;
		int vir_ver = 0;
		int ver_set = 0;
		int ver_vir = 0;
		int ver_ver = 0;
		int total = 0;
		for(int i: res.keySet()) {
			String answer = ans.get(i);
			String result = res.get(i);
			if(!ans.containsKey(i)) {
				continue;
			}
			total++;
			if(!res.containsKey(i)) continue;
			if(answer.equals("setosa")) {
				if(result.equals("setosa")) set_set++;
				else if(result.equals("virginica")) set_vir++;
				else set_ver++;
			}
			else if(answer.equals("virginica")) {
				if(result.equals("setosa")) vir_set++;
				else if(result.equals("virginica")) vir_vir++;
				else vir_ver++;
			}
			else {
				if(result.equals("setosa")) ver_set++;
				else if(result.equals("virginica")) ver_vir++;
				else ver_ver++;
			}
		}
		System.out.println("                  Predicted Setosa          Predicted Virginica          Predicted Verisicolor");
		System.out.println("Actual Setosa          " + set_set + "                            " + set_vir + "                             " + set_ver);
		System.out.println("Actual Virginica       " + vir_set + "                            " + vir_vir + "                             " + vir_ver);
		System.out.println("Actual Verisicolor     " + ver_set + "                            " + ver_vir + "                             " + ver_ver);
		double accuracy = (double)(set_set + vir_vir + ver_ver) / total;
		double precision_ser = (double)set_set / (set_set + vir_set + ver_set);
		double precision_vir = (double)vir_vir / (set_vir + vir_vir + ver_vir);
		double precision_ver = (double)ver_ver / (set_set + vir_set + ver_set);
		double recall_ser = (double)set_set / 50;
		double recall_vir = (double)vir_vir / 50;
		double recall_ver = (double)ver_ver / 50;
		System.out.println();
		System.out.println("Accuracy: " + accuracy);
		System.out.println("Precision of 'setosa': " + precision_ser);
		System.out.println("Precision of 'Virginica': " + precision_vir);
		System.out.println("Precision of 'Verisicolor': " + precision_ver);
		System.out.println("Recall of 'setosa': " + recall_ser);
		System.out.println("Recall of 'Virginica': " + recall_vir);
		System.out.println("Recall of 'Verisicolor': " + recall_ver);
		
		
		// ---------------------------------------------------------------------------------------------BREAST_CANCER
//		HashMap<Integer, String> ans = new HashMap<Integer, String>();
//		HashMap<Integer, String> res = new HashMap<Integer, String>();
//		File f = new File("breastCancer_performance_input");
//		BufferedReader reader = new BufferedReader(new FileReader(f));
//		String l;
//		String label = "";
//		int idx = 0;
//		while((l = reader.readLine()) != null) {
//			if(l.equals("clusterM")){ 
//				label = "M";
//			}
//			else if(l.equals("clusterB")) {
//				label = "B";
//			}
//			else {
//				res.put(idx, label);
//				ans.put(idx, l);
//				idx++;
//			}
//		}
//		reader.close();
//		
//		// confusion matrix
//		int MM = 0;
//		int MB = 0;
//		int BM = 0;
//		int BB = 0;
//		int total = 0;
//		int totalM = 0;
//		int totalB = 0;
//		for(int i: res.keySet()) {
//			String answer = ans.get(i);
//			String result = res.get(i);
//			if(!ans.containsKey(i)) {
//				continue;
//			}
//			total++;
//			if(!res.containsKey(i)) continue;
//			if(answer.equals("M")) {
//				if(result.equals("M")) MM++;
//				else MB++;
//				totalM++;
//			}
//			else if(answer.equals("B")) {
//				if(result.equals("M")) BM++;
//				else BB++;
//				totalB++;
//			}
//		}
//		System.out.println("                  M            B");
//		System.out.println("Actual M         " + MM + "          " + MB);
//		System.out.println("Actual B         " + BM + "          " + BB );
//		double accuracy = (double)(MM + BB) / total;
//		double precisionM = (double)MM / (MM + BM);
//		double precisionB = (double)BB / (BB + MB);
//		double recallM = (double)MM / totalM;
//		double recallB = (double)BB / totalB;
//		System.out.println();
//		System.out.println("Accuracy: " + accuracy);
//		System.out.println("Precision of 'Malignant': " + precisionM);
//		System.out.println("Precision of 'Benigh': " + precisionB);
//		System.out.println("Recall of 'Malignant': " + recallM);
//		System.out.println("Recall of 'Benigh': " + recallB);
		
		
		// ----------------------------------------------------------------------------------------CERVICAL CANCER
//		HashMap<Integer, String> ans = new HashMap<Integer, String>();
//		HashMap<Integer, String> res = new HashMap<Integer, String>();
//		File f = new File("cervicalCancer_performance_input");
//		BufferedReader reader = new BufferedReader(new FileReader(f));
//		String l;
//		String label = "";
//		int idx = 0;
//		while((l = reader.readLine()) != null) {
//			if(l.equals("cluster1")){ 
//				label = "1";
//			}
//			else if(l.equals("cluster0")) {
//				label = "0";
//			}
//			else {
//				res.put(idx, label);
//				ans.put(idx, l);
//				idx++;
//			}
//		}
//		reader.close();
//		
//		// confusion matrix
//		int _00 = 0;
//		int _01 = 0;
//		int _10 = 0;
//		int _11 = 0;
//		int total = 0;
//		int total0 = 0;
//		int total1 = 0;
//		for(int i: res.keySet()) {
//			String answer = ans.get(i);
//			String result = res.get(i);
//			if(!ans.containsKey(i)) {
//				continue;
//			}
//			total++;
//			if(!res.containsKey(i)) continue;
//			if(answer.equals("0")) {
//				if(result.equals("0")) _00++;
//				else _01++;
//				total0++;
//			}
//			else if(answer.equals("1")) {
//				if(result.equals("0")) _10++;
//				else _11++;
//				total1++;
//			}
//		}
//		System.out.println("                         NO cancer(0)           HAS cancer(1)");
//		System.out.println("Actual NO cancer(0)         " + _00 + "                    " + _01);
//		System.out.println("Actual HAS cancer(1)         " + _10 + "                    " + _11 );
//		double accuracy = (double)(_00 + _11) / total;
//		double precision0 = (double)_00 / (_00 + _10);
//		double precision1 = (double)_11 / (_11 + _10);
//		double recall0 = (double)_00 / total0;
//		double recall1 = (double)_11 / total1;
//		System.out.println();
//		System.out.println("Accuracy: " + accuracy);
//		System.out.println("Precision of 'HAS cancer'(0)': " + precision0);
//		System.out.println("Precision of 'NO cancer(1)': " + precision1);
//		System.out.println("Recall of 'HAS cancer'(0)': " + recall0);
//		System.out.println("Recall of 'NO cancer(1)': " + recall1);
		
		
		// -----------------------------------------------------------------------------------QCM3, 6, 7, 10, 12, all
//		HashMap<Integer, String> ans = new HashMap<Integer, String>();
//		HashMap<Integer, String> res = new HashMap<Integer, String>();
//		File f = new File("qcm_performance_input"); //---------------------------change the file name to the qcm file that you want to check
//		BufferedReader reader = new BufferedReader(new FileReader(f));
//		String l;
//		String label = "";
//		int idx = 0;
//		while((l = reader.readLine()) != null) {
//			if(l.equals("cluster1-Octanol")){ 
//				label = "1-Octanol";
//			}
//			else if(l.equals("cluster1-Propanol")) {
//				label = "1-Propanol";
//			}
//			else if(l.equals("cluster2-Butanol")) label = "2-Butanol";
//			else if(l.equals("cluster2-propanol")) label = "2-propanol";
//			else if(l.equals("cluster2-isobutanol")) label = "2-isobutanol";
//			else {
//				res.put(idx, label);
//				ans.put(idx, l);
//				idx++;
//			}
//		}
//		reader.close();
//		
//		// confusion matrix
//		int oct1_oct1 = 0;
//		int oct1_pro1 = 0;
//		int oct1_but2 = 0;
//		int oct1_pro2 = 0;
//		int oct1_iso2 = 0;
//		int pro1_oct1 = 0;
//		int pro1_pro1 = 0;
//		int pro1_but2 = 0;
//		int pro1_pro2 = 0;
//		int pro1_iso2 = 0;
//		int but2_oct1 = 0;
//		int but2_pro1 = 0;
//		int but2_but2 = 0;
//		int but2_pro2 = 0;
//		int but2_iso2 = 0;
//		int pro2_oct1 = 0;
//		int pro2_pro1 = 0;
//		int pro2_but2 = 0;
//		int pro2_pro2 = 0;
//		int pro2_iso2 = 0;
//		int iso2_oct1 = 0;
//		int iso2_pro1 = 0;
//		int iso2_but2 = 0;
//		int iso2_pro2 = 0;
//		int iso2_iso2 = 0;
//		int total = 0;
//		int totaloct1 = 0;
//		int totalpro1 = 0;
//		int totalbut2 = 0;
//		int totalpro2 = 0;
//		int totaliso2 = 0;
//		for(int i: res.keySet()) {
//			String answer = ans.get(i);
//			String result = res.get(i);
//			if(!ans.containsKey(i)) {
//				continue;
//			}
//			total++;
//			if(!res.containsKey(i)) continue;
//			if(answer.equals("1-Octanol")) {
//				if(result.equals("1-Octanol")) oct1_oct1++;
//				else if(result.equals("1-Propanol")) oct1_pro1++;
//				else if(result.equals("2-Butanol")) oct1_but2++;
//				else if(result.equals("2-propanol")) oct1_pro2++;
//				else oct1_iso2++;
//				totaloct1++;
//			}
//			else if(answer.equals("1-Propanol")) {
//				if(result.equals("1-Octanol")) pro1_oct1++;
//				else if(result.equals("1-Propanol")) pro1_pro1++;
//				else if(result.equals("2-Butanol")) pro1_but2++;
//				else if(result.equals("2-propanol")) pro1_pro2++;
//				else pro1_iso2++;
//				totalpro1++;
//			}
//			else if(answer.equals("2-Butanol")) {
//				if(result.equals("1-Octanol")) but2_oct1++;
//				else if(result.equals("1-Propanol")) but2_pro1++;
//				else if(result.equals("2-Butanol")) but2_but2++;
//				else if(result.equals("2-propanol")) but2_pro2++;
//				else but2_iso2++;
//				totalbut2++;
//			}
//			else if(answer.equals("2-propanol")) {
//				if(result.equals("1-Octanol")) pro2_oct1++;
//				else if(result.equals("1-Propanol")) pro2_pro1++;
//				else if(result.equals("2-Butanol")) pro2_but2++;
//				else if(result.equals("2-propanol")) pro2_pro2++;
//				else pro2_iso2++;
//				totalpro2++;
//			}
//			else {
//				if(result.equals("1-Octanol")) iso2_oct1++;
//				else if(result.equals("1-Propanol")) iso2_pro1++;
//				else if(result.equals("2-Butanol")) iso2_but2++;
//				else if(result.equals("2-propanol")) iso2_pro2++;
//				else iso2_iso2++;
//				totaliso2++;
//			}
//		}
//		System.out.println("                         1-Octanol           1-Propanol           2-Butanol           2-propanol           2-isobutanol");
//		System.out.println("Actual 1-Octanol          " + oct1_oct1 + "                    " + oct1_pro1 + "                    " + oct1_but2 + "                    " + oct1_pro2 + "                    " + oct1_iso2);
//		System.out.println("Actual 1-Propanol         " + pro1_oct1 + "                    " + pro1_pro1 + "                    " + pro1_but2 + "                    " + pro1_pro2 + "                    " + pro1_iso2 );
//		System.out.println("Actual 2-Butanol          " + but2_oct1 + "                    " + but2_pro1 + "                    " + but2_but2 + "                    " + but2_pro2 + "                    " + but2_iso2 );
//		System.out.println("Actual 2-propanol         " + pro2_oct1 + "                    " + pro2_pro1 + "                    " + pro2_but2 + "                    " + pro2_pro2 + "                    " + pro2_iso2 );
//		System.out.println("Actual 2-isobutanol       " + iso2_oct1 + "                    " + iso2_pro1 + "                    " + iso2_but2 + "                    " + iso2_pro2 + "                    " + iso2_iso2 );
//		double accuracy = (double)(oct1_oct1 + pro1_pro1 + but2_but2 + pro2_pro2 + iso2_iso2) / total;
//		double precision_oct1 = (double)oct1_oct1 / (oct1_oct1 + pro1_oct1 + but2_oct1 + pro2_oct1 + iso2_oct1);
//		double precision_pro1 = (double)pro1_pro1 / (oct1_pro1 + pro1_pro1 + but2_pro1 + pro2_pro1 + iso2_pro1);
//		double precision_but2 = (double)but2_but2 / (oct1_but2 + pro1_but2 + but2_but2 + pro2_but2 + iso2_but2);
//		double precision_pro2 = (double)pro2_pro2 / (oct1_pro2 + pro1_pro2 + but2_pro2 + pro2_pro2 + iso2_pro2);
//		double precision_iso2 = (double)iso2_iso2 / (oct1_iso2 + pro1_iso2 + but2_iso2 + pro2_iso2 + iso2_iso2);
//		double recall_oct1 = (double) oct1_oct1 / totaloct1;
//		double recall_pro1 = (double) pro1_pro1 / totalpro1;
//		double recall_but2 = (double) but2_but2 / totalbut2;
//		double recall_pro2 = (double) pro2_pro2 / totalpro2;
//		double recall_iso2 = (double) iso2_iso2 / totaliso2;
//		System.out.println();
//		System.out.println("Accuracy: " + accuracy);
//		System.out.println("Precision of 1-Octanol: " + precision_oct1);
//		System.out.println("Precision of 1-Propanol: " + precision_pro1);
//		System.out.println("Precision of 2-Butanol: " + precision_but2);
//		System.out.println("Precision of 2-propanol: " + precision_pro2);
//		System.out.println("Precision of 2-isobutanol: " + precision_iso2);
//		System.out.println("Recall of 1-Octanol: " + recall_oct1);
//		System.out.println("Recall of 1-Propanol: " + recall_pro1);
//		System.out.println("Recall of 2-Butanol: " + recall_but2);
//		System.out.println("Recall of 2-propanol: " + recall_pro2);
//		System.out.println("Recall of 2-isobutanol: " + recall_iso2);
		
		
		// ----------------------------------------------------------------------------------------------HTRU
//		HashMap<Integer, String> ans = new HashMap<Integer, String>();
//		HashMap<Integer, String> res = new HashMap<Integer, String>();
//		File f = new File("htru_performance_input");
//		BufferedReader reader = new BufferedReader(new FileReader(f));
//		String l;
//		String label = "";
//		int idx = 0;
//		while((l = reader.readLine()) != null) {
//			if(l.equals("cluster1")){ 
//				label = "1";
//			}
//			else if(l.equals("cluster0")) {
//				label = "0";
//			}
//			else {
//				res.put(idx, label);
//				ans.put(idx, l);
//				idx++;
//			}
//		}
//		reader.close();
//		
//		// confusion matrix
//		int _00 = 0;
//		int _01 = 0;
//		int _10 = 0;
//		int _11 = 0;
//		int total = 0;
//		int total0 = 0;
//		int total1 = 0;
//		for(int i: res.keySet()) {
//			String answer = ans.get(i);
//			String result = res.get(i);
//			if(!ans.containsKey(i)) {
//				continue;
//			}
//			total++;
//			if(!res.containsKey(i)) continue;
//			if(answer.equals("0")) {
//				if(result.equals("0")) _00++;
//				else _01++;
//				total0++;
//			}
//			else if(answer.equals("1")) {
//				if(result.equals("0")) _10++;
//				else _11++;
//				total1++;
//			}
//		}
//		System.out.println("HTRU");
//		System.out.println("                         Class 0           Class 1");
//		System.out.println("Actual Class 0            " + _00 + "                " + _01);
//		System.out.println("Actual Class 1            " + _10 + "                  " + _11 );
//		double accuracy = (double)(_00 + _11) / total;
//		double precision0 = (double)_00 / (_00 + _10);
//		double precision1 = (double)_11 / (_11 + _10);
//		double recall0 = (double)_00 / total0;
//		double recall1 = (double)_11 / total1;
//		System.out.println();
//		System.out.println("Accuracy: " + accuracy);
//		System.out.println("Precision of Class 0: " + precision0);
//		System.out.println("Precision of Class 1: " + precision1);
//		System.out.println("Recall of Class 0: " + recall0);
//		System.out.println("Recall of Class 1: " + recall1);
		
		
		
		// -------------------------------------------------------------------------------------------AUTHOR
//		HashMap<String, String> ans = new HashMap<String, String>();
//		HashMap<String, String> res = new HashMap<String, String>();
//		File f = new File("author_performance_input");
//		BufferedReader reader = new BufferedReader(new FileReader(f));
//		String l;
//		String label = "";
//		int idx = 0;
//		while((l = reader.readLine()) != null) {
//			String[] dataStr = l.split("     ");
//			String name = dataStr[0];
//			String cat;
//			if(dataStr[1].equals("Cluster 0")) cat = "AP";
//			else if(dataStr[1].equals("Cluster 1")) cat = "AC";
//			else if(dataStr[1].equals("Cluster 2")) cat = "BH";
//			else if(dataStr[1].equals("Cluster 4")) cat = "BK";
//			else cat = "AS";
//			res.put(name, cat);
//			String actual = dataStr[0].substring(0, 2);
//			ans.put(name, actual);
//		}
//		reader.close();
//		
//		// confusion matrix
//		int APAP = 0;
//		int APAC = 0;
//		int APBH = 0;
//		int APBK = 0;
//		int APAS = 0;
//		int ACAP = 0;
//		int ACAC = 0;
//		int ACBH = 0;
//		int ACBK = 0;
//		int ACAS = 0;
//		int BHAP = 0;
//		int BHAC = 0;
//		int BHBH = 0;
//		int BHBK = 0;
//		int BHAS = 0;
//		int BKAP = 0;
//		int BKAC = 0;
//		int BKBH = 0;
//		int BKBK = 0;
//		int BKAS = 0;
//		int ASAP = 0;
//		int ASAC = 0;
//		int ASBH = 0;
//		int ASBK = 0;
//		int ASAS = 0;
//		int total = 0;
//		int totalAP = 0;
//		int totalAC = 0;
//		int totalBH = 0;
//		int totalBK = 0;
//		int totalAS = 0;
//		for(String name: res.keySet()) {
//			String answer = ans.get(name);
//			String result = res.get(name);
//			if(!ans.containsKey(name)) {
//				continue;
//			}
//			total++;
//			if(!res.containsKey(name)) continue;
//			if(answer.equals("AP")) {
//				if(result.equals("AP")) APAP++;
//				else if(result.equals("AC")) APAC++;
//				else if(result.equals("BH")) APBH++;
//				else if(result.equals("BK")) APBK++;
//				else APAS++;
//				totalAP++;
//			}
//			else if(answer.equals("AC")) {
//				if(result.equals("AP")) ACAP++;
//				else if(result.equals("AC")) ACAC++;
//				else if(result.equals("BH")) ACBH++;
//				else if(result.equals("BK")) ACBK++;
//				else ACAS++;
//				totalAC++;
//			}
//			else if(answer.equals("BH")) {
//				if(result.equals("AP")) BHAP++;
//				else if(result.equals("AC")) BHAC++;
//				else if(result.equals("BH")) BHBH++;
//				else if(result.equals("BK")) BHBK++;
//				else BHAS++;
//				totalBH++;
//			}
//			else if(answer.equals("BK")) {
//				if(result.equals("AP")) BKAP++;
//				else if(result.equals("AC")) BKAC++;
//				else if(result.equals("BH")) BKBH++;
//				else if(result.equals("BK")) BKBK++;
//				else BKAS++;
//				totalBK++;
//			}
//			else{
//				if(result.equals("AP")) ASAP++;
//				else if(result.equals("AC")) ASAC++;
//				else if(result.equals("BH")) ASBH++;
//				else if(result.equals("BK")) ASBK++;
//				else ASAS++;
//				totalAS++;
//			}
//		}
//		System.out.println("                         Aaron Pressman           Alan Crosby           Bernard Hickey           Benjamin Kang Lim           Alexander Smith");
//		System.out.println("Actual Aaron Pressman          " + APAP + "                   " + APAC + "                       " + APBH+ "                           " + APBK+ "                       " + APAC);
//		System.out.println("Actual Alan Crosby             " + ACAP+ "                   " + ACAC+ "                       " + ACBH + "                           " + ACBK + "                       " + ACAS );
//		System.out.println("Actual Bernard Hickey          " + BHAP + "                    " + BHAC + "                      " + BHBH + "                           " + BHBK + "                       " + BHAS );
//		System.out.println("Actual Benjamin Kang Lim       " + BKAP + "                    " + BKAC + "                       " + BKBH + "                          " + BKBK + "                       " + BKAS );
//		System.out.println("Actual Alexander Smith         " + ASAP + "                    " + ASAC + "                       " + ASBH + "                           " + ASBK + "                       " + ASAS );
//		double accuracy = (double)(APAP + ACAC + BHBH + BKBK + ASAS) / total;
//		double precisionAP = (double)APAP / (APAP + ACAP + BHAP + BKAP + ASAP);
//		double precisionAC = (double)ACAC / (ACAC + APAC + BHAC + BKAC + ASAC);
//		double precisionBH = (double)BHBH / (ACBH + APBH + BHBH + BKBH + ASBH);
//		double precisionBK = (double)BKBK / (ACBK + APBK + BHBK + BKBK + ASBK);
//		double precisionAS = (double)ASAS / (ACAS + APAS + BHAS + BKAS + ASAS);
//		double recallAP = (double) APAP / totalAP;
//		double recallAC = (double) ACAC / totalAC;
//		double recallBH = (double) BHBH / totalBH;
//		double recallBK = (double) BKBK / totalBK;
//		double recallAS = (double) ASAS / totalAS;
//		System.out.println();
//		System.out.println("Accuracy: " + accuracy);
//		System.out.println("Precision of Aaron Pressman: " + precisionAP);
//		System.out.println("Precision of Alan Crosby: " + precisionAC);
//		System.out.println("Precision of Bernard Hickey: " + precisionBH);
//		System.out.println("Precision of Benjamin Kang Lim: " + precisionBK);
//		System.out.println("Precision of Alexander Smith: " + precisionAS);
//		System.out.println("Recall of Aaron Pressman: " + recallAP);
//		System.out.println("Recall of Alan Crosby: " + recallAC);
//		System.out.println("Recall of Bernard Hickey: " + recallBH);
//		System.out.println("Recall of Benjamin Kang Lim: " + recallBK);
//		System.out.println("Recall of Alexander Smith: " + recallAS);
		
		
		// ----------------------------------------------------------------------------------------HW1
//		HashMap<String, String> ans = new HashMap<String, String>();
//		HashMap<String, String> res = new HashMap<String, String>();
//		File f = new File("hw1_performance_input");
//		BufferedReader reader = new BufferedReader(new FileReader(f));
//		String l;
//		String label = "";
//		int idx = 0;
//		while((l = reader.readLine()) != null) {
//			String[] dataStr = l.split("     ");
//			String name = dataStr[0];
//			String cat;
//			if(dataStr[1].equals("Cluster 0")) cat = "C7";
//			else if(dataStr[1].equals("Cluster 1")) cat = "C4";
//			else cat = "C1";
//			res.put(name, cat);
//			String actual = dataStr[0].substring(0, 2);
//			ans.put(name, actual);
//		}
//		reader.close();
//		
//		// confusion matrix
//		int c11 = 0;
//		int c14 = 0;
//		int c17 = 0;
//		int c41 = 0;
//		int c44 = 0;
//		int c47 = 0;
//		int c71 = 0;
//		int c74 = 0;
//		int c77 = 0;
//		int total = 0;
//		int totalc1 = 0;
//		int totalc4 = 0;
//		int totalc7 = 0;
//		for(String name: res.keySet()) {
//			String answer = ans.get(name);
//			String result = res.get(name);
//			if(!ans.containsKey(name)) {
//				continue;
//			}
//			total++;
//			if(!res.containsKey(name)) continue;
//			if(answer.equals("C1")) {
//				if(result.equals("C1")) c11++;
//				else if(result.equals("C4")) c14++;
//				else if(result.equals("C7")) c17++;
//				totalc1++;
//			}
//			else if(answer.equals("C4")) {
//				if(result.equals("C1")) c41++;
//				else if(result.equals("C4")) c44++;
//				else if(result.equals("C7")) c47++;
//				totalc4++;
//			}
//			else {
//				if(result.equals("C1")) c71++;
//				else if(result.equals("C4")) c74++;
//				else if(result.equals("C7")) c77++;
//				totalc7++;
//			}
//		}
//		System.out.println("                                        C1 (airline saftty)           C4 (hoof  and  mouth  disease)           C7 (mortgage rate)");
//		System.out.println("Actual C1 (airline saftty)                        " + c11 + "                           " + c14 + "                                     " + c17);
//		System.out.println("Actual C4 (hoof  and  mouth  disease)             " + c41+ "                           " + c44+ "                                     " + c47);
//		System.out.println("Actual C7 (mortgage rate)                         " + c71 + "                           " + c74 + "                                     " + c77);
//		double accuracy = (double)(c11 + c44 + c77) / total;
//		double precision1 = (double)c11 / (c11 + c41 + c71);
//		double precision4 = (double)c44 / (c14 + c44 + c74);
//		double precision7 = (double)c77 / (c17 + c47 + c77);
//		double recall1 = (double) c11 / totalc1;
//		double recall4 = (double) c44 / totalc4;
//		double recall7 = (double) c77 / totalc7;
//		System.out.println();
//		System.out.println("Accuracy: " + accuracy);
//		System.out.println("Precision of C1 (airline saftty): " + precision1);
//		System.out.println("Precision of C4 (hoof  and  mouth  disease): " + precision4);
//		System.out.println("Precision of C7 (mortgage rate): " + precision7);
//		System.out.println("Recall of C1 (airline saftty): " + recall1);
//		System.out.println("Recall of C4 (hoof  and  mouth  disease): " + recall4);
//		System.out.println("Recall of C7 (mortgage rate): " + recall7);
	}
	

}
