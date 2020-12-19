
public class Prey {
	
	/**
	 * 2-d document vector
	 */
	public double[] position;
	/**
	 * demonstrate whether it is alive 'a' or dead 'd'
	 */
	public char status; 
	/**
	 * original high-dimensional document vector
	 */
	public double[] raw;
	
	/**
	 * name for Document object
	 * only needed for text datasets
	 */
	public String name;
	
	/**
	 * 
	 * @param position
	 * @param raw
	 * @param name ---------------------------------need to be commented for real number documents
	 */
	public Prey(double[] position, double[] raw, String name) {
//	public Prey(double[] position, double[] raw) { //-------------------------------------use this for real number datasets
		this.position = position;
		this.raw = raw;
		this.status = 'a';
		this.name = name; // --------------------------------commented out for text datasets and commented for real number datasets
	}
	
	public Prey() {}

}
