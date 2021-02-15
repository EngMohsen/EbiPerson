FROM centos

RUN yum install -y java

VOLUME /tmp

ADD target/EbiProblem1-0.0.1-SNAPSHOT.jar ebiApp.jar

RUN sh -c 'touch /ebiApp.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ebiApp.jar"]