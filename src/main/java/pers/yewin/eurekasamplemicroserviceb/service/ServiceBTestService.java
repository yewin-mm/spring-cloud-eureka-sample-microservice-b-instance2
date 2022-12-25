package pers.yewin.eurekasamplemicroserviceb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Ye Win
 * @created: 25/09/2022
 * @project: eureka-sample-microservice-b
 * @package: pers.yewin.eurekasamplemicroserviceb.service
 */

@Service
@Slf4j // for logging
public class ServiceBTestService {

    @Autowired
    private RestTemplate restTemplate;

    // we used Ribbon Load Balancer as that is client side load balancer, you can find more about load balancer in google.
    @Autowired
    private LoadBalancerClient loadBalancerClient; // you can use Feign instead of using Ribbon LoadBalancerClient directly.

    private static final String SERVICE_A_DIRECT_URL = "http://localhost:8080";
    private static final String SERVICE_A_EUREKA_SERVICE_NAME = "Service-A";

    public String serviceBProcess(){
        // here, I return hard code and if you want, you can retrieve from db.
        return "Hello from Service B";
    }

    
    /**
     * This method is for direct api calling to service A
     */
    public String callServiceAFromServiceBDirectAPICall(){
        // it's better accessing url from application.properties file or application.yml file rather than hardcoded and static value.
        // added application context path which declare in yml file and endpoint after url which we want to call.
        String url = getServiceADirectURL()+"/serviceA/serviceADemoAPI";
        log.info("callServiceAFromServiceBDirectAPICall method, url: {}", url);
        String response = restTemplate.getForObject(url, String.class);
        return response; // return service a response
    }


    /**
     * This below method will call all instances of service-a,
     * Whether service-a has many instances or not, whatever, it can call.
     * Because we used Load Balancer to call api
     * And that Load Balancer will find as per Service Name which our application registered in Eureka Server as per application name in yml file.
     * Load Balancer will call as Round Robin rule (you can find more about load balancer in google)
     *
     * Normally, We need service location(address) like ip:port mapping to call other service (service = microservice application).
     * So, Service Registry (Eureka) return the location of registered service and that Eureka server knew the location of service which registered in that Eureka.
     * So, here, we don't need ip:port to call other service and just need to call by service name which name provided from Eureka
     * and that Eureka server already mapped with Service Name with that service location (address).
     *
     * It's the main reason to use Service Discovery Approach
     * because <let's say Service A (producer) call Service B (consumer)>
     *     we can easily migrate our service B deployed server (if changed deployed server, the ip will also change) without changing the url in Service A when A call to B.
     *     Because normally if we changed IP address in Service B, Service A also needed to change that ip url in RestTemplate when A call to B.
     *     So, using with Service Registry, we don't need to changed from Producer side even Consumer side was changed.
     *     Also, Eureka Server provided for load balancing,
     *     eg. Service B might duplicate into many services for handling thousands requests, and we don't need to add url for each Instance Services Location.
     *     The load balancer will route automatically when A call to B and B instances.
     */
    public String callServiceAFromServiceBThroughEureka(){
        // added application context path which declare in yml file and endpoint after url which we want to call.
        String url = getServiceAEurekaURL()+"/serviceA/serviceADemoAPI";
        log.info("callServiceAFromServiceBThroughEureka method, url: {}", url);
        String response = restTemplate.getForObject(url, String.class);
        return response; // return service a response
    }

    private String getServiceADirectURL(){
        // it's better accessing value from application.properties file or application.yml file rather than static value.
        return SERVICE_A_DIRECT_URL;
    }

    /**
     * You can use Feign instead of using loadbalancerClient (Ribbon) directly. Feign include Ribbon loadbalancer too.
     */
    private String getServiceAEurekaURL(){
        // get URI by Service Name which is registered in Eureka
        // we can also get url by using netflix EurekaClient and InstanceInfo rather than ServiceInstance
        // we used Ribbon Load Balancer as that is client side load balancer, you can find more about load balancer in google.
        // that below load balancer can call all instances of service-a as Round Robin policy
        ServiceInstance serviceInstance = loadBalancerClient.choose(SERVICE_A_EUREKA_SERVICE_NAME);
        return serviceInstance.getUri().toString();
    }

}
