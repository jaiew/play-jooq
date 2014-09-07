# JOOQ Play 2.3 Module

This project provides a very simple Play plugin that will invoke the JOOQ generation for a database.
The plugin will be invoked on startup of a Play app but will not be executed in Prod mode.

In order for the plugin to know what database to use to generate the source code you need to specify some settings in the application.conf. An example for a H2 database is below.

    jooq.default.database.name="org.jooq.util.h2.H2Database"
    jooq.default.database.schema="PUBLIC"
    jooq.default.database.includes=".*"
    jooq.default.database.excludes="PLAY_EVOLUTIONS"
    jooq.default.generator.name="org.jooq.util.JavaGenerator"
    jooq.default.target.directory="./app"
    jooq.default.target.package="models.generated.h2"
    
For a list of the supported databases see the JOOQ documentation [here](http://www.jooq.org/learn.php).


Note the plugin is currently not available from maven so you will have to build it and publish it locally for the sample app to work.
