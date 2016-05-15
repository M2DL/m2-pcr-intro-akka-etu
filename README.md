## Master 2 DL - PCR - Introduction à Akka

### Préliminaire

1. Mettez dans vos favoris le lien vers la [documentation de Akka](http://doc.akka.io/docs/akka/2.4.4/java.html).
2. Récupérez le projet "intro-akka" et importez le comme projet Maven dans IntelliJ.
3. Étudiez le fichier "pom.xml" et explorez la structure du projet.

### Partie 1 - Hello World revisité avec les acteurs

#### Exercice 1.1

1. Étudiez le contenu du package "m2dl.pcr.akka.helloworld1".
2. Éxécutez la classe "System" de ce package.
3. Étudiez le contenu du package "m2dl.pcr.akka.helloworld2".
4. Éxécutez la classe "System" de ce package. Comment expliquer le comportement du système observé ?
5. Étudiez le contenu du package "m2dl.pcr.akka.helloworld3".
6. Éxécutez la classe "System" de ce package. Expliquez le comportement du système observé :
    - que représentent les objets "hello" et "goodbye" ?
    - que permet de faire l'exécution de "getContext().unbecome()" ?
    - que signifie l'utilisation du deuxième paramètre de l'appel à "become" dans la définition de l'objet "hello" ?

#### Exercice 1.2

Dans un nouveau package, implémentez un système à 3 acteurs. L'acteur parent, lorsqu'il reçoit un message *msg* correspondant à une
chaîne de caractère, délègue en fonction de l'un de ses 2 comportements "hello" ou "goodbye" l'affichage d'un message "Hello" ou "Good bye" à un de ses
enfants respectivement spécialisé pour afficher "Hello *msg* !" ou "Good bye *msg* !"