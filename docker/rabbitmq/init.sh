#!/bin/sh
set -e

echo "INIT START"
ls -la /definitions.json

echo "WAIT API"
until curl -s -u bootstrap:bootstrap http://rabbitmq:15672/api/overview >/dev/null; do
  echo "waiting..."
  sleep 2
done

echo "APPLY DEFINITIONS (POST)"
curl -v -u bootstrap:bootstrap \
  -H "Content-Type: application/json" \
  -X POST \
  --data-binary "@/definitions.json" \
  http://rabbitmq:15672/api/definitions

echo "INIT END"
