package monitor;

import java.awt.Dimension;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import plateforme.Loader;
import plateforme.Observer;

public class Monitor extends JFrame implements Runnable, Observer {

	Map<String,String> listePlugins;
	
	
	JLabel labelList;	
	JList <String> historic;
	DefaultListModel <String> dlm;

	DefaultTableModel dtm;
	JTable table;
	
	public Monitor() {
		Loader.getInstance().addSubscriber(this);
		listePlugins = new HashMap<String, String>();
		
		setTitle("Monitor");
		setBounds(300, 90, 400, 500);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));	
		
		labelList = new JLabel("Liste des plugins charges : ",SwingConstants.LEFT);
		
		dlm = new DefaultListModel<String>();
		historic = new JList<>(dlm);
		String[] entetes = {"Plugin","Status"};
		dtm = new DefaultTableModel(entetes,0);
		table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setMinimumSize(new Dimension(200,150));
		add(labelList);
		add(scrollPane);
		add(historic);
	}
	
	public void run() {
		System.out.println("Lancement du moniteur");		
		setVisible(true);
	}

	/**
	 * Ajoute les informations reçues dans la map des plugins et met à jour le tableau de suivi et l'historique
	 */
	@Override
	public void update(String name, String status) {
		listePlugins.put(name,status);
		if(!status.equals("Disponible dans la config")) {
			addHistoricLine(LocalDate.now().toString(),name,status);
		}
		refreshTable();
		revalidate();
		repaint();
		
	}
	
	private void addHistoricLine(String time, String plugin, String status) {
		String newLine = time;
		switch (status) {
			case "Charge" : 
				newLine+=" : Le plugin " + plugin +" a ete charge.";
				break;
			case "Echec du chargement" :
				newLine+=" : Le chargement du plugin "+ plugin + " a echoue.";
				break;
			case "Demande de chargement":
				newLine+=" : Le chargement du plugin "+ plugin + " a ete demande.";
				break;
			default:
				;	
		
		}
		dlm.addElement(newLine);
	}
	
	/**
	 * Remplace les données du tableau avec les modifications apportées via le loader
	 */
	private void refreshTable() {
		Object[][] arr = new Object[listePlugins.size()][2];
		Set entries = listePlugins.entrySet();
		Iterator entriesIterator = entries.iterator();

		int i = 0;
		while(entriesIterator.hasNext()){

		    Map.Entry mapping = (Map.Entry) entriesIterator.next();

		    arr[i][0] = mapping.getKey();
		    arr[i][1] = mapping.getValue();
		    i++;
		}
		String[] entetes = {"Plugin","Status"};
		dtm.setDataVector(arr, entetes);
	}
	
}
