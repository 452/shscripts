#!/bin/bash

#MESSAGE_TO_SEND="send"
#BASE_JOLIKIA_EXEC_ADDR="http://localhost:8181/jolokia/exec"

#curl --user admin:admin -G -v "$BASE_JOLIKIA_EXEC_ADDR/org.apache.activemq:type=Broker,brokerName=root,destinationType=Queue,destinationName=test/sendTextMessage(java.lang.String,java.lang.String,java.lang.String)/$MESSAGE_TO_SEND/admin/admin"


#MESSAGE_TO_POST='{"type":"exec","mbean":"org.apache.activemq:type=Broker,brokerName=root,destinationType=Queue,destinationName=test","operation":"sendTextMessage(java.lang.String,java.lang.String,java.lang.String)","arguments":["message","admin","admin"]}'

#curl --user admin:admin $BASE_JOLIKIA_POST_ADDR -v -XPOST -d $MESSAGE_TO_POST 

#curl --user admin:admin $BASE_JOLIKIA_POST_ADDR -v -XPOST -d '{"type":"exec","mbean":"org.apache.activemq:type=Broker,brokerName=root,destinationType=Queue,destinationName=test","operation":"sendTextMessage(java.lang.String,java.lang.String,java.lang.String)",,"arguments":["message","admin","admin"]}' 

#MESSAGE_TO_POST='{"type":"exec","mbean":"org.apache.camel:context=org.jboss.quickstarts.fuse.eip,type=routes,name=\"filterRoute\"","operation":"stop()"}'

#curl --user fuse:fuse $BASE_JOLIKIA_POST_ADDR -v -XPOST -d $MESSAGE_TO_POST 

#MESSAGE_TO_POST='{"type":"exec","mbean":"io.fabric8:type=Fabric","operation":"fabricStatus()"}'
BASE_JOLIKIA_GET_ADDR="http://localhost:8181/hawtio/jolokia/"
MESSAGE_TO_GET='read/java.lang:type=Memory/HeapMemoryUsage'
#MESSAGE_TO_GET='exec/org.apache.karaf:type=bundles,name=root/list'
echo $BASE_JOLIKIA_GET_ADDR$MESSAGE_TO_GET
curl --user fuse:fuse -s -XGET $BASE_JOLIKIA_GET_ADDR$MESSAGE_TO_GET | python -m json.tool | pygmentize -g
#http://localhost:8181/hawtio/jolokia/list
#curl --user fuse:fuse $BASE_JOLIKIA_GET_ADDR -v  $MESSAGE_TO_GET
#jolokia/read/java.lang:type=Memory/HeapMemoryUsage
#curl --verbose  --user fuse:fuse http://localhost:8181/hawtio/jolokia/version
