# Rapport TP IA Heartstone Deck Pattern Mining

## Choix d'implémentation

### Représentation des données

Pour représenter les différentes données sur lesquels nous devont réaliser le pattern mining nous avons du faire des choix : 

* Pour représenter la bdd, on a utiliser une `map<Integer,String>`, ou l'entier est l'id et le String représente la carte.

* Pour représenter les decks une fois transformés à l'aide de la bdd nous avons utiliser des `TreeSet` qui sont automatiquement triés.

* On utilise un `FileWriter` pour recréer un fichier texte a partir des données mises en forme pour l'utilisation de l'algorithme LCM de la bibliothèque SPMF.

* Nous avons créé un fonction `input` qui s'occupe de créer la base de donnée et de commencer un premier tri des cartes par deck. Nous avons donc créé 2 fonctions d'output. La première `output` créé un gros fichier sans carte de pouvoir et sans distinction. La deuxième `OneFileOneClass` va créé un fichier par des decks par classe, sans carte de pouvoir.

## Questions 
 * Que représente un itemset fréquent fermé ici? pourquoi est-ce potentiellement intéressant?
     * Un itemset fréquent fermé représente ici une combinaison de carte ou un singulet de carte fréquemment utilisé, ce qui présente un intérêt dans la recherche de cartes utilse à la création d'un deck.
 * Y a-t-il des itemsets fréquents fermés «triviaux» qui ne nous apprennent pas grand-chose?
     * Les pouvoirs de héros sont des singletons inhérants à chaque classe et ne nous apprenne rien. En revanche à haut % de LCM et sans prendre en compte les classes, les singletons sont les cartes les plus utilisés par tous les joueurs.
 
## Analyse des résultats

* Nous avons choisi un Minsup a 40% pour que les cartes soit présentes dans au moins 40% des decks étudiés, cela veut dire que les cartes sont souvent utilisées dans la classe choisie et sont donc intéressante à choisir pour former un deck.

* Hors-classe : 
    * Le premier duo de cartes qui est apparu après être passé dans l'algorithme LCM (8%) est  [[Piloted Shredder]](https://hearthstone.gamepedia.com/Piloted_Shredder) et [[Haunted Creeper]](https://hearthstone.gamepedia.com/Haunted_Creeper). Au vu de leur pouvoir, on peut imaginer que Piloted Shredder permet d'invoquer Haunted creeper qui lui-même invoque 2 nouvelles cartes, c'est une technique rapide qui permet d'invoquer en un tour seulement 4 cartes créatures, c'est donc un combo offensif et défensif redoutable.
    
* Deck Guerrier:
     [[Armorsmith]](https://hearthstone.gamepedia.com/Armorsmith)[[Death's Bite]](https://hearthstone.gamepedia.com/Death%27s_Bite)[[WhirlWind]](https://hearthstone.gamepedia.com/Whirlwind)[[Acolyte of Pain]](https://hearthstone.gamepedia.com/Acolyte_of_Pain)
    * Armorsmith permet de gagner 1 armure a chaque dégats sur un minion allié or Death's bite ou whirlwind fait des dégâts à tous les minions du board, donc permet un combo pour obtenir plus d'armure, de plus Acolyte of pain permet de piocher une carte pour chaque dégât subi sur cette carte.


* Rogue : 
    [[Azure drake]](https://hearthstone.gamepedia.com/Azure_Drake)[[Fan of Knives]](https://hearthstone.gamepedia.com/Fan_of_Knives)[[Eviscerate]](https://hearthstone.gamepedia.com/Eviscerate)....
    * Azure drake augmente les dégâts de sort de dégât or fan of knives et eviscerate font tout les 2 des dégâts directs et dans le cas de eviscerate sont même doublés en cas de combo.Le rogue possède un combo de dégât direct pouvant s'améliorer au fil des cartes piochées.


* Mage : 
[[Mana Wyrm]](https://hearthstone.gamepedia.com/Mana_Wyrm)
    * Mana wyrm gagne en attaque a chaque spell, sachant que le mage utilise beaucoup de sort.On peut imaginer que la carte monte rapidement en attaque alors qu'elle ne coûte que 2 pts de mana.

* Prêtre : 
[[Twillight Welp]](https://hearthstone.gamepedia.com/Twilight_Whelp)[[Azure Drake]](https://hearthstone.gamepedia.com/Azure_Drake)[[Wyrmrest Agent]](https://hearthstone.gamepedia.com/Wyrmrest_Agent)[[Twilight Guardian]](https://hearthstone.gamepedia.com/Twilight_Guardian)[[Blackwing Corruptor]](https://hearthstone.gamepedia.com/Blackwing_Corruptor)....
    * La pluparts des cartes se heal entre elle et ne coûte rien ou permette de pioche pour chaque heal comme (Northshire Cleric). Les cartes du deck sont majoritairement des dragons qui ont une très bonne synergie les uns avec les autres et se boost ou permette de se healer en fonction du nombre de dragon en main.

* Démoniste : 
    [[Mortal Coil]](https://hearthstone.gamepedia.com/Mortal_Coil)[[Imp Gang Boss]](https://hearthstone.gamepedia.com/Imp_Gang_Boss)[[Defender of Argus]](https://hearthstone.gamepedia.com/Defender_of_Argus)[[Twillight Drake]](https://hearthstone.gamepedia.com/Twilight_Drake)[[Dark Bomb]](https://hearthstone.gamepedia.com/Dark_Bomb)....
    * Ne possède pas de combo aussi présent que dans les autres classes.Seules les cartes puissantes de la classe sont donc présentes

* Paladin : 
    [[Muster for Battle]](https://hearthstone.gamepedia.com/Muster_for_Battle)[[Knife Juggler]](https://hearthstone.gamepedia.com/Knife_Juggler)[[Piloted Shredder]](https://hearthstone.gamepedia.com/Piloted_Shredder)[[True Silver Champion]](https://hearthstone.gamepedia.com/True_Silver_Champion)....
    *    Ne possède pas de combo aussi présent que dans les autres classes.Seules les cartes puissantes de la classe sont donc présentes

* Druide : 
    [[Darnassus Aspirant]](https://hearthstone.gamepedia.com/Darnassus_Aspirant)[[Wild Growth]](https://hearthstone.gamepedia.com/Wild_Growth)[[Innervate]](https://hearthstone.gamepedia.com/Innervate)[[Swipe]](https://hearthstone.gamepedia.com/Swipe)[[Keeper of the Grove]](https://hearthstone.gamepedia.com/Keeper_of_the_Grove)....
    *    La plupart de ces cartes permettent de gagner un point de mana, le combo possible est d’accumuler les points de mana gratuitement pour lancer une grosse carte au début de la partie où les points de mana sont faibles.



* Chasseur : 
    [[Haunted Creeper]](https://hearthstone.gamepedia.com/Haunted_Creeper)[[Mad Scientist]](https://hearthstone.gamepedia.com/Mad_Scientist)[[Animal Companion]](https://hearthstone.gamepedia.com/Animal_Companion)[[Iron Beak Owl]](https://hearthstone.gamepedia.com/Iron_Beak_Owl)[[Eagle Horn Bow]](https://hearthstone.gamepedia.com/Eagle_Horn_Bow)....
    * Il ne possède pas de combo particulier, seulement quelques cartes puissantes à utiliser avec le chasseur.

* Chaman : 
     [[Lightning Storm]](https://hearthstone.gamepedia.com/Lightning_Storm)[[Flametongue Totem]](https://hearthstone.gamepedia.com/Flametongue_Totem)[[Hex]](https://hearthstone.gamepedia.com/Hew)[[Haunted Creeper]](https://hearthstone.gamepedia.com/Haunted_Creeper)[[Fire Elemental]](https://hearthstone.gamepedia.com/Fire_Elemental)....
    *   Le Chaman ne possède pas de combos particulier à 40% de LCM, on peut observer qu’il y a quelques cartes assez intéressantes.

### Conclusion

Nous avons pu ressortir pour presque chaques classes la métagame qui se jouait au moment où les données ont été enregistrer. Toutesfois ces données n'ont pu être pondérée par la victoire ou la défaite du joueur du fait de l'indisponibilité de cette information dans les données de départ, on ne peut donc pas réellement quantifier l'efficacité de ces combinaisons les unes par rapport aux autres.

> Métagame : l'ensemble des stratégies et des méthodes qui ne sont pas explicitement prescrites par quelque règle que ce soit, mais qui résultent de la seule expérience des joueurs qui émerge en dépit de ce que les développeurs avaient prévu. source : [Wikipédia](https://fr.wikipedia.org/wiki/M%C3%A9tagame)


