FROM mtraina/java8-sbt

# copy over private key, and set permissions
ADD id_rsa /root/.ssh/id_rsa
RUN chmod 700 /root/.ssh/id_rsa
RUN chown -R root:root /root/.ssh
RUN echo "Host github.com\n\tStrictHostKeyChecking no\n" >> /root/.ssh/config

# get code and deploy it
RUN git clone git@github.com:mtraina/sprechen-prod.git
RUN cd /sprechen-prod && sbt dist
RUN unzip /sprechen-prod/target/universal/sprechen-1.0-SNAPSHOT.zip

# start the server
CMD sprechen-1.0-SNAPSHOT/bin/sprechen

EXPOSE 9000