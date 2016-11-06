package problemSeating;

import java.io.File;
import java.util.ArrayList;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.variables.IntVar;

import feature.Parser;

public class SeatMultiplePax {
	public static void run (){
		ArrayList<String> seatmap=Parser.readSeatmap(new File("sourceFolder/seatmap1.txt"));
		Model model = new Model("Seat 1 pax problem");
		// Create an array of 2 variables taking their value in [0,50]
		IntVar[] coord = new IntVar[2];
		coord[0]=model.intVar("column",0,seatmap.get(0).length()-1);
		coord[1]=model.intVar("row",0,seatmap.size()-1);
		
		//CONSTRAINT
		
		//occupation constraints
		int[][] excludedCouples = new int[21][];
		System.out.println(excludedCouples);
		int nbExcludedCouple=0;
		for (int rowNumber =0;rowNumber<seatmap.size();rowNumber++) {
			String row=seatmap.get(rowNumber);
			for(int colNumber=0;colNumber<row.length();colNumber++){
				if(row.charAt(colNumber)=='8' || row.charAt(colNumber)=='O'){
					excludedCouples[nbExcludedCouple]=new int[]{colNumber,rowNumber};
					nbExcludedCouple++;
					System.out.println("ecludes:{"+colNumber+","+rowNumber+"}");
				}
			}
		}
		System.out.println(excludedCouples);
		if(excludedCouples!=null)
		model.table(coord[0],coord[1],new Tuples(excludedCouples,false)).post();
		
		//
		
		
		//SOLVER
		Solution solution = model.getSolver().findSolution();
		if(solution != null){
		    System.out.println(solution.toString());
		}

	}
	
	
}
