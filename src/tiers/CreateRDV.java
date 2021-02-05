package tiers;

import appliRDV.ICreateRDV;
import data.Medecin;
import data.Patient;
import data.RDV;

/** plugin de test pour la création de rdv
 *
 */
public class CreateRDV implements ICreateRDV {
	int index;
	
	public CreateRDV () {
		index=1;
	}
	
	@Override
	public RDV getNewRdv() {
		RDV rdv = new RDV("01-01-2021", "10:00", "Covid", new Patient("Jean","Jacques " + index), new Medecin("John","Doe"));
		index++;
		return rdv;
	}

}
