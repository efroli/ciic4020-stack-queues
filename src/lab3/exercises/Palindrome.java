package lab3.exercises;
import java.util.Scanner;
import ciic4020.stack.LinkedListStack;

public class Palindrome 
{

	public static void main(String[] args) throws Exception 
	{
		//Scanner that receives possible palindrome from user
		System.out.println("Enter a word/phrase/sentece to verify if it is a Palindrome:");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		scanner.close();
		
		//Verifies if the string is a palindrome and prints the results (true or false). 
		if(isPalindrome(userInput))
			System.out.println("Palindrome");
		else
			System.out.println("Not a palindrome"); //maybe need else

		
	}//main 
	
	/**
	 * isPalindrome receives a string and verifies if it is a palindrome. 
	 * A palindrome is a string that reads the same forward as backwards.
	 * Case sensitivity (A a), spaces, and punctuation will be ignored.
	 * <p>
	 * The Method will save the string in a stack then move the second half 
	 * of the string to another stack.
	 * <p>
	 * If the string is of even length once the middle point (half +1) is reached that character
	 * will be saved in the second half stack. 
	 * <p>
	 * If the string is of odd length ignore the middle character (example eve, ignore v). 
	 * Store everything before the middle character in the first half stack and everything 
	 * after it in the second half stack.
	 * 
	 * @param possiblePalindrome is a string that maybe a palindrome.
	 * @throws Exception if provided string is empty
	 * @return true if string is a palindrome false if not a palindrome.  
	 **/
	public static boolean isPalindrome(String possiblePalindrome) throws Exception
	{		
		LinkedListStack<Character> firstHalf = new LinkedListStack<Character>();
		LinkedListStack<Character> secondHalf = new LinkedListStack<Character>();
		//Eliminating special characters
		String scrubbedText = possiblePalindrome.replaceAll("[^a-zA-Z0-9]", ""); 
		//Turning upper case into lower case letters.
		scrubbedText = scrubbedText.toLowerCase();

		//One lettered strings are palindromes
		if(scrubbedText.isEmpty())
			throw new Exception("Developer Exception: Empty String. Please provide a valid String. Thank you!");
		if(scrubbedText.length() == 1)
			return true;
	
		
		//Saving palindrome in stacks. 
		//Saving full string in one stack
		for(int i=0; i < scrubbedText.length(); i++)
			firstHalf.push(scrubbedText.charAt(i));
		
		
		//Move second half of string to other stack 
		for(int i = scrubbedText.length()-1; i >= scrubbedText.length()/2; i--)
		{
		//length = even number
			//MODIFY
			if(scrubbedText.length()%2 == 0) 
			{
				if(i >= scrubbedText.length()/2)
					secondHalf.push(firstHalf.pop());		
			}	
		//length = odd number
			//Pass characters from on stack to the other 
			else if(i > scrubbedText.length()/2)
				secondHalf.push(firstHalf.pop());
			//Do not add middle character to second stack (example eve, avoid the "v").
			else if(i == scrubbedText.length()/2)
				firstHalf.pop();
		}
		
		//Comparing stacks to see if both stack's top contains the same character. 
		System.out.println("**** Dev Test Output (For TA's Ease) ****");
		System.out.println("");
		while(!firstHalf.isEmpty()) //both stacks same size. Doesn't matter which is condition
		{
			System.out.println("First Half's Top: ");
			System.out.println(firstHalf.top());
			System.out.println("");
			System.out.println("Second Half's Top: ");
			System.out.println(secondHalf.top());
			System.out.println("");

			if(!firstHalf.top().equals(secondHalf.top()))
				return false;
			firstHalf.pop(); 
			secondHalf.pop();
		}
		System.out.println("**** Dev Test Output (For TA's Ease) ****");
		System.out.println("");
		System.out.println("");
		System.out.println("Official Output: ");

		return true;
	}//isPalindrome

}//Palindrome
