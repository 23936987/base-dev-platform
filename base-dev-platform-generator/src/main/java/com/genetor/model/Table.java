package com.genetor.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private String schema;
    private String table;
    private String name;
    private String nameCn;
    private String label;
    private String comment;
    private List<Field> fields=new ArrayList<Field>();
    private List<Field> model_fields=new ArrayList<Field>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Field> getModel_fields() {
        return model_fields;
    }

    public void setModel_fields(List<Field> model_fields) {
        this.model_fields = model_fields;
    }
    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

}
