import java.io.*;
import java.util.*;

public class SuperheroController {
    private List<SuperheroModel> suits;
    private int[] repairCount = new int[3]; // เพื่อเก็บจำนวนชุดที่ถูกซ่อมแซมตามประเภท

    // Constructor
    public SuperheroController() {
        suits = new ArrayList<>();
        loadSuitsFromCSV("CodeSuperhero.csv");
    }

    // Method to load suits from CSV
    private void loadSuitsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String id = data[0];
                    String type = data[1];
                    int durability = Integer.parseInt(data[2]);
                    suits.add(new SuperheroModel(id, type, durability));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get suit by ID with additional check for IDs that start with 0
    public SuperheroModel getSuitById(String id) {
        // Check if the ID starts with 0
        if (id.length() != 6 || id.charAt(0) == '0') {
            return null; // Return null if ID is invalid
        }
        
        for (SuperheroModel suit : suits) {
            if (suit.getId().equals(id)) {
                return suit;
            }
        }
        return null; // Return null if no suit is found with the given ID
    }

    // Repair the suit and update count
    public void repairSuit(SuperheroModel suit) {
        if (suit != null) {
            if (!suit.isDurabilityValid()) { // ตรวจสอบความทนทานของชุด
                suit.repair(); // ซ่อมแซมชุด
                // เพิ่มจำนวนชุดที่ถูกซ่อมแซมตามประเภท
                if (suit.getType().equals("Power Suit")) {
                    repairCount[0]++;
                } else if (suit.getType().equals("Stealth Suit")) {
                    repairCount[1]++;
                } else if (suit.getType().equals("Identity Concealing Suit")) {
                    repairCount[2]++;
                }
            }
        }
    }

    // Get repair count for each type
    public int[] getRepairCount() {
        return repairCount;
    }

    // Method to display all suits and their durability (for debugging)
    public void displayAllSuits() {
        for (SuperheroModel suit : suits) {
            System.out.println("ID: " + suit.getId() + ", Type: " + suit.getType() + ", Durability: " + suit.getDurability());
        }
    }
}
