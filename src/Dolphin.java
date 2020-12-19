import java.util.*;

public class Dolphin {
	
	/**
	 * 2-d vector
	 */
	public double[] position;
	/**
	 * original high-dimensional document vector
	 */
	public double[] raw;
	/**
	 * search radius
	 */
	public double searchR;
	/**
	 * store data points assigned to the cluster represented by this dophin object
	 */
	public ArrayList<Prey> cluster;
	/**
	 * keep track of its current best-fit prey
	 */
	public Prey optimal;
	
	/**
	 * store the name attribute of Document object
	 * only used for text datasets
	 * NEED TO BE COMMENTED FOR REAL NUMBERS DATASETS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	public String name;
	
	/**
	 * @param position
	 * @param raw
	 * @param searchR
	 * @param name -------------------------NEED TO BE COMMENTED OUT FOR REAL NUMBERS DATASETS, and add for text datasets
	 */
	public Dolphin(double[] position, double[] raw, double searchR, String name) {
//	public Dolphin(double[] position, double[] raw, double searchR) { // ---------------------------Use this for real number datasets
		this.position = position;
		this.raw = raw;
		this.searchR = searchR;
		this.cluster = new ArrayList<Prey>();
		this.optimal = new Prey();
		this.optimal.status = 'n';
		this.name = name; // comment out this line for real number datasets
	}
	
	/**
	 * Calculate cosine similarity between two dolphins or preys
	 * needed for 'move' function
	 * @param d1 raw vector for data 1
	 * @param d2 raw vector for data 2
	 * @return cosine similarity of d1 and d2
	 */
	public double cosineSim(double[] d1, double[] d2) {
		double upp = (double) 0;
		for(int i = 0; i < d1.length; i++) {
			upp += (double) d1[i] * (double) d2[i];
		}
		double downD1 = (double) 0;
		double downD2 = (double) 0;
		for(int i = 0; i < d1.length; i++) {
			downD1 += (double) d1[i] * d1[i];
			downD2 += (double) d2[i] * d2[i];
		}
		downD1 = (double) Math.sqrt(downD1);
		downD2 = (double) Math.sqrt(downD2);
		double down = (double) downD1 * downD2;
		return (double) upp / down;
	}
	
	/**
	 * Calculate euclidean distance between two dolphins or preys
	 * needed for 'move' function
	 * @param d1 raw vector for data 1
	 * @param d2 raw vector for data 2
	 * @return euclidean distance of d1 and d2
	 */
	public double euclidean(double[] d1, double[] d2) {
		double sum = 0;
		for(int i = 0; i < d1.length; i++) {
			sum += (double)(d1[i] - d2[i]) * (double)(d1[i] - d2[i]);
		}
		return (double) Math.sqrt(sum);
	}
	
	/**
	 * Calculate fitness value, between 0 and 1, smaller it is better the current prey is
	 * related to distance, and the prey density of p
	 * @param d dolphin object
	 * @param p prey object
	 * @return a value demonstrated how fit this prey is to this dolphin
	 */
	// -------------------------------------NEEDS TO BE DEVELOPED---------------------------------------
	public double fitness(Dolphin d, Prey p, ArrayList<Prey> preyArr) {
		double check = 0.5;
		int dense = 0;
		for(Prey prey: preyArr) {
			if(cosineSim(prey.raw, d.raw) >  check) {
				dense ++;
			}
		}		
		// Comment out this line for 1) cosine similarity measure and 2) dense involve fitness function test
//		return cosineSim(d.raw, p.raw) * dense; 
		// Comment out this line for 1) euclidean distance measure and 2) dense involve fitness function test
//		return cosineSim(d.raw, p.raw) / dense; 
		
		// used most often
		return cosineSim(d.raw, p.raw);		
	}
	
	
	/**
	 * SEARCH FOR INDIVIDUAL OPTIMAL SOLUTION WITHIN SEARCH RADIUS
	 * @param preyArr array of prey objects
	 */
	public void search(ArrayList<Prey> preyArr) {
//		double maxFit = 100000000; // -------------------------------------------comment out this line for euclidean distance measure
		double maxFit = -1;
		int found = 0;
		for(Prey p: preyArr) {
			if(cosineSim(this.raw, p.raw) > searchR && p.status != 'd') { //---------------------------change > to < for euclidean distance measure
				double currFit = fitness(this, p, preyArr);
				if(currFit > maxFit) { // -------------------------------------------------------------change > to < for euclidean distance measure
					maxFit = currFit;
					this.optimal = p;
					found = 1;
				}
			}
		}
		if(found == 0) {
			this.optimal = new Prey();
			this.optimal.status = 'd';
		}
	}
	
	/**
	 * MAKE MOVE, I.E., UPDATE EACH DOLPHIN'S POSITION IN 2-D BASED ON THEIR OPTIMAL PREY
	 * @param target the one, either dolphin or prey, to move towards
	 * @param e a parameter to control how close to move, must >2, borrowed from paper
	 * @param c record whether using neighbor's prey or own prey
	 */
	public void move(Prey target, int e, char c, ArrayList<Prey> preyArr) {
		// move to its neighbor's best-fit prey
		// in this case, the dolphin will move to a random position ALONG A CIRCLE which centers at the target prey with radius R2
		if(c == 'n') {
			double DK = cosineSim(this.raw, target.raw); // distance between this dolphin and its neighbor's best-fit prey
			double DKL = cosineSim(this.optimal.raw, target.raw); // distance betwen this dolphin's individual searched prey and its neighbor' best prey
			double R2; 
			if(DK > DKL) {
				R2 = (1 - (DK / fitness(this, target, preyArr) + (DK - DKL) / fitness(this, this.optimal, preyArr)) / e * DK / fitness(this, target, preyArr)) * DK;
			}
			else {
				R2 = (1 - (DK / fitness(this, target, preyArr) - (DKL - DK) / fitness(this, this.optimal, preyArr)) / e * DK / fitness(this, target, preyArr)) * DK;
			}
			Random rand = new Random();
			double[] randDir = {100 * rand.nextDouble(), 100 * rand.nextDouble()}; // 0 - 100
			double normRandDir = Math.sqrt(randDir[0] * randDir[0] + randDir[1] * randDir[1]);
			double newx = target.position[0] + randDir[0] / normRandDir * R2;
			double newy = target.position[1] + randDir[1] / normRandDir * R2;
			this.position[0] = newx;
			this.position[1] = newy;
		}
		// move to its own individual searched prey
		// in this case, the dolphin will move towards to target prey and stop at a position R2 away from the target prey
		else if(c == 'i') {
			double DL = cosineSim(this.raw, this.optimal.raw);
			double R2 = (1 - 2 / e) * DL;
			double newx = this.optimal.position[0] + (this.position[0] - this.optimal.position[0]) * R2 / DL;
			double newy = this.optimal.position[1] + (this.position[1] - this.optimal.position[1]) * R2 / DL;
			this.position[0] = newx;
			this.position[1] = newy;
		}
	}
     
	
	

}
