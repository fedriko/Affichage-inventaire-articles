import java.io.*;

/* @author Federico Ezequiel Barallobres, Philippe Beaulieu Poulin
 * @version 12-decembre-2024
 * @code_permanent BARF24069501, BEAP10079006
 */

/**
 * Classe principale du programme.
 *
 * Ce programme gère un inventaire d'articles et propose diverses fonctionnalités :
 * - Ajout, affichage, modification et suppression d'articles.
 * - Gestion des articles avec des stocks critiques.
 * - Génération de factures.
 * - Sauvegarde de l'inventaire.
 */

public class TP3 {

    // CONSTANTES

    // Menus et écrans
    public static final String MENU_PRINCIPAL=""" 
                    **************************************************               
                    *               Menu principal                   *
                    **************************************************
                    *       Bienvenue chez Le Meilleur               *
                    *       Choisissez une option :                  *
                    **************************************************
                    *   A       Ajouter un article                   *
                    *   I       Afficher les détails d’un article    *
                    *   T       Afficher tous les articles           *
                    *   S       Supprimer un article                 *
                    *   L       Afficher les articles à risque       *
                    *   M       Modifier un article                  *
                    *   F       Facturation                          *
                    *   Q       Quitter                              *
                    **************************************************""";
    public static final String LISTE_ARTICLES= """
            ***********************************************************************************************************************************
            *                                                    Liste des Articles                                                           *
            ***********************************************************************************************************************************
            ID   | Catégorie                 | Description                                                            | Quantité | Prix       |
            -----------------------------------------------------------------------------------------------------------------------------------""";
    public static final String DETAILS_ARTICLE_MENU= """
            **************************************************
            *           Détails de l'Article                 *
            **************************************************""";
    public static final String MSG_ERR_ARTICLE_INEXISTANT= """
                L'article avec l'ID spécifié n'existe pas.
            **************************************************""";
    public static final String MSG_ERR_INVENTAIRE_VIDE= """
            **************************************
            *          Inventaire vide           *
            **************************************""";
    public static final String MSG_ERR_TABLEAU_ARTICLE_VIDE= """
            *************************************************
            *           Liste des Articles                  *
            *************************************************
            Le tableau des articles est vide !
            *************************************************""";
    public static final String MSG_ARTICLE_TROUVE= """
            *************************************************
            *               Article Trouvé                  *
            *************************************************""";
    public static final String MSG_AFFICHER_ARTICLES_RISQUE= """
            ***********************************************************************************************************************************
            *                                                Liste des Articles à Risque                                                      *
            ***********************************************************************************************************************************
             ID  | Catégorie                 | Description                                                            | Quantité | Prix       |
            -----------------------------------------------------------------------------------------------------------------------------------
            """;
    public static final String MSG_AUCUN_ARTICLE_RISQUE= """
            **************************************
            *       Pas d'article à risque       *
            **************************************""";
    public static final String AUCUN_ARTICLE_TROUVE_ID = """
            *************************************************
            *         Aucun article trouvé avec cet ID      *
            *************************************************
            """;
    public static final String MSG_CHOIX_MODIFICATION = """
        Que souhaitez-vous modifier ?
        1. La description
        2. Le prix
        3. Les deux (au format : Description;Prix)
        Entrez votre choix (1, 2 ou 3) : """;

    // Messages d'erreurs et de notifications pour guider l'utilisateur.
    public static final String MSG_ERR_CHOIX_INVALIDE_MENU = "Erreur, le choix pour le menu principal est incorrect !";
    public static final String MSG_ERR_FICHIER_MANQUANT = "Erreur: Le fichier pour générer l'inventaire est introuvable.";
    public static final String MSG_ERR_MAUVAIS_FORMAT_FICHIER = "Erreur: Le format du fichier est mauvais.";
    public static final String MSG_ERR_LECTURE = "Erreur: Problème de lecture";
    public static final String MSG_CHOIX = "Tapez votre choix : ";
    public static final String MSG_SAISIE_ID = "Tapez l'id pour cet article : ";
    public static final String MSG_SAISIE_CATEGORIE = "Tapez la catégorie pour cet article : ";
    public static final String MSG_SAISIE_DESCRIPTION = "Tapez la description pour cet article : ";
    public static final String MSG_SAISIE_QUANTITE = "Tapez la quantité pour cet article : ";
    public static final String MSG_SAISIE_PRIX = "Tapez le prix pour cet article : ";
    public static final String MSG_ARTICLE_NON_SUPPRIME = "Nous n'avons pas supprimé l'article !";
    public static final String MSG_ARTICLE_SUPPRIME = "Article supprimé!";
    public static final String MSG_MODIFIER_ARTICLE = "Entrez l'ID de l'article à modifier : ";
    public static final String MSG_QUANTITE_ACHETE = "Quantité acheté : ";
    public static final String MSG_ERREUR_STOCK_INSUFFISANT = "Erreur, La quantité en stock est insuffisante pour procéder à l'achat!";
    public static final String MSG_ERR_ID_NON_ENTIER = "Erreur: L'ID doit être un nombre entier";
    public static final String MSG_ERR_ID_INVALIDE = "Erreur, l'id est invalide !";
    public static final String MSG_ERR_CATEGORIE_INVALIDE = "Erreur, la catégorie est invalide !";
    public static final String MSG_ERR_DESCRIPTION_INVALIDE = "Erreur, la description est invalide !";
    public static final String MSG_ERR_QUANTITE_INVALIDE = "Erreur, la quantité est invalide !";
    public static final String MSG_ERR_PRIX_INVALIDE = "Erreur, le prix est invalide";
    public static final String MSG_QUANTITE_MISE_A_JOUR = "Quantité mise à jour pour l'article existant. \n";
    public static final String MSG_TABLEAU_AGRANDI_ARTICLE_AJOUTE = "Tableau agrandi et article ajouté.";
    public static final String MSG_CONFIRMER_SUPPRESSION = "Confirmez-vous la suppression ? (o/n) : ";
    public static final String MSG_ERR_SAISIE_INVALIDE = "Erreur: Entrez saisie invalide";
    public static final String MSG_CONFIRMER_QUITTER = "Voulez-vous quitter ? (o/n) : ";
    public static final String MSG_ERR_CHOIX_MODIF_INVALIDE = "Erreur: La valeur saisie n'est pas entre 1 et 3";
    public static final String MSG_NOUVELLE_DESCRIPTION = "Entrez la nouvelle description de l'article : ";
    public static final String MSG_NOUVEAU_PRIX = "Entrez le nouveau prix de l'article : ";
    public static final String MSG_ERR_VALEUR_NOMBRE_REEL = "Erreur, la valeur saisie doit être un nombre réel";
    public static final String MSG_DESCRIPTION_ET_PRIX = "Entrez la description et le prix de l'article : ";
    public static final String MSG_ERR_FORMAT_CHAINE_INVALIDE = "Erreur, Format de la chaine invalide!";
    public static final String MSG_ERR_QUANTITE_NON_ENTIERE = "Erreur, la quantité doit être un nombre entier";
    public static final String MSG_ERR_QUANTITE_NEGATIVE_ZERO = "La quantité saisie est plus petite ou égale à zéro. L'achat est annulé";
    public static final String MSG_CONFIRMER_CONTINUER = "Continuer ? (o/n) : ";
    public static final String MSG_ERR_ECRITURE = "Erreur, problème d'écriture!";

    // Noms de fichier
    public static final String NOM_FICHIER_INVENTAIRE = "inventaire.txt";
    public static final String NOM_FICHIER_SAUVEGARDE = "sortie.txt";
    public static final String MSG_ARTICLE_EXISTE_MODIF = "L'article existe !";

    /**
     * Méthode main
     *
     * Initialise un inventaire, charge les données depuis un fichier,
     * et gère un menu principal pour interagir avec l'utilisateur.
     *
     */
    public static void main(String[] args) {

        // Initialisation d'un tableau vide d'articles.
        Article[] tabArticleVide = new Article[0];

        // Initialisation d'un inventaire null.
        Inventaire magasin=new Inventaire("Kotsko",tabArticleVide,100);

        int id; // Variable pour stocker l'ID de l'article.
        boolean quitter = false; // Indicateur pour savoir si l'utilisateur veut quitter le programme.

        // Tentative de chargement de l'inventaire depuis un fichier texte.
        try {
            magasin = new Inventaire("kotsko", chargerInventaire(NOM_FICHIER_INVENTAIRE), 100);
        } catch (FileNotFoundException e) {
            // Affiche un message d'erreur si le fichier n'est pas trouvé.
            System.out.println(MSG_ERR_FICHIER_MANQUANT);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Affiche un message d'erreur si le format du fichier est incorrect.
            System.out.println(MSG_ERR_MAUVAIS_FORMAT_FICHIER);
        } catch (IOException e) {
            // Affiche un message d'erreur si un problème de lecture survient.
            System.out.println(MSG_ERR_LECTURE);
        }

        // Boucle principale qui gère les options du menu.
        do {
            // Affichage du menu principal et traitement du choix de l'utilisateur.
            switch (afficherMenu()) {
                case 'A': // Ajouter un article.

                    ajouterArticle(magasin, saisirArticle(magasin)); // Appel de la méthode pour ajouter un article.
                    break;

                case 'I': // Afficher les détails d'un article.

                    // Vérifie si l'inventaire est vide avant d'afficher un article.
                    if (magasin.getTabArticle().length == 0) {
                        System.out.println();
                        System.out.println(MSG_ERR_INVENTAIRE_VIDE);
                    } else {
                        id = saisirID(); // Demande l'ID de l'article à afficher.
                        afficherUnArticle(magasin, id); // Affiche l'article.
                    }
                    break;

                case 'T': // Afficher tous les articles.

                    // Vérifie si l'inventaire est vide avant d'afficher tous les articles.
                    if (magasin.getTabArticle().length == 0) {
                        System.out.println();
                        System.out.println(MSG_ERR_TABLEAU_ARTICLE_VIDE);
                    } else {
                        afficherTousLesArticles(magasin); // Affiche tous les articles de l'inventaire.
                    }
                    break;

                case 'S': // Supprimer un article.

                    // Si l'inventaire est vide, affiche un message d'erreur.
                    if (magasin.getTabArticle().length == 0) {
                        System.out.println(MSG_ERR_INVENTAIRE_VIDE);
                    } else {
                        id = saisirID(); // Demande l'ID de l'article à supprimer.
                        supprimerUnArticle(magasin, id); // Supprime l'article de l'inventaire.
                    }
                    break;

                case 'L': // Afficher les articles à risque (quantité faible).

                    // Si l'inventaire est vide, affiche un message d'erreur.
                    if (magasin.getTabArticle().length == 0) {
                        System.out.println(MSG_ERR_INVENTAIRE_VIDE);
                    } else {
                        afficherArticleARisque(magasin); // Affiche les articles dont la quantité est faible.
                    }
                    break;

                case 'M': // Modifier un article.

                    // Si l'inventaire est vide, affiche un message d'erreur.
                    if (magasin.getTabArticle().length == 0) {
                        System.out.println(MSG_ERR_INVENTAIRE_VIDE);
                    } else {
                        System.out.print(MSG_MODIFIER_ARTICLE);
                        id = saisirID(); // Demande l'ID de l'article à modifier.

                        // Vérifie si l'article existe dans l'inventaire.
                        if (trouverIndexArticleId(id, magasin.getTabArticle()) == -1) {
                            System.out.println(AUCUN_ARTICLE_TROUVE_ID);
                        } else {
                            // Si l'article existe, affiche ses informations actuelles.
                            Article article = magasin.getTabArticle()[trouverIndexArticleId(id, magasin.getTabArticle())];
                            System.out.println(MSG_ARTICLE_TROUVE);
                            System.out.println("Description actuelle : " + article.getDescription());
                            System.out.println("Prix actuel          : " + article.getPrix());
                            System.out.println("*************************************************");

                            modifierArticle(magasin, id); // Permet à l'utilisateur de modifier l'article.
                        }
                    }
                    break;

                case 'F': // Facturer les articles.

                    // Lance la facturation si l'inventaire n'est pas vide.
                    if (magasin.getTabArticle().length != 0) {
                        facturer(magasin);
                    }else{
                        System.out.println(MSG_ERR_INVENTAIRE_VIDE);
                    }
                    break;

                case 'Q': // Quitter le programme.

                    char saisie = demanderConfirmation(MSG_CONFIRMER_QUITTER); // Demande confirmation
                    if (saisie == 'O') {
                        quitter = true; // Marque que l'utilisateur veut quitter le programme.
                    }
            }
        } while (!quitter); // Répète tant que l'utilisateur n'a pas choisi de quitter.

        // Sauvegarde l'inventaire dans un fichier.
        try {
            FileWriter canal = new FileWriter(NOM_FICHIER_SAUVEGARDE);
            StringBuilder inventaire = new StringBuilder();

            // Parcourt tous les articles et les ajoute au fichier.
            for (int i = 0; i < magasin.getTabArticle().length; i++) {
                Article article = magasin.getTabArticle()[i];
                inventaire.append(String.format("%d;%s;%s;%d;%.2f\n", article.getId(), article.getCategorie(),
                        article.getDescription(), article.getQuantite(), article.getPrix()));
            }

            canal.write(inventaire.toString()); // Écrit les informations dans le fichier.
            canal.close(); // Ferme le fichier après l'écriture.
        } catch (IOException e) {
            // Si une erreur d'écriture se produit, elle est levée sous forme d'exception.
            throw new RuntimeException(e);
        }
    }

    /**
     * Demande à l'utilisateur de saisir un identifiant (ID) pour un article.
     *
     * Cette méthode vérifie que l'ID saisi par l'utilisateur est un entier valide.
     * Si la saisie n'est pas un nombre entier, elle continue à demander jusqu'à ce que l'utilisateur
     * fournisse une saisie correcte.
     *
     * @return L'ID saisi par l'utilisateur sous forme d'entier.
     */
    private static int saisirID() {

        // Variable pour vérifier si l'ID saisi est valide.
        boolean idEstEntier = false;
        int id = 0; // Variable id.

        // Boucle qui continue tant que l'ID n'est pas un nombre entier valide.
        do {
            // Demande à l'utilisateur de saisir un ID.
            try {
                System.out.print(MSG_SAISIE_ID);
                String idS = Clavier.lireString().trim();

                // Tente de convertir la chaîne en entier.
                id = Integer.parseInt(idS);
                idEstEntier = true;

            } catch (NumberFormatException e) {
                // Si la saisie n'est pas un nombre entier valide, affiche un message d'erreur.
                System.out.println(MSG_ERR_ID_NON_ENTIER);
            }
        } while (!idEstEntier); // Continue à demander l'ID jusqu'à ce qu'une saisie valide soit effectuée.

        return id; // Retourne l'ID valide saisi par l'utilisateur.
    }

    /**
     * Affiche le menu principal et récupère le choix de l'utilisateur.
     *
     * Cette méthode affiche le menu principal à l'utilisateur et attend qu'il fasse un choix valide.
     * Si l'utilisateur entre une valeur invalide, le menu sera affiché à nouveau jusqu'à ce qu'un choix valide soit fait.
     *
     * @return Le caractère correspondant à l'option choisie par l'utilisateur.
     */
    public static char afficherMenu() {

        String saisie; // Variable pour stocker la saisie de l'utilisateur.
        boolean valide = false; // Indicateur pour vérifier si l'option est valide.
        char option = 'p'; // Initialisation de l'option à une valeur par défaut non valide.

        // Boucle qui répète l'affichage du menu tant que l'utilisateur ne fait pas un choix valide.
        do {
            System.out.println();
            System.out.println(MENU_PRINCIPAL); // Affiche le menu principal.
            System.out.print(MSG_CHOIX); // Demande à l'utilisateur de faire un choix.
            saisie = Clavier.lireString().toUpperCase(); // Récupère la saisie et la met en majuscule pour simplifier la comparaison.

            if (saisie.length() != 1) {
                // Si l'utilisateur entre plus d'un caractère, affiche un message d'erreur.
                System.out.println(MSG_ERR_CHOIX_INVALIDE_MENU + "\n");
            } else {
                option = saisie.charAt(0); // Prend le premier caractère de la saisie comme option.

                // Vérifie si l'option entrée est valide parmi les options disponibles.
                valide = option == 'A' || option == 'I' || option == 'T' || option == 'S' ||
                        option == 'L' || option == 'M' || option == 'F' || option == 'Q';

                if (!valide) {
                    // Si l'option n'est pas valide, affiche un message d'erreur.
                    System.out.println(MSG_ERR_CHOIX_INVALIDE_MENU + "\n");
                }
            }
        } while (!valide); // Répète la boucle tant qu'une option valide n'est pas entrée.

        return option; // Retourne l'option choisie par l'utilisateur.
    }

    /**
     * Demande à l'utilisateur de saisir les informations d'un article.
     *
     * Cette méthode permet à l'utilisateur de saisir un ID, une catégorie, une description,
     * une quantité et un prix pour un nouvel article. Elle vérifie que les données saisies sont valides.
     * Si une saisie est invalide, un message d'erreur est affiché et l'utilisateur est invité à réessayer.
     * Si l'article existe déjà, seul la quantité à ajouter est demandé.
     *
     * @return Un objet `Article` contenant les informations saisies par l'utilisateur.
     */
    public static Article saisirArticle(Inventaire inventaire) {

        // Initialisation des variables pour les différents champs de l'article.
        int id = 0;
        String categorie = "";
        String description = "";
        int quantite = 0;
        double prix = 0;

        boolean articleExist = false; // Indique si l'article est déjà présent dans l'inventaire.

        // Tableaux de messages pour guider l'utilisateur dans ses saisies.
        String[] messages = {MSG_SAISIE_ID, MSG_SAISIE_CATEGORIE, MSG_SAISIE_DESCRIPTION, MSG_SAISIE_QUANTITE, MSG_SAISIE_PRIX};
        String[] messagesErreur = {MSG_ERR_ID_INVALIDE, MSG_ERR_CATEGORIE_INVALIDE, MSG_ERR_DESCRIPTION_INVALIDE,
                MSG_ERR_QUANTITE_INVALIDE, MSG_ERR_PRIX_INVALIDE};

        int i = 0; // Variable pour suivre l'étape de saisie.
        String saisie; // Variable pour stocker la saisie de l'utilisateur.

        // Boucle de saisie, qui continue jusqu'à ce que toutes les informations soient valides.
        do {
            // Affiche le message correspondant à l'étape actuelle de la saisie.
            System.out.print(messages[i]);
            saisie = Clavier.lireString().trim(); // Récupère la saisie et supprime les espaces inutiles.

            if (saisie.isEmpty()) {
                // Si l'utilisateur n'a rien saisi, affiche un message d'erreur.
                System.out.println(messagesErreur[i]);
            } else {
                // Selon l'étape (i), on traite la saisie de manière appropriée.
                switch (i) {
                    case 0: // Saisie de l'ID.
                        try {
                            id = Integer.parseInt(saisie); // Essaie de convertir la saisie en entier.
                            i++; // Passe à l'étape suivante si la conversion réussit et l'article est nouveau.

                            // Si ce n'est pas un nouvel article, passe directement à la saisie de la quantité.
                            articleExist = trouverIndexArticleId(id,inventaire.getTabArticle()) != -1;
                            if(articleExist) {
                                System.out.println(MSG_ARTICLE_EXISTE_MODIF);
                                i = 3; // le 'case' 3 est celui de la saisie de quantité.
                            }
                        } catch (NumberFormatException e) {
                            // Si l'ID n'est pas un nombre entier, affiche un message d'erreur.
                            System.out.println(messagesErreur[i]);
                        }
                        break;

                    case 1: // Saisie de la catégorie.
                        categorie = saisie;
                        i++; // Passe à l'étape suivante.
                        break;

                    case 2: // Saisie de la description.
                        description = saisie;
                        i++; // Passe à l'étape suivante.
                        break;

                    case 3: // Saisie de la quantité.
                        try {
                            quantite = Integer.parseInt(saisie); // Essaie de convertir la saisie en entier.
                            i++; // Passe à l'étape suivante si la conversion réussit.

                            // Si l'article exist déjà, on sort de la boucle
                            if(articleExist) {
                                i = 5; // La boucle se termine quand i est supérieur à 4.
                            }
                        } catch (NumberFormatException e) {
                            // Si la quantité n'est pas un nombre entier, affiche un message d'erreur.
                            System.out.println(messagesErreur[i]);
                        }
                        break;

                    case 4: // Saisie du prix.
                        try {
                            prix = Double.parseDouble(saisie); // Essaie de convertir la saisie en nombre décimal.
                            i++; // Passe à l'étape suivante si la conversion réussit.
                        } catch (NumberFormatException e) {
                            // Si le prix n'est pas un nombre décimal, affiche un message d'erreur.
                            System.out.println(messagesErreur[i]);
                        }
                        break;
                }
            }
        } while (i < 5); // Répète tant que toutes les étapes de saisie nécessaires ne sont pas validées.

        // Une fois toutes les saisies valides, retourne un nouvel objet Article avec les informations saisies.
        return new Article(id, categorie, description, quantite, prix);
    }

    /**
     * Ajoute un article à l'inventaire. Si l'article existe déjà, la quantité est mise à jour.
     * Sinon, l'article est ajouté au tableau d'articles. Si l'ID de l'article est invalide, un nouvel ID est généré.
     *
     * @param inventaire L'inventaire auquel l'article doit être ajouté.
     * @param article L'article à ajouter à l'inventaire.
     */
    public static void ajouterArticle(Inventaire inventaire, Article article) {

        // Recherche l'index de l'article dans l'inventaire en fonction de son ID.
        int indexId = trouverIndexArticleId(article.getId(), inventaire.getTabArticle());
        // Récupère le tableau des articles de l'inventaire.
        Article[] tabArticle = inventaire.getTabArticle();

        if (indexId != -1) {
            // Si l'article existe déjà, on met à jour sa quantité.
            int quantite = article.getQuantite() + tabArticle[indexId].getQuantite(); // Additionne les quantités.
            tabArticle[indexId].setQuantite(quantite); // Met à jour la quantité de l'article dans le tableau.
            System.out.println(MSG_QUANTITE_MISE_A_JOUR);
        } else {
            // Si l'article n'existe pas dans l'inventaire, on vérifie si l'ID est valide.
            if (tabArticle.length != 0 && article.getId() != tabArticle[tabArticle.length - 1].getId() + 1) {
                // Si l'ID est invalide (pas consécutif au dernier ID), on génère un nouvel ID automatiquement.
                int newId = tabArticle[tabArticle.length - 1].getId() + 1; // Génère un nouvel ID basé sur le dernier ID de l'inventaire.
                System.out.println("ID " + article.getId() + " invalide. Un nouvel ID généré automatiquement : " + newId);
                article.setId(newId); // Définit le nouvel ID à l'article.
            }

            // Ajoute l'article au tableau des articles de l'inventaire.
            inventaire.setTabArticle(creerTableauNouveauArticle(inventaire.getTabArticle(), article));
            System.out.println(MSG_TABLEAU_AGRANDI_ARTICLE_AJOUTE);
        }
    }


    /**
     * Recherche l'indice d'un article dans le tableau en fonction de son ID.
     *
     * Cette méthode parcourt le tableau des articles à la recherche de l'article ayant l'ID spécifié.
     * Si l'article est trouvé, la méthode retourne l'indice de cet article dans le tableau.
     * Si l'article n'est pas trouvé, elle retourne -1.
     *
     * @param IdArticle L'ID de l'article à rechercher dans le tableau.
     * @param tabArticle Le tableau des articles dans lequel chercher.
     * @return L'indice de l'article dans le tableau si trouvé, -1 sinon.
     */
    private static int trouverIndexArticleId(int IdArticle, Article[] tabArticle) {

        int longTab = tabArticle.length; // Récupère la taille du tableau des articles.
        int i = 0; // Initialisation de l'indice pour parcourir le tableau.
        int index = -1; // Valeur par défaut pour l'indice si l'article n'est pas trouvé.
        boolean idEgal = false; // Indicateur pour vérifier si l'ID de l'article correspond à celui recherché.

        // Parcourt le tableau des articles tant que l'indice est dans les limites du tableau et que l'ID n'a pas été trouvé.
        while (i < longTab && !idEgal) {
            // Vérifie si l'ID de l'article courant est égal à l'ID recherché.
            idEgal = tabArticle[i].getId() == IdArticle;

            if (idEgal) {
                // Si l'ID correspond, enregistre l'indice de l'article trouvé.
                index = i;
            }

            i++;
        }

        // Retourne l'indice de l'article trouvé, ou -1 si l'article n'existe pas dans le tableau.
        return index;
    }


    /**
     * Charge un inventaire à partir d'un fichier texte.
     *
     * Cette méthode lit un fichier contenant les informations des articles, puis crée un tableau
     * d'objets `Article` à partir de ces informations. Chaque ligne du fichier représente un article
     * avec des valeurs séparées par des points-virgules (ID, catégorie, description, quantité, prix).
     *
     * @param cheminFichier Le chemin du fichier contenant les informations des articles.
     * @return Un tableau d'objets `Article` représentant l'inventaire chargé à partir du fichier.
     * @throws IOException Si une erreur de lecture du fichier se produit.
     */
    public static Article[] chargerInventaire(String cheminFichier) throws IOException {

        // Lit le contenu du fichier et récupère chaque ligne dans un tableau de String.
        String[] tabLignes = lireFichierInventaire(cheminFichier);

        // Tableau pour stocker les objets Article créés à partir des lignes du fichier.
        Article[] tabArticle = new Article[tabLignes.length];

        // Parcourt chaque ligne du fichier pour créer un objet Article pour chaque ligne.
        for (int i = 0; i < tabLignes.length; i++) {

            // Sépare la ligne en différentes parties en utilisant un séparateur.
            String[] article = tabLignes[i].split(";");

            // Crée un nouvel objet Article avec les données extraites de la ligne et l'ajoute au tableau.
            tabArticle[i] = new Article(
                    Integer.parseInt(article[0]),   // ID de l'article.
                    article[1],                     // Catégorie de l'article.
                    article[2],                     // Description de l'article.
                    Integer.parseInt(article[3]),   // Quantité de l'article.
                    Double.parseDouble(article[4])  // Prix de l'article.
            );
        }

        // Retourne le tableau d'articles créés à partir des données du fichier.
        return tabArticle;
    }

    /**
     * Lit un fichier texte et retourne son contenu sous forme de tableau de chaînes.
     *
     * Cette méthode ouvre un fichier spécifié par son chemin, compte le nombre de lignes qu'il contient,
     * puis lit chaque ligne et la stocke dans un tableau de chaînes. Chaque ligne du fichier sera un élément
     * du tableau retourné.
     *
     * @param cheminFichier Le chemin du fichier à lire.
     * @return Un tableau de chaînes contenant toutes les lignes du fichier.
     * @throws IOException Si une erreur se produit lors de la lecture du fichier.
     */
    private static String[] lireFichierInventaire(String cheminFichier) throws IOException {

        // Ouvre le fichier pour la lecture.
        FileReader canal = new FileReader(cheminFichier);

        // Compte le nombre de lignes dans le fichier.
        int nbLignes = 0;
        String[] tabLignes; // Tableau pour stocker les lignes du fichier.

        // Crée un BufferedReader pour lire le fichier ligne par ligne.
        BufferedReader ligneCanal = new BufferedReader(canal);

        // Parcourt le fichier pour compter le nombre de lignes.
        while (ligneCanal.ready()) {
            if (ligneCanal.readLine() != null) {
                nbLignes++; // Incrémente le compteur pour chaque ligne lue.
            }
        }

        // Crée un tableau pour stocker toutes les lignes lues.
        tabLignes = new String[nbLignes];

        // Ferme le BufferedReader et le FileReader.
        ligneCanal.close();

        // Réouvre le fichier pour une nouvelle lecture.
        canal = new FileReader(cheminFichier);
        ligneCanal = new BufferedReader(canal);

        // Lit toutes les lignes et les stocke dans le tableau.
        for (int i = 0; i < nbLignes; i++) {
            tabLignes[i] = ligneCanal.readLine();
        }

        // Ferme le BufferedReader et le FileReader après la lecture.
        ligneCanal.close();

        // Retourne le tableau contenant toutes les lignes lues du fichier.
        return tabLignes;
    }


    /**
     * Crée un nouveau tableau d'articles en y ajoutant un nouvel article.
     *
     * Cette méthode prend un tableau d'articles existant, crée un nouveau tableau de taille
     * plus grande, puis copie tous les articles existants dans le nouveau tableau
     * et ajoute l'article supplémentaire à la fin.
     *
     * @param tabArticle Le tableau d'articles existants.
     * @param article L'article à ajouter au tableau.
     * @return Un nouveau tableau d'articles incluant le nouvel article.
     */
    private static Article[] creerTableauNouveauArticle(Article[] tabArticle, Article article) {

        // Crée un tableau temporaire de taille plus grande.
        Article[] tabTemp = new Article[tabArticle.length + 1];

        // Copie tous les articles existants dans le tableau temporaire.
        for (int i = 0; i < tabArticle.length; i++) {
            tabTemp[i] = tabArticle[i];
        }

        // Ajoute l'article supplémentaire à la fin du tableau.
        tabTemp[tabTemp.length - 1] = article;

        // Retourne le nouveau tableau d'articles, incluant l'article ajouté.
        return tabTemp;
    }

    /**
     * Affiche tous les articles de l'inventaire.
     *
     * Cette méthode affiche l'en-tête et appelle une autre méthode
     * pour afficher chaque article dans le tableau.
     *
     * @param inventaire L'inventaire contenant les articles à afficher.
     */
    public static void afficherTousLesArticles(Inventaire inventaire) {

        System.out.println();

        // Affiche l'en-tête du tableau des articles.
        System.out.println(LISTE_ARTICLES);

        // Appelle la méthode afficherArticlesLong pour afficher les détails de chaque article.
        afficherArticlesLong(inventaire.getTabArticle());
    }

    /**
     * Affiche les détails de tous les articles dans un format de tableau.
     *
     * Cette méthode affiche chaque article de l'inventaire sous forme d'un tableau détaillé,
     * avec des informations formatées de manière lisible, telles que l'ID, la catégorie, la description,
     * la quantité et le prix de chaque article.
     *
     * @param articles Le tableau d'articles à afficher.
     */
    private static void afficherArticlesLong(Article[] articles) {

        // Parcourt chaque article dans le tableau d'articles.
        for (Article article : articles) {

            // Affiche les informations de l'article dans un format de tableau.
            System.out.printf("%-4d | %-25s | %-70s | %-8d | %-10.2f |\n",
                    article.getId(),         // ID de l'article
                    article.getCategorie(),  // Catégorie de l'article
                    article.getDescription(), // Description de l'article
                    article.getQuantite(),    // Quantité de l'article
                    article.getPrix());       // Prix de l'article
        }

        System.out.println("***********************************************************************************************************************************");
    }


    /**
     * Affiche les détails d'un article.
     *
     * Cette méthode recherche un article dans l'inventaire en utilisant son ID. Si l'article est trouvé,
     * elle affiche ses détails. Si l'article n'existe pas, un message d'erreur est affiché.
     *
     * @param inventaire L'inventaire contenant les articles.
     * @param id L'ID de l'article à afficher.
     */
    public static void afficherUnArticle(Inventaire inventaire, int id) {

        // Recherche l'article par son ID dans l'inventaire.
        Article article = trouverArticleId(inventaire, id);

        System.out.println();

        // Affiche l'en-tête.
        System.out.println(DETAILS_ARTICLE_MENU);

        if (article == null) {
            // Si l'article n'est pas trouvé, affiche un message d'erreur.
            System.out.println(MSG_ERR_ARTICLE_INEXISTANT);
        } else {
            // Si l'article est trouvé, affiche ses détails.
            afficherArticleSeul(article);
        }
    }

    /**
     * Affiche les détails d'un article spécifique.
     *
     * Cette méthode affiche les informations détaillées d'un article, telles que son ID, sa catégorie,
     * sa description, sa quantité et son prix.
     *
     * @param article L'article dont les détails doivent être affichés.
     */
    private static void afficherArticleSeul(Article article) {

        // Affiche l'ID de l'article.
        System.out.printf("ID           : %d\n", article.getId());

        // Affiche la catégorie de l'article.
        System.out.printf("Catégorie    : %s\n", article.getCategorie());

        // Affiche la description de l'article.
        System.out.printf("Description  : %s\n", article.getDescription());

        // Affiche la quantité de l'article.
        System.out.printf("Quantité     : %d\n", article.getQuantite());

        // Affiche le prix de l'article.
        System.out.printf("Prix         : %.2f\n", article.getPrix());

        System.out.println("**************************************************");
    }

    /**
     * Recherche un article dans l'inventaire en fonction de son ID.
     *
     * Cette méthode utilise l'ID de l'article pour rechercher dans l'inventaire et retourner l'article correspondant.
     * Si l'article est trouvé, il est retourné, sinon la méthode retourne `null`.
     *
     * @param inventaire L'inventaire dans lequel rechercher l'article.
     * @param id L'ID de l'article à rechercher.
     * @return L'article correspondant à l'ID, ou `null` si l'article n'est pas trouvé.
     */
    private static Article trouverArticleId(Inventaire inventaire, int id) {

        // Recherche l'index de l'article dans l'inventaire en utilisant son ID.
        int indexArticle = trouverIndexArticleId(id, inventaire.getTabArticle());

        // Retourne l'article à l'index trouvé.
        return inventaire.retournerArticleIndice(indexArticle);
    }


    /**
     * Supprime un article de l'inventaire en fonction de son ID.
     *
     * Cette méthode recherche un article dans l'inventaire en utilisant son ID. Si l'article est trouvé,
     * elle affiche ses détails et demande à l'utilisateur de confirmer la suppression. Si l'utilisateur confirme,
     * l'article est supprimé. Sinon, un message indique que la suppression a été annulée.
     *
     * @param inventaire L'inventaire contenant l'article à supprimer.
     * @param id L'ID de l'article à supprimer.
     */
    public static void supprimerUnArticle(Inventaire inventaire, int id) {

        // Recherche l'article par son ID.
        Article articleSupprimer = trouverArticleId(inventaire, id);

        if (articleSupprimer == null) {
            // Si l'article n'est pas trouvé, affiche un message d'erreur.
            System.out.println(MSG_ERR_ARTICLE_INEXISTANT);
        } else {
            // Si l'article est trouvé, affiche ses détails.
            System.out.println(MSG_ARTICLE_TROUVE);
            afficherArticleSeul(articleSupprimer);
            System.out.println();

            // Demande à l'utilisateur de confirmer la suppression.
            char choix = demanderConfirmation(MSG_CONFIRMER_SUPPRESSION);

            if (choix == 'N') {
                // Si l'utilisateur annule la suppression, affiche un message.
                System.out.println(MSG_ARTICLE_NON_SUPPRIME);
            } else {
                // Si l'utilisateur confirme, supprime l'article.
                inventaire.setTabArticle(enleverArticleTableau(inventaire.getTabArticle(), articleSupprimer));
                System.out.println(MSG_ARTICLE_SUPPRIME);
            }
        }
    }

    /**
     * Demande à l'utilisateur de confirmer ou annuler une suppression.
     *
     * Cette méthode affiche un message de confirmation et attend que l'utilisateur entre 'O' pour confirmer
     * ou 'N' pour annuler. Si la saisie est invalide, l'utilisateur est invité à recommencer jusqu'à obtenir
     * une réponse valide.
     *
     * @return Le choix de l'utilisateur.
     */
    private static char demanderConfirmation(String message) {

        boolean saisieValide = false; // Indicateur pour vérifier si la saisie est valide.
        char choix = '0'; // Variable pour le choix de l'utilisateur.

        do {
            // Demande à l'utilisateur de confirmer ou annuler la suppression.
            System.out.print(message);
            String saisie = Clavier.lireString().trim().toUpperCase();

            // Vérifie si la saisie est valide (un seul caractère 'O' ou 'N').
            if (saisie.length() != 1 || (saisie.charAt(0) != 'O' && saisie.charAt(0) != 'N')) {
                // Si la saisie est invalide, affiche un message d'erreur.
                System.out.println(MSG_ERR_SAISIE_INVALIDE);
            } else {
                // Si la saisie est valide, assigne la valeur à `choix` et marque la saisie comme valide.
                choix = saisie.charAt(0);
                saisieValide = true;
            }

        } while (!saisieValide); // Répète tant qu'une saisie valide n'est pas effectuée.

        return choix; // Retourne le choix de l'utilisateur.
    }

    /**
     * Supprime un article d'un tableau d'articles en créant un nouveau tableau.
     *
     * Cette méthode prend un tableau d'articles et supprime l'article spécifié en fonction de son ID.
     * Elle crée un nouveau tableau contenant tous les articles, sauf celui à supprimer.
     *
     * @param tabArticle Le tableau d'articles initial.
     * @param article L'article à supprimer du tableau.
     * @return Un nouveau tableau d'articles sans l'article supprimé.
     */
    private static Article[] enleverArticleTableau(Article[] tabArticle, Article article) {

        // Crée un tableau temporaire avec une taille réduite de 1.
        Article[] tempTab = new Article[tabArticle.length - 1];

        // Trouve l'index de l'article à supprimer.
        int indexArticleSupprimer = trouverIndexArticleId(article.getId(), tabArticle);

        // Copie tous les articles sauf celui à supprimer, dans le nouveau tableau.
        for (int i = 0; i < tempTab.length; i++) {
            if (i < indexArticleSupprimer) {
                tempTab[i] = tabArticle[i]; // Copie les articles avant celui à supprimer.
            } else {
                tempTab[i] = tabArticle[i + 1]; // Copie les articles après celui à supprimer.
            }
        }

        // Retourne le nouveau tableau d'articles.
        return tempTab;
    }

    /**
     * Affiche les articles à risque dans l'inventaire.
     *
     * Cette méthode recherche tous les articles dont la quantité est inférieure ou égale à 10
     * et les affiche. Si aucun article à risque n'est trouvé, un message est affiché.
     *
     * @param inventaire L'inventaire contenant les articles à analyser.
     */
    public static void afficherArticleARisque(Inventaire inventaire) {

        // Recherche les articles à risque dans l'inventaire.
        Article[] articlesARisque = trouverArticlesARisque(inventaire.getTabArticle());

        if (articlesARisque.length == 0) {
            // Si aucun article à risque n'est trouvé, affiche un message informatif.
            System.out.println(MSG_AUCUN_ARTICLE_RISQUE);
        } else {
            // Si des articles à risque sont trouvés, affiche les détails.
            System.out.print(MSG_AFFICHER_ARTICLES_RISQUE);
            afficherArticlesLong(articlesARisque);
        }
    }

    /**
     * Trouve tous les articles à risque dans l'inventaire.
     *
     * Cette méthode parcourt tous les articles de l'inventaire et sélectionne ceux dont la quantité
     * est inférieure ou égale à 10. Elle retourne un tableau contenant uniquement les articles à risque.
     *
     * @param articles Le tableau des articles à analyser.
     * @return Un tableau contenant tous les articles à risque.
     */
    private static Article[] trouverArticlesARisque(Article[] articles) {

        // Compteur pour le nombre d'articles à risque.
        int nbARisque = 0;

        // Comptage du nombre d'articles à risque.
        for (Article article : articles) {
            if (article.getQuantite() <= 10) {
                nbARisque++; // Incrémente si l'article est à risque.
            }
        }

        // Crée un tableau pour stocker les articles à risque.
        Article[] articlesARisque = new Article[nbARisque];
        int j = 0; // Indice pour remplir le tableau des articles à risque.

        // Remplissage du tableau avec les articles à risque.
        for (int i = 0; i < articles.length; i++) {
            if (articles[i].getQuantite() <= 10) {
                articlesARisque[j] = articles[i]; // Ajoute l'article à risque dans le tableau.
                j++; // Passe à l'index suivant du tableau à risque.
            }
        }

        // Retourne le tableau des articles à risque.
        return articlesARisque;
    }


    /**
     * Permet à l'utilisateur de modifier les informations d'un article dans l'inventaire.
     *
     * Cette méthode permet à l'utilisateur de choisir quelle information d'un article modifier :
     * la description, le prix, ou les deux en même temps. Selon le choix de l'utilisateur,
     * la méthode demande la nouvelle valeur pour chaque champ et met à jour l'article en conséquence.
     *
     * @param inventaire L'inventaire contenant l'article à modifier.
     * @param id L'ID de l'article à modifier.
     */
    public static void modifierArticle(Inventaire inventaire, int id) {

        boolean saisieValide = false; // Indicateur pour vérifier si la saisie de l'utilisateur est valide.
        int choix = 0; // Variable pour stocker le choix de l'utilisateur.

        // Recherche l'article à modifier en fonction de son ID.
        Article article = trouverArticleId(inventaire, id);

        // Boucle de validation de la saisie de l'utilisateur pour choisir l'option de modification.
        do {
            System.out.print(MSG_CHOIX_MODIFICATION);

            String saisie = Clavier.lireString().trim(); // Récupère la saisie de l'utilisateur.

            try {
                // Vérifie que la saisie est un caractère unique.
                if (saisie.length() != 1) {
                    throw new NumberFormatException();
                }

                choix = Integer.parseInt(saisie); // Tente de convertir la saisie en entier.

                // Vérifie que le choix est entre 1 et 3.
                if (choix > 3 || choix < 1) {
                    throw new NumberFormatException();
                }

                saisieValide = true; // Si la saisie est valide, on sort de la boucle.

            } catch (NumberFormatException e) {
                // Affiche un message d'erreur si la saisie est invalide.
                System.out.println(MSG_ERR_CHOIX_MODIF_INVALIDE);
            }

        } while (!saisieValide); // Répète tant que la saisie n'est pas valide.

        // Traitement en fonction du choix de l'utilisateur.
        if (choix == 1) {
            // Modification de la description uniquement.
            System.out.print(MSG_NOUVELLE_DESCRIPTION);
            String description = Clavier.lireString().trim();
            modifierDescriptionArticle(article, description); // Appelle la méthode pour modifier la description.

        } else if (choix == 2) {

            // Modification du prix uniquement.
            boolean prixValide = false;
            double prix = -10; // Valeur par défaut invalide pour le prix.

            do {
                System.out.print(MSG_NOUVEAU_PRIX);
                String saisie = Clavier.lireString().trim();

                try {
                    prix = Double.parseDouble(saisie); // Tente de convertir la saisie en un nombre décimal.
                    prixValide = true; // Si la conversion réussit, on valide la saisie.
                } catch (NumberFormatException e) {
                    // Si la saisie n'est pas un nombre valide, affiche un message d'erreur.
                    System.out.println(MSG_ERR_VALEUR_NOMBRE_REEL);
                }
            } while (!prixValide); // Répète tant que le prix n'est pas valide.

            modifierPrixArticle(article, prix); // Modifie le prix de l'article.
        } else {
            // Modification de la description et du prix en une seule saisie.
            String description = "";
            double prix = -10;
            boolean chaineValide = false;

            do {
                System.out.print(MSG_DESCRIPTION_ET_PRIX);
                String saisie = Clavier.lireString().trim();
                String[] listeSaisie = saisie.split(";"); // Sépare la saisie en description et prix.

                if (listeSaisie.length != 2) {
                    // Si le format n'est pas valide (deux éléments séparés par un point-virgule), affiche un message d'erreur.
                    System.out.println(MSG_ERR_FORMAT_CHAINE_INVALIDE);
                } else {
                    try {
                        prix = Double.parseDouble(listeSaisie[1]); // Tente de convertir le prix.
                        description = listeSaisie[0]; // Si valide, affecte la description.
                        chaineValide = true; // Si valide, marque la saisie comme correcte.
                    } catch (NumberFormatException e) {
                        // Si le prix n'est pas valide, affiche un message d'erreur.
                        System.out.println(MSG_ERR_FORMAT_CHAINE_INVALIDE);
                    }
                }
            } while (!chaineValide); // Répète tant que la chaîne n'est pas valide.

            modifierPrixEtDescriptionArticle(article, description, prix); // Modifie la description et le prix de l'article.
        }
    }

    /**
     * Modifie la description d'un article.
     *
     * Cette méthode permet de modifier la description d'un article spécifique en la mettant à jour
     * avec la nouvelle valeur fournie.
     *
     * @param article L'article dont la description doit être modifiée.
     * @param description La nouvelle description à attribuer à l'article.
     */
    private static void modifierDescriptionArticle(Article article, String description) {

        // Modifie la description de l'article avec la valeur fournie.
        article.setDescription(description);
    }

    /**
     * Modifie le prix d'un article.
     *
     * Cette méthode permet de modifier le prix d'un article spécifique en le mettant à jour
     * avec la nouvelle valeur fournie.
     *
     * @param article L'article dont le prix doit être modifié.
     * @param prix Le nouveau prix à attribuer à l'article.
     */
    private static void modifierPrixArticle(Article article, double prix) {

        // Modifie le prix de l'article avec la valeur fournie.
        article.setPrix(prix);
    }

    /**
     * Modifie à la fois la description et le prix d'un article.
     *
     * Cette méthode permet de modifier à la fois la description et le prix d'un article spécifique
     * en appelant les méthodes appropriées pour chaque champ.
     *
     * @param article L'article dont les informations doivent être modifiées.
     * @param description La nouvelle description à attribuer à l'article.
     * @param prix Le nouveau prix à attribuer à l'article.
     */
    private static void modifierPrixEtDescriptionArticle(Article article, String description, double prix) {

        // Modifie la description de l'article.
        modifierDescriptionArticle(article, description);

        // Modifie le prix de l'article.
        modifierPrixArticle(article, prix);
    }

    /**
     * Effectue la facturation des articles achetés dans l'inventaire.
     *
     * Cette méthode permet à un utilisateur d'acheter des articles, de spécifier les quantités souhaitées,
     * et de générer une facture avec les informations suivantes : sous-total, TPS, TVQ et total.
     * Si la quantité demandée pour un article est insuffisante en stock, un message d'erreur est affiché.
     * Une fois l'achat terminé, la facture est affichée et sauvegardée dans un fichier texte.
     *
     * @param inventaire L'inventaire contenant les articles à acheter.
     */
    public static void facturer(Inventaire inventaire) {

        int id; // L'ID de l'article à acheter.
        double sousTotal = 0; // Le sous-total de la facture (avant taxes).
        double tps; // Le montant de la TPS (5% du sous-total).
        double tvq; // Le montant de la TVQ (9.95% du sous-total).
        double total; // Le total de la facture (sous-total + taxes).
        String nomFichier; // Le nom du fichier de la facture.
        boolean continuer = true; // Indicateur pour savoir si l'utilisateur souhaite continuer l'achat.
        boolean achat = false; // Indicateur pour savoir si au moins un achat a été effectué.

        // Création d'un objet StringBuilder pour générer la facture.
        StringBuilder facture = new StringBuilder();
        // En-tête de la facture.
        facture.append(String.format("FACTURE %d\n\n", inventaire.getNumFacture()));
        facture.append(String.format("%-12s%-70s%-10s%-10s\n", "ITEM", "DESCRIPTION", "QTÉ", "PRIX"));


        // Boucle pour traiter les achats d'articles.
        do {
            id = saisirID(); // Demande à l'utilisateur de saisir l'ID de l'article à acheter.
            Article article = trouverArticleId(inventaire, id); // Recherche l'article par son ID.

            if (article == null) {
                // Si l'article n'existe pas, affiche un message d'erreur.
                System.out.println(MSG_ERR_ARTICLE_INEXISTANT);
            } else {
                boolean valide = false; // Indicateur pour valider la saisie de la quantité.
                int quantiteAchat = 0; // La quantité d'article que l'utilisateur souhaite acheter.

                // Demande et valide la quantité d'article à acheter.
                do {
                    System.out.print(MSG_QUANTITE_ACHETE);
                    String quantiteS = Clavier.lireString().trim();
                    try {
                        quantiteAchat = Integer.parseInt(quantiteS); // Tente de convertir la saisie en entier.
                        valide = true; // Si la conversion réussit, la saisie est valide.
                    } catch (NumberFormatException e) {
                        System.out.println(MSG_ERR_QUANTITE_NON_ENTIERE);
                    }
                } while (!valide); // Répète tant que la quantité n'est pas valide.

                if (quantiteAchat <= 0) {
                    // Si la quantité est inférieure ou égale à zéro, affiche un message d'annulation d'achat.
                    System.out.println(MSG_ERR_QUANTITE_NEGATIVE_ZERO);
                } else {
                    // Vérifie si la quantité en stock est suffisante.
                    int quantiteStock = article.getQuantite();
                    if (quantiteStock - quantiteAchat < 0) {
                        // Si le stock est insuffisant, affiche un message d'erreur.
                        System.out.println(MSG_ERREUR_STOCK_INSUFFISANT);
                    } else {
                        // Si tout est valide, effectue l'achat.
                        achat = true;
                        article.setQuantite(quantiteStock - quantiteAchat); // Met à jour la quantité en stock.
                        sousTotal += quantiteAchat * article.getPrix(); // Ajoute le coût de l'article au sous-total.

                        // Ajoute les détails de l'achat à la facture.
                        facture.append(String.format("%-12d%-70s%-10d%-10.2f$\n", article.getId(), article.getDescription(), quantiteAchat, article.getPrix()));
                    }
                }
            }

            // Demande à l'utilisateur s'il souhaite continuer l'achat.
            char saisie = demanderConfirmation(MSG_CONFIRMER_CONTINUER);
            if (saisie == 'N') {
                continuer = false; // Si l'utilisateur choisit de ne pas continuer, quitte la boucle.
            }
        } while (continuer); // Répète tant que l'utilisateur souhaite continuer à acheter.

        // Si un ou plusieurs articles ont été achetés, génère et affiche la facture.
        if (achat) {
            // Calcule les taxes et le total de la facture.
            tps = sousTotal * 0.05;
            tvq = sousTotal * 0.0995;
            total = sousTotal + tps + tvq;

            // Ajoute le prix final et les taxes à la facture.
            facture.append("\n");
            facture.append(String.format("SOUS-TOTAL                                    %-10.2f$\n", sousTotal));
            facture.append(String.format("TPS                                           %-10.2f$\n", tps));
            facture.append(String.format("TVQ                                           %-10.2f$\n", tvq));
            facture.append(String.format("TOTAL                                         %-10.2f$\n", total));

            // Affiche la facture dans la console.
            System.out.println(facture);

            // Génère un fichier de facture avec le numéro de facture unique.
            nomFichier = String.format("facture%d.txt", inventaire.getNumFacture());

            // Incrémente le numéro de la prochaine facture.
            inventaire.setNumFacture(inventaire.getNumFacture() + 1);

            // Sauvegarde la facture dans un fichier texte.
            try (FileWriter canalEcriture = new FileWriter(nomFichier)) {
                canalEcriture.write(facture.toString());
                // Le fichier est automatiquement fermé grâce au try-with-resources.
            } catch (IOException e) {
                // Si une erreur d'écriture se produit, affiche un message d'erreur.
                System.out.println(MSG_ERR_ECRITURE);
            }
        }
    }
}