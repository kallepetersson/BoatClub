package model;


import java.util.HashMap;

/**
 * This class handles errors in the model
 *
 * @author Karl petersson
 * @author Christoffer Tronje
 */
public class ErrorHandling {

    /**
     * Returns true if firstName and lastName is a string and personalNum length is 10 and only numeric characters
     * Otherwise false
     *
     * @param firstName first name
     * @param lastName last name
     * @param personalNum personal number
     * @return true/false
     */
    public boolean validMember(String firstName, String lastName, String personalNum) {
        if (isString(firstName) && isString(lastName) && personalNum.length() == 10 && personalNum.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if member exist and length is greater than 0 and only numerical characters
     *
     * @param id id of the member
     * @param length length of boat
     * @param members HashMap of members
     * @return true/false
     */
    public boolean validBoat(int id, int length, HashMap<Integer, Member> members) {
        if (members.get(id) != null && String.valueOf(length).matches("[0-9]+") && length > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns  true if the member exist.
     * Otherwise false.
     *
     * @param id id of the member
     * @param members HashMap of member
     * @return true/false
     */
    public boolean memberExist(int id, HashMap<Integer, Member> members) {
        if (members.get(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the string is only alphabetic characters A-Ö
     * Otherwise false
     *
     * @param s string to be tested
     * @return true/false
     */
    public boolean isString(String s) {
        if (s.matches("^[a-öA-Ö]*")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the boat exist in the ArrayList
     * Otherwise false
     *
     * @param id id of member
     * @param num position in the ArrayList of boats
     * @param members HashMap of members
     * @return true/false
     */
    public boolean boatExist(int id, int num, HashMap<Integer, Member> members) {
        if (num >= 0 && num < members.get(id).getBoats().size()) {
            if (memberExist(id, members) && members.get(id).getBoats().get(num) != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}