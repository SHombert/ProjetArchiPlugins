package appli;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import data.RDV;

public interface ILoadRDVs {
	
	public List<RDV> getRdvList(String filename); 

}