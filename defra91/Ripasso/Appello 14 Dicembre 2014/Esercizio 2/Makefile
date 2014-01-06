default: OImpl.class Server.class program1.class program2.class

OImpl.class : OImpl.java
	javac OImpl.java -d ./

Server.class : Server.java
	javac Server.java -d ./

program1.class : program1.java
	javac program1.java -d ./

program2.class : program2.java
	javac program2.java -d ./

start: 
	rmiregistry &
	java appello.Server & 
	java appello.program1 &
	java appello.program2 &

stop:
	killall -q rmiregistry &