package problemSeating;

import java.io.File;
import java.util.ArrayList;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.variables.IntVar;

import feature.Parser;

public class Seat1Pax {
	public static void run (){
		ArrayList<String> seatmap=Parser.readSeatmap(new File("sourceFolder/seatmap1.txt"));
		Model model = new Model("Seat 1 pax problem");
		//DOMAIN
		IntVar[] coord = new IntVar[2];
		coord[0]=model.intVar("column",0,seatmap.get(0).length()-1);
		coord[1]=model.intVar("row",0,seatmap.size()-1);
		
		//CONSTRAINTS
		ArrayList<int[]> excludedCouplesList=new ArrayList<int[]>(); 
		System.out.println(excludedCouplesList);
		int nbExcludedCouple=0;
		for (int rowNumber =0;rowNumber<seatmap.size();rowNumber++) {
			String row=seatmap.get(rowNumber);
			for(int colNumber=0;colNumber<row.length();colNumber++){
				if(row.charAt(colNumber)=='8' || row.charAt(colNumber)=='O'){
					excludedCouplesList.add(new int[]{colNumber,rowNumber});

					System.out.println("ecludes:{"+colNumber+","+rowNumber+"}");
				}
			}
		}
		int[][] excludedCouples= new int[excludedCouplesList.size()][];
		excludedCouples=excludedCouplesList.toArray(excludedCouples);
		System.out.println(excludedCouples);
		if(excludedCouples!=null)
		model.table(coord[0],coord[1],new Tuples(excludedCouples,false)).post();
		
		
		//SOLVER
		Solution solution = model.getSolver().findSolution();
		if(solution != null){
		    System.out.println(solution.toString());
		}

	}
	
	
}
