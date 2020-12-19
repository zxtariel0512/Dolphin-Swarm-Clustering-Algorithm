import java.util.*;
import java.lang.*;
import java.io.*; 

public class Term_Matrix {
	
	public String[] all_words;
	public int[][] matrix;
	public Set<String> wordSet;
	public double[][] tfidf;
	
	public Term_Matrix() {
		this.wordSet = new HashSet<String>();
	}
	
	/**
	 * Get all words appeared in all files
	 * @param documents
	 */
	public void getAllWords(ArrayList<Document> documents) {
		ArrayList<String> all_words = new ArrayList<String>();
		for(int i = 0; i < documents.size(); i++) {
			Document d = documents.get(i);
			for(int j = 0; j < d.token.size(); j++) {
				if(! wordSet.contains(d.token.get(j))) {
					wordSet.add(d.token.get(j));
					all_words.add(d.token.get(j));
				}				
			}
		}
		this.all_words = new String[all_words.size()];
		for(int i = 0; i < all_words.size(); i++) {
			this.all_words[i] = all_words.get(i);
		}
	}
	
	public void getMatrix(ArrayList<Document> documents) {
		this.matrix = new int[documents.size()][this.all_words.length];
		for(int i = 0; i < documents.size(); i++) {
			for(int j = 0; j < this.all_words.length; j++) {
				if(documents.get(i).terms.containsKey(all_words[j])) {
					this.matrix[i][j] = documents.get(i).terms.get(all_words[j]);
				}
				else {
					this.matrix[i][j] = 0;
				}
			}
		}
	}
	
	public void computeTFIDF(ArrayList<Document> documents) {
		this.tfidf = new double[this.matrix.length][this.matrix[0].length];
		int numDoc = documents.size();
		for(int i = 0; i < this.matrix.length; i++) {
			for(int j = 0; j < this.matrix[i].length; j++) {
//				System.out.print(this.matrix[i][j] + "   ");
//				System.out.print(documents.get(i).token.size() + "\n");
				double tf = (double)this.matrix[i][j] / (double)documents.get(i).token.size();
				int numContain = 0;
				for(int k = 0; k < documents.size(); k++) {
					if(this.matrix[k][j] > 0) numContain ++;
				}
				double idf = Math.log((double)numDoc / (double)numContain);
				this.tfidf[i][j] = tf * idf;
			}
		}
	}
	
	public void keywords(ArrayList<Document> documents, int C1, int C4, int C7, int keys) {
		// C1
		ArrayList<String> c1key = new ArrayList<String>();
		HashMap<Double, ArrayList<String>> map1 = new HashMap<Double, ArrayList<String>>();
		double[] c1Matrix = new double[this.tfidf[0].length];
		for(int i = 0; i < this.tfidf[0].length; i++) {
			double currTFIDF = 0;
			for(int j = 0; j < C1; j++) {
				currTFIDF = (double) currTFIDF + (double)this.tfidf[j][i];
			}
			c1Matrix[i] = currTFIDF;
//			System.out.println(currTFIDF);
			if(map1.containsKey(currTFIDF)) {
				ArrayList<String> stringAL = map1.get(currTFIDF);
				stringAL.add(this.all_words[i]);
				map1.put(currTFIDF, stringAL);
			}
			else{
				ArrayList<String> stringAL = new ArrayList<String>();
				stringAL.add(this.all_words[i]);
				map1.put(currTFIDF, stringAL);
			}
		}
		Arrays.sort(c1Matrix);
		int currSize = 0;
		int idx = 1;
		while(currSize <= keys) {
			c1key.addAll(map1.get(c1Matrix[c1Matrix.length - idx]));
			currSize += map1.get(c1Matrix[c1Matrix.length - idx]).size();
			idx ++;
		}
		// C4
		ArrayList<String> c4key = new ArrayList<String>();
		HashMap<Double, ArrayList<String>> map4 = new HashMap<Double, ArrayList<String>>();
		double[] c4Matrix = new double[this.tfidf[0].length];
		for(int i = 0; i < this.tfidf[0].length; i++) {
			double currTFIDF = 0;
			for(int j = C1; j < C1 + C4 - 1; j++) {
				currTFIDF = (double) currTFIDF + (double)this.tfidf[j][i];
			}
			c4Matrix[i] = currTFIDF;
//			System.out.println(currTFIDF);
			if(map4.containsKey(currTFIDF)) {
				ArrayList<String> stringAL = map4.get(currTFIDF);
				stringAL.add(this.all_words[i]);
				map4.put(currTFIDF, stringAL);
			}
			else{
				ArrayList<String> stringAL = new ArrayList<String>();
				stringAL.add(this.all_words[i]);
				map4.put(currTFIDF, stringAL);
			}
		}
		Arrays.sort(c4Matrix);
		currSize = 0;
		idx = 1;
		while(currSize <= keys) {
			c4key.addAll(map4.get(c4Matrix[c4Matrix.length - idx]));
			currSize += map4.get(c4Matrix[c4Matrix.length - idx]).size();
			idx ++;
		}
		// C7
		ArrayList<String> c7key = new ArrayList<String>();
		HashMap<Double, ArrayList<String>> map7 = new HashMap<Double, ArrayList<String>>();
		double[] c7Matrix = new double[this.tfidf[0].length];
		for(int i = 0; i < this.tfidf[0].length; i++) {
			double currTFIDF = 0;
			for(int j = C1 + C4; j < C1 + C4 + C7 - 1; j++) {
				currTFIDF = (double) currTFIDF + (double)this.tfidf[j][i];
			}
			c7Matrix[i] = currTFIDF;
//			System.out.println(currTFIDF);
			if(map7.containsKey(currTFIDF)) {
				ArrayList<String> stringAL = map7.get(currTFIDF);
				stringAL.add(this.all_words[i]);
				map7.put(currTFIDF, stringAL);
			}
			else{
				ArrayList<String> stringAL = new ArrayList<String>();
				stringAL.add(this.all_words[i]);
				map7.put(currTFIDF, stringAL);
			}
		}
		Arrays.sort(c7Matrix);
		currSize = 0;
		idx = 1;
		while(currSize <= keys) {
			c7key.addAll(map7.get(c7Matrix[c7Matrix.length - idx]));
			currSize += map7.get(c7Matrix[c7Matrix.length - idx]).size();
			idx ++;
		}
		// write
		try {
			BufferedWriter myWriter = new BufferedWriter(new FileWriter("topics.txt"));
			ArrayList<String> written1 = new ArrayList<String>();
		    myWriter.write("Keywords for C1:\n");
		    for(int i = 0; i < c1key.size(); i++) {
		    	if(i < c1key.size() - 1 && !written1.contains(c1key.get(i).toLowerCase())) {
		    		myWriter.write(c1key.get(i) + ", ");
		    		written1.add(c1key.get(i));
		    	}
		    	else if(i == c1key.size() - 1 && !written1.contains(c1key.get(i).toLowerCase())) myWriter.write(c1key.get(i) + "\n");
		    }
		    myWriter.write("\n");
		    ArrayList<String> written2 = new ArrayList<String>();
		    myWriter.write("Keywords for C4:\n");
		    for(int i = 0; i < c4key.size(); i++) {
		    	if(i < c4key.size() - 1 && !written2.contains(c4key.get(i).toLowerCase())) {
		    		myWriter.write(c4key.get(i) + ", ");
		    		written2.add(c4key.get(i));
		    	}
		    	else if(i == c4key.size() - 1 && !written2.contains(c4key.get(i).toLowerCase()))  myWriter.write(c4key.get(i) + "\n");
		    }
		    myWriter.write("\n");
		    ArrayList<String> written3 = new ArrayList<String>();
		    myWriter.write("Keywords for C7:\n");
		    for(int i = 0; i < c7key.size(); i++) {
		    	if(i < c7key.size() - 1 && !written3.contains(c7key.get(i).toLowerCase())) {
		    		myWriter.write(c7key.get(i) + ", ");
		    		written3.add(c7key.get(i));
		    	}
		    	else if(i == c7key.size() - 1 && !written3.contains(c7key.get(i).toLowerCase()))  myWriter.write(c7key.get(i) + "\n");
		    }
		    myWriter.close();
		} catch(Exception e) {
			System.out.println("Errors in writing to files!");
			e.printStackTrace();
		}
	}
}
