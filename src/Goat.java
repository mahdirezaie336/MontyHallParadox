import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Goat implements Item
{
	private ImageIcon imageIcon;
	
	public Goat()
	{
		int size = MainFrame.DEFAULT_ITEM_SIZE;
		Image i = null;
		try
		{
			i = ImageIO.read(new File("./images/goat.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);
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
