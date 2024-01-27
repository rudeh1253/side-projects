package personal.nsl.termlist.container.dto;

import personal.nsl.termlist.container.domain.TermContainer;

public class TermContainerResponseDTO {
    private final boolean present;
    private final TermContainer termContainer;
    
    private TermContainerResponseDTO(boolean present, TermContainer termContainer) {
        this.present = present;
        this.termContainer = termContainer;
    }
    
    public static TermContainerResponseDTO of(boolean present,
                                              long code,
                                              String name) {
        return new TermContainerResponseDTO(present, new TermContainer(code, name));
    }
    
    public static TermContainerResponseDTO of(boolean present,
                                              TermContainer termContainer) {
        return new TermContainerResponseDTO(present, termContainer);
    }
    
    public boolean isPresent() {
        return this.present;
    }

    public TermContainer getTermContainer() {
        return this.termContainer;
    }
}
