FROM ubuntu:14.04

ENV TOMCAT_VERSION 8.0.33

# Install dependencies
RUN apt-get update && \
apt-get install -y git build-essential curl wget software-properties-common

# Install JDK 8
RUN \
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
add-apt-repository -y ppa:webupd8team/java && \
apt-get update && \
apt-get install -y software-properties-common && \
add-apt-repository ppa:cwchien/gradle && apt-get -y update && \
apt-get install -y git gradle oracle-java8-installer wget unzip tar && \
rm -rf /var/lib/apt/lists/* && \
rm -rf /var/cache/oracle-jdk8-installer

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Build Eureka
RUN git clone https://github.com/Netflix/eureka.git
WORKDIR eureka
RUN ./gradlew clean build

# Get Tomcat
RUN rm /bin/sh && ln -s /bin/bash /bin/sh
RUN wget --quiet --no-cookies http://apache.rediris.es/tomcat/tomcat-8/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tgz && \
tar xzvf /tmp/tomcat.tgz -C /opt && \
mv /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
rm /tmp/tomcat.tgz 

# Configure Tomcat
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin

EXPOSE 80
EXPOSE 8009
VOLUME "/opt/tomcat/webapps"
WORKDIR /opt/tomcat

# Deploy Eureka
#RUN cp /eureka/eureka-server/build/libs/eureka-server-1.4.7-SNAPSHOT.war /opt/tomcat/webapps/eureka.war
ADD startEureka.sh /opt/tomcat/conf/
ADD tomcat-users.xml /opt/tomcat/conf/
ADD server.xml /opt/tomcat/conf/

# Launch Tomcat
CMD ["sh", "/opt/tomcat/conf/startEureka.sh"]
#CMD ["/opt/tomcat/bin/catalina.sh", "run"]

