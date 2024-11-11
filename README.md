# microservice-kubernetes  

- This is a spring boot project for microservice architecture with kubernetes.
- available services are
  - api gateway using Spring Cloud Gateway MVC
  - product service
  - order service
  - inventory service
  - notification service
  - frontend using Angular 18
 
## Tech Stack  

- Java 21
- Spring Boot
- Angular 18
- Mongo DB
- MySQL
- Kafka
- Keycloak
- Test Containers with Wiremock
- Grafana Stack (Prometheus, Grafana, Loki and Tempo)
- API Gateway using Spring Cloud Gateway MVC
- Cloud Native Buildpacks
- Docker
- Kind for local kubernetes cluster
- Kubernetes

#### Start Kind Cluster

Run the k8s/kind/create-kind-cluster.sh script to create the kind Kubernetes cluster
> ./k8s/kind/create-kind-cluster.sh

#### Deploy the infrastructure
Run the k8s/manisfests/infrastructure.yaml file to deploy the infrastructure
> kubectl apply -f k8s/manifests/infrastructure.yml

#### Deploy the services
Run the k8s/manifests/applications.yaml file to deploy the services
> kubectl apply -f k8s/manifests/applications.yml

#### Access the API Gateway
To access the API Gateway, you need to port-forward the gateway service to your local machine
> kubectl port-forward svc/gateway-service 9000:9000

#### Access the Keycloak Admin Console
To access the Keycloak admin console, you need to port-forward the keycloak service to your local machine
> kubectl port-forward svc/keycloak 8080:8080

#### Access the Grafana Dashboards
To access the Grafana dashboards, you need to port-forward the grafana service to your local machine
> kubectl port-forward svc/grafana 3000:3000
