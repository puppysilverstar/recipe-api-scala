FROM hseeberger/scala-sbt:17.0.15_2.13.16_1.9.9

WORKDIR /app

COPY . .

RUN sbt compile stage

CMD ["target/universal/stage/bin/recipe-api", "-Dplay.http.secret.key=secret"]
