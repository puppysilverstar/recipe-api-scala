FROM hseeberger/scala-sbt:11.0.16_1.8.2_1.7.1

WORKDIR /app

COPY . .

RUN sbt compile stage

CMD ["target/universal/stage/bin/recipe-api", "-Dplay.http.secret.key=secret"]




