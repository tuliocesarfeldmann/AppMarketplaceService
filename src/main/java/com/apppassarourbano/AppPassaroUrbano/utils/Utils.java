package com.apppassarourbano.AppPassaroUrbano.utils;

import com.apppassarourbano.AppPassaroUrbano.config.security.CustomUser;
import com.apppassarourbano.AppPassaroUrbano.model.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static User getCurrentUser(){
        CustomUser userLogged = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userLogged.getUser();
    }
}
