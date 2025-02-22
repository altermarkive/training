#!/usr/bin/env sh

set -e

echo "--- isort ---"
isort --settings-file .github/linters/.isort.cfg --check algorithms/code
echo "--- pycodestyle ---"
pycodestyle --ignore=E203,W503 .
echo "--- flake8 ---"
flake8 --ignore=E203,W503
echo "--- pylint ---"
find . -name '*.py' | grep -v banking_api | xargs pylint --disable=C0103,C0111,C0302,R0801,R0903
echo "--- mypy ---"
mypy algorithms --exclude banking_api
echo "--- bandit ---"
bandit -r .
