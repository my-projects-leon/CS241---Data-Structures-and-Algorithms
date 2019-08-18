import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Project3 
{
	public static void main(String[] args)// throws IOException
	{
		Graph graph = new Graph();
		graph.create();
		
		Scanner userIn = new Scanner(System.in);
		graph.menu();
		//used to take users choice
		char choiceIn;
		//used to hold choiceIn and make it an upper case if needed
		
		char choice;
		String codes;
		String code1;
		String code2;
		String[] parts = new String[10];
		int num;
		String delim = "\\s";
		
		boolean exit = true;
		while (exit == true)
		{
			System.out.print("Command? ");
			choiceIn = userIn.next().charAt(0);
			choice = Character.toUpperCase(choiceIn);
			
			switch(choice)
			{
			case 'Q':
				System.out.print("City code: ");
				codes = query();
				graph.show(codes);
				break;
			case 'D':
				System.out.print("City codes(seperated by a space): ");
				codes = query();
				parts = codes.split(delim);
				code1 = parts[0];
				code2 = parts[1];
				graph.distance(code1, code2);
				break;
			case 'I':
				System.out.print("City codes and distance: ");
				codes = query();
				String[] parts2 = codes.split(delim);
				code1 = parts2[0];
				code2 = parts2[1];
				num = Integer.parseInt(parts2[2]);
				graph.insertE(code1, code2, num);
				break;
			case 'R':
				System.out.print("City codes(seperated by a space): ");
				codes = query();
				String[] parts3 = codes.split(delim);
				code1 = parts3[0];
				code2 = parts3[1];
				graph.deleteE(code1, code2);
				break;
			case 'E':
				exit = false;
				System.out.println("Thank you, come again!");
				break;
			case 'H':
				graph.menu();
				break;
			}
		}
	}
	
	public static String query()
	{
		Scanner userIn = new Scanner(System.in);
		String codes = userIn.nextLine();
		return codes;
	}

}
