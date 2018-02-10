#!/bin/bash

green=$(tput setaf 118)
yellow=$(tput setaf 214)
blue=$(tput setaf 69)
default=$(tput sgr0)

function finish {
    pids=(`cat ../data/pid/rs-*.pid`)
    for pid in "${pids[@]}"
    do
        kill -9 $pid
        wait $pid
    done
}
trap finish EXIT

mkdir -p ../data/rs-0-0
mkdir -p ../data/rs-0-1
mkdir -p ../data/rs-0-2
mkdir -p ../data/pid

mongod --replSet rs0 --port 27017 --bind_ip localhost --dbpath ../data/rs-0-0 --pidfilepath ../data/pid/rs-0-0.pid --smallfiles --oplogSize 128 2>&1 | sed "s/.*/$green&$default/" &
mongod --replSet rs0 --port 27018 --bind_ip localhost --dbpath ../data/rs-0-1 --pidfilepath ../data/pid/rs-0-1.pid --smallfiles --oplogSize 128 2>&1 | sed "s/.*/$blue&$default/" &
mongod --replSet rs0 --port 27019 --bind_ip localhost --dbpath ../data/rs-0-2 --pidfilepath ../data/pid/rs-0-2.pid --smallfiles --oplogSize 128 2>&1 | sed "s/.*/$yellow&$default/" &

sleep 5

rsconfig="{
    _id: 'rs0',
    members: [
        {_id: 0, host: 'localhost:27017'},
        {_id: 1, host: 'localhost:27018'},
        {_id: 2, host: 'localhost:27019'}
    ]
}"

mongo --port 27017 --eval "rs.initiate($rsconfig)"

cat