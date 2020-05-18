class GameModel {
    val deck = Deck()
    val wastePile: MutableList<Card> = mutableListOf()

    val foundationPiles = arrayOf(
        FoundationPile(clubs),
        FoundationPile(diamonds), FoundationPile(hearts), FoundationPile(spades)
    )

    val tableauPiles = Array(7, {TableauPile()})

    /*Function to Reset the game*/
    fun resetGame(){
        //Clear wastePile first
        wastePile.clear()

        /*reset out foundation piles too
        for(pile in foundationPiles){
            pile.reset()
        } or Better:*/
        foundationPiles.forEach { it.reset() }

    }
}