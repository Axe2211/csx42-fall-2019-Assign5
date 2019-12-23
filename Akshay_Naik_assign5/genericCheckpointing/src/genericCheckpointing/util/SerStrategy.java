package genericCheckpointing.util;

import java.util.Iterator;

public interface SerStrategy{
    public void processInput(SerializableObject sObject);
    public Iterator<String> serialOutputItr();
}