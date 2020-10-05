import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Box extends JButton implements Item
{
	private static final long serialVersionUID = 1L;
	public static final Item GOAT_ITEM = new Goat();
	public static final Item CAR_ITEM = new Car();
	private Item item;
	private boolean open;
	private ImageIcon imageIcon;

	public Box()
	{
		super();
		
		int size = MainFrame.DEFAULT_ITEM_SIZE;
		Image i = null;
		try
		{
			i = ImageIO.read(new File("./images/box.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		imageIcon = new ImageIcon(i);
		setIcon(getImageIcon());
		item = new Goat();
		open = false;
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public void fillWith(Item item)
	{
		this.item = item;
	}
	
	public void open()
	{
		setIcon(item.getImageIcon());
		open = true;
	}
	
	public void close()
	{
		setIcon(getImageIcon());
		open = false;
	}
	
	public boolean isOpened()
	{
		return open;
	}
	
	public boolean containsCar()
	{
		return item instanceof Car;
	}
	
	public ImageIcon getImageIcon()
	{
		return imageIcon;
	}

}
