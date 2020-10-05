import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Box extends JButton implements Item
{
	private static final long serialVersionUID = 1L;
	private Item item;

	public Box()
	{
		super();
		try
		{
			setIcon(getImageIcon());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		item = new Goat();
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
		try
		{
			setIcon(item.getImageIcon());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try
		{
			setIcon(getImageIcon());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public ImageIcon getImageIcon() throws IOException
	{
		int size = MainFrame.DEFAULT_ITEM_SIZE;
		Image i = ImageIO.read(new File("./images/box.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);
		return new ImageIcon(i);
	}

}
