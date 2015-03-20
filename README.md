This is a simple application that demonstrate use of various Java technologies like:
* Java 1.6+
* Spring 4
* Maven
* Hibernate
* Log4j
* H2 database (memory and in file)
* Dozer
* jUnit
* Mockito
* xercesImpl (xml processing)
* JAXB

It is also written with Clean Code principles so it is worth to have a look how it is to use this practices in work.

Its functionality is about maintaining informations about servers. It is easy to extend it to different data schemas.
Application works in interactive mode. You can start it simply by:
java -jar <path to server-manager.jar> [optional configuration file or 'help']

For available options pass 'help' parameter.

Main features:
* add servers from XML file to database
* list persisted servers
* count persisted servers
* change name of persisted servers
* delete servers from database

I hope you find it useful in this or in other way.
