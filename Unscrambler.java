import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Class that allows user to find how many words exist in a word
 * @author Aidan Canavan
 * @1.0
 */
////////////////////////////////////////////////////////////////////////////////
public class Unscrambler  
{
    private File myFile;
    
    /**
     * 
     * Constructor for Unscrambler
     * @param fileName A string representing a filename
     * @throws FileNotFoundException
     */
    public Unscrambler(String fileName)throws FileNotFoundException{
        File myFile = new File(fileName);
        this.myFile = myFile;
        //constructs class
    }
    
    /**
     * Finds the amount of words in a target string, adds them to a list
     * @param target The target string
     * @return true if method worked correctly, else false
     * @throws FileNotFoundException
     */
    public boolean findWords(String target) throws FileNotFoundException{

        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> allCorrect = new ArrayList<>();
        Scanner s = new Scanner(myFile);
        while(s.hasNext()){
            words.add(s.next());//makes a list of the words
        }

        Collections.sort(words);//in case the word list is not alphabetical

        WordFinder wf = new WordFinder();
        //implements last HW (I had to fix a few things)
        ArrayList[] foundWords = new ArrayList[target.length()];
        //makes an array of ArrayLists and initializes them as Strings
        for(int g =0;g<target.length();g++){
            foundWords[g]= new ArrayList<String>();
            foundWords[g].add("");//they cannot be empty when I search later
        }

        //I did this because It can organize the words by length
        //adds words to thier correct spot(number of letters) 
        //in the Array of ArrayLists. Also makes sure they are correct
        for(String p:words){
            if(wf.contains(target,p)){
                foundWords[p.length()-1].add(p);
                allCorrect.add(p);
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) throws FileNotFoundException{
        Unscrambler u = new Unscrambler("words.txt");
        u.findWords("eggnog tiger");
    }
}
