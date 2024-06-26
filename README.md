﻿## Evaluation Analyse et Maintenabilité du code

> Ce projet est une application Java, qui calcule et imprime des factures pour un service de location de voitures. L'application utilise Maven pour l'automatisation des builds et la gestion des dépendances.

- [Exercice 1 - Questions sur les acquis notions vues en cours](#Exercice-1-Questions-sur-les-acquis-notions-vues-en-cours)


- [Exercice 2 - Refactoring](#Exercice-2-Refactoring)
  - [Prérequis](#Prérequis)
  - [Installation](#Installation)
  - [Installation du git hook pre commit](#Installation-du-git-hook-pre-commit)
  - [Lancer le projet](#Lancer-le-projet)
  - [Exécuter les tests](#Exécuter-les-tests)

## Exercice 1 - Questions sur les acquis, notions vues en cours

### 1. Quelles sont les principales sources de complexité dans un système logiciel (sources d’un programme) ?


   - **Taille du système :** _Plus un système est grand, plus il est complexe à gérer en raison du nombre d'applications, de tables de données et de flux._


   - **Subdivision difficile :** _La division d'un système en sous-systèmes plus petits sans créer de nombreux liens entre eux est difficile, augmentant ainsi la complexité._


   - **Couplage indémêlable :** _Les parties du système sont fortement interconnectées, ce qui fait que les modifications dans une partie affectent les autres, rendant les évolutions coûteuses et complexes._


   - **Hétérogénéité et incohérence :** _Les différentes parties du système peuvent être hétérogènes et incohérentes (organisation, processus métiers, données, architectures applicatives, langages de développement, infrastructures), ce qui complique la standardisation et l'industrialisation des actions._


   - **Imprévisibilité :** _En raison de la complexité et du nombre d'états possibles, il est impossible de prévoir ou de tester le comportement du système dans toutes les situations possibles, augmentant les incidents et les difficultés d'intégration._


   - **Interactions complexes :** _Les composants du logiciel échangent et traitent constamment des données, créant des interactions complexes. La gestion et la maintenance deviennent difficiles avec une grande base de code et de nombreux utilisateurs._


   - **Évolution :** _Les mises à jour et modifications nécessaires au fil du temps introduisent des rétroactions et des comportements imprévus, augmentant la complexité._



### 2. Quel(s) avantage(s) procure le fait de programmer vers une interface et non vers une implémentation ? Vous pouvez illustrer votre réponse avec un code source minimal et/ou avec un diagramme.

Programmer vers une interface plutôt que vers une implémentation offre plusieurs avantages :

- **Flexibilité :** _Vous pouvez changer l'implémentation sans modifier le code qui utilise l'interface._


- **Modularité :** _Facilite la réutilisation du code et l'interchangeabilité des composants._


- **Testabilité :** _Permet de créer des mocks ou des stubs pour les tests unitaires._


- **Dépendance minimale :** _Réduit les dépendances entre les composants, facilitant la maintenance et l'évolutivité._


- **Séparation des préoccupations :** _Clarifie les responsabilités des composants et facilite la gestion des interactions entre eux._


- **Encapsulation :** _L'interface expose uniquement les méthodes publiques, cachant les détails de l'implémentation._


Exemple minimal en Java :

```java
interface Animal {
    void makeSound();
}

class Wolf implements Animal {
    public void makeSound() {
        System.out.println("Aouuuw");
    }
}

class Lion implements Animal {
    public void makeSound() {
        System.out.println("Roaaar");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Wolf();
        animal.makeSound();
        System.out.println("Output: Aouuuw\n" + animal.makeSound());

        animal = new Lion();
        animal.makeSound();
        System.out.println("Output: Roaaar\n" + animal.makeSound());
    }
}
```


### 3. Eric Steven Raymond, figure du mouvement open-source et hacker notoire, a récemment écrit sur le développement d’un système : “First make it run, next make it correct, and only after that worry about making it fast.” que l’on peut traduire par “D’abord, faites en sorte que ça fonctionne, ensuite assurez-vous que ce soit correct, et seulement après, préoccupez-vous de le rendre rapide.”. Comment comprenez-vous chaque étape de cette heuristique ? Comment comprenez-vous cette heuristique dans son ensemble ?

Le terme heuristique désigne une méthode de résolution de problème par des approches successives, en éliminant progressivement les alternatives pour se concentrer sur les solutions optimales. Cette approche ne passe pas par l'analyse détaillée du problème, mais par son adhérence à une classe de problèmes déjà identifiés.

Ainsi, nous pouvons comprendre l'heuristique d'Eric Steven Raymond de la manière suivante :

1. [ ] _"First make it run" (Faites en sorte que ça fonctionne) : Créer une version fonctionnelle de base du logiciel. L'objectif est d'avoir quelque chose qui peut être exécuté et testé, même si ce n'est pas parfait._


2. [ ] _"Next make it correct" (Assurez-vous que ce soit correct) : Une fois que le logiciel fonctionne, corriger les erreurs et s'assurer qu'il fonctionne correctement et selon les spécifications._


3. [ ] _"Only after that worry about making it fast" (Préoccupez-vous de le rendre rapide) : Enfin, une fois que le logiciel est fonctionnel et correct, on se concentre sur l'optimisation des performances._

Cette approche heuristique est pragmatique et itérative, permettant un développement par étapes. Chaque étape produit un logiciel qui peut être évalué et amélioré progressivement, assurant ainsi une qualité et des performances optimales avec le temps.

### 4. Nous avons vu en cours une technique pour lutter contre la déstructuration du code au cours du temps, le refactoring. Quelle méthode ou approche est recommandée pour mener à bien un travail de refactoring ?

Le refactoring vise à améliorer le code source, pour rendre le code plus efficace et plus facile à maintenir. Un code bien refactorisé permet d'ajouter de nouveaux éléments sans introduire d'erreurs et simplifie l'analyse des erreurs.

Avant de commencer le refactoring, il est crucial d'avoir une **suite de tests unitaires** complète et fiable, pour s'assurer que le comportement du code reste inchangé après les modifications.

**Le refactoring incrémental** est lui aussi recommandé, pour effectuer des changements petits et fréquents, plutôt que de grands remaniements. Cela permet d'identifier rapidement les erreurs et de réduire les risques.

**Techniques courantes** :
- extraction de méthode
- simplification des expressions conditionnelles
- réduction de la duplication de code.

Faire **relire le code** refactoré par un pair garantit également la qualité et la conformité aux standards de codage.

En appliquant déjà ces méthodes, le refactoring peut être effectué de manière contrôlée et efficace, améliorant la structure du code sans introduire de nouvelles erreurs.


# Exercice 2 - Refactoring
## Prérequis

Avant de commencer, assurez-vous de remplir les conditions suivantes :

- **Java Development Kit (JDK) 21** : Assurez-vous d'avoir le JDK installé. Vous pouvez le télécharger depuis le [site officiel d'Oracle](https://www.oracle.com/java/technologies/downloads/).


- **Maven** : Apache Maven doit être installé sur votre machine, Afin de pouvoir lancer les tests en ligne de commande ou pouvoir utiliser le git hook pré-commit. Vous pouvez le télécharger depuis le [site officiel de Maven](https://maven.apache.org/download.cgi).


- **MAVEN_HOME** : Assurez-vous que la variable d'environnement `MAVEN_HOME` est configurée correctement, sans quoi vous ne pourrez pas exécuter les commandes qui vont suivre. Vous pouvez suivre [ce tutoriel](https://mkyong.com/maven/how-to-install-maven-in-windows/) pour configurer Maven sur Windows.


## Installation

Suivez ces étapes pour installer le projet sur votre machine locale :

1. **Clonez le dépôt** :
   ```sh
   git clone https://github.com/LaetitiaDes/eval_analyse_et_maintenabilite_du_code.git
   ```
   
2. Pour la suite des étapes, assurez-vous d'être dans le répertoire du projet :
   ```sh
   cd eval_analyse_et_maintenabilite_du_code
   ```

2. **Installez les dépendances** :
   ```sh
   mvn clean install
   ```
Cette ligne de commande permettra de télécharger les dépendances nécessaires pour le projet.

## Installation du git hook pre commit

4. Exécutez le script d'installation pour configurer le hook pre-commit :
   ```sh
   ./install_hooks.sh
   ```
Avec ce script vous pourrez voir au chemin suivant : .git/hooks/pre-commit, un fichier qui permettra de lancer les tests avant chaque commit.

## Lancer le projet

5. Exécutez l'application :
   ```sh
   mvn exec:java
   ```
Les paramètres de l'application sont configurer pour pointer vers la class Main.java. Et vous permettra d'afficher les factures pour les clients.

## Exécuter les tests

6. Exécutez les tests :
   ```sh
   mvn test
   ```
Vous pourrez voir les résultats des tests unitaires dans votre console.
