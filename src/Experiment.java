import java.util.*;

public class Experiment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] position = {3.2, 2.1};
		double[] raw = {2.1, 3.3, 2.1};
		String name = "yeah";
		Prey prey = new Prey(position, raw, name);
		ArrayList<Prey> pArr = new ArrayList<Prey>();
		pArr.add(prey);
		double searchR = 0.2;
		Dolphin d = new Dolphin(position, raw, searchR, "no");
		d.cluster.add(prey);
		for(Prey p: d.cluster) {
			p.status = 'q';
		}
		for(Prey p: pArr) {
			System.out.println(p.status);
		}
	}

}
