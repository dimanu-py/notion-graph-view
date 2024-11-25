#!/bin/bash

function get_all_bounded_contexts {
  bounded_contexts=$(find src/contexts -mindepth 1 -maxdepth 1 -type d -exec basename {} \; | grep -Ev '^(shared)$')
  echo "$bounded_contexts"
}

function run_tests {
  local contexts="$1"

  for context in $contexts; do
    echo "Running application and domain tests for: $context"
    infra_folders=$(find tests/contexts -type d -name infra)
    pdm run pytest -n auto $infra_folders -ra
  done
}

function main {
  local bounded_contexts
  bounded_contexts=$(get_all_bounded_contexts)

  if [[ -z "$bounded_contexts" ]]; then
    echo "No bounded contexts found in the src directory."
    exit 1
  fi

  run_tests "$bounded_contexts"
}

main
