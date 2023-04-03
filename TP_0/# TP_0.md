# TP_0

## Exercice 1

### Reponse 1

Cette première version du programme ne contient aucune synchronisation pour gérer l'accès concurrent à la variable partagée. Par conséquent, lorsque les threads lisent et écrivent dans la variable partagée, on constate qu'il peut y avoir des problèmes de cohérence des données, c'est-à-dire que les threads peuvent lire des valeurs incorrectes.

En exécutant ce programme, nous observons des sorties de console incorrectes, où les threads de lecture affichent des valeurs incorrectes ou dupliquées. Cela est dû au fait que les threads d'incrémentation modifient la variable partagée sans prendre en compte les autres threads qui lisent la même variable. Par conséquent, les valeurs lues par les threads de lecture peuvent ne pas être à jour. Cette situation est connue sous le nom de condition de course (race condition).

### Reponse 2

on crée un verrou réentrant en utilisant la classe `ReentrantLock`. La méthode `lock()` est appelée pour acquérir le verrou avant l'exécution de la section critique, tandis que la méthode `unlock()` est appelée pour libérer le verrou après l'exécution de la section critique. La clause try-finally garantit que le verrou est toujours libéré, même en cas d'exception dans la section critique.

nous avons utilisé la méthode `System.currentTimeMillis()` pour mesurer le temps de début et de fin du programme, et calculer la différence pour obtenir le temps d'exécution.

### Reponse 3

On constate un temps d'exécution plus bas que le temps d'exécution du verrou réentrant. Cela est dû au fait que le verrou lecture/écritture permet à plusieurs threads de lire en même temps sans attendre qu'un thread d'écriture se termine.Cependant, si le nombre de threads en écriture est important, le verrou réentrant peut être plus efficace en garantissant que seul un thread à la fois a accès à la section critique.

En fin de compte, il est important de mesurer et de profiler votre programme pour déterminer quelle option de verrouillage fonctionne le mieux pour votre cas d'utilisation spécifique.