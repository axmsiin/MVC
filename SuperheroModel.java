public class SuperheroModel {
    private String id;
    private String type;
    private int durability;

    // Constructor
    public SuperheroModel(String id, String type, int durability) {
        this.id = id;
        this.type = type;
        this.durability = durability;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getDurability() {
        return durability;
    }

    // Check if the durability is valid based on suit type
    public boolean isDurabilityValid() {
        if (type.equals("Power Suit") && durability < 70) {
            return false;
        } else if (type.equals("Stealth Suit") && durability < 50) {
            return false;
        } else if (type.equals("Identity Concealing Suit") && (durability % 10 == 3 || durability % 10 == 7)) {
            return false;
        }
        return true;
    }

    // Check if durability is less than the minimum requirement and provide a message
    public String checkDurability() {
        if (type.equals("Power Suit") && durability < 70) {
            return "Power Suit durability is less than the required 70.";
        } else if (type.equals("Stealth Suit") && durability < 50) {
            return "Stealth Suit durability is less than the required 50.";
        } else if (type.equals("Identity Concealing Suit") && (durability % 10 == 3 || durability % 10 == 7)) {
            return "Identity Concealing Suit durability cannot end in 3 or 7.";
        }
        return null;
    }

    // Repair the suit by increasing its durability by 25 (max 100)
    public void repair() {
        if (durability < 100) {
            durability = Math.min(durability + 25, 100);
        }
    }
}
