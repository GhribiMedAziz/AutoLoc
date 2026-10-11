# 📘 Fiche de Révision — Projet AutoLoc (Ateliers 1 & 2)

## 1. Pile Technologique
- **Java 17** + **Spring Boot**
- **Spring Data JPA** & **Hibernate ORM**
- **MySQL** (XAMPP sur le port 3306)
- **Lombok** (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`)

---

## 2. Bonnes Pratiques Clean Code sur les Entités JPA

1. **Pourquoi bannir `@Data` sur les entités ?**
   - `@Data` génère automatiquement `toString()`, `equals()` et `hashCode()`.
   - Avec des relations bidirectionnelles, cela provoque une **récursion infinie** (`StackOverflowError`).
   - Solution : utiliser `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor` ciblés.

2. **Génération d'identifiants (`@GeneratedValue`)** :
   - `GenerationType.IDENTITY` s'appuie sur la colonne `AUTO_INCREMENT` native de MySQL.

3. **Énumérations (`@Enumerated`)** :
   - `@Enumerated(EnumType.STRING)` enregistre le libellé textuel au lieu de l'indice numérique (`ORDINAL`), préservant la cohérence même si l'ordre des valeurs change dans le code.

---

## 3. Synthèse des 8 Associations JPA

| Relation | Entités concernées | Type d'annotation | Côté Propriétaire (*Owning side*) | Clé étrangère / Jointure |
| :--- | :--- | :--- | :--- | :--- |
| **1** | `Agence` (1) ↔ `Employe` (*) | `@OneToMany` / `@ManyToOne` | `Employe` | `id_agence` (dans table `employe`) |
| **2** | `Agence` (1) ↔ `Vehicule` (*) | `@OneToMany` / `@ManyToOne` | `Vehicule` | `id_agence` (dans table `vehicule`) |
| **3** | `Vehicule` (1) ↔ `Maintenance` (*) | `@OneToMany` / `@ManyToOne` | `Maintenance` | `id_vehicule` (dans table `maintenance`) |
| **4** | `Vehicule` (*) ↔ `Equipement` (*) | `@ManyToMany` | `Vehicule` | Table de jointure `vehicule_equipement` |
| **5** | `Vehicule` (1) ↔ `Reservation` (*) | `@OneToMany` / `@ManyToOne` | `Reservation` | `id_vehicule` (dans table `reservation`) |
| **6** | `Client` (1) ↔ `Reservation` (*) | `@OneToMany` / `@ManyToOne` | `Reservation` | `id_client` (dans table `reservation`) |
| **7** | `Reservation` (1) ↔ `Contrat` (1) | `@OneToOne` | `Contrat` | `id_reservation` (clé étrangère unique dans `contrat`) |
| **8** | `Contrat` (1) ◆── (*) `Paiement` | `@OneToMany` (Composition) / `@ManyToOne` | `Paiement` | `id_contrat` (dans table `paiement`) |

### Points Clés :
- **Composition UML (`Contrat` ◆── `Paiement`)** : Traduit par `cascade = CascadeType.ALL, orphanRemoval = true` dans `Contrat`. Si un contrat est supprimé, ses paiements le sont automatiquement.
- **Jointure Many-to-Many (`Vehicule` ↔ `Equipement`)** : `@JoinTable(name = "vehicule_equipement", joinColumns = @JoinColumn(name = "id_vehicule"), inverseJoinColumns = @JoinColumn(name = "id_equipement"))`.
- **Éviter les boucles JSON** : `@JsonIgnore` sur le côté inverse des relations bidirectionnelles empêche Jackson de boucler à l'infini lors des appels HTTP.
- **Résultat en base de données** : **10 tables au total** (9 entités + 1 table d'association `vehicule_equipement`).

---

## 4. Connexion MySQL (XAMPP) & Configuration Spring Boot

Fichier `src/main/resources/application.properties` :

```properties
spring.application.name=autoloc-api

# Port personnalise pour eviter le conflit avec Oracle TNS sur le port 8080
server.port=${SERVER_PORT:8081}

# Connexion MySQL avec creation automatique du schema
spring.datasource.url=jdbc:mysql://localhost:3306/autoloc_db?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}

# Gestion du schema Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logs
logging.level.org.hibernate.SQL=DEBUG
logging.level.tn.esprit.autoloc=INFO
```

### Mécanismes importants :
- `createDatabaseIfNotExist=true` : MySQL crée la base `autoloc_db` automatiquement dès la première connexion s'il ne la trouve pas.
- `${DB_USERNAME:root}` & `${DB_PASSWORD:}` : Prise en charge des variables d'environnement tout en conservant les valeurs par défaut de XAMPP.
- `server.port=8081` : Libère l'application du conflit avec le port `8080` occupé par Oracle sur votre machine.

---

## 5. Stratégie DDL Hibernate (`ddl-auto`)

- **`update`** : Mode quotidien. Met à jour la structure sans effacer les données.
- **`create`** : Supprime (`DROP TABLE IF EXISTS`) puis recrée tout à neuf.
- **Pourquoi `create` puis `update` ?**  
  Lorsque nous avons ajouté les associations, les anciennes tables de la séance 1 n'avaient pas les clés étrangères. Nous avons basculé temporairement sur `create` pour forcer Hibernate à reconstruire les 10 tables avec les contraintes d'intégrité, puis nous sommes revenus à `update` pour sécuriser les futures données saisies.

---

## 6. Commandes Utiles de Vérification

```powershell
# Connexion en ligne de commande MySQL
C:\xampp\mysql\bin\mysql.exe -u root

# Verifications SQL
USE autoloc_db;
SHOW TABLES;          # Affiche les 10 tables
DESCRIBE vehicule;    # Structure d'une table
SELECT * FROM vehicule;
```

- **phpMyAdmin** : `http://localhost/phpmyadmin`
- **Tester l'API** : `http://localhost:8081/api/vehicules`
