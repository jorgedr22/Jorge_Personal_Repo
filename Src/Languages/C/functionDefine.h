typedef struct card_s {
    char color[7];
    int value;
    char action[15];
    struct card_s* pt;
} card;

void shuffleCards(card* deck);

card* createHand(card* deck, int* topDeck);

card createActive(card* deck, int* topDeck);

void printHand(card* hand, int c);

void printActive(card* active);

void addCard(card* deck, card* hand, int* topDeck);

void deleteCard(card** hand, int numCard);

card playCard(card* hand, int numCard);

bool validCard(card active, card* hand, int numCard);

int incriment(int rev, int k, int numPlayer);

int countCard(card* hand);
