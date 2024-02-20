// $Id$
// $Log$
//


import java.awt.*;


/**
---------------------------------------------------
  CLASS: LogFrame
  SUPERCLASS: Frame
  PURPOSE: Show the conversation in a separate
  window.
---------------------------------------------------

*/
class LogFrame extends Frame
{

	public boolean handleEvent(Event evt)
	{
          if (evt.id == Event.WINDOW_DESTROY)
		   this.hide();
		return true;
	}

}

