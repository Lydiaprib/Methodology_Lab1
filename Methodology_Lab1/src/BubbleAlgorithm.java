import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class BubbleAlgorithm {
	public static void main (String [] args){
		int[] v = null;
		long t0, t1, deltaT;

		v = menu(v);

		t0 = System.nanoTime();
		bubble(v);
		t1 = System.nanoTime();
		deltaT = t1 - t0;
		System.out.println("The time taken in the bubble method was " + deltaT + " ns.");

		t0 = System.nanoTime();
		selection(v);
		t1 = System.nanoTime();
		deltaT = t1 - t0;
		System.out.println("The time taken in the selection method was " + deltaT + " ns.");
	}//end main

	public static int[] readFile(int[] v, int value){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea;
		int i = 0;

		switch(value){
		case 1:
			archivo = new File ("C:\\TestCS1_1.dat");
			break;
		case 2:
			archivo = new File ("C:\\TestCS2_2.dat");
			break;
		}
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			linea = br.readLine();
			value = Integer.parseInt(linea);
			v = new int[value];
			while((linea = br.readLine()) != null){
				v[i] = Integer.parseInt(linea);
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return v;
	}//end readFile

	public static int[] rand(int[] v){
		int max = 100000;
		Random r = new Random();
		for(int i = 0; i < v.length; i++){
			v[i] = r.nextInt(max);
		}

		return v;
	}//end rand

	public static void bubble(int[] list){
		int aux, n;
		n = list.length;
		for(int i = 0; i < n-1; i++){
			for(int j = 0; j < (n-1-i); j++){
				if(list[j] > list[j+1]){
					aux = list[j];
					list[j] = list[j + 1];
					list[j + 1] = aux;					
				}
			}
		}
	}//end bubble

	public static void selection(int[] list){
		int aux, index_min, n;
		n = list.length;
		for(int j = 0; j < n-1; j++){
			index_min = j;
			for(int i = j+1; i < n; i++){
				if(list[i] < list[index_min])
					index_min = i;				
			}		
			aux=list[j];
			list[j]=list[index_min];
			list[index_min]=aux;	
		}		
	}//end selection

	public static int[] menu(int[] v){
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		int value = 0, op = 0, opt = 0;
		do{
			System.out.println("Do you want to insert the length of the array manually or choose a file?\n"
					+ "1. I want to insert the length manually\n"
					+ "2. I want to choose a file");
			op = read.nextInt();
			if(op == 1){
				System.out.println("Insert the length of the array");

				value = read.nextInt();

				v = new int[value];
				v = rand(v);
			}else if(op == 2){
				do{
					System.out.println("Do you want to read file 1 or file 2?");
					opt = read.nextInt();
					if(opt !=1 && opt != 2)
						System.out.println("Please, insert 1 or 2.\n");
				}while(opt != 1 && opt != 2);
				v = readFile(v, opt);
			}else
				System.out.println("Please, insert 1 or 2.\n");
		}while(op != 1 && op != 2);

		return v;
	}//end menu

}//end class
