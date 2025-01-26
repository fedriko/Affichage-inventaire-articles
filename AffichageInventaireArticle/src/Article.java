/* @author Federico Ezequiel Barallobres, Philippe Beaulieu Poulin
 * @version 12-decembre-2024
 * @code_permanent BARF24069501, BEAP10079006
 */

/**
 * Classe représentant un article dans l'inventaire.
 *
 * Chaque article possède un ID unique, une catégorie, une description,
 * une quantité disponible, et un prix unitaire.
 */
public class Article {

    // Identifiant unique de l'article.
    private int id;

    // Quantité disponible en stock pour cet article.
    private int quantite;

    // Prix unitaire de l'article.
    private double prix;

    // Catégorie à laquelle appartient l'article.
    private String categorie;

    // Brève description de l'article.
    private String description;

    /**
     * Constructeur principal permettant d'initialiser un article complet.
     *
     * @param id          Identifiant unique de l'article.
     * @param categorie   Catégorie de l'article.
     * @param description Description de l'article.
     * @param quantite    Quantité en stock.
     * @param prix        Prix unitaire.
     */
    public Article(int id, String categorie, String description, int quantite, double prix) {

        this.id = id;
        this.categorie = categorie;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
    }

    /**
     * Constructeur alternatif permettant d'initialiser uniquement l'ID.
     * Utile si les autres informations sont ajoutées ultérieurement.
     *
     * @param id Identifiant unique de l'article.
     */
    public Article(int id) {
        this.id = id;
    }

    /**
     * Setter pour l'identifiant de l'article.
     *
     * @param id Nouvel identifiant.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter pour le prix unitaire de l'article.
     *
     * @param prix Nouveau prix unitaire.
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Setter pour la quantité en stock pour cet article.
     *
     * @param quantite Nouvelle quantité disponible.
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Setter pour la catégorie de l'article.
     *
     * @param categorie Nouvelle catégorie.
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * Setter pour la description de l'article.
     *
     * @param description Nouvelle description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter pour l'identifiant unique de l'article.
     *
     * @return ID de l'article.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pour la quantité disponible en stock.
     *
     * @return Quantité disponible.
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Getter pour le prix unitaire de l'article.
     *
     * @return Prix de l'article.
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Getter pour la catégorie de l'article.
     *
     * @return Catégorie de l'article.
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Getter pour la description de l'article.
     *
     * @return Description de l'article.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Redéfinit la méthode `toString` pour fournir une représentation textuelle
     * claire de l'article.
     *
     * @return Représentation textuelle de l'article.
     */
    @Override
    public String toString() {

        return "Article: { ID: " + this.id + ", Catégorie: " + this.categorie + ", Description: " + this.description
                + ", Quantité: " + this.quantite + ", Prix: " + this.prix + " }";
    }
}

