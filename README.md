## Gestió de videojocs

El programa de `RA1/pt3` gestiona un catàleg de videojocs i el desa a
`videojocs.dat` mitjançant `ObjectOutputStream` i `ObjectInputStream`.

Per executar-lo des de la carpeta del projecte:

```bash
javac -encoding UTF-8 RA1/pt3/CRUD.java RA1/pt3/Videojoc.java
java -cp RA1 pt3.CRUD
```

El menú permet afegir, llistar, cercar parcialment pel títol, actualitzar,
eliminar i desar videojocs. La classe `Videojoc` implementa `Serializable` i
inclou `serialVersionUID` per controlar la compatibilitat de la serialització.

## Problemàtiques dels fitxers binaris

- No són llegibles directament amb un editor de text.
- La compatibilitat depèn de la classe Java i de la seva versió. Si es canvien
	els atributs o la classe, pot aparèixer una `InvalidClassException`; per això
	es defineix `serialVersionUID`.
- El format és específic de Java i no és interoperable directament amb Python,
	JavaScript o altres llenguatges.
- Deserialitzar dades desconegudes pot ser insegur, perquè Java reconstrueix
	objectes durant la lectura. Només s'haurien de llegir fitxers de confiança.
- Si el procés s'interromp mentre s'escriu, el fitxer pot quedar corrupte i
	caldria recuperar-lo des d'una còpia de seguretat.
