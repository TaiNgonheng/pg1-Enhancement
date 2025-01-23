#!/bin/bash

printf "***************************************\n"
printf "*      Release note generator         *\n"
printf "***************************************\n\n\n"

if [ -n "$1" ]; then
  printf "Generating release tickets after release tags -> [$1]...\n"
else
  printf "Warning, previous release tags is not supplied!\n"
  exit 1
fi

git fetch --tags &>/dev/null

platform=""
if [[ $1 == be* ]]; then platform="be"
elif [[ $1 == app* ]]; then platform="fe"
else
  platform="bo"
fi

releaseCommitId=$(git rev-list -n 1 $1)

git rev-list --format=%B $releaseCommitId..HEAD > release.txt

printf "\nTickets in the release notes for platform $platform:\n"
grep -o 'CDRB-[0-9]\{4,5\}-'$platform release.txt | grep -o 'CDRB-[0-9]\{4,5\}' | sort -u

printf "\nAll tickets:\n"
grep -o 'CDRB-[0-9]\{4,5\}' release.txt | sort -u | tr '\n' ','
printf "\n"
rm release.txt

exit 0
