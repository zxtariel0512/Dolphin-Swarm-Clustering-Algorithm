import edu.stanford.nlp.pipeline.*;


import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Helping class to realize name entity recognition
 * @author arielzhu
 * Copyright to Stanford coreNLP
 */
public class NER {	
	public StanfordCoreNLP pipeline;
	public static NER ner;
	public Properties props;
	
	public NER(){
		this.props = new Properties();
		props.put("annotators", "tokenize,ssplit,pos,lemma,ner");
		props.setProperty("ner.useSUTime", "0");
		this.pipeline = new StanfordCoreNLP(props, false);
	}
	
	public static NER getInstance() {
		if (ner == null) {
			ner = new NER();
		}
		return ner;

	}
  
  public boolean checkNER(String word) {
	  CoreDocument doc = new CoreDocument(word);
	  this.pipeline.annotate(doc);
	  if(doc.entityMentions() == null) {
		  return false;
	  }
	  if(doc.entityMentions().size() == 0) return false;
	  return true;
  }

}