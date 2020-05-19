object GamePresenter {
    var view: GameView? = null

    fun setGameView(gameView: GameView) {
        view = gameView
    }

    fun onDeckTap() {
        GameModel.onDeckTap()
        view?.upDateModel()
    }

    fun onWastTap() {
        GameModel.onWasteTap()
        view?.upDateModel()
    }

    fun onFoundationTap(foundationIndex: Int) {
        GameModel.onFoundationTap(foundationIndex)
        view?.upDateModel()
    }

    fun onTableauTap(tableauIndex: Int, cardIndex: Int) {
        GameModel.onTableauTap(tableauIndex, cardIndex)
        view?.upDateModel()
    }
}