import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Write a description of class TextTwist here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TextTwist
{
    public ArrayList<String> letterList;
    public String letters;
    public ArrayList<String> correctWords;
    int[] wordNumbers;
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
    public void printRemainingWords(){
        System.out.println("---------------------------------");
        for(int t = 0; t<wordNumbers.length;t++){
        if(wordNumbers[t] != 0){
            System.out.println("Number of "+t+" letter words left: "+wordNumbers[t]);
            
        }
        }
        System.out.println("---------------------------------");
    }
    public boolean endCondition(){
        int counter = 0;
    for(int i:wordNumbers){
        counter = counter+i;
    
    }
    if (counter == 0){
        return false;
    }
    else{
    return true;
    }
    }
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
            System.out.println("Your word was removed from list of possible words.");
            printRemainingWords();
        }
        
    }
    
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
        
    }
    public static void main(String[] args) throws FileNotFoundException{
    TextTwist t = new TextTwist("input.txt");
    t.play();
    
    }
}
