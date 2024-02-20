import java.awt.*;

// $Id: Picture.java,v 1.1 1997/04/03 20:45:37 lcrnkovi Exp lcrnkovi $
//
// $Log: Picture.java,v $
// Revision 1.1  1997/04/03 20:45:37  lcrnkovi
// Initial Version. Draws a nice picture in the applet...
//
//
//

   /**
---------------------------------------------------
  Class: Picture
  Superclass: Panel
  PURPOSE: Put a nice picture on the page...
---------------------------------------------------

*/
public class Picture extends Panel
{
	Image img;

/**
---------------------------------------------------
  CONSTRUCTOR: Canvas
  PURPOSE: transfer the image from Bob.
---------------------------------------------------
*/
    Picture(Image i)
	{
		img = i;
	}
	 
/**
---------------------------------------------------
  METHOD: Paint
  PURPOSE: draw the image
---------------------------------------------------
*/
	public void paint(Graphics g)
	{
		g.drawImage(img,0,0,this);
	}


}

