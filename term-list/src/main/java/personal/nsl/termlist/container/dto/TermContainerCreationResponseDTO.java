package personal.nsl.termlist.container.dto;

public class TermContainerCreationResponseDTO {
    private boolean duplicateName;
    private String name;
    private long code;
    
    private TermContainerCreationResponseDTO(boolean duplicateName,
                                             String name,
                                             long code) {
        this.duplicateName = duplicateName;
        this.name = name;
        this.code = code;
    }
    
    public static TermContainerCreationResponseDTO of(boolean duplicateName,
                                                      String name,
                                                      long code) {
        return new TermContainerCreationResponseDTO(duplicateName, name, code);
    }

    public boolean isDuplicateName() {
        return duplicateName;
    }

    public String getName() {
        return name;
    }

    public long getCode() {
        return code;
    }
}
