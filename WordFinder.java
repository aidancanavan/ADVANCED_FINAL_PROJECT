import java.util.ArrayList;

/**
 * This class has a static class method called contains, which takes 
 * two string arguments, text and target. The function will 
 * return true if the text (first argument) contains all of the letters 
 * (case insensitive, but distribution-sensitive) in the target string 
 * (second argument).  Otherwise, the function will return false.
 *
 * @author Aidan Canavan
 * @version 1.0
 */
////////////////////////////////////////////////////////////////////////////////
public class WordFinder
{
    /**
     * Takes two string arguments, returns true if first argument contains all of the letters in the second argument. Else, 
     * returns false
     * 
     * @param text first string argument
     * @param target second string argument
     * @return true if first argument contains all of the letters in the second argument. Else, returns false
     */
    public static boolean contains(String text, String target){
        text = text.toLowerCase();
        target = target.toLowerCase();
        //base case 1: if target is larger than text then it cant be true
        if(target.length() >text.length()){
            return false;
        }
        //base case 2: if text is empty and target is not return false
        if(text.length() <1 && target.length()>0){
            return false;
        }
        //base case 3: if target is empty then return true
        if(target.length()==0){
            return true;
        }
        //Step 1: convert the target string into an array list of 
        //pairs that contain the char and times it appears in the string
        ArrayList<Pair> targetList = new ArrayList<Pair>();
        String temp = target;
        while(temp.length()>0){
            //take the first letter and count the number of times it appears
            //delete the other instances
            String firstLetter = temp.substring(0,1);
            int numberInstances = numLetters(temp,firstLetter);
            Pair p = new Pair(firstLetter,numberInstances);
            targetList.add(p);
            temp = deleteLetter(temp,firstLetter);
        }

        for(int k=0; k<targetList.size();k++){
            if((targetList.get(k).getCount()) > 
            (numLetters(text,targetList.get(k).getLetter()))){
                return false;
            }
        }
        return true;
    }
    
    /**
     * This method returns the number of times a given letter shows up
     * @param str String to search
     * @param letter letter to search for in str
     * @return amount of times letter is in word
     */
    public static int numLetters(String str, String letter){
        
        int counter=0;
        for(int i =0;i<str.length();i++){
            if(letter.equals(str.substring(i,i+1))){
                counter++;
            }

        }
        return counter;
    }
    
    /**
     * Deletes a letter from a string
     * @param str String to delete from
     * @param delete Letter to delete from str
     */
    public static String deleteLetter(String str,String delete){

        int i=0;

        while(str.contains(delete)){
            if(str.substring(i,i+1).equals(delete)){
                str = str.substring(0,i)+str.substring(i+1);
            }
            else{
                i++;
            }
        }
        return str;
    }

    public static void main(String[] args){
        WordFinder wf = new WordFinder();
        System.out.println(wf.contains("eggnog tiger","orient"));
        System.out.println(wf.contains("eggnog tiger","egret"));
        System.out.println(wf.contains("eggnog tiger", "genie"));
    }
}
