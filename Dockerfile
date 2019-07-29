FROM gradle as builder

WORKDIR /opt/treelist

COPY . .

RUN gradle installDist

FROM openjdk:8-alpine

WORKDIR /opt/treelist

COPY --from=builder /opt/treelist/build/install/treelist-server .

ENV MUSIC_DIR /music
ENV CONFIG_DIR /config

VOLUME /music
VOLUME /config

EXPOSE 5000

CMD bin/treelist-server