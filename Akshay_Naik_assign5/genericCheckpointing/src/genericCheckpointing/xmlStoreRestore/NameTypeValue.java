package genericCheckpointing.xmlStoreRestore;

public class NameTypeValue{
    private String fieldName;
    private String fieldType;
    private String fieldValue;

    public void setFieldName(String fieldNameIn){
        fieldName = fieldNameIn;
    }

    public void setFieldType(String fieldTypeIn){
        fieldType = fieldTypeIn;
    }
    
    public void setFieldValue(String fieldValueIn){
        fieldValue = fieldValueIn;
    }

    public String getFieldName(){
        return fieldName;
    }

    public String getFieldType(){
        return fieldType;
    }
    
    public String getFieldValue(){
        return fieldValue;
    }
}