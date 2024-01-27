package personal.nsl.termlist.container.service;

import java.util.Optional;

import personal.nsl.termlist.container.domain.CodeGenerator;
import personal.nsl.termlist.container.domain.ContainerConfig;
import personal.nsl.termlist.container.domain.TermContainer;
import personal.nsl.termlist.container.dto.TermContainerCreationResponseDTO;
import personal.nsl.termlist.container.dto.TermContainerResponseDTO;
import personal.nsl.termlist.container.repository.TermContainerRepository;

public class TermContainerServiceImpl implements TermContainerService {
    private final TermContainerRepository termContainerRepository;
    
    public TermContainerServiceImpl(TermContainerRepository termContainerRepository) {
        this.termContainerRepository = termContainerRepository;
    }

    @Override
    public TermContainerCreationResponseDTO addContainer(String name) {
        Optional<TermContainer> found = this.termContainerRepository.findByName(name);
        if (found.isPresent()) {
            return TermContainerCreationResponseDTO.of(true, name, 0);
        }
        
        long lowerBound = ContainerConfig.CONTAINER_CODE_LOWER_BOUND.get();
        long upperBound = ContainerConfig.CONTAINER_CODE_UPPER_BOUND.get();
        long randomCode = CodeGenerator.generate(lowerBound, upperBound);
        TermContainer newContainer = new TermContainer(randomCode, name);
        
        this.termContainerRepository.save(newContainer);
        
        return TermContainerCreationResponseDTO.of(false, name, randomCode);
    }

    @Override
    public boolean removeContainer(String name) {
        return this.termContainerRepository.deleteByName(name);
    }

    @Override
    public TermContainerResponseDTO getContainer(String name) {
        Optional<TermContainer> op = this.termContainerRepository.findByName(name);
        return TermContainerResponseDTO.of(op.isPresent(),
                                           op.orElse(new TermContainer(0, null)));
    }

}
