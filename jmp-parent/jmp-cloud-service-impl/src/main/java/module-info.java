module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    requires java.sql;

    exports com.epam.jmp.sevice.impl;
}
