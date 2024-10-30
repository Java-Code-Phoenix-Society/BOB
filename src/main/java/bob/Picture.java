package bob;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

// $Id: bob.Picture.java,v 1.1 1997/04/03 20:45:37 lcrnkovi Exp lcrnkovi $
//
// $Log: bob.Picture.java,v $
// Revision 1.1  1997/04/03 20:45:37  lcrnkovi
// Initial Version. Draws a nice picture in the applet...
//
//
//

/**
 ---------------------------------------------------
 Class: bob.Picture
 Superclass: Panel
 PURPOSE: Put a nice picture on the page...
 ---------------------------------------------------

 */
public class Picture extends Panel {
    transient Image img;

    /**
     ---------------------------------------------------
     CONSTRUCTOR: Canvas
     PURPOSE: transfer the image from Bob.
     ---------------------------------------------------
     */
    Picture(Image i) {
        img = i;
    }

    /**
     ---------------------------------------------------
     METHOD: Paint
     PURPOSE: draw the image
     ---------------------------------------------------
     */
    @Override
    public void paint(@NotNull Graphics g) {
        g.drawImage(img, 0, 0, this);
    }


}

