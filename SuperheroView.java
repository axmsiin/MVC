import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuperheroView extends JFrame {
    private JTextField idField;
    private JTextArea resultArea;
    private JButton checkButton;
    private JButton repairButton;
    private SuperheroController controller;

    // Constructor
    public SuperheroView(SuperheroController controller) {
        this.controller = controller;

        // Set up the frame
        setTitle("Superhero Suit Checker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set up components
        JLabel idLabel = new JLabel("Enter Suit ID:");
        idLabel.setBounds(20, 20, 100, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(120, 20, 150, 25);
        add(idField);

        checkButton = new JButton("Check Suit");
        checkButton.setBounds(120, 60, 150, 30);
        add(checkButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 100, 350, 100);
        resultArea.setEditable(false);
        add(resultArea);

        repairButton = new JButton("Repair Suit");
        repairButton.setBounds(120, 210, 150, 30);
        repairButton.setEnabled(false); // Initially disabled until checked
        add(repairButton);

        // Action Listeners
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();

                // Check if the ID starts with 0 or is not of the correct length (6 digits)
                if (id.length() != 6 || id.charAt(0) == '0') {
                    resultArea.setText("Error: Suit ID cannot start with 0. Please enter a valid 6-digit ID.");
                    idField.setText(""); // Clear the input field to reset
                    repairButton.setEnabled(false); // Disable repair button if ID is invalid
                } else {
                    SuperheroModel suit = controller.getSuitById(id);

                    if (suit == null) {
                        resultArea.setText("No suit found with this ID.");
                        repairButton.setEnabled(false); // Disable repair button if suit is not found
                    } else {
                        String durabilityMessage = suit.checkDurability(); // Check the durability
                        if (durabilityMessage == null) {
                            resultArea.setText("Suit is valid with durability: " + suit.getDurability());
                            repairButton.setEnabled(false); // Disable repair button if suit is valid
                        } else {
                            resultArea.setText(durabilityMessage + "\nRepair to: " + (suit.getDurability() + 25));
                            repairButton.setEnabled(true); // Enable repair button if suit is invalid
                        }
                    }
                }
            }
        });

        repairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                SuperheroModel suit = controller.getSuitById(id);

                if (suit != null) {
                    // Perform repair action
                    controller.repairSuit(suit);
                    resultArea.setText("Suit repaired! New durability: " + suit.getDurability());
                    repairButton.setEnabled(false); // Disable repair button after repair
                }
            }
        });
    }

    public static void main(String[] args) {
        SuperheroController controller = new SuperheroController();
        SuperheroView view = new SuperheroView(controller);
        view.setVisible(true);
    }
}
