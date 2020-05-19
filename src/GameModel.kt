/*The object key-word makes this class a Singleton*/
object GameModel {
    val deck = Deck()
    val wastePile: MutableList<Card> = mutableListOf()

    val foundationPiles = arrayOf(
        FoundationPile(clubs),
        FoundationPile(diamonds), FoundationPile(hearts), FoundationPile(spades)
    )

    val tableauPiles = Array(7, { TableauPile() })

    /*Function to Reset the game*/
    fun resetGame() {
        //Clear wastePile first
        wastePile.clear()

        /*reset out foundation piles too
        for(pile in foundationPiles){
            pile.reset()
        } or Better:*/
        foundationPiles.forEach { it.reset() }

        //Reset the deck
        deck.reset()

        //Set up Tableau Piles
        tableauPiles.forEachIndexed { index, tableauPile ->
            val cardsInPile: MutableList<Card> = Array(index + 1, { deck.drawCard() }).toMutableList()
            tableauPiles[index] = TableauPile(cardsInPile)
        }
    }

    /*Function to add Card to wastePile when deck is tapped*/
    fun onDeckTap() {
        if (deck.cardsInDeck.size > 0) {
            //Draw card first and make it face up
            val tappedCard = deck.drawCard()
            tappedCard.faceUp = true
            //Then add that card to the wastePile
            wastePile.add(tappedCard)
        } else {
            deck.cardsInDeck = wastePile.toMutableList()
            wastePile.clear()
        }
    }

    /*When user taps on the WastePile*/
    fun onWasteTap() {
        if (wastePile.size > 0) {
            val card = wastePile.last()
            if (playCard(card)) {
                wastePile.remove(card)
            }
        }
    }

    /*Handle taps on the Foundation Piles*/
    fun onFoundationTap(foundationIndex: Int) {
        val foundationPile = foundationPiles[foundationIndex]
        if (foundationPile.cards.size > 0) {
            val card = foundationPile.cards.last()
            if (playCard(card)) {
                foundationPile.removeCard(card)
            }
        }
    }

    /*Handle taps on Tableau Piles*/
    fun onTableauTap(tableauIndex: Int, cardIndex: Int) {
        val tableauPile = tableauPiles[tableauIndex]
        if (tableauPile.cards.size > 0) {
            val cards = tableauPile.cards.subList(cardIndex, tableauPile.cards.lastIndex + 1)
            if (playCards(cards)) {
                tableauPile.removeCards(cardIndex)
            }
        }
    }

    private fun playCards(cards: MutableList<Card>): Boolean {
        //If there's only one card in the list, pass it to playCard
        if (cards.size == 1) {
            return playCard(cards.first())
        } else {
            //When we're moving multiple cards from one Tableau Pile to another
            tableauPiles.forEach {
                if (it.addCards(cards)) {
                    return true
                }
            }
        }
        return false
    }

    private fun playCard(card: Card): Boolean {
        foundationPiles.forEach {
            if (it.addCard(card)) {
                return true
            }
        }
        tableauPiles.forEach {
            if (it.addCards(mutableListOf(card))) {
                return true
            }
        }
        return false
    }
}