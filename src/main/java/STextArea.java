// $Id: STextArea.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: STextArea.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * STextArea - Special Text Area - emulates human typing.
 *
 */

import java.awt.*;

/**
 * ---------------------------------------------------
 * CLASS: STextArea
 * SUPERCLASS: TextArea, Thread
 * PURPOSE: Simulate human-alike typing. Does not work
 * under Linux(!?!)
 * ---------------------------------------------------
 */
public class STextArea extends TextArea implements Runnable {

    int cnr;
    int tot;
    char cha[];
    String out;
    Thread runner;

    /**
     * ---------------------------------------------------
     * METHOD: start
     * PURPOSE: start the thread
     * ---------------------------------------------------
     */
    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    /**
     * ---------------------------------------------------
     * METHOD: stop
     * PURPOSE: stop the thread
     * ---------------------------------------------------
     */
    public void stop() {
        if (runner != null) {
            runner.stop();
            runner = null;
        }
    }

    /**
     * ---------------------------------------------------
     * METHOD: type
     * PURPOSE: start the typing, convert into a char array
     * ---------------------------------------------------
     */
    public void type(String msg) {
        out = "";
        cha = msg.toCharArray();
        tot = cha.length;
        run();

    }

    /**
     * ---------------------------------------------------
     * METHOD: run
     * PURPOSE:main thread, type it out, char by char
     * ---------------------------------------------------
     */
    public void run() {
        for (int cnr = 0; cnr < tot; cnr++) {
            out += cha[cnr];
            this.setText(out);
            try {
                this.runner.sleep(60 + (int) Math.floor(Math.random() * 30));
            } catch (InterruptedException e) {
            }
        }

        this.stop();
    }

    /**
     * ---------------------------------------------------
     * METHOD: clear
     * PURPOSE: clear the textarea
     * ---------------------------------------------------
     */
    public void clear() {
        this.setText("");

    }
}

