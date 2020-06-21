package com.company.Controller;

import com.company.View.UiManager;

public class Startup {
    public static Startup StartupBuilder(){
        return new Startup();
    }
    public void start(){
        new UiManager();
    }
}
