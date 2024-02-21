// $Id: Bob.java,v 1.5 1997/04/04 15:41:22 lcrnkovi Exp lcrnkovi $
// $Log: Bob.java,v $
// Revision 1.5  1997/04/04 15:41:22  lcrnkovi
// New log-window
//
// Revision 1.4  1997/04/04 13:59:48  lcrnkovi
// Image works; quit button disabled when applet
//
// Revision 1.3  1997/04/01 15:01:16  lcrnkovi
// New GUI. No picture yet
//
// Revision 1.2  1997/03/31 21:52:32  lcrnkovi
// No entry
//

/*

  Bob.java
  
    The main file.


    This handles the UI - the rest is done by the modules. I hope this code doesn't scare
    you - it is just some Java AWT programming. The actual app is more logical and easy
    to understand.

  */

import javax.swing.*;
import java.awt.*;
import java.net.URL;


/**
 * ---------------------------------------------------
 * The main BOB interface. This code is BOB specific.
 * <p>
 * Class: Bob
 * Superclass: Applet
 * <p>
 * Purpose: Provide a UI for the program.
 * ---------------------------------------------------
 * <p>
 * <p>
 * <i>Note: The gui is created in Symantec Visual Café while the rest
 * of the program is made in Microsoft Visual J++</i>
 */
public class Bob extends JFrame {
    public static Brain b;
    public static boolean isApplet = true;
    public static URL url;
    public static StringBuffer Conversation; //the conversation log
    public static Image bobImage;
    final String iName = "bob_dev.gif"; //a nice picture
    STextArea out; //Special TextArea - for human-like output
    TextArea in;
    Button send;
    Button quitter;
    Button RC;
    Picture picture; //the actual panel where the very nice picture is displayed
    String currAnswer = "";
    String ans;
    private Event lastEvent; //�vent-tracking

    /**
     * Support application format for the program
     */
    public static void main(String[] args) {
        isApplet = false;
        //JFrame f = new JFrame("Bob");
        Bob win = new Bob();
        win.init();
        //win.add("Center", win);
        win.setSize(640, 420);
        //win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

    }

    /**
     * initialize GUI elements and misc variables
     */
    public void init() {
        b = new Brain();
        Conversation = new StringBuffer();

        setLayout(null);
        setSize(640, 380);
        out = new STextArea();
        out.setEditable(false);
        out.setBounds(7, 15, 608, 120);
        add(out);
        send = new Button("Send");
        send.setBounds(7, 293, 112, 30);
        add(send);
        quitter = new Button("Quit");
        quitter.setBounds(126, 293, 118, 30);
        setBackground(Color.white);

        bobImage = Toolkit.getDefaultToolkit().getImage(iName); //load the img

        RC = new Button("View Conversation");
        RC.setBounds(7, 338, 238, 28);
        add(RC);
        in = new TextArea();
        in.setBounds(7, 150, 611, 118);
        add(in);
        picture = new Picture(bobImage);
        picture.setLayout(null);
        picture.setBounds(287, 278, 331, 90);
        add(picture);
        add(quitter);

        if (isApplet) quitter.disable();


        out.type("Hello!");
        //}}
    }


// done with the gui. now to the real stuff

    /**
     * Create a command-chain based on user interaction.
     */
    public boolean action(Event evt, Object arg) {
        if (evt.target instanceof Button || evt.target instanceof TextArea) {
            if ((this.in.getText()).equalsIgnoreCase("quit") || arg.toString().equals("Quit")) System.exit(0);
            if (arg.toString().equals("View Conversation")) printConv();
            else this.doChain(this.in.getText());
            return true;
        }
        return false;
    }

    /**
     * The main command loop
     */
    public void doChain(String txt) {
        Conversation.append("U: ").append(txt).append("\n");
        out.clear();
        ans = b.basic_answer(txt);
        if (!(ans.equals("-"))) answer(ans);
        if (b.gram_answer(txt).equals("#") && ans != null) this.out.type("Err, is this supposed to be English?");
        if (ans != null) answer(b.avoid_answer(txt));
    }

    /**
     * Format the program answer in a good way and type it out.
     */
    private void answer(String answ) {
        ans = answ;
        this.out.type(ans);
        currAnswer = ans;
        ans = ans + "\n";
        Conversation.append("B: ").append(ans);
        ans = null;
    }

    /**
     * Print the entire conversation into a separate window.
     */
    public void printConv() {
        LogFrame f = new LogFrame();
        String cnv = Conversation.toString();
        cnv = "Conversation with Bob.\nCopyleft 1997 by Luka Crnkovic\n\n" + cnv;

        TextArea conv = new TextArea(cnv);
        f.add("Center", conv);
        f.setSize(480, 320);
        f.setVisible(true);
    }
}