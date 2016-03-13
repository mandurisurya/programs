import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;


public class FCITrecurse {
	static int a=1;
	static int b=0;
	static Deque<Integer> stack = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws FileNotFoundException
	{
		String command;
		
		
		/**
		 *    Give input File name and output file name as per the your machine location
		 *     so that you can see the appropriate output in the appropriate files.
		 * 
		 * */
		File inputFile = new File("/Users/surya/Desktop/buffercheck/recursion/sampleio/sample.in");
		FileOutputStream outputfile = new FileOutputStream("/Users/surya/Desktop/buffercheck/recursion/sampleio/smaple2.out");
		
		if (!inputFile.exists()) {
			System.out.println("Input file, " + inputFile + ", does not exist.");
			System.exit(0);
		}
		
		Scanner input=new Scanner(inputFile);
		PrintStream printer= new PrintStream(outputfile);

		int numCommands = input.nextInt();
		
		      
		  for(int i=0;i<numCommands;i++){
			  command = input.next();
			  
				if (command.equals("FCITmath") == true) {
					FCITmath(input,printer );
				}
				else if (command.equals("FCITshape") == true) {
					FCITshape(input,printer );
				}else if (command.equals("CSflip") == true) {
					CSflip(input,printer );
				}
				else {
					if(command != "FCIThop"){
						
						FCIThop(input,printer );
					
					}
					else{
						String s= input.nextLine();
					}
					
					; // do nothing
				}
		  }
		  
	}
	
	public static void FCITshape(Scanner input, PrintStream output) {
		// Scan the int from the input file'
		int blank=1;
		int line=1;
		output.println("FCITshape:");
		int space = Integer.parseInt(input.nextLine().trim());
		FCITshapeRecursion(output,"x",blank,space-1,space,line);
		output.println();
	}
	
	static int FCITshapeRecursion(PrintStream output ,String str,int b,int sp,int p,int k)
	{
		try{
		
			if(k==p/2+1)
			{
				output.println(String.format("%1$" +((p/2)+1)+ "s",str));
			FCITshapeRecursion(output,str,b,sp,p,k+1);
			}
		
		if(k<p/2+1)
		{
			
			output.print(String.format("%1$"+b+"s",str));
			output.println(String.format("%1$"+sp+"s",str));
		b++;
		sp=sp-2;
		FCITshapeRecursion(output,str,b,sp,p,k+1);
		}
		
	 if(k>p/2+1)
		{
		 if(b != 1){
		// System.out.println(" b::::::: "+b);
			b--;
		 
			sp=sp+2;
			output.print(String.format("%1$"+b+"s",str));
			output.println(String.format("%1$"+sp+"s",str));
			FCITshapeRecursion(output,str,b,sp,p,k+1);
		 }
		}
		
 	if (k==p+1)
 		return 0;
	}
	catch(Exception e)
	{
			System.err.println(e);
			e.printStackTrace();
	}
 	return 0;
	}
	

public static void FCITmath(Scanner input, PrintStream output) {
		// Scan the int from the input file
		a=1;
		 b=0;
		int n = Integer.parseInt(input.nextLine().trim());
		FCITmathRecursion(output, n);
		output.println();
	}
	

static double FCITmathRecursion(PrintStream output, int n ){
	if(b==0)
		b=n;
	int fact = 0;
	int result=0;
	boolean test = false;
	
	if(n==b)
		test =true;
	if( n == 0)
	{
		if(n == 0 && b == 0)
			output.println("FCITmath : 1");
         return 1;
	}
	result = (int) FCITmathRecursion(output,n-1);
	fact =  (int) (result * n);
	stack.add(fact);
	if(test){
		int i = stack.size();
		for(int p= 0 ; p < b; p++,--i){
		a+=stack.removeLast();
		}
		output.println("FCITmath : "+ a);
		return a;
	}
       return fact;
}

public static void CSflip(Scanner input, PrintStream output) {
	
	int space = Integer.parseInt(input.nextLine().trim());
	output.println("CSflip:");
	CSflipRecursion(output,space,"");
	output.println();
}

static void CSflipRecursion(PrintStream output, int length, String prefix)
{
    if (prefix.length() == length) {
    	output.println(prefix);
        return;
    }
    CSflipRecursion( output,length, prefix + 'C');
    CSflipRecursion( output,length, prefix + 'S');
    
}

public static void FCIThop(Scanner input, PrintStream output){
	
	String space = input.nextLine();
	String[] inArray = space.trim().split(" ");
	if(inArray[0].equals("0")){
		output.println("FCIThop: Not Solvable");
	}else{
		boolean value = FCIThopsupport(inArray);
		if(value == true)
			output.println("FCIThop: Solvable");
		else
			output.println("FCIThop: Not Solvable");
		
	}
	output.println();
	
}

private static boolean FCIThopsupport(String[] input) {
	String[] splitied =	input;
	int[] values	=	new int[splitied.length];
	for(int i = 0; i < splitied.length; i++) {
	values[i]	=	Integer.valueOf(splitied[i]);
	}
	int currentIndex =	0;
	int nextIndex =	0;
	int beforeIndex =	0;
	int inputLength	=	values.length;	
	List<Integer> indexes =	new ArrayList<Integer>();
	while(true) {
	nextIndex	=	currentIndex + values[currentIndex];
	beforeIndex =	currentIndex - values[currentIndex];
	if(nextIndex < inputLength) {
	if(values[nextIndex] == 0) return true;
	else {
	currentIndex =	nextIndex;
	if(!indexes.contains(currentIndex)) {
	//System.out.println(indexes);
	indexes.add(currentIndex);
	} else {
	currentIndex	=	FCIThopRecusion(indexes, values);
	if(currentIndex == 0) return false;
	}
	}
	} else if(beforeIndex > 0 && (values[beforeIndex] != values[currentIndex])) {
	currentIndex	=	beforeIndex;
	if(!indexes.contains(currentIndex)) {
	//System.out.println(indexes);
	indexes.add(currentIndex);
	} else {
	currentIndex	=	FCIThopRecusion(indexes, values);
	if(currentIndex == 0) return false;
	}

	} else {
	currentIndex	=	FCIThopRecusion(indexes, values);
	if(currentIndex == 0) return false;
	}
	}
	}

	private static int FCIThopRecusion(List<Integer> indexes, int[] values) {
	indexes.remove(indexes.size() - 1);
	if(indexes.size() > 0) {
	int currentIndex =	indexes.get(indexes.size() - 1);
	int beforeIndex =	currentIndex - values[currentIndex];
	if(beforeIndex > 0 && values[currentIndex] != values[beforeIndex]) {
	currentIndex =	beforeIndex;
	return currentIndex;
	}
	else {
		FCIThopRecusion(indexes, values);
	}
	return currentIndex;
	} else {
	return 0;
	}
	}
}



