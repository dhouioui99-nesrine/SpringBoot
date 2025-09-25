# Utiliser une image JDK
FROM maven:3.9.9-eclipse-temurin-21-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le pom et télécharger les dépendances (cache Maven)
COPY pom.xml .
RUN mkdir -p src && echo "" > src/placeholder.txt
RUN mvn dependency:go-offline -B

# Copier le reste du projet
COPY . .

# Construire le projet
RUN mvn clean package -DskipTests

# Exposer le port de l'application
EXPOSE 8080

# Lancer l'application
CMD ["java", "-jar", "target/IntegrationAPI-0.0.1-SNAPSHOT.jar"]
