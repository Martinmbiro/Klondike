class TableauPile(var cards: MutableList<Card> = mutableListOf()) {
    /*The last card in the Tableau needs to face up, so:*/
    init {
        if (cards.size > 0) {
            cards.last().faceUp = true
        }
    }

    /*Function to add cards to Tableau Piles*/
    fun addCards(newCards: MutableList<Card>): Boolean {
        /*We want to add a card if last value of the card in the Tableau pile is one
        * greater than the value of the card being added*/
        if (cards.size > 0) {
            if (newCards.first().value == cards.last().value - 1
                && suitCheck(newCards.first(), cards.last())
            ) {
                cards.addAll(newCards)
                return true
            }
        } else if (newCards.first().value == 12) {
            /*Otherwise, if the card being added is a King (value 12)*/
            cards.addAll(newCards)
            return true
        }
        return false;
    }

    /*Function to compare whether card suits are same or different*/
    private fun suitCheck(c1: Card, c2: Card): Boolean {
        if ((redSuits.contains(c1.suit) && blackSuits.contains(c2.suit))
            || (redSuits.contains(c2.suit) && blackSuits.contains(c1.suit))
        ) {
            return true
        }
        return false
    }

    /*Function to remove cards to Tableau Piles*/
    fun removeCards(tappedIndex: Int) {
        for (i in tappedIndex..cards.lastIndex) {
            cards.removeAt(tappedIndex)
        }

        if (cards.size > 0) {
            cards.last().faceUp = true
        }
    }
}