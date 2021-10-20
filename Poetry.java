package Task4;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;

public class Poetry {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		//Varaibles
		String str;
		PrintWriter writer,vowels,reverse;
	
		//Try to write to files and create files 
		try {
		//Accessing the file poem and reading it in
		File x=new File("C:\\poem.txt");
		Scanner sc=new Scanner(x);
		
		//Creating a new file where i want to store text
		writer=new PrintWriter("encoded.txt","UTF-8");
		vowels=new PrintWriter("capitalVowels.txt","UTF-8");
		reverse=new PrintWriter("reversePoem.txt","UTF-8");
		
		//Writing my new text to files
		while(sc.hasNextLine()) {
			str=sc.nextLine();
			writer.println(encodedPoem(str));
			vowels.println(capitalVowels(encodedPoem(str)));
			reverse.println(reverseWords(capitalVowels(encodedPoem(str))));
		}
		//Closing scanners and writers
		sc.close();
		writer.close();
		vowels.close();
		reverse.close();
		}
		//If we cant write or creating new file give me a error 
		catch (FileNotFoundException e){
			System.out.println("Error accessing file");
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method to change the original poem to secret encoded mesg and save to file
	private static String encodedPoem(String myString) {
		//Declare var & Arr
		int stringToUnicode;
		int [] unicodeArr=new int[myString.length()];
		
		for (int i=0;i<myString.length();i++) {//For loop to go through all the text inside the arr
			stringToUnicode=Character.codePointAt(myString, i);//Converting all the text to unicode numbers
			
			//Calculation for each char with space
			if (stringToUnicode==32) {
				unicodeArr[i]=32;
			}
			//Calculation for each char with uppercase
			else if(stringToUnicode>=65 && stringToUnicode<=75) {//This meen wont be a letter we keep it
				unicodeArr[i]=stringToUnicode+15;
			}
			else if(stringToUnicode>75 && stringToUnicode<=90){
				unicodeArr[i]=(stringToUnicode+15)-26;
			}
			//Calculation for each char with lowercase
			else if(stringToUnicode>=97 && stringToUnicode<=107) {
				unicodeArr[i]=stringToUnicode+15;
			}
			else if(stringToUnicode>107 && stringToUnicode<=122) {
				unicodeArr[i]=(stringToUnicode+15)-26;
			}
			//Calculation for all the other char in file to stay the same
			else {
				unicodeArr[i]=stringToUnicode;
			}
		}
		//Converting all the unicode to text
		String newStr=convertFromUnicodeToText(unicodeArr);
		return newStr;//Return string to the file writer
}
	
	//Method that convert all the uncode data to a string
	private static String convertFromUnicodeToText(int [] myUniCodeArr) {
		return new String (myUniCodeArr,0,myUniCodeArr.length);
	}
	
	//Method that convert secret msg vowels to capital letters
	private static String capitalVowels(String vowels) {
		int vowelsToCap;
		int [] vowelsCap=new int[vowels.length()];
		
		//For loop to go through whole arr to check for arr
		for (int i=0;i<vowels.length();i++) {
			vowelsToCap=Character.codePointAt(vowels, i);//Converting all the text to unicode numbers
			
			if(vowelsToCap==97 ) {//If its char is a convert to A
				vowelsCap[i]=65;
			}
			else if (vowelsToCap==101) {//If its char is e convert to E
				vowelsCap[i]=69;
			}
			else if(vowelsToCap==105) {//If its char is i  convert to I
				vowelsCap[i]=73;
			}
			else if(vowelsToCap==111) {//If its char is o convert to O
				vowelsCap[i]=79;
			}
			else if(vowelsToCap==117) {//If its char is u convert to U
				vowelsCap[i]=85;
			}
			else if(vowelsToCap==121) {//If its char is y convert to Y
				vowelsCap[i]=89;
			}	
			else {//If not a vowel keep it as is 
				vowelsCap[i]=vowelsToCap;
			}
		}
		String uinToVowels=convertFromUnicodeToText(vowelsCap);//Converting the unicode back to String 
		
		return uinToVowels;//Return the vowels to file
	}
	
	//Method to change the text to reverse and back to original poem(reversed)
	private static String reverseWords(String myWord) {
		
		//Reversing the string 
		String [] words=myWord.split(" ");
		String reversedString="";
		//For loop that run through the each word and split in 
		for (int k=0;k<words.length;k++) {
			String word=words[k];
			String reverseWord="";
			for (int j=word.length()-1;j>=0;j--) {//Reversing the word
				reverseWord=reverseWord+word.charAt(j);
		}
			reversedString=reversedString+reverseWord+" ";
		}
		//Changing capital vowels to lowercase
		int vowelsToLower;
		int [] vowelsLowerArr=new int[reversedString.length()];
		String word;
		for (int i=0;i<reversedString.length();i++) {
			vowelsToLower=Character.codePointAt(reversedString, i);
			if(vowelsToLower==65 ) {//If its char is A convert to a
				vowelsLowerArr[i]=97;
			}
			else if (vowelsToLower==69) {//If its char is E convert to e
				vowelsLowerArr[i]=101;
			}
			else if(vowelsToLower==73) {//If its char is I  convert to i
				vowelsLowerArr[i]=105;
			}
			else if(vowelsToLower==79) {//If its char is O convert to o
				vowelsLowerArr[i]=111;
			}
			else if(vowelsToLower==85) {//If its char is U convert to u
				vowelsLowerArr[i]=117;
			}
			else if(vowelsToLower==89) {//If its char is Y convert to y
				vowelsLowerArr[i]=121;
			}	
			else {//If not a vowel keep it as is 
				vowelsLowerArr[i]=vowelsToLower;
			}
		}
		//Converting the unicode to String so we can use again
		String reverseUnicodeToString=convertFromUnicodeToText(vowelsLowerArr);
		
		//Setting all code 15 back to originil
		int convertRevStrToUnicode;
		int [] reverseUniArr=new int[reverseUnicodeToString.length()];
		
		for(int x=0;x<reverseUnicodeToString.length();x++) {
			convertRevStrToUnicode=Character.codePointAt(reverseUnicodeToString, x);
		//Calculation for each char with uppercase
		if(convertRevStrToUnicode>=65 && convertRevStrToUnicode<=79) {//This meen wont be a letter we keep it
			reverseUniArr[x]=(convertRevStrToUnicode-15)+26;
		}
		else if(convertRevStrToUnicode>=80 && convertRevStrToUnicode<=90){
			reverseUniArr[x]=convertRevStrToUnicode-15;
		}
		//Calculation for each char with lowercase
		else if(convertRevStrToUnicode>=97 && convertRevStrToUnicode<=111) {
			reverseUniArr[x]=(convertRevStrToUnicode-15)+26;
		}
		else if(convertRevStrToUnicode>=112 && convertRevStrToUnicode<=122) {
			reverseUniArr[x]=convertRevStrToUnicode-15;
		}
		//Calculation for all the other char in file to stay the same
		else {
			reverseUniArr[x]=convertRevStrToUnicode;
		}
	}
		String reverseOrg=convertFromUnicodeToText(reverseUniArr);//Converting the unicode to string
		System.out.println(reverseOrg);
		return reverseOrg;//Returning this reverse original poem to file
	}

}
