FROM debian:8.4

# update first time to install apt https package, needed for installing sbt
RUN apt-get update

# auto validate license
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections

# java repos
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list

# sbt repo
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-get install apt-transport-https ca-certificates -y

# add key
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
RUN apt-get update

# install java
#RUN apt-get install oracle-java8-installer -y
#RUN sbt install

# cleanup
#RUN apt-get clean && apt-get autoclean

#ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# get code and deploy it
#RUN git clone https://github.com/mtraina/sprechen.git
#RUN cd ./sprechen && sbt dist
#RUN unzip ./sprechen/target/universal/sprechen-1.0-SNAPSHOT.zip

#CMD sprechen-1.0-SNAPSHOT/bin/sprechen

EXPOSE 9000
