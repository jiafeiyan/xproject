FROM java:8
MAINTAINER zeus
RUN apt-get -y update && apt-get install -y nginx supervisor
RUN mkdir -p /var/log/supervisor
ADD boot //
ADD web /root/web
ADD nginx /etc/nginx
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf
ENTRYPOINT ["/usr/bin/supervisord"]