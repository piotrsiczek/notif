# Spring Boot 2.0.0.RC1 + Angular 5.2.0 + mongoDB 3.6 Change Streams

Exemplary application using Reactive WebClient to create async api and Server Send Event to capture notifications received from mongoDB via Change Streams to allow to access real-time data changes.
To make things more interesting it uses Raspberry Pi 2 B and LIS3DH accelerometer to to emit data. 

### Project structure

### Apps Description

App Name | Description | Profiles
--- | ------ | ---
notif-pi | Application generating data, can generate random values or if launched on raspberry accelerometer data | pi - run on pi <br> local - run locally
notif-server | Server fetching data from notif-pi app and saving it to mongoDb database |
notif-panel | Angular frontend visualizing data |

### How to build

1. Run mongo db replica set
```bash
cd scripts
sh mongo.sh
```

2. Build and run all three apps
```bash
cd notif-pi
mvn clean install spring-boot:run -Dspring.profiles.active=local
cd ../notif-server
mvn clean install spring-boot:run
cd ../notif-panel
mvn clean install spring-boot:run
cd src/main/angular/panel/
npm start
```

3. Open your browser on http://localhost:4200/