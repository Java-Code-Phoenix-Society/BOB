package bob;
// $Id$
// $Log$
//


import org.jetbrains.annotations.NotNull;

import java.awt.*;


/**
 ---------------------------------------------------
 CLASS: bob.LogFrame
 SUPERCLASS: Frame
 PURPOSE: Show the conversation in a separate
 window.
 ---------------------------------------------------

 */
class LogFrame extends Frame {

    @Override
    public boolean handleEvent(@NotNull Event evt) {
        if (evt.id == Event.WINDOW_DESTROY)
            this.hide();
        return true;
    }

}

