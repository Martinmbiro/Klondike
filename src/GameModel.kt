class GameModel {
    val deck = Deck()
    val wastPile: MutableList<Card> = mutableListOf()

    val foundationPiles = arrayOf(
        FoundationPile(clubs),
        FoundationPile(diamonds), FoundationPile(hearts), FoundationPile(spades)
    )

    val tableauPiles = Array(7, {TableauPile()})
}