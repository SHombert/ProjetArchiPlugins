package tiers;


import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import appliRDV.ICreateRDV;
import data.Medecin;
import data.Patient;
import data.RDV;

public class LoadFromForm extends JFrame implements ICreateRDV , ActionListener{
	
	// Components of the Form 
	private Container c; 
	private JLabel title; 
	
	private JLabel medecin; 
	private JComboBox jMedecins; 
		 
	private JLabel dateRdv; 
    private JComboBox date; 
    private JComboBox month; 
    private JComboBox year; 
	
    private JLabel motif; 
    private JTextField tMotif;
    
    private JLabel heure; 
    private JComboBox tHeure;
    private JComboBox tMinute;
    
    private JLabel patient;
    private JComboBox jPatients;
    
	private JButton sub; 
	private JButton reset; 
	private JTextArea tout; 
	private JLabel res; 
	private JTextArea resadd; 
   
	private String heures[] 
			= {"00", "01", "02", "03", "04", "05", 
				"06", "07", "08", "09", "10", 
				"11", "12", "13", "14", "15", 
				"16", "17", "18", "19", "20", 
				"21", "22", "23"}; 
	private String minutes[] 
			= { "00","01", "02", "03", "04", "05", 
				"06", "07", "08", "09", "10", 
				"11", "12", "13", "14", "15", 
				"16", "17", "18", "19", "20", 
				"21", "22", "23", "24", "25", 
				"26", "27", "28", "29", "30", 
				"31","32", "33", "34", "35", 
				"36","37", "38", "39", "40", 
				"41","42", "43", "44", "45", 
				"46","47", "48", "49", "50",
				"51","52", "53", "54", "55", 
				"56","57", "58", "59"}; 
	private String dates[] 
		= { "01", "02", "03", "04", "05", 
			"06", "07", "08", "09", "10", 
			"11", "12", "13", "14", "15", 
			"16", "17", "18", "19", "20", 
			"21", "22", "23", "24", "25", 
			"26", "27", "28", "29", "30", 
			"31" }; 
	private String months[] 
		= { "01", "02", "03", "04", 
			"05", "06", "07", "08", 
			"09", "10", "11", "12" }; 
	private String years[] 
		= { "2021", "2022", "2023", "2024","2025" }; 
	
	private RDV rdv;
             
	// constructor, to initialize the components 
	// with default values. 
	public LoadFromForm(List<Medecin> medecins, List<Patient> patients) { 
		
		setTitle("Cr�ation de RDV MEDICAL"); 
		setBounds(300, 90, 900, 600);  
		setResizable(false); 

		c = getContentPane(); 
		c.setLayout(null); 

		title = new JLabel("Ajout de Rendez-vous"); 
		title.setFont(new Font("Arial", Font.PLAIN, 30)); 
		title.setSize(300, 30); 
		title.setLocation(300, 30); 
		c.add(title); 
		
		medecin = new JLabel("Medecin"); 
		medecin.setFont(new Font("Arial", Font.PLAIN, 20)); 
		medecin.setSize(100, 20); 
		medecin.setLocation(100, 100); 
		c.add(medecin); 
		
		jMedecins = new JComboBox(medecins.toArray()); 
		jMedecins.setFont(new Font("Arial", Font.PLAIN, 15)); 
		jMedecins.setSize(250, 20); 
		jMedecins.setLocation(200, 100); 
		c.add(jMedecins);

		motif = new JLabel("Motif"); 
		motif.setFont(new Font("Arial", Font.PLAIN, 20)); 
		motif.setSize(100, 20); 
		motif.setLocation(100, 150); 
		c.add(motif); 

		tMotif = new JTextField(); 
		tMotif.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tMotif.setSize(250, 20); 
		tMotif.setLocation(200, 150); 
		c.add(tMotif); 

					

		dateRdv = new JLabel("Date"); 
		dateRdv.setFont(new Font("Arial", Font.PLAIN, 20)); 
		dateRdv.setSize(100, 20); 
		dateRdv.setLocation(100, 200); 
		c.add(dateRdv); 

		date = new JComboBox(dates); 
		date.setFont(new Font("Arial", Font.PLAIN, 15)); 
		date.setSize(50, 20); 
		date.setLocation(200, 200); 
		c.add(date); 

		month = new JComboBox(months); 
		month.setFont(new Font("Arial", Font.PLAIN, 15)); 
		month.setSize(60, 20); 
		month.setLocation(250, 200); 
		c.add(month); 

		year = new JComboBox(years); 
		year.setFont(new Font("Arial", Font.PLAIN, 15)); 
		year.setSize(60, 20); 
		year.setLocation(320, 200); 
		c.add(year); 

		heure = new JLabel("Heure"); 
		heure.setFont(new Font("Arial", Font.PLAIN, 20)); 
		heure.setSize(100, 20); 
		heure.setLocation(100, 250); 
		c.add(heure); 

		tHeure = new JComboBox(heures); 
		tHeure.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tHeure.setSize(50, 20); 
		tHeure.setLocation(200, 250); 
		
		c.add(tHeure); 
		
		tMinute = new JComboBox(minutes); 
		tMinute.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tMinute.setSize(60, 20); 
		tMinute.setLocation(250, 250); 
		
		c.add(tMinute); 

		patient = new JLabel("Patient"); 
		patient.setFont(new Font("Arial", Font.PLAIN, 20)); 
		patient.setSize(100, 20); 
		patient.setLocation(100, 300); 
		c.add(patient); 
		
		jPatients = new JComboBox(patients.toArray()); 
		jPatients.setFont(new Font("Arial", Font.PLAIN, 15)); 
		jPatients.setSize(250, 20); 
		jPatients.setLocation(200, 300); 
		c.add(jPatients);
		
		sub = new JButton("Ajouter"); 
		sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
		sub.setSize(100, 20); 
		sub.setLocation(150, 450); 
		sub.addActionListener(this); 
		c.add(sub); 

		reset = new JButton("Reset"); 
		reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
		reset.setSize(100, 20); 
		reset.setLocation(270, 450); 
		reset.addActionListener(this); 
		c.add(reset); 

		tout = new JTextArea(); 
		tout.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tout.setSize(300, 400); 
		tout.setLocation(500, 100); 
		tout.setLineWrap(true); 
		tout.setEditable(false); 
		c.add(tout); 

		res = new JLabel(""); 
		res.setFont(new Font("Arial", Font.PLAIN, 20)); 
		res.setSize(500, 25); 
		res.setLocation(100, 500); 
		c.add(res); 

		resadd = new JTextArea(); 
		resadd.setFont(new Font("Arial", Font.PLAIN, 15)); 
		resadd.setSize(200, 75); 
		resadd.setLocation(580, 175); 
		resadd.setLineWrap(true); 
		c.add(resadd); 

		setVisible(true); 
	} 

	// method actionPerformed() 
	// to get the action performed 
	// by the user and act accordingly 
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == sub) {  

				String data1 
					= "Medecin : "
					+ jMedecins.getSelectedItem()+ "\n"
					+ "Motif : "
					+ tMotif.getText() + "\n"
					+ "Heure : "
					+ tHeure.getSelectedItem() + ":"+tMinute.getSelectedItem()+"\n"; 
				
				String data2 
					= "Date RDV : "
					+ date.getSelectedItem() 
					+ "-" + (String)month.getSelectedItem() 
					+ "-" + (String)year.getSelectedItem() 
					+ "\n"; 
				
				tout.setText(data1  + data2 ); 
				tout.setEditable(false); 
				
				
				//set added Rdv
				Medecin medecinS =  (Medecin)jMedecins.getSelectedItem();
				Patient patientS = (Patient)jPatients.getSelectedItem();
				String motifS = tMotif.getText();
				String dateS =date.getSelectedItem().toString() 
						+ "-" + month.getSelectedItem().toString() 
						+ "-" + year.getSelectedItem().toString();
				String horaire = tHeure.getSelectedItem()+":"+tMinute.getSelectedItem();
				
				setRdv(new RDV(dateS, horaire, motifS, patientS, medecinS));
				
				//Ajouter le nouvel �l�ment � la fin du fichier texte initial
				String filename = "src/tiers/added_rdv_file.txt";
				try(FileWriter fw = new FileWriter(filename, true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					{
					    // ecriture du nouveau rdv
					    out.println(this.getNewRdv());
					    System.out.println("Added successfully to the file");
					    res.setText("Rendez cr�� avec Succ�s.."); 
					} catch (IOException e1) {
					    System.out.println("Something go wrong with appendind the file texte : "+e1.getMessage());
					}
			
		} else if (e.getSource() == reset) { 
			String def = ""; 
			tMotif.setText(def); 
			res.setText(def); 
			tout.setText(def);
			jMedecins.setSelectedIndex(0); 
			jPatients.setSelectedIndex(0); 
			date.setSelectedIndex(0); 
			month.setSelectedIndex(0); 
			year.setSelectedIndex(0); 
			resadd.setText(def); 
		} 
	} 
			 
   public void setRdv(RDV rdv) {
	   this.rdv = rdv;
   }

	@Override
	public RDV getNewRdv() {
		
		return this.rdv;
	}
}