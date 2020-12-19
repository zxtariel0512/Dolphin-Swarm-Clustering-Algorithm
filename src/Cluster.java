import java.util.*;

public class Cluster {
	
	/**
	 * store all high-dimensional (original) document vectors
	 */
	public double[][] rawArr;
	/**
	 * store all Document objects
	 * only used for text documents
	 */
	public ArrayList<Document> docs;
	/**
	 * initial choice of number of clusters (may be adjusted in clustering iterations)
	 */
	public int k;
	/**
	 * search radius of dolphins (used in individual search phase)
	 */
	public double searchR;
	/**
	 * a parameter used in dolphin swarm algorithm model (in paper). used in TS matrix updated part. demonstrate the speed of echolocation
	 */
	public double speed;
	/**
	 * a parameter set by programmers. must > 2. used in 'move' function borrowed from paper
	 */
	public int e;
	
	/**
	 * 
	 * @param docs ---------------------------------------------------comment this parameter for real number datasets
	 * @param rawArr
	 * @param k
	 * @param searchR
	 * @param speed
	 * @param e
	 */
	public Cluster(ArrayList<Document> docs, double[][] rawArr, int k, double searchR, double speed, int e) {
//	public Cluster(double[][] rawArr, int k, double searchR, double speed, int e) { // ---------------------use this for real number datasets
		this.rawArr = rawArr;
		this.k = k;
		this.searchR = searchR;
		this.speed = speed;
		this.e = e;
		this.docs = new ArrayList<Document>(docs); // ----------------------------------------------comment this line for real number datasets
	}
	
	/**
	 * transform high-dimensional document vectors to 2-d vectors randomly (later can change to svd or pca)
	 * @return 2-d vectors
	 */
	public double[] transformDimension() {
		Random rand = new Random();
		double x = 100 * rand.nextDouble();
		double y = 100 * rand.nextDouble();
		double[] newArr = {x, y};
		return newArr;
	}
	
	/**
	 * Cluster main method
	 */
	public HashMap<String, String> cluster() {				
		// select dolphins randomly
		ArrayList<Dolphin> dolphinArr = new ArrayList<Dolphin>();
		ArrayList<Prey> preyArr = new ArrayList<Prey>();
		Set<Integer> dolphinIdx = new HashSet<Integer>();
		int idx;
		for(int i = 0; i < this.k; i++) {
			while(true) {
				Random rand = new Random();
				idx = rand.nextInt(this.rawArr.length);
				if(!dolphinIdx.contains(idx)) break;
			}
			dolphinIdx.add(idx);
		}
		// transform the high-d original document vectors to 2-d vectors
		// save selected Dolphin object to array, and Prey objects to another array
		for(int i = 0; i < this.rawArr.length; i++) {
			if(dolphinIdx.contains(i)) {
				double[] newPos = transformDimension();
				Dolphin d = new Dolphin(newPos, this.rawArr[i], this.searchR, docs.get(i).name); // -------------delete name parameter for real number datasets
//				Dolphin d = new Dolphin(newPos, this.rawArr[i], this.searchR); // use this line for real number datasets
				dolphinArr.add(d);
			}
			else {
				double[] newPos = transformDimension();
			    Prey p = new Prey(newPos, this.rawArr[i], docs.get(i).name);// ----------------------------------delete name parameter for real number datasets
//			    Prey p = new Prey(newPos, this.rawArr[i]); // use this line for real number datasets
			    preyArr.add(p);
			}
		}
		
		// print raw positions for all data
		System.out.println("raw");
		for(Dolphin d: dolphinArr) {
			System.out.print(Arrays.toString(d.position) + ", ");
		}
		for(Prey p: preyArr) {
			System.out.print(Arrays.toString(p.position) + ", ");
		}
		System.out.println();
		
		// main clustering iteration loop
		while(true) {			
			// Step 1: search phase
	        for (Dolphin dolphin: dolphinArr) {
	            dolphin.search(preyArr); 
	        }
	        
	        // Step 2: call phase
	        // use this matrix to keep track of remaining time of echolocation travel from one Dolphin to another
	        // TS[i][j] means the remaining time of sound to travel from Dolphin_j to Dolphin_i
	        double[][] TS = new double[dolphinArr.size()][dolphinArr.size()];
	        for(int i = 0; i < TS.length; i++) {
	        	for(int j = 0; j < TS[0].length; j++) {
	        		TS[i][j] = 10000000; //initialize with a big number, remaining time for sound to travel from Dj to Di
	        	}
	        }
	        // update every entry of TS[i][j] if needed
	        for(int i = 0; i < TS.length; i++) {
	        	for(int j = 0; j < TS[0].length; j++) {
	        		Dolphin Dj = dolphinArr.get(j);
	        		Dolphin Di = dolphinArr.get(i);
	        		// TS[i][j] update conditions:
	        		// 1. Dj has better prey than Di (use finess function to decide)
	        		// 2. current entry (remaining time of sound travel from Dj to Di) is larger than the time needs to travel from Dj to Di in current round
	        		if(Dj.optimal.status != 'd' && Di.optimal.status != 'd') {
	        			// ----------------------------------------change > in first inequality to < for euclidean distance-------------------------------
	        			if(Dj.fitness(Dj, Dj.optimal, preyArr) > Di.fitness(Di, Di.optimal, preyArr) && TS[i][j] > Dj.cosineSim(Dj.raw, Di.raw) / this.speed) {
		        			// need update
		        			TS[i][j] = Dj.cosineSim(Dj.raw, Di.raw) / this.speed;
		        		}
	        		}
	        		else {
	        			if(Di.optimal.status == 'd' && Dj.optimal.status != 'd') {
	        				// -------------------------------------change > in first inequality to < for euclidean distance----------------------
	        				if(TS[i][j] > Dj.cosineSim(Dj.raw, Di.raw) / this.speed) TS[i][j] = Dj.cosineSim(Dj.raw, Di.raw) / this.speed;
	        			}
	        		}
	        	}
	        }
	        ArrayList<Dolphin> removeDol = new ArrayList<Dolphin>();
	        
	        // Step 3: reception phase
	        for(int i = 0; i < TS.length; i++) {
	        	for(int j = 0; j < TS[0].length; j++) {
	        		Dolphin Dj = dolphinArr.get(j);
	        		Dolphin Di = dolphinArr.get(i);
	        		TS[i][j] -= 1; // as time moves on, very important step
	        		if(TS[i][j] <= 0) {
	        			// sound accepted!
	        			TS[i][j] = 10000000;
	        			// DOLi needs to make decision: whether to insist on its individual optimal or take neighbor's
	        			if(Dj.optimal.status != 'd' && Di.optimal.status != 'd') {
	        				if(Dj.fitness(Dj, Dj.optimal, preyArr) >= Di.fitness(Di, Di.optimal, preyArr)) {
		        				char c = 'n'; // for move function to know whether this Dolphin is going to move to its own individual searched prey or neighbor's prey
		        				// TAKE NEIGHBOR'S!
		        				if(Di.cluster.size() > 0) {
		        					// release its previous preys
		        					for(Prey prey: Di.cluster) {
		        						prey.status = 'a';
		        					}
		        					// move this dolphin towards Dj's optimal prey before changing this dolphin to prey
		        					Di.move(Dj.optimal, this.e, c, preyArr); // >2
		        					// re-initialize all TS entries regarding with DOLi
		        					for(int k = 0; k < TS.length; k++) {
		        						TS[i][k] = 10000000;
		        					}
		        					for(int k = 0; k < TS.length; k++) {
		        						TS[k][i] = 10000000;
		        					}
		        					// change DOLi to a prey
		        					Prey newPrey = new Prey(Di.position, Di.raw, Di.name); // ----------------------------delete name parameter for real number datasets
		        					preyArr.add(newPrey);
		        					if(removeDol.indexOf(Di) == -1) removeDol.add(Di);
		        					// decide whether DOLj needs to changes it optimal decision 
		        					if(Dj.fitness(Dj, Dj.optimal, preyArr) < Dj.fitness(Dj, newPrey, preyArr)) { // ---------------------change < to > for euclidean distance
		        						Dj.optimal = newPrey;
		        					}
		        				}
		        			}
	        			}
	        			// otherwise, nothing needs to change. Di still use its own optimal solution
	        		}
	        	}
	        }
	        
	        // remove "prey" dolphin 
	        for(Dolphin D: removeDol) {
	        	dolphinArr.remove(D);
	        }
	        
	        // deal with the case when two dolphins are having the same optimal
	        HashMap<Prey, Dolphin> repeat = new HashMap<Prey, Dolphin>();
	        for(Dolphin dol: dolphinArr) {
	        	if(repeat.containsKey(dol.optimal)) {
	        		// find repeatable prey
	        		// keep the one with higher fitness, move the dolphin, but do not assign cluster
	        		Prey P = dol.optimal;
	        		Dolphin D1 = repeat.get(P);
	        		if(D1.fitness(D1, P, preyArr) > dol.fitness(dol, P, preyArr)) { // -------------------------change > to < for euclidean distance
	        			// keep the existing one
	        			char c;
		        		if(dol.cosineSim(dol.raw, P.raw) > searchR) { // ---------------------------------------change > to < for euclidean distance
		        			c = 'i'; // indicate the Dolphin is moving to its own individual search prey
		        		}
		        		else {
		        			c = 'n'; // indicate the Dolphin is moving to neighbor's prey
		        		}
		        		dol.move(P, this.e, c, preyArr);
		        		dol.optimal = new Prey();
		        		dol.optimal.status = 'd';
	        		}
	        		else {
	        			// replace, and assign cluster by current one
	        			char c;
		        		if(D1.cosineSim(D1.raw, P.raw) > searchR) { // ----------------------------------------change > to < for euclidean distance
		        			c = 'i'; // indicate the Dolphin is moving to its own individual search prey
		        		}
		        		else {
		        			c = 'n'; // indicate the Dolphin is moving to neighbor's prey
		        		}
		        		D1.move(P, this.e, c, preyArr);
		        		repeat.put(P, dol);
		        		D1.optimal = new Prey();
		        		D1.optimal.status = 'd';
	        		}
	        	}
	        	else repeat.put(dol.optimal, dol);
	        }
	        
	        // Step 4: predation phase (clustering thing)
	        for(Dolphin D: dolphinArr) {
	        	// check if optimal empty
	        	if(D.optimal.status != 'd') {
	        		// assign its optimal sol to its cluster, and change the prey's status
	        		D.optimal.status = 'd';
	        		D.cluster.add(D.optimal);
	        		// move the dolphin
	        		char c;
	        		if(D.cosineSim(D.raw, D.optimal.raw) > searchR) { // -----------------------------------------change > to < for euclidean distance
	        			c = 'i'; // indicate the Dolphin is moving to its own individual search prey
	        		}
	        		else {
	        			c = 'n'; // indicate the Dolphin is moving to neighbor's prey
	        		}
	        		D.move(D.optimal, this.e, c, preyArr);
	        	}
	        }

	        // check dolphin's number
	        if(dolphinArr.size() < this.k) {
	        	// need new one
	        	ArrayList<Prey> alive = new ArrayList<Prey>();
	        	for(Prey prey: preyArr) {
	        		if(prey.status != 'd') alive.add(prey);
	        	}
	        	int needs = k - dolphinArr.size();
	        	for(int i = 0; i < needs; i++) {
	        		Random rand = new Random();
		        	int newDolIdx = rand.nextInt(alive.size());
		        	//------------------------------------------------------delete the last parameter (name parameter) for real number datasets
		        	Dolphin newD = new Dolphin(alive.get(newDolIdx).position, alive.get(newDolIdx).raw, this.searchR, alive.get(newDolIdx).name);
//		        	Dolphin newD = new Dolphin(alive.get(newDolIdx).position, alive.get(newDolIdx).raw, this.searchR) // use this line for real number datasets
		        	preyArr.remove(alive.get(newDolIdx));
		        	dolphinArr.add(newD);
		        	alive.remove(newDolIdx);
	        	}
	        }
	        
	        // check if k enough
	        int checkEnd = 0; // used to check if the loop can end
	        int checkK = 0; // used to check if k enough
	        int currCheck; // used to check if k enough
	        int isolatedIdx = -1;
	        Prey isolatedPrey = new Prey(); // used to store one alive prey that cannot be got by any existing Dolphin within search radius
	        for(int i = 0; i < preyArr.size(); i++) {
	        	if(preyArr.get(i).status == 'a') {
	        		checkEnd++;
	        		currCheck = 0;
	        		for(Dolphin D: dolphinArr) {
	        			if(D.cosineSim(D.raw, preyArr.get(i).raw) > searchR) { //---------------change > to < for euclidean distance
	        				currCheck += 1;
	        			}
	        		}
	        		if(currCheck == 0) {
	        			checkK ++;
	        			isolatedPrey.position = preyArr.get(i).position;
		        		isolatedPrey.status = preyArr.get(i).status;
		        		isolatedPrey.raw = preyArr.get(i).raw;
		        		isolatedPrey.name = preyArr.get(i).name;
		        		isolatedIdx = i;
	        		}
	        	}
	        }
	        if(checkEnd == 0) break;
	        if(checkK > 0) {
	        	// k not enough!
	            // initialize one isolated prey as a new dolphin for new cluster
	        	//--------------------------------------------------------------------delete the last parameter (name parameter) for real number datasets
	        	Dolphin newPD = new Dolphin(isolatedPrey.position, isolatedPrey.raw, this.searchR, isolatedPrey.name);
//	        	Dolphin newPD = new Dolphin(isolatedPrey.position, isolatedPrey.raw, this.searchR) // use this line for real number datasets
	        	preyArr.remove(isolatedIdx);
	        	dolphinArr.add(newPD);
	        	k++;
	        }
	       
		}
		
		
		// -----------------------------------------RETURN CLUSTER RESULT FOR REAL NUMBER DATASETS--------------------------------
		// ----------------comment out this block of code for real number datasets, and change the return type in line 64 to ArrayList<Prey[]>-----------------
//		ArrayList<Prey[]> res = new ArrayList<Prey[]>();
//		for(Dolphin dol: dolphinArr) {
//			Prey[] currRes = new Prey[dol.cluster.size() + 1];
//			for(int i = 0; i < dol.cluster.size(); i++) {
//				currRes[i] = dol.cluster.get(i);
//			}
//			Prey pD = new Prey(dol.position, dol.raw, dol.name);
//			currRes[currRes.length - 1] = pD;
//			res.add(currRes);
//		}
//		return res;
		
		// ----------------------------------------RETURN CLUSTER RESULT FOR TEXT DATASETS------------------------------------------
		// ----------------comment this block of code for real number datasets, and remember to change the return type in line 64 to the type of 'res' variable------------
		HashMap<String, String> res = new HashMap<String, String>();
		System.out.println();
		int No = 0;
		for(Dolphin dol: dolphinArr) {
			String clusterNo = "Cluster " + No; 
			System.out.println(clusterNo);
			System.out.print(Arrays.toString(dol.position) + ",");
			res.put(dol.name, clusterNo);
			for(Prey p: dol.cluster) {
				res.put(p.name, clusterNo);
				System.out.print(Arrays.toString(p.position)+ "," );
			}
			No++;
			System.out.println();
		}
		return res;
	}
}
