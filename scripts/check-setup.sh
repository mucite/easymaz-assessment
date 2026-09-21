#!/usr/bin/env bash
# Pre-interview environment check. Run from the repository root.
set -u

ok()   { printf '  [ OK ] %s\n' "$1"; }
fail() { printf '  [FAIL] %s\n' "$1"; FAILED=1; }
FAILED=0

echo "Checking environment..."

if command -v java >/dev/null 2>&1; then
  ver=$(java -version 2>&1 | head -1)
  if echo "$ver" | grep -q '"21'; then ok "Java 21 ($ver)"; else fail "Java 21 required, found: $ver"; fi
else
  fail "java not found"
fi

if [ -x ./mvnw ]; then MVN=./mvnw; elif command -v mvn >/dev/null 2>&1; then MVN=mvn; else MVN=""; fi
if [ -n "$MVN" ]; then ok "Maven ($MVN)"; else fail "Maven not found (install Maven or use your IDE's bundled Maven)"; fi

if command -v docker >/dev/null 2>&1 && docker info >/dev/null 2>&1; then
  ok "Docker is running"
else
  fail "Docker is not installed or not running"
fi

if docker compose up -d --wait >/dev/null 2>&1; then
  ok "MongoDB container is healthy on localhost:27017"
else
  fail "Could not start MongoDB (docker compose up -d --wait)"
fi

if [ -n "$MVN" ] && [ "$FAILED" -eq 0 ]; then
  echo "Running tests (first run downloads dependencies)..."
  if $MVN -q test; then ok "Tests pass"; else fail "Tests failed"; fi
fi

echo
if [ "$FAILED" -eq 0 ]; then echo "All checks passed."; else echo "Some checks failed."; exit 1; fi
