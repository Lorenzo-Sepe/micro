- Apro il prompt di dos
- mi posiziono nella cartella del progetto (dove c'è il pom.xml)
- creo il file jar lanciando il comando:  mvn clean package (se Maven è installato) oppure .\mwnv clean install (verrà creato il jar nella cartella 'target')
- Predispongo il file Dockerfile all'interno del progetto e lancio il comando:
        "docker build -t ms-discovery:0.0.1-SNAPSHOT ."
  dove "ms-discovery" è il nome che voglio dare alla mia immagine e "0.0.1-SNAPSHOT" è la versione della mia immagine;
  in questo modo creo l'immagine del mio container.
- lancio il comando :
        docker run -d --name=ms-discovery -p 8761:8761 ms-discovery:0.0.1-SNAPSHOT
  "ms-discovery" è il nome del mio container che sarà esposto sulla porta 8761 partendo dall'immagine "ms-discovery" con tag "0.0.1-SNAPSHOT"



