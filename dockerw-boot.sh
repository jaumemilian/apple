#!/bin/sh
case "${1}" in
   "build")
      echo "Building [apple-boot] docker image..."
      docker build -f boot.dockerfile -t apple-boot .
      ;;
   "start")
      echo "Starting [apple-boot] docker container..."
      docker run --name apple-boot -p 8500:8500 -p 8599:8599 apple-boot
      ;;
   "stop")
      echo "Stopping and cleaning up [apple-boot] docker container..."
      docker stop apple-boot > /dev/null 2>&1
      id=$(docker ps -a -q --filter ancestor=apple-boot --format="{{.ID}}")
      if [ -n "$id" ]
      then
          docker stop $id > /dev/null 2>&1
      fi
      docker rm apple-boot > /dev/null 2>&1
      ;;
   "restart")
      echo "Restarting [apple-boot] docker container..."
      sh ./dockerw-boot.sh stop ; sh ./dockerw-boot.sh build && sh ./dockerw-boot.sh start
      ;;
   "attach")
      docker exec -i -t apple-boot /bin/bash
      ;;
   *)
      echo "`basename ${0}`! usage: dockerw-boot.sh build | start | stop | restart | attach"
      exit 1
      ;;
esac