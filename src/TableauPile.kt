class TableauPile(var cards: MutableList<Card>) {
    /*Initialize the last card in the Tableau pile to face up*/
    init {
        cards.last().faceUp = true
    }
}