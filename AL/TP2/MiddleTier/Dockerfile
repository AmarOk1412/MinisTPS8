FROM ubuntu

RUN apt-get -y update && apt-get install -y software-properties-common --fix-missing
RUN add-apt-repository ppa:cwchien/gradle && apt-get -y update
RUN apt-get install -y git gradle
RUN apt-get update && apt-get install -y openjdk-7-jdk
RUN git clone https://github.com/Netflix/recipes-rss.git
WORKDIR recipes-rss
ADD edge.properties ./rss-edge/src/main/resources/edge.properties
ADD middletier.properties ./rss-middletier/src/main/resources/middletier.properties
RUN ./gradlew clean build
ENV APP_ENV=dev
ADD rss-edge.sh ./rss-edge.sh
ADD middletier.sh ./middletier.sh
EXPOSE 9090
EXPOSE 9191
