### Status
[![Build Status](https://travis-ci.org/msamayoar/zookeeper-curator-balancer.svg?branch=master)](https://travis-ci.org/msamayoar/zookeeper-curator-balancer)

Zookeeper and microservices-load-balancing
============================

How it works?

[Zookeeper](https://zookeeper.apache.org/)  provides a central service for register, manage and discovery services  running on distributed machines.

Since ZooKeeper is a sub-project of Hadoop, the purpose of Zookeeper is cluster management, but you can use it, to implement different ["recipes and solutions"]( http://zookeeper.apache.org/doc/trunk/recipes.html) that require  cluster management like locks, balancing, leader election and so forth.

We're going to use it for Load balancing microservices with a Netflix project called [Curator](https://github.com/Netflix/curator/wiki), Curator now is a Apache project, and this framework provides recipes of ZooKeeper and API's to register and discovery services in ZooKeeper.

###Simple Worker
Our distributed service will be different instances of `simple-worker` Spring Boot project. You can start one single instance in different machines by using the following command:

`java -jar simple-worker-1.0-SNAPSHOT.jar Worker_1 9001 --server.port=9001`

You can access the url of you worker with the url: `http://{host}:{port}/api/customer/list`.

###Simple Manager
Our simple manager service is waiting request on `/cl/api/customer/list`, note the 'cl' prefix in the url, is to differentiate our cluster, this service will delegate the request to the workers registered in ZooKeeper trough Curator Framework. This manager doesn’t know anything about the workers register and you can test it, adding or removing 'simple-workers' instances.  You can run the simple manager by using the following command:

`java -jar simple-manager-1.0-SNAPSHOT.jar --server.port=8001`


>Note: make sure you that before running any worker or the manager you already have [installed](https://zookeeper.apache.org/doc/trunk/zookeeperStarted.html#ch_GettingStarted) and running your ZooKeeper service in the 2181 port.

In your Spring Boot application you'll see logs like this:

###NORMAL
`2017-01-13 12:45:31.732  INFO 29845 --- [nio-9094-exec-9] c.m.controller.DelegateController        : response :http://localhost:9093/api/customer/list
2017-01-13 12:45:31.780  INFO 29845 --- [io-9094-exec-10] c.m.controller.DelegateController        : response :http://localhost:9091/api/customer/list
2017-01-13 12:45:31.789  INFO 29845 --- [nio-9094-exec-1] c.m.controller.DelegateController        : response :http://localhost:9093/api/customer/list
2017-01-13 12:45:31.828  INFO 29845 --- [nio-9094-exec-2] c.m.controller.DelegateController        : response :http://localhost:9091/api/customer/list`

###FAILED
2017-01-13 12:47:12.995 ERROR 29845 --- [nio-9094-exec-3] c.m.controller.DelegateController        : Error: java.net.ConnectException: Connection refused
2017-01-13 12:47:14.329  INFO 29845 --- [nio-9094-exec-5] c.m.controller.DelegateController        : response :http://localhost:9091/api/customer/list
2017-01-13 12:47:15.450 ERROR 29845 --- [nio-9094-exec-7] c.m.controller.DelegateController        : Error: java.net.ConnectException: Connection refused
