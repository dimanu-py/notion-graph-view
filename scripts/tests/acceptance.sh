#!/bin/bash

function run_tests {
  pdm run pytest -n auto tests/delivery -ra
}

function main {
  run_tests
}

main