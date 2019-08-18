import java.util.Scanner;

public class Project2 
{
	public static void main(String[] args) 
	{
		int aSwap = 0;
		
		System.out.print("How would you like to build a Max Heap?:\n"
		+ "1. With 100 randomly generated integers.\n"
				+ "2. With fixed values of 1 trough 100\n");
		System.out.print("Choose (1 or 2): ");
		Scanner user = new Scanner(System.in);
		int choice = user.nextInt();
		//Choice 1
		if(choice == 1)
		{
			//Optimal random value heap
			System.out.println("Optimal method for random integers");
			for(int i = 0; i < 20; i++)
			{
				Heap heap = new Heap();
				aSwap += heap.ranOHeap();
				System.out.println("Overall number of swaps " + aSwap);
			}
			System.out.println("Average number of swaps for 20 sets of random integers: " + aSwap/20);
			//Sequential random value heap
			aSwap = 0;
			System.out.println("Sequential method for random integers");
			for(int i = 0; i < 20; i++)
			{
				Heap heap = new Heap();
				aSwap += heap.ranSHeap();
				System.out.println("Overall number of swaps " + aSwap);
			}
			System.out.println("Average number of swaps for 20 sets of random integers: " + aSwap/20);
			
		}
		//choice 2
		else
		{
			//Optimal fixed value Heap
			System.out.println("Optimal method for fixed integers");
			Heap heap = new Heap();
			System.out.print("First 10 values: ");
			int swap = heap.fixOHeap();
			System.out.println("Number of swaps = " + swap);
			
			//Sequential fixed value Heap
			System.out.println("Sequential method for fixed integers");
			Heap heaps = new Heap();
			System.out.print("First 10 values: ");
			int swap2 = heaps.fixSHeap();
			System.out.println("Number of swaps = " + swap2);
			
			//Deletion of first 10 values in the sequential fixed value heap
			Heap heapD = new Heap();
			heapD.delete();
		}
	}

}
