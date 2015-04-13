full-run:
	lein uberjar && java -jar target/hangman-*standalone.jar $*
