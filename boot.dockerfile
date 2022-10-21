FROM centos:7 as builder
RUN yum -y update

RUN mkdir /home/asg
ADD apple-war/target/apple-war-1.0.0-SNAPSHOT.war /home/asg/apple.war

FROM wal-artifactory.rocketsoftware.com:6564/7adoptium8
COPY --from=builder /usr/lib64/ /usr/lib64/
COPY --from=builder --chown=6006:8008 /home/asg /home/asg
VOLUME /tmp
RUN chmod 1777 /tmp


EXPOSE 8500
EXPOSE 8599
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,address=8599,suspend=n"


CMD ["bash", "-c", \
"java ${JAVA_OPTS} -jar /home/asg/apple.war;"
]
USER asg
