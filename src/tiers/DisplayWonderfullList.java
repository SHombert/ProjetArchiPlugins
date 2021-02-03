package tiers;


import java.awt.HeadlessException;
import java.util.List;


import javax.swing.JComponent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import appliRDV.IDisplay;
import data.RDV;
import tiers.utils.DateHeaderRenderer;
import tiers.utils.DefaultHeaderRenderer;
import tiers.utils.MedecinHeaderRenderer;
import tiers.utils.PatientHeaderRenderer;

public class DisplayWonderfullList implements IDisplay{
	  
  
	private JTable table;
    
	public DisplayWonderfullList() throws HeadlessException {
		 
	}

	@Override
	public JComponent displayRDVList(List<RDV> rdvList) {
    	
    	RDV [] rdvArray = new RDV[rdvList.size()];
		for (int i = 0; i < rdvList.size(); i++) 
		 rdvArray[i] = rdvList.get(i);

		 		 
 		
 		String[] columnNames = new String[] {"N","DATE", "HEURE", "MOTIF", "MEDECIN", "PATIENT"};
 		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
 		for (int i = 0; i < rdvList.size(); i++) {
 			 model.addRow( new Object[]{ 
 					 1+i, 
 					 rdvList.get(i).getDate(),
 					 rdvList.get(i).getHoraire(),
 					 rdvList.get(i).getMotif(),
 					 rdvList.get(i).getMedecin(),
 					 rdvList.get(i).getPatient()} );
 		}
 		table = new JTable(model);
        
 		table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
        table.getColumnModel().getColumn(2).setMaxWidth(50);
        
        table.getTableHeader().setDefaultRenderer(new DefaultHeaderRenderer());
        table.getColumnModel().getColumn(1).setHeaderRenderer(new DateHeaderRenderer());
        table.getColumnModel().getColumn(2).setHeaderRenderer(new DateHeaderRenderer());
        table.getColumnModel().getColumn(4).setHeaderRenderer(new MedecinHeaderRenderer());
        table.getColumnModel().getColumn(5).setHeaderRenderer(new PatientHeaderRenderer());
 		
 		//table.setForeground(Color.CYAN);
 		//table.setFont(new Font("Arial", Font.PLAIN, 15));
 		table.setSize(600, 400);
 		table.setLocation(20, 150);
 		JScrollPane scrollPane = new  JScrollPane(table);
 		scrollPane.setBounds(20, 150, 600, 400);
 		
 		return scrollPane;
 		     
    }   
 

	 

}
