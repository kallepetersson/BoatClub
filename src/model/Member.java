package model;
import java.util.ArrayList;

/**
 * This class represent a member in the register
 *
 * @author Karl Petersson
 * @author Christoffer Tronje
 */
public class Member{
    private String firstName;
    private String lastName;
    private String personalNum;
    private int id;
    private ArrayList<Boat> boats = new ArrayList<>();

    public Member(int id, String firstName, String lastName, String personalNum){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNum = personalNum;
    }


    /**
     * Sets the first name of the member
     *
     * @param s the first name
     */
    public void setFirstName(String s){
        this.firstName = s;
    }

    /**
     * Sets the last name of the member
     *
     * @param s the last name
     */
    public void setLastName(String s){
        this.lastName = s;}


    /**
     * Returns a string which is the first name of the member
     *
     * @return first name
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Returns a string which is the last name of the member
     *
     * @return last name
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Returns a string which is the member's personal number
     *
     * @return personal number
     */
    public String getPersonalNum(){
        return this.personalNum;
    }

    /**
     * Returns an integer which is the member's ID
     *
     * @return the members ID
     */
    public int getID(){
        return this.id;
    }

    /**
     * Returns and int which is how many boats the member has in the ArrayList
     *
     * @return Arraylist of boats size.
     */
    public int getNumOfBoats(){
    return boats.size();
    }

    /**
     * Return the members ArrayList of boats
     *
     * @return ArrayList of boat
     */
    public ArrayList<Boat> getBoats(){
        return boats;
    }


}
