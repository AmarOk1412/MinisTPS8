#!/bin/python
import commands

MAX_INSTANCE = 2
listeActive = commands.getstatusoutput("docker ps")[1]

for i in range(1,MAX_INSTANCE+1):
    if 'middletier' + str(i) not in listeActive:
	port = 9191 + i
        print("sudo docker rm middletier{0}".format(i))
        print("sudo docker run --rm --name middletier{0} -h middletier{0} --link tomcat:tomcat -p {1}:9191 middletier sh middletier.sh&".format(i, port))
    if 'edge' + str(i) not in listeActive:
        port = 9090 + i
        print("sudo docker rm edge{0}".format(i))
        print("sudo docker run --rm --name edge{0} -h edge{0} --link tomcat:tomcat --link middletier{0}:middletier -p {1}:9090 middletier sh rss-edge.sh&".format(i, port))
