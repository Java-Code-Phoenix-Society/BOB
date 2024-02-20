// $Id: Lap.java,v 1.3 1997/04/04 14:44:42 lcrnkovi Exp lcrnkovi $
// $Log: Lap.java,v $
// Revision 1.3  1997/04/04 14:44:42  lcrnkovi
// The person conversion works now!!!!
//
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * Lap
 *
  
	Lexical Analysis Processor


 */

 /**
---------------------------------------------------
  CLASS: Lap
  SUPERCLASS: none
  PURPOSE: Process the text entered by the user.
  Analyze entered text. Create sentences in proper
  english.
---------------------------------------------------

*/
public class Lap 
{
	public static String[] sent;
	public static String template;
	public static String out;




	public static int adjective_num = -1;
	public static int adverb_num = -1;
	public static int possessive_num = -1;
	public static int question_num = -1;
	public static int pronoun_num = -1;
	public static int verb_num = -1;

	public static String adjective;
	public static String adverb;
	public static String possessive;
	public static String question;
	public static String pronoun;
	public static String verb;

/**
---------------------------------------------------
  METHOD: init
  PURPOSE: clear all previous data
---------------------------------------------------

*/
public static void init()
{
	adjective = "";
	adverb = "";
	possessive = "";
	question="";
	pronoun="";
	verb="";
}

	
/*  
---------------------------------------------------
  METHOD: segment
  PURPOSE: segmentize the entered sentence into 
  an array of strings.
---------------------------------------------------

*/
	
	public static String[] segment(String msg) 
	{
		
		int words = 0; //wordcount
		
		
		
		for(int i = 0; i < msg.length(); i++) //get the length	
		{
			if(msg.charAt(i) == ' ')
				words++; 
		
		}

				
		String seg[] = new String[words + 1]; //set the length of the array

		int last_space = 0; //index of the next found space
		int start = 0; //the starting position
		int cnt = 0;

		
		while(last_space != -1)
		{
			
			if(last_space!=0)
				start = last_space +1;
			
			last_space = msg.indexOf(" ", start);
			
			

			if(last_space == -1)
			{
				seg[cnt] = msg.substring(start, msg.length());
				break;

			}

			if(start != last_space)
				seg[cnt]= new String(msg.substring(start, last_space));
			else
			{
				seg[cnt] = msg.substring(start, msg.length() -1 );
				//System.out.println(seg[cnt]);
				
				break;

			}
			cnt++;
		}
		seg = clean(seg);
		

		sent = seg;
		return seg;
	}

	 /*  
---------------------------------------------------
  METHOD: clean
  PURPOSE: Clear the array of all signs.
---------------------------------------------------

*/

	private static String[] clean(String[] msg)  //clean signs
	{
		for(int i = 0; i < msg.length; i++)
		{
			String str = "";
			for(int j = 0; j < msg[i].length();j++)
			{
				if(msg[i].charAt(j)!='.' && msg[i].charAt(j)!='?'&&msg[i].charAt(j)!='!'&&msg[i].charAt(j)!=',')
					str +=msg[i].charAt(j);
			}
			msg[i] = str;
		}

		return msg;
	}


 /*  
---------------------------------------------------
  METHOD: get_words
  PURPOSE: Analyze the structure of the entered
  sentence; find the positions of verbs, pronouns, adverbs, etc...
---------------------------------------------------

*/
	 
	static void get_words(String[] in)

	{
		String words;
		String keywords[] = new String[in.length];

		for(int i=0; i < in.length; i++) //convert into lowercase
			in[i] = in[i].toLowerCase();

		boolean known = false;
		
		for(int i = 0; i < in.length; i++) //pretty self-expaining loop
		{
		
			
			
			for(int j=0;j< Words.adjective.length; j++)
			{
				if(in[i].equals(Words.adjective[j]) )
				{
					adjective_num = i;
					adjective = in[i];
				}
			}
			for(int j=0;j< Words.adverb.length; j++)
			{
				if(in[i].equals(Words.adverb[j]))
				{
					adverb_num = i;
					adverb = in[i];
				}
			}
			for(int j=0;j< Words.pronoun.length; j++)
			{
				if(in[i].equals(Words.pronoun[j]) )
				{
					pronoun_num = i;
					pronoun = in[i];
				}
			}
			for(int j=0;j< Words.verb.length; j++)
			{
				if(in[i].equals(Words.verb[j]) )
				{
					verb_num = i;
					verb = in[i];
			
				}
			}
			
			for(int j=0;j< Words.possessive.length; j++)
			{
				if(in[i].equals(Words.possessive[j]) )
				{
					possessive_num = i;
					possessive = in[i];
				}
			}

			for(int j=0;j< Words.question.length; j++)
			{
				if(in[i].equals(Words.question[j]))
				{
					question_num = i;
					question = in[i];
					
				}
				//System.out.println(Words.question[j]);
			}
		}
		
	}
 /*  
---------------------------------------------------
  METHOD: check_grammar
  PURPOSE: Look if the sentence contains the necessary
  words to be grammatically correct.
---------------------------------------------------

*/

	public static boolean check_grammar()
	{
		if(adjective_num==-1 && adverb_num==-1 &&pronoun_num==-1 && verb_num==-1 &&
			possessive_num==-1&&question_num==-1)
			
			return false;
		else
			return true;
	}
 /*  
---------------------------------------------------
  METHOD: main
  PURPOSE: Stand-alone support for debugging
---------------------------------------------------

*/

	public static void main(String args[])
	{
		String all = "";
		
		for(int i=0;i<args.length;i++)
			all = args[i] + " ";

		
		
		get_words(segment(all));
		check_grammar();
		System.out.println("Placement :");
		System.out.println("(-1 indicates lack of word)");
		System.out.println("Adjective: " + adjective_num);
		System.out.println("Adverb: " + adverb_num);
		System.out.println("Pronoun: " + pronoun_num);
		System.out.println("Verb: " + verb_num);
		System.out.println("Possessive: " + possessive_num);
		System.out.println("Question: " + question_num);
		if(check_grammar())
			System.out.println("The sentance is grammaticaly correct");
		else
			System.out.println("Incorrect sentance");
	}


			


	public static String formulateReply()

	{
		sent = changePerson(sent);
		
		String reply = new String();

		    if(question.equals("what"))
				reply=handleWhatWhere();
			if(question.equals("when"))
				reply=handleWhen();
			if(question.equals("where"))
				reply=handleWhatWhere();
			if(question.equals("why"))
				reply=handleWhy();
			if(question.equals("which"))
				reply=handleWhich();
			if(question.equals("who"))
				reply=handleWho();
			if(question.equals("how"))
				reply=handleHow();
			if(sent[0].equals("i")||sent[0].equals("you"))
			  reply=handleIYou();
			else if(!sent[0].equals(question))
				    reply=handleTheRest();
	

		//reply = changePerson(reply); //this should work in the future.
		
		String answ = insertTemplate(template,reply); 
		//System.out.println(answ);

		

		return answ;

	}


	public static String[] changePerson(String txt[]) //the really tricky part
	{

		String verbs = new String();

		for(int i = 0; i < Words.verb.length; i++)
			verbs += Words.verb[i] + " "; 
	
		String[] snt = txt;

		int l = snt.length;
		boolean[] changed = new boolean[l]; //if the word was changed
		
		for(int i=0; i < l; i++) //init the bools
			changed[i] = false; 
		
		for(int i = 0; i < l; i++) //the adjective loop
		{
			if(snt[i].equals("my") && !changed[i])
			{
				snt[i] = "your";
				changed[i] = true;
			}

			if(snt[i].equals("your") && !changed[i])
			{
				snt[i] = "my";
				changed[i] = true;
			}
			
			if(snt[i].equals("our") && !changed[i])
			{
				snt[i] = "your";
				changed[i] = true;
			}

		}

		for(int i = 0; i < l; i++) //possesive
		{
			
			if(snt[i].equals("mine") && !changed[i])
			{
				snt[i] = "yours";
				changed[i] = true;
			}

			if(snt[i].equals("yours") && !changed[i])
			{
				snt[i] = "mine";
				changed[i] = true;
			}

			if(snt[i].equals("ours") && !changed[i])
			{
				snt[i] = "yours";
				changed[i] = true;
			}

		}

		for(int i = 0; i < l; i++) //verb
		{
			
			if(snt[i].equals("are") && !changed[i])
			{
				snt[i] = "am";
				changed[i] = true;
			}

			if(snt[i].equals("am") && !changed[i])
			{
				snt[i] = "are";
				changed[i] = true;
			}
		}

		for(int i = 0; i < l; i++) //pronoun - the hard part
		{
			
			if(snt[i].equals("i") && !changed[i])
			{
				snt[i] = "you";
				changed[i] = true;

			}


			if(snt[i].equals("me") && !changed[i])
			{
				snt[i] = "you";
				changed[i] = true;

			}

			if(snt[i].equals("you") && !changed[i])
			{
				boolean isI = false;
				
				if(i!=0)
					if(verbs.indexOf(snt[i-1])!=-1)
						isI = true;
				if(i!=l)
					if(verbs.indexOf(snt[i+1])!=-1)
						isI = true;
					
				if(isI)
					snt[i] = "i";
				else
					snt[i] = "me";
				changed[i] = true;
			}
		}

		
	
		return snt;

	}


	public static String replace(String in, String oldText, String newText)
	{
		int idx = in.indexOf(oldText); //first match
		if(idx == 0)
			idx = 1;
		
		String uCT = in;

		if(idx!=-1)
		{
		
		
				String fp = in.substring(0,idx); //first part
				String lp = in.substring(idx+oldText.length(),in.length()); //last part
				in = fp + newText + lp;
				//System.out.println(in);
				
				if(uCT.charAt(idx - 1) != ' ' &&  uCT.charAt(idx + 1) != ' ')
					in = uCT;
				idx = fp.length() + newText.length();
		}

		return in;
	}



	public static String handleWhatWhere()
	{
		String answ = new String();
		String adv;
		String ver;

		String all[] = sent;
		ver = all[1];

		for(int i=2; i < all.length;i++)
			answ += all[i] + " "; 

		
		
		/*
		   The verb comes last if the verb used is "is" or "was"
		   ex: 
		   Q:What is this?
		   A:I have no idea what this is.


         */
		if(ver.equals("is") || all[1].equals("was"))   //ex:i don't know what this ///is/was///
			if(adverb_num == -1) //to avoid "is" twice
			   answ+=ver;

		
		/*
		  Adverbs mess it up.
		  ex:
		  Q:What are you doing here?
		  A:I don't know what I am doing here.


		*/
		if(adverb_num != -1 && !adverb.equals("do"))   //i don't know what I am doing here.
		{
			adv = adverb;
			int m = answ.indexOf(all[adverb_num]); //adverb position i.e doing
			StringBuffer sb = new StringBuffer(answ); //so I can insert easy
			sb.insert(m-1,  " " + ver); //insert the verb
			answ = sb.toString(); //convert back
			System.out.println("Adverb num: " + adverb_num + " " + adverb);
			adverb_num = -1;
			adverb = "";
		}
		else
		{
			
			//ADD the verb
			if(all.length > 3)
				if(!ver.equals("is") && !ver.equals("was")&&!all[2].equals("it"))
				{
				answ = ver + " " + answ;
				}

			/* it, should, could, would have special constructions
			ex1:
			Q:What should I do?
			A:I know what you should do.

			ex2:
			Q:What will it take.
			A:I don't know what it will take.


			*/
			if(all.length > 3)
				if(all[2].equals("it")||all[1].equals("would") ||
					all[1].equals("should") || all[1].equals("could")|| all[1].equals("did")
					|| all[1].equals("have")||all[1].equals("are"))	   //i don't know what it will take
				{
					answ = all[2] + " " + all[1] + " ";
					for(int i=3; i < all.length;i++)
					answ += all[i] + " "; 

				}

		}

		if(all.length < 3)
			answ = all[1] + " ";
			
		
		return answ;

	}
	public static String handleWhen()
	{
		
		/* When was Peter here?
		I'm not quite sure when peter was here
		
		When was I here last time?
		1    2   3 4        5
		I don't recall when you were here last time.

		*/
		String all[] = sent; //the sentance factorized
		String answ = new String(); //answer
		String adv; //adverb
		String ver = all[verb_num]; ///verb

		
		//ver = all[1];

		for(int i=2; i < all.length;i++)
			answ += all[i] + " ";
		
		
		if(ver.equals("did") || ver.equals("will") || ver.equals("does")|| ver.equals("do"))
		{
			answ = "";
			int i;
			for(i=1;i<verb_num;i++) //skip the question
			{
				answ = all[i] + " "; //add to the word
			}

			int z=i; //the position of the verb
			String adjBuffer = ""; //all possible adjectives
			for(int j=0;j<Words.adjective.length;j++)
				adjBuffer = adjBuffer + Words.adjective[j] + " ";
			for(int j=0;j<Words.color.length;j++)
				adjBuffer = adjBuffer + Words.color[j] + " ";


			if(!all[i+1].equals("the")&&adjBuffer.indexOf(all[i+1])==-1) //ex: the tanker - must be as one word
				answ = answ + all[i+1] + " "; //switch places of the worb

			else
			{
				answ=answ + all[i+1] + " " + all[i + 2] + " ";
				i++;
			}

			answ = answ + all[z] + " ";   // and the next word (when did it happen?)
			i=i+2;						// I don't know when it did happen!
			for(int q=i;q<all.length;q++)  //add the rest
				answ = answ + all[q] + " ";
		}

		//System.out.println(answ);
		


		return answ;

	}
	public static String handleWhere()
	{
		String answ = new String();
		String adv;
		String ver;

		String all[] = sent;
		ver = all[1];

		for(int i=2; i < all.length;i++)
			answ += all[i] + " "; 
		return answ;

	}
	public static String handleWhich()
	{
		String answ = new String();
		String adv;
		String ver;

		String all[] = sent;
		ver = all[1];

		for(int i=2; i < all.length;i++)
			answ += all[i] + " "; 

		if(verb_num == 1)
			answ = answ  + ver;
		else
			answ = ver +" "+ answ;

		return answ;

	}
	public static String handleWho()
	{
		String answ = new String();
		String adv;
		String ver;

		String all[] = sent;
		ver = all[1];

		for(int i=2; i < all.length;i++)
			answ += all[i] + " "; 
		
		if(verb.equals("is")&&verb.equals("does"))
			answ = answ  + ver;
		else
			answ = ver +" "+ answ;

		return answ;

	}
	public static String handleHow()
	{
		String answ = new String();
		String adv;
		String ver;

		String all[] = sent;
		ver = all[1];

		for(int i=1; i < all.length;i++)
			answ += all[i] + " "; 
		return answ;

	}
	public static String handleWhy()
	{
		String answ = new String();
		String adv;
		String ver;

		String all[] = sent;
		ver = all[1];

		for(int i=2; i < all.length;i++)
			answ += all[i] + " "; 
		return answ;

	}

	public static String handleIYou()
	{
		String answ = new String();
		

		for(int i=0; i < sent.length;i++)
			answ += sent[i] + " "; 
		
		return answ;
	}

	public static String handleTheRest()
	{
		String answ = new String();

		answ = sent[1] + " " + sent[0] + " ";
		for(int i=2; i < sent.length;i++)
			answ += sent[i] + " ";
		
		return answ;
	}



	
	//insert in a template and return.
	public static String insertTemplate(String in, String answer)
	{
		int end = in.indexOf('[') - 1;
		int start = end + 3;

		String firstPart = in.substring(0,end);
		String lastPart = in.substring(start,in.length());
		
		String tot = firstPart + " " + answer + " " + lastPart; 

		return tot;
	}

	public static String polish(String in)
	{
		in = replace(in, " . ", ". ");
		in = replace(in, " , ", ", ");
		in = replace(in, " ? ", "? ");
		in = replace(in, " ! ", "! ");
		
		char[] aC = in.toCharArray();
		for(int i=0; i < aC.length;i++)
		{
			if(aC[i] == 'i' && aC[i-1] == ' '&& aC[i+1] == ' ')
				aC[i] = 'I';
		}
		in ="";
		for(int i=0; i< aC.length; i++)
		{
			in+=aC[i];
		}
			
		return in;
	}
}
