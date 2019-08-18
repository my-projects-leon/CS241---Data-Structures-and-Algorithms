import java.util.Scanner;


public class Project1 
{
	Node root;
	Node current;
	Node previous;
	//pointer used to find successor or predecessor
	Node currentT;

	public static void main(String[] args) 
	{
		Project1 tree = new Project1();
		int n;
		//Used first scanner to read the string of numbers, 
		//and the second one I used a delimiter to separate 
		//the numbers
		System.out.println("Enter sequence of numbers: ");
		Scanner userIn = new Scanner(System.in);
		String stuff = userIn.nextLine();
		Scanner ints = new Scanner(stuff).useDelimiter(" ");
		//inserting numbers one integer at a time
		while ( ints.hasNext())
		{
			n =ints.nextInt();
			tree.sInsert(n);
		}
		//display tree values in every order
		tree.preOrder();
		tree.inOrder();
		tree.PostOrder();
		
		tree.menu();
		//used to take users choice
		char choiceIn;
		//used to hold choiceIn and make it an upper case if needed
		//I'm sure there must be a more elegant way of doing this but
		//this is the recommendation I found online that worked so I
		//used it instead of taking longer messing with it.
		char choice;
		
		boolean exit = true;
		while (exit == true)
		{
			System.out.print("Command? ");
			choiceIn = userIn.next().charAt(0);
			choice = Character.toUpperCase(choiceIn);
			
			switch(choice)
			{
			case 'I':
				System.out.print("Value to insert: ");
				n = userIn.nextInt();
				tree.sInsert(n);
				tree.inOrder();
				break;
			case 'D':
				System.out.print("Value to delete: ");
				n = userIn.nextInt();
				tree.delete(n);
				tree.inOrder();
				break;
			case 'P':
				System.out.print("Find Predecessor for: ");
				n = userIn.nextInt();
				tree.search(n);
				n = tree.getPredecessor();
				System.out.println("Predecessor is " + n);
				break;
			case 'S':
				System.out.print("Find Successor for: ");
				n = userIn.nextInt();
				tree.search(n);
				n = tree.getSucessor();
				System.out.println("Sucessor is " + n);
				break;
			case 'E':
				exit = false;
				System.out.println("Thank you, come again!");
				break;
			case 'H':
				tree.menu();
				break;
			}
		}
	}
	
	public void menu()
	{
		System.out.println("Commands:\nI Insert a value\n"
				+ "D Delete a value\nP Find Predecessor\n"
				+ "S Find Successor\nE Exit the Program\n"
				+ "H Display this message");
	}
	
	//Because you wanted the insert to be recursive and the search always 
	//starts at the root. I couldn't figure out a way to set the cursor to 
	//root to start the insert function without it getting called over and over.
	//So I separated that first step of setting the start of the insert to root 
	//from the recursive part of insert. SInsert is it.
	public void sInsert(int n)
	{
		current = root;
		if (root == null)
		{
			root = new Node();
			root.key = n;
			return;
		}
		else
		{
			//check to see if the value is already inserted
			boolean found = search(n);
			current = root;
			if (found == true)
			{
				System.out.println(n + " already exists, no repeats!");
			}
			else
				insert(n);
		}
	}
	
	//Recursive insert after the start of comparisons was set to root
	public void insert(int n)
	{
		if (n > current.key)
		{
			if (current.right == null)
			{
				previous = current;
				current = current.right;
				current = new Node();
				current.key = n;
				current.parent = previous;
				previous.right = current;
				return;
			}
			previous = current;
			current = current.right;
			insert(n);
		}
		else
		{
			if (current.left == null)
			{
				previous = current;
				current = current.left;
				current = new Node();
				current.key = n;
				current.parent = previous;
				previous.left = current;
				return;
			}
			previous = current;
			current = current.left;
			insert(n);
		}
		
	}
	
	public boolean search (int number)
	{
		current = root;
		while (current != null)
		{
			if (current.key == number)
			{
				return true;
			}
			else if (number < current.key)
			{
				previous = current;
				current = current.left;
			}
			else
			{
				previous = current;
				current = current.right;
			}
		}
		return false;
	}
	
	//used to start delete, same issue as insert not sure how to
	//start the function at root without recursively returning 
	//the pointer to root every iteration
	public void delete(int num)
	{
		current = root;
		delete1(num);
	}
	//recursive part of delete function
	public void delete1(int num)
	{
		//if the number was not found:
		if (current == null)
		{
			System.out.println(num + " is not found");
			return;
		}
		if (current.key == num)
		{
			//if the value is a leaf
			if(current.left == null && current.right == null)
			{
				//if root is the number being deleted and has no children
				if(current == root)
				{
					root = null;
				}
				if(current == current.parent.left)
				{
					current.parent.left = null;
					return;
				}
				else
				{
					current.parent.right = null;
					return;
				}
			}
			//found but has no left child
			else if(current.left == null)
			{
				//if root is being deleted
				if (current.parent == null)
				{
					root = current.right;
					current.right.parent = null;
				}
				if(current == current.parent.left)
				{
					current.parent.left = current.right;
					current.right.parent = current.parent;
					return;
				}
				else
				{
					current.parent.right = current.right;
					current.right.parent = current.parent;
					return;
				}
			}
			//found with left child so get predecessor and replace it
			else
			{
				int temp = getPredecessor();
				Node tempN = current;
				delete(temp);
				tempN.key = temp;
				//reassigning root just in case that was what was deleted
				if (tempN.parent == null)
				{
					root = tempN;
				}
				return;
			}
		}
		else if (num < current.key)
		{
			//going left and recursive call
			previous = current;
			current = current.left;
			delete1(num);
		}
		else
		{
			//going right and recursive call
			previous = current;
			current = current.right;
			delete1(num);
		}
		return;
	}
	//gets successor and returns the integer value
	public int getSucessor()
	{
		currentT = current;
		if (currentT.right == null)
		{
			while(currentT.parent != null)
			{
				if (currentT.parent.key > currentT.key)
				{
					currentT = currentT.parent;
					return currentT.key;
				}
				currentT = currentT.parent;
			}
			return 0;
		}
		else
		{
			currentT = currentT.right;
			while(currentT.left != null)
			{
				currentT = currentT.left;
			}
			return currentT.key;
		}
	}
	//used to find the predecessor and return the integer value
	public int getPredecessor()
	{
		currentT = current;
		if (currentT.left == null)
		{
			while(currentT.parent != null)
			{
				if (currentT.parent.key < currentT.key)
				{
					currentT = currentT.parent;
					return currentT.key;
				}
				currentT = currentT.parent;
			}
			return root.key;
		}
		else
		{
			currentT = currentT.left;
			while(currentT.right != null)
			{
				currentT = currentT.right;
			}
			return currentT.key;
		}
	}
	//displays tree values in order
	public void inOrder()
	{
		int isThereMore = 1;
		current = root;
		while (current.left != null)
		{
			previous = current;
			current = current.left;
		}
		System.out.print("In-order: " + current.key);
		while (isThereMore != 0)
		{
			isThereMore = getSucessor();
			if (isThereMore == 0)
				break;
			current = currentT;
			System.out.print(" " + current.key);
		}
		System.out.print("\n");
	}
	//displays the begging of pre-order display 
	//and calls the recursive call for the rest
	public void preOrder()
	{
		current = root;
		System.out.print("Pre-Order: " + root.key);
		preOrderR();
		System.out.print("\n");
	}
	//used to recursively call rest of values in pre-order
	public void preOrderR()
	{
		if ( current.left != null)
		{
			previous = current;
			current = current.left;
			System.out.print(" " + current.key);
			preOrderR();
		}
		if (current.right != null)
		{
			previous = current;
			current = current.right;
			System.out.print(" " + current.key);
			preOrderR();
		}
		current = current.parent;
		return;
	}
	//starts post-order display and calls recursive method 
	//to display the rest of the integers
	public void PostOrder()
	{
		System.out.print("Post-Order: ");
		current = root;
		postOrderR();
		System.out.print("\n");
	}
	//recursive call for post-order
	public void postOrderR()
	{
		if ( current.left != null)
		{
			previous = current;
			current = current.left;
			postOrderR();
		}
		if (current.right != null)
		{
			previous = current;
			current = current.right;
			postOrderR();
		}
		System.out.print(current.key + " ");
		current = current.parent;
		return;
	}
}
