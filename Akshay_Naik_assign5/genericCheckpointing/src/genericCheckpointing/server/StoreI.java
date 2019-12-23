package genericCheckpointing.server;

import genericCheckpointing.server.StoreRestoreI;
// import genericCheckpointing.util.MyAllTypesFirst;
// import genericCheckpointing.util.MySpecialTypes;
import genericCheckpointing.util.SerializableObject;

public interface StoreI extends StoreRestoreI{

    public void writeObj(SerializableObject myFirstIn, String wireFormat);
    //public void writeObj(MySpecialTypes mySpecialIn, String wireFormat);

}