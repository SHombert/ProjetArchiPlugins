package tiers;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import plateforme.DescripteurPlugin;
import plateforme.Loader;
import plateforme.Observer;

public class Monitor extends JFrame implements Runnable, Observer {

	Map<String,String> listePlugins;
	
	
	JLabel labelList;
	JLabel historic;
	

	DefaultTableModel dtm;
	JTable table;
	
	public Monitor() {
		Loader.getInstance().addSubscriber(this);
		listePlugins = new HashMap<String, String>();
		
		setTitle("Monitor");
		setBounds(300, 90, 400, 500);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));	
		
		labelList = new JLabel("Liste des plugins chargés : ",SwingConstants.LEFT);
		historic = new JLabel();
		String[] entetes = {"Plugin","Status"};
		dtm = new DefaultTableModel(entetes,0);
		table = new JTable(dtm);
		
		add(labelList);
		add(table);
		
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
		refreshTable();
		revalidate();
		repaint();
		
	}
	
	private void addHistoricLine(String time, String plugin, String status) {
		
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
