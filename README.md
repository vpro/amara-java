# Amara Java Client
[![Build Status](https://travis-ci.org/vpro/amara-java.svg?)](https://travis-ci.org/vpro/amara-java)
[![Maven Central](https://img.shields.io/maven-central/v/nl.vpro.amara/amara-java.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22nl.vpro.amara%22%20AND%20a:%22amara-java%22)
[![snapshots](https://img.shields.io/nexus/s/https/oss.sonatype.org/nl.vpro.amara/amara-java.svg)](https://oss.sonatype.org/content/repositories/staging/nl/vpro/amara/amara-java)
[![javadoc](http://www.javadoc.io/badge/nl.vpro.amara/amara-java.svg?color=blue)](http://www.javadoc.io/doc/nl.vpro.amara/amara-ava)



This is a simple java client for the amara project (http://www.amara.org/). It isn't yet complete, but methods can easilty be added. The API itself is documented [here](https://amara.org/api/ "Amara API documentation") (You need to be logged in)
 

## How to use
 
 
 ```java
import nl.vpro.amara.domain.*;
import nl.vpro.amara.Client;
 ...

 
  Client amaraClient = Client.builder()
                 .url(getRequiredConfig("amara.api.url"))
                 .user(getRequiredConfig("amara.api.username"))
                 .apiKey(getRequiredConfig("amara.api.key"))
                 .team(getRequiredConfig("amara.api.team"))
                 .build();
                 
   Activity amaraActivity = amaraClient.activities().get("5036197");
   
   Video amaraVideo = amaraClient.videos().get("FSW0qzp2Enlk"); 
   
   List<Activity> amaraActivities = amaraClient.activity().list(Activity.TYPE_APPROVE_VERSION, now - afterTimestampInSeconds).getActivities();

 ```


## Installation

Download the most recent jar from: https://oss.sonatype.org/content/repositories/snapshots/nl/vpro/amara/amara-java and install it like you'd normally would.

Or you can add this to your pom.xml
```xml
<dependency>
  <groupId>nl.vpro.amara</groupId>
  <artifactId>amara-java</artifactId>
  <version>0.8</version>
</dependency>
```
