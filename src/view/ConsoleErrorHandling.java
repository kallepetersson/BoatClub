package view;

import model.Member;
import model.Boat.boatType;

import java.util.HashMap;

/**
 * This class handles error from the view perspective(User inputs)
 *
 * @author Karl Petersson
 * @author Christoffer Tronje
 */
public class ConsoleErrorHandling {

    /**
     * Returns true if the string is only alphabetic characters A-Ö
     * Otherwise false and prints a message
     *
     * @param s string to be tested
     * @return true/false
     */
    public boolean validName(String s) {
        if (s.matches("^[a-öA-Ö]*")) {
            return true;
        } else {
            System.err.println("Input is not a valid name(A-Z)");
            return false;
        }
    }

    /**
     * Returns true if the String length is 10 and only numeric characters
     * Otherwise false and prints a message
     *
     * @param num String to be tested
     * @return true/false
     */
    public boolean validPersonalNum(String num) {
        if (num.length() == 10 && num.matches("[0-9]+")) {
            return true;
        } else {
            System.err.println("Input is not a valid personal number(10 characters and 0-9)");
            return false;
        }
    }

    /**
     * Returns  true if the member exist.
     * Otherwise false and prints a message
     *
     * @param id      id of the member
     * @param members HashMap of member
     * @return true/false
     */
    public boolean memberExist(int id, HashMap<Integer, Member> members) {
        if (members.get(id) != null) {
            return true;
        } else {
            System.err.println("Member doesn't exist");
            return false;
        }
    }

    /**
     * Returns true if the user input is one if the enums listed
     * Otherwise false and prints a message
     *
     * @param num inputted number
     * @return true/false
     */
    public boolean validBoatType(int num) {
        if (num <= boatType.values().length && num > 0) {
            return true;
        } else {
            System.err.println("Not a valid boat type (1-" + boatType.values().length + ")");
            return false;
        }
    }

    /**
     * Returns true if the user input is >0
     * Otherwise false and prints a message
     *
     * @param length inputted length
     * @return true/false
     */
    public boolean validBoatLength(int length) {
        if (length > 0) {
            return true;
        } else {
            System.err.println("Not a valid boat length (>0)");
            return false;
        }
    }

    /**
     * Returns true if member exist
     * Otherwise false and prints a message
     *
     * @param id      id of the member
     * @param members HashMap of members
     * @return true/false
     */
    public boolean boatExist(int id, int num, HashMap<Integer, Member> members) {
        if (num >= 0 && num < members.get(id).getBoats().size()) {
            if (memberExist(id, members) && members.get(id).getBoats().get(num) != null) {
                return true;
            } else {
                System.err.println("Boat doesnt exist");
                return false;
            }
        } else {
            System.err.println("Boat doesnt exist");
            return false;
        }
    }

}
