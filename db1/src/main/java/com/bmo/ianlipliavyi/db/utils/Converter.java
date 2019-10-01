package com.bmo.ianlipliavyi.db.utils;

import com.bmo.ianlipliavyi.db.model.UserDBModel;
import com.bmo.ianlipliavyi.global.model.UserModel;

public class Converter {

    public static UserModel convert(final UserDBModel parm){
        UserModel userModel = new UserModel();

        userModel.setUid(parm.getUid());
        userModel.setName(parm.getName());
        userModel.setLast(parm.getLast());

        return userModel;
    }


    public static UserDBModel convert(final UserModel parm){
        UserDBModel userModel = new UserDBModel();

        userModel.setUid(parm.getUid());
        userModel.setName(parm.getName());
        userModel.setLast(parm.getLast());

        return userModel;
    }
}
