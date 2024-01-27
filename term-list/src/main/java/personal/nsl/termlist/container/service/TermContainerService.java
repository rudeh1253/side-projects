package personal.nsl.termlist.container.service;

import personal.nsl.termlist.container.dto.TermContainerCreationResponseDTO;
import personal.nsl.termlist.container.dto.TermContainerResponseDTO;
import personal.nsl.termlist.container.repository.TermContainerRepository;

public interface TermContainerService {
    
    public static TermContainerService getInstance() {
        return new TermContainerServiceImpl(TermContainerRepository.getInstance());
    }

    public TermContainerCreationResponseDTO addContainer(String name);
    
    public boolean removeContainer(String name);
    
    public TermContainerResponseDTO getContainer(String name);
}
