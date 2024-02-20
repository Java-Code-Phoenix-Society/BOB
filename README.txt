Bob Beta 1 Source
--- ---- - ------
http://www.geocities.com/CapeCanaveral/1630/bob.html
http://www.luka.home.ml.org/bob.html

-----------------
Copyright 1997 By Luka Crnkovic <devalmont@geocities.com>
All Rights Reserved.
-----------------

This program is under the Gnu Public Licence (GPL),
included as GPL.txt in the package. That means that
it is free software under some conditions. Basically it
is that the programs you make using the source must also
be free software. You must also include the original authors
name...blah...blah...blah.

Well, glad to have that overwith, as someone would say.

-------------------------------------------------------
INSTALLATION


It is quite simple. You have to have a java development kit,
for instance the JDK (http://www.javasoft.com).

to unpack the files just type:
  tar xvzf bobsrc.tar.gz (Linux)

or:
gzip -d|tar xvf

on pc:s
 (pk)unzip bobsrc
 
To build it you type:

 javac Bob.java

and to run it type

   java Bob

note: Bob can run as an applet as well.

---------------------------------------
CUSTOMIZING

Bob is very easy to customize. You have
two basic data-files. The bob.dat and
bob.tpl

bob.dat is the main datafile containing all
keywords and responses.
Here is an example entry:
-------------------------

#
joke
-
I hate jokes. Here is one for you anyway:

lim  sqrt(8) = 3
8->9
-
Another joke:
1+1=3 for large values of 1

---------------------------
Well, you get the point. If not,
here is another one:
--------------------------

#
beethoven
ludvig
ludvig van
-
I hate Beethoven. His music is trivial and pathetic.

--------------------------
The general structure is:

"#" - marks a new keyword
<keyword entries>
"-"  end of keywords or new answer
<answer 1>
-
<answer 2>
-
<answer n>


and then from the start for a new keyword.
As you can see, there can be severals words in 
every keyword (and by that loosing the original meaning of the word :) ).
There can be several keywords and several answers.
--
The bob.tpl contains the templates for generic answers
based on the user's question.

example:
---------------------------------------
#
who
-
Hey pal, don't ask about who [], it's top secret!
-
Hey, you know very well who [].
-
Huh?  I have no idea who [], do you???
-
Who [] has nothing to do with me.
-
Well, I could tell you who [], but you'd never believe me.
--------------------------------
example 2:
--------------------------------
#
how
-
I don't really know how [], but I sure would like to.
-
I don't know how []. Do you?
-
Scientist have been trying to figure out how []
for centuries, and you are asking me how???
-
Don't ask me about how []. It's none of your business.
-
Goedel once said "how [] cannot be defined within
the existing system.
-------------------------------------------
The structure is similar, but there are differences:

The [] marks where the output of Bob's LAP (Lexical Analysing Processor)
will be placed. You don't have to have the [], but that
will make Bob skip the output from LAP. 
The bob.tpl is GRAMMAR ORIENTED and not keyword. Therefor
you have only one 'keyword' -> the grammar object -> example: "who".

The larger database you create, the better it will be. My original
Bob dbase has 131 different keywords, and 39 grammatical objects.
The quality of this program depends only on the size of the
database.

-------------------------------------------
A short FAQ:


Q:Is this a FAQ?
A:Yes

Q: Is it short?
A: Yes

Q:Where can I get the latest version of Bob?
A: http://www.geocities.com/CapeCanaveral/1630/bob.html

Q: What is the latest version (1997-06-15)?
A: Beta 2

Q: Is Bob Shareware?
A: No. Bob is FREEWARE. Under the GPL.

Q: Can I use Bob as a base to build my own programs?
A: Yes, as long as your program is freeware and you release
the FULL source. (Read the Gnu Public Licence)

Q: There is this wierd bug, where can I report it?
A: /dev/null
   no, seriously devalmont@geocities.com
   
Q: There is this wierd bug when I type a specific message. 
   What should I do?
A: Don't type that specific message.

Q: Is there any documentation?
A: No.

Q: Is there going to be a new version of Bob.
A: No. I am doing an entirely new program called Fnord.

http://www.luka.home.ml.org/fnord.html

Fnord is much more advanced. If you find Bob interesting,
I suggest you take a look at the fnord home.
-----
Luka Crnkovic


