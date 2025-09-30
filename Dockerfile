# Utiliser une image Maven avec JDK
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier uniquement le pom pour télécharger les dépendances
COPY pom.xml ./

# Créer un répertoire src vide pour éviter les erreurs de build
RUN mkdir -p src && echo "" > src/placeholder.txt

# Télécharger les dépendances Maven (mode offline)
RUN mvn dependency:go-offline -B

# Copier tout le reste du projet
COPY . .

# Construire le projet et générer le JAR
RUN mvn clean package -DskipTests

# Étape finale pour l'image runtime
FROM eclipse-temurin:21-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/IntegrationAPI-0.0.1-SNAPSHOT.jar ./IntegrationAPI.jar

# Exposer le port de l'application
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "IntegrationAPI.jar"]
