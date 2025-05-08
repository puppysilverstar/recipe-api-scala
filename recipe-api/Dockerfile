FROM hseeberger/scala-sbt:11.0.15_1.8.0_1.7.2

WORKDIR /app

COPY . .

RUN sbt compile stage

CMD ["target/universal/stage/bin/recipe-api", "-Dplay.http.secret.key=secret"]
