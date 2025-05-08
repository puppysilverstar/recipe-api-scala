FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

WORKDIR /app

COPY . .

RUN sbt compile stage

CMD ["target/universal/stage/bin/recipe-api", "-Dplay.http.secret.key=secret"]
