package tiers;

import java.awt.Dimension;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.*;

import data.RDV;

public class DisplayList extends JFrame  {
	  
  
    //main class 
    public static void main(String[] args) throws IOException, ParseException 
    { 
    	List<RDV> listRDV = LoadFromFile.getRdvList("C:\\Users\\Utilisateur\\Desktop\\MASTER 2\\ARCHI LOG\\ProjetArchiPlugins\\src\\tiers\\rdv_file.txt");
    	DisplayList dl = new DisplayList();
    	dl.displayRDVList(listRDV);
    } 
    
    public void displayRDVList(List<RDV> rdvList) {
    	
    	RDV [] rdvArray = new RDV[rdvList.size()];
		for (int i = 0; i < rdvList.size(); i++) 
		 rdvArray[i] = rdvList.get(i);

         JList<RDV> rdvJList = new JList<>(rdvArray);

         getContentPane().add(rdvJList);

         pack();
         setMinimumSize(new Dimension(200, 200));
         setVisible(true);
    }     
 

}
