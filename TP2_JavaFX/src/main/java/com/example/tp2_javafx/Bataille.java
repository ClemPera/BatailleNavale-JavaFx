package com.example.tp2_javafx;

import java.util.Random;
import java.util.Scanner;

/**
 * Nom du programme : Bataille Navale
 * @author Clément Pera
 * Date : 06 Mars 2023
 * Résumé : Programme qui permet de jouer à la bataille navale contre un ordinateur
 * L'ordinateur et le joueur disposent d’une grille de 10
 * cases sur 10 cases, les colonnes de cette grille sont indiquées par une lettre de A à J et les
 * lignes sont numérotées de 1 à 10. Sur cette grille sont placés 5 bateaux en horizontal ou en
 * vertical. Le but de chaque joueur est de couler tous les bateaux de l’autre joueur
 */
public class Bataille {
    public static Random rand = new Random(); /** * Tire des entiers aléatoire entre a inclus et b exclu * @param a à partir de ce nombre * @param b à jusqu'à ce nombre b exclu * @return retourne un nombre entier aléatoire entre a et b */
    public static int randRange(int a, int b){
        return rand.nextInt(b-a)+a;
    }
    public static int[][] grilleOrdi = new int [10][10];
    public static int[][] grilleJeu = new int [10][10];

    /*** Vérifie si la position de placement du bateau est correct
     *
     * @param grille une grille
     * @param ligne un numéro de ligne
     * @param colonne un numéro de colonne (entre 0 et 9)
     * @param direction un entier codant une direction (1 pour horizontal et 2 pour vertical)
     * @param type donnant le type du bateau
     *
     * @return Renvoie "vraie" si on peut mettre le bateau sur les cases correspondantes et sinon renvoie "faux"
     */
    public static boolean posOk(int [][]grille, int ligne, int colonne, int direction, int type){
        int longueur = 0;

        if(type == 1){
            longueur = 5;
        } else if(type == 2){
            longueur = 4;
        } else if(type == 3){
            longueur = 3;
        } else if(type == 4){
            longueur = 3;
        } else if(type == 5){
            longueur = 2;
        }

        int verifLong = 0;

        if(direction == 1) { //Si c'est horizontal
            if ((colonne + longueur) <= 10) {
                for (int i = 0; i < longueur; i++) {
                    if (grille[ligne][colonne + i] == 0) {
                        verifLong++;
                    }
                }
            }
        } else if (direction == 2) {//Si c'est vertical
            if ((ligne + longueur) <= 10) {
                for (int i = 0; i < longueur; i++) {
                    if (grille[ligne + i][colonne] == 0) {
                        verifLong++;
                    }
                }
            }
        }

        if(verifLong==longueur)
            return true;
        else
            return false;
    }

    /**
     * La procédure va placer au hasard un bateau sur la grille "grilleOrdi"
     */
    public static int[] initGrilleOrdi(int type) {
        boolean ok = false;
        int ligne = 0, colonne = 0, numDirection = 0;

        while (!ok) {
            ligne = randRange(0, 10);
            colonne = randRange(0, 10);
            numDirection = randRange(1, 3); //1 pour horizontal et 2 pour vertical

            ok = posOk(grilleOrdi, ligne, colonne, numDirection, type);
        }

        ajoutBateau(grilleOrdi, ligne, colonne, numDirection, type);

        return new int[]{ligne, colonne, numDirection, type};
    }

    /**
     * Procédure pour ajouter un bateau à la grille spécifié
     *
     * @param grille Quelle grille ajouter un bateau
     * @param ligne numéro de ligne
     * @param colonne numéro de colonne
     * @param direction numéro de direction
     * @param type type de bateau
     */
    public static void ajoutBateau(int [][]grille,int ligne, int colonne, int direction, int type){
        int longueur = 0;

        if(type == 1){
            longueur = 5;
        } else if(type == 2){
            longueur = 4;
        } else if(type == 3){
            longueur = 3;
        } else if(type == 4){
            longueur = 3;
        } else if(type == 5){
            longueur = 2;
        }

        grille[ligne][colonne] = type;

        for (int i = 0; i < longueur; i++) {
            if(direction == 1) { //Si c'est horizontal
                grille[ligne][colonne + i] = type;
            }
            else {
                grille[ligne + i][colonne] = type;
            }
        }
    }

    /**
     * Affiche une grille de jeu
     *
     * @param grille grille à afficher
     */
    public static void AfficherGrille(int [][]grille){
        System.out.print("    ");
        for(char lettre = 'A'; lettre <= 'J'; lettre++){ //Affiche une ligne avec les lettres de A à J
            System.out.print(lettre);
            System.out.print(' ');
        }

        System.out.println();
        System.out.println("   --------------------");

        for(int ligne = 0; ligne < 10; ligne++){ //Affiche la grille avec les nombres de 0 à 9
            System.out.print(ligne);
            System.out.print(" | ");

            for(int colonne = 0; colonne < 10; colonne++){
                System.out.print(grille[ligne][colonne]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Fonction qui retourne "vrai" si le bateau est coulé sinon "faux"
     *
     * @param grille Sur quelle grille vérifier
     * @param type numéro du bateau compris entre 1 et 5
     */
    public static boolean couler(int [][]grille, int type) {
        for (int ligne = 0; ligne < 10; ligne++) {
            for (int colonne = 0; colonne < 10; colonne++) {
                if (grille[ligne][colonne] == type) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Affiche soit « Touché », soit « Coulé » (en indiquant de quel bateau il s’agit), soit « À l’eau ». Met aussi la grille à jour
     *
     * @param grille Sur quelle grille vérifier
     * @param ligne la ligne
     * @param colonne la colonne
     */
    public static int mouvement(int [][]grille, int ligne, int colonne){
        int numBateau;

        if(grille[ligne][colonne] != 0 && grille[ligne][colonne] != 6){
            numBateau = grille[ligne][colonne];
            grille[ligne][colonne] = 6;

            if(couler(grille, numBateau)){
                //System.out.print("Le bateau " + numBateau + " a été coulé!");
                return numBateau;
            } else{
                //System.out.print("Touché");
                return 8;
            }
        }
        else{
            //System.out.print("A l'eau");
            return 9;
        }
    }

    /**
     * Renvoie un tableau avec 2 entiers tiré au hasard entre 0 et 9 pour indiquer où l'ordinateur va tirer
     *
     * @return un tableau avec 2 entiers tiré au hasard entre 0 et 9
     */
    public static int[] tirOrdinateur(){
        int[] intTab = new int[2];

        intTab[0] = randRange(0,10);
        intTab[1] = randRange(0,10);

        return intTab;
    }

    /**
     * Retourne vrai s'il n'y a plus de bateau sur la grille envoyé
     *
     * @param grille sur quelle grille vérifier
     * @return vrai s'il n'y a plus de bateau sinon faux
     */
    public static boolean vainqueur(int [][]grille){
        for (int ligne = 0; ligne < 10; ligne++) {
            for (int colonne = 0; colonne < 10; colonne++) {
                if (grille[ligne][colonne] != 0 && grille[ligne][colonne] != 6) {
                    return false;
                }
            }
        }

        return true;
    }
}