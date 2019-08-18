import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph
{
	//pointers used for vertexes in code. I know there is too many
	Vertex front;
	Vertex current;
	Vertex current2;
	//linked list of verticies
	LinkedL cities = new LinkedL();
	//2-D array used to calculate shortest path
	double[][] shortD = new double[20][20];
	boolean change = true;
	int numOCity = 0;
	
	//creates the graph by reading the info from the text documents provided
	//NOTE: I had to delete so random white spaces you had at the end of 
	//certain random lines in both files to get my read code to work. 
	//So I included the files with the deleted whitespaces in the project folder
	public void create()
	{
		//reading and writing the info for the cities into nodes
		File file = new File("city.dat.txt");
		try
		{
			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String delim = "\\s{2,}";
				String[] parts = line.split(delim);
				int i = 0;
				Vertex vert = new Vertex();
				vert.num = Integer.parseInt(parts[i]);
				i++;
				vert.code = parts[i];
				i++;
				vert.name = parts[i];
				i++;
				vert.pop = Integer.parseInt(parts[i]);
				i++;
				vert.elev = Integer.parseInt(parts[i]);
				
				cities.insert(vert);
				if ( numOCity == 0)
				{
					front = vert;
				}
				numOCity++;
			}
		}catch (FileNotFoundException e)
		{
			System.out.println("File was not found");
		}
		//reading and writing the roads into edges arrays in the nodes
		File file2 = new File("road.dat.txt");
		
		try
		{
			Scanner scan = new Scanner(file2);
			
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String delim = "\\s+";
				String[] parts = line.split(delim);
				int i = 0;
				int num1 = Integer.parseInt(parts[i]);
				i++;
				int num2 = Integer.parseInt(parts[i]);
				i++;
				int num3 = Integer.parseInt(parts[i]);
				
				//search for node with num = num1
				current = cities.searchE(num1);
				
				//then add num2 as the neighbor vertex and num3 as the distance to it
				current.insert(num2, num3);
			}
		}catch (FileNotFoundException e)
		{
			System.out.println("File was not found");
		}
	}
	
	//shows option menu
	public void menu()
	{
		System.out.println("Options Menu:\nQ Query the city information by entering the city code.\n"
				+ "D Find the minimum distance between two cities.\n"
				+ "I Insert a road by entering two city codes and a distance.\n"
				+ "R Remove an existing road by entering two city codes.\n"
				+ "H Display the options menu\n"
				+ "E Exit");
	}
	
	//shows the properties of a vertex when identified trough its city code
	public void show(String code)
	{
		current = cities.search(code);
		
		System.out.println("Information: " + current.num + " " + current.code
				+ " " + current.name + " " + current.pop + " " + current.elev);
	}
	
	//calculates distances to all vertex and stores in 2-d array
	public void distance(String code1, String code2)
	{
		if (change == true)
		{
			updateDist();
			change = false;
		}
		
		current = cities.search(code1);
		current2 = cities.search(code2);
		System.out.println("The minimum distance between " + current.name
				+ " and " + current2.name + " is " + shortD[current.num-1][current2.num-1]);
	}
	
	//inserts a new edge into a vertex by changeing the infinity value in the array of that vertex
	public void insertE(String code1, String code2, int dist)
	{
		current = cities.search(code1);
		current2 = cities.search(code2);
		current.insert(current2.num, dist);
		
		System.out.println("You have inserted a road from " + current.name + " to "
				+ current2.name + "with a distance of " + dist);
		change = true;
	}
	
	//changes distances in edge array of a vertex to infinity so its deleted
	public void deleteE(String code1, String code2)
	{
		current = cities.search(code1);
		current2 = cities.search(code2);
		int delete = -1;
		current.insert(current2.num, delete);
		System.out.println("The road from " + current.name + " to "
		+ current2.name + " has been removed.");
		
		change = true;
	}
	//updates distace to each vertex after changes in roads
	public void updateDist()
	{
		current = front;
		
		for(int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 20; j++)
			{
				shortD[i][j] = current.edges[j];
			}
			current = current.right;
		}
		
		dijkstras();
	}
	//my Bellman-Ford algorithm I failed to identify correctly
	public void dijkstras()
	{
		int k = 0;
		while(k < 20)
		{
			for(int i = 0; i < 20; i++)
			{
				for (int j = 0; j < 20; j++)
				{
					if(shortD[i][j] > shortD[i][k] + shortD[k][j])
					{
						shortD[i][j] = shortD[i][k] + shortD[k][j];
						if(i == 4 && j == 14)
						{
							System.out.println("from " + (i+1) + " to " + (j+1)
									+ " updated trough " + (k+1) + " dist: " + shortD[i][j]);
						}
					}
				}
			}
			
			k++;
		}
	}
}
