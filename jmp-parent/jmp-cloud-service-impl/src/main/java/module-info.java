module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.naming;

    exports com.epam.jmp.service.impl;
}
