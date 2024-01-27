package personal.nsl.termlist.container.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import personal.nsl.termlist.container.domain.TermContainer;
import personal.nsl.termlist.container.repository.TermContainerRepository;

class MemoryTermContainerRepositoryTest {
    private TermContainerRepository testRepository;

    @BeforeEach
    void beforeEach() {
        this.testRepository = TermContainerRepository.getInstance();
    }

    @DisplayName("JUnit is running without problems.")
    @Test
    void test() {
        assertThatNoException().isThrownBy(() -> {
        });
    }

    @DisplayName("save test")
    @ValueSource(strings = { "1302414315:a", "137134815:b", "1:asodhvoief", "2:afyfeifhq" })
    @ParameterizedTest
    void save(String testCaseRaw) {
        TermContainer container = getContainer(testCaseRaw);

        testRepository.save(container);

        TermContainer found = testRepository.findByName(container.getName()).get();

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(container.getName());
        assertThat(found.getCode()).isEqualTo(container.getCode());
    }

    @DisplayName("delete test")
    @ValueSource(strings = { "1:asdvas", "2:rrrr", "3:51824" })
    @ParameterizedTest
    void delete(String testCaseRaw) {
        TermContainer container = getContainer(testCaseRaw);

        testRepository.save(container);

        TermContainer found = testRepository.findByName(container.getName()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(container.getName());
        assertThat(found.getCode()).isEqualTo(container.getCode());

        boolean success = testRepository.deleteByName(container.getName());

        assertThat(success).isTrue();

        boolean fail = testRepository.deleteByName(container.getName());

        assertThat(fail).isFalse();

        TermContainer mayNull = testRepository.findByName(container.getName()).orElse(null);

        assertThat(mayNull).isNull();
    }

    private TermContainer getContainer(String testCaseRaw) {
        final String separator = ":";
        String[] parsedRaw = testCaseRaw.split(separator);
        long code = Long.parseLong(parsedRaw[0]);
        String containerName = parsedRaw[1];
        TermContainer container = new TermContainer(code, containerName);
        return container;
    }
}
