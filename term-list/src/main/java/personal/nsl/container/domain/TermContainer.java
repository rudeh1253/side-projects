package personal.nsl.container.domain;

public final class TermContainer {
    private long code;
    private String name;
    
    public TermContainer() {
    }

    public TermContainer(long code, String name) {
        this.code = code;
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}