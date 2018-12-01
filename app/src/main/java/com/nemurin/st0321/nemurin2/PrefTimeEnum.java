package com.nemurin.st0321.nemurin2;

public enum PrefTimeEnum {
        DEFTIME("40"),
        DEFNumger("0"),
        NOINPUT("0"),
        ;

        private final String value;

        private  PrefTimeEnum(String value) {
            this.value = value;
        }

        public String getString() {
            return this.value;
        }
    }
