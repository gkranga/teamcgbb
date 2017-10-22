FROM tomcat
RUN apt-get update && apt-get upgrade -y
RUN apt-get install git -y
RUN apt-get install ssh -y
RUN mkdir /root/.ssh
RUN touch /root/.ssh/known_hosts
COPY id_rsa.pub /root/.ssh/
COPY id_rsa /root/.ssh/
RUN ssh-keyscan github.com >> /root/.ssh/known_hosts
RUN git init
RUN git config --global user.email "gkranganath2000@gmail.com"
RUN git config --global user.name "gkranga"
RUN git clone git@github.com:gkranga/teamcgbb.git
RUN cp /usr/local/tomcat/teamcgbb/OnlineShoppingPortal/target/OnlineShoppingPortal.war /usr/local/tomcat/webapps/
CMD /usr/local/tomcat/bin/startup.sh
