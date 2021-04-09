package feature.superhero.domain.models

data class Hero (
	val id : Int,
	val name : String,
	val powerstats : Powerstats,
	val biography : Biography,
	val appearance : Appearance,
	val work : Work,
	val connections : Connections,
	val image : Image
)