import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
/**
 
 *
 * @author Aidan
 * @version (a version number or a date)
 */
public class Unscrambler  
{
    private File myFile;
    public Unscrambler(String fileName)throws FileNotFoundException{
        File myFile = new File(fileName);
        this.myFile = myFile;
        //constructs class
    }
    
    public boolean findWords(String target) throws FileNotFoundException{

    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> allCorrect = new ArrayList<>();
    Scanner s = new Scanner(myFile);
    while(s.hasNext()){
    words.add(s.next());//makes a list of the words
    }
    
    Collections.sort(words);//in case the word list is not alphabetical
    
    WordFinder wf = new WordFinder();//implements last HW (I had to fix a few things)
    ArrayList[] foundWords = new ArrayList[target.length()];
    for(int g =0;g<target.length();g++){//makes an array of ArrayLists and initializes them as Strings
    foundWords[g]= new ArrayList<String>();
    foundWords[g].add("");//they cannot be empty when I search later
    }
    
    //I did this because It can organize the words by length
    
    for(String p:words){//adds words to thier correct spot(number of letters) in the Array of ArrayLists. Also makes sure they are correct
        if(wf.contains(target,p)){
            foundWords[p.length()-1].add(p);
            allCorrect.add(p);
            return true;
        }
    }
    return false;
    /*
    String output ="";//This creates a string to print using the Array of ArrayLists
    for(int f=foundWords.length-1;f>=1;f--){
    if(foundWords[f].size()>1){
        int let = f+1;
        output = output+"\n"+ let +" letter words made by unscrambling the letters in "+target+"\n";
        String temp ="";
        for(int r=1;r<foundWords[f].size();r++){
            
            String word = (String)foundWords[f].get(r);//had to cast it as a String because it thought it was Object type
            if(temp.length()+word.length() < 40){
                temp = temp+word.toLowerCase()+" ";
            }
            else{
            output = output + temp + "\n";
            temp ="";
            }
        }
        output = output + temp + "\n";
        
    }
    }
    
    System.out.println(output);
    */
   
   
    }
    
    public static void main(String[] args) throws FileNotFoundException{
        Unscrambler u = new Unscrambler("words.txt");
        u.findWords("eggnog tiger");
    }
}
