package lab3.exercises;

import java.util.Scanner;

import ciic4020.stack.LinkedListStack;

public class StackSort 
{
	public static void main(String[] args) 
	{
		//Scanner that receives user integers
		System.out.println("Enter integers to be sorted:");
		Scanner scanner = new Scanner(System.in);
		String userInput = ""; //empty string 
		userInput = scanner.nextLine();

		//Left stack integers must be less or equal than right stack integers.
		//integers are organized in ascending order (bottom to top: 1,2,3,4) 
		LinkedListStack<Integer> leftStack = new LinkedListStack<Integer>();
		LinkedListStack<Integer> rightStack = new LinkedListStack<Integer>();

		//Add first integer. Since no other just add to left. Dev's choice. I'm left handed. 
		leftStack.push(Integer.parseInt(userInput));

		//Scan while an integer is provided by user. When empty string is given (double tap of ENTER)
		//loop will stop. No more input to read.
		while(!userInput.isEmpty())
		{	
			userInput = scanner.nextLine();
			if(!userInput.isEmpty())
				stackSorting(Integer.parseInt(userInput), leftStack, rightStack);
		}
		scanner.close();

		//Printing sorted integers
		System.out.println("/////////////////////");
		System.out.println("Sorted Integers: ");
		//Move all integers to one stack (left to right), print top integer (lowest integer)
		//then pop from stack. 
		while(!leftStack.isEmpty())
		{	rightStack.push(leftStack.pop());	}
		while(!rightStack.isEmpty())
			System.out.println(rightStack.pop());

	}//main

	/**
	 * <b>stackSorting</b> receives an integer and sorts it with two stacks. 
	 * Left stack integers must be less or equal than right stack integers.
     * <p>
     * Integers are organized in ascending order (bottom to top: 1,2,3,4) in left stack. 
     * <br>Integers are organized in descending order (top to bottom: 1,2,3,4) in right stack
	 * <p>
	 * The Method will add an integer to the corresponding stack. It will verify if 
	 * the integer is bigger or smaller than the top integers in each stack and
	 * push/pop integers accordingly. 
	 * <p>
	 * @param integer is a integer value to be sorted. l
	 * @param leftStack stores integers smaller or equal than integers in rightStack.
	 * @param rightStack stores integers values bigger or equal that leftStack's top value. 
	 * @return no return type
	 **/
	public static void stackSorting(int integer, LinkedListStack<Integer> leftStack,
			LinkedListStack<Integer> rightStack)
	{
		//Sorting second integer
		if(rightStack.top() == null)
		{	
			if(leftStack.size() == 1 && integer > leftStack.top())
			{	rightStack.push(integer);	}
			else 
			{	
				rightStack.push(leftStack.pop());
				leftStack.push(integer);
			}
		}

		//If integer = top of left and smaller than top right just add to left.
		else if(integer == leftStack.top() && integer < rightStack.top())
			leftStack.push(integer);

		//If integer = top of right and bigger than top left just add to right.
		else if(integer > leftStack.top() && integer == rightStack.top())
			rightStack.push(integer);

		//If integer is bigger than top left and smaller than top right add to left.
		//No reason, I'm just left handed. 
		else if(integer > leftStack.top() && integer < rightStack.top())
			leftStack.push(integer);

		//If integer is bigger than left and bigger than right move values from right to left
		//until integer is smaller or equal to top right. If integer is equal just add to right. 
		//If integer is smaller to top right add it to right.
		else if(integer > leftStack.top() && integer > rightStack.top())
		{
			leftStack.push(rightStack.pop());

			boolean isSorted = false;
			while(!isSorted) 
			{
				//If integer is the biggest number of the user inputs add integer at
				//the bottom of the stack.
				if(rightStack.top() == null)
				{
					rightStack.push(integer);
					isSorted = true;
				}
				
				//Top of right stack is still smaller than integer. Move top of right to the left
				else if(rightStack.top() < integer && rightStack.top() != null)
				{	leftStack.push(rightStack.pop());	}
				
				//Integer is smaller than top of right stack, push integer to right stack
				else if(rightStack.top() > integer)
				{	
					rightStack.push(integer);
					isSorted = true;
				}
			}
		}
		
		//If integer is smaller than left and smaller than right move values from left to right
		//until integer is smaller or equal to top left. If integer is equal just add to left. 
		//If integer is smaller than top left sort it and add it to left.
		else if(integer < leftStack.top() && integer < rightStack.top())
		{
			boolean isSorted = false;
			while(!isSorted) 
			{
				//If integer is the smallest number of the user inputs add integer at
				//the bottom of the stack.
				if(leftStack.top() == null)
				{
					leftStack.push(integer);
					isSorted = true;
				}
				
				//If integer equals the top of the stack add integer to the top. 
				else if(leftStack.top() == integer)
				{
					leftStack.push(integer);
					isSorted = true;
				}

				//Top of left stack is still bigger than integer. Move top of left to the right
				else if(leftStack.top() > integer && leftStack.top() != null )
				{	rightStack.push(leftStack.pop());	}
				
				//Integer is bigger than top of left stack, push integer to left stack
				else if(leftStack.top() < integer)
				{	
					leftStack.push(integer);
					isSorted = true;
				}
			}
		}

		//Developer Tester Outputs for TA's ease. 
		//Able to see how each stack changes as integers are added and sorted. 
		//Shows top integer and size for each stack.
		System.out.println("Top of Left Stack: "+leftStack.top());
		System.out.println("Size of Left Stack: "+leftStack.size());
		System.out.println("Top of Right Stack: "+rightStack.top());
		System.out.println("Size of Right Stack: "+rightStack.size());
		
	}//stackSorting method

}//StackSort
