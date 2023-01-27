FROM alpine:3.17

MAINTAINER Moritz Moxter <moritz@clockworks.io>
LABEL Description="Docker based Github Action runner for Clojure projects" 
LABEL Vendor="Clockworks UG" 
LABEL Version="1.0.0"
LABEL Platform="amd64"

RUN apk add --no-cache openjdk17 curl bash tar


RUN mkdir -p /opt/usr

WORKDIR /opt/usr

RUN curl -O https://download.clojure.org/install/linux-install-1.11.1.1208.sh && \
	chmod +x linux-install-1.11.1.1208.sh && \
	./linux-install-1.11.1.1208.sh && \
	rm linux-install-1.11.1.1208.sh


RUN curl -L https://github.com/babashka/babashka/releases/download/v1.1.172/babashka-1.1.172-linux-amd64-static.tar.gz > babashka-1.1.172-linux-amd64-static.tar.gz && \
	tar xfv babashka-1.1.172-linux-amd64-static.tar.gz && \
	mv bb /usr/local/bin/bb && \
	chmod +x /usr/local/bin/bb && \
	rm babashka-1.1.172-linux-amd64-static.tar.gz



COPY entrypoint.sh /entrypoint.sh
COPY run.clj /run.clj
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]