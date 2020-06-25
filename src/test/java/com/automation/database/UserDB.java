package com.automation.database;

import com.automation.utils.DBUtility;

public class UserDB {

    public boolean checkUserExistance(String email){
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = '"+email+"'";

       long count = (Long) DBUtility.getCellValue(query);

        return count ==1;
    }
}
