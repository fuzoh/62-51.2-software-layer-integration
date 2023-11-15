# Guideresto - data mapper - Bastien
> 62-51.2 Intégration de couches logicielles

**uses java version 21**

## Reflexion about the questions in the exercise 1 :

- **Comment gérer les connexions JDBC ?** : I made the choice to use one connexion
for all app lifecycle, by implementing a naive singleton. The singleton needs to be reimplemented more
robustly to supports multithreading, and handle database connexion crash.
- **Comment générer les identifiants techniques (PK) et faire en sorte qu’ils soient présents dans les
objets après leur création ?** : I use the returning keyword to get back the generated id, and put in the entity.
- **Comment gérer les relations bidirectionnelles (Restaurant -> Evaluations, Evaluation -> Restaurant)** :
This is a real problem, because if y reuse my mappers to load bidirectional relations, this will request
a new time data that are already loaded in memory, and this will also create infinite recursion loop between mappers.
In my app, I choose to not use the bidirectional relations, because we don't need it to retrieve the information, 
I can simply filter the restaurants by the fk of the city. But this will imply to modify CLI relations access.
- **Que faire dans le Data Mapper lors de la recherche du restaurant (rechercher uniquement le
restaurant ? également ses évaluations ? également ses notes ? où est-ce que l’on s’arrête ?)** :
In choose to load all data directly, because the app holds a small data set, and the cache will mitigate the
problem of query repetitions. In real situations, the ideal solution id to let developper chosse the 
loading policy directly in service, so he can chosse best case depending on needs.
- **Doit-il y avoir des relations entre les différents Data Mapper ?** : I think not if we follow SOLID
principles, but in this app, y choose to handle relations in data mapper to simplify code.
- **Combien d’interactions (requêtes JDBC) sont effectuées à la base de données avec votre code ?**
One per tables, regarding to the small data set and de cache integration, i choose to not use "inner joins".
This leads to a simpler data mapper architecture, but multiple request triggered when joins need to be loaded.

## exercise 2 : identityMap

Y choose to implement identity map with a sort of "singleton class", this class generates a new cache for each
data type you want a cache and stores it in a Map. When you request a cache, it checks the cache existence
and get you back the corresponding cache.

## exercise 3

- **Récupérer une connexion JDBC (ou de créer une nouvelle connexion)** this step is delegated to a singleton class
- **Faire appel à vos différents DataMappers/DAOs** Each service contain methods tha call the required data mapper
- **Commiter votre transaction** The service can call the database provider to commit the transaction.
- **Fermer (ou libérer) la connection JDBC (ce qui va la rendre au pool de connexions)** In my app, the
connection is free at the end of program, I choose to use only one connection for all app lifecycle.
To achieve that I created a singleton. For now, I don't manage potential connection lost.
