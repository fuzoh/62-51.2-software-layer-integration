# Guideresto - data mapper - Bastien
> 62-51.2 Intégration de couches logicielles

**uses java version 21**

## Reflexion about the questions in the exercise 1 :

- **Comment gérer les connexions JDBC ?** : I made the choice to use one connexion
for all app lifecycle, by implementing a naive singleton. The singleton needs to be reimplemented more
robustly to supports multithreading, and handle database connexion crash.
- 