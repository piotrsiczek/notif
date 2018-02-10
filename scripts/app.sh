#!/bin/bash

green=$(tput setaf 118)
yellow=$(tput setaf 214)
blue=$(tput setaf 69)
default=$(tput sgr0)

function finish {
    pids=(`for f in ../data/pid/app-*.pid; do cat "$f"; echo ""; done`)
    for pid in "${pids[@]}"
    do
        kill -9 $pid
        wait $pid
    done
}
trap finish EXIT

#cd ../notif-server
#mvn clean spring-boot:run 2>&1 | sed "s/.*/$green&$default/" &
#cd ../notif-panel
#mvn clean spring-boot:run 2>&1 | sed "s/.*/$yellow&$default/" &
##cd ../notif-pi && mvn clean spring-boot:run 2>&1 | sed "s/.*/$green&$default/" &

cat