# TP_0

## Exercice 1

### Reponse 1

Cette première version du programme ne contient aucune synchronisation pour gérer l'accès concurrent à la variable partagée. Par conséquent, lorsque les threads lisent et écrivent dans la variable partagée, on constate qu'il peut y avoir des problèmes de cohérence des données, c'est-à-dire que les threads peuvent lire des valeurs incorrectes.

En exécutant ce programme, nous observons des sorties de console incorrectes, où les threads de lecture affichent des valeurs incorrectes ou dupliquées. Cela est dû au fait que les threads d'incrémentation modifient la variable partagée sans prendre en compte les autres threads qui lisent la même variable. Par conséquent, les valeurs lues par les threads de lecture peuvent ne pas être à jour. Cette situation est connue sous le nom de condition de course (race condition).

### Reponse 2

Dans cette version modifiée, nous avons encapsulé les accès à la variable partagée dans des blocs synchronized pour synchroniser l'accès concurrent à la variable partagée.

En mesurant le temps d'exécution de ce programme, nous avvons utiliser la méthode System.currentTimeMillis() pour mesurer le temps de début et de fin du programme, et calculer la différence pour obtenir le temps d'exécution.