package tiers;

import appli.IDisplayStrategy;
import data.Personne;

public class DisplayStrategyPlus implements IDisplayStrategy {

	@Override
	public void output(Personne p) {
		System.out.println();

	}

}
