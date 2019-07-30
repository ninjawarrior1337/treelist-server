FROM gradle as builder

WORKDIR /opt/treelist

COPY build.gradle.kts .

RUN gradle resolveDependencies

COPY . .

RUN gradle installDist

FROM openjdk:8-alpine

WORKDIR /opt/treelist

ENV MUSIC_DIR /music
ENV CONFIG_DIR /config

VOLUME /music
VOLUME /config

EXPOSE 5000

COPY --from=builder /opt/treelist/build/install/treelist-server .

CMD bin/treelist-server