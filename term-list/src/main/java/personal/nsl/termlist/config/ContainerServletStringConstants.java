package personal.nsl.termlist.config;

public enum ContainerServletStringConstants {
    REQUEST_PARAM_KEY("name"),
    WRONG_PARAM_KEY("Cannot find request parameter");
    
    private String value;
    
    private ContainerServletStringConstants(String value) {
        this.value = value;
    }
    
    public String get() {
        return this.value;
    }
}
