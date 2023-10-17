javac -d . *.java
jar cvf externalisation.jar basesql session servlet
# cp -R basesql servlet session /opt/tomcat/apache-tomcat-10.1.5/webapps/HAproxySession/WEB-INF/classes 
# cp externalisation.jar /opt/tomcat/apache-tomcat-10.1.5/webapps/HAproxySession/WEB-INF/lib

copy -R basesql servlet session "D:/Logiciel/Tomcat/webapps/HAproxySession/WEB-INF/classes"
copy externalisation.jar "D:/Logiciel/Tomcat/lib"