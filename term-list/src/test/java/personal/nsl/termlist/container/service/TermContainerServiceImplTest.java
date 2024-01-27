package personal.nsl.termlist.container.service;

import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import personal.nsl.termlist.container.dto.TermContainerCreationResponseDTO;
import personal.nsl.termlist.container.dto.TermContainerResponseDTO;
import personal.nsl.termlist.container.repository.MemoryTermContainerRepository;
import personal.nsl.termlist.container.repository.TermContainerRepository;
import personal.nsl.termlist.container.service.TermContainerService;
import personal.nsl.termlist.container.service.TermContainerServiceImpl;

class TermContainerServiceImplTest {
    TermContainerService testService;
    TermContainerRepository testRepository;

    @BeforeEach
    void setUp() throws Exception {
        this.testRepository = new MemoryTermContainerRepository();
        this.testService = new TermContainerServiceImpl(testRepository);
    }

    @DisplayName("Test addContainer")
    @ValueSource(strings = { "", "a", "ab", "1", "13", "Container", "Sample" })
    @ParameterizedTest
    void testAddContainer(String containerName) {
        TermContainerCreationResponseDTO result =
                this.testService.addContainer(containerName);
        
        assertThat(result.getName()).isEqualTo(containerName);
        assertThat(result.isDuplicateName()).isFalse();
        
        TermContainerResponseDTO found =
                this.testService.getContainer(containerName);
        
        assertThat(result.getName()).isEqualTo(found.getTermContainer().getName());
    }
    
    @DisplayName("Test addContainer - duplicate")
    @ValueSource(strings = { "", "a", "ab", "1", "13", "Container", "Sample" })
    @ParameterizedTest
    void testAddContainer_addDuplicate(String containerName) {
        TermContainerCreationResponseDTO result =
                this.testService.addContainer(containerName);
        
        TermContainerCreationResponseDTO resultDuplicate =
                this.testService.addContainer(containerName);
        
        assertThat(resultDuplicate.getName()).isEqualTo(containerName);
        assertThat(resultDuplicate.isDuplicateName()).isTrue();
        
        TermContainerResponseDTO found =
                this.testService.getContainer(containerName);
        
        assertThat(result.getName()).isEqualTo(found.getTermContainer().getName());
    }

    @DisplayName("Test removeContainer")
    @ValueSource(strings = { "", "a", "ab", "abc", "abcd" })
    @ParameterizedTest
    void testRemoveContainer(String containerName) {
        this.testService.addContainer(containerName);
        
        assertThatNoException().isThrownBy(() ->
                        this.testRepository.findByName(containerName).orElseThrow());
        
        boolean result = this.testService.removeContainer(containerName);
        
        assertThat(result).isTrue();
        
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() ->
                        this.testRepository.findByName(containerName).orElseThrow());
    }

    @DisplayName("Test removeContainer - remove twice")
    @ValueSource(strings = { "", "a", "ab", "abc", "abcd" })
    @ParameterizedTest
    void testRemoveContainer_twice(String containerName) {
        this.testService.addContainer(containerName);
        
        assertThatNoException().isThrownBy(() ->
                        this.testRepository.findByName(containerName).orElseThrow());
        
        this.testService.removeContainer(containerName);
        
        boolean result = this.testService.removeContainer(containerName);
        
        assertThat(result).isFalse();
        
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() ->
                        this.testRepository.findByName(containerName).orElseThrow());
    }

}
