package personal.nsl.container.domain;

public enum ContainerConfig {
    CONTAINER_CODE_LOWER_BOUND(10),
    CONTAINER_CODE_UPPER_BOUND(100000000000L);
    
    private long value;
    
    private ContainerConfig(long value) {
        this.value = value;
    }
    
    public long get() {
        return this.value;
    }
}
