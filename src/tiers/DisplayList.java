package tiers;


import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;

import appliRDV.IDisplay;
import data.RDV;

public class DisplayList  implements IDisplay {

	private JList<RDV> rdvJList;

	public JComponent displayRDVList(List<RDV> rdvList) {

		RDV [] rdvArray = new RDV[rdvList.size()];
		for (int i = 0; i < rdvList.size(); i++) 
			rdvArray[i] = rdvList.get(i);

		rdvJList= new JList<>(rdvArray);
		rdvJList.setForeground(Color.BLUE);
		rdvJList.setFont(new Font("Arial", Font.PLAIN, 15));
		rdvJList.setSize(500, 400);
		rdvJList.setLocation(20, 150);
		return rdvJList;
	}   


}
