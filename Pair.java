
/**
 * Write a description of class Pair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pair
{
    private int count;
    private String c;
    
    public Pair(String c, int count){
    this.count = count;
    this.c = c;
    
    }
    
    public void add(){
    count = count + 1;
    }
    
    public int getCount(){
    return count;
    }
    
    public void setLetter(String c){
    this.c = c;
    }
    
    public String getLetter(){
    return c;
    }
    
    
    
}
