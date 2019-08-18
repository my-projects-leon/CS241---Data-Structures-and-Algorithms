import java.util.Arrays;

public class Vertex 
{
	//This class holds all the values that pertain to one city in the graph
	int num; //city ID number
	String code; //Two letter city code
	String name; //Citty name
	int pop; //City population
	int elev; //Elevation
	
	//pointers for this vertex's neighbors in overall city linked list
	Vertex right;
	Vertex left;
	
	//a size 20 array that holds all the distances read from the fil for each vertex
	double[] edges = new double[20];
	//vertex constructor initializes array to all infinity
	public Vertex()
	{
		Arrays.fill(edges, Double.POSITIVE_INFINITY);
	}
	
	//used to insert or delete edges from the edges array above
	public void insert(int city, int dist)
	{
		edges[num-1] = 0;
		if(dist == -1)
		{
			edges[city-1] = Double.POSITIVE_INFINITY;
		}
		else
		{
			edges[city-1] = dist;
		}
	}
	
	
}
