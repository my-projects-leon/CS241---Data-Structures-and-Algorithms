import java.util.Random;

public class Heap 
{
	int size = 0;
	int swap = 0;
	public int fixSHeap()	//creates a heap in a sequential method with fixed 1-100 values
	{
		int[] array = new int[101];
		for (int i = 0; i < 100; i++)
		{
			array[i] = i+1;
			size ++;
			reHeap(array);
		}
		showT(array);
		return swap;
	}
	
	public int fixOHeap() //creates a heap the optimal method with fixed 1-100 values
	{
		int[] array = new int[101];
		for (int i = 0; i < 100; i++)
		{
			array[i] = i+1;
			size ++;
		}
		reOHeap(array);
		showT(array);
		return swap;
	}
	
	public int ranSHeap()	//created a max heap sequentially with random values
	{
		int[] array = new int[101];
		int n = 0;
		Random ran = new Random();
		boolean rep;
		for(int i = 0; i<100; i++)
		{
			rep = true;
			while(rep == true)
			{
				n = ran.nextInt(200);
				rep = checkRep(array, n);
			}
			array[i] = n;
			size++;
			reHeap(array);
		}
		show(array);
		return swap;
	}
	
	public int ranOHeap() //creates a heap by the optimal method with random values
	{
		int[] array = new int[101];
		int n = 0;
		Random ran = new Random();
		boolean rep;
		for(int i = 0; i<100; i++)
		{
			rep = true;
			while(rep == true)
			{
				n = ran.nextInt(200);
				rep = checkRep(array, n);
			}
			array[i] = n;
			size++;
		}
		reOHeap(array);
		show(array);
		return swap;
	}
	
	public boolean checkRep(int array[], int n) //checks if a random value has been repeated
	{
		for(int i = 0; i < size; i++)
		{
			if(array[i] == n)
			{
				return true;
			}
		}
		return false;
	}
	
	public void reHeap(int array[]) //reheaps the values inserted sequentially every time on is inserted
	{
		if (size == 1) //if only a root exists dont check for reheap
		{
			return;
		}
		//otherwise set the p to the parent of the value just inserted
		int p = (size -2)/2;
		while(p >= 0)
		{
			//if left child is larger than right child, and larger than its parent swap
			if(array[2*p+1] > array[2*p+2] && array[2*p+1] > array[p])
			{
				int temp = array[p];
				array[p] = array[2*p+1];
				array[2*p+1] = temp;
				swap ++;
				p = (p-1)/2;
			}
			//if right child is larger than left child, and larger than parent then swap
			else if(array[2*p+2] > array[2*p+1] && array[2*p+2] > array[p])
			{
				int temp = array[p];
				array[p] = array[2*p+2];
				array[2*p+2] = temp;
				swap ++;
				p = (p-1)/2;
			}
			else //if no swap was made set p to the parent of the current p to check all the way up to the root.
			{
				if (p == 0)//if the parent is set to root and no reheap occured then reheap is done
				{
					return;
				}
				p = (p-1)/2;
			}
		}
	}
	
	public void reOHeap(int array[]) //reheaps the whole tree the optimal method.
	{
		 //goes trough the whole heap in one go from bottom to top. if it makes a swap it keep tally.
		//At the end if swaps where made it will go trough the whole tree once more to check if any 
		//more swaps need to happen. If no swaps are done in that cycle then the reheap is done

		int cswap = 1;
		while (cswap != 0) 
		{
			cswap = 0;
			int current = array.length-1;
			int p = (current - 1)/2;
			while(current != 0)
			{
				//if left child is larger than right child, and larger than its parent swap
				if(array[2*p+1] > array[2*p+2] && array[2*p+1] > array[p])
				{
					int temp = array[p];
					array[p] = array[2*p+1];
					array[2*p+1] = temp;
					cswap++;
					swap ++;
					current = current - 2;
					p = (current-1)/2;
				}
				//if right child is larger than left child, and larger than parent then swap
				else if(array[2*p+2] > array[2*p+1] && array[2*p+2] > array[p])
				{
					int temp = array[p];
					array[p] = array[2*p+2];
					array[2*p+2] = temp;
					cswap++;
					swap++;
					current = current-2;
					p = (current-1)/2;
				}
				else //if no swap was made compare the next to lower values to their parent.
				{
					current= current-2;
					p = (current-1)/2;
				}
			}
		}
	}
	
	//shows the first 10 values in the heap, used during option 2 to show first 10 values.
	public void showT(int array[])
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.print("\n");
	}
	
	//Shows all the values in a heap. Used to see if the values where sorting correctly.
	public void show(int array[])
	{
		for (int i = 0; i < size; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.print("\n");
	}
	
	//Decided to create the fixed sequential tree within the deletions because of 
	//how I formated the rest of the project it made it really hard to do this function 
	//any other way. Deletes the root of the heap and reheaps all the values 10 times in a row.
	public void delete()
	{
		int[] array = new int[101];
		for (int i = 0; i < 100; i++)
		{
			array[i] = i+1;
			size ++;
			reHeap(array);
		}
		
		for (int i=0; i<10;i++)
		{
			int del = array[0];
			array[0] = array[size-1];
			array[size-1] = 0;
			size --;
			downReHeap(array);
			System.out.println(del + " was deleted");
		}
		showT(array);
	}
	
	// ReHeaps after a deletion. moving the new root till it fits in the heap.
	public void downReHeap(int array[])
	{
		int current = 0;
		while(true)
		{
			if(array[2*current+1] > array[2*current+2] && array[2*current+1] > array[current])
			{
				int temp = array[current];
				array[current] = array[2*current+1];
				array[2*current+1] = temp;
				current = (2*current)+1;
			}
			//if right child is larger than left child, and larger than parent then swap
			else if(array[2*current+2] > array[2*current+1] && array[2*current+2] > array[current])
			{
				int temp = array[current];
				array[current] = array[2*current+2];
				array[2*current+2] = temp;
				current = (2*current)+2;
			}
			else //if no swap was made set p to the parent of the current p to check all the way up to the root.
			{
				return;
			}
		}
	}
}
