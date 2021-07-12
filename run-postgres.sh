#!/bin/bash

docker run \
  --detach \
  --name acme_testdatabase \
  --publish 5432:5432 \
  --env POSTGRES_INITDB_ARGS="--encoding=UTF8" \
  --env POSTGRES_DB=acme_test \
  --env POSTGRES_USER=acme_test \
  --env POSTGRES_PASSWORD=acme_test \
  postgres
