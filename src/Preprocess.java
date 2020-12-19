import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
/**
 * Class to preprocess each data file
 * 1. get content as a single string
 * 2. remove stop words (but keep capital letters
 * 3. lemmatize (and change non-NER words to lower case
 * 4. use 2-gram and 3-gram to merge
 * 5. generate term map
 * @author arielzhu
 *
 */
public class Preprocess {
	
	/**
	 * a list of all Document objects
	 */
	public ArrayList<Document> documents;
	/**
	 * an array of stop words
	 */
	public String[] stop;
	/**
	 * a set integer used for 2-gram and 3-gram noun group merge function. 
	 */
	public int frequency;
	
	/**
	 * Constructor
	 */
	public Preprocess() {
		this.documents = new ArrayList<Document>();
		this.frequency = 3;
	}
	
	/**
	 * Start to do preprocess
	 * @throws Exception
	 */
	public void action() throws Exception {
		for(int i = 0; i < this.documents.size(); i++) {
			this.stopWords();
			Document currD = this.documents.get(i);
			currD.getContent();
			currD.removeStopWords(stop);
			currD.lemmatize();
			currD.merge(this.frequency);
			currD.getTerms();
		}
	}
	
	/**
	 * Get stops words from 'stopwords.txt' file
	 * @throws Exception
	 */
	public void stopWords() throws Exception{
		File file = new File("stopwords.txt");
		BufferedReader r = new BufferedReader(new FileReader(file));
		String line = "";
		ArrayList<String> words = new ArrayList<String>();
		while((line = r.readLine()) != null) {
			words.add(line);
		}
		this.stop = new String[words.size()];
		for(int i = 0; i < words.size(); i++) {
			this.stop[i] = words.get(i);
		}
	}

}
