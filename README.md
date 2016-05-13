# Amara Java Client
[![Build Status](https://travis-ci.org/vpro/amara-java.svg?)](https://travis-ci.org/vpro/amara-java)


This is a simple java client for the amara project (http://www.amara.org/).
 

 ##How to use
 
 
 ```java
 
  Client amaraClient = new Client.Builder()
                 .url(getRequiredConfig("amara.api.url"))
                 .user(getRequiredConfig("amara.api.username"))
                 .apiKey(getRequiredConfig("amara.api.key"))
                 .team(getRequiredConfig("amara.api.team"))
                 .build();
                 
   Activity amaraActivity = Config.getAmaraClient().getActivity("5036197");
 ```


##Installation

Download the most recent jar from: https://oss.sonatype.org/content/repositories/snapshots/nl/vpro/amara/amara-java and install it like you'd normally would.

Or you can add this to your pom.xml
```xml
<dependency>
  <groupId>nl.vpro.amara</groupId>
  <artifactId>amara-java</artifactId>
  <version>1.0</version>
</dependency>
```
