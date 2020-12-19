
import java.io.*;
import java.util.*;

/**
 * Use for text datasets
 * @author arielzhu
 */
public class Document {
	
	/**
	 * name of document object
	 */
	public String name;
	/**
	 * director of file
	 */
	public String dir;
	/**
	 * contents of the file
	 */
	public String content;
	/**
	 * token of the documents after tokenize
	 */
	public ArrayList<String> token;
	/**
	 * frequency of each meaningful words, i.e., not stop words, no punctuation
	 */
	public HashMap<String, Integer> terms;
	
	/**
	 * Constructor
	 * @param name
	 * @param dir
	 */
	public Document(String name, String dir) {
		this.name = name;
		this.dir = dir;
		this.token = new ArrayList<String>();
		this.terms = new HashMap<String, Integer>();
	}
	
	/**
	 * Open the text file, and store content as a single string.
	 * @throws Exception
	 */
	public void getContent() throws Exception {
		File file = new File(this.dir);
		BufferedReader r = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = r.readLine()) != null) {
			if(!line.equals("")) this.content += line;
			else this.content += " ";
		}
		this.content = this.content.substring(4);
		r.close();
	}
	
	/**
	 * a helping function of stop words removing. check if a given word is in the stop word lists.
	 * @param stop Array of all stop words extracted from the 'stopwords.txt'
	 * @param word Target word
	 * @return
	 */
	public int checkStop(String[] stop, String word) {
		for(int i = 0; i < stop.length; i++) {
			if(stop[i].equals(word)) return i;
		}
		return -1;
	}
	
	/**
	 * Remove stop words, punctuations, and make every word to lower case.
	 * @param stop Array of all stop words extracted from the 'stopwords.txt'
	 */
	public void removeStopWords(String[] stop) {
		String[] contentArr = this.content.split("\\s+");
		String newContent = "";
		for(String word: contentArr) {
			word = word.replaceAll("[^a-zA-Z]", "");
			String dum_word = word.toLowerCase();
			if(this.checkStop(stop, dum_word) < 0) {
				newContent += word + " ";
			}
		}
		newContent = newContent.substring(0, newContent.length() - 1);
		this.content = newContent;
	}
	
	/**
	 * Lemmatize and tokenize.
	 */
	public void lemmatize() {
		String[] contentArr = this.content.split(" ");
		for(String word: contentArr) {
			if(word.length() != 0) {
				// check if it is NER. If so, no change
				if(NER.getInstance().checkNER(word)) {
					this.token.add(word.replace("\\s+", ""));
				}
				else {
					// check if the word has several capitalize letter
					int num = 0;
					for(int i = 0; i < word.length(); i++) {
						if(Character.isUpperCase(word.charAt(i))) num++;
					}
					if(num > 1) this.token.add(word.replace("\\s+", ""));
					else {
						this.token.add(Lemmatizer.getInstance().getLemma(word.replace("\\s+", "")));
					}
				}
			}
		}
	}
	
	/**
	 * Merge words as a single token, such as computer science, based on a frequency check.
	 * Use 2-gram and 3-gram.
	 * @param frequency check of whether two/three words should be merged together.
	 */
	public void merge(int frequency) {
		HashMap<String, Integer> table = new HashMap<String, Integer>();
		for(int i = 0; i < this.token.size(); i++) {
			if(i <= this.token.size() - 2) {
				String two = this.token.get(i) + " " + this.token.get(i + 1);
				if(table.containsKey(two)) table.put(two, table.get(two) + 1);
				else table.put(two, 1);
			}
			if(i <= this.token.size() - 3) {
				String three = this.token.get(i) + " " + this.token.get(i + 1) + " " + this.token.get(i + 2);
				if(table.containsKey(three)) table.put(three, table.get(three) + 1);
				else table.put(three, 1);
			}
		}
		ArrayList<String> mergeToken = new ArrayList<String>();
		int i = 0;
		while(i < this.token.size()){
			if(i <= this.token.size() - 3) {
				String three = this.token.get(i) + " " + this.token.get(i + 1) + " " + this.token.get(i + 2);
				if(table.get(three) >= frequency) {
					mergeToken.add(three);
					i += 3;
				}
				else {
					String two = this.token.get(i) + " " + this.token.get(i + 1);
					if(table.get(two) >= frequency) {
						mergeToken.add(two);
						i += 2;
					}
					else {
						mergeToken.add(this.token.get(i));
						i += 1;
					}
				}
			}
			else if(i <= this.token.size() - 2) {
				String two = this.token.get(i) + " " + this.token.get(i + 1);
				if(table.get(two) >= frequency) {
					mergeToken.add(two);
					i += 2;
				}
				else {
					mergeToken.add(this.token.get(i));
					i += 1;
				}
			}
			else {
				mergeToken.add(this.token.get(i));
				i += 1;
			}
		}
		this.token = new ArrayList<String>(mergeToken);
	}
	
	public void getTerms() {
		for(String word: this.token) {
			if(this.terms.containsKey(word)) this.terms.put(word, this.terms.get(word) + 1);
			else this.terms.put(word, 1);
		}
	}
	
}

