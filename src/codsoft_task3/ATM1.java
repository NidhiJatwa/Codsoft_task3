
package codsoft_task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM1 {
     BankAcc account;
     JFrame frame;
     JTextField amountField;
    JTextArea outputArea;
     JButton depositButton;
     JButton withdrawButton;
     JButton checkBalanceButton;

    public ATM1(BankAcc account) {
        this.account = account;
        createAndShowGUI();
    }

     void createAndShowGUI() {
        frame = new JFrame("ATM");
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel heading = new JLabel("ATM");
    heading.setBounds(400, 5, 1500, 90); // Positioning the heading at the top
    heading.setFont(new Font("Verdana", Font.BOLD, 120)); // Large font size
    heading.setForeground(new Color(0, 0, 204));
    frame.add(heading);
        
    JPanel panel = new JPanel();
    panel.setLayout(null); // Allow manual positioning
    panel.setBounds(0, 100, 1500, 800); // Adjust position and size
    frame.add(panel);
    
       // JPanel panel = new JPanel();
       // panel.setLayout(new GridLayout(4, 2));

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 120, 1500, 80);
        amountLabel.setFont(new Font("Verdana", Font.BOLD, 40));
       // panel.add(amountLabel);
        
        amountField = new JTextField();
         amountField.setBounds(300, 145, 350, 40);
        amountField.setFont(new Font("Verdana", Font.PLAIN, 36));
        amountField.setForeground(Color.RED);

        depositButton = new JButton("Deposit");
         depositButton.setBounds(250, 250, 240, 50);
         depositButton.setBackground(Color.BLUE);
         depositButton.setForeground(Color.WHITE);
        depositButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        frame.add( depositButton);
        
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(250, 480, 240, 50);
         withdrawButton.setBackground(Color.BLUE);
         withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        frame.add( withdrawButton);
        
        
        checkBalanceButton = new JButton("Check Balance");
       checkBalanceButton.setBounds(250, 370, 240, 50);
          checkBalanceButton.setBackground(Color.BLUE);
          checkBalanceButton.setForeground(Color.WHITE);
         checkBalanceButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        frame.add( checkBalanceButton);

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton);

        //outputArea = new JTextArea();
       
       // frame.add(BorderLayout.NORTH);
       //// outputArea.setBounds(450, 180, 240, 50);
        //frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == depositButton) {
                    handleDeposit();
                } else if (e.getSource() == withdrawButton) {
                    handleWithdraw();
                } else if (e.getSource() == checkBalanceButton) {
                    handleCheckBalance();
                }
            }
        };
        
        outputArea = new JTextArea();
    outputArea.setBounds(400, 180, 600, 850);// Adjust size and position
    outputArea.setFont(new Font("Verdana", Font.BOLD, 20));
    outputArea.setForeground(Color.GREEN);
    outputArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(outputArea);
    scrollPane.setBounds(750, 40, 600, 800); 
   
    frame.add(scrollPane);

        depositButton.addActionListener(buttonListener);
        withdrawButton.addActionListener(buttonListener);
        checkBalanceButton.addActionListener(buttonListener);

        frame.setVisible(true);
    }

    private void handleDeposit() {
        double amount = getAmountFromField();
        if (amount > 0 && account.deposit(amount)) {
            outputArea.append("Deposited: INR " + amount + "\n");
        } else {
            outputArea.append("Deposit failed. Amount must be positive.\n");
        }
    }

    private void handleWithdraw() {
        double amount = getAmountFromField();
        if (amount > 0 && account.withdraw(amount)) {
            outputArea.append("Withdrew: INR " + amount + "\n");
        } else {
            outputArea.append("Withdrawal failed. Insufficient funds or invalid amount.\n");
        }
    }

    private void handleCheckBalance() {
        double balance = account.checkBalance();
        outputArea.append("Current Balance: INR " + balance + "\n");
    }

    private double getAmountFromField() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            outputArea.append("Invalid amount. Please enter a valid number.\n");
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAcc userAccount = new BankAcc(1000.00);
            new ATM1(userAccount);
        });
    }
}
