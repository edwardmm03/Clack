# Clack Part 3
Megan Edwards and Kell Grady

This first run is closed incorrectly and that can be seen with the exit code that is produced. In additon in the first two runs shown here we did not decrypt 
the message when it was echoed back so we added this functionality in the last run we preformed.

"C:\Program Files\Java\jdk1.8.0_341\bin\java.exe" -Dfile.encoding=windows-1252 -jar "C:\Users\megan\OneDrive\Documents\CS 242\Clack\out\artifacts\ClackClient_jar\ClackClient.jar" Kell@128.153.207.223:7000
Hello
Tue Nov 15 22:34:21 EST 2022
2
Kell
Amxph
h
Tue Nov 15 22:34:41 EST 2022
2
Kell
a

Process finished with exit code 130

"C:\Program Files\Java\jdk1.8.0_341\bin\java.exe" -Dfile.encoding=windows-1252 -jar "C:\Users\megan\OneDrive\Documents\CS 242\Clack\out\artifacts\ClackClient_jar\ClackClient.jar" Kell@128.153.207.223:7000
hello world
Tue Nov 15 22:46:10 EST 2022
2
Kell
amxph eavel
DONE
Tue Nov 15 22:46:14 EST 2022
1
Kell
WWZI

Process finished with exit code 0

In this final test we made sure that the message was properly decryped when it was printed back after being
received from the server.

hello world
Tue Nov 15 23:13:12 EST 2022
2
Kell
hello world





