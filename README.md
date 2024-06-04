# Airness

## Description

MyApplication est une application Android permettant aux utilisateurs de parcourir une liste de produits, d'ajouter des articles à un panier, de visualiser les détails des produits et de passer des commandes. L'application utilise plusieurs technologies modernes pour offrir une expérience utilisateur fluide et réactive.

## Table des matières

- [Description](#description)
- [Table des matières](#table-des-matières)
- [Fonctionnalités](#fonctionnalités)
- [Technologies utilisées](#technologies-utilisées)
- [Structure du projet](#structure-du-projet)
- [Configuration initiale](#configuration-initiale)
- [Dépendances](#dépendances)
- [Instructions pour le développement](#instructions-pour-le-développement)
- [Architecture](#architecture)
- [Licence](#licence)

## Fonctionnalités

- **Navigation fluide** : Utilisation du Jetpack Navigation Component pour une navigation simple et efficace entre les différents écrans.
- **Affichage des produits** : Parcourez une liste de produits, filtrée par catégories et triée par nom ou prix.
- **Détails des produits** : Visualisez les détails des produits, y compris l'image, la description, le prix, le matériel et la couleur.
- **Ajout au panier** : Ajoutez des produits au panier et ajustez les quantités.
- **Panier** : Visualisez les articles dans le panier, avec des options pour augmenter ou diminuer les quantités.
- **Passage de commande** : Remplissez un formulaire de commande avec adresse, ville, code postal et informations de paiement.
- **Options de livraison** : Choisissez entre une livraison gratuite ou une livraison payante.
- **Retour à l'accueil** : Bouton pour retourner à l'accueil depuis le panier.

## Technologies utilisées

- **Kotlin** : Langage principal utilisé pour le développement de l'application.
- **Android Jetpack** : Composants modernes de l'architecture Android.
  - **Navigation Component** : Gestion de la navigation dans l'application.
  - **ViewBinding** : Liaison des vues pour une manipulation efficace des composants UI.
  - **DataBinding** : Liaison des données aux vues et gestion des événements UI.
- **Glide** : Chargement et gestion des images.
- **Material Components** : Utilisation des composants Material Design pour une interface utilisateur moderne et cohérente.
- **Safe Args** : Passer des arguments de manière sécurisée entre les fragments.

## Structure du projet

```
Airness
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/airness/myapplication
│   │   │   │   ├── ui
│   │   │   │   │   ├── MainActivity.kt
│   │   │   │   │   ├── fragment
│   │   │   │   │   │   ├── HomeFragment.kt
│   │   │   │   │   │   ├── ProductsFragment.kt
│   │   │   │   │   │   ├── DetailFragment.kt
│   │   │   │   │   │   ├── CartFragment.kt
│   │   │   │   │   │   ├── CategoriesFragment.kt
│   │   │   │   │   │   ├── CheckoutFragment.kt
│   │   │   │   │   ├── adapter
│   │   │   │   │   │   ├── MeubleAdapter.kt
│   │   │   │   │   │   ├── CartAdapter.kt
│   │   │   │   │   ├── model
│   │   │   │   │   │   ├── Meuble.kt
│   │   │   │   │   │   ├── CartItem.kt
│   │   │   │   │   ├── viewmodel
│   │   │   │   │   │   ├── MeubleViewModel.kt
│   │   │   │   │   │   ├── CartViewModel.kt
│   │   │   │   │   │   ├── NavigationViewModel.kt
│   │   │   ├── res
│   │   │   │   ├── layout
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── fragment_home.xml
│   │   │   │   │   ├── fragment_products.xml
│   │   │   │   │   ├── fragment_detail.xml
│   │   │   │   │   ├── fragment_cart.xml
│   │   │   │   │   ├── fragment_categories.xml
│   │   │   │   │   ├── fragment_checkout.xml
│   │   │   │   │   ├── item_meuble.xml
│   │   │   │   │   ├── item_cart.xml
│   │   │   │   ├── drawable
│   │   │   │   │   ├── blacknoback.png
│   │   │   │   ├── menu
│   │   │   │   │   ├── menu_main.xml
│   │   │   │   │   ├── drawer_menu.xml
│   │   │   │   ├── values
│   │   │   │   ├── AndroidManifest.xml
├── build.gradle
├── settings.gradle
└── ...
```

## Configuration initiale

### 1. Cloner le dépôt

```bash
git clone https://github.com/votre-utilisateur/MyApplication.git
cd MyApplication
```

### 2. Ouvrir le projet dans Android Studio

- Lancez Android Studio.
- Sélectionnez `Open an existing Android Studio project`.
- Naviguez jusqu'au répertoire du projet et sélectionnez-le.

### 3. Synchroniser le projet avec Gradle

- Android Studio devrait automatiquement commencer à synchroniser le projet avec Gradle.
- Si ce n'est pas le cas, cliquez sur `File > Sync Project with Gradle Files`.

### 4. Ajouter les clés API nécessaires (si applicable)

- Si le projet utilise des API nécessitant des clés ou des configurations spécifiques, ajoutez-les dans les fichiers appropriés (par exemple, `local.properties` ou des fichiers de configuration spécifiques).

## Dépendances

Le fichier `build.gradle` (module : app) contient toutes les dépendances nécessaires pour le projet :

```groovy
dependencies {
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    kapt 'com.github.bumptech.glide:compiler:4.12.0'
    implementation 'jp.wasabeef:glide-transformations:4.3.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}
```

## Instructions pour le développement

### Ajouter de nouveaux produits

- Ajoutez de nouveaux objets `Meuble` dans le fichier de données source (ou la base de données si applicable).
- Mettez à jour l'interface utilisateur si nécessaire.

### Ajouter de nouvelles fonctionnalités

- Créez un nouveau fragment pour la fonctionnalité.
- Ajoutez la navigation vers ce fragment dans le fichier de navigation.
- Mettez à jour le ViewModel et les adapters si nécessaire.

### Tester l'application

- Exécutez les tests unitaires avec JUnit.
- Effectuez des tests instrumentés avec Espresso.

## Architecture

### MVVM (Model-View-ViewModel)

L'application suit le modèle d'architecture MVVM pour une séparation claire des préoccupations et une meilleure testabilité.

- **Model** : Représente les données de l'application. Ce sont les classes de données comme `Meuble` et `CartItem`.
- **View** : Les activités et les fragments qui affichent les données et écoutent les interactions de l'utilisateur.
- **ViewModel** : Gère la logique de l'application et prépare les données pour la vue.

### Navigation Component

- Utilisation du composant de navigation de Jetpack pour gérer les transitions entre les fragments et les actions de navigation.

### ViewBinding et DataBinding

- **ViewBinding** : Pour lier les vues sans les nécessités d'utiliser `findViewById`.
- **DataBinding** : Pour lier les données aux vues et gérer les interactions de l'utilisateur.

## Licence

Ce projet est sous licence MIT. Consultez le fichier [LICENSE](LICENSE) pour plus d'informations.
```
