package genericCheckpointing.xmlStoreRestore;

import java.util.List;
import java.util.ArrayList;
import genericCheckpointing.xmlStoreRestore.NameTypeValue;
import java.util.Iterator;

public class XMLValueStore{
    String className;
    List<NameTypeValue> fields;

    public XMLValueStore(){
        fields = new ArrayList<>();
    }
    
    public void setClassName(String classNameIn) {
        className = classNameIn;
    }

    public String getClassName(){
        return className;
    }

    public void addField(String fieldNameIn, String fieldTypeIn, String fieldValueIn){
        NameTypeValue newField = new NameTypeValue();
        newField.setFieldName(fieldNameIn);
        newField.setFieldType(fieldTypeIn);
        newField.setFieldValue(fieldValueIn);
        fields.add(newField);
    }

    public Iterator<NameTypeValue> getFieldIterator(){
        Iterator<NameTypeValue> listItr;

        listItr = fields.listIterator();
        return listItr;
    }

}