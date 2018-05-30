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

Dans un nouveau package, implémentez un système à 3 acteurs. L'acteur parent, lorsqu'il reçoit un message *msg*
correspondant à une chaîne de caractère, délègue en fonction de l'un de ses 2 comportements "hello" ou "goodbye"
l'affichage d'un message "Hello" ou "Good bye" à un de ses enfants respectivement spécialisé pour afficher
"Hello *msg* !" ou "Good bye *msg* !". Après chaque traitement de message, l'acteur parent change de comportement.

### Partie 2 - Crible d'Ératosthène

Rappel de l’algorithme vu en cours : les nombres premiers sont représentés par des acteurs qui forment une
chaîne et se passent les uns les autres les nombres en filtrant (« cribler ») ceux qui sont des multiples du nombre
qu’ils représentent. Chaque acteur a deux comportements possibles :
- soit l’acteur est en bout de chaîne et si le nombre qu’on lui envoie n’est pas filtré, alors il crée un acteur
représentant ce nombre pour étendre la chaîne avec celui-ci et change de comportement pour le second
afin de devenir un intermédiaire dans la chaîne ;
- soit l’acteur est un intermédiaire dans la chaîne et si le nombre qu’on lui envoi n’est pas filtré, alors il le
passe à l’acteur suivant dans la chaîne (dont il a mémorisé l’adresse car l’ayant créé).

![Crible d'Ératosthène](crible.png)


1. Dans un nouveau package, implémentez cet algorithme de telle sorte que l’on puisse passer les nombres de 3 à N
(argument du programme) à un acteur représentant le nombre 2 que l’on aura créé auparavant.

2. À chaque fois qu’un nombre premier sera trouvé, il faudra l’afficher.

3. Modifiez le programme pour terminer en chaîne l’exécution des acteurs une fois que tout les nombres ont
été envoyés.

### Partie 3 - Cryptage et contrôle d'erreur

En s’appuyant sur les méthodes disponibles dans la classe StringUtils fournie dans le package
"m2dl.pcr.akka.stringservices", définir et implémenter dans ce même package une application dans laquelle cohabitent
un service de cryptage CryptageProvider et un service d’ajout de contrôle d’erreur ErreurControleProvider pour des
chaînes de caractères.

Chaque service sera représenté par un acteur, leurs caractéristiques sont les suivantes :
- CryptageProvider reçoit des messages contenant une chaîne de caractères et l’adresse d’un acteur récepteur (Recepteur). Il
encrypte la chaîne de caractère et l’envoi à l’acteur Recepteur.
- ErreurControleProvider reçoit des messages contenant une chaîne de caractères et l’adresse d’un acteur récepteur (Recepteur).
Il ajoute un contrôle d’erreur à la chaîne de caractère et l’envoi à l’acteur Recepteur.

Ces services seront utilisés séparément (pour les tester individuellement) ainsi que composés. L’acteur Recepteur sera
susceptible de recevoir ces différents types de résultats (encrypté, avec contrôle d’erreur et composition).

Nous nous restreindrons aux 3 cas d’utilisation suivants :

- -> CryptageProvider -> Recepteur
- -> ErreurControleProvider -> Recepteur
- -> CryptageProvider -> ErreurControleProvider -> Recepteur

L’ActorSystem créera les différents services et le récepteur.

Le troisième cas d'utilisation correspond à la mise en œuvre de la composition séquentielle des services. 
Il est attendu une implantation conforme à la solution étudiée en cours.

### Partie 4 - Cryptage et contrôle d'erreur en mode distribué

L'objectif de cette partie est d'illustrer les capacités de Akka pour la gestion d'acteurs distribués (*remote capabilities*).
Deux liens utils pour mener à bien votre travail :
- [Documentation Akka sur les aspects *remoting*](http://doc.akka.io/docs/akka/2.4.4/java/remoting.html)
- [L'exemple fourni par le projet Akka sur Github](https://github.com/akka/akka-samples/tree/2.5/akka-sample-remote-java)

Reprendre le 3ème cas d'utilisation de la partie 3 (composition) de telle sorte que votre application déployée dans une VM
dédiée exploite les acteurs CryptageProvider et ErreurControleProvider déployés chacun sur une VM dédiée.

### Partie 5 - Conception et programmation d'un agent mobile basé sur le modèle d'acteur.

Dans un nouveau package, définir et implémenter une application dans laquelle un acteur "mobile" s'exécute séquentiellement sur plusieurs "places".
Ici, les places sont des objets de type ActorSystem.
Le comportement de base de cet acteur est d'afficher la place sur laquelle il se trouve en réponse au message "Where are you?".
L'acteur réagit aussi à un autre type de message de type ActorSystem. Alors, il "se déplace" dans cette nouvelle place passée par message, conformément au protocole vu en cours.

Pour déployer et tester l'application, on ouvrira trois places de nom "Marseille", "Lyon" et "Paris". 
L'agent mobile sera initialement créé "à Marseille", puis on lui demandera de se déplacer succéssivement "à Lyon" puis "à Paris". Après la création et après chaque déplacement, on lui demandera d'afficher la place sur laquelle il se trouve. Tous ces messages seront envoyés à la référence initiale de l'acteur mobile.

Dans une étape suivante, on pourra répartir les places sur différentes machines virtuelles pour exécuter réellement dans un contexte réparti.

### Partie 6 - LightSlack (travail en binôme)

L'objectif de cette activité est le développement d'un serveur et d'un client de discussion (très) rudimentaire à l'aide d'Akka.

1. Implantez le serveur de discussions.
2. Implantez un client de votre serveur de discussions en utilisant les librairies d'interface de votre choix (JavaFX, Swing, ligne de commande).
 

