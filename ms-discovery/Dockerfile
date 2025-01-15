# FROM: indica l'immagine presente sul docker hub dalla quale partire per creare l'immagine del mio container
FROM amazoncorretto:21-alpine-jdk
# Aggiungere il nostro jar all'immagine in fase di creazione del container
ADD target/ms-discovery.jar ms-discovery.jar
# ENTRYPOINT: è il comando che viene lanciato all'avvio del container
ENTRYPOINT ["java", "-jar", "ms-discovery.jar", "--spring.profiles.active=production"]