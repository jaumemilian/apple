#!/bin/bash

function getVersion() {
   cat Jenkinsfile | grep -e "def version\s*=\s*'.*'" | sed 's/def version//' | sed 's/=//' | sed 's/ //g' | sed "s/'//g"
}

function setVersion() {
  prev=$(getVersion); new=$1
  echo "Changing version from $prev to $new..."

  # jenkinsfile
  sed -i.bak "s/\(def version[ ]*=[ ]*'\)\(.*\)\('\)/\1$new\3/" Jenkinsfile

  # maven project
  mvn versions:set -DnewVersion=$new
  mvn versions:commit

  # adding copyrights to the release branch only
  if [[ $new != *"SNAPSHOT" ]]; then
     echo "Adding copyrights to release version $new"
     mvn clean install -DskipTests -P copyright
     mvn clean install -DskipTests -P license
  fi
  # other files
  files=("*.dockerfile" "*.md" "*.MD" "Chart.yaml" "requirements.yaml" "pom.xml" "kube-config.yaml" "values.yaml")
  for file in "${files[@]}"
  do
    find . -type f -name "$file" -exec sed -i.bak "s/${prev}/${new}/g" {} +
  done
  find . -name "*.bak" -type f -delete
}

function getReleaseBranch() {
  cat Jenkinsfile | grep -e "def releaseBranch\s*=\s*'.*'" | sed 's/def releaseBranch//' | sed 's/=//' | sed 's/ //g' | sed "s/'//g"
}

function setReleaseBranch() {
  prev=$(getReleaseBranch)
  new=$1
  sed -i.bak "s/\(def releaseBranch[ ]*=[ ]*'\)\(.*\)\('\)/\1$new\3/" Jenkinsfile
  find . -name "*.bak" -type f -delete
}

function usage() {
  echo "`basename ${0}`! usage: ci.sh version get | version set new | release-branch get | release-branch set new"
  exit 1
}

case "$1" in
  "version")
    case "$2" in
      "get")
        getVersion
        ;;
      "set")
        if [ -z "$3" ]
        then
          usage
        fi
        setVersion "$3"
        ;;
      *)
        usage
        ;;
    esac
    ;;
  "release-branch")
    case "$2" in
      "get")
        getReleaseBranch
        ;;
      "set")
        if [ -z "$3" ]
        then
          usage
        fi
        setReleaseBranch "$3"
        ;;
      *)
        usage
        ;;
    esac
    ;;
  *)
    usage
    ;;
esac