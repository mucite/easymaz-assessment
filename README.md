# Easymaz Technical Session

This is a collaborative working session, not an exam. Think of it as pairing with a colleague.

## Task

Build a REST API for creating restaurant sales.

- Java 21, Spring Boot 3, WebFlux, Reactor and MongoDB
- 60 minutes, using your normal IDE, tools and documentation

## How the hour goes

Four short rounds. Each one is a small, concrete task, and we will look at your code together afterwards.

| Round | Time | What |
|---|---|---|
| Intro | ~3 min | Say hello, share your screen |
| 1. Build | ~15 min | The sales API |
| 2. Check the data | ~12 min | Read a sale back and look at it in MongoDB |
| 3. Extend | ~15 min | We change the requirements together |
| 4. Test | ~12 min | Prove your change works |
| Your questions | ~3 min | |

- **You are not expected to finish everything.** A small, well-reasoned solution beats a rushed large one.
- **Nothing to prepare.** We are not measuring typing speed or trivia.
- Questions are about the code you just wrote. If you get stuck, we will nudge you. That is normal.

## Getting started

Requirements: Java 21, Maven, Docker.

```bash
docker compose up -d      # starts MongoDB on localhost:27017
mvn test                  # runs the tests
mvn spring-boot:run       # starts the app on http://localhost:8080
```

Check that everything works: `curl http://localhost:8080/ping` should return `{"app":"up","mongo":"up"}`.
Or run `./scripts/check-setup.sh`. Please do this a day before, so we do not spend session time on setup.

To stop MongoDB: `docker compose down` (add `-v` to wipe the data).

## Notes

- The project is a minimal skeleton. Structure it however you would in a real service.
- `/ping` and its test only exist to verify your setup.
