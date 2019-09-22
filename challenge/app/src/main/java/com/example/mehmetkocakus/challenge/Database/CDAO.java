package com.example.mehmetkocakus.challenge.Database;

import android.content.Context;

import com.example.mehmetkocakus.challenge.Activities.Login.Model.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Bu class ROOM database'i oluşturmak için kullanılmıştır.
@Database(entities = {User.class}, version = 1)
public abstract class CDAO extends RoomDatabase {

    private static  CDAO INSTANCE;

    public abstract IDAO idaoUSER();

    public static CDAO getCDAO(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CDAO.class, "user-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return  INSTANCE;
    }

    public  static  void destroyInstance(){
        INSTANCE = null;
    }

}
