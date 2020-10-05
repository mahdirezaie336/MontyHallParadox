
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public static HashMap<String, Component> impComponents;
	public static final int DEFAULT_ITEM_SIZE = 150;
	public static final short FIRST_CHOOSE_STATE = 0;
	public static final short SECOND_CHOOSE_STATE = 1;
	private ArrayList<State> states;
	private short state;
	private int totalTests;
	private int succeedTests;
	
	public MainFrame()
	{
		super("The Monty Hall Game");
		impComponents = new HashMap<>();
		setSize(460, 250);
		setResizable(false);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		state = 0;
		states = new ArrayList<>();
		
		JLabel message = new JLabel("Choose one of boxes:");						// Creating message label
		message.setBackground(Color.CYAN);
		message.setOpaque(true);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		Dimension mSize = message.getPreferredSize();
		message.setPreferredSize(new Dimension(mSize.width + 10, mSize.height + 30));
		add(message, BorderLayout.NORTH);
		impComponents.put("Message JLabel", message);
		
		JPanel choices = new JPanel(new GridLayout(1,3,5,5));						// Creating choices buttons
		Box choice1 = new Box();
		Box choice2 = new Box();
		Box choice3 = new Box();
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		add(choices, BorderLayout.CENTER);
		impComponents.put("Choice 1 JButton", choice1);
		impComponents.put("Choice 2 JButton", choice2);
		impComponents.put("Choice 3 JButton", choice3);
		
		JPanel stats = new JPanel(new FlowLayout(FlowLayout.LEADING));				// Creating stats details
		JLabel total = new JLabel("0");
		JLabel corrs = new JLabel("0");
		stats.add(new JLabel("Total Tests:"));
		stats.add(total);
		stats.add(new JLabel(" / Correct Choices: "));
		stats.add(corrs);
		add(stats, BorderLayout.SOUTH);
		impComponents.put("Total Tests JLabel", total);
		impComponents.put("Correct Choices JLabel", corrs);
		addStates();
	}
	
	private void addStates()
	{
		State firstChoose = new State()
		{
			
			@Override
			public void act()
			{
				
			}
		};
	}

	public static void main(String[] args)
	{
		new MainFrame().setVisible(true);
	}
	
	private class BoxHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
		}
		
	}

}
