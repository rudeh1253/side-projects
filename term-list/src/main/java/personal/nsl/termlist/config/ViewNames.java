package personal.nsl.termlist.config;

public enum ViewNames {
    CONTAINER("container.jsp"),
    CONTAINER_CREATION_SUCCESS("container-creation-success.jsp");
    
    private String value;
    
    private ViewNames(String value) {
        this.value = value;
    }
    
    public String get() {
        return this.value;
    }
}
