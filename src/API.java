public enum API{
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new IMDbContentExtractor()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=I3h9qmQ8M6FxO5Xjen35PkgWhLGFSzg4cmKoRuke", new NasaContentExtractor()),
    TOP_LANGUAGES("http://localhost:8080/languages", new IMDbContentExtractor());

    private String url;
    private ContentExtractor extractor;

    API(String url, ContentExtractor extractor){
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl() {
        return url;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }
}