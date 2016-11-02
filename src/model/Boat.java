package model;

/**
 * This class represent a boat that a member has.
 *
 * @author Karl Petersson
 * @author Christoffer Tronje
 */
public class Boat {
    private int length;
    private boatType type;

    public Boat(boatType type, int length)  {
        this.type = type;
        this.length = length;
    }

    /**
     * The different boat types a boat can have.
     */
    public enum boatType {
        SAILBOAT{
            public String toString(){
                return "Sailboat";
            }
        }, MOTORSAILER{
            public String toString(){
                return "Motorsailer";
            }
        }, KAYAK{
            public String toString(){
                return "Kayak";
            }
        }, OTHER{
            public String toString(){
                return "Other";
            }
        }


    }

    /**
     * Returns an integer that is the length of the boat.
     *
     * @return length of the boat.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns a boatType that the boat has.
     *
     * @return boatType.
     */
    public boatType getType() {
        return type;
    }

    /**
     * Sets boatType to boat.
     *
     * @param type boatType.
     */
    public void setType(boatType type){
        this.type = type;
    }

    /**
     * Sets length to boat.
     *
     * @param length length of boat.
     */
    public void setLength(int length){
        this.length = length;
    }

}
