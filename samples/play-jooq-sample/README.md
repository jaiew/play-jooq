# Sample Play application using JOOQ

This is a sample app that has generated JOOQ code for both a h2 and mysql database.
It's using Guice to inject the correct implementation of a helper that executes the JOOQ code to access the Database.

To run the h2 version simple do:

    > activator
    [play-jooq-sample] $ run

To run the mysql version you can either run it in Dev mode with the following:

    > activator
    [play-jooq-sample] $ run -Dconfig.file=conf/application.mysql.conf

Or run it in Production mode with

    > activator
    [play-jooq-sample] $ start -Dconfig.file=conf/application.mysql.conf
