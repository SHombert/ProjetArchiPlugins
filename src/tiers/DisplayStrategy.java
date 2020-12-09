package tiers;

import appli.IDisplayStrategy;
import data.Personne;

public class DisplayStrategy implements IDisplayStrategy {
	

	@Override
	public void output(Personne p) { // refactor --> extract
		System.out.println(p);
	}
}
