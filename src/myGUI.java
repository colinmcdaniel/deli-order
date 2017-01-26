import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class myGUI extends JFrame {
	
	// Private variables that user can interact with
	private JTextField name;
	private JCheckBox foodItem1;
	private JCheckBox foodItem2;
	private JCheckBox foodItem3;
	private JComboBox orderType;
	private JSlider minutes;
	private JButton ok;
	private JButton quit1;
	private JButton quit2;
	
	// Constants
	final private int itemPrice1 = 10;
	final private int itemPrice2 = 8;
	final private int itemPrice3 = 5;
	final private double taxRate = 0.08;
	
	/* Sets up contents of frame */
	public myGUI(){
		
		// Sets title and layout
		super("Deli Customer");
		setLayout(new FlowLayout());
		
		// Add welcome label
		JLabel welcome=new JLabel("Welcome to the deli! Please select your order.");
		add(welcome);
		
		// Adds panel1 with text field for name
		JLabel promptName=new JLabel("Type your name here");
		name=new JTextField(15);
		JPanel panel1=new JPanel();
		panel1.add(promptName);
		panel1.add(name);
		panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
		add(panel1);
		
		// Adds panel2 with food item selection
		JLabel promptItem=new JLabel("What food items do you want?");
		foodItem1=new JCheckBox("Turkey sandwich ($" + itemPrice1 + ")");
		foodItem2=new JCheckBox("Mini pizza ($" + itemPrice2 + ")");
		foodItem3=new JCheckBox("Salad ($" + itemPrice3 + ")");
		JPanel panel2=new JPanel();
		panel2.add(promptItem);
		panel2.add(foodItem1);
		panel2.add(foodItem2);
		panel2.add(foodItem3);
		panel2.setBorder(BorderFactory.createLineBorder(Color.red));
		add(panel2);
		
		// Adds panel3 with order type
		JLabel promptOrderType=new JLabel("What type of order would you like?");
		String[] orderTypeList={"For here","To-go","Drive-thru"};
		orderType=new JComboBox(orderTypeList);
		JPanel panel3=new JPanel();
		panel3.add(promptOrderType);
		panel3.add(orderType);
		panel3.setBorder(BorderFactory.createLineBorder(Color.orange));
		add(panel3);
		
		// Adds panel4 with order time
		JLabel promptMinutes=new JLabel("How many minutes would you like your order in?");
		minutes=new JSlider(JSlider.HORIZONTAL,15,60,15);
		minutes.setMajorTickSpacing(15);
		minutes.setMinorTickSpacing(5);
		minutes.setPaintTicks(true);
		minutes.setPaintLabels(true);
		JPanel panel4=new JPanel();
		panel4.add(promptMinutes);
		panel4.add(minutes);
		panel4.setBorder(BorderFactory.createLineBorder(Color.green));
		add(panel4);
		
		// Adds panel5 with OK and QUIT buttons
		ok=new JButton("OK");
		quit1=new JButton("Quit");
		JPanel panel5=new JPanel();
		panel5.add(ok);
		panel5.add(quit1);
		add(panel5);
		
		// Add ActionListeners to JButtons
		ok.addActionListener(new Listen());
		quit1.addActionListener(new Listen());
	}
	
	/* ActionListener for JButtons */
	private class Listen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==ok){
				
				// Count number of food items selected
				int count=0;
				if(foodItem1.isSelected())
					count++;
				if(foodItem2.isSelected())
					count++;
				if(foodItem3.isSelected())
					count++;
				
				// Set frame to invisible
				setVisible(false);
				
				// Call new window
				JFrame display=new JFrame();
				
				// Window settings
				display.setTitle("Receipt Confirmation");
				display.setLayout(new FlowLayout());
				
				// Set size based on number of food items selected
				if(count<=1)
					display.setSize(540,475);
				else if(count==2)
					display.setSize(800,450);
				else if(count==3)
					display.setSize(1070,425);
				
				// Window settings
				display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				display.setLocationRelativeTo(null);
				display.getContentPane().setBackground(Color.white);
				display.setVisible(true);
				
				// Add header to display
				JLabel announceOrder=new JLabel("Thank you. Here is your receipt:");
				display.add(announceOrder);
				
				// Display name in p0
				JPanel p0=new JPanel();
				p0.add(new JLabel("Name: " + name.getText()));
				p0.setBackground(Color.cyan);
				display.add(p0);
				
				// Display food items selected in p1
				JPanel p1=new JPanel();
				p1.add(new JLabel("Food item(s): "));
				if(foodItem1.isSelected())
					p1.add(new JLabel("1 Turkey sandwich."));
				if(foodItem2.isSelected())
					p1.add(new JLabel("1 Mini pizza."));
				if(foodItem3.isSelected())
					p1.add(new JLabel("1 Salad."));
				p1.setBackground(Color.yellow);
				display.add(p1);
				
				// Add food images to p2
				JPanel p2=new JPanel();
				if(foodItem1.isSelected()){
					ImageIcon turkey = new ImageIcon("../images/img1.jpg");
					JLabel picLabel1=new JLabel(turkey);
					p2.add(picLabel1);
				}
				if(foodItem2.isSelected()){
					ImageIcon pizza = new ImageIcon("../images/img2.jpg");
					JLabel picLabel2=new JLabel(pizza);
					p2.add(picLabel2);
				}
				if(foodItem3.isSelected()){
					ImageIcon salad = new ImageIcon("../images/img3.jpg");
					JLabel picLabel3=new JLabel(salad);
					p2.add(picLabel3);
				}
				
				// Add order type images to p2
				String comboSelection=(String)orderType.getSelectedItem();
				if(comboSelection.equals("For here")){
					ImageIcon forHere = new ImageIcon("img4.jpg");
					JLabel picLabel4=new JLabel(forHere);
					p2.add(picLabel4);
				}
				else if(comboSelection.equals("To-go")){
					ImageIcon toGo=new ImageIcon("img5.jpg");
					JLabel picLabel5=new JLabel(toGo);
					p2.add(picLabel5);
				}
				else if(comboSelection.equals("Drive-thru")){
					ImageIcon driveThru=new ImageIcon("img6.jpg");
					JLabel picLabel6=new JLabel(driveThru);
					p2.add(picLabel6);
				}
				display.add(p2);
				
				// Display order type selected in p3
				JPanel p3=new JPanel();
				p3.add(new JLabel("Order type: "));
				p3.add(new JLabel(comboSelection));
				p3.setBackground(Color.cyan);
				display.add(p3);
				
				// Calculate subtotal
				int subTotal=0;
				if(foodItem1.isSelected())
					subTotal+=itemPrice1;
				if(foodItem2.isSelected())
					subTotal+=itemPrice2;
				if(foodItem3.isSelected())
					subTotal+=itemPrice3;
				
				// Calculate total
				double tax = subTotal*taxRate;
				double total = subTotal + tax;
				
				// Add subtotal and total in p4
				JPanel p4=new JPanel();
				p4.add(new JLabel("Subtotal: $" + subTotal + ","));
				p4.add(new JLabel("Tax: $" + tax + ","));
				p4.add(new JLabel("Total: $" + total));
				p4.setBackground(Color.yellow);
				display.add(p4);
				
				// Display order time selected in p5
				JPanel p5=new JPanel();
				int timeSelected=(int)minutes.getValue();
				p5.add(new JLabel("Your order will be ready in " + timeSelected + " minutes."));
				p5.setBackground(Color.cyan);
				display.add(p5);
				
				// Add another QUIT button
				quit2=new JButton("Quit");
				quit2.addActionListener(new ListenQuit());
				display.add(quit2);
			}
			else if(e.getSource()==quit1)		// exit program
				System.exit(0);
		}
	}
	
	/* ActionListener for quit2 button */
	private class ListenQuit implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	/* Main method */
	public static void main(String[] args){
		
		// Call frame
		myGUI frame=new myGUI();
		
		// Frame settings
		frame.setSize(625,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
