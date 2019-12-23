package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

public interface RestoreI extends StoreRestoreI{

    public SerializableObject readObj(String input);
    public void openReadFile(String fileNameIn);

}