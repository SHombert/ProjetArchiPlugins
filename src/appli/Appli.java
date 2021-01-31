package appli;

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


public class Appli extends JFrame implements Runnable , ActionListener{
	private static final String APPNAME = "Application RDV m�dicaux";
	IDisplay display;
	HashMap<String, DescripteurPlugin> descriptionPluginsDispos;
	ILoadRDVs loadRdv;
	ICreateRDV createRDV;
	List<RDV> rdvs;
	List<Medecin> medecins;
	List<Patient> patients;
	
	//Elements pour la Jframe
	private Container c; 
	private JLabel frameTitle; 
	private JLabel listTitle; 
	private JButton create;
	private JComponent displayComponent;

	public Appli()  {
		setTitle("GESTION DE RDV MEDICAUX"); 
		setBounds(300, 90, 900, 700); 
		setResizable(false); 



		// recuperer descripteurs
		System.out.println("Lancement de l'appli : constructeur");
		descriptionPluginsDispos = Loader.getDescripteurs(APPNAME);
		//display = (IDisplay) Loader.loadPluginsFor(descriptionPluginsDispos.get("Affichage en liste"));// load la liste
		display = (IDisplay) Loader.loadPluginsFor(descriptionPluginsDispos.get("Affichage en tableau"), null);// load la liste dans un tableau
		loadRdv =  (ILoadRDVs) Loader.loadPluginsFor(descriptionPluginsDispos.get("Chargement par fichiers"), null);


		rdvs = (ArrayList<RDV>) loadRdv.getRdvList("src/tiers/rdv_file.txt");

		medecins = (ArrayList<Medecin>)loadRdv.getMedecins();
		patients = (ArrayList<Patient>)loadRdv.getPatients();

		// load des infos � partir du fichier

	}

	public void run() {

		System.out.println("Lancement de l'appli");
		output();
		setFrameContent();
		setVisible(true);
	}

	public void output() { // refactor --> extract
		if(display == null) {
			//display = Loader.getDisplayFor(descriptionDisplayDispos.get(0));

		}  

		// FIXME : charger display si n�cessaire (si on en a pas un on le charge) : par d�faut prendre le premier trouv�
		displayComponent = display.displayRDVList(rdvs);


	}
	private void setFrameContent() {
		c = getContentPane(); 
		c.setLayout(null); 

		frameTitle = new JLabel("BIENVENUE SUR E-RDV"); 
		frameTitle.setFont(new Font("Arial", Font.PLAIN, 30)); 
		frameTitle.setSize(600, 30); 
		frameTitle.setLocation(100, 30); 
		c.add(frameTitle);

		listTitle = new JLabel("Liste des RDV programm�s"); 
		listTitle.setFont(new Font("Arial", Font.PLAIN, 20)); 
		listTitle.setSize(500, 20); 
		listTitle.setLocation(100, 100); 
		c.add(listTitle);

		create = new JButton("Cr�er un RDV"); 
		create.setForeground(Color.WHITE);
		create.setBackground(Color.BLUE);
		//create.setOpaque(true);
		create.setBorderPainted(false);
		create.setFont(new Font("Arial", Font.PLAIN, 15)); 
		create.setSize(150, 20); 
		create.setLocation(650, 150); 
		create.addActionListener(this); 
		c.add(create);

		c.add(displayComponent);

	}

	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == this.create) {  

			System.out.println("Create clicked");	

			//dispose();
			Object [] args = {this.medecins, this.patients }; 	  
			createRDV = (ICreateRDV)Loader.loadPluginsFor(descriptionPluginsDispos.get("Cr�ation formulaire"), args);
			rdvs.add(createRDV.getNewRdv());

			output();

			revalidate();
			repaint();


		} 
	}


}
