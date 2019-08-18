//typical linked list class that holds functions to search and insert vertexes in the list
public class LinkedL 
{
	Vertex front;
	Vertex current;
	
	//Inserts a new vertex into the vertex/cities linked list
	public void insert(Vertex city)
	{
		if (front == null)
		{
			front = city;
		}
		else
		{
			current = front;
			while(true)
			{
			if(current.num > city.num)
				{
					city.right = current;
					city.left = current.left;
					current.left.right = city;
					current.left = city;
					break;
				}
			else if (current.right == null)
				{
					current.right = city;
					city.left = current;
					break;
				}
			else
				{
					current = current.right;
				}
			}
		}
	}
	//searches for vertex with the characteristic of the passed over String code
	public Vertex search(String code)
	{
		current = front;
		while ( current != null)
		{
			if (current.code.compareToIgnoreCase(code) == 0)
			{
				return current;
			}
			else
				current = current.right;
		}
		return current;
	}
	//searches for vertex with the characteristic of the passed over int num
	public Vertex searchE(int num)
	{
		current = front;
		while(current != null)
		{
			if (current.num == num)
			{
				return current;
			}
			else
				current = current.right;
		}
		return current;
	}
}
