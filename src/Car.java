import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Car implements Item
{
	private ImageIcon imageIcon;
	
	public Car()
	{
		int size = MainFrame.DEFAULT_ITEM_SIZE;
		Image i = null;
		try
		{
			i = ImageIO.read(new File("./images/car.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		imageIcon = new ImageIcon(i);
	}

	@Override
	public ImageIcon getImageIcon()
	{
		return imageIcon;
	}

}
