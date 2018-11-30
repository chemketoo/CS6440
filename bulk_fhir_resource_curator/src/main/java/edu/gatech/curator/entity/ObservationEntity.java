package edu.gatech.curator.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "observation")
public class ObservationEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "issued")
    @Temporal(TemporalType.DATE)
    private Date issued;

    @Column(name = "code_text")
    private String codeText;

    @Column(name = "quantity_value")
    private double quantityValue;

    @Column(name = "quantity_unit")
    private String quantityUnit;

    @Column(name = "source_system_id")
    private long sourceSystemId;

    @Column(name = "source_system_name")
    private String sourceSystemName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public double getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(double quantityValue) {
        this.quantityValue = quantityValue;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public long getSourceSystemId() {
        return sourceSystemId;
    }

    public void setSourceSystemId(long sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    public String getSourceSystemName() {
        return sourceSystemName;
    }

    public void setSourceSystemName(String sourceSystemName) {
        this.sourceSystemName = sourceSystemName;
    }
}
