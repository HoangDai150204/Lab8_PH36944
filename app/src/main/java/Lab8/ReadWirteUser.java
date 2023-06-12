package Lab8;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadWirteUser {
    Context context;

    public ReadWirteUser(Context context) {
        this.context = context;
    }
    //hàm ghi dữ liệu vào file
    public void writeUser(Context context, String fname, User user){
        ArrayList<User> list = new ArrayList<>();
        try {
            FileOutputStream f=context.openFileOutput(fname, Context.MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream(f);
            list.add(user);
            o.writeObject(list);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // hàm đọc dữ liệu từ file
    public ArrayList<User> readUser(Context context, String fname){
        ArrayList<User> list = new ArrayList<>();
        try {
            FileInputStream f = context.openFileInput(fname);
            ObjectInputStream o = new ObjectInputStream(f);
            list=(ArrayList<User>)o.readObject();
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
