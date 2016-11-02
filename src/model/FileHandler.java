package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles the read/write to file
 *
 * @author Karl Petersson
 * @author Christoffer Tronje
 */
public class FileHandler {

    private String path = new File("").getAbsolutePath().concat("/resources/database.txt");

    /**
     * Reads from a .txt file and creates members and boat from it.
     * Every line represent a member. Below is an example member with 2 boats.
     * ID FIRSTNAME LASTNAME PERSONALNUMBER BOATTYPE LENGTH BOATTYPE LENGTH
     *
     * @return HashMap of members
     */

    public HashMap<Integer, Member> loadDatabase() {
        HashMap<Integer, Member> members = new HashMap<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String s = in.readLine();

            while (s != null) {
                String[] arr = s.split("\\s+");
                Member member = new Member(Integer.valueOf(arr[0]),arr[1],arr[2],arr[3]);
                members.put(Integer.valueOf(arr[0]), member);

                for (int i = 4; i < arr.length - 1; i += 2) {
                    Boat boat = new Boat(Boat.boatType.valueOf(arr[i].toUpperCase()), Integer.valueOf(arr[i + 1]));
                    member.getBoats().add(boat);
                }
                s = in.readLine();
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    /**
     * Writes all members and boats to a .txt file
     * Every line represent a member. Below is an example member with 2 boats.
     * ID FIRSTNAME LASTNAME PERSONALNUMBER BOATTYPE LENGTH BOATTYPE LENGTH
     *
     * @param members The HashMap to write from
     */
    public void writeToFile(HashMap<Integer, Member> members) {
        try {
            ArrayList<Boat> memberBoats;
            File file = new File(path);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);
            for (Member m : members.values()) {
                writer.write(m.getID() + " " + m.getFirstName() + " " + m.getLastName() + " " + m.getPersonalNum());
                memberBoats = m.getBoats();
                for (int i = 0; i < memberBoats.size(); i++) {
                    writer.write(" " + memberBoats.get(i).getType().toString().toUpperCase() + " " + memberBoats.get(i).getLength());
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
