#define _CRT_SECURE_NO_WARNINGS
#define _USE_MATH_DEFINES
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
#include <assert.h>
#include "functionDefine.h"
//gcc main.c cardFunction.c -o main <-- run this line terminal
//./main <-- then this
int main(void) {
    FILE* inp;
    char ans[5]; // answer if the user wants to play
    int ans2; // answer if the user wants to shuffle the deck
    int numPlayer; //number of players
    int topDeck = 107; // used for selecting the top card of the deck
    int numCard; //player defined card they pick. Answer if to choose a card or draw a card
    bool valid; // check if player played a valid card
    char color[10]; //for wild or plusfour card
    int k = 0; //controls player turn
    int rev = 1; // controls flow of players
    int w = 0; //checks the last players deck to see if  empty. If empty, they win

    inp = fopen("deck.txt","r"); //opens deck file
    if (inp == NULL) {
        printf("ERROR: Deck could not be openned\n");
        return -1;
    }
    card* deck = NULL;
    deck = (card*)malloc(216 * sizeof(card)); //allocates memory for deck
    for (int i = 0; i < 216; i++) { //reads deck
        fscanf(inp, "%s", deck[i].color);
        fscanf(inp, "%d", &deck[i].value);
        fscanf(inp, "%s", deck[i].action);
        deck[i].pt = NULL;
    }

    printf("Welcome to UNO. Would you like to play?\n");
    scanf("%s", ans);

    while (strcmp(ans, "yes") == 0 || strcmp(ans, "Yes") == 0) {
        system("clear");
        printf("\nPress [1] if you would you like to shuffle the deck.\n");
        printf("Otherwise, press [2] if you would like to use a pre-loaded a deck\n");
        scanf("%d", &ans2);
        system("clear");

        while (ans2 != 1 && ans2 != 2) {
            printf("Invalid input\n");
            printf("Press [1] if you would you like to shuffle the deck.\n");
            printf("Otherwise, press [2] if you would like to use a pre-loaded a deck\n");
            scanf("%d", &ans2);
            system("clear");
        }

        if (ans2 == 1) {
            shuffleCards(deck);
            printf("Cards have been shuffled\n\n");
        }

        printf("How many players are playing today?\n");
        scanf("%d", &numPlayer);
        system("clear");

        card* playerDecks[8] = { NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL };

        for (int i = 0; i < numPlayer; i++) { //creates first card in the hand
            playerDecks[i] = createHand(deck, &topDeck);
        }
        for (int i = 0; i < 9; i++) { // creates rest of cards in the hand
            for (int j = 0; j < numPlayer; j++) {
                addCard(deck, playerDecks[j], &topDeck);
            }
        }

        card active = createActive(deck, &topDeck);
        card* tempActive = &active;

        while (countCard(playerDecks[w]) != 0) {
            w = k;
            valid = false;
            while (valid != true) {
                printActive(tempActive);
                printf("Player %d press [0] to draw a card or pick a card from your hand?\n\n", (k + 1));
                printHand(playerDecks[k], countCard(playerDecks[w]));
                printf("\n");
                scanf("%d", &numCard);
                while ((numCard > countCard(playerDecks[k])) || (numCard < 0)) {
                    printf("\nInvalid input. Enter a number between 0 and %d.\n", countCard(playerDecks[k]));
                    printActive(tempActive);
                    printf("Player %d press [0] to draw a card or pick a card from your hand?\n\n", (k + 1));
                    printHand(playerDecks[k], countCard(playerDecks[w]));
                    printf("\n");
                    scanf("%d", &numCard);
                }
                if (numCard != 0) {
                    if (validCard(active, playerDecks[k], numCard)) {
                        active = playCard(playerDecks[k], numCard);
                        deleteCard(&playerDecks[k], numCard);
                        if ((strcmp(active.action, "reverse") == 0)) {
                            rev = rev * (-1);
                        }
                        else if ((strcmp(active.action, "skip") == 0)) {
                            k = incriment(rev, k, numPlayer);
                        }
                        else if ((strcmp(active.action, "plusTwo") == 0)) {
                            k = incriment(rev, k, numPlayer);
                            addCard(deck, playerDecks[k], &topDeck);
                            addCard(deck, playerDecks[k], &topDeck);
                        }
                        else if (strcmp(active.action, "wild") == 0) {
                            printf("What color would you like to change it to?\n");
                            scanf("%s", color);
                            while ((strcmp(color, "red") != 0) && (strcmp(color, "green") != 0) && (strcmp(color, "blue") != 0) && (strcmp(color, "yellow") != 0)) {
                                printf("Invalid color. Enter: red, green, blue, or yellow. Case Sensitive\n");
                                printf("What color would you like to change it to?\n");
                                scanf("%s", color);
                            }
                            if ((strcmp(color, "red") == 0) || (strcmp(color, "green") == 0) || (strcmp(color, "blue") == 0) || (strcmp(color, "yellow") == 0)) {
                                strcpy(active.color, color);
                            }
                        }
                        else if (strcmp(active.action, "wildFour") == 0) {
                            printf("What color would you like to change it to?\n");
                            scanf("%s", color);
                            while ((strcmp(color, "red") != 0) && (strcmp(color, "green") != 0) && (strcmp(color, "blue") != 0) && (strcmp(color, "yellow") != 0)) {
                                printf("Invalid color. Enter: red, green, blue, or yellow. Case Sensitive\n");
                                printf("What color would you like to change it to?\n");
                                scanf("%s", color);
                            }
                            if ((strcmp(color, "red") == 0) || (strcmp(color, "green") == 0) || (strcmp(color, "blue") == 0) || (strcmp(color, "yellow") == 0)) {
                                strcpy(active.color, color);
                            }
                            k = incriment(rev, k, numPlayer);
                            addCard(deck, playerDecks[k], &topDeck);
                            addCard(deck, playerDecks[k], &topDeck);
                            addCard(deck, playerDecks[k], &topDeck);
                            addCard(deck, playerDecks[k], &topDeck);

                        }
                        valid = true;
                    }
                    else {
                        printf("Invalid Card\n");
                    }
                }
                else if (numCard == 0) {
                    addCard(deck, playerDecks[k], &topDeck);
                    valid = true;
                }
            }
            system("clear");
            k = incriment(rev, k, numPlayer);
        }
        printf("CONGRATULATIONS!!! You Win!!!\n\n");
        printf("Would you like to play again? Enter yes or no.\n");
        scanf("%s", ans);
    }

    printf("\nThank you for playing, have a great rest of your day\n");
    return 0;
}
