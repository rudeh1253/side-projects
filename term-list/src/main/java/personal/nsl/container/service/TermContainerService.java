package personal.nsl.container.service;

import personal.nsl.container.dto.TermContainerCreationResponseDTO;
import personal.nsl.container.dto.TermContainerResponseDTO;
import personal.nsl.container.repository.TermContainerRepository;

public interface TermContainerService {
    
    public static TermContainerService getInstance() {
        return new TermContainerServiceImpl(TermContainerRepository.getInstance());
    }

    public TermContainerCreationResponseDTO addContainer(String name);
    
    public boolean removeContainer(String name);
    
    public TermContainerResponseDTO getContainer(String name);
}
