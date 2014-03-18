# JOOQ Play 2.2 Module
update support to play 2.2.2

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
    
    //this is optional 
    jooq.default.strategy.name="org.jooq.util.DefaultGeneratorStrategy"
    jooq.default.generate.relations=true
    jooq.default.generate.deprecated=true
    jooq.default.generate.instanceFields=true
    jooq.default.generate.generatedAnnotation=true
    jooq.default.generate.records=true
    jooq.default.generate.pojos=false
    jooq.default.generate.immutablePojos=false
    jooq.default.generate.interfaces=false
    jooq.default.generate.daos=false
    jooq.default.generate.jpaAnnotations=false
    jooq.default.generate.validationAnnotations=false
    jooq.default.generate.globalObjectReferences=true
    jooq.default.generate.fluentSetters=false



For a list of the supported databases see the JOOQ documentation [here](http://www.jooq.org/learn.php).


Note the plugin is currently not available from maven so you will have to build it and publish it locally for the sample app to work.
