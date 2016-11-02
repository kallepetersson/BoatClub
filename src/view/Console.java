package view;

import model.Boat;
import model.Registry;
import model.Member;

import java.util.*;

import model.Boat.boatType;

/**
 * This class in the Console UI to the Registry
 *
 * @author Karl Petersson
 * @author Christoffer Tronje
 */
public class Console {

    private Registry registry;
    private int id;
    private int boatNum;

    ConsoleErrorHandling error = new ConsoleErrorHandling();

    public Console() {
        registry = new Registry();
    }

    /**
     * Starts the UI and handles navigation in the registry
     */
    public void start() {

        while (true) {
            int choice = 0;
            HashMap<Integer, Member> members = registry.getMembers();

            System.out.println("1. Create | 2. Retrieve | 3. Update | 4. Delete");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
            }
            switch (choice) {
                case 1:
                    System.out.println("1. Create member | 2. Create boat");
                    scan = new Scanner(System.in);
                    if (scan.hasNextInt()) {
                        choice = scan.nextInt();
                    } else {
                        choice = 0;
                    }
                    switch (choice) {
                        case 1:
                            createMember();
                            break;
                        case 2:
                            enterMember(members);
                            createBoat();
                            break;
                    }
                    break;

                case 2:
                    System.out.println("1. Compact list | 2. Verbose list | 3. Specific Member");
                    scan = new Scanner(System.in);
                    if (scan.hasNextInt()) {
                        choice = scan.nextInt();
                    } else {
                        choice = 0;
                    }
                    switch (choice) {
                        case 1:
                            Iterator<Map.Entry<Integer, Member>> compactListIter = registry.getMemberList();
                            System.out.println("Compact List:\n-----------------------------");
                            while (compactListIter.hasNext()) {
                                Member m = compactListIter.next().getValue();
                                System.out.println("Name: " + m.getFirstName() + " " + m.getLastName() + "\nMember ID: " + m.getID()
                                        + "\nNumber of boats: " + m.getNumOfBoats() + "\n-----------------------------");
                            }
                            System.out.println("\n");
                            break;

                        case 2:
                            Iterator<Map.Entry<Integer, Member>> verboseListIter = registry.getMemberList();
                            System.out.println("Verbose List:\n-----------------------------");
                            while (verboseListIter.hasNext()) {
                                Member m = verboseListIter.next().getValue();
                                System.out.println("Name: " + m.getFirstName() + " " + m.getLastName() + "\nPersonal Number: " + m.getPersonalNum()
                                        + "\nMember ID: " + m.getID());
                                ArrayList<Boat> memberBoats = m.getBoats();
                                for (int i = 0; i < memberBoats.size(); i++) {
                                    System.out.println("Boat " + (i + 1) + ": " + memberBoats.get(i).getType() + " " + memberBoats.get(i).getLength());
                                }
                                System.out.println("-----------------------------");
                            }
                            System.out.println("\n");

                            break;

                        case 3:
                            enterMember(members);
                            Member m = registry.specificMemberInfo(id);
                            System.out.println("Name: " + m.getFirstName() + " " + m.getLastName() + "\nMember ID: " + m.getID()
                                    + "\nNumber of boats: " + m.getNumOfBoats() + "\n-----------------------------\n");
                            break;
                    }
                    break;

                case 3:
                    enterMember(members);
                    System.out.println("1. Update first name | 2. Update last name | 3. Update boat");
                    scan = new Scanner(System.in);
                    if (scan.hasNextInt()) {
                        choice = scan.nextInt();
                    } else {
                        choice = 0;
                    }

                    switch (choice) {
                        case 1:
                            String firstName;
                            do {
                                scan = new Scanner(System.in);
                                System.out.println("First name:");
                                firstName = scan.nextLine();
                            } while (!error.validName(firstName));
                            registry.updateFirstName(id, firstName);
                            break;

                        case 2:
                            String lastName;
                            do {
                                scan = new Scanner(System.in);
                                System.out.println("Last name:");
                                lastName = scan.nextLine();
                            } while (!error.validName(lastName));
                            registry.updateLastName(id, lastName);
                            break;

                        case 3:
                            updateBoat(members);
                            System.out.println("1. Change type | 2. Change length");
                            if (scan.hasNextInt()) {
                                choice = scan.nextInt();
                            } else {
                                choice = 0;
                            }

                            switch (choice) {
                                case 1:
                                    updateBoatType();
                                    break;
                                case 2:
                                    updateBoatLength();
                                    break;
                            }
                            break;
                    }
                    break;

                case 4:
                    System.out.println("1. Delete member | 2. Delete boat");
                    scan = new Scanner(System.in);
                    if (scan.hasNextInt()) {
                        choice = scan.nextInt();
                    } else {
                        choice = 0;
                    }
                    switch (choice) {
                        case 1:
                            do {
                                enterMember(members);
                            } while (!error.memberExist(id, members));
                            registry.deleteMember(id);
                            break;

                        case 2:
                            enterMember(members);
                            deleteBoat(members);
                            break;
                    }
                    break;

            }

        }

    }

    /**
     * Handle user input and sends the information to create a Member in the class Registry
     */
    private void createMember() {
        int yourID;
        String firstName;
        String lastName;
        String personalNum;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("First name:");
            firstName = scan.nextLine();
        } while (!error.validName(firstName));
        do {
            System.out.println("Last name:");
            lastName = scan.nextLine();
        } while (!error.validName(lastName));
        do {
            System.out.println("Personal number:");
            personalNum = scan.nextLine();
        } while (!error.validPersonalNum(personalNum));
        yourID = registry.idAvailable();
        registry.createMember(firstName, lastName, personalNum);
        System.out.println("Your ID is: " + yourID);
    }

    /**
     * Handle user input and sends the information to create a Boat in the class Registry
     */
    private void createBoat() {
        int chosenType = 0;
        int typeNum;
        int length;
        boatType type = null;
        do {
            System.out.println("Boat type:");
            typeNum = 1;
            for (boatType value : boatType.values()) {
                System.out.print(typeNum++ + ". " + value + " ");
            }
            System.out.println();
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                chosenType = scan.nextInt();
            } else {
                chosenType = 0;
            }
            System.out.println(chosenType);
        } while (!error.validBoatType(chosenType));
        typeNum = 1;
        for (boatType value : boatType.values()) {
            if (typeNum == chosenType) {
                type = value;
                System.out.println(type);
            }
            typeNum++;
        }
        do {
            System.out.println("Boat length: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                length = scan.nextInt();
            } else {
                length = 0;
            }
        } while (!error.validBoatLength(length));
        registry.createBoat(id, type, length);
    }

    /**
     * Handle user input to check if it's an int and if the member exist
     */
    private void enterMember(HashMap<Integer, Member> members) {
        do {
            System.out.println("Enter member id: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                id = scan.nextInt();
            } else {
                id = 0;
            }
        } while (!error.memberExist(id, members));
    }

    /**
     * Handle user input and sends the information to delete a Boat in the class Registry
     */
    private void deleteBoat(HashMap<Integer, Member> members) {
        boolean keepDelete = true;
        while (keepDelete) {
            ArrayList<Boat> memberBoats = members.get(id).getBoats();
            Scanner scan = new Scanner(System.in);

            if (members.get(id).getBoats().size() == 0) {
                System.out.println("This member doesn't have any boats");
                break;
            }
            int num;
            do {
                System.out.println("Enter the boats number to be deleted");
                for (int i = 0; i < memberBoats.size(); i++) {
                    System.out.println((i + 1) + ". " + memberBoats.get(i).getType() + " " + memberBoats.get(i).getLength());
                }
                if (scan.hasNextInt()) {
                    num = scan.nextInt() - 1;
                } else {
                    num = -1;
                }
            } while (!error.boatExist(id, num, members));
            registry.deleteBoat(id, num);
            System.out.println("Keep deleting? (Y/N)");
            char c = Character.toLowerCase(scan.next().trim().charAt(0));
            if (c == 'y') {
                keepDelete = true;
            } else {
                keepDelete = false;
            }
        }
    }

    /**
     * Handle user input and sends the information to update a Boat's boatType in the class Registry
     */
    private void updateBoatType() {
        int chosenType;
        boatType type = null;
        do {
            int typeNum = 1;
            for (boatType value : boatType.values()) {
                System.out.print(typeNum++ + ". " + value + " ");
            }
            System.out.println();
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                chosenType = scan.nextInt();
            } else {
                chosenType = 0;
            }
            typeNum = 1;
            for (boatType value : boatType.values()) {
                if (typeNum == chosenType) {
                    type = value;
                    System.out.println(type);
                }
                typeNum++;
            }
        } while (!error.validBoatType(chosenType));
        registry.updateBoatType(id, boatNum, type);

    }

    /**
     * Handle user input and sends the information to update a Boat's length in the class Registry
     */
    private void updateBoatLength() {
        int length;
        do {
            System.out.println("New length: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                length = scan.nextInt();
            } else {
                length = 0;
            }
        } while (!error.validBoatLength(length));
        registry.updateBoatLength(id, boatNum, length);

    }

    /**
     * Handle user input to update a boat
     */
    private void updateBoat(HashMap<Integer, Member> members) {
        do {
            ArrayList<Boat> memberBoats = members.get(id).getBoats();
            System.out.println("Choose boat to update: ");
            for (int i = 0; i < memberBoats.size(); i++) {
                System.out.println((i + 1) + ". " + memberBoats.get(i).getType() + " " + memberBoats.get(i).getLength());
            }
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                boatNum = scan.nextInt() - 1;
            } else {
                boatNum = -1;
            }
        } while (!error.boatExist(id, boatNum, members));
    }
}
