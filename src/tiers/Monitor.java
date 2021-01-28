package tiers;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import plateforme.DescripteurPlugin;

public class Monitor extends JFrame implements Runnable{

	List<String> listePlugins;
	
	JPanel container;
	JLabel labelList;
	JList<String> pluginsList;
	
	public Monitor() {
		listePlugins = new ArrayList();
		this.listePlugins.add("Plugin 1");
		this.listePlugins.add("Plugin 2");
		
		setTitle("Monitor");
		setBounds(300, 90, 400, 500);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));	
		labelList = new JLabel("Liste des plugins chargés : ",SwingConstants.LEFT);
		pluginsList = new JList <String> ((String[]) listePlugins.toArray(new String[listePlugins.size()]));
		add(labelList);
		add(pluginsList);
		
	}
	
	public void run() {
		System.out.println("Lancement du moniteur");
		
		
		// Frame qui affiche regulièrement l'état des pluggins
		// notification chargement pluggin
		
		
		setVisible(true);
	}
	
	// update qui met à jour listePlugins et recharge la JList
	// +  affiche un message (jlabel ?) le plugin *** vient d'etre chargé 
}
