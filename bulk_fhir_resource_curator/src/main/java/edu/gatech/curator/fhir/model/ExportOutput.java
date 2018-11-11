package edu.gatech.curator.fhir.model;

public class ExportOutput {
    private String type;

    private int count;

    private String url;

    protected ExportOutput() {}

    public ExportOutput(String type, int count, String url) {
        this.type = type;
        this.count = count;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }
}
