# spring-cloud-eureka-sample-microservice-b-instance2
<!-- PROJECT SHIELDS -->

<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2.svg?style=for-the-badge
[contributors-url]: https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2.svg?style=for-the-badge
[forks-url]: https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2/network/members
[stars-shield]: https://img.shields.io/github/stars/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2.svg?style=for-the-badge
[stars-url]: https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2/stargazers
[issues-shield]: https://img.shields.io/github/issues/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2.svg?style=for-the-badge
[issues-url]: https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/ye-win-1a33a292/

<h1 align="center">
  Overview
  <img src="https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2/blob/master/github/template/images/overview/eureka.png" /><br/>
</h1>


# spring-cloud-eureka-sample-microservice-b-instance2
* This is the sample microservice for Spring Cloud Netflix Eureka service B Instance project.
* So, this service is same with [Microservice b](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b) project as I duplicate Service B to this service as Instance of Service B.
* Eureka is for service discovery. Microservices (client) can register in Eureka Server and communicate each other through Eureka server by Service name.
* This project is communicate with [Eureka Server](https://github.com/yewin-mm/spring-cloud-eureka-server) for Service Registry <br> 
and communicate with [Microservice a](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) and [Microservice b](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b).

<!-- TABLE OF CONTENTS -->
## Table of Contents
- [About The Project](#about-the-project)
    - [Built With](#built-with)
- [Getting Started](#getting-started)
    - [Before you begin](#before-you-begin)
    - [Clone Project](#clone-project)
    - [Prerequisites](#prerequisites)
    - [Instruction](#instruction)
      -  [Checking Eureka Server Portal](#check-eureka-server)
      -  [Testing](#testing)
          -  [Call APIs by Direct](#call-api-direct)
          -  [Call APIs through Eureka ](#call-api-eureka)
- [Contact Me](#contact)
- [Contributing](#Contributing)


<a name="about-the-project"></a>
## ⚡️About The Project
This is Demo Project for Spring boot with Spring Cloud Netflix Eureka as Service Discovery and communicate with other microservices.<br>
There are two types of service discovery, <br>
1. Client Side Service Discovery
2. Server Side Service Discovery

Eureka is for Client Side Service Discovery and the main advantages is Microservices can communicate well even without knowing ip address location.<br>
Because normally we need ip:port to call service. (Here, service means microservice) <Br>
* Let's say `Service B (producer)` call to `Service A (consumer)`.
* We need the `location (ip address:port)` of Service A to call from B.
* But here, Eureka Server provide for `registry` and for that case, Services need to register in Eureka Server.
* Eureka know the registered Service location and map as Service Name. <br> And Discovery Server is like database and storing Services location and querying Service location from it's database <br>
So that, Service B can call to Service A with `Service Name` and Eureka Server take that name and route to Service A location.
* So, Service B don't need to know for Service A location and just only need to know the Service Name which registered in Eureka, and it's the one of the advantage of using Service Discovery. <br>
Because we might migrate Service A deployed Server and if we changed that the address (ip) will change also.
* If we used the direct calling way rather than using Service Discovery approach, we need to change the url in Service B when B call to Service A. 
<br> As an example, we need to change url in RestTemplate to call A when A deployed server was changed.
* But using with Service Discovery, we don't need to change the url as we use Service Name instead of using ip:port address when B call to A. <br> 
Because Eureka can send actual location (ip address) of registered services when one service ask for other services location by Service Name. So, we need only Service Name to call other services.
* Also, Eureka provide for `Load Balancing` collaboration with `Ribbon`. <br>
It's mean, Service A might have many Instances (duplicate service) `for handling thousands requests`.<br>
We use that kind of multi instances approach in Enterprise level applications.
* Using Load Balancer, Service B don't need to add url for each Instance location and Load Balancer will auto route to Instance of Service A. <br>
In this Example, I dropped for Service B and Service B instance. And Service A as single instance, it might have more than one Instance.
* The disadvantages of using Client Side Service Discovery is all Services need to add Eureka in application and <br> 
because if Service don't connect with Eureka, Eureka don't know the service location and other service can't connect to that service through Eureka.
* In modern cloud based service deployment architecture, I `don't recommend` to use `Eureka` and use Server Side Discovery (same logic with Client Side Discovery) not to depend on connecting to Discovery from each Service. <br>
Using Server Side Discovery, every Service don't need to add code logic for Load Balancing and Load Balance will serve as Separately. <br>
eg. Kubernetes orchestrate for each Container and involved Load Balancer as native.

Firstly, you should pull [Eureka Server](https://github.com/yewin-mm/spring-cloud-eureka-server) as for server and [Microservice a](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) and [Microservice b](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b) for testing.<br>
I've dropped [Instructions](#instruction) in below.

<a name="built-with"></a>
### 🪓 Built With
This project is built with
* [Java](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Eureka Server](https://github.com/yewin-mm/spring-cloud-eureka-server)

<a name="getting-started"></a>
## 🔥 Getting Started
This project is built with Java, Maven, [Eureka Server](https://github.com/yewin-mm/spring-cloud-eureka-server) as Server and use `Project Lombok` as plugin.
So, please make sure all are installed in your machine.

<a name="before-you-begin"></a>
### 🔔 Before you begin
If you are new in Git, GitHub and new in Spring Boot configuration structure, <br>
You should see basic detail instructions first in here [Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction)<br>
If you are not good enough in basic API knowledge with Java Spring Boot and other spring basic knowledge, you should learn below example projects first. <br>
Click below links.
* [Spring Boot Sample CRUD Application](https://github.com/yewin-mm/spring-boot-sample-crud) (for sample CRUD application)
* [Reading Values from Properties files](https://github.com/yewin-mm/reading-properties-file-values) (for reading values from properties files)
* [Rest API Sample Caller Service](https://github.com/yewin-mm/restapi-sample-caller-service) (for calling APIs)
* [Service A](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) (for Eureka Service A)
* [Service B](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b) (for Eureka Service B)


<a name="clone-project"></a>
### 🥡 Clone Project
* Clone the repo
   ```sh
   git clone https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2.git
   ```
<a name="prerequisites"></a>
### 🔑 Prerequisites
Prerequisites can be found here, [Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction). <br>
You need to clone [Eureka Server](https://github.com/yewin-mm/spring-cloud-eureka-server). <br>
You need to clone [Microservice a](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) and [Microservice b](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b).


<a name="instruction"></a>
### 📝 Instruction
* If you don't look [Service A](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) yet, you should read that [Service A Instruction](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a#instruction) first.
* If you don't look [Service B](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b) yet, you should read that [Service B Instruction](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b#instruction) first.

<a name="check-eureka-server"></a>
#### Checking Eureka Server Portal
* Clone and Run [Eureka Server](https://github.com/yewin-mm/spring-cloud-eureka-server) in your IDE.
* If you don't run Eureka Server first, you will get error when you run this service, because this service will connect to that server.
* After run the server, Call Eureka Server Portal `http://localhost:8761/` in browser. [Default Eureka Server Url](http://localhost:8761/).
* You can see `No Instances available` under `Application` tab under `Instances currently registered with Eureka` in `Eureka Server Portal`.
* Because you up your server and no instance (application) are connected (register) to this server right now.
* Clone this Service B Instance 2 project and Run in your IDE.
* Refresh in your Eureka Server Portal.
  * There, you can see `SERVICE-B` under `Application` tab and `Up(1)` under Status.
  * There, `Up(1)` mean there has only one instance (one application) which registered in Eureka with name `SERVICE-B`. 
  * You can see follow by url after Up like `192.168.1.2:Service-B:9091`, and you can click that endpoint to see this `SERVICE-B Instance` application `info` which provided by Spring boot `Actuator`.
  * You can also see other Services if you `run` [Microservice a](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) and [Microservice b](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b) at the same time.
  * After run those above Services, `refresh` eureka portal. You can see `Service A` is new line and `Up(1)` as status. `1` mean, there is not running other A Service Instances.
  * But you can't see as two line for `Service-B` and `Service-B Instance` because it used same Service Name when registered in Eureka server. It's mean you duplicated Service B for handling thousands requests.
  * So, for this `Service-B`, you can see `Up(2)` because you run two applications with same Service Name.
  * If you down(stop) one service of `Service-B`, you can see `Down(1)` and `Up(1)` under `Status` tab.
  * So, you can test those three services by stopping, starting applications. And See the `Eureka Server Portal` by refreshing.
* You can also see `General Info` and `Instance Info` in Eureka Server Portal.

<a name="testing"></a>
#### Testing

<a name="call-api-direct"></a>
##### Testing APIs by Direct
* Import `eureka-client.postman_collection.json` file under project directory (see that file in project directory) into your installed Postman application.
  * Click in your Postman (top left corner) import -> file -> upload files -> {choose that json file} and open/import.
  * After that, you can see the folder which you import from file in your Postman.
* Now, you can 'Test' normal APIs by calling from Postman.
  * Open your imported `eureka-client` folder in postman and inside this folder, you will see total of 3 folders,
  * Open `service-b instance 2` folder
    * Open `serviceBDemoAPI` api, click `send` button, you can see `Hello from Service B` Message and this message is from this `Service B Instance 2`.
    * If not already run, Run [Microservice a](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) and [Microservice b](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b) applications.
    * Below is `direct API call` to `Service A`.
    * Open `(direct call) call service-a api from service-b` api, click `send` button, you can see `Hello from Service A` message and this message is from `Service A`.
  * You can see code in this project for Direct API Calling and this is under `callServiceAFromServiceBDirectAPICall` method of `ServiceBTestService` class.  


<a name="call-api-eureka"></a>
##### Call APIs through Eureka
* I assumed that you already imported my postman collection as above [Call APIs by Direct](#call-api-direct) guide and already `Run` Service B and B Instance applications.
  * Open your imported `eureka-client` folder in postman and inside this folder, you will see total of 3 folders,
  * Open `service-b instance 2` folder
    * If not already run, Run [Microservice a](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-a) and [Microservice b](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b) applications.
    * Open `(through eureka) call service-a api from service-b` api, click `send` button, 
    * You can see `Hello from Service A` message and this message is from `Service A`.
    * You can check above message is from service A to make sure by checking in your IDE logs, check logs in `Service A` console logs in your IDE.
    * If there has another Instance of Service A (duplicate A application and register in Eureka with same Name), Service B can call well.
    * It's mean this `Service-B Instance` can call to both `Service A` and `A Instance` by using one URL as `Round Robin` rule of Load Balancer.
  
  * You can see code in this project for call API through Eureka and this is under `callServiceAFromServiceBThroughEureka` method of `ServiceBTestService` class.
  * Surprisingly that `Service-B Instance` need only `Service Name` to call other Services and no need to know for URL and also used `only one` url which provided from `Eureka Server` to call `Service A` and `Service A instance`.
  * So, `Eureka` can provide url by `Service Name` which service (application) registered in `Eureka Server`.
  * That's what we call Service Discovery, and it's mean one service can discover (can call) easily other services location (ip address) by just giving Service Name. But all services need to register in `Eureka Server`.

* Please note that there can have many services and each service has one or more instances.
  * Eg. `Service A` might have one or more instances.
  * `Service B` also might have another instance like this Service B Instance 2.
  * There might have other services like `Service C`, etc... as per your microservices structure.
  * Service mean your application (microservice), and you can give the name as your want.
  * In real world applications, we do separate many instances for one service because it can handle well `thousands requests`.

* That's what we call Service Discovery, 
  * It's mean one service can discover (can call) easily other services location (ip address) by `just giving only Service Name`.
  * But all services need to register in `Eureka Server`.
  * Service Discovery will reduce coupling because we can easily migrate(change) our application deployed server, and we `don't need to change` url to reach that server again in caller (producer) service.

* Also, You can call `(built-in api) info api (which provided from actuator)` and `(built-in api) health api (which provided from actuator)` APIs too which APIs are provided from Spring Boot Actuator and that's built-in APIs as I don't need to add manually for those APIs.

* After that you can check the code which you don't know. You can learn it, and you can apply in your job or study fields.

***Have Fun and Enjoy in Learning Code***


<a name="contact"></a>
## ✉️ Contact
Name - Ye Win <br> LinkedIn profile -  [Ye Win's LinkedIn](https://www.linkedin.com/in/ye-win-1a33a292/)  <br> Email Address - yewin.mmr@gmail.com

Project Link: [Spring Cloud Eureka Sample Microservice B Instance 2](https://github.com/yewin-mm/spring-cloud-eureka-sample-microservice-b-instance2)


<a name="contributing"></a>
## ⭐ Contributing
Contributions are what make the open source community such an amazing place to be learnt, inspire, and create. Any contributions you make are **greatly appreciated**.
<br>If you want to contribute....
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/yourname`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeatures'`)
4. Push to the Branch (`git push -u origin feature/yourname`)
5. Open a Pull Request

