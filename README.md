# Microservices avec RestTemplate

Ce projet implémente une architecture microservices complète utilisant Spring Boot, Eureka, Spring Cloud Gateway et RestTemplate.

## Architecture

Le système est composé de 4 services principaux :

1. **Eureka Server** - Service de découverte
2. **Client Service** - Gestion des clients
3. **Car Service** - Gestion des voitures
4. **Gateway** - Passerelle API centralisée

## Services

### 1. Eureka Server (Port 8761)

Service de découverte qui permet aux microservices de s'enregistrer et de se découvrir automatiquement.

(img/Eureka%20server.png)

**Accès :** http://localhost:8761

### 2. Client Service (Port 8081)

Microservice gérant les opérations CRUD sur les clients.

(img/client%20service.png)

**Endpoints :**
- GET http://localhost:8081/api/client - Liste tous les clients
- GET http://localhost:8081/api/client/{id} - Récupère un client par ID
- POST http://localhost:8081/api/client - Crée un nouveau client

**Via Gateway :**
- http://localhost:8888/service-client/api/client

### 3. Car Service (Port 8082)

Microservice gérant les voitures et récupérant les informations des clients via RestTemplate.

(img/car%20service.png)

**Endpoints :**
- GET http://localhost:8082/api/car - Liste toutes les voitures avec détails clients
- GET http://localhost:8082/api/car/{id} - Récupère une voiture par ID avec détails client
- POST http://localhost:8082/api/car - Crée une nouvelle voiture

**Via Gateway :**
- http://localhost:8888/service-car/api/car

### 4. Gateway (Port 8888)

Spring Cloud Gateway qui centralise l'accès aux microservices et route les requêtes.

(img/gateway.png)

**Endpoints :**
- http://localhost:8888/actuator/health - Health check
- http://localhost:8888/actuator/gateway/routes - Liste des routes configurées

**Format des URLs :**
```
http://localhost:8888/{nom-du-service}/{chemin-du-service}
```

Exemples :
- http://localhost:8888/service-client/api/client
- http://localhost:8888/service-car/api/car

## Diagramme d'architecture

(img/3.png)

## Démarrage

### Ordre de démarrage

1. **Eureka Server** (port 8761)
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

2. **Client Service** (port 8081)
   ```bash
   cd client-service
   mvn spring-boot:run
   ```

3. **Gateway** (port 8888)
   ```bash
   cd gateway
   mvn spring-boot:run
   ```

4. **Car Service** (port 8082)
   ```bash
   cd car-service
   mvn spring-boot:run
   ```

### Vérification

Après le démarrage de tous les services, vérifiez dans Eureka :
- http://localhost:8761
- Vous devriez voir : `service-client`, `service-car`, et `Gateway`

## Technologies utilisées

- **Spring Boot 2.7.18**
- **Spring Cloud 2021.0.4**
- **Netflix Eureka** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **RestTemplate** - Communication inter-services
- **H2 Database** - Base de données en mémoire
- **JPA/Hibernate** - ORM
- **Lombok** - Réduction du code boilerplate

## Structure du projet

```
Micro-services avec RestTemplate/
├── eureka-server/          # Service de découverte
├── client-service/         # Service de gestion des clients
├── car-service/            # Service de gestion des voitures
├── gateway/                # Passerelle API
└── img/                    # Images de documentation
```

## Configuration

### Base de données

Les services utilisent H2 (base de données en mémoire) pour faciliter les tests :
- **Client Service** : `jdbc:h2:mem:clientservicedb`
- **Car Service** : `jdbc:h2:mem:carservicedb`

Console H2 accessible via :
- Client Service : http://localhost:8081/h2-console
- Car Service : http://localhost:8082/h2-console

### Eureka

Tous les services s'enregistrent automatiquement dans Eureka à l'adresse :
- http://localhost:8761/eureka/

## Tests

### Insérer des données de test

Utilisez le script PowerShell `inserer-donnees.ps1` ou les commandes dans `COMMANDES_INSERTION.txt`.

### Exemples de requêtes

**Créer un client :**
```bash
POST http://localhost:8081/api/client
Content-Type: application/json

{
  "nom": "Amine SAFI",
  "age": 23
}
```

**Créer une voiture :**
```bash
POST http://localhost:8082/api/car
Content-Type: application/json

{
  "brand": "Ford",
  "model": "2022",
  "matricule": "12 A 2345",
  "client_id": 1
}
```

## Documentation

- [Guide de démarrage](GUIDE_DEMARRAGE.md)
- [Guide de test des endpoints](TEST_ENDPOINTS.md)
- [Diagnostic Gateway](DIAGNOSTIC_GATEWAY.md)
- [Insertion de données](INSERTION_DONNEES.md)

## Auteur

Projet réalisé dans le cadre d'un TP sur les microservices avec Spring Boot.

