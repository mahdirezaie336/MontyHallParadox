
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public static HashMap<String, Component> impComponents;
	
	public MainFrame()
	{
		super("The Monty Hall Game");
		impComponents = new HashMap<>();
		setSize(400, 250);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel message = new JLabel("Choose one of boxes:");						// Creating message label
		message.setBackground(Color.CYAN);
		message.setOpaque(true);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		Dimension mSize = message.getPreferredSize();
		message.setPreferredSize(new Dimension(mSize.width + 10, mSize.height + 30));
		add(message, BorderLayout.NORTH);
		impComponents.put("Message JLabel", message);
		
		JPanel choices = new JPanel(new GridLayout(1,3,5,5));						// Creating choices buttons
		//choices.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JButton choice1 = new JButton("1");
		JButton choice2 = new JButton("2");
		JButton choice3 = new JButton("3");
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		add(choices, BorderLayout.CENTER);
		impComponents.put("Choice 1 JButton", choice1);
		impComponents.put("Choice 2 JButton", choice2);
		impComponents.put("Choice 3 JButton", choice3);
		
		JPanel stats = new JPanel(new FlowLayout(FlowLayout.LEADING));				// Creating stats details
		JLabel total = new JLabel("Total tests: 0");
		JLabel corrs = new JLabel("Correct choices: 0");
		stats.add(total);
		stats.add(new JLabel(" / "));
		stats.add(corrs);
		add(stats, BorderLayout.SOUTH);
		impComponents.put("Total Tests JLabel", total);
		impComponents.put("Correct Choices JLabel", corrs);
		
	}

	public static void main(String[] args)
	{
		new MainFrame().setVisible(true);
	}

}
