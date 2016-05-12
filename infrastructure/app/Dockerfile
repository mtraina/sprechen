FROM sbt-base

# get code and deploy it
RUN git clone https://github.com/mtraina/sprechen.git
RUN cd ./sprechen && sbt dist
#RUN unzip ./target/universal/sprechen-1.0-SNAPSHOT.zip

# start the server
#CMD sprechen-1.0-SNAPSHOT/bin/sprechen

EXPOSE 9000
