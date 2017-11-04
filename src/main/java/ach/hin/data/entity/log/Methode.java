package ach.hin.data.entity.log;

public enum Methode {
	/**
	 * La méthode GET demande une représentation de la ressource spécifiée. Les
	 * requêtes GET doivent uniquement être utilisées afin de récupérer des données.
	 */
	GET,
	// La méthode HEAD demande une réponse identique à une requête GET pour laquelle
	// on aura omis le corps de la réponse (on a uniquement l'en-tête).

	HEAD,
	// La méthode POST est utilisée pour envoyer une entité vers la ressource
	// indiquée. Cela entraîne généralement un changement d'état ou des effets de
	// bord sur le serveur.
	POST,
	// La méthode PUT remplace toutes les représentations actuelles de la ressource
	// visée par le contenu de la requête.
	PUT, // La méthode DELETE supprime la ressource indiquée.
	DELETE,
	// La méthode CONNECT établit un tunnel vers le serveur identifié par la
	// ressource cible.
	CONNECT,
	// La méthode OPTIONS est utilisée pour décrire les options de communications
	// avec la ressource visée.

	OPTIONS,
	// La méthode TRACE réalise un message de test aller/retour en suivant le chemin
	// de la ressource visée.
	TRACE,
	// La méthode PATCH est utilisée pour appliquer des modifications partielles à
	// une ressource.

	PATCH;
	
	public static Methode byName(String value) {
		Methode[] values = Methode.values();
		for (Methode methode : values) {
			if(methode.name().equals(value)) {
				return methode;
			}
		}
		return null;
		
	}
}
