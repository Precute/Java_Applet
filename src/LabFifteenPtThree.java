
import java.util.*;
import java.io.*;

public class LabFifteenPtThree {
 
public static void main (String [] args) throws Exception
{
int max = 10000000;
int c;//hold int unicode for char
String fileName;
int word = 0;
int characters = -1;
int average;
int criticalValue;
int userSelection;
int wordLength =0;
int totalChars = 0;
 
//prompt user to input file name
System.out.println("Enter name of file: ");
 
//create new scanner and save to newFile String
Scanner input = new Scanner (System.in);
fileName =input.next();
 
//prompt user to input file length
System.out.println("Enter the critical value: ");
criticalValue = input.nextInt();
 


 
//open file for reading
FileReader fReader = new FileReader(fileName);
BufferedReader bReader = new BufferedReader(fReader);
 

c = bReader.read();

 
int i =0;
while (c != -1 && i <= max){
i++;
c = bReader.read(); //read next char
 
if(c !=32 && c!=13) {//excludes unicode numbers for space and return
++characters;
}
else if (c==32 || c==13) {//excludes unicode numbers for space and return
word++;
if (wordLength < characters){
wordLength = characters;
}
totalChars += characters;
characters = 0;
}

}

//TEST System.out.println(fileName + " has " + totalChars + " characters and " + word + " words. its modal word length is " + wordLength);
 
//calculate aveage
average = totalChars/word;

 

//determine the density of the text and print output.
System.out.println("To calculate using the average wordlength enter 1.\nTo calculate using the modal word length enter 2:  ");
userSelection = input.nextInt();
 
switch (userSelection)
{
case 1:
if ( average > criticalValue)  {
System.out.println(fileName + " has an average word length of " + average + ", therefore the word density is heavy.");
}
else  {
System.out.println(fileName + " has an average word length of " + average + ", therefore the word density is light.");
}
break;
 
case 2:
if ( wordLength > criticalValue) {
System.out.println(fileName + " has an modal word length of " + wordLength + ", therefore the word density is heavy.");
}
else {
System.out.println(fileName + " has an modal word length of " + wordLength + ", therefore the word density is light.");
}
break;
 
default:
System.out.println("Invalid selection!");
break;
 
}
 
bReader.close();
fReader.close();
input.close();
}
}