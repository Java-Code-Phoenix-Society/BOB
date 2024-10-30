package bob;
// $Id: bob.STextArea.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: bob.STextArea.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * bob.STextArea - Special Text Area - emulates human typing.
 *
 */

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * SUPERCLASS: TextArea, Thread
 * Simulate human-alike typing. Does not work under Linux(!?!)
 */
public class STextArea extends TextArea implements Runnable {
    int tot;
    char[] cha;
    StringBuilder out;
    transient Thread runner;

    /**
     * start the thread
     */
    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    /**
     * stop the thread
     */
    public void stop() {
        if (runner != null) {
            runner.stop();
            runner = null;
        }
    }

    /**
     * start the typing, convert into a char array
     */
    public void type(@NotNull String msg) {
        out = new StringBuilder();
        cha = msg.toCharArray();
        tot = cha.length;
        run();

    }

    /**
     * main thread, type it out, char by char
     */
    public void run() {
        for (int cnr = 0; cnr < tot; cnr++) {
            out.append(cha[cnr]);
            this.setText(out.toString());
            try {
                this.runner.sleep((long) 60 + (int) Math.floor(Math.random() * 30));
            } catch (InterruptedException ignored) {
                // Empty
            }
        }

        this.stop();
    }

    /**
     * clear the textarea
     */
    public void clear() {
        this.setText("");

    }
}

