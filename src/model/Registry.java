package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.Boat.boatType;


/**
 * This class is a member registry with CRUD(Create Retrieve Update Delete) functionality
 *
 * @author Karl Petersson
 * @author Christoffer Tronje
 */
public class Registry {
    ErrorHandling error = new ErrorHandling();
    private HashMap<Integer, Member> members = new HashMap<>();
    private FileHandler fileHandler;

    public Registry() {
        fileHandler = new FileHandler();
        members = fileHandler.loadDatabase();
    }


    /**
     * Creates a member with an available ID and put it in the HashMap.
     * Also writes to the txt file
     *
     * @param firstName   first name of the member
     * @param lastName    last name of the member
     * @param personalNum personal number of the member
     */
    public void createMember(String firstName, String lastName, String personalNum) {
        if (error.validMember(firstName, lastName, personalNum)) {
            int nextID = idAvailable();
            Member member = new Member(nextID, firstName, lastName, personalNum);
            members.put(nextID, member);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the HashMap of Members
     *
     * @return HashMap
     */
    public HashMap<Integer, Member> getMembers() {
        return members;
    }

    /**
     * Creates a boat to a specific member and adds it to it's own ArrayList of boats.
     * Also writes to the txt file
     *
     * @param id     id of the member to add boat to
     * @param type   boatType of the boat
     * @param length length of the boat
     */
    public void createBoat(int id, boatType type, int length) {
        if (error.validBoat(id, length, members)) {
            Boat boat = new Boat(type, length);
            members.get(id).getBoats().add(boat);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Deletes a member from the HashMap
     * Also writes to the txt file
     *
     * @param id id of the Member to be deleted
     */
    public void deleteMember(int id) {
        if (error.memberExist(id, members)) {
            members.remove(id);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Update a members first name
     * Also writes to the txt file
     * @param id id of the member to be updated
     * @param firstName the new first name of the member
     *
     */
    public void updateFirstName(int id, String firstName) {
        if (error.memberExist(id, members) && error.isString(firstName)) {
            members.get(id).setFirstName(firstName);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Update a members last name
     * Also writes to the txt file
     *
     * @param id id if the member to be updated
     * @param lastName the new last name of the member
     */
    public void updateLastName(int id, String lastName) {
        if (error.memberExist(id, members) && error.isString(lastName)) {
            members.get(id).setLastName(lastName);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Deletes a boat from a members ArrayList of boats.
     * Also writes to the txt file
     *
     * @param id id of the member
     * @param num position in the ArrayList of boats to be deleted
     */
    public void deleteBoat(int id, int num) {
        if (error.boatExist(id, num, members)) {
//            members.get(id).deleteBoat(num);
            members.get(id).getBoats().remove(num);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Updates a boats type.
     * Also writes to the txt file
     *
     * @param id id of the member
     * @param num position in the ArrayList of boats to be updated
     * @param type the new type to be updated
     */
    public void updateBoatType(int id, int num, boatType type) {
        if (error.memberExist(id, members) && error.boatExist(id, num, members)) {
            members.get(id).getBoats().get(num).setType(type);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Updates a boats length
     * Also writes to the txt file
     *
     * @param id id of the member
     * @param num position in the ArrayList of boats to be updated
     * @param length the new length to be updated
     */
    public void updateBoatLength(int id, int num, int length) {
        if (error.memberExist(id, members) && error.boatExist(id, num, members) && length > 0) {
            members.get(id).getBoats().get(num).setLength(length);
            fileHandler.writeToFile(members);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns an integer of the lowest available ID to a member
     *
     * @return the lowest available id
     */
    public int idAvailable() {
        int counter = 1;
        for (Integer key : members.keySet()) {
            if (counter != key) {
                return counter;
            }
            counter++;
        }
        return counter;
    }


    /**
     * Returns an iterator of all members
     *
     * @return iterator
     * */
    public Iterator<Map.Entry<Integer,Member>> getMemberList(){
    Iterator<Map.Entry<Integer, Member>> iterator = members.entrySet().iterator();
        return iterator;
    }

    /**
     * Returns a member
     *
     * @param id
     * @return
     */
    public Member specificMemberInfo(int id){
        return members.get(id);
    }




}

