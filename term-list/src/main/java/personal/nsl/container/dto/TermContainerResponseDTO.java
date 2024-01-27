package personal.nsl.container.dto;

public class TermContainerResponseDTO {
    private long code;
    private String name;
    
    private TermContainerResponseDTO(long code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public static TermContainerResponseDTO of(long code, String name) {
        return new TermContainerResponseDTO(code, name);
    }

    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
