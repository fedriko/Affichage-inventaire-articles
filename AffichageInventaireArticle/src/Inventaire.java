/* @author Federico Ezequiel Barallobres, Philippe Beaulieu Poulin
 * @version 12-decembre-2024
 * @code_permanent BARF24069501, BEAP10079006
 */

/**
 * Classe représentant l'inventaire d'une succursale.
 * Permet de gérer les articles disponibles, ainsi que la numérotation des factures.
 */
public class Inventaire {

    // Nom de la succursale
    private String nomSuccursale;

    // Tableau des articles présents dans l'inventaire
    private Article[] tabArticle;

    // Numéro de la facture actuelle (initialisé à 100 par défaut)
    private int numFacture = 100;

    /**
     * Constructeur de la classe Inventaire.
     *
     * @param nomSuccursale Le nom de la succursale.
     * @param tabArticle    Tableau des articles de l'inventaire.
     * @param numFacture    Numéro de départ pour la facturation.
     */
    public Inventaire(String nomSuccursale, Article[] tabArticle, int numFacture) {

        this.nomSuccursale = nomSuccursale;
        this.tabArticle = tabArticle;
        this.numFacture = numFacture;
    }

    /**
     * Getter pour le tableau des articles.
     *
     * @return Le tableau des articles dans l'inventaire.
     */
    public Article[] getTabArticle() {
        return tabArticle;
    }

    /**
     * Setter pour mettre à jour le tableau des articles.
     *
     * @param tabArticle Nouveau tableau d'articles.
     */
    public void setTabArticle(Article[] tabArticle) {
        this.tabArticle = tabArticle;
    }

    /**
     * Setter pour le numéro de facture.
     *
     * @param numFacture Nouveau numéro de facture.
     */
    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    /**
     * Retourne un article à un indice donné.
     *
     * @param i Indice de l'article dans le tableau.
     * @return L'article à l'indice spécifié ou null si l'indice est invalide.
     */
    public Article retournerArticleIndice(int i) {

        try {
            return this.tabArticle[i];
        } catch (IndexOutOfBoundsException e) {
            return null; // Retourne null si l'indice dépasse les limites.
        }
    }

    /**
     * Getter pour le numéro de facture.
     *
     * @return Le numéro de la facture actuelle.
     */
    public int getNumFacture() {
        return this.numFacture;
    }
}

