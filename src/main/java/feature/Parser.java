package feature;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Parser {
	/**
	 * 
	 * @param path
	 * @return la liste de tout ceux qui sont pertinent dans la qrel
	 */
	public static ArrayList<String> readSeatmap(File file){
		ArrayList<String> output=new ArrayList<String>();
		//File file= new File(path);
		String line="";
		System.out.println(file.getName());
		try {
			BufferedReader br= new BufferedReader(new FileReader(file));
			
			try {
				
				while((line=br.readLine()) != null){
					output.add(line);
				}	

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("seatmap:\t"+output);
		return output;
	}
	
}