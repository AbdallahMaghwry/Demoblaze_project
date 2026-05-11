package com.demoblaze.drivers;

public enum Browsers {
    CHROME{
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeFactory();
        }
    },
    EDGE{
        public AbstractDriver getDriverFactory() {
            return new EdgeFactory();
        }
    };

    public abstract AbstractDriver getDriverFactory();
}
