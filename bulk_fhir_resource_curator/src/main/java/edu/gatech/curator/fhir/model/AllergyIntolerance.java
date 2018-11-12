package edu.gatech.curator.fhir.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class AllergyIntolerance {

    private String clinicalStatus;
    private String resourceType;
    private String id;
    private Meta meta;
    private String verificationStatus;
    private String type;
    private List<String> category;
    private String criticality;
    private Code code;
    private Patient patient;
    private Date assertedDate;

    @JsonProperty("resourceType")
    public String getResourceType() {
        return resourceType;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("clinicalStatus")
    public String getClinicalStatus() {
        return clinicalStatus;
    }

    @JsonProperty("verificationStatus")
    public String getVerificationStatus() {
        return verificationStatus;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("criticality")
    public String getCriticality() {
        return criticality;
    }

    @JsonProperty("assertedDate")
    public Date getAssertedDate() {
        return assertedDate;
    }

    @JsonProperty("category")
    public List<String> getCategory() {
        return category;
    }

    @JsonProperty("code")
    public Code getCode() {
        return code;
    }

    @JsonProperty("patient")
    public Patient getPatient() {
        return patient;
    }

    public static class Meta {
        private List<String> profile;

        @JsonProperty("profile")
        public List<String> getProfile() {
            return profile;
        }
    }

    public static class Code {
        private List<Coding> coding;
        private String text;

        @JsonProperty("coding")
        public List<Coding> getCoding() {
            return coding;
        }

        @JsonProperty("text")
        public String getText() {
            return text;
        }

        public static class Coding {

            private String system;
            private String code;
            private String display;

            @JsonProperty("system")
            public String getSystem() {
                return system;
            }

            @JsonProperty("code")
            public String getCode() {
                return code;
            }

            @JsonProperty("display")
            public String getDisplay() {
                return display;
            }
        }
    }

    public static class Patient {

        private String reference;

        @JsonProperty("reference")
        public String getReference() {
            return reference;
        }
    }
}
