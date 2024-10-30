package bob;
// $Id: bob.Spam.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: bob.Spam.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * bob.Spam
 *
 *Stream Processing and Analysing Module
 */

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * ---------------------------------------------------
 * CLASS: bob.Spam
 * SUPERCLASS: none
 * PURPOSE: Handles the I/O actually only input.
 * ---------------------------------------------------
 */
public class Spam {
    public static String fName;
    public static String tName;
    public static boolean isApplet = false;
    public static final String N_SUBJ = "#";
    public static final String N_TXT = "-";
    public static String comment = "//";


    /**
     * ---------------------------------------------------
     * CONSTRUCTOR: bob.Spam
     * PURPOSE: transfer the isApplet attribute from Bob.
     * ---------------------------------------------------
     */
    Spam() {
        isApplet = Bob.isApplet; //hmm, there goes the modularity
    }

    /**
     * ---------------------------------------------------
     * METHOD: load_structure
     * PURPOSE: load the database into a vector
     * ---------------------------------------------------
     */
    public static final @NotNull Vector<Keyword> loadStructure(String fn) {

        Vector<Keyword> data = new Vector<>();
        DataInputStream dis = Spam.setStream(fn);
        String line;
        StringBuilder txt = new StringBuilder();
        int nr = -1;

        boolean isKword = true;

        try {

            while ((line = dis.readLine()) != null) {
                if (line.equals(N_SUBJ))//new keyword
                {
                    line = dis.readLine();
                    isKword = true;
                    data.addElement(new Keyword());

                    if (!txt.toString().equals(""))
                        (data.elementAt(nr)).text.addElement(txt.toString());

                    nr++;
                    txt = new StringBuilder();
                }
                if (line.equals(N_TXT)) //new text
                {
                    line = dis.readLine();
                    if (!txt.toString().equals(""))
                        (data.elementAt(nr)).text.addElement(txt.toString());
                    txt = new StringBuilder();
                    isKword = false;

                }
                if (line.indexOf("//") != -1)
                    line = dis.readLine();


                if (isKword) //add new keyword
                    data.elementAt(nr).keywords.addElement(line);


                if (!isKword)         //if allright add text
                    txt.append(line).append("\n");
            }


        } catch (IOException e) {
            // empty
        }

        return data;
    }

    /**
     * ---------------------------------------------------
     * METHOD: setStream
     * PURPOSE: Set the input-stream depending on if it runs
     * as an applet or program.
     * ---------------------------------------------------
     */
    static DataInputStream setStream(String fn) {
        DataInputStream dis = null;

        if (!isApplet) {
            try {

                FileInputStream fis = new FileInputStream(fn);
                dis = new DataInputStream(fis);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else if (isApplet) {
            URL theUrl;
            String url = fn;
            try {
                if (Bob.url != null)
                    theUrl = new URL(Bob.url, url);
                else
                    theUrl = new URL(url);
                InputStream conn = theUrl.openStream();
                dis = new DataInputStream(new BufferedInputStream(conn));

            } catch (MalformedURLException e) {
                System.out.println("Error loding URL");
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }

        return dis;
    }

    /**
     * ---------------------------------------------------
     * METHOD: main
     * PURPOSE: stand-alone support for debbuging.
     * ---------------------------------------------------
     */
    public static void main(@NotNull String @NotNull [] args) {


        fName = "bob.dat";
        tName = "bob.tpl";
        Vector<Keyword> bob = loadStructure(fName);

        if (args.length == 0) {


            for (int i = 0; i < bob.size(); i++) {
                Keyword tmp = (Keyword) bob.elementAt(i);


                for (int j = 0; j < tmp.keywords.size(); j++) {
                    System.out.println((String) tmp.keywords.elementAt(j));
                }


            }


        } else {
            if (args[0].equals("1") && args.length == 2) {
                Keyword p = (Keyword) bob.elementAt(Integer.parseInt(args[1]));
                for (int i = 0; i < p.text.size(); i++) {
                    System.out.println("Text " + i + " : " + (String) p.text.elementAt(i));
                }
            }

            String[] seg = Lap.segment(args[0].toLowerCase());
            Lap.getWords(seg);
            StringBuilder all = new StringBuilder();

            for (String s : seg) all.append(s).append(" \n");

            Keyword[] k = new Keyword[bob.size()];
            for (int i = 0; i < bob.size(); i++) {
                k[i] = (Keyword) bob.elementAt(i);

                if (!(k[i].getText(all.toString()).equals("-"))) {
                    System.out.println(k[i].getText(all.toString()));
                    break;
                }

            }

        }

    }
}