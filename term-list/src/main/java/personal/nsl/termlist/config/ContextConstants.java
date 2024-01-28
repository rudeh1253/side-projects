package personal.nsl.termlist.config;

public enum ContextConstants {
    VIEW_LOCATION("WEB-INF/view/"),
    DTO_KEY("dto");
    
    private String value;

    private ContextConstants(String value) {
        this.value = value;
    }
    
    public String get() {
        return this.value;
    }
}
