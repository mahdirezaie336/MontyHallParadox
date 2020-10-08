
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public static HashMap<String, Component> impComponents;
	public static final int DEFAULT_ITEM_SIZE = 150;
	public static final long MESSAGE_SLEEP_TIME = 0;
	private ArrayList<State> states;
	private int state;
	private int totalTests;
	private int succeedTests;
	private SecureRandom rand;
	
	public MainFrame()
	{
		super("The Monty Hall Game");
		setLookAndFeel();
		impComponents = new HashMap<>();
		setSize(460, 250);
		setResizable(false);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		totalTests = 0;
		succeedTests = 0;
		state = 0;
		states = new ArrayList<>();
		rand = new SecureRandom();
		
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
		BoxHandler handler = new BoxHandler();
		choice1.addActionListener(handler);
		choice2.addActionListener(handler);
		choice3.addActionListener(handler);
		impComponents.put("Choice 1 Box", choice1);
		impComponents.put("Choice 2 Box", choice2);
		impComponents.put("Choice 3 Box", choice3);
		
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
		
		try
		{
			states.get(0).act(null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}
	
	private void addStates()
	{
		State firstChoose = new State()
		{
			
			@Override
			public void act(Object source)
			{
				showMessage("Choose one of boxes:");

				for(int i = 1; i < 4; ++i)
				{
					Box box = (Box) impComponents.get("Choice " + i + " Box");
					box.fillWith(Box.GOAT_ITEM);
					box.close();
				}
				String box = "Choice " + (rand.nextInt(3) + 1) + " Box";
				//System.out.println(box);
				((Box)impComponents.get(box)).fillWith(Box.CAR_ITEM);
				
				state++;
			}
		};
		
		State secondChoose = new State()
		{
			
			@Override
			public void act(Object source) throws Exception
			{
				if(((Box)source).isOpened())
					return;
				
				showMessage("We open a box for you. Do you change? Choose one box.");
				
				int index = 0;
				do
				{
					index = (rand.nextInt(3) + 1);
				}
				while(index == getBoxIndex(source) || getBox(index).containsCar());
				
				getBox(index).open();
				
				state++;
			}
		};
	
		State message2 = new State()
		{
			
			@Override
			public void act(Object source)
			{
				Box box = (Box) source;
				
				if(box.isOpened())
					return;
				box.open();
				
				if(box.containsCar())
				{
					showMessage("You won a car! Click any box.", Color.green);
					succeedTests++;
				}
				else
					showMessage("You won a goat! Click any box.", Color.orange);
				
				totalTests++;
				updateGameStats();
				
				state = 0;
			}
		};
	
		states.add(firstChoose);
		states.add(secondChoose);
		states.add(message2);
	}
	
	private void updateGameStats()
	{
		((JLabel)impComponents.get("Total Tests JLabel")).setText("" + totalTests);
		((JLabel)impComponents.get("Correct Choices JLabel")).setText("" + succeedTests);
	}
	
	private void showMessage(String s)
	{
		showMessage(s, Color.cyan);
	}
	
	private void showMessage(String s, Color color)
	{
		JLabel m = (JLabel)impComponents.get("Message JLabel");
		m.setText(s);
		m.setBackground(color);
		m.updateUI();
	}
	
	private Box getBox(int i)
	{
		return (Box) impComponents.get("Choice " + i + " Box");
	}
	
	private int getBoxIndex(Object box) throws Exception
	{
		for(int i = 1; i < 4; ++i)
			if(impComponents.get("Choice " + i + " Box") == box)
				return i;
		throw new Exception("Box not found!");
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
				try
				{
					states.get(state).act(arg0.getSource());
				} catch (Exception e)
				{
					throw new RuntimeException(e.getMessage());
				}
		}
		
	}

}
