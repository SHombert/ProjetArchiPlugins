package appliRDV;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;


import data.Medecin;
import data.Patient;
import data.RDV;
import plateforme.DescripteurPlugin;
import plateforme.Loader;

/**
 * Classe principale pour l'application de gestion de rdv médicaux
 *
 */
public class Appli extends JFrame implements Runnable , ActionListener{
	
	// Elements pour les plugins
	private static final String APPNAME = "Application RDV medicaux";
	IDisplay display;
	HashMap<String, DescripteurPlugin> descriptionPluginsDispos;
	ILoadRDVs loadRdv;
	ICreateRDV createRDV;
	
	// Données
	List<RDV> rdvs;
	List<Medecin> medecins;
	List<Patient> patients;
	
	//Elements pour la Jframe
	private Container c; 
	private JLabel frameTitle; 
	private JLabel listTitle; 
	private JButton create;
	private JButton switchDisplayList;
	private JButton switchDisplayTable;
	private JButton createStatic;

	private JComponent displayComponent;

	public Appli()  {
		setTitle("GESTION DE RDV MEDICAUX"); 
		setBounds(300, 90, 900, 700); 
		setResizable(false); 
		c = getContentPane(); 
		setLayout(null); 

		descriptionPluginsDispos = Loader.getDescripteurs(APPNAME);
		display = (IDisplay) Loader.loadPluginsFor(descriptionPluginsDispos.get("Affichage en tableau"), null);// load la liste dans un tableau
		loadRdv =  (ILoadRDVs) Loader.loadPluginsFor(descriptionPluginsDispos.get("Chargement par fichiers"), null);

		rdvs = (ArrayList<RDV>) loadRdv.getRdvList("src/tiers/rdv_file.txt");
		medecins = (ArrayList<Medecin>)loadRdv.getMedecins();
		patients = (ArrayList<Patient>)loadRdv.getPatients();
	}

	/**
	 * Méthode lancée lors du thread.start
	 */
	public void run() {

		System.out.println("Lancement de l'appli");
		output();
		setFrameContent();
		setVisible(true);
	}

	/**
	 * Affichage de la liste des RDV médicaux par le plugin displayComponent
	 * Le type d'affichage est modifié par les boutons de l'interface graphique
	 */
	public void output() {
		if(display == null) {
			display = (IDisplay) Loader.loadPluginsFor(descriptionPluginsDispos.get("Affichage en tableau"),null);
		}  
		displayComponent = display.displayRDVList(rdvs);
		c.add(displayComponent);
	}
	
	/**
	 * Construction des éléments de la fenetre principale
	 */
	private void setFrameContent() {
		frameTitle = new JLabel("BIENVENUE SUR E-RDV"); 
		frameTitle.setFont(new Font("Arial", Font.PLAIN, 30)); 
		frameTitle.setSize(600, 30); 
		frameTitle.setLocation(100, 30); 
		c.add(frameTitle);

		listTitle = new JLabel("Liste des RDV programmes"); 
		listTitle.setFont(new Font("Arial", Font.PLAIN, 20)); 
		listTitle.setSize(500, 20); 
		listTitle.setLocation(100, 100); 
		c.add(listTitle);

		create = new JButton("Creer un RDV"); 
		create.setForeground(Color.WHITE);
		create.setBackground(Color.BLUE);
		//create.setOpaque(true);
		create.setBorderPainted(false);
		create.setFont(new Font("Arial", Font.PLAIN, 15)); 
		create.setSize(175, 20); 
		create.setLocation(650, 150); 
		create.addActionListener(this); 
		c.add(create);
		
		switchDisplayList = new JButton("Afficher en liste"); 
		switchDisplayList.setForeground(Color.WHITE);
		switchDisplayList.setBackground(Color.BLUE);
		//create.setOpaque(true);
		switchDisplayList.setBorderPainted(false);
		switchDisplayList.setFont(new Font("Arial", Font.PLAIN, 15)); 
		switchDisplayList.setSize(175, 20); 
		switchDisplayList.setLocation(650 , 100); 
		switchDisplayList.addActionListener(this); 
		c.add(switchDisplayList);
		
		switchDisplayTable = new JButton("Afficher en tableau"); 
		switchDisplayTable.setForeground(Color.WHITE);
		switchDisplayTable.setBackground(Color.BLUE);
		//create.setOpaque(true);
		switchDisplayTable.setBorderPainted(false);
		switchDisplayTable.setFont(new Font("Arial", Font.PLAIN, 15)); 
		switchDisplayTable.setSize(175, 20); 
		switchDisplayTable.setLocation(650 , 125); 
		switchDisplayTable.addActionListener(this); 
		c.add(switchDisplayTable);
		
		createStatic = new JButton("Creer RDV static"); 
		createStatic.setForeground(Color.WHITE);
		createStatic.setBackground(Color.BLUE);
		//create.setOpaque(true);
		createStatic.setBorderPainted(false);
		createStatic.setFont(new Font("Arial", Font.PLAIN, 15)); 
		createStatic.setSize(175, 20); 
		createStatic.setLocation(650 , 175); 
		createStatic.addActionListener(this); 
		c.add(createStatic);
		
	}

	/**
	 * Action listener pour les éléments cliquables de l'interface graphique
	 */
	public void actionPerformed(ActionEvent e) { 
		
		// bouton créer, charge le plugin de création via formulaire puis lance la méthode getRDV et met à jour la liste des RDV
		if (e.getSource() == this.create) {  
			System.out.println("Create clicked");
			//dispose();
			Object [] args = {this.medecins, this.patients };
			if(createRDV==null)
				createRDV = (ICreateRDV)Loader.loadPluginsFor(descriptionPluginsDispos.get("Creation formulaire"), args);
			RDV newRDV = createRDV.getNewRdv();
			if(newRDV != null)
				rdvs.add(newRDV);
			c.remove(displayComponent);
			output();
			c.revalidate();
			c.repaint();
		}
		
		// bouton affichage en liste, charge le plugin d'affichage en liste et met à jour l'interface graphique
		if (e.getSource() == this.switchDisplayList) { 
			
			display = (IDisplay) Loader.loadPluginsFor(descriptionPluginsDispos.get("Affichage en liste"), null);
			c.remove(displayComponent);
			output();
			c.revalidate();
			c.repaint();
		}
		
		// bouton affichage en tableau, charge le plugin d'affichage en tableau et met à jour l'interface graphique
		if (e.getSource() == this.switchDisplayTable) { 
			display = (IDisplay) Loader.loadPluginsFor(descriptionPluginsDispos.get("Affichage en tableau"), null);
			c.remove(displayComponent);
			output();
			c.revalidate();
			c.repaint();
		}
		
		// bouton creer un rdv static pour une deuxieme implementation fonctionnelle du ICreateRDV
				if (e.getSource() == this.createStatic) { 
					if(createRDV==null)
					createRDV = (ICreateRDV) Loader.loadPluginsFor(descriptionPluginsDispos.get("Création index"), null);
					rdvs.add(createRDV.getNewRdv());
					c.remove(displayComponent);
					output();
					c.revalidate();
					c.repaint();
				}
	}


}
