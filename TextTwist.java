import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class provides the background logic for the testtwist game.
 * it generates a random combination of characters based on an input
 * file. The user then needs to rearrange this letters to find all
 * matched words in the file.
 *
 * @author Aidan Canavan
 * @version 1
 * 
 */

public class TextTwist
{
    public ArrayList<String> letterList;
    public String letters;
    public ArrayList<String> correctWords;
    int[] wordNumbers;

    /**
     * TexTwist constructor
     * @param fileName String that is name of text file to read from
     * @throws FileNotFoundExpception
     */
    public TextTwist(String fileName) throws FileNotFoundException{
        File myFile = new File(fileName);
        Scanner sc = new Scanner(myFile);
        letters = sc.next();
        letterList = new ArrayList<String>();
        correctWords = new ArrayList<String>();

        while(sc.hasNext()){
            String str = sc.next();
            if(str.length() > 1){
                correctWords.add(str);
            }
        }
        for(int p = 0;p < letters.length();p++){
            letterList.add(letters.substring(p,p+1));
        }
        sc.close();
        //
        wordNumbers = new int[letterList.size()];
        for(int j = 0; j<letterList.size();j++){

            for(String word:correctWords){
                if(word.length()==j){
                    wordNumbers[j]++;
                }
            }
        }

    }
    
    /**
     * Prints number of words left
     */
    public void printRemainingWords(){
        System.out.println("---------------------------------");
        for(int t = 0; t<wordNumbers.length;t++){
            if(wordNumbers[t] != 0){
                System.out.print("Number of "+t);
                System.out.println("letter words left: "+wordNumbers[t]);

            }
        }
        System.out.println("---------------------------------");
    }
    
    /**
     * Determines end condition
     * @return true or false
     */
    public boolean endCondition(){
        int counter = 0;

        for(int i:wordNumbers){
            counter = counter+i;

        }
        //System.out.println(counter);
        if (counter == 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    /**
     * Used for testing non-gui implementation
     * @guess String to see if is in game
     */
    public void inputGameValue(String guess){
        guess = guess.toLowerCase();
        WordFinder wf = new WordFinder();
        if(!wf.contains(letters,guess)){
            System.out.println("Input letters invalid.");
        }
        else if(!correctWords.contains(guess)){
            System.out.println(guess+" is not a correct word.");
        }
        else{
            System.out.println(guess+" was found.");
            correctWords.remove(correctWords.indexOf(guess));
            wordNumbers[guess.length()]--;
            System.out.print("Your word was removed from ");
            System.out.println("list of possible words.");
            printRemainingWords();
        }

    }
    
    /**
     * Determines if input is valid for gui
     * @param String to see if is in game
     */
    public boolean inputGameValueForGUI(String guess){
        guess = guess.toLowerCase();
        WordFinder wf = new WordFinder();
        if(!wf.contains(letters,guess)){
            //System.out.println("Input letters invalid.");
            return false;
        }
        else if(!correctWords.contains(guess)){
            //System.out.println(guess+" is not a correct word.");
            return false;
        }
        else{
            //System.out.println(guess+" was found.");
            correctWords.remove(correctWords.indexOf(guess));
            wordNumbers[guess.length()]--;

            return true;
            //printRemainingWords();
        }

    }
    
    
    /**
     * Test for non-gui implementation
     */
    public void play(){
        System.out.println("Welcome to Text Twist.");
        Scanner myScanner = new Scanner(System.in);
        printRemainingWords();

        while(endCondition()){
            System.out.println("Your letters are: "+letters);
            System.out.println("Enter a word.");
            String input = myScanner.next();
            inputGameValue(input);
        }
        System.out.println("You win!!");
    }

    public static void main(String[] args) throws FileNotFoundException{
        TextTwist t = new TextTwist("input.txt");
        t.play();

    }
}
