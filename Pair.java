
/**
 * This is a class used to keep track of the number of 
 * times a specfic string occurs in a given interval
 *
 * @author (Team 5)
 * @version (1.3)
 */


public class Pair
{
    private int count;
    private String c;

    public Pair(String c, int count){
        this.count = count;
        this.c = c;

    }

    /**
     * this method increments the varible count by 1
     * 
     */
    public void add(){
        count = count + 1;
    }

    /**
     * this method returns the current count
     *  
     */
    public int getCount(){
        return count;
    }

    
    /**
     * this method sets the current pair's string value
     */
    public void setLetter(String c){
        this.c = c;
    }

    /**
     * this method returns the pair's current string value
     * 
     * @return the string associated with the pair
     */
    public String getLetter(){
        return c;
    }

}
