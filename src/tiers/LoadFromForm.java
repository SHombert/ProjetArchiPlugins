package tiers;


import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import appli.ICreateRDV;
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
    private JTextField tHeure; 
    
    private JLabel patient;
    private JComboBox jPatients;
    
	private JButton sub; 
	private JButton reset; 
	private JTextArea tout; 
	private JLabel res; 
	private JTextArea resadd; 
   
	
	private String dates[] 
		= { "1", "2", "3", "4", "5", 
			"6", "7", "8", "9", "10", 
			"11", "12", "13", "14", "15", 
			"16", "17", "18", "19", "20", 
			"21", "22", "23", "24", "25", 
			"26", "27", "28", "29", "30", 
			"31" }; 
	private String months[] 
		= { "Jan", "feb", "Mar", "Apr", 
			"May", "Jun", "July", "Aug", 
			"Sup", "Oct", "Nov", "Dec" }; 
	private String years[] 
		= { "2021", "2022", "2023", "2024", 
			 }; 
	
	private RDV rdv;
             
	// constructor, to initialize the components 
	// with default values. 
	public LoadFromForm(List<Medecin> medecins, List<Patient> patients) { 
		setTitle("Registration Form"); 
		setBounds(300, 90, 900, 600); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
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
		medecin.setLocation(100, 250); 
		c.add(medecin); 
		
		jMedecins = new JComboBox<Medecin>((Medecin[]) medecins.toArray()); 
		jMedecins.setFont(new Font("Arial", Font.PLAIN, 15)); 
		jMedecins.setSize(50, 20); 
		jMedecins.setLocation(200, 250); 
		c.add(jMedecins);

		motif = new JLabel("Motif"); 
		motif.setFont(new Font("Arial", Font.PLAIN, 20)); 
		motif.setSize(100, 20); 
		motif.setLocation(100, 100); 
		c.add(motif); 

		tMotif = new JTextField(); 
		tMotif.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tMotif.setSize(190, 20); 
		tMotif.setLocation(200, 100); 
		c.add(tMotif); 

					

		dateRdv = new JLabel("Date"); 
		dateRdv.setFont(new Font("Arial", Font.PLAIN, 20)); 
		dateRdv.setSize(100, 20); 
		dateRdv.setLocation(100, 250); 
		c.add(dateRdv); 

		date = new JComboBox(dates); 
		date.setFont(new Font("Arial", Font.PLAIN, 15)); 
		date.setSize(50, 20); 
		date.setLocation(200, 250); 
		c.add(date); 

		month = new JComboBox(months); 
		month.setFont(new Font("Arial", Font.PLAIN, 15)); 
		month.setSize(60, 20); 
		month.setLocation(250, 250); 
		c.add(month); 

		year = new JComboBox(years); 
		year.setFont(new Font("Arial", Font.PLAIN, 15)); 
		year.setSize(60, 20); 
		year.setLocation(320, 250); 
		c.add(year); 

		heure = new JLabel("Heure"); 
		heure.setFont(new Font("Arial", Font.PLAIN, 20)); 
		heure.setSize(100, 20); 
		heure.setLocation(100, 300); 
		c.add(heure); 

		tHeure = new JTextField(); 
		tHeure.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tHeure.setSize(100, 20); 
		tHeure.setLocation(200, 300); 
		
		c.add(tHeure); 

		patient = new JLabel("Patient"); 
		patient.setFont(new Font("Arial", Font.PLAIN, 20)); 
		patient.setSize(100, 20); 
		patient.setLocation(100, 250); 
		c.add(patient); 
		
		jPatients = new JComboBox<Patient>((Patient[]) patients.toArray()); 
		jPatients.setFont(new Font("Arial", Font.PLAIN, 15)); 
		jPatients.setSize(50, 20); 
		jPatients.setLocation(200, 250); 
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
					+ (String)jMedecins.getSelectedItem()+ "\n"
					+ "Motif : "
					+ tMotif.getText() + "\n"
					+ "Heure : "
					+ tHeure.getText() + "\n"; 
				
				String data2 
					= "Date RDV : "
					+ (String)date.getSelectedItem() 
					+ "/" + (String)month.getSelectedItem() 
					+ "/" + (String)year.getSelectedItem() 
					+ "\n"; 
				
				tout.setText(data1  + data2 ); 
				tout.setEditable(false); 
				res.setText("Registration Successfully.."); 
				
				//set added Rdv
				Medecin medecinS =  (Medecin)jMedecins.getSelectedItem();
				Patient patientS = (Patient)jPatients.getSelectedItem();
				String motifS = tMotif.getText();
				String dateS =date.getSelectedItem().toString() 
						+ "/" + month.getSelectedItem().toString() 
						+ "/" + year.getSelectedItem().toString();
				String horaire = tHeure.getText();
				
				setRdv(new RDV(dateS, horaire, motifS, patientS, medecinS));
			
		} else if (e.getSource() == reset) { 
			String def = ""; 
			tMotif.setText(def); 
			tHeure.setText(def); 
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