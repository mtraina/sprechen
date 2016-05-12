FROM debian:8.4


ENV TERM=xterm-256color \
    SCALA_VERSION=2.11.7 \
    SBT_VERSION=0.13.11 \
    JAVA_VERSION=8 \
    JAVA_HOME=/usr/lib/jvm/java-8-oracle/jre

# update first time to install apt https package, needed for installing sbt
RUN apt-get update
RUN apt-get install -y wget git unzip java6-runtime-headless

# auto validate license
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections

# java repos
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886

# download sbt
RUN wget http://www.scala-lang.org/files/archive/scala-${SCALA_VERSION}.deb && \
    wget http://dl.bintray.com/sbt/debian/sbt-${SBT_VERSION}.deb && \
    dpkg -i scala-${SCALA_VERSION}.deb && \
    dpkg -i sbt-${SBT_VERSION}.deb && \
    apt-get update && \
    apt-get install -y scala sbt && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* /*.deb && \
    echo "exit" | sbt
#RUN echo deb http://dl.bintray.com/sbt/debian / > /etc/apt/sources.list.d/sbt.list
#RUN wget -c https://bintray.com/artifact/download/sbt/debian/sbt-0.13.11.deb
#RUN dpkg -i sbt-0.13.11.deb

RUN apt-get update

# install java
#RUN apt-get install -y oracle-java8-installer

# install sbt
#RUN apt-get install -yq --force-yes --no-install-recommends sbt

# cleanup
#RUN apt-get clean && apt-get autoclean

#ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# get code and deploy it
#RUN git clone https://github.com/mtraina/sprechen.git
#RUN cd ./sprechen && sbt dist
#RUN unzip ./sprechen/target/universal/sprechen-1.0-SNAPSHOT.zip

#CMD sprechen-1.0-SNAPSHOT/bin/sprechen

EXPOSE 9000
