import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Car implements Item
{

	public Car()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImageIcon getImageIcon() throws IOException
	{
		int size = MainFrame.DEFAULT_ITEM_SIZE;
		Image i = ImageIO.read(new File("./images/car.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);
		return new ImageIcon(i);
	}

}
