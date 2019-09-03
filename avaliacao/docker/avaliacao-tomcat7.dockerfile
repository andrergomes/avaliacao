FROM tomcat:7-jdk8-openjdk-slim
MAINTAINER Andre Gomes
ADD postgresql-42.2.6.jar /usr/local/tomcat/lib/
ADD avaliacao.war /usr/local/tomcat/webapps/ 
COPY imagens /usr/local/tomcat/imagens/
VOLUME C:/Users/Suporte/postgres/imagens:/usr/local/tomcat/imagens/
EXPOSE 8080