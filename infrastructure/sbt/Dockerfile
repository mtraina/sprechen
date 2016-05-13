FROM debian:8.4

MAINTAINER Matteo Traina <matteo.traina@ymail.com>

# add oracle java repo
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886

RUN apt-get update

# install java
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer
RUN apt-get install -y wget git unzip

# manual installation of sbt
# http://www.scala-sbt.org/0.13/docs/Manual-Installation.html
RUN wget -O /bin/sbt-launch.jar https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/0.13.11/sbt-launch.jar
ADD sbt /bin/sbt
RUN chmod u+x /bin/sbt

RUN echo "exit" | sbt

# cleanup
RUN apt-get clean && apt-get autoclean