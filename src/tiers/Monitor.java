package tiers;

import java.util.ArrayList;

import plateforme.DescripteurPluggin;

public class Monitor implements Runnable{

	ArrayList<DescripteurPluggin> listePluggins;
	
	public Monitor() {
		
	}
	
	public void run() {
		System.out.println("Lancement du moniteur");
		 
	}
}
