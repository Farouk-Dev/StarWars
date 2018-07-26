1 -Librairies  Utilisées :
- Retrofit  :
Retrofit simple à utiliser. Il vous permet essentiellement de traiter les appels d'API comme de simples appels de méthode Java. Vous ne définissez donc que les URL à atteindre et les types de paramètres de requête / réponse en tant que classes Java. L'ensemble de l'appel réseau + analyse syntaxique JSON / XML est entièrement géré par celui-ci (avec l'aide de Gson pour l'analyse JSON), ainsi que la prise en charge de formats arbitraires avec sérialisation / désérialisation connectable. La documentation est géniale et la communauté est immense. 

- Fresco  :
Pour les images Fresco peut le charger à partir du réseau, du stockage local ou des ressources locales. Pour enregistrer les données et le processeur, il dispose de trois niveaux de cache; un en stockage interne et deux en mémoire.

2-Design pattern :
J'ai utilisé  une architecture  MVP afin de faciliter l'impléméntation des test unitaires  ,le logique complexe est situé au niveau du Presenter.
