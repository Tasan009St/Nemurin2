package com.nemurin.st0321.nemurin2;

public enum PrefTimeEnum {
        defaultTime("40"),
        showerDef("40"),
        hobbyDef("40"),
        noInput("0"),
        ;

        private final String value;

        private  PrefTimeEnum(String value) {
            this.value = value;
        }

        public String getString() {
            return this.value;
        }
    }
